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
    
    private String funcao;
    private String telefone;

    public Colaborador() {
        super();
    }

    public Colaborador(String nome, Email email, Password password){
        super(nome, email, password);
    }

    public Colaborador(String nome, Email email, Password password, String funcao,
            String telefone){
        super(nome, email, password);
        setFuncao(funcao);
        setTelefone(telefone);
    }
    
    public Colaborador(String nome, String emailCol, Password password, String funcao,
            String telefone){
        super(nome, emailCol, password);
        setFuncao(funcao);
        setTelefone(telefone);
    }
    
    public Colaborador(String nome, String emailCol, String passCol, String funcao,
            String telefone){
        super(nome, emailCol, passCol);
        setFuncao(funcao);
        setTelefone(telefone);
    }

    public Colaborador(String nomeGestor, Email emailGestor, String funcao, String telefoneGestor) {
        super(nomeGestor, emailGestor);
        setFuncao(funcao);
        setTelefone(telefoneGestor);
    }

    public Colaborador (Colaborador colaborador){
        super(colaborador.getNome(), colaborador.getEmail(), colaborador.getPassword());
        setFuncao(colaborador.funcao);
        setTelefone(colaborador.telefone);
    }


    public final void setFuncao(String funcao){
        if (funcao == null || funcao.trim().isEmpty()) {
            throw new IllegalArgumentException("Função é inválida!");
        }
        this.funcao = funcao;
    }

    public final void setTelefone(String telefone) {
        int numero = Integer.parseInt(telefone);
        if (numero < 100000000 || numero > 999999999) {
            throw new IllegalArgumentException("Número é inválido!");
        }
        this.telefone = telefone;
    }
     
    public String getFuncao(){
        return funcao;
    }
    
    public String getTelefone(){
        return telefone;
    }
}