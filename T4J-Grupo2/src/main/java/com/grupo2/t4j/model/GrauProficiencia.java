/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.CodigoInvalidoException;
import com.grupo2.t4j.exception.CompetenciaTecnicaInexistenteException;
import com.grupo2.t4j.exception.DescricaoInvalidaException;
import com.grupo2.t4j.exception.GrauInvalidoException;

import java.io.Serializable;

/**
 *
 * @author acris
 */

public class GrauProficiencia {

    private int idGrauProficiencia;
    private String grau;
    private String designacao;
    private String codigoCompetenciaTecnica;

    public GrauProficiencia() {
    }

    public GrauProficiencia(GrauProficiencia gp) {
        setGrau(gp.grau);
        setDesignacao(gp.designacao);
    }
    
    public GrauProficiencia(String grau, String designacao) {

        setGrau(grau);
        setDesignacao(designacao);
        
    }
    public GrauProficiencia(int idGrauProficiencia, String grau, String designacao, String codigoCompetenciaTecnica){
        this.idGrauProficiencia = idGrauProficiencia;
        setGrau(grau);
        setDesignacao(designacao);
        setCodigoCompetenciaTecnica(codigoCompetenciaTecnica);
    }

    public GrauProficiencia(String grau, String designacao, String codigoCompetenciaTecnica) {
        setGrau(grau);
        setDesignacao(designacao);
        setCodigoCompetenciaTecnica(codigoCompetenciaTecnica);
    }

    public int getIdGrauProficiencia() {
        return idGrauProficiencia;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        if (grau == null || grau.trim().isEmpty()) {
            throw new GrauInvalidoException("O valor do grau é inválido.");
        }
        this.grau = grau;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        if (designacao == null || designacao.trim().isEmpty()) {
            throw new DescricaoInvalidaException("A descrição é inválida.");
        }
        this.designacao = designacao;
    }
    
    public String getCodigoCompetenciaTecnica(){
        return codigoCompetenciaTecnica;
    }

    private void setCodigoCompetenciaTecnica(String codigoCompetenciaTecnica) {
        if(codigoCompetenciaTecnica != null) {
            this.codigoCompetenciaTecnica = codigoCompetenciaTecnica;
        }
        else {
            throw new CompetenciaTecnicaInexistenteException ("A competência técnica não existe");
        }
    }

    @Override
    public String toString() {
        return "GrauProficiencia{" +
                "grau='" + grau + '\'' +
                ", designacao='" + designacao + '\'' +
                ", codigoCompetenciaTecnica='" + codigoCompetenciaTecnica + '\'' +
                '}';
    }


}
