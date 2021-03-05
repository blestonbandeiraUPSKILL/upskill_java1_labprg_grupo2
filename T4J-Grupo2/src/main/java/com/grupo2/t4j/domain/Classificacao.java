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
public class Classificacao {
    
    /**
     * O id da Classificação
     */
    private int idClassificacao;
    
    /**
     * O id da Candidatura
     */
    private int idCandidatura;
   
    /**
     * A colocação ordinal do Freelancer
     */
    private int posicao;
    
    /**
     * O id do processo de seriação realizado.
     */
    private int idSeriacao;
    
    /**
     * Construtor vazio da classe Classificacao
     */
    public Classificacao(){
        
    }
    
    /**
     * Construtor completo da classe Classificacao
     * @param idClassificacao - o id da Classificação
     * @param posicao - a colocação ordinal do Freelancer  
     * @param idSeriacao - o id do processo de seriação realizado. 
     * @param idCandidatura - o id da Candidatura
    
     */
    public Classificacao(int idClassificacao,  int posicao, int idSeriacao,
            int idCandidatura){
        setIdClassificacao(idClassificacao);
        setPosicaoFreelancer(posicao);
        setIdSeriacao(idSeriacao);
        setIdCandidatura(idCandidatura);        
    }
    
     /**
     * Construtor da classe Classificacao com id ainda a ser atribuído na BD
     * @param posicao - a colocação ordinal do Freelancer  
     * @param idSeriacao - o id do processo de seriação realizado. 
     * @param idCandidatura - o id da Candidatura
     */
    public Classificacao(int posicao, int idSeriacao,
            int idCandidatura){
        setPosicaoFreelancer(posicao);
        setIdSeriacao(idSeriacao);
        setIdCandidatura(idCandidatura);         
    }
    
    
    /**
     * Construtor da classe Classificação que recebe como 
     * parâmetro um tipo da mesma classe
     * @param classificacao 
     */
    public Classificacao(Classificacao classificacao){
        setIdClassificacao(classificacao.idClassificacao);
        setPosicaoFreelancer(classificacao.posicao);
        setIdSeriacao(classificacao.idSeriacao);
        setIdCandidatura(classificacao.idCandidatura);      
    }
    
    /**
     * Define o id da Classificação
     * @param idClassificacao 
     */
    public void setIdClassificacao(int idClassificacao){
        this.idClassificacao = idClassificacao;
    }
    
    /**
     * Define a colocação ordinal do Freelancer
     * @param posicao
     */
    public void setPosicaoFreelancer(int posicao){
        this.posicao = posicao;
    }
    
    /**
     * Define o id da Seriação
     * @param idSeriacao 
     */
    public void setIdSeriacao(int idSeriacao){
        this.idSeriacao = idSeriacao;
    }
    
    /**
     * Define o id da Candidatura
     * @param idCandidatura
     */
    public void setIdCandidatura(int idCandidatura){
        this.idCandidatura = idCandidatura;
    } 
        
    /**
     * Devolve o id da Classificação
     * @return idClassificacao
     */
    public int getIdClassificacao(){
        return idClassificacao;
    }
    
/**
     * Devolve a colocação ordinal do Freelancer
     * @return posicao
     */
    public int getPosicaoFreelancer(){
        return posicao;
    }
    
    /**
     * Devolve o id da Seriação realizada.
     * @return idSeriacao
     */
    public int getIdSeriacao(){
        return idSeriacao;
    }
    
    /**
     * Devolve o id da Candidatura
     * @return idCandidatura
     */
    public int getIdCandidatura(){
        return idCandidatura;
    }
     
    /**
     * Representação textual da classe Classificação em formato de apresentação
     * @return o id da Seriação, o id da Candidatura e a  colocação 
     * ordinal desta Candidatura do Freelancer na seriação
     */
    @Override
    public String toString(){
        return String.format("ID Seriação: %-10s |ID Candidatura: %-10s"
                + "|Colocação: %-4d º lugar", idSeriacao, idCandidatura, posicao);                
    }
}
