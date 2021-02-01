/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import java.util.List;

/**
 *
 * @author acris
 */
public class Categoria {
    
    private String id;
    private String descricao;
    private AreaActividade at;
    private List<CompetenciaTecnica> compTecnicas;
    
    public Categoria (String id, String descricao, AreaActividade at, List<CompetenciaTecnica> compTecnicas){
        setId(id);
        this.descricao=descricao;
        this.at=at;
        this.compTecnicas=compTecnicas;
    }
    public Categoria (Categoria categoria){
        setId(id);
        setDescricao(descricao);
        setAt(at);
        setCompTecnicas(compTecnicas);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public AreaActividade getAt() {
        return at;
    }

    public void setAt(AreaActividade at) {
        this.at = at;
    }

    public List<CompetenciaTecnica> getCompTecnicas() {
        return compTecnicas;
    }

    public void setCompTecnicas(List<CompetenciaTecnica> compTecnicas) {
        this.compTecnicas = compTecnicas;
    }
    
}
