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
import java.util.ArrayList;
import java.util.List;

public class RepositorioOrganizacao {

    private List<Organizacao> listaOrganizacoes = new ArrayList<>();
    private Plataforma plataforma;
    Colaborador colaborador;

    public RepositorioOrganizacao(Plataforma plataforma) {
        this.plataforma = plataforma;
    }
    
    public RepositorioOrganizacao(List<Organizacao> listaOrganizacoes){
        this.listaOrganizacoes = listaOrganizacoes;
    }

    public Organizacao novaOrganizacao(String nome, String nif, String enderecoLocal,
                                       String enderecoPostal, String localidade,
                                       String telefone, Website website, Email emailOrganizacao,
                                       String nomeGestor, String funcao, String telefoneGestor,
                                       Email emailGestor) {
        EnderecoPostal endereco = Organizacao.novoEndereco(enderecoLocal, enderecoPostal, localidade);
        //colaborador = Organizacao.novoColaborador(nomeGestor, funcao, telefoneGestor, emailGestor);

        return new Organizacao(nome, nif, endereco, telefone, website, emailOrganizacao, colaborador);
    }

    public boolean validaOrganizacao(Organizacao organizacao) {
        return true;
    }

    public void registaOrganizacao(Organizacao organizacao) throws Exception {
        if(validaOrganizacao(organizacao)) {
            IAlgoritmoGeradorPasswords algoritmoGeradorPasswords = plataforma.getAlgoritmoGeradorPwd();
            Password password = algoritmoGeradorPasswords.geraPassword();

           // plataforma.getUsersAPI().registerUserWithRoles(colaborador.getEmailGestor(), colaborador.getEmailGestor(), password, "gestor,colaborador");

            addOrganizacao(organizacao);

            //gravar

            //Enviar email com credenciais de acesso
            /*Email email = new Email();
            email.setTo(colaborador.getEmailGestor());
            email.setSubject("Envio de credenciais para acesso à plataforma");
            email.setText("username " + colaborador.getEmailGestor() + " ... password " + password);
            email.send();*/
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
