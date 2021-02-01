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

    public CompetenciaTecnica(String codigo, String descricaoBreve, String descricaoDetalhada) {
        this.codigo = codigo;
        this.descricaoBreve = descricaoBreve;
        this.descricaoDetalhada = descricaoDetalhada;
        this.grau=grau;
    }

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
