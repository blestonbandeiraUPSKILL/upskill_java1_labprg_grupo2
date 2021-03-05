package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({"idGrauProficiencia", "dataReconhecimento", "emailFreelancer"})

public class ReconhecimentoGPDTO {

    @JacksonXmlProperty(localName = "idGrauProficiencia")
    private int idGrauProficiencia;

    @JacksonXmlProperty(localName = "dataReconhecimento")
    private String dataReconhecimento;

    @JacksonXmlProperty(localName = "emailFreelancer")
    private EmailDTO emailFreelancer;

    public ReconhecimentoGPDTO(){
    }

    public ReconhecimentoGPDTO(int idGrauProficiencia,
                            EmailDTO emailFreelancer, String dataReconhecimento){
        setIdGrauProficiencia(idGrauProficiencia);
        setDataReconhecimento(dataReconhecimento);
        setEmailFreelancer(emailFreelancer);
    }


    public ReconhecimentoGPDTO (ReconhecimentoGPDTO reconhecimentoGPDTO){
        setIdGrauProficiencia(reconhecimentoGPDTO.idGrauProficiencia);
        setDataReconhecimento(reconhecimentoGPDTO.dataReconhecimento);
        setEmailFreelancer(reconhecimentoGPDTO.emailFreelancer);
    }

    public ReconhecimentoGPDTO(int idGrauProficiencia, String emailFreelancer, String dataReconhecimento) {
        setIdGrauProficiencia(idGrauProficiencia);
        setEmailFreelancer(new EmailDTO(emailFreelancer));
        setDataReconhecimento(dataReconhecimento);
    }


    public void setIdGrauProficiencia(int idGrauProficiencia) {
        this.idGrauProficiencia = idGrauProficiencia;
    }

    public void setDataReconhecimento(String dataReconhecimento) {
        this.dataReconhecimento = dataReconhecimento;
    }

    public void setEmailFreelancer(EmailDTO emailFreelancer) {
        this.emailFreelancer = emailFreelancer;
    }

    public int getIdGrauProficiencia() {
        return idGrauProficiencia;
    }

    public String getDataReconhecimento() {
        return dataReconhecimento;
    }

    public EmailDTO getEmailFreelancer() {
        return emailFreelancer;
    }

}
