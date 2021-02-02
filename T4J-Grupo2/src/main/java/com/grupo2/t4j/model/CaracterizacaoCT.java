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
    
    private CompetenciaTecnica ct;
    private GrauProficiencia gp;
    private boolean obrigatoria;


    public CaracterizacaoCT(CompetenciaTecnica ct, GrauProficiencia gp, boolean obrigatoria) {
        this.ct=ct;
        this.gp=gp;
        this.obrigatoria = obrigatoria;

    }

    /**
     * @return the obrigatoria
     */
    public boolean isObrigatoria() {
        return obrigatoria;
    }

    /**
     * @param obrigatoria the obrigatoria to set
     */
    public void setObrigatoria(boolean obrigatoria) {
        this.obrigatoria = obrigatoria;
    }
    
    
}
