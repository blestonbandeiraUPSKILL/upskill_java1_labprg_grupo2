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
public class Utilizador {
   
    private String nome;
    private Email email;
    private Password password;
    
    public Utilizador(String nome, Email email, Password password){
        setNome(nome);
        setEmail(email);
        setPassword(password);
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
