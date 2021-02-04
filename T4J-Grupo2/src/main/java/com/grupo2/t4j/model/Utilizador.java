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

public class Utilizador implements Serializable{
   
    /**
     * O nome do Utilizador
     */
    private String nome;
    
    /**
     * O email do Utilizador
     */
    private Email email;
    
    /**
     * A password do Utilizador
     */
    private Password password;
    
    /**
     * Construtor vazio da classe Utilizador
     */
    public Utilizador(){
    }
     
    /**
     * Construtor completo da classe Utilizador
     * @param nome o nome do Utilizador
     * @param email o email do Utilizador em formato da classe Email
     * @param password a password do Utilizador em formato da classe Password
     */
    public Utilizador(String nome, Email email, Password password){
        setNome(nome);
        setEmail(email);
        setPassword(password);
    }
    
    /**
     * Construtor completo da classe Utilizador
     * @param nome o nome do Utilizador
     * @param emailUt o email do Utilizador em formato String
     * @param password a password do Utilizador em formato da classe Password     * 
     */
    public Utilizador(String nome, String emailUt, Password password){
        setNome(nome);
        this.email = new Email(emailUt);
        setPassword(password);
    }
    
    /**
     * Construtor completo da classe Utilizador
     * @param nome o nome do Utilizador
     * @param emailUt o email do Utilizador em formato String
     * @param passUt a password do Utilizador em formato String
     */
    public Utilizador(String nome, String emailUt, String passUt){
        setNome(nome);
        this.email = new Email(emailUt);
        this.password = new Password(passUt);
    }
    
    /**
     * Construtor da classe Utilizador
     * @param utilizador é do tipo da classe Utilizador
     */
    public Utilizador(Utilizador utilizador){
        setNome(utilizador.nome);
        setEmail(utilizador.email);
        setPassword(utilizador.password);
    }

    /**
     * Construtor da classe Utilizador para uso específico de Gestor de Organização
     * @param nomeGestor o nome do Gestor da Organização
     * @param emailGestor o email do Gestor da Organização
     */
    public Utilizador(String nomeGestor, Email emailGestor) {
        setNome(nomeGestor);
        setEmail(emailGestor);
    }

    /**
     * Verifica a validade do parâmetro recebido e regista o nome do Utilizador
     * @param nome o nome do Utilizador
     */
    public final void setNome(String nome){
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é inválido!");
        }
        this.nome = nome;
    }
    
    /**
     * Regista o email do Utilizador
     * @param email o email do Utilizador
     */
    public final void setEmail(Email email){
        this.email = email;
    }
    
    /**
     * Regista a password do Utilizador
     * @param password a password do Utilizador
     */
    public final void setPassword(Password password){
        this.password = password;
    }
    
    /**
     * Retorna o nome do Utilizador
     * @return nome
     */
    public String getNome(){
        return nome;
    }
    
    /**
     * Retorna o email do Utilizador
     * @return email
     */
    public Email getEmail(){
        return email;
    }  
    
    /**
     * Retorna a password do Utilizador
     * @return password
     */
    public Password getPassword(){
        return password;
    } 
    
    /**
     * Representação textual da classe Utilizador
     * @return Nome, email e password do Utilizador
     */   
    @Override
    public String toString(){
        return String.format("O utilizador %s tem os seguintes dados: /nEmail: "
                + "%s /nPassword: %s", nome, email, password);
    }
    
    /**
     * Representação textual da classe Utilizador sem a password
     * @return Nome e email do Utilizador
     */
    public String toStringSemPass(){
        return String.format("O utilizador %s tem o seguinte email registado: %s", 
                nome, email);
    }
}
