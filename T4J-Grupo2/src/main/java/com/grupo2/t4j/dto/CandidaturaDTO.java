package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.IdInvalidoException;

public class CandidaturaDTO {

    private int idCandidatura;
    private int idAnuncio;
    private String emailFreelancer;
    private String dataCandidatura;
    private double valorPretendido;
    private int numeroDias;
    private String txtApresentacao;
    private String txtMotivacao;
    private String dataEdicaoCandidatura;


    public CandidaturaDTO(){
    }

    public CandidaturaDTO(int idCandidatura, int idAnuncio, String emailFreelancer, double valorPretendido,
                       int numeroDias, String txtApresentacao, String txtMotivacao, String dataCandidatura, String dataEdicaoCandidatura){
        this.idCandidatura = idCandidatura;
        this.idAnuncio = idAnuncio;
        this.emailFreelancer = emailFreelancer;
        this.valorPretendido = valorPretendido;
        this.numeroDias = numeroDias;
        this.txtApresentacao = txtApresentacao;
        this.txtMotivacao = txtMotivacao;
        this.dataCandidatura = dataCandidatura;
        this.dataEdicaoCandidatura = dataEdicaoCandidatura;
    }

    public int getIdCandidatura(){
        return idCandidatura;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public String getEmailFreelancer(){
        return emailFreelancer;
    }

    public String getDataCandidatura(){
        return dataCandidatura;
    }

    public double getValorPretendido(){
        return valorPretendido;
    }

    public int getNumeroDias(){
        return numeroDias;
    }

    public String getApresentacao(){
        return txtApresentacao;
    }

    public String getMotivacao(){
        return txtMotivacao;
    }

    public String getDataEdicaoCandidatura() {
        return dataEdicaoCandidatura;
    }
}
