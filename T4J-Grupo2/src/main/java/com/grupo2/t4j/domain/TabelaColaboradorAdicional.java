/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

import com.grupo2.t4j.dto.DTO;
import com.grupo2.t4j.dto.TabelaColaboradorAdicionalDTO;

/**
 *
 * @author CAD
 */
public class TabelaColaboradorAdicional implements DTO {
    
    private String email;
    private String selecao;
    
    public TabelaColaboradorAdicional(String email,String selecao){
        setEmail(email);
        setSelecao(selecao);
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setSelecao(String selecao){
        this.selecao = selecao;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getSelecao(){
        return selecao;
    }

    @Override
    public Object toDTO() {
        return new TabelaColaboradorAdicionalDTO(email, selecao);
    }
}
