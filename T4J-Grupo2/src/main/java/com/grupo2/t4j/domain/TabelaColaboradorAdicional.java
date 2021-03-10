/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

/**
 *
 * @author CAD
 */
public class TabelaColaboradorAdicional {
    
    private String emailColaboradorAdd;
    private String selecao;
    
    public TabelaColaboradorAdicional(String emailColaboradorAdd,String selecao){
        setEmail(emailColaboradorAdd);
        setSelecao(selecao);
    }
    
    public void setEmail(String emailColaboradorAdd){
        this.emailColaboradorAdd = emailColaboradorAdd;
    }
    
    public void setSelecao(String selecao){
        this.selecao = selecao;
    }
    
    public String getEmail(){
        return emailColaboradorAdd;
    }
    
    public String getSelecao(){
        return selecao;
    }
}
