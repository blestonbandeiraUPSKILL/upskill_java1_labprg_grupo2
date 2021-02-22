/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

/**
 *
 * @author acris
 */
public class ReconhecimentoGP {
    /**
     * O id do Grau de Proficiência a que se refere o reconhecimento
     */
    private String idGrauProficiencia;
    
    private String idCompetenciaTecnica;
    /**
     * A data em que foi atribuido o reconhecimento
     */
    private Data dataReconhecimento;
   
    /**
     * O email do freelancer a quem se refere o reconhecimento
     */
    private Email emailFreelancer;
    
    /**
     * Construtor vazio da classe ReconhecimentoGP
     */
    
    public ReconhecimentoGP(){  
    }
    
    public ReconhecimentoGP(String idGrauProficiencia, Data dataReconhecimento, 
             Email emailFreelancer, String idCompetenciaTecnica){
        setIdGrauProficiencia(idGrauProficiencia);
        setDataReconhecimento(dataReconhecimento);
        setEmailFreelancer(emailFreelancer);
        setIdCompetenciaTecnica(idCompetenciaTecnica);
    }
    
    /**
     * Construtor completo da classe ReconhecimentoGO
     * @param idRGrauProficiencia
     * @param dataReconhecimento
     * @param codigoCompetenciaTecnica
     * @param emailFreelancer 
     */
    public ReconhecimentoGP (String idGrauProficiencia, Data dataReconhecimento, 
             Email emailFreelancer){
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
     * Devolve o id do Grau de Proficiência a que se refere o reconhecimento
     * @return idGrauProficiencia
     */
    public String getIdGrauProficiencia() {
        return idGrauProficiencia;
    }

    /**
     * Atualiza o id do Grau de Proficiencia a que se refere o reconhecimento
     * @param idGrauProficiencia 
     */
    public void setIdGrauProficiencia(String idGrauProficiencia) {
        this.idGrauProficiencia = idGrauProficiencia;
    }

    /**
     * Devolve a data em que foi feito o reconhecimento
     * @return dataReconhecimento
     */
    public Data getDataReconhecimento() {
        return dataReconhecimento;
    }

    /**
     * Atualiza a data em que foi feito o reconhecimento
     * @param dataReconhecimento 
     */
    public void setDataReconhecimento(Data dataReconhecimento) {
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

    private void setIdCompetenciaTecnica(String idCompetenciaTecnica) {
        this.idCompetenciaTecnica=idCompetenciaTecnica;
    }
    
    public String getIdCompetenciaTecnica(){
        return idCompetenciaTecnica;
    }
    
    /**
     * Representação textual da classe de reconhecimento de grau de proficiência em 
     * formato de exibição
     * @return 
     */
    @Override
    public String toString(){
        return String.format("ID GP: %-12s |ID CP: %-12s |Data Reconhecimento: %-12s "
                + "|Email Freelancer: %-20s",idGrauProficiencia, idCompetenciaTecnica,
                dataReconhecimento,emailFreelancer);
    }    
}
