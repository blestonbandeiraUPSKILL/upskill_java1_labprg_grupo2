/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.AreaActividadeInexistenteException;
import com.grupo2.t4j.exception.CodigoInvalidoException;
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
    private String codigoCategoria;

    /**
     * Descricao breve da categoria
     */
    private String descBreve;

    /**
     * Descrição detalhada da categoria
     */
    private String descDetalhada;

    /**
     * Area de actividade a que se refere a categoria
     */
    private AreaActividade at;
    
    /**
     * Lista de coompetencias tecnicas tipicamente necessarias para a categoria
     */
    private List<CaracterizacaoCT> caracterizacaoCTS = new ArrayList<>();
    
    /**
     *Construtor Categoria
     * @param descBreve
     * @param descDetalhada
     * @param at
     * @param caracterizacaoCTS
     */
    public Categoria (String codigoCategoria, String descBreve, String descDetalhada, AreaActividade at, List<CaracterizacaoCT> caracterizacaoCTS){
        setCodigo(codigoCategoria);
        setDescBreve(descBreve);
        setDescDetalhada(descDetalhada);
        this.at = at;
        setCompTecnicasCaracter(caracterizacaoCTS);
    }

    /**
     *Construtor Categoria
     * @param categoria
     */
    public Categoria (Categoria categoria){
        setCodigo(categoria.codigoCategoria);
        setDescBreve(categoria.descBreve);
        setDescDetalhada(categoria.descDetalhada);
        setAt(categoria.at);
        setCompTecnicasCaracter(categoria.caracterizacaoCTS);
    }

    /**
     *Devolve o código da categoria
     *
     * @return
     */
    public String getCodigoCategoria() {
        return codigoCategoria;
    }

    /**
     *Atualiza o código da categoria
     * @param codigoCategoria
     */
    public void setCodigo(String codigoCategoria) {
        if (codigoCategoria == null || codigoCategoria.trim().isEmpty()) {
            throw new CodigoInvalidoException("Deve introduzir um código válido!");
        } else {
            this.codigoCategoria = codigoCategoria;
        } this.codigoCategoria = codigoCategoria;
    }

    /**
     *
     * Devolve a descrição detalhada da categoria
     *
     * @return descDetalhada;
     */
    public String getDescDetalhada() {
        return descDetalhada;
    }

    /**
     *Retorna a descricao da categoria
     * @return
     */
    public String getDescBreve() {
        return descBreve;
    }

    /**
     *Atualiza a descricao breve da categoria
     * @param descBreve
     */
    public void setDescBreve(String descBreve) {
        if (descBreve == null || descBreve.trim().isEmpty()) {
            throw new DescricaoInvalidaException("Deve introduzir uma descrição breve válida!");
        } else {
            this.descBreve = descBreve;
        }
    }
    /**
     * Actualiza a descricao detalhada da categoria
     * @param descDetalhada 
     */
    public void setDescDetalhada(String descDetalhada) {
        if(descDetalhada == null || descDetalhada.trim().isEmpty()) {
            throw new DescricaoInvalidaException("Deve introduzir uma descrição detalhada válida!");
        }
        else {
            this.descDetalhada = descDetalhada;
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
     * @param areaActividade
     */
    public void setAt(AreaActividade areaActividade) {
        if(areaActividade != null) {
            this.at = areaActividade;
        }
        else {
            throw new AreaActividadeInexistenteException ("A área de actividade não existe");
        }
    }

    /**
     *Devolve uma lista de competencias tencicas necessarias para a categoria com a respetiva caracterizacao
     * @return
     */
    public List<CaracterizacaoCT> getCompTecnicasCaracter() {
        return new ArrayList<CaracterizacaoCT>(caracterizacaoCTS);
    }

    /**
     *Atualiza a lista de competencias tecnicas caracterizadas
     * @param caracterizacaoCTS
     */
    public void setCompTecnicasCaracter(List<CaracterizacaoCT> caracterizacaoCTS) {
        this.caracterizacaoCTS = caracterizacaoCTS;
    }
    

    /**
     * Representação textual da lista de competencias tecnicas de uma categoria
     * @return 
     */
    public String toStringCompTec (){
        StringBuilder s = new StringBuilder();
        for (CaracterizacaoCT cct : this.caracterizacaoCTS) {
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
    public String toString() {
        return String.format("Código: %s; Descrição breve: %s; Descrição detalhada: %s;",
                codigoCategoria, descBreve, descDetalhada);
    }
}
