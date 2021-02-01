/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

/**
 *
 * @author acris
 */

import com.grupo2.t4j.exception.*;
        
public class Organizacao {
    
    private String nome;
    private String NIF;
    private Website websiteOrg;
    private String telefone;
    private Email emailOrg;
    
    public Organizacao(){
    }
    
    public Organizacao(String nome, String NIF, Website websiteOrg, String telefone, Email emailOrg){
        setNome(nome);
        setNif(NIF);
        this.websiteOrg = new Website(websiteOrg);
        setTelefone(telefone);
        this.emailOrg = new Email(emailOrg);
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
    
    
    public final void setEmail(Email email){
        this.emailOrg = emailOrg;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getNif(){
        return NIF;
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
}
