/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

import com.grupo2.t4j.dto.DTO;
import com.grupo2.t4j.dto.ReconhecimentoGPDTO;

/**
 *
 * @author acris
 */
public class ReconhecimentoGP implements DTO {
    /**
     * O id do Grau de Proficiência a que se refere o reconhecimento
     */
    private int idGrauProficiencia;

    /**
     * A data em que foi atribuido o reconhecimento
     */
    private String dataReconhecimento;
   
    /**
     * O email do freelancer a quem se refere o reconhecimento
     */
    private Email emailFreelancer;
    
    /**
     * A designacao do Grau de Proficiencia
     */
    private String designacaoGrau;
    
    /**
     * A descricao breve da competencia tecnica
     */
    private String descBreveCompetencia;
    
    /**
     * Construtor vazio da classe ReconhecimentoGP
     */
    
    public ReconhecimentoGP(){  
    }
    
    /**
     * Construtor completo da classe ReconhecimentoGP
     * @param idGrauProficiencia
     * @param dataReconhecimento
     * @param emailFreelancer
     */
    public ReconhecimentoGP(int idGrauProficiencia,
             Email emailFreelancer, String dataReconhecimento){
        setIdGrauProficiencia(idGrauProficiencia);
        setDataReconhecimento(dataReconhecimento);
        setEmailFreelancer(emailFreelancer);
    }
     
    /**
     * Construtor da classe ReconhecimentoGP
     * @param reconhecimentoGP 
     */
    public ReconhecimentoGP (ReconhecimentoGP reconhecimentoGP){
        setIdGrauProficiencia(reconhecimentoGP.idGrauProficiencia);
        setDataReconhecimento(reconhecimentoGP.dataReconhecimento);
        setEmailFreelancer(reconhecimentoGP.emailFreelancer);
    }

    /**
     * Construtor da classe ReconhecimentoGP
     * @param idGrauProficiencia
     * @param emailFreelancer
     * @param dataReconhecimento 
     */
    public ReconhecimentoGP(int idGrauProficiencia, String emailFreelancer, String dataReconhecimento) {
        setIdGrauProficiencia(idGrauProficiencia);
        setEmailFreelancer(new Email(emailFreelancer));
        setDataReconhecimento(dataReconhecimento);
    }

    /**
     * Construtor da classe ReconhecimentoGP
     * @param dataReconhecimento
     * @param designacaoGrau
     * @param descBreve 
     */
    public ReconhecimentoGP(String dataReconhecimento, String designacaoGrau, String descBreve) {
        setDataReconhecimento(dataReconhecimento);
        this.designacaoGrau = designacaoGrau;
        this.descBreveCompetencia = descBreve;
    }

    /**
     * Devolve o id do Grau de Proficiência a que se refere o reconhecimento
     * @return idGrauProficiencia
     */
    public int getIdGrauProficiencia() {
        return idGrauProficiencia;
    }

    /**
     * Atualiza o id do Grau de Proficiencia a que se refere o reconhecimento
     * @param idGrauProficiencia 
     */
    public void setIdGrauProficiencia(int idGrauProficiencia) {
        this.idGrauProficiencia = idGrauProficiencia;
    }

    /**
     * Devolve a data em que foi feito o reconhecimento
     * @return dataReconhecimento
     */
    public String getDataReconhecimento() {
        return dataReconhecimento;
    }

    /**
     * Atualiza a data em que foi feito o reconhecimento
     * @param dataReconhecimento 
     */
    public void setDataReconhecimento(String dataReconhecimento) {
        this.dataReconhecimento = dataReconhecimento;
    }

    /**
     * Devolve o email do freelancer a que se refere o reconhecimento
     * @return 
     */
    public Email getEmailFreelancer() {
        return emailFreelancer;
    }

    /**
     * Atualiza o email do freelancer a que se refere o reconhecimento
     * @param emailFreelancer 
     */
    public void setEmailFreelancer(Email emailFreelancer) {
        this.emailFreelancer = emailFreelancer;
    }

    /**
     * Devolve a designacao do grau de proficiencia
     * @return 
     */
    public String getDesignacaoGrau() {
        return designacaoGrau;
    }

    /**
     * Atualiza a designacao do grau de proficiencia
     * @param designacaoGrau 
     */
    public void setDesignacaoGrau(String designacaoGrau) {
        this.designacaoGrau = designacaoGrau;
    }

    /**
     * Devolve a descricao breve da competencia tecnica
     * @return 
     */
    public String getDescBreveCompetencia() {
        return descBreveCompetencia;
    }

    /**
     * Atualiza a descricao breve da competencia tecnica
     * @param descBreveCompetencia 
     */
    public void setDescBreveCompetencia(String descBreveCompetencia) {
        this.descBreveCompetencia = descBreveCompetencia;
    }

    /**
     * Representacao textual do reconhecimento de uma competencia tecnica
     * @return 
     */
    @Override
    public String toString(){
        return String.format("Competência Técnica: %s%n" +
                "Grau: %s%n" +
                "Data Reconhecimento: %s", descBreveCompetencia, designacaoGrau, dataReconhecimento);
    }

    @Override
    public Object toDTO() {
        return new ReconhecimentoGPDTO(idGrauProficiencia, emailFreelancer,
                dataReconhecimento, designacaoGrau, descBreveCompetencia);
    }
}
