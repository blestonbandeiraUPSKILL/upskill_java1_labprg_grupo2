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

    public Utilizador(String nome, Email email) {
        setNome(nome);
        setEmail(email);
    }
    
    public Utilizador(String nome, Email email, Password password){
        setNome(nome);
        setEmail(email);
        setPassword(password);
    }
    
    public Utilizador(Utilizador utilizador){
        setNome(utilizador.nome);
        setEmail(utilizador.email);
        setPassword(utilizador.password);
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

    /**
     * vejam se esta vale a pena:
     */
    /*private boolean eNomeValido(String nome){
        if(nome == null){
            return false;
        }
        if(nome.length() < 3){
            return false;
        }
        for(int i=0;i<nome.length();i++){
            if(nome.charAt(i) >= '0' && nome.charAt(i) <= '9')
                return false;
        }
        return true;
    }*/
}
