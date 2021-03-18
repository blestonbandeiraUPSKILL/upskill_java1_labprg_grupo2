package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.domain.Email;

public class ReconhecimentoGPDTO {

    private int idGrauProficiencia;
    private String dataReconhecimento;
    private Email emailFreelancer;
    private String designacaoGrau;
    private String descBreveCompetencia;

    public ReconhecimentoGPDTO(){
    }

    public ReconhecimentoGPDTO(int idGrauProficiencia,
                               Email emailFreelancer, String dataReconhecimento,
                               String designacaoGrau, String descBreveCompetencia){
        this.idGrauProficiencia = idGrauProficiencia;
        this.dataReconhecimento = dataReconhecimento;
        this.emailFreelancer = emailFreelancer;
        this.designacaoGrau = designacaoGrau;
        this.descBreveCompetencia = descBreveCompetencia;
    }

    public int getIdGrauProficiencia() {
        return idGrauProficiencia;
    }

    public String getDataReconhecimento() {
        return dataReconhecimento;
    }

    public Email getEmailFreelancer() {
        return emailFreelancer;
    }

    public String getDesignacaoGrau() {
        return designacaoGrau;
    }

    public String getDescBreveCompetencia() {
        return descBreveCompetencia;
    }
}
