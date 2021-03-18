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
public class Atribuicao {

    private int idAtribuicao;
    private String nifOrganizacao;
    private String refTarefa;
    private int idAnuncio;
    private int idCandidatura;
    private String emailFreelancer;
    private double valorAceito;
    private int numDiasAceito;
    private String codigoAtribuicao;
    private String dataAtribuicao;
    private String dataInicioTarefa;
    private String dataFimTarefa;

    public Atribuicao(String nifOrganizacao, String refTarefa, int idAnuncio, int idCandidatura, String emailFreelancer,
                      double valorAceito, int numDiasAceito, String codigoAtribuicao, String dataAtribuicao, String dataInicioTarefa,
                      String dataFimTarefa){
        setNifOrganizacao(nifOrganizacao);
        setRefTarefa(refTarefa);
        setIdAnuncio(idAnuncio);
        setIdCandidatura(idCandidatura);
        setEmailFreelancer(emailFreelancer);
        setValorAceito(valorAceito);
        setNumDiasAceito(numDiasAceito);
        setCodigoAtribuicao(codigoAtribuicao);
        setDataAtribuicao(dataAtribuicao);
        setDataInicioTarefa(dataInicioTarefa);
        setDataFimTarefa(dataFimTarefa);
    }

    public void setNifOrganizacao(String nifOrganizacao){
        this.nifOrganizacao = nifOrganizacao;
    }

    public void setRefTarefa(String refTarefa){
        this.refTarefa = refTarefa;
    }

    public void setIdAnuncio(int idAnuncio){
        this.idAnuncio = idAnuncio;
    }

    public void setIdCandidatura(int idCandidatura){
        this.idCandidatura = idCandidatura
    }

    public void setEmailFreelancer(String emailFreelancer){
        this.emailFreelancer = emailFreelancer;
    }

    public void setValorAceito(double valorAceito){
        this.valorAceito = valorAceito;
    }

    public void setNumDiasAceito(int numDiasAceito){
        this.numDiasAceito = numDiasAceito;
    }

    public void setCodigoAtribuicao(String codigoAtribuicao){
        this.codigoAtribuicao = codigoAtribuicao;
    }

    public void setDataAtribuicao(String dataAtribuicao){
        this.dataAtribuicao = dataAtribuicao;
    }

    public void setDataInicioTarefa(String dataInicioTarefa){
        this.dataInicioTarefa = dataInicioTarefa;
    }

    public void setDataFimTarefa(String dataFimTarefa){
        this.dataFimTarefa = dataFimTarefa;
    }











    
    
    //Falta acabar
    
}
