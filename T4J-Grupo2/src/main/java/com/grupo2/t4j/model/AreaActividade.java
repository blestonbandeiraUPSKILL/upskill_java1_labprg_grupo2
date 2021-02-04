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

    public AreaActividade() {
    }
    
    public AreaActividade(String codigo, String descBreve, String descDetalhada){
        setCodigo(codigo);
        setDescBreve(descBreve);
        setDescDetalhada(descDetalhada);
    }
    
    public AreaActividade(AreaActividade areaActividade){
        setCodigo(areaActividade.codigo);
        setDescBreve(areaActividade.descBreve);
        setDescDetalhada(areaActividade.descDetalhada);
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new AreaActividadeDuplicadaException("Código de Área de Actividade "
                    + "já existe!");
        }
        this.codigo = codigo;
    }
    
    public void setDescBreve(String descBreve) {
        if (descBreve == null || descBreve.trim().isEmpty() || descBreve.length() < 5) {
            throw new IllegalArgumentException("Descrição breve é obrigatória!");
        }
        this.descBreve = descBreve;
    }
    
    public void setDescDetalhada(String descDetalhada) {
        if (descDetalhada == null || descDetalhada.trim().isEmpty() || descDetalhada.length() < 10) {
            throw new IllegalArgumentException("Descrição detalhada é obrigatória!");
        }
        this.descDetalhada = descDetalhada;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public String getDescBreve() {
        return descBreve;
    }

    public String getDescDetalhada() {
        return descDetalhada;
    }
    
    @Override
    
    public String toString(){
        return String.format("A Área de Actividade de código %s tem: /n Descrição "
                + "breve: %s. /n Descrição detalhada: %s.", codigo, descBreve, descDetalhada );
    }
    
    public String toStringExport(){
        return String.format("%s%c%s%c%s", codigo, SEPARADOR, descBreve, SEPARADOR, descDetalhada);
    }
}
