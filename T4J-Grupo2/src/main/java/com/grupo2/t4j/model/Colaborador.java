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
     * Password inicial de um Colaborador em processo de registo - antes de receber 
     * a password oficial por email.
     */
    private static final Password PASSWORD_POR_OMISSAO = new Password("00000000");
    
    /**
     * Por definição, todos da classe Colaborador tem rolename Colaborador.
     */
    private static final Rolename ROLENAME_POR_OMISSAO = Rolename.COLABORADOR;
    
    /**
     * Construtor completo da classe Colaborador
     * @param nome o nome do Colaborador
     * @param email o email do Utilizador em formato da classe Email
     * @param password a password do Utilizador em formato da classe Password
     * @param funcao a função do Colaborador da organização
     * @param telefone o telefone do Colaborador da organização
     * @param rolename o papel do colaborador na T4J
     * 
     */

    public Colaborador(String nome, Email email, Password password, String funcao, 
            String telefone, Rolename rolename) {
        super(nome, email, password, rolename);
        setFuncao(funcao);
        setTelefone(telefone);
    }
    
    /**
     * Construtor completo da classe Colaborador
     * @param nome o nome do Colaborador
     * @param emailCol o email do Utilizador em formato String
     * @param passCol a password do Utilizador em formato String
     * @param funcao a função do Colaborador da organização
     * @param telefone o telefone do Colaborador da organização
     * 
     */

    public Colaborador(String nome, String emailCol, String passCol,
            String funcao, String telefone, Rolename rolename) {
        super(nome, emailCol, passCol, rolename);
        setFuncao(funcao);
        setTelefone(telefone);
    }
      
    /**
     * Construtor da classe Colaborador com password por omissão (antes de receber 
     * a password oficial por email - password = "00000000"
     * @param nome o nome do Colaborador
     * @param emailCol o email do Colaborador em formato String
     * @param funcao a função do Colaborador da organização
     * @param telefone o telefone do Colaborador da organização
     */

    public Colaborador(String nome, String emailCol, String funcao,
            String telefone, Rolename rolename){
        super(nome, emailCol, rolename);
        setFuncao(funcao);
        setTelefone(telefone);
    }
       
    /**
     * Construtor da classe Colaborador
     * @param colaborador é do tipo da classe Colaborador
     */
    public Colaborador (Colaborador colaborador){
        super(colaborador.getNome(), colaborador.getEmail(), colaborador.getPassword(),
                colaborador.getRolename());               
        setFuncao(colaborador.funcao);
        setTelefone(colaborador.telefone);
    }

    /**
     * Verifica a validade do parâmetro recebido e regista a função do Colaborador
     * @param funcao a função do Colaborador
     */
    public void setFuncao(String funcao){
        if (funcao == null || funcao.trim().isEmpty()) {
            throw new IllegalArgumentException("Função é inválida!");
        }
        this.funcao = funcao;
    }

    /**
     * Verifica a validade do parâmetro recebido e regista o telefone do Colaborador
     * @param telefone o telefone do Colaborador
     */
    public void setTelefone(String telefone) {
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
        return String.format("Nome colaborador: %s  %nEmail: %s"
                + "%nPassword: %s %nfuncao: %s %nTelefone: %s ", 
                super.getNome(), super.getEmail().getEmailText(),
                super.getPassword().getPasswordText(), funcao, telefone);
    }
    
    /**
     * Representação textual da classe Colaborador sem a password
     * @return Nome, função, email  e telefone do Utilizador
     */
    @Override
    public String toStringSemPass(){
        return String.format("Nome colaborador: %s  %nEmail: %s"
                + "%nfuncao: %s %nTelefone: %s ", 
                super.getNome(), super.getEmail().getEmailText(),
                funcao, telefone);
    }
}