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
public class TabelaFreelancerClassificacao {
    
    private int idCandidatura;
    private String emailFreelancer;
    private int classificacao;
    
    public TabelaFreelancerClassificacao(int idCandidatura, String emailFreelancer, int classificacao){
        setIdCandidatura(idCandidatura);
        setEmailFreelancer(emailFreelancer);
        setClassificacao(classificacao);
    }
    
    public TabelaFreelancerClassificacao(int idCandidatura, String emailFreelancer){
        setIdCandidatura(idCandidatura);
        setEmailFreelancer(emailFreelancer);       
    }
    
    public void setIdCandidatura(int idCandidatura){
        this.idCandidatura = idCandidatura;
    }
    
    public void setEmailFreelancer(String emailFreelancer){
        this.emailFreelancer = emailFreelancer;
    }
    
    public void setClassificacao(int classificacao){
        this.classificacao = classificacao;
    }
    
    public String getEmailFreelancer(){
        return emailFreelancer;
    }
    
    public int getClassificacao(){
        return classificacao;
    }
    
    public int getIdCandidatura(){
        return idCandidatura;
    }
}
