/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.*;
import java.io.Serializable;
import java.util.Objects;

public class Organizacao implements Serializable{
    
    private String nome;
    private String NIF;
    private EnderecoPostal enderecoOrg;
    private Website websiteOrg;
    private String telefone;
    private Email emailOrg;
    
    private Colaborador colabGestor;
    
    private static final EnderecoPostal ENDERECO_POR_OMISSAO = new EnderecoPostal("Rua ", "s/n", "Portugal", "1111-111");
    //private static final Website WEBSITE_POR_OMISSAO = new Website("");
    private static final String TELEFONE_ORG_POR_OMISSAO = "999999999";
        
    public Organizacao(){
    }
    
    public Organizacao(String nome, String NIF, EnderecoPostal enderecoOrg,
            Website websiteOrg, String telefone, Email emailOrg, Colaborador 
            colabGestor){
        setWebsite(websiteOrg);
        /*if (websiteOrg instanceof Website) {
            this.websiteOrg = websiteOrg;
        }
        else {
            this.websiteOrg = new Website(Objects.requireNonNull(websiteOrg));
        }*/
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(enderecoOrg);
        //this.websiteOrg = new Website(websiteOrg);
        setTelefone(telefone);
        this.emailOrg = new Email(emailOrg);
        this.colabGestor = new Colaborador(colabGestor);
    }
    
    public Organizacao(String nome, String NIF, String arruamento, String numeroPorta,
            String localidade, String codigoPostal, Website websiteOrg, String telefone,
            Email emailOrg, Colaborador colabGestor){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(arruamento, numeroPorta, localidade, codigoPostal);
        setWebsite(websiteOrg);
      /*  if (websiteOrg instanceof Website) {
            this.websiteOrg = websiteOrg;
        }
        else {
            this.websiteOrg = new Website(websiteOrg);
        }*/
        //this.websiteOrg = new Website(websiteOrg);
        setTelefone(telefone);
        this.emailOrg = new Email(emailOrg);
        this.colabGestor = new Colaborador(colabGestor);
    }
    
    /*public Organizacao(String nome, String NIF, String arruamento, String numeroPorta,
            String localidade, String codigoPostal, String website, String telefone,
            String email, Colaborador colabGestor){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(arruamento, numeroPorta, localidade, codigoPostal);
        setWebsite(website);
      *//*  if (websiteOrg instanceof Website) {
            this.websiteOrg = websiteOrg;
        }
        else {
            this.websiteOrg = new Website(websiteOrg);
        }*//*
        //this.websiteOrg = new Website(website);
        setTelefone(telefone);
        this.emailOrg = new Email(email);
        this.colabGestor = new Colaborador(colabGestor);
    }*/
    
    /*public Organizacao(String nome, String NIF, String arruamento, String numeroPorta,
            String localidade, String codigoPostal, String website, String telefone,
            String email, String nomeColab, String emailColab, Password password,
            String funcao, String telefoneColab){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(arruamento, numeroPorta, localidade, codigoPostal);
        setWebsite(website);
        //this.websiteOrg = new Website(website);
       *//* if (websiteOrg instanceof Website) {
            this.websiteOrg = websiteOrg;
        }
        else {
            this.websiteOrg = new Website(websiteOrg);
        }*//*
        setTelefone(telefone);
        this.emailOrg = new Email(email);
        this.colabGestor = new Colaborador(nomeColab, emailColab, password, funcao, telefoneColab);
    }*/
    
    public Organizacao(String nome, String NIF, String arruamento, String numeroPorta,
            String localidade, String codigoPostal, String website, String telefone,
            String email, String nomeColab, String emailColab, String passColab,
            String funcao, String telefoneColab){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(arruamento, numeroPorta, localidade, codigoPostal);
        this.websiteOrg = new Website(website);
        setTelefone(telefone);
        this.emailOrg = new Email(email);
        this.colabGestor = new Colaborador(nomeColab, emailColab, passColab, funcao, telefoneColab);
    }
    
