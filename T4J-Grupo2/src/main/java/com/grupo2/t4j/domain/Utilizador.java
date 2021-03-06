/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

import com.grupo2.t4j.dto.DTO;
import com.grupo2.t4j.dto.UtilizadorDTO;
import com.grupo2.t4j.exception.NomeInvalidoException;

import java.io.Serializable;

/**
 *
 * @author CAD
 */

public class Utilizador implements Serializable, DTO {
   
    /**
     * O nome do Utilizador
     */
    String nome;
    
    /**
     * O email do Utilizador
     */
    Email email;
    
    /**
     * A password do Utilizador
     */
    Password password;

    /**
     * O papel do Utilizador
     */
    private String rolename;


    
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

    /**
     * Construtor da classe utilizador
     * @param utilizador 
     */
    public Utilizador(Utilizador utilizador) {
        this.email = utilizador.getEmail();
        this.nome = utilizador.getNome();
        this.password = utilizador.getPassword();
        this.rolename = utilizador.getRolename();
    }

    /**
     * Cosntrutor da classe utilizador
     * @param emailUtilizador
     * @param nome
     * @param password
     * @param rolename 
     */
    public Utilizador(String emailUtilizador, String nome, String password, String rolename) {
        setEmail(new Email(emailUtilizador));
        setNome(nome);
        setPassword(new Password(password));
        this.rolename = rolename;

    }

    /**
     * Construtor da classe Utilizador
     * @param email
     * @param nome 
     */
    public Utilizador(String email, String nome) {
        setNome(nome);
        setEmail(new Email(email));
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
        this.email = new Email(email);
    }

    /**
     * Atualiza o email do utilizador
     * @param email 
     */
    public void setEmail(String email) {
        this.email = new Email(email);
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
    public void setRolename(String rolename){
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
    public String getRolename(){
        return rolename;
    }
    
    
    /**
     * Representação textual da classe Utilizador em formato de exibição
     * @return Nome e email do Utilizador
     */
    @Override
    public String toString(){
        return String.format("Nome: %-20s |Email:%-20s", 
                nome, email.getEmailText());
    }
    
    /**
     * Representação textual da classe Utilizador
     * @return Nome, email e password do Utilizador
     */
    public String toStringComPass(){
        return String.format("Nome: %s %nEmail:%s %nPassword: %s "
                + "%nRolename: %s", nome, email.getEmailText(), 
                password.getPasswordText(), rolename.toString());
    }
    
    /**
     * Representação textual da classe Utilizador sem a password
     * @return Nome e email do Utilizador
     */
    public String toStringSemPass(){
        return String.format("Nome: %s %nEmail:%s %nRolename: %s", 
                nome, email.getEmailText(), rolename.toString());
    }


    @Override
    public Object toDTO() {
        return new UtilizadorDTO(email, nome, password, rolename);
    }
}
