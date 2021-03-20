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
public class TabelaConsultaAtribuicaoDTO {

    private String refTarefa;
    private String emailFreelancer;
    private String codigoAtribuicao;
    private String dataAtribuicao;
    private String dataInicioTarefa;

    public TabelaConsultaAtribuicaoDTO(String refTarefa, String emailFreelancer, String codigoAtribuicao, String dataAtribuicao){
        this.refTarefa = refTarefa;
        this.emailFreelancer = emailFreelancer;
        this.codigoAtribuicao = codigoAtribuicao;
        this.dataAtribuicao = dataAtribuicao;
    }

    public TabelaConsultaAtribuicaoDTO(String refTarefa, String emailFreelancer, String dataAtribuicao, String codigoAtribuicao,
                                    String dataInicioTarefa){
        this.refTarefa = refTarefa;
        this.emailFreelancer = emailFreelancer;
        this.codigoAtribuicao = codigoAtribuicao;
        this.dataAtribuicao = dataAtribuicao;
        this.dataInicioTarefa = dataInicioTarefa;
    }

    public void setRefTarefa(String refTarefa){
        this.refTarefa = refTarefa;
    }

    public void setEmailFreelancer(String emailFreelancer){
        this.emailFreelancer = emailFreelancer;
    }

    public void setDataAtribuicao(String dataAtribuicao){
        this.dataAtribuicao = dataAtribuicao;
    }

    public void setCodigoAtribuicao(String codigoAtribuicao){
        this.codigoAtribuicao = codigoAtribuicao;
    }

    public void setDataInicioTarefa(String dataInicioTarefa){
        this.dataInicioTarefa = dataInicioTarefa;
    }

    public String getRefTarefa(){
        return refTarefa;
    }

    public String getEmailFreelancer(){
        return emailFreelancer;
    }

    public String getCodigoAtribuicao(){
        return codigoAtribuicao;
    }

    public String getDataAtribuicao(){
        return dataAtribuicao;
    }

    public String getDataInicioTarefa(){
        return dataInicioTarefa;
    }

}
