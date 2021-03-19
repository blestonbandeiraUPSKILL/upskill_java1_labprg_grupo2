/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.dto;

/**
 *
 * @author CAD
 */
public class TabelaColaboradorAdicionalDTO {

    private String email;
    private String selecao;

    public TabelaColaboradorAdicionalDTO(String email, String selecao){
        this.email = email;
        this.selecao = selecao;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getSelecao(){
        return selecao;
    }
}
