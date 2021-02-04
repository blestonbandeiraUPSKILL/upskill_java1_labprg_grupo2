/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.DescricaoInvalidaException;
import java.util.List;

/**
 *
 * @author acris
 */
public class Categoria {
    
    private String id;
    private static int id2=0;
    private String descricao;
    private AreaActividade at;
    private List<CompetenciaTecnica> compTecnicas;
    
    public Categoria (String descricao, AreaActividade at, List<CompetenciaTecnica> compTecnicas){
        this.descricao=descricao;
        this.at=at;
        this.compTecnicas=compTecnicas;
        this.id = geradorId(descricao, id2);
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
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new DescricaoInvalidaException("Deve introduzir uma descrição válida!");
        } else {
            this.descricao = descricao;
        }
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
    
    public String geradorId(String descricao,int id2){
        id2++;
        StringBuilder s = new StringBuilder();
        s.append(descricao);
        s.append("_");
        s.append(id2);
        return s.toString();
    }
}
