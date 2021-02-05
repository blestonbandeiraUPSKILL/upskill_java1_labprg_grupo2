/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.AreaActividadeInexistenteException;
import com.grupo2.t4j.exception.DescricaoInvalidaException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
public class Categoria implements Serializable{
    
    /**
     * O identificador da Categoria
     */
    private String id;
    
    /**
     * Parte do gerador de id
     */
    private int id2=0;
    
    /**
     * Descricao da categor
     */
    private String descricao;
    
    /**
     * Area de actividade a que se refere a categoria
     */
    private AreaActividade at;
    
    /**
     * Lista de coompetencias tecnicas tipicamente necessarias para a categoria
     */
    private List<CaracterizacaoCT> compTecnicasCaracter=new ArrayList<>();
    
    /**
     *Construtor Categoria
     * @param descricao
     * @param at
     * @param compTecnicas
     */
    public Categoria (String descricao, AreaActividade at, List<CaracterizacaoCT> compTecnicasCaracter){
        setDescricao(descricao);
        setAt(at);
        setCompTecnicasCaracter(compTecnicasCaracter);
        this.id = geradorId(descricao, id2);
    }

    /**
     *Construtor Categoria
     * @param categoria
     */
    public Categoria (Categoria categoria){
        setId(id);
        setDescricao(descricao);
        setAt(at);
        setCompTecnicasCaracter(compTecnicasCaracter);
    }

    /**
     *Devolve o Id da categoria
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *Atualiza o id da categoria
     * @param id
     */
    public void setId(String id) {
        
        this.id = id;
    }

    /**
     *Retorna a descricao da categoria
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     *Atualiza a descricao da categoria
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
     *Retorna a Area de Actividade a que se refere a categoria
     * @return
     */
    public AreaActividade getAt() {
        return at;
    }

    /**
     *Atualiza a Area de Actividade
     * @param at
     */
    public void setAt(AreaActividade areaActividade) {
        if (areaActividade != null) {
            this.at = areaActividade;
        }else {
            throw new AreaActividadeInexistenteException ("A área de actividade não existe");
        }
    }

    /**
     *Devolve uma lista de competencias tencicas necessarias para a categoria com a respetiva caracterizacao
     * @return
     */
    public List<CaracterizacaoCT> getCompTecnicasCaracter() {
        return new ArrayList<CaracterizacaoCT>(compTecnicasCaracter);
    }

    /**
     *Atualiza a lista de competencias tecnicas caracterizadas
     * @param compTecnicas
     */
    public void setCompTecnicasCaracter(List<CaracterizacaoCT> compTecnicasCaracter) {
        this.compTecnicasCaracter = compTecnicasCaracter;
    }
    
    /**
     *Gera um id baseado na descricao da categoria
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
    /**
     * Representação textual da lista de competencias tecnicas de uma categoria
     * @return 
     */
    public String toStringCompTec (){
        StringBuilder s = new StringBuilder();
        for (CaracterizacaoCT cct : this.compTecnicasCaracter) {
            s.append("->");
            s.append(cct.toString());
            s.append("\n");
        }
        return s.toString();
    }
    
    /**
     * Representação textual da categoria de tarefa
     * @return 
     */
    @Override
    public String toString(){
        return String.format("%s; %s; %s", descricao, at.getDescBreve(), toStringCompTec());
    }
}
