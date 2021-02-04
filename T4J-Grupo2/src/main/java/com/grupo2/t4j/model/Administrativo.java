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

import java.io.Serializable;

public class Administrativo implements Serializable {
    
    /**
     * O nome do Administrativo
     */
    private String nome;
    
    /**
     * O email do Administrativo
     */
    private Email email;
    
    /**
     * A password do Administrativo
     */
    private Password password;
    
    /**
     * Construtor vazio da classe Administrativo
     */
    public Administrativo(){
   }
    
    /**
     * Construtor completo da classe Administrativo
     * @param nome o nome do Administrador
     * @param email o email do Administrador em formato da classe Email
     * @param password a password do Administrador em formato da classe Password
     */
    public Administrativo(String nome, Email email, Password password){
        setNome(nome);
        setEmail(email);
        setPassword(password);
    }
    
    /**
     * Construtor completo da classe Administrativo
     * @param nome o nome do Administrador
     * @param emailAdm o email do Administrador em formato String
     * @param password a password do Administrador em formato da classe Password
     */
    public Administrativo(String nome, String emailAdm, Password password){
        setNome(nome);
        this.email = new Email(emailAdm); 
        setPassword(password);
    }
    
    /**
     * Construtor completo da classe Administrativo
     * @param nome o nome do Administrador
     * @param emailAdm o email do Administrador em formato String
     * @param passAdm a password do Administrador em formato String
     */
    public Administrativo(String nome, String emailAdm, String passAdm){
        setNome(nome);
        this.email = new Email(emailAdm); 
        this.password = new Password(passAdm);
    }
    
    /**
     * Construtor da classe Administrativo
     * @param administrativo é do tipo da classe Administrativo
     */
    public Administrativo(Administrativo administrativo){
        setNome(administrativo.nome);
        setEmail(administrativo.email);
        setPassword(administrativo.password);
    }
    
    /**
     * Verifica a validade do parâmetro recebido e regista o nome do Administrador
     * @param nome o nome do Administrador
     */
    public final void setNome(String nome){
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é inválido!");
        }
        this.nome = nome;
    }
    
    /**
     * Regista o email do Administrador
     * @param email o email do Administrador
     */
    public final void setEmail(Email email){
        this.email = email;
    }
    
    /**
     * Regista a password do Administrador
     * @param password a password do Administrador
     */
    public final void setPassword(Password password){
        this.password = password;
    }
    
    /**
     * Retorna o nome do Administrador
     * @return nome
     */
    public String getNome(){
        return nome;
    }
    
    /**
     * Retorna o email do Administrador
     * @return email
     */
    public Email getEmail(){
        return email;
    }  
    
    /**
     * Retorna a password do Administrador
     * @return password
     */
    public Password getPassword(){
        return password;
    }
        
    /**
     * Representação textual da classe Administrativo
     * @return Nome, email e password do Administrador
     */   
    @Override
    public String toString(){
        return String.format("O administrador %s tem os seguintes dados: /nEmail: "
                + "%s /nPassword: %s", nome, email, password);
    }
    
    /**
     * Representação textual da classe Administrativo sem a password
     * @return Nome e email do Administrador
     */
    public String toStringSemPass(){
        return String.format("O administrador %s tem o seguinte email registado: %s", 
                nome, email);
    }
}

