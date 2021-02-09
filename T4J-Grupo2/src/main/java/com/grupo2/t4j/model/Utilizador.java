/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import java.io.Serializable;
import com.grupo2.t4j.exception.*;

/**
 *
 * @author CAD
 */

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
     * O papel do Utilizador
     */
    private Rolename rolename;
    
    /**
     * Password inicial de um Utilizador em processo de registo - antes de receber 
     * a password oficial por email.
     */
    private static final Password PASSWORD_POR_OMISSAO = new Password("00000000");
    
    /**
     * O papel do Utilizador por omissão.
     */
    private static final Rolename ROLENAME_POR_OMISSAO = Rolename.UTILIZADOR;
    
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
     * @param rolename o papel do Utilizador na T4J
     */
    public Utilizador(String nome, Email email, Password password, Rolename rolename){
        setNome(nome);
        setEmail(email);
        setPassword(password);
        setRolename(rolename);
    }
    
    /**
     * Construtor completo da classe Utilizador só com Strings
     * @param nome o nome do Utilizador
     * @param emailUt o email do Utilizador em formato String
     * @param passUt a password do Utilizador em formato String 
     * @param rolename o papel do Utilizador na T4J
     */
    public Utilizador(String nome, String emailUt, String passUt, Rolename rolename){
        setNome(nome);
        this.email = new Email(emailUt);
        this.password = new Password(passUt);
        setRolename(rolename);
    }
    
    /**
     * Construtor da classe Utilizador com a password por omissão
     * @param nome o nome do Utilizador
     * @param emailUt o email do Utilizador em formato String
     * @param rolename o papel do Utilizador na T4J
     */
    public Utilizador (String nome, String emailUt, Rolename rolename){
        setNome(nome);
        this.email = new Email(emailUt);
        setPassword(PASSWORD_POR_OMISSAO);
        setRolename(rolename); 
    }
       
    /**
     * Construtor da classe Utilizador com a password por omissão e o papel
     * do utilizador por omissão
     * @param nome o nome do Utilizador
     * @param emailUt o email do Utilizador em formato String
     */
    public Utilizador(String nome, String emailUt){
        setNome(nome);
        this.email = new Email(emailUt);
        setPassword(PASSWORD_POR_OMISSAO);
        setRolename(ROLENAME_POR_OMISSAO);
    }
    
    /**
     * Construtor da classe Utilizador
     * @param utilizador é do tipo da classe Utilizador
     */
    public Utilizador(Utilizador utilizador){
        setNome(utilizador.nome);
        setEmail(utilizador.email);
        setPassword(utilizador.password);
        setRolename(utilizador.rolename);
    }
   
    /**
     * Verifica a validade do parâmetro recebido e regista o nome do Utilizador
     * @param nome o nome do Utilizador
     */
    public void setNome(String nome){
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeInvalidoException("Nome é inválido!");
        }
        this.nome = nome;
    }
    
    /**
     * Regista o email do Utilizador
     * @param email o email do Utilizador
     */
    public void setEmail(Email email){
        this.email = email;
    }
    
    /**
     * Regista a password do Utilizador
     * @param password a password do Utilizador
     */
    public void setPassword(Password password){
        this.password = password;
    }
    
    /**
     * Regista o papel do Utilizador
     * @param rolename o papel do Utilizador
     */
    public void setRolename(Rolename rolename){
        this.rolename = rolename;
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
     * Retorna o rolename do Utilizador
     * @return rolename
     */
    public Rolename getRolename(){
        return rolename;
    }
    
    /**
     * Representação textual da classe Utilizador
     * @return Nome, email e password do Utilizador
     */   
    @Override
    public String toString(){
        return String.format("Nome utilizador: %s %nEmail:%s %nPassword: %s "
                + "%nRolename: %s", nome, email.getEmailText(), 
                password.getPasswordText(), rolename.toString());
    }
    
    /**
     * Representação textual da classe Utilizador sem a password
     * @return Nome e email do Utilizador
     */
    public String toStringSemPass(){
        return String.format("Nome utilizador: %s %nEmail:%s %nRolename: %s", 
                nome, email.getEmailText(), rolename.toString());
    }
    
    //////////////////////////////// Ver se precisa mesmo
    
     /**
     * Construtor da classe Utilizador para uso específico de Gestor de Organização
     * @param nomeGestor o nome do Gestor da Organização
     * @param emailGestor o email do Gestor da Organização
     */
   /* public Utilizador(String nomeGestor, Email emailGestor) {
        setNome(nomeGestor);
        setEmail(emailGestor);
    }*/

}
