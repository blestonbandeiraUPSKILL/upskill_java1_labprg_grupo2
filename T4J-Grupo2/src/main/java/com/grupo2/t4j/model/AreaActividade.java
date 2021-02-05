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
import com.grupo2.t4j.exception.*;
import java.io.Serializable;

/**
 *
 * @author Geral
 */
public class AreaActividade implements Serializable{
    /**
     * Separador para exportar ficheiro
     */
    private static final char SEPARADOR = ';';

/**
 * O código único de cada Área de Actividade.
 */
    private String codigo;
    
/**
 * A descrição breve da Área de Actividade.
 */
    private String descBreve;

/**
 * A descrição detalhada da Área de Actividade.
 */
   private String descDetalhada;

    /**
     * Construtor vazio da classe AreaActividade
     */
    public AreaActividade() {
    }
    
    /**
     * Construtor completo da classe AreaActividade
     * @param codigo o código único de cada Área de Actividade.
     * @param descBreve a descrição breve da Área de Actividade.
     * @param descDetalhada a descrição detalhada da Área de Actividade.
     */
    public AreaActividade(String codigo, String descBreve, String descDetalhada){
        setCodigo(codigo);
        setDescBreve(descBreve);
        setDescDetalhada(descDetalhada);
    }
    
    /**
     * Construtor da classe AreaActividade
     * @param areaActividade é do tipo da classe AreaActividade
     */
    public AreaActividade(AreaActividade areaActividade){
        setCodigo(areaActividade.codigo);
        setDescBreve(areaActividade.descBreve);
        setDescDetalhada(areaActividade.descDetalhada);
    }

    /**
     * Verifica a validade do parâmetro recebido e regista o código da Área de Actividade
     * @param codigo o código único de cada Área de Actividade.
     */
    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new AreaActividadeDuplicadaException("Código de Área de Actividade "
                    + "já existe!");
        }
        this.codigo = codigo;
    }
    
    /**
     * Verifica a validade do parâmetro recebido e regista a descrição breve da Área de Actividade
     * @param descBreve a descrição breve da Área de Actividade
     */
    public void setDescBreve(String descBreve) {
        if (descBreve == null || descBreve.trim().isEmpty() || descBreve.length() < 5) {
            throw new IllegalArgumentException("Descrição breve é obrigatória!");
        }
        this.descBreve = descBreve;
    }
    
    /**
     * Verifica a validade do parâmetro recebido e regista a descrição breve da Área de Actividade
     * @param descDetalhada a descrição detalhada da Área de Actividade
     */
    public void setDescDetalhada(String descDetalhada) {
        if (descDetalhada == null || descDetalhada.trim().isEmpty() || descDetalhada.length() < 10) {
            throw new IllegalArgumentException("Descrição detalhada é obrigatória!");
        }
        this.descDetalhada = descDetalhada;
    }
    
    /**
     * Devolve o código único de cada Área de Actividade.
     * @return codigo
     */
    public String getCodigo() {
        return codigo;
    }
    
    /**
     * Devolve a descrição breve da Área de Actividade.
     * @return descBreve
     */
    public String getDescBreve() {
        return descBreve;
    }

    /**
     * Devolve a descrição detalhada da Área de Actividade
     * @return descDetalhada
     */
    public String getDescDetalhada() {
        return descDetalhada;
    }
    
    /**
     * Representação textual da classe Área de Actividade
     * @return codigo, descBreve e descDetalhada
     */ 
    @Override    
    public String toString(){
        return String.format("A Área de Actividade de código %s tem: %nDescrição "
                + "breve: %s. %nDescrição detalhada: %s.", codigo, descBreve, descDetalhada );
    }
    
    /**
     * Representação textual da classe Área de Actividade no formato expresso
     * @return codigo, descBreve e descDetalhada
     */
    public String toStringExport(){
        return String.format("%s%c%s%c%s", codigo, SEPARADOR, descBreve, SEPARADOR, descDetalhada);
    }
}
