/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import java.io.Serializable;

/**
 *
 * @author acris
 */
public class CaracterizacaoCT implements Serializable{

    private String codigoCCT;
    /**
     * O grau de proficiencia da competencia tecnica
     */
    private String codigoGP;
    /**
     * A obrigatoriedade da competencia tecnica
     */
    private Obrigatoriedade obrigatoriedade;
    
    /**
     * A competencia tecnica
     */
    private String codigoCompetenciaTecnica;

    /**
     * Construtor completo para caracterizacao de uma competencia tecnica
     * @param codigoGP
     * @param obrigatoriedade
     * @param codigoCompetenciaTecnica
     */
    public CaracterizacaoCT(String codigoCCT, String codigoGP, Obrigatoriedade obrigatoriedade,
                            String codigoCompetenciaTecnica) {
        this.codigoCCT = codigoCCT;
        this.codigoGP = codigoGP;
        this.obrigatoriedade = obrigatoriedade;
        this.codigoCompetenciaTecnica = codigoCompetenciaTecnica;
    }

    public String getCodigoGP() {
        return codigoGP;
    }

    public Obrigatoriedade getObrigatoriedade() {
        return obrigatoriedade;
    }

    
    /**
     * Actualiza a obrigatoriedade da competencia tecnica
     * @param obrigatoriedade the obrigatoriedade to set
     */
    public void setObrigatoriedade(Obrigatoriedade obrigatoriedade) {
        this.obrigatoriedade = obrigatoriedade;
    }

    /**
     * Representacao textual da competencia tecnica caracterizada
     * @return 
     */
    @Override
    public String toString() {
        return String.format("Competência Tecnica: %s; Grau de Proficiencia: %s; "
                + "Carácter: %s", codigoCompetenciaTecnica, codigoGP, obrigatoriedade.toString());

    }

    public String getCodigoCompetenciaTecnica() {
        return this.codigoCompetenciaTecnica;
    }

    public String getCodigoCCT() {
        return codigoCCT;
    }
}
