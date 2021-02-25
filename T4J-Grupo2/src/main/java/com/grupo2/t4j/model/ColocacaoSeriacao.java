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
public class ColocacaoSeriacao {
    
    /**
     * O id do Anúncio
     */
    private String idAnuncio;
    
    /**
     * O id da Candidatura
     */
    private String idCandidatura;
    
    /**
     * O email do Freelancer que se candidatou
     */
    private String emailFreelancer;
    
    /**
     * A colocação ordinal do Freelancer
     */
    private int colocacaoFreelancer;
    
    /**
     * A data de realização da seriação do Anúncio
     */
    private Data dataSeriacao;
    
    /**
     * Construtor completo da classe Colocação na Seriação de Anúncios
     * @param idAnuncio - o id do Anúncio
     * @param idCandidatura - o id da Candidatura
     * @param emailFreelancer - email do Freelancer que se candidatou
     * @param colocacaoFreelancer - a colocação ordinal do Freelancer
     * @param dataSeriacao - a data de realização da seriação do Anúncio
     */
    public ColocacaoSeriacao(String idAnuncio, String idCandidatura, String emailFreelancer,
            int colocacaoFreelancer, 
            Data dataSeriacao){
        setIdAnuncio(idAnuncio);
        setIdCandidatura(idCandidatura);
        setEmailFreelancer(emailFreelancer);
        setColocacaoFreelancer(colocacaoFreelancer);
        setDataSeriacao(dataSeriacao);
    }
    
    /**
     * Construtor da classe Colocação na Seriação de Anúncios que recebe como 
     * parâmetro um tipo da mesma classe
     * @param colocacaoSeriacao 
     */
    public ColocacaoSeriacao(ColocacaoSeriacao colocacaoSeriacao){
        setIdAnuncio(colocacaoSeriacao.idAnuncio);
        setIdCandidatura(colocacaoSeriacao.idCandidatura);
        setEmailFreelancer(emailFreelancer);
        setColocacaoFreelancer(colocacaoSeriacao.colocacaoFreelancer);
        setDataSeriacao(colocacaoSeriacao.dataSeriacao);
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
     * Define email do Freelancer que se candidatou
     * @param emailFreelancer 
     */
    public void setEmailFreelancer(String emailFreelancer){
        this.emailFreelancer = emailFreelancer;
    }
    /**
     * Define a colocação ordinal do Freelancer
     * @param colocacaoFreelancer
     */
    public void setColocacaoFreelancer(int colocacaoFreelancer){
        this.colocacaoFreelancer = colocacaoFreelancer;
    }
    
    /**
     * Define a data de realização da seriação do Anúncio
     * @param dataSeriacao 
     */
    public void setDataSeriacao(Data dataSeriacao){
        this.dataSeriacao = dataSeriacao;
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
     * Devolve o email do Freelancer que se candidatou
     * @return emailFreelancer
     */
    public String getEmailFreelancer(){
        return emailFreelancer;
    }
    
    /**
     * Devolve a colocação ordinal do Freelancer
     * @return colocacaoFreelancer
     */
    public int getColocacaoFreelancer(){
        return colocacaoFreelancer;
    }
    
    /**
     * Devolve a data de realização da seriação do Anúncio
     * @return dataSeriacao
     */
    public Data getDataSeriacao(){
        return dataSeriacao;
    }
    
    /**
     * Representação textual da classe ColocacaoSeriacao em formato de apresentação
     * @return o id do Anúncio, o id da Candidatura, o email do Freelancer, a 
     * colocação ordinal do Freelancer na seriação e a data da realização da seriação
     */
    @Override
    public String toString(){
        return String.format("ID Anúncio: %-12s |ID Candidatura: %-12s |Email Freelancer: %-20s "
                + "|Colocação: %-4d º lugar |Data Seriação: %-10s", idAnuncio, idCandidatura,
                emailFreelancer, colocacaoFreelancer, dataSeriacao.toAnoMesDiaString());
    }
}