   /* public Organizacao(String nome, String NIF, String email,Colaborador colabGestor){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = ENDERECO_POR_OMISSAO;
        this.websiteOrg = WEBSITE_POR_OMISSAO;
        setTelefone(TELEFONE_ORG_POR_OMISSAO);
        this.emailOrg = new Email(email);
        this.colabGestor = new Colaborador(colabGestor);
    }*/
    
    public Organizacao(Organizacao organizacao){
        setNome(organizacao.getNome());
        setNif(organizacao.getNif());
        this.enderecoOrg = new EnderecoPostal(organizacao.getEnderecoPostal());
        this.websiteOrg = new Website(organizacao.getWebsite());
        setTelefone(organizacao.getTelefone());
        this.emailOrg = new Email(organizacao.getEmail());
        this.colabGestor = new Colaborador(organizacao.getColabGestor());
    }

    public Organizacao(String nomeOrganizacao, String nif,
                       String arruamento, String numeroPorta,
                       String localidade, String codPostal,
                       String telefoneOrg, Website website,
                       Email emailOrg, String nomeGestor,
                       Email emailGestor, String telefoneGestor,
                       Rolename gestor) {
        setNome(nomeOrganizacao);
        setNif(nif);
        this.enderecoOrg = new EnderecoPostal(arruamento, numeroPorta, localidade, codPostal);
        setTelefone(telefoneOrg);
        setWebsite(website);
        setEmail(emailOrg);
        this.colabGestor = new Colaborador(nomeGestor, emailGestor, telefoneGestor, gestor);
    }

    public Colaborador getColabGestor() {
        return colabGestor;
    }

    public Organizacao(String nomeOrganizacao, String nif, String telefone,
                       Website website, Email email, EnderecoPostal enderecoPostal) {
        setNome(nomeOrganizacao);
        setNif(nif);
        setTelefone(telefone);
        setWebsite(website);
        setEmail(new Email(email));
        setEnderecoPostal(enderecoPostal);
    }

    public final void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é inválido!");
        }
        this.nome = nome;
    }
    
    public void setNif(String NIF) throws NifInvalidoException {
        long numNif = Long.parseLong(NIF);
        if (numNif >= 100000000 && numNif <= 999999999) {
            this.NIF = NIF;
        } else {
            throw new NifInvalidoException(NIF + ": NIF inválido!");
        }
    }
    
    public void setEnderecoPostal(EnderecoPostal enderecoOrg){
        this.enderecoOrg = enderecoOrg;
    }
    
    public void setWebsite(Website websiteOrg){
        this.websiteOrg = websiteOrg;
    }
    
    public final void setTelefone(String telefone) {
        int numero = Integer.parseInt(telefone);
        if (numero < 100000000 || numero > 999999999) {
            throw new IllegalArgumentException("Número é inválido!");
        }
        this.telefone = telefone;
    }  
    
    
    public final void setEmail(Email emailOrg){
        this.emailOrg = emailOrg;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getNif(){
        return NIF;
    }
    
    public EnderecoPostal getEnderecoPostal(){
        EnderecoPostal endereco = new EnderecoPostal(enderecoOrg);
        return endereco;
    }
    
    public Website getWebsite(){
        Website website = new Website(websiteOrg);
        return website;
    }
    
    public String getTelefone(){
        return telefone;
    }
    
    public Email getEmail(){
        Email email = new Email(emailOrg);
        return email;
    }

    public static EnderecoPostal novoEndereco(String arruamento, String numeroPorta,
            String localidade, String codigoPostal) {
        return new EnderecoPostal(arruamento, numeroPorta, localidade, codigoPostal);
    }

    public static Colaborador novoColaborador(String nomeGestor, Email emailGestor, String telefoneGestor, Rolename rolename) {
        return new Colaborador(nomeGestor, emailGestor, telefoneGestor, rolename);
    }

    public String getNomeGestor() {
        return colabGestor.getNome();
    }

    public String getTelefoneGestor() {
        return colabGestor.getTelefone();
    }

    public Email getEmailGestor() {
        return colabGestor.getEmail();
    }


}