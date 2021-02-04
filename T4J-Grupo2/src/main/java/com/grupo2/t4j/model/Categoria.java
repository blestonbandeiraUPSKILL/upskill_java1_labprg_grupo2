/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.DescricaoInvalidaException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author acris
 */
public class Categoria implements Serializable{
    
    /**
     * 
     */
    private String id;
    
    /**
     * 
     */
    private static int id2=0;
    
    /**
     * 
     */
    private String descricao;
    
    /**
     * 
     */
    private AreaActividade at;
    
    /**
     * 
     */
    private List<CompetenciaTecnica> compTecnicas;
    
    /**
     *
     * @param descricao
     * @param at
     * @param compTecnicas
     */
    public Categoria (String descricao, AreaActividade at, List<CompetenciaTecnica> compTecnicas){
        this.descricao=descricao;
        this.at=at;
        this.compTecnicas=compTecnicas;
        this.id = geradorId(descricao, id2);
    }

    /**
     *
     * @param categoria
     */
    public Categoria (Categoria categoria){
        setId(id);
        setDescricao(descricao);
        setAt(at);
        setCompTecnicas(compTecnicas);
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     *
     * @param descricao
     */
    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new DescricaoInvalidaException("Deve introduzir uma descrição válida!");
        } else {
            this.descricao = descricao;
        }
    }

    /**
     *
     * @return
     */
    public AreaActividade getAt() {
        return at;
    }

    /**
     *
     * @param at
     */
    public void setAt(AreaActividade at) {
        this.at = at;
    }

    /**
     *
     * @return
     */
    public List<CompetenciaTecnica> getCompTecnicas() {
        return compTecnicas;
    }

    /**
     *
     * @param compTecnicas
     */
    public void setCompTecnicas(List<CompetenciaTecnica> compTecnicas) {
        this.compTecnicas = compTecnicas;
    }
    
    /**
     *
     * @param descricao
     * @param id2
     * @return
     */
    public String geradorId(String descricao,int id2){
        id2++;
        StringBuilder s = new StringBuilder();
        s.append(descricao);
        s.append("_");
        s.append(id2);
        return s.toString();
    }
}
