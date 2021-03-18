/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

import com.grupo2.t4j.dto.DTO;
import com.grupo2.t4j.dto.TabelaConsultaAtribuicaoDTO;

/**
 *
 * @author CAD
 */
public class TabelaConsultaAtribuicao implements DTO {

    private String refTarefa;
    private String emailFreelancer;
    private String codigoAtribuicao;
    private String dataAtribuicao;

    public TabelaConsultaAtribuicao(String refTarefa, String emailFreelancer, String codigoAtribuicao, String dataAtribuicao){
        setRefTarefa(refTarefa);
        setEmailFreelancer(emailFreelancer);
        setCodigoAtribuicao(codigoAtribuicao);
        setDataAtribuicao(dataAtribuicao);
    }

    public void setRefTarefa(String refTarefa){
        this.refTarefa = refTarefa;
    }

    public void setEmailFreelancer(String emailFreelancer){
        this.emailFreelancer = emailFreelancer;
    }

    public void setCodigoAtribuicao(String codigoAtribuicao){
        this.codigoAtribuicao = codigoAtribuicao;
    }

    public void setDataAtribuicao(String dataAtribuicao){
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

    @Override
    public Object toDTO() {
        return new TabelaConsultaAtribuicaoDTO(refTarefa, emailFreelancer, codigoAtribuicao, dataAtribuicao);
    }
}
