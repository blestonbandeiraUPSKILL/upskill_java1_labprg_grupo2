package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.domain.Anuncio;
import com.grupo2.t4j.exception.DataInvalidaException;
import com.grupo2.t4j.exception.IdInvalidoException;

public class AnuncioDTO {

    private int idAnuncio;
    private String referenciaTarefa;
    private String nifOrganizacao;
    private String dataInicioPublicitacao;
    private String dataFimPublicitacao;
    private String dataInicioCandidatura;
    private String dataFimCandidatura;
    private String dataInicioSeriacao;
    private String dataFimSeriacao;
    private int idTipoRegimento;
    private int idProcessoSeriacao;

    public AnuncioDTO() {}

    public AnuncioDTO(int idAnuncio, String referenciaTarefa, String nifOrganizacao, String dataInicioPublicitacao, String dataFimPublicitacao, String
            dataInicioCandidatura, String dataFimCandidatura, String dataInicioSeriacao,
                   String dataFimSeriacao, int idTipoRegimento) {
        this.idAnuncio = idAnuncio;
        this.referenciaTarefa = referenciaTarefa;
        this.nifOrganizacao = nifOrganizacao;
        this.dataInicioPublicitacao = dataInicioPublicitacao;
        this.dataFimPublicitacao = dataFimPublicitacao;
        this.dataInicioCandidatura = dataInicioCandidatura;
        this.dataFimCandidatura = dataFimCandidatura;
        this.dataInicioSeriacao = dataInicioSeriacao;
        this.dataFimSeriacao = dataFimSeriacao;
        this.idTipoRegimento = idTipoRegimento;
    }

    public int getIdAnuncio(){
        return idAnuncio;
    }

    public String getDtInicioPub() {
        return dataInicioPublicitacao;
    }

    public String getDtFimPub() {
        return dataFimPublicitacao;
    }

    public String getDtInicioCand() {
        return dataInicioCandidatura;
    }

    public String getDtFimCand() {
        return dataFimCandidatura;
    }

    public String getdataInicioSeriacao() {
        return dataInicioSeriacao;
    }

    public String getdataFimSeriacao() {
        return dataFimSeriacao;
    }

    public String getReferenciaTarefa() {
        return referenciaTarefa;
    }

    public String getNifOrganizacao() {
        return nifOrganizacao;
    }

    public int getIdTipoRegimento() {
        return idTipoRegimento;
    }

    public int getIdProcessoSeriacao() {
        return idProcessoSeriacao;
    }
}
