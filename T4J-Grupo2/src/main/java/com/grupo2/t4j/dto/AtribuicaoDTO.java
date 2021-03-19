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
public class AtribuicaoDTO{

    private int idAtribuicao;
    private String nifOrganizacao;
    private String refTarefa;
    private int idAnuncio;
    private int idCandidatura;
    private String emailFreelancer;
    private double valorAceite;
    private int numDiasAceite;
    private String codigoAtribuicao;
    private String dataAtribuicao;
    private String dataInicioTarefa;
    private String dataFimTarefa;

    public AtribuicaoDTO() {}
    public AtribuicaoDTO(int idAtribuicao, String nifOrganizacao, String refTarefa, int idAnuncio, int idCandidatura, String emailFreelancer,
                         double valorAceite, int numDiasAceite, String codigoAtribuicao, String dataAtribuicao, String dataInicioTarefa,
                         String dataFimTarefa){
        this.idAtribuicao = idAtribuicao;
        this.nifOrganizacao = nifOrganizacao;
        this.refTarefa = refTarefa;
        this.idAnuncio = idAnuncio;
        this.idCandidatura = idCandidatura;
        this.emailFreelancer = emailFreelancer;
        this.valorAceite = valorAceite;
        this.numDiasAceite = numDiasAceite;
        this.codigoAtribuicao = codigoAtribuicao;
        this.dataAtribuicao = dataAtribuicao;
        this.dataInicioTarefa = dataInicioTarefa;
        this.dataFimTarefa = dataFimTarefa;
    }

    public int getIdAtribuicao() {
        return idAtribuicao;
    }

    public String getNifOrganizacao() {
        return nifOrganizacao;
    }

    public String getRefTarefa() {
        return refTarefa;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public int getIdCandidatura() {
        return idCandidatura;
    }

    public String getEmailFreelancer() {
        return emailFreelancer;
    }

    public double getValorAceite() {
        return valorAceite;
    }

    public int getNumDiasAceite() {
        return numDiasAceite;
    }

    public String getCodigoAtribuicao() {
        return codigoAtribuicao;
    }

    public String getDataAtribuicao() {
        return dataAtribuicao;
    }

    public String getDataInicioTarefa() {
        return dataInicioTarefa;
    }

    public String getDataFimTarefa() {
        return dataFimTarefa;
    }

    
}
