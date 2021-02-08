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

    private GrauProficiencia gp;
    private Obrigatoriedade obrigatoriedade;
    private CompetenciaTecnica competenciaTecnica;

    public CaracterizacaoCT(GrauProficiencia gp, Obrigatoriedade obrigatoriedade,
                            CompetenciaTecnica competenciaTecnica) {
        this.gp = gp;
        this.obrigatoriedade = obrigatoriedade;
        this.competenciaTecnica = competenciaTecnica;
    }

    /**
     * @param obrigatoriedade the obrigatoria to set
     */
    public void setObrigatoriedade(Obrigatoriedade obrigatoriedade) {
        this.obrigatoriedade = obrigatoriedade;
    }

    @Override
    public String toString() {
        return "Grau de Proficiência: " + gp +
                ", Obrigatoriedade: " + obrigatoriedade +
                ", Competência Técnica: " + competenciaTecnica +
                '}';
    }

    public CompetenciaTecnica getCompetenciaTecnica() {
        return this.competenciaTecnica;
    }
}
