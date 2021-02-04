/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.grupo2.t4j.repository;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.exception.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioOrganizacao implements Serializable{

    private static RepositorioOrganizacao instance;

    private List<Organizacao> listaOrganizacoes = new ArrayList<>();
    private Plataforma plataforma;
    private Organizacao organizacao;
    Colaborador colabGestor;

    public Organizacao getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(Organizacao organizacao) {
        novaOrganizacao(organizacao.getNome(),
                organizacao.getNif(),
                organizacao.getEnderecoPostal().getArruamento(),
                organizacao.getEnderecoPostal().getPorta(),
                organizacao.getEnderecoPostal().getLocalidade(),
                organizacao.getEnderecoPostal().getCodigoPostal(),
                organizacao.getTelefone(),
                organizacao.getWebsite(),
                organizacao.getEmail(),
                organizacao.getColabGestor().getNome(),
                organizacao.getColabGestor().getEmail(),
                organizacao.getColabGestor().getTelefone(),
                organizacao.getColabGestor().getRolename()
                );
    }

    private RepositorioOrganizacao() {
        listaOrganizacoes = new ArrayList<>();
    }

    public static RepositorioOrganizacao getInstance() {
        if(instance == null) {
            instance = new RepositorioOrganizacao();
        }
        return instance;

    }

    public Organizacao novaOrganizacao(String nome, String nif, String arruamento,
                                       String numeroPorta, String localidade, String codigoPostal,
                                       String telefone, Website website, Email emailOrganizacao,
                                       String nomeGestor, Email emailGestor, String telefoneGestor,
                                       Rolename rolename) {
        EnderecoPostal endereco = Organizacao.novoEndereco(arruamento, numeroPorta, localidade, codigoPostal);
        colabGestor = Organizacao.novoColaborador(nomeGestor, emailGestor, telefoneGestor, rolename);

        return new Organizacao(nome, nif, endereco, website, telefone, emailOrganizacao, colabGestor);
    }

    public boolean validaOrganizacao(Organizacao organizacao) throws IllegalArgumentException{

        try {
            return true;
        }
        catch (OrganizacaoInvalidaException exception) {
            return false;
        }
    }

    public void registaOrganizacao(Organizacao organizacao) throws Exception {
        if(validaOrganizacao(organizacao)) {
            IAlgoritmoGeradorPasswords algoritmoGeradorPasswords = plataforma.getAlgoritmoGeradorPwd();
            Password password = new Password(algoritmoGeradorPasswords.geraPassword());

           plataforma.getUsersAPI().registerUserWithRoles(colabGestor.getNome(), colabGestor.getEmail(), password, "gestor,colaborador");

            addOrganizacao(organizacao);

            //gravar

            //Enviar email com credenciais de acesso
            Email email = new Email();
            email.setTo(colabGestor.getEmail().toString());
            email.setSubject("Envio de credenciais para acesso à plataforma");
            email.setText("username " + colabGestor.getEmail() + " ... password " + password);
            email.send();
        }
        else {
            throw new Exception();
        }
    }

    public void addOrganizacao(Organizacao organizacao) throws OrganizacaoDuplicadaException {
        Organizacao o = getOrganizacaoByNif(organizacao.getNif());
        if (o == null) {
            this.listaOrganizacoes.add(organizacao);
        } else {
            throw new OrganizacaoDuplicadaException(o.getNif() + ": Organização já registada!");
        }
    }
    
    private Organizacao getOrganizacaoByNif(String NIF) {
        Organizacao organizacao = null;
        for (int i = 0; i < this.listaOrganizacoes.size(); i++) {
            organizacao = this.listaOrganizacoes.get(i);
            if (organizacao.getNif().equals(NIF)) {
                Organizacao copia = new Organizacao(organizacao);
                return copia;
            }
        }
        return null;
    }
}
