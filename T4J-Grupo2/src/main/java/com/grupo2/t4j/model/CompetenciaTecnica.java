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
public class CompetenciaTecnica {
    
   /**
    * Atributos Competencia Tecnica
    */
    private String codigo;
    private String descricaoBreve;
    private String descricaoDetalhada;
    private GrauProficiencia grau;
    private boolean obrigatoria;
/**
 * Construtor Competencia Tecnica Completo
 * @param codigo
 * @param descricaoBreve
 * @param descricaoDetalhada
 * @param obrigatoria 
 */
    public CompetenciaTecnica(String codigo, String descricaoBreve, String descricaoDetalhada, boolean obrigatoria) {
        this.codigo = codigo;
        this.descricaoBreve = descricaoBreve;
        this.descricaoDetalhada = descricaoDetalhada;
        this.grau=grau;
        this.obrigatoria=obrigatoria;
        
    }
/**
 * Construtor Classe Competencia Tecnica
 * @param compTec 
 */
    public CompetenciaTecnica(CompetenciaTecnica compTec){
        setCodigo(codigo);
        setDescricaoBreve(descricaoBreve);
        setDescricaoDetalhada(descricaoDetalhada);
        setGrau(grau);
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

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descricaoBreve
     */
    public String getDescricaoBreve() {
        return descricaoBreve;
    }

    /**
     * @param descricaoBreve the descricaoBreve to set
     */
    public void setDescricaoBreve(String descricaoBreve) {
        this.descricaoBreve = descricaoBreve;
    }

    /**
     * @return the descricaoDetalhada
     */
    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    /**
     * @param descricaoDetalhada the descricaoDetalhada to set
     */
    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    /**
     * @return the grau
     */
    public GrauProficiencia getGrau() {
        return grau;
    }

    /**
     * @param grau the grau to set
     */
    public void setGrau(GrauProficiencia grau) {
        this.grau = grau;
    }
    
    
    
}
