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
    private String codigo;
    private String descricaoBreve;
    private String descricaoDetalhada;
    private GrauProficiencia grau;
<<<<<<< HEAD
    
/**
 * Construtor Competencia Tecnica Completo
 * @param codigo
 * @param descricaoBreve
 * @param descricaoDetalhada
 * @param obrigatoria 
 */
    public CompetenciaTecnica(String codigo, String descricaoBreve, String descricaoDetalhada, boolean obrigatoria) {
=======

    public CompetenciaTecnica(String codigo, String descricaoBreve, String descricaoDetalhada) {
>>>>>>> c2705aa357a950ee1e162eec970147b6b084acda
        this.codigo = codigo;
        this.descricaoBreve = descricaoBreve;
        this.descricaoDetalhada = descricaoDetalhada;
        this.grau=grau;
    }
<<<<<<< HEAD
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
     * @return the codigo
     */
=======

>>>>>>> c2705aa357a950ee1e162eec970147b6b084acda
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricaoBreve() {
        return descricaoBreve;
    }

    public void setDescricaoBreve(String descricaoBreve) {
        this.descricaoBreve = descricaoBreve;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }
    
    
    
}
