/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

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

    private String nifOrganizacao;

    public Colaborador() {
    }

    /**
     * Construtor completo da classe Colaborador
     * @param email o email do Utilizador em formato da classe Email
     * @param nome o nome do Colaborador
     * @param funcao a função do Colaborador da organização
     * @param telefone o telefone do Colaborador da organização
     * 
     */
    public Colaborador(Email email, String nome, Password password, String funcao, String telefone, String nifOrganizacao) {
        super(email, nome, password);
        setFuncao(funcao);
        setTelefone(telefone);
        setNifOrganizacao(nifOrganizacao);
    }

    public Colaborador(Colaborador colaborador) {
       super(colaborador.getEmail(), colaborador.getNome(), colaborador.getPassword());
       this.funcao = colaborador.getFuncao();
       this.telefone = colaborador.getTelefone();
    }

    public Colaborador(Email email, String funcao, String telefone, String nifOrganizacao) {
        setEmail(email);
        setFuncao(funcao);
        setTelefone(telefone);
        this.nifOrganizacao = nifOrganizacao;
    }

    public Colaborador(Utilizador utilizador, String funcao, String telefone) {
        super(utilizador);
        setFuncao(funcao);
        setTelefone(telefone);
    }

    public Colaborador(String email, String nome, String funcao, String telefone, String nifOrganizacao) {
        super(nome, email);
        setFuncao(funcao);
        setTelefone(telefone);
        setNifOrganizacao(nifOrganizacao);
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
        /*int numero = Integer.parseInt(telefone);
        if (numero < 100000000 || numero > 999999999) {
            throw new IllegalArgumentException("Número é inválido!");
        }*/
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

    public String getNifOrganizacao() {
        return nifOrganizacao;
    }

    public void setNifOrganizacao(String nifOrganizacao) {
        this.nifOrganizacao = nifOrganizacao;
    }
    
    
    
    /**
     * Representação textual da classe Colaborador em formato de exibição
     * @return Nome, função, email e telefone do Colaborador
     */   
    @Override
    public String toString(){
        return String.format("Nome: %-20s  |Email: %-20s"
                + " |Funcao: %-15s |Telefone: %-15s",
                super.getNome(), super.getEmail().getEmailText(), funcao, telefone);
    }
    
     /**
     * Representação textual da classe Colaborador
     * @return Nome, função, email, telefone e password do Colaborador
     */   
    public String toStringCompleto(){
        return String.format("Nome colaborador: %s  %nEmail: %s"
                + "%nFuncao: %s %nTelefone: %s %nRolename: %s",
                super.getNome(), super.getEmail().getEmailText(), funcao, telefone,
                super.getRolename().toString());
    }

}