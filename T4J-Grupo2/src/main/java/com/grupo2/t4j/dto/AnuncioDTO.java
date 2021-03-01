package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.DataInvalidaException;
import com.grupo2.t4j.exception.IdInvalidoException;

@JsonPropertyOrder({"idAnuncio", "referenciaTarefa", "nifOrganizacao", "dataInicioPublicitacao",
                    "dataFimPublicitacao", "dataInicioCandidatura", "dataFimCandidatura",
                    "dataInicioSeriacao", "dataFimSeriacao", "idTipoRegimento", "idProcessoSeriacao"})


public class AnuncioDTO {

    @JacksonXmlProperty(localName = "idAnuncio")
    private int idAnuncio;

    @JacksonXmlProperty(localName = "referenciaTarefa")
    private String referenciaTarefa;

    @JacksonXmlProperty(localName = "nifOrganizacao")
    private String nifOrganizacao;

    @JacksonXmlProperty(localName = "dataInicioPublicitacao")
    private String dataInicioPublicitacao;

    @JacksonXmlProperty(localName = "dataFimPublicitacao")
    private String dataFimPublicitacao;

    @JacksonXmlProperty(localName = "dataInicioCandidatura")
    private String dataInicioCandidatura;

    @JacksonXmlProperty(localName = "dataFimCandidatura")
    private String dataFimCandidatura;

    @JacksonXmlProperty(localName = "dataInicioSeriacao")
    private String dataInicioSeriacao;

    @JacksonXmlProperty(localName = "dataFimsSeriacao")
    private String dataFimSeriacao;

    @JacksonXmlProperty(localName = "idTipoRegimento")
    private int idTipoRegimento;

    @JacksonXmlProperty(localName = "idProcessoSeriacao")
    private int idProcessoSeriacao;


    public AnuncioDTO(int idAnuncio, String referenciaTarefa, String nifOrganizacao, String dataInicioPublicitacao, String dataFimPublicitacao, String
            dataInicioCandidatura, String dataFimCandidatura, String dataInicioSeriacao,
                   String dataFimSeriacao, int idTipoRegimento) {
        this.idAnuncio = idAnuncio;
        setReferenciaTarefa(referenciaTarefa);
        setNifOrganizacao(nifOrganizacao);
        setDtInicioPub(AnuncioDTO.this.dataInicioPublicitacao);
        setDtFimPub(dataFimPublicitacao);
        setDtInicioCand(dataInicioCandidatura);
        setDtFimCand(dataFimCandidatura);
        setdataInicioSeriacao(dataInicioSeriacao);
        setdataFimSeriacao(dataFimSeriacao);
        setIdTipoRegimento(idTipoRegimento);
    }

    public AnuncioDTO (AnuncioDTO anuncioDTO){
        setIdAnuncio(anuncioDTO.idAnuncio);
        setReferenciaTarefa(anuncioDTO.referenciaTarefa);
        setNifOrganizacao(anuncioDTO.nifOrganizacao);
        setDtInicioPub(anuncioDTO.dataInicioPublicitacao);
        setDtFimPub(anuncioDTO.dataFimPublicitacao);
        setDtInicioCand(anuncioDTO.dataInicioCandidatura);
        setDtFimCand(anuncioDTO.dataFimCandidatura);
        setdataInicioSeriacao(anuncioDTO.dataInicioSeriacao);
        setdataFimSeriacao(anuncioDTO.dataFimSeriacao);
    }

    public AnuncioDTO(int idAnuncio, String referenciaTarefa, String nifOrganizacao, String dataInicioPublicitacao,
                   String dataFimPublicitacao, String dataInicioCandidatura, String dataFimCandidatura, String dataInicioSeriacao, String dataFimSeriacao) {
        setIdAnuncio(idAnuncio);
        setReferenciaTarefa(referenciaTarefa);
        setNifOrganizacao(nifOrganizacao);
        setDtInicioPub(dataInicioPublicitacao);
        setDtFimPub(dataFimPublicitacao);
        setDtInicioCand(dataInicioCandidatura);
        setDtFimCand(dataFimCandidatura);
        setdataInicioSeriacao(dataInicioSeriacao);
        setdataFimSeriacao(dataFimSeriacao);
    }

    public AnuncioDTO(String referencia, String nifOrganizacao, String dataInicioPublicitacao, String dataFimPublicitacao, String dataInicioCandidatura,
                   String dataFimCandidatura, String dataInicioSeriacao, String dataFimSeriacao, int idTipoRegimento) {
        setReferenciaTarefa(referencia);
        setNifOrganizacao(nifOrganizacao);
        setDtInicioPub(dataInicioPublicitacao);
        setDtFimPub(dataFimPublicitacao);
        setDtInicioCand(dataInicioCandidatura);
        setDtFimCand(dataFimCandidatura);
        setdataInicioSeriacao(dataInicioSeriacao);
        setdataFimSeriacao(dataFimSeriacao);
        setIdTipoRegimento(idTipoRegimento);
    }

    public void setIdAnuncio(int idAnuncio){
        if (idAnuncio < 0 ) {
            throw new IdInvalidoException("Id do anúncio é inválido!");
        }
        this.idAnuncio = idAnuncio;
    }

    public void setReferenciaTarefa(String referenciaTarefa) {
        this.referenciaTarefa = referenciaTarefa;
    }

    public void setNifOrganizacao(String nifOrganizacao) {
        this.nifOrganizacao = nifOrganizacao;
    }

    public void setDtInicioPub(String dataInicioPublicitacao) {
        if(dataInicioPublicitacao == null || dataInicioPublicitacao.trim().isEmpty()){
            throw new DataInvalidaException("A data de início de publicitação não é válida.");
        }
        this.dataInicioPublicitacao = dataInicioPublicitacao;
    }

    public void setDtFimPub(String dataFimPublicitacao) {
         if(dataFimPublicitacao == null || dataFimPublicitacao.trim().isEmpty()) {
             throw new DataInvalidaException("A data de fim de publicitação não é válida.");
         }
        this.dataFimPublicitacao = dataFimPublicitacao;
    }

    public void setDtInicioCand(String dataInicioCandidatura) {
        if(dataInicioCandidatura == null || dataInicioCandidatura.trim().isEmpty()){
            throw new DataInvalidaException("A data de início de candidatura não é válida.");
        }
        this.dataInicioCandidatura = dataInicioCandidatura;
    }

    public void setDtFimCand(String dataFimCandidatura) {
        if(dataFimCandidatura == null || dataFimCandidatura.trim().isEmpty()){
            throw new DataInvalidaException("A data de fim de candidatura não é válida.");
        }
        this.dataFimCandidatura = dataFimCandidatura;
    }

    public void setdataInicioSeriacao(String dataInicioSeriacao) {
        if(dataInicioSeriacao == null || dataInicioSeriacao.trim().isEmpty()){
            throw new DataInvalidaException("A data de início de seriação não é válida.");
        }
        this.dataInicioSeriacao = dataInicioSeriacao;
    }

    public void setdataFimSeriacao(String dataFimSeriacao) {
        if(dataFimSeriacao == null || dataFimSeriacao.trim().isEmpty()){
            throw new DataInvalidaException("A data de fim de seriação não é válida.");
        }
        this.dataFimSeriacao = dataFimSeriacao;
    }

    public void setIdTipoRegimento(int idTipoRegimento) {
        this.idTipoRegimento = idTipoRegimento;
    }

    public void setIdProcessoSeriacao(int idProcessoSeriacao) {
        this.idProcessoSeriacao = idProcessoSeriacao;
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
