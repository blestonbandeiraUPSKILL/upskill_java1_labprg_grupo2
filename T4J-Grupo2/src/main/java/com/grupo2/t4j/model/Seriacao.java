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

    /**
     * O id da Seriação.
     */
    private int idSeriacao;
    
    /**
     * O id do Anúncio.
     */
    private int idAnuncio;
    
    /**
     * A data da realização da seriação
     */
    private String dataSeriacao;
    
    /**
     * A lista de candidaturas ao anúncio já seriadas.
     */
    private ArrayList<Classificacao> listaCandidaturasSeriadas;
    
    /**
     * O construtor completo da classe Seriação
     * @param idSeriacao - o id da Seriação.
     * @param idAnuncio - o id do Anúncio.
     * @param dataSeriacao - a data da realização da seriação
     * @param listaCandidaturasSeriadas - a lista de candidaturas ao anúncio já seriadas.
     */
    public Seriacao(int idSeriacao, int idAnuncio, String dataSeriacao, ArrayList<Classificacao> 
            listaCandidaturasSeriadas){
        setIdSeriacao(idSeriacao);
        setIdAnuncio(idAnuncio);
        setData(dataSeriacao);
        setListaSeriada(listaCandidaturasSeriadas);        
    }
    
    /**
     *  O construtor da classe Seriação com id ainda a ser atribuído na BD
     * @param idAnuncio - o id do Anúncio.
     * @param dataSeriacao - a data da realização da seriação
     * @param listaCandidaturasSeriadas - a lista de candidaturas ao anúncio já seriadas.
     */
    public Seriacao(int idAnuncio, String dataSeriacao, ArrayList<Classificacao> 
            listaCandidaturasSeriadas){
        setIdAnuncio(idAnuncio);
        setData(dataSeriacao);
        setListaSeriada(listaCandidaturasSeriadas);        
    }
    
    /**
     * Construtor da classe Seriação que recebe como 
     * parâmetro um tipo da mesma classe
     * @param seriacao 
     */
    public Seriacao(Seriacao seriacao){
        setIdSeriacao(seriacao.idSeriacao);
        setIdAnuncio(seriacao.idAnuncio);
        setData(seriacao.dataSeriacao);
        setListaSeriada(seriacao.listaCandidaturasSeriadas);        
    }    
    
    /**
     * Define o id da Seriação.
     * @param idSeriacao 
     */
    public void setIdSeriacao(int idSeriacao){
        this.idSeriacao = idSeriacao;
    }
    
    /**
     * Define o id do Anúncio.
     * @param idAnuncio 
     */
    public void setIdAnuncio(int idAnuncio){
        this.idAnuncio = idAnuncio;
    }
    
    /**
     * Define a data da realização da seriação
     * @param dataSeriacao 
     */
    public void setData(String dataSeriacao){
        this.dataSeriacao = dataSeriacao;
    }
    
    /**
     * Define a lista de candidaturas ao anúncio já seriadas.
     * @param listaCandidaturasSeriada 
     */
    public void setListaSeriada(ArrayList<Classificacao> listaCandidaturasSeriada){
        this.listaCandidaturasSeriadas = listaCandidaturasSeriadas;
    }
    
    /**
     * Devolve o id da Seriação.
     * @return 
     */
    public int getIdSeriacao(){
        return idSeriacao;
    }
    
    /**
     * Devolve o id do Anúncio.
     * @return 
     */
    public int getIdAnuncio(){
        return idAnuncio;
    }
    
    /**
     * Devolve a data da realização da seriação
     * @return 
     */
    public String dataSeriacao(){
        return dataSeriacao;
    }
    
    /**
     * Devolve a lista de candidaturas ao anúncio já seriadas.
     * @return 
     */
    public ArrayList<Classificacao> getListaSeriada(){
        return listaCandidaturasSeriadas;
    }
 
    
}
