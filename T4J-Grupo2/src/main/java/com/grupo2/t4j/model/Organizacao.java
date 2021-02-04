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
        
public class Organizacao {
    
    private String nome;
    private String NIF;
    private EnderecoPostal enderecoOrg;
    private Website websiteOrg;
    private String telefone;
    private Email emailOrg;
    
    private Colaborador colabGestor;
    
    private static final EnderecoPostal ENDERECO_POR_OMISSAO = new EnderecoPostal("Rua ", "s/n", "Portugal", "1111-111");
    private static final Website WEBSITE_POR_OMISSAO = new Website("http://***");
    private static final String TELEFONE_ORG_POR_OMISSAO = "999999999";
        
    public Organizacao(){
    }
    
    public Organizacao(String nome, String NIF, EnderecoPostal enderecoOrg, 
            Website websiteOrg, String telefone, Email emailOrg, Colaborador 
            colabGestor){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(enderecoOrg);
        this.websiteOrg = new Website(websiteOrg);
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
        this.websiteOrg = new Website(websiteOrg);
        setTelefone(telefone);
        this.emailOrg = new Email(emailOrg);
        this.colabGestor = new Colaborador(colabGestor);
    }
    
    public Organizacao(String nome, String NIF, String arruamento, String numeroPorta,
            String localidade, String codigoPostal, String website, String telefone,
            String email, Colaborador colabGestor){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(arruamento, numeroPorta, localidade, codigoPostal);
        this.websiteOrg = new Website(website);
        setTelefone(telefone);
        this.emailOrg = new Email(email);
        this.colabGestor = new Colaborador(colabGestor);
    }
    
    public Organizacao(String nome, String NIF, String arruamento, String numeroPorta,
            String localidade, String codigoPostal, String website, String telefone,
            String email, String nomeColab, String emailColab, Password password,
            String funcao, String telefoneColab){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(arruamento, numeroPorta, localidade, codigoPostal);
        this.websiteOrg = new Website(website);
        setTelefone(telefone);
        this.emailOrg = new Email(email);
        this.colabGestor = new Colaborador(nomeColab, emailColab, password, funcao, telefoneColab);
    }
    
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
    
    public Organizacao(String nome, String NIF, String email,Colaborador colabGestor){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = ENDERECO_POR_OMISSAO;
        this.websiteOrg = WEBSITE_POR_OMISSAO;
        setTelefone(TELEFONE_ORG_POR_OMISSAO);
        this.emailOrg = new Email(email);
        this.colabGestor = new Colaborador(colabGestor);
    }
    
    public Organizacao(Organizacao organizacao){
        setNome(organizacao.nome);
        setNif(organizacao.NIF);
        this.enderecoOrg = new EnderecoPostal(organizacao.enderecoOrg);
        this.websiteOrg = new Website(organizacao.websiteOrg);
        setTelefone(organizacao.telefone);
        this.emailOrg = new Email(organizacao.emailOrg);
        this.colabGestor = new Colaborador(organizacao.colabGestor);
    }

    public Organizacao(String nomeOrganizacao, String nif, String telefone,
                       String website, String email, EnderecoPostal enderecoPostal) {
        Website website1 = new Website(website);
        setNome(nomeOrganizacao);
        setNif(nif);
        setTelefone(telefone);
        setWebsite(website1);
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

    public static Colaborador novoColaborador(String nomeGestor, String funcao, String telefoneGestor, Email emailGestor ) {
        return new Colaborador(nomeGestor, emailGestor, funcao, telefoneGestor);
    }
}