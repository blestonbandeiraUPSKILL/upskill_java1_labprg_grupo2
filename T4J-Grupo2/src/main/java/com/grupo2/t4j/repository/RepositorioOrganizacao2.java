/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


package com.grupo2.t4j.repository;

/**
 *
 * @author CAD
 *//*



import com.grupo2.t4j.model.*;
import com.grupo2.t4j.exception.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioOrganizacao2 {
   /*
    private RepositorioOrganizacao2 repositorioOrganizacao;
    
    private List<Organizacao> listaOrganizacoes;
    
    private Plataforma plataforma;
    private Colaborador colabGestor;
    
    private RepositorioOrganizacao2() {
        listaOrganizacoes = new ArrayList<>();
    }
    
    public void addOrganizacao(Organizacao organizacao) throws OrganizacaoDuplicadaException {
        Organizacao o = getOrganizacaoByNif(organizacao.getNif());
        if (o == null) {
            this.listaOrganizacoes.add(organizacao);
        } else {
            throw new OrganizacaoDuplicadaException(o.getNif() + ": Organização já registada!");
        }
    }
    
    public void addOrganizacao(String nome, String NIF, EnderecoPostal enderecoOrg, 
            Website websiteOrg, String telefone, Email emailOrg, Colaborador 
            colabGestor) throws OrganizacaoDuplicadaException {
        Organizacao o = getOrganizacaoByNif(NIF);
        if (o == null) {
            Organizacao organizacao = new Organizacao(nome, NIF, enderecoOrg, websiteOrg,
            telefone, emailOrg, colabGestor);
            this.listaOrganizacoes.add(organizacao);
        } else {
            throw new OrganizacaoDuplicadaException(o.getNif() + ": Organização já registada!");
        }
    }
    
    public void addOrganizacao(String nome, String NIF, String arruamento, String numeroPorta,
            String localidade, String codigoPostal, Website websiteOrg, String telefone,
            Email emailOrg, Colaborador colabGestor) throws OrganizacaoDuplicadaException {
        Organizacao o = getOrganizacaoByNif(NIF);
        if (o == null) {
            EnderecoPostal enderecoOrg = new EnderecoPostal(arruamento, numeroPorta,
            localidade, codigoPostal);
            Organizacao organizacao = new Organizacao(nome, NIF, enderecoOrg, websiteOrg,
            telefone, emailOrg, colabGestor);
            this.listaOrganizacoes.add(organizacao);
        } else {
            throw new OrganizacaoDuplicadaException(o.getNif() + ": Organização já registada!");
        }
    }
    
    public void addOrganizacao(String nome, String NIF, String arruamento, String numeroPorta,
            String localidade, String codigoPostal, Website websiteOrg, String telefone,
            Email emailOrg, String nomeColab, String emailColab, Password password,
            String funcao, String telefoneColab) throws OrganizacaoDuplicadaException {
        Organizacao o = getOrganizacaoByNif(NIF);
        if (o == null) {
            EnderecoPostal enderecoOrg = new EnderecoPostal(arruamento, numeroPorta,
            localidade, codigoPostal);
            Colaborador gestor = new Colaborador(nomeColab, emailColab, password, 
                    funcao, telefoneColab);
            Organizacao organizacao = new Organizacao(nome, NIF, enderecoOrg, websiteOrg,
            telefone, emailOrg, gestor);
            this.listaOrganizacoes.add(organizacao);
        } else {
            throw new OrganizacaoDuplicadaException(o.getNif() + ": Organização já registada!");
        }
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

    }*/
}    

