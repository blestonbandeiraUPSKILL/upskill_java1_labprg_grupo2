/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

import java.io.Serializable;

/**
 *
 * @author acris
 */
public class CaracterizacaoCT implements Serializable{
    
    private int idCaracterizacao;

    private String codigoCategoria;
    private String descBreveCompetencia;
    private String designacaoGrau;
    /**
     * O grau de proficiencia da competencia tecnica
     */
    private int codigoGP;
    /**
     * A obrigatoriedade da competencia tecnica
     */
    private Obrigatoriedade obrigatoriedade;
    
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
    public CaracterizacaoCT(String codigoCategoria, int codigoGP,
            Obrigatoriedade obrigatoriedade) {
        this.codigoCategoria = codigoCategoria;
        this.codigoGP = codigoGP;
        this.obrigatoriedade = obrigatoriedade;
    }
    
    public CaracterizacaoCT(int idCaracterizacao, String codigoCategoria, 
            int codigoGP, Obrigatoriedade obrigatoriedade){
        this.idCaracterizacao = idCaracterizacao;
        setCodigoCategoria(codigoCategoria);
        setCodigoGP(codigoGP);
        setObrigatoriedade(obrigatoriedade);
    }

    public CaracterizacaoCT(int idCaracterCT, String obrigatoria, int grauProfMinimo, String codigoCategoria) {
        setIdCaracterizacao(idCaracterCT);
        setObrigatoriedade(Obrigatoriedade.valueOf(obrigatoria.toUpperCase()));
        setCodigoGP(grauProfMinimo);
        setCodigoCategoria(codigoCategoria);
    }

    public CaracterizacaoCT(String obrigatoria, String designacaoGrau, String descBreveCompetencia) {
        setObrigatoriedade(Obrigatoriedade.valueOf(obrigatoria.toUpperCase()));
        this.designacaoGrau = designacaoGrau;
        this.descBreveCompetencia = descBreveCompetencia;
    }

    public int getCodigoGP() {
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

    public void setCodigoGP(int codigoGP) {
        this.codigoGP = codigoGP;
    }

    /**
     * Actualiza a obrigatoriedade da competencia tecnica
     * @param obrigatoriedade the obrigatoriedade to set
     */
    public void setObrigatoriedade(Obrigatoriedade obrigatoriedade) {
        this.obrigatoriedade = obrigatoriedade;
    }
    
    /**
     * Representacao textual da competencia tecnica caracterizada em formato de exibição
     * @return 
     */
    @Override
    public String toString() {
        return String.format("Competencia Téncina: %-30s %nGrau de Proficiencia: %-20s "
                + "%nCarácter: %-15s", descBreveCompetencia, designacaoGrau, obrigatoriedade.toString());

    }
    
    /**
     * Representacao textual da competencia tecnica caracterizada
     * @return 
     */
     public String toStringCompleto() {
        return String.format("Categoria: %s; Grau de Proficiencia: %s; "
                + "Carácter: %s", codigoCategoria, codigoGP, obrigatoriedade.toString());

    }   
}
