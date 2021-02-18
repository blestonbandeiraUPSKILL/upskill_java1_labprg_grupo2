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
     * O papel do Utilizador por omissão.
     */
    private static final Rolename ROLENAME_POR_OMISSAO = Rolename.COLABORADOR;
    
    /**
     * Construtor vazio da classe Utilizador
     */
    public Utilizador(){
    }

    
    /**
     * Construtor da classe Utilizador com a password por omissão
     * @param nome o nome do Utilizador
     * @param emailUt o email do Utilizador em formato String
     *
     */
    public Utilizador (Email emailUt, String nome, Password password){
        this.email = emailUt;
        setNome(nome);
        setPassword(password);
    }

    public Utilizador(Utilizador utilizador) {
        this.email = utilizador.getEmail();
        this.nome = utilizador.getNome();
        this.password = utilizador.getPassword();
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
    public String toStringComPass(){
        return String.format("Nome utilizador: %s %nEmail:%s %nPassword: %s "
                + "%nRolename: %s", nome, email.getEmailText(), 
                password.getPasswordText(), rolename.toString());
    }
    
    /**
     * Representação textual da classe Utilizador sem a password
     * @return Nome e email do Utilizador
     */
    @Override
    public String toString(){
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
