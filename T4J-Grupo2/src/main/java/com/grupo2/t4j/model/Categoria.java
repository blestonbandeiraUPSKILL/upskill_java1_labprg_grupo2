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
import java.util.Objects;

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
    private String codigoAreaActividade;
    
    /**
     * Lista de coompetencias tecnicas tipicamente necessarias para a categoria
     */
    private List<CaracterizacaoCT> caracterizacaoCTS = new ArrayList<>();
    
    /**
     *Construtor Categoria
     * @param codigoCategoria
     * @param descBreve
     * @param descDetalhada
     * @param codigoAreaActividade
     * @param caracterizacaoCTS
     */
    public Categoria (String codigoCategoria, String descBreve, String descDetalhada, String codigoAreaActividade, List<CaracterizacaoCT> caracterizacaoCTS){
        setCodigo(codigoCategoria);
        setDescBreve(descBreve);
        setDescDetalhada(descDetalhada);
        this.codigoAreaActividade = codigoAreaActividade;
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
        setCodigoAreaActividade(categoria.codigoAreaActividade);
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
    public String getAt() {
        return codigoAreaActividade;
    }

    /**
     *Atualiza a Area de Actividade
     * @param codigoAreaActividade
     */
    public void setCodigoAreaActividade(String codigoAreaActividade) {
        if(codigoAreaActividade != null) {
            this.codigoAreaActividade = codigoAreaActividade;
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

    @Override
    public String toString() {
        return "Categoria{" +
                "codigoCategoria='" + codigoCategoria + '\'' +
                ", descBreve='" + descBreve + '\'' +
                ", descDetalhada='" + descDetalhada + '\'' +
                ", codigoAreaActividade='" + codigoAreaActividade + '\'' +
                ", caracterizacaoCTS=" + caracterizacaoCTS +
                '}';
    }

    /**
     * Representação textual da categoria de tarefa
     * @return 
     */
    /*@Override
    public String toString() {
        return String.format("Código: %s; Descrição breve: %s; Descrição detalhada: %s;",
                codigoCategoria, descBreve, descDetalhada);
    }*/



    @Override
    public boolean equals(Object categoria) {
        if (this == categoria)
            return true;
        if (categoria == null || getClass() != categoria.getClass())
            return false;
        Categoria that = (Categoria) categoria;
        return Objects.equals(codigoCategoria, that.codigoCategoria)
                && Objects.equals(descBreve, that.descBreve)
                && Objects.equals(descDetalhada, that.descDetalhada)
                && Objects.equals(codigoAreaActividade, that.codigoAreaActividade)
                && Objects.equals(caracterizacaoCTS, that.caracterizacaoCTS);
    }

}
