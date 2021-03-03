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
     * O id da Classificação
     */
    private int idClassificacao;
    
    /**
     * O id do Anúncio
     */
    private int idAnuncio;
    
    /**
     * O id da Candidatura
     */
    private int idCandidatura;
   
    /**
     * A colocação ordinal do Freelancer
     */
    private int posicao;
    
    /**
     * Construtor vazio da classe Classificacao
     */
    public Classificacao(){
        
    }
    
    /**
     * Construtor completo da classe Classificacao
     * @param idClassificacao - o id da Classificação
     * @param idAnuncio - o id do Anúncio
     * @param idCandidatura - o id da Candidatura
     * @param posicao - a colocação ordinal do Freelancer     
     */
    public Classificacao(int idClassificacao, int idAnuncio, int idCandidatura, 
            int posicao){
        setIdClassificacao(idClassificacao);
        setIdAnuncio(idAnuncio);
        setIdCandidatura(idCandidatura);
        setColocacaoFreelancer(posicao);        
    }
    
     /**
     * Construtor da classe Classificacao com id ainda a ser atribuído na BD
     * @param idAnuncio - o id do Anúncio
     * @param idCandidatura - o id da Candidatura
     * @param posicao - a colocação ordinal do Freelancer     
     */
    public Classificacao(int idAnuncio, int idCandidatura, 
            int posicao){
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
        setIdClassificacao(classificacao.idClassificacao);
        setIdAnuncio(classificacao.idAnuncio);
        setIdCandidatura(classificacao.idCandidatura);
        setColocacaoFreelancer(classificacao.posicao);        
    }
    
    /**
     * 
     */
    public void setIdClassificacao(int idClassificacao){
        this.idClassificacao = idClassificacao;
    }
    
    /**
     * Define o id do Anúncio
     * @param idAnuncio
     */
    public void setIdAnuncio(int idAnuncio){
        this.idAnuncio = idAnuncio;
    }
    
    /**
     * Define o id da Candidatura
     * @param idCandidatura
     */
    public void setIdCandidatura(int idCandidatura){
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
    public int getIdAnuncio(){
        return idAnuncio;
    }
    
    /**
     * Devolve o id da Candidatura
     * @return idCandidatura
     */
    public int getIdCandidatura(){
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
     * Representação textual da classe Classificação em formato de apresentação
     * @return o id do Anúncio, o id da Candidatura e a  olocação ordinal desta
     * Candidatura do Freelancer na seriação
     */
    @Override
    public String toString(){
        return String.format("ID Anúncio: %-12s |ID Candidatura: %-12s"
                + "|Colocação: %-4d º lugar", idAnuncio, idCandidatura, posicao);                
    }
}
