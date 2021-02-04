/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import java.io.Serializable;

/**
 *
 * @author CAD
 */
public class Colaborador extends Utilizador implements Serializable{
    
    /**
     * A função do Colaborador da organização
     */
    private String funcao;
    
    /**
     * O telefone do Colaborador da organização
     */
    private String telefone;
    
    /**
     * 
     */

    private Rolename rolename;

    /**
     * Construtor vazio da classe Colaborador
     */
    public Colaborador() {
        super();
    }

    /**
     * Construtor da classe Colaborador somente com parâmetros da classe Utilizador
     * @param nome o nome do Colaborador
     * @param email o email do Colaborador em formato da classe Email
     * @param password a password do Colaborador em formato da classe Password
     */
    public Colaborador(String nome, Email email, Password password){
        super(nome, email, password);
    }
    
    /**
     * 
     * @param nome
     * @param email
     * @param telefone
     * @param rolename 
     */

    public Colaborador(String nome, Email email, String telefone, Rolename rolename) {
        setNome(nome);
        setEmail(email);
        setTelefone(telefone);
        this.rolename = rolename;
    }
  
    /**
     * Construtor completo da classe Colaborador 
     * @param nome o nome do Colaborador
     * @param email o email do Colaborador em formato da classe Email
     * @param password a password do Colaborador em formato da classe Password
     * @param funcao a função do Colaborador da organização
     * @param telefone o telefone do Colaborador da organização
     */

    public Colaborador(String nome, Email email, Password password, String funcao,
            String telefone){
        super(nome, email, password);
        setFuncao(funcao);
        setTelefone(telefone);
    }
    
    /**
     * Construtor completo da classe Colaborador 
     * @param nome o nome do Colaborador
     * @param emailCol o email do Colaborador em formato String
     * @param password a password do Colaborador em formato da classe Password
     * @param funcao a função do Colaborador da organização
     * @param telefone o telefone do Colaborador da organização
     */
    public Colaborador(String nome, String emailCol, Password password, String funcao,
            String telefone){
        super(nome, emailCol, password);
        setFuncao(funcao);
        setTelefone(telefone);
    }
    
    /**
     * Construtor completo da classe Colaborador 
     * @param nome o nome do Colaborador
     * @param emailCol o email do Colaborador em formato String
     * @param passCol a password do Colaborador em formato String
     * @param funcao a função do Colaborador da organização
     * @param telefone o telefone do Colaborador da organização     * 
     */
    public Colaborador(String nome, String emailCol, String passCol, String funcao,
            String telefone){
        super(nome, emailCol, passCol);
        setFuncao(funcao);
        setTelefone(telefone);
    }


    /* Construtor da classe Colaborador para uso específico de Gestor de Organização
     * @param nomeGestor o nome do Gestor da Organização
     * @param emailGestor o email do Gestor da Organização
     * @param funcao a função do Gestor da Organização
     * @param telefoneGestor o telefone do Gestor da Organização
     */
    /*public Colaborador(String nomeGestor, Email emailGestor, String funcao, String telefoneGestor) {
        super(nomeGestor, emailGestor);
        setFuncao(funcao);
        setTelefone(telefoneGestor);
    }*/

    /**
     * Construtor da classe Colaborador
     * @param colaborador é do tipo da classe Colaborador
     */
    public Colaborador (Colaborador colaborador){
        super(colaborador.getNome(), colaborador.getEmail(), colaborador.getPassword());
        /*setFuncao(colaborador.funcao);*/
        setTelefone(colaborador.telefone);
    }

    /**
     * Verifica a validade do parâmetro recebido e regista a função do Colaborador
     * @param funcao a função do Colaborador
     */
    public final void setFuncao(String funcao){
        if (funcao == null || funcao.trim().isEmpty()) {
            throw new IllegalArgumentException("Função é inválida!");
        }
        this.funcao = funcao;
    }

    /**
     * Verifica a validade do parâmetro recebido e regista o telefone do Colaborador
     * @param telefone o telefone do Colaborador
     */
    public final void setTelefone(String telefone) {
        int numero = Integer.parseInt(telefone);
        if (numero < 100000000 || numero > 999999999) {
            throw new IllegalArgumentException("Número é inválido!");
        }
        this.telefone = telefone;
    }
     
    /**
     * Devolve a função do colaborador
     * @return funcao
     */
    public String getFuncao(){
        return funcao;
    }
    
    /**
     * Devolve o telefone do Colaborador
     * @return telefone
     */
    public String getTelefone(){
        return telefone;
    }
    
    /**
     * Representação textual da classe Colaborador
     * @return Nome, função, email, telefone e password do Colaborador
     */   
    @Override
    public String toString(){
        return String.format("O colaborador %s tem os seguintes dados: /nfuncao: %s"
                + " /nEmail: %s /nTelefone: %s /nPassword: %s", super.getNome(), 
                funcao, super.getEmail(), telefone, super.getPassword());
    }
    
    /**
     * Representação textual da classe Colaborador sem a password
     * @return Nome, função, email  e telefone do Utilizador
     */
    @Override
    public String toStringSemPass(){
        return String.format("O colaborador %s tem os seguintes dados: /nfuncao: %s"
                + " /nEmail: %s /nTelefone: %s", super.getNome(), 
                funcao, super.getEmail(), telefone);
    }   
}