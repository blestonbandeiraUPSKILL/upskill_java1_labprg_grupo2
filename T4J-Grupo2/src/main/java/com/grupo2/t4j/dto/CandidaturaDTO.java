package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.IdInvalidoException;

@JsonPropertyOrder({"idCandidatura", "idAnuncio", "emailFreelancer", "dataCandidatura",
        "valorPretendido", "numeroDias", "txtApresentacao", "txtMotivacao"})

public class CandidaturaDTO {

    @JacksonXmlProperty(localName = "idCandidatura")
    private int idCandidatura;

    @JacksonXmlProperty(localName = "idAnuncio")
    private int idAnuncio;

    @JacksonXmlProperty(localName = "emailFreelancer")
    private String emailFreelancer;

    @JacksonXmlProperty(localName = "dataCandidatura")
    private String dataCandidatura;

    @JacksonXmlProperty(localName = "valorPretendido")
    private double valorPretendido;

    @JacksonXmlProperty(localName = "numeroDias")
    private int numeroDias;

    @JacksonXmlProperty(localName = "txtApresentacao")
    private String txtApresentacao;

    @JacksonXmlProperty(localName = "txtMotivacao")
    private String txtMotivacao;

    public CandidaturaDTO(){
    }

    public CandidaturaDTO(int idCandidatura, int idAnuncio, String emailFreelancer, double valorPretendido,
                       int numeroDias, String txtApresentacao, String txtMotivacao, String dataCandidatura){
        setIdCandidatura(idCandidatura);
        setIdAnuncio(idAnuncio);
        setEmailFreelancer(emailFreelancer);
        setValor(valorPretendido);
        setDias(numeroDias);
        setApresentacao(txtApresentacao);
        setMotivacao(txtMotivacao);
        setData(dataCandidatura);
    }

    public CandidaturaDTO(int idCandidatura, String emailFreelancer, double valorPretendido,
                       int numeroDias){
        setIdCandidatura(idCandidatura);
        setEmailFreelancer(emailFreelancer);
        setValor(valorPretendido);
        setDias(numeroDias);
        setApresentacao("");
        setMotivacao("");
    }

    public CandidaturaDTO(CandidaturaDTO candidaturaDTO){
        setIdCandidatura(candidaturaDTO.idCandidatura);
        setEmailFreelancer(candidaturaDTO.emailFreelancer);
        setData(candidaturaDTO.dataCandidatura);
        setValor(candidaturaDTO.valorPretendido);
        setDias(candidaturaDTO.numeroDias);
        setApresentacao(candidaturaDTO.txtApresentacao);
        setMotivacao(candidaturaDTO.txtMotivacao);
    }

    public CandidaturaDTO(int idAnuncio, String emailFreelancer, double valor, int dias, String apresentacao, String motivacao) {
        setIdAnuncio(idAnuncio);
        setEmailFreelancer(emailFreelancer);
        setValor(valor);
        setDias(dias);
        setApresentacao(apresentacao);
        setMotivacao(motivacao);
    }

    public void setIdAnuncio(int idAnuncio) {
        if (idAnuncio > 0) {
            this.idAnuncio = idAnuncio;
        }
        else {
            throw new IdInvalidoException("O id não é válido.");
        }
    }

    public void setIdCandidatura(int idCandidatura){
        if (idCandidatura <= 0 ) {
            throw new IdInvalidoException("Id da Candidatura é inválido!");
        }
        this.idCandidatura = idCandidatura;
    }

    public void setEmailFreelancer(String emailFreelancer){
        this.emailFreelancer = emailFreelancer;
    }

    public void setData(String dataCandidatura){
        this.dataCandidatura = dataCandidatura;
    }

    public void setValor(double valorPretendido){
        if (valorPretendido > 0) {
            this.valorPretendido = valorPretendido;
        }
        else{
            throw new IllegalArgumentException("O valor prentendido informado não"
                    + "é válido!");
        }
    }

    public void setDias(int numeroDias){
        if(numeroDias > 0){
            this.numeroDias = numeroDias;
        }
        else{
            throw new IllegalArgumentException("O número de dias informado não"
                    + "é válido!");
        }
    }

    public void setApresentacao(String txtApresentacao){
        this.txtApresentacao = txtApresentacao;
    }

    public void setMotivacao(String txtMotivacao){
        this.txtMotivacao = txtMotivacao;
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
}
