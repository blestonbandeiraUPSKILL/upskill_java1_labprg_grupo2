/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import java.io.Serializable;
import com.grupo2.t4j.exception.NomeInvalidoException;

/**
 *
 * @author CAD
 */

public class Administrativo implements Serializable{
   
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
     * O papel do Administrativo
     */
    private Rolename rolename; 
    
    /**
     * Password inicial de um Administrativo em processo de registo - antes de receber 
     * a password oficial por email.
     */
    private static final Password PASSWORD_POR_OMISSAO = new Password("00000000");
    
    /**
     * O papel do Administrativo
     */
    private static final Rolename ROLENAME_POR_DEFINICAO = Rolename.ADMINISTRATIVO; 
    
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
        setRolename(ROLENAME_POR_DEFINICAO);
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
        setRolename(ROLENAME_POR_DEFINICAO);
    }
    
    /**
     * Construtor da classe Administrativo com a password por omissão
     * @param nome o nome do Administrador
     * @param emailAdm o email do Administrador em formato String
     * @param password a password do Administrador em formato da classe Password
     */
    public Administrativo(String nome, String emailAdm){
        setNome(nome);
        this.email = new Email(emailAdm); 
        setPassword(PASSWORD_POR_OMISSAO);
        setRolename(ROLENAME_POR_DEFINICAO);
    }
     
    /**
     * Construtor da classe Administrativo
     * @param administrativo é do tipo da classe Administrativo
     */
    public Administrativo(Administrativo administrativo){
        setNome(administrativo.nome);
        setEmail(administrativo.email);
        setPassword(administrativo.password);
        setRolename(ROLENAME_POR_DEFINICAO);
    }
    
    /**
     * Verifica a validade do parâmetro recebido e regista o nome do Administrador
     * @param nome o nome do Administrador
     */
    public void setNome(String nome){
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeInvalidoException("Nome é inválido!");
        }
        this.nome = nome;
    }
    
    /**
     * Regista o email do Administrador
     * @param email o email do Administrador
     */
    public void setEmail(Email email){
        this.email = email;
    }
    
    /**
     * Regista a password do Administrador
     * @param password a password do Administrador
     */
    public void setPassword(Password password){
        this.password = password;
    }
    
    /**
     * Regista o papel do Administrativo
     * @param rolename o papel do Administrativo
     */
    public void setRolename(Rolename rolename){
        this.rolename = rolename;
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
        return String.format("Nome administrativo: %s %nEmail: %s %nPassword: %s", 
                nome, email.getEmailText(), password.getPasswordText());
    }
    
    /**
     * Representação textual da classe Administrativo sem a password
     * @return Nome e email do Administrador
     */
    public String toStringSemPass(){
        return String.format("Nome administrativo: %s %nEmail: %s", 
                nome, email.getEmailText());
    }
}

