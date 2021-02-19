/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.CompetenciaTecnicaInexistenteException;
import java.io.Serializable;

/**
 *
 * @author acris
 */

/*public enum GrauProficiencia implements Serializable { INICIANTE,
                              BOM, 
                              EXPERIENTE, 
                              ESPECIALISTA}; */
public class GrauProficiencia {
    private int grau;
    private String designacao;
    private CompetenciaTecnica competenciaTecnica;
    

    public GrauProficiencia(GrauProficiencia gp) {
        setGrau(grau);
        setDesignacao(designacao);
    }
    
    public GrauProficiencia(int grau, String designacao) {
        setGrau(grau);
        setDesignacao(designacao);
        
    }
    public GrauProficiencia(int grau, String designacao, CompetenciaTecnica competenciaTecnica){
        setGrau(grau);
        setDesignacao(designacao);
        setCompetenciaTecnica(competenciaTecnica);
    }

    public int getGrau() {
        return grau;
    }

    public void setGrau(int grau) {
        this.grau = grau;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
    
    public CompetenciaTecnica getCompetenciaTecnica(){
        return competenciaTecnica;
    }

    private void setCompetenciaTecnica(CompetenciaTecnica competenciaTecnica) {
        if(competenciaTecnica != null) {
            this.competenciaTecnica = competenciaTecnica;
        }
        else {
            throw new CompetenciaTecnicaInexistenteException ("A competência técnica não existe");
        }
    }

    @Override
    public String toString() {
        return "GrauProficiencia{" +
                "grau=" + grau +
                ", designacao='" + designacao + '\'' +
                ", competenciaTecnica=" + competenciaTecnica +
                '}';
    }
}
