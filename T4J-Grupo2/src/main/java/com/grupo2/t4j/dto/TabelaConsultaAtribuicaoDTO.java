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

    public TabelaConsultaAtribuicaoDTO(String refTarefa, String emailFreelancer, String codigoAtribuicao, String dataAtribuicao){
        this.refTarefa = refTarefa;
        this.emailFreelancer = emailFreelancer;
        this.codigoAtribuicao = codigoAtribuicao;
        this.dataAtribuicao = dataAtribuicao;
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
}
