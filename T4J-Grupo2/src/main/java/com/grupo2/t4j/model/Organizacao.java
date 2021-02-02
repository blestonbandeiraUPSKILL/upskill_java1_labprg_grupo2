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
    private String website;
    private String telefone;
    private Email email;
    
    public Organizacao(String nome, String NIF, String website, String telefone, Email email){
        setNome(nome);
        setNif(NIF);
        setWebsite(website);
        setTelefone(telefone);
        setEmail(email);
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
    
    public void setWebsite(String website){
        if(eURL(website)){
            this.website = website;
        }
        else{
            throw new IllegalArgumentException("O endereço do website é inválido!");
        }
    }
    
    public final void setTelefone(String telefone) {
        int numero = Integer.parseInt(telefone);
        if (numero < 100000000 || numero > 999999999) {
            throw new IllegalArgumentException("Número é inválido!");
        }
        this.telefone = telefone;
    }
    
    // Dica de: https://qastack.com.br/programming/2230676/how-to-check-for-a-valid-url-in-java
    
    public boolean eURL(String url) {
        try {
            (new java.net.URL(url)).openStream().close();
            return true;
        } catch (Exception ex) { }
            return false;
    }
    
    public final void setEmail(Email email){
        this.email = email;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getNif(){
        return NIF;
    }
    
    public String getWebsite(){
        return website;
    }
    
    public String getTelefone(){
        return telefone;
    }
    
    public Email getEmail(){
        return email;
    }    
}
