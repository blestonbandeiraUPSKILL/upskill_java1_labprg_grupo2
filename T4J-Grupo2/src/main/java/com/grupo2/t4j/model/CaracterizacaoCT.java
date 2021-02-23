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
    
    private int idCaracterizacao;

    private String codigoCategoria;
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
    
    
    
    public CaracterizacaoCT (){
    }
    
    public CaracterizacaoCT(CaracterizacaoCT cct){
        setCodigoGP(cct.codigoGP);
        setObrigatoriedade(cct.obrigatoriedade);
    }

    /**
     * Construtor completo para caracterizacao de uma competencia tecnica
     * @param codigoGP
     * @param obrigatoriedade
     * @param codigoCategoria
     */
    public CaracterizacaoCT(String codigoCategoria, String codigoGP, 
            Obrigatoriedade obrigatoriedade) {
        this.codigoCategoria = codigoCategoria;
        this.codigoGP = codigoGP;
        this.obrigatoriedade = obrigatoriedade;
    }
    
    public CaracterizacaoCT(int idCaracterizacao, String codigoCategoria, 
            String codigoGP, Obrigatoriedade obrigatoriedade){
        this.idCaracterizacao = idCaracterizacao;
        setCodigoCategoria(codigoCategoria);
        setCodigoGP(codigoGP);
        setObrigatoriedade(obrigatoriedade);
    }

    public String getCodigoGP() {
        return codigoGP;
    }

    public Obrigatoriedade getObrigatoriedade() {
        return obrigatoriedade;
    }

    public String getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(String codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public void setIdCaracterizacao(int idCaracterizacao) {
        this.idCaracterizacao = idCaracterizacao;
    }

    public void setCodigoGP(String codigoGP) {
        this.codigoGP = codigoGP;
    }

    public void setCodigoCompetenciaTecnica(String codigoCompetenciaTecnica) {
        this.codigoCompetenciaTecnica = codigoCompetenciaTecnica;
    }
    
    

    
    /**
     * Actualiza a obrigatoriedade da competencia tecnica
     * @param obrigatoriedade the obrigatoriedade to set
     */
    public void setObrigatoriedade(Obrigatoriedade obrigatoriedade) {
        this.obrigatoriedade = obrigatoriedade;
    }
    
    public String getCodigoCompetenciaTecnica() {
        return this.codigoCompetenciaTecnica;
    }

    
    
    /**
     * Representacao textual da competencia tecnica caracterizada em formato de exibição
     * @return 
     */
    @Override
    public String toString() {
        return String.format("Competência Técnica: %-30s |Grau de Proficiencia: %-20s "
                + "|Carácter: %-15s", codigoCompetenciaTecnica, codigoGP, obrigatoriedade.toString());

    }
    
    /**
     * Representacao textual da competencia tecnica caracterizada
     * @return 
     */
     public String toStringCompleto() {
        return String.format("Competência Tecnica: %s; Grau de Proficiencia: %s; "
                + "Carácter: %s", codigoCompetenciaTecnica, codigoGP, obrigatoriedade.toString());

    }   
}
