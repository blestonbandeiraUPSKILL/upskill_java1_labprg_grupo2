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
    private String dataAtribuicao;
    private String codigoAtribuicao;
    private String dataInicioTarefa;


    public TabelaConsultaAtribuicao(String refTarefa, String emailFreelancer, String dataAtribuicao, String codigoAtribuicao){
        setRefTarefa(refTarefa);
        setEmailFreelancer(emailFreelancer);
        setDataAtribuicao(dataAtribuicao);
        setCodigoAtribuicao(codigoAtribuicao);
    }

    public TabelaConsultaAtribuicao(String refTarefa, String emailFreelancer, String dataAtribuicao, String codigoAtribuicao,
                                    String dataInicioTarefa){
        setRefTarefa(refTarefa);
        setEmailFreelancer(emailFreelancer);
        setDataAtribuicao(dataAtribuicao);
        setCodigoAtribuicao(codigoAtribuicao);
        setDataInicioTarefa(dataInicioTarefa);
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

    public String getDataAtribuicao(){
        return dataAtribuicao;
    }

    public String getCodigoAtribuicao(){
        return codigoAtribuicao;
    }

    public String getDataInicioTarefa(){
        return dataInicioTarefa;
    }

    @Override
    public Object toDTO() {
        return new TabelaConsultaAtribuicaoDTO(refTarefa, emailFreelancer, codigoAtribuicao, dataAtribuicao);
    }
}
