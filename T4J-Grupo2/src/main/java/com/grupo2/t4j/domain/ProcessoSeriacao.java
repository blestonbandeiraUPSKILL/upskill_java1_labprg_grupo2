/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

import java.util.Calendar;

/**
 *
 * @author CAD
 */

public class ProcessoSeriacao {

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
     * A data atual no formato da classe Data
     */
    private Calendar cal = Calendar.getInstance();
    
    /**
     * O dia de hoje
     */
    private Data hoje = new Data(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH));
    
    /**
     * O construtor vazio da classe Seriação
     */
    public ProcessoSeriacao(){
        
    }
    
    /**
     * O construtor completo da classe Seriação
     * @param idSeriacao - o id da Seriação.
     * @param idAnuncio - o id do Anúncio.
     * @param dataSeriacao - a data da realização da seriação     
     */
    public ProcessoSeriacao(int idSeriacao, int idAnuncio, String dataSeriacao){
        setIdSeriacao(idSeriacao);
        setIdAnuncio(idAnuncio);
        setData(dataSeriacao);              
    }
    
    /**
     * O construtor da classe Seriação com id ainda a ser atribuído na BD
     * @param idAnuncio - o id do Anúncio.
     * @param dataSeriacao - a data da realização da seriação
     */
    public ProcessoSeriacao(int idAnuncio, String dataSeriacao){
        setIdAnuncio(idAnuncio);
        setData(dataSeriacao);               
    }
    
    /**
     * O construtor da classe Seriação com id ainda a ser atribuído na BD
     * e com a data de Seriação a ser gravada automaticamente na BD
     * @param idAnuncio - o id do Anúncio.
     */
    public ProcessoSeriacao(int idAnuncio){
        setIdAnuncio(idAnuncio);                      
    }
    
    /**
     * Construtor da classe Seriação que recebe como 
     * parâmetro um tipo da mesma classe
     * @param seriacao 
     */
    public ProcessoSeriacao(ProcessoSeriacao seriacao){
        setIdSeriacao(seriacao.idSeriacao);
        setIdAnuncio(seriacao.idAnuncio);
        setData(seriacao.dataSeriacao);                
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
    public String getDataSeriacao(){
        return dataSeriacao;
    }
    
      /**
     * Representação textual da classe Classificação em formato de apresentação
     * @return o id da Seriação, o id do Anúncio, o id da Candidatura e a  colocação 
     * ordinal desta Candidatura do Freelancer na seriação
     */
    @Override
    public String toString(){
        return String.format("ID Seriação: %-12s |ID Anúncio: %-12s |Data da Seriação:"
                + " %-12s", idSeriacao, idAnuncio, dataSeriacao);                
    }
   }
