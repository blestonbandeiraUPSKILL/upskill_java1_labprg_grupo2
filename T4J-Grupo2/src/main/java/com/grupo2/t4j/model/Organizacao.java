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
    private Website websiteOrg;
    private String telefone;
    private Email emailOrg;
    private EnderecoPostal enderecoOrg;
    private Colaborador colabGestor;
    
    public Organizacao(){
    }
    
    public Organizacao(String nome, String NIF, EnderecoPostal enderecoOrg, String telefone,
                       Website websiteOrg, Email emailOrg, Colaborador colabGestor){
        setNome(nome);
        setNif(NIF);
        this.enderecoOrg = new EnderecoPostal(enderecoOrg);
        this.websiteOrg = new Website(websiteOrg);
        setTelefone(telefone);
        this.emailOrg = new Email(emailOrg);
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
