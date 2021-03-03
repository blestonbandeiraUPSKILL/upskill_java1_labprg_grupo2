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
public class Classificacao {
    
    /**
     * 
     */
    private int idClassificacao;
    
    /**
     * O id do Anúncio
     */
    private String idAnuncio;
    
    /**
     * O id da Candidatura
     */
    private String idCandidatura;
   
    /**
     * A colocação ordinal do Freelancer
     */
    private int posicao;

    
    /**
     * Construtor completo da classe Classificacao
     * @param idAnuncio - o id do Anúncio
     * @param idCandidatura - o id da Candidatura
     * @param posicao - a colocação ordinal do Freelancer     
     */
    public Classificacao(String idAnuncio, String idCandidatura, int posicao){
        setIdAnuncio(idAnuncio);
        setIdCandidatura(idCandidatura);
        setColocacaoFreelancer(posicao);        
    }
    
    /**
     * Construtor da classe Classificação que recebe como 
     * parâmetro um tipo da mesma classe
     * @param classificacao 
     */
    public Classificacao(Classificacao classificacao){
        setIdAnuncio(classificacao.idAnuncio);
        setIdCandidatura(classificacao.idCandidatura);
        setColocacaoFreelancer(classificacao.posicao);        
    }
    
    /**
     * Define o id do Anúncio
     * @param idAnuncio
     */
    public void setIdAnuncio(String idAnuncio){
        this.idAnuncio = idAnuncio;
    }
    
    /**
     * Define o id da Candidatura
     * @param idCandidatura
     */
    public void setIdCandidatura(String idCandidatura){
        this.idCandidatura = idCandidatura;
    }
  
    /**
     * Define a colocação ordinal do Freelancer
     * @param colocacaoFreelancer
     */
    public void setColocacaoFreelancer(int posicao){
        this.posicao = posicao;
    }
    
    /**
     * Devolve o id do Anúncio
     * @return idAnuncio
     */
    public String getIdAnuncio(){
        return idAnuncio;
    }
    
    /**
     * Devolve o id da Candidatura
     * @return idCandidatura
     */
    public String getIdCandidatura(){
        return idCandidatura;
    }
    
    /**
     * Devolve a colocação ordinal do Freelancer
     * @return colocacaoFreelancer
     */
    public int getColocacaoFreelancer(){
        return posicao;
    }
     
    /**
     * Representação textual da classe ColocacaoSeriacao em formato de apresentação
     * @return o id do Anúncio, o id da Candidatura, o email do Freelancer, a 
     * colocação ordinal do Freelancer na seriação e a data da realização da seriação
     */
    @Override
    public String toString(){
        return String.format("ID Anúncio: %-12s |ID Candidatura: %-12s"
                + "|Colocação: %-4d º lugar", idAnuncio, idCandidatura, posicao);                
    }
}
