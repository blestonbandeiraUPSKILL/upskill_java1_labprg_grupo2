/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.model.Email;

/**
 *
 * @author acris
 */
public class ReconhecimentoGP {
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

    public ReconhecimentoGP(int idGrauProficiencia, String emailFreelancer, String dataReconhecimento) {
        setIdGrauProficiencia(idGrauProficiencia);
        setEmailFreelancer(new Email(emailFreelancer));
        setDataReconhecimento(dataReconhecimento);
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


    @Override
    public String toString(){
        return String.format("ID GP: %-10d |Data Reconhecimento: %-12s "
                + "|Email Freelancer: %-20s",idGrauProficiencia,
                dataReconhecimento, emailFreelancer.getEmailText());
    }    
}
