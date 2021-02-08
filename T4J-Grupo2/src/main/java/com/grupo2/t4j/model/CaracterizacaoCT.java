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
public class CaracterizacaoCT {

    /**
     * O grau de proficiencia da competencia tecnica
     */
    private GrauProficiencia gp;
    /**
     * A obrigatoriedade da competencia tecnica
     */
    private Obrigatoriedade obrigatoriedade;
    
    /**
     * A competencia tecnica
     */
    private CompetenciaTecnica competenciaTecnica;

    /**
     * Construtor completo para caracterizacao de uma competencia tecnica
     * @param gp
     * @param obrigatoriedade
     * @param competenciaTecnica 
     */
    public CaracterizacaoCT(GrauProficiencia gp, Obrigatoriedade obrigatoriedade,
                            CompetenciaTecnica competenciaTecnica) {
        this.gp = gp;
        this.obrigatoriedade = obrigatoriedade;
        this.competenciaTecnica = competenciaTecnica;
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
                + "Carácter: %s", competenciaTecnica.getDescricaoBreve(), gp.toString(), obrigatoriedade.toString());
        
    }

    public CompetenciaTecnica getCompetenciaTecnica() {
        return this.competenciaTecnica;
    }
}
