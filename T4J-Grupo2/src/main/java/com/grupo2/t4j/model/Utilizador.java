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
public class Utilizador {
   
    private String nome;
    private Email email;
    private Password password;
    
    public Utilizador(){
    }
     
    public Utilizador(String nome, Email email, Password password){
        setNome(nome);
        setEmail(email);
        setPassword(password);
    }
    
    public Utilizador(String nome, String emailUt, Password password){
        setNome(nome);
        this.email = new Email(emailUt);
        setPassword(password);
    }
    
    public Utilizador(String nome, String emailUt, String passUt){
        setNome(nome);
        this.email = new Email(emailUt);
        this.password = new Password(passUt);
    }
    
    public Utilizador(Utilizador utilizador){
        setNome(utilizador.nome);
        setEmail(utilizador.email);
        setPassword(utilizador.password);
    }

    public Utilizador(String nomeGestor, Email emailGestor) {
        setNome(nomeGestor);
        setEmail(emailGestor);
    }

    public final void setNome(String nome){
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é inválido!");
        }
        this.nome = nome;
    }
    
    public final void setEmail(Email email){
        this.email = email;
    }
    
    public final void setPassword(Password password){
        this.password = password;
    }
    
    public String getNome(){
        return nome;
    }
    
    public Email getEmail(){
        return email;
    }  
    
    public Password getPassword(){
        return password;
    }  
}

