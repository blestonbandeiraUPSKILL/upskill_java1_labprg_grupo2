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
public class GrauProficiencia {
    private int grau;
    private String designacao;
/**
 * Construtor completo
 * @param grau
 * @param designacao 
 */
    public GrauProficiencia(int grau, String designacao) {
        setGrau(grau);
        setDesignacao(designacao);
    }

    /**
     * @return the grau
     */
    public int getGrau() {
        return grau;
    }

    /**
     * @param grau the grau to set
     */
    public void setGrau(int grau) {
        this.grau = grau;
    }

    /**
     * @return the designacao
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * @param designacao the designacao to set
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    
    
}
