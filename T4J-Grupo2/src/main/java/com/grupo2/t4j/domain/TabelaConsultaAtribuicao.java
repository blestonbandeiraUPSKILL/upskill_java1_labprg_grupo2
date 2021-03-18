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
public class TabelaConsultaAtribuicao {

    private String refTarefa;
    private String emailFreelancer;
    private String idAtribuicao;
    private String dataAtribuicao;
    private String emailGestor;

    public TabelaConsultaAtribuicao(String refTarefa, String emailFreelancer, String idAtribuicao, String dataAtribuicao,
                                    String emailGestor){
        setRefTarefa(refTarefa);
        setEmailFreelancer(emailFreelancer);
        setIdAtribuicao(idAtribuicao);
        setDataAtribuicao(dataAtribuicao);
        setEmailGestor(emailGestor);
    }

    public void setRefTarefa(String refTarefa){
        this.refTarefa = refTarefa;
    }

    public void setEmailFreelancer(String emailFreelancer){
        this.emailFreelancer = emailFreelancer;
    }

    public void setIdAtribuicao(String idAtribuicao){
        this.idAtribuicao = idAtribuicao;
    }

    public void setDataAtribuicao(String dataAtribuicao){
        this.dataAtribuicao = dataAtribuicao;
    }

    public void setEmailGestor(String emailGestor){
        this.emailGestor = emailGestor;
    }

    public String getRefTarefa(){
        return refTarefa;
    }

    public String getEmailFreelancer(){
        return emailFreelancer;
    }

    public String getIdAtribuicao(){
        return idAtribuicao;
    }

    public String getDataAtribuicao(){
        return dataAtribuicao;
    }

    public String getEmailGestor(){
        return emailGestor;
    }
}
