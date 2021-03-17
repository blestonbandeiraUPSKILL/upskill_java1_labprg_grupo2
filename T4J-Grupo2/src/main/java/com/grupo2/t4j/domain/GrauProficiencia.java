/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

import com.grupo2.t4j.dto.DTO;
import com.grupo2.t4j.dto.GrauProficienciaDTO;
import com.grupo2.t4j.exception.CompetenciaTecnicaInexistenteException;
import com.grupo2.t4j.exception.DescricaoInvalidaException;
import com.grupo2.t4j.exception.GrauInvalidoException;

/**
 *
 * @author acris
 */

public class GrauProficiencia implements DTO {

    /**
     * O id do Grau de Proficiencia
     */
    private int idGrauProficiencia;
    
    /**
     * O grau de proficiencia
     */
    private int grau;
    
    /**
     * A designacao do grau de competencia
     */
    private String designacao;
    
    /**
     * O codigo da competencia tecnica
     */
    private String codigoCompetenciaTecnica;

    public GrauProficiencia() {
    }

    /**
     * Contrutor do Grau de Proficiencia
     * @param gp 
     */
    public GrauProficiencia(GrauProficiencia gp) {
        setGrau(gp.grau);
        setDesignacao(gp.designacao);
    }
    
    /**
     * Contrutor do Grau de Proficiencia
     * @param grau
     * @param designacao 
     */
    public GrauProficiencia(int grau, String designacao) {
        setGrau(grau);
        setDesignacao(designacao);
        
    }
    
    /**
     * Contrutor do Grau de Proficiencia
     * @param idGrauProficiencia
     * @param grau
     * @param designacao
     * @param codigoCompetenciaTecnica 
     */
    public GrauProficiencia(int idGrauProficiencia, int grau, String designacao, String codigoCompetenciaTecnica){
        this.idGrauProficiencia = idGrauProficiencia;
        setGrau(grau);
        setDesignacao(designacao);
        setCodigoCompetenciaTecnica(codigoCompetenciaTecnica);
    }

    /**
     * Contrutor do Grau de Proficiencia
     * @param grau
     * @param designacao
     * @param codigoCompetenciaTecnica 
     */
    public GrauProficiencia(int grau, String designacao, String codigoCompetenciaTecnica) {
        setGrau(grau);
        setDesignacao(designacao);
        setCodigoCompetenciaTecnica(codigoCompetenciaTecnica);
    }

    /**
     * Devolve o id do grau de proficiencia
     * @return 
     */
    public int getIdGrauProficiencia() {
        return idGrauProficiencia;
    }

    /**
     * Devolve o grau de proficiencia
     * @return 
     */
    public int getGrau() {
        return grau;
    }

    /**
     * Atualiza o grau de proficiencia
     * @param grau 
     */
    public void setGrau(int grau) {
        if (grau < 0) {
            throw new GrauInvalidoException("O valor do grau é inválido.");
        }
        this.grau = grau;
    }

    /**
     * Devolve a designacao do grau de proficiencia
     * @return 
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Atualiza a designacao do grau de proficiencia
     * @param designacao 
     */
    public void setDesignacao(String designacao) {
        if (designacao == null || designacao.trim().isEmpty()) {
            throw new DescricaoInvalidaException("A descrição é inválida.");
        }
        this.designacao = designacao;
    }
    
    /**
     * Devolve o codigo de uma competencia tecnica
     * @return 
     */
    public String getCodigoCompetenciaTecnica(){
        return codigoCompetenciaTecnica;
    }
    
    /**
     * Atualiza o codigo da competencia tecnica
     * @param codigoCompetenciaTecnica 
     */
    private void setCodigoCompetenciaTecnica(String codigoCompetenciaTecnica) {
        if(codigoCompetenciaTecnica != null) {
            this.codigoCompetenciaTecnica = codigoCompetenciaTecnica;
        }
        else {
            throw new CompetenciaTecnicaInexistenteException ("A competência técnica não existe");
        }
    }

    /**
     * Representação textual do Grau de Proficiência no formato de exibição
     * @return 
     */
    @Override
    public String toString(){
        return String.format("%s - %s", designacao, grau);
    }
    
    /**
     * Representação textual completa do Grau de Proficiência no formato de exibição
     * @return 
     */
    public String toStringCompleta() {
        return "GrauProficiencia{" +
                "grau='" + grau + '\'' +
                ", designacao='" + designacao + '\'' +
                ", codigoCompetenciaTecnica='" + codigoCompetenciaTecnica + '\'' +
                '}';
    }


    @Override
    public Object toDTO() {
        return new GrauProficienciaDTO();
    }
}
