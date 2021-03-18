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
public class TabelaFreelancerClassificacaoDTO {

    private int idCandidatura;
    private String emailFreelancer;
    private int classificacao;

    public TabelaFreelancerClassificacaoDTO(int idCandidatura, String emailFreelancer, int classificacao){
        this.idCandidatura = idCandidatura;
        this.emailFreelancer = emailFreelancer;
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
