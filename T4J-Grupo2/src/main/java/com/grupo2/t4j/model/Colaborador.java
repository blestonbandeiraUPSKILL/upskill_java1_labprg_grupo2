/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

/**
 *
 * @author acris
 */
public class Colaborador {
    
    private String funcao;
    private String telefone;
    
    public Colaborador(String funcao, String telefone){
        setFuncao(funcao);
        setTelefone(telefone);
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
