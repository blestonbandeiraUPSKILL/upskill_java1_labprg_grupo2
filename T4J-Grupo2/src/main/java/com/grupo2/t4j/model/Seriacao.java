/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

/**
 *
 * @author CAD
 */

import java.util.ArrayList;
import java.util.List;

public class Seriacao {

    private int idSeriacao;
    
    private int idAnuncio;
    
    private String dataSeriacao;
    
    private List<Classificacao> listaCandidaturasSeriadas;
       
    public Seriacao(int idSeriacao, int idAnuncio, String dataSeriacao, List<Classificacao> 
            listaCandidaturasSeriadas){
        setIdSeriacao(idSeriacao);
        setIdAnuncio(idAnuncio);
        setData(dataSeriacao);
        setListaSeriada(listaCandidaturasSeriadas);        
    }
    
    public void setIdSeriacao(int idSeriacao){
        this.idSeriacao = idSeriacao;
    }
    
    public void setIdAnuncio(int idAnuncio){
        this.idAnuncio = idAnuncio;
    }
    
    public void setData(String dataSeriacao){
        this.dataSeriacao = dataSeriacao;
    }
    
    public void setListaSeriada(List<Classificacao> listaCandidaturasSeriada){
        this.listaCandidaturasSeriadas = listaCandidaturasSeriadas;
    }
    
    public int getIdSeriacao(){
        return idSeriacao;
    }
    
    public int getIdAnuncio(){
        return idAnuncio;
    }
    
    public String dataSeriacao(){
        return dataSeriacao;
    }
    
    public List<Classificacao> getListaSeriada(){
        return listaCandidaturasSeriadas;
    }
 
    
}
