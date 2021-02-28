/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.DescricaoInvalidaException;
import com.grupo2.t4j.exception.IdInvalidoException;
import com.grupo2.t4j.exception.NomeInvalidoException;

/**
 *
 * @author CAD
 */
public class TipoRegimento {
    
    /**
     * O Id do Tipo de Regimento.
     */
    private int idTipoRegimento;
    
    /**
     * A designação do tipo de seriação adotado para este tipo de regimento.
     */
    private String designacao;
    
    /**
     * As regras aplicáveis a este tipo de regimento.
     */
    private String descricaoRegras;

    public TipoRegimento(){}
    
    /**
     * Construtor completo da classe Tipo de Regimento
     * @param idTipoRegimento - Id do Tipo de Regimento.
     * @param designacao - a designação do tipo de seriação adotado para este tipo de regimento.
     * @param descricaoRegras - as regras aplicáveis a este tipo de regimento.
     */
    public TipoRegimento(int idTipoRegimento, String designacao,
            String descricaoRegras){
        setIdTipoRegimento(idTipoRegimento);
        setDesignacao(designacao);
        setDescricaoRegras(descricaoRegras);
    }
    
    /**
     * Define o Id do Tipo de Regimento.
     * @param tipoRegimento
     */
    public TipoRegimento (TipoRegimento tipoRegimento){
        setIdTipoRegimento(tipoRegimento.idTipoRegimento);
        setDesignacao(tipoRegimento.designacao);
        setDescricaoRegras(tipoRegimento.descricaoRegras);
    }
    
    /**
     *
     * @param idTipoRegimento
     */
    public void setIdTipoRegimento(int idTipoRegimento){
        if(idTipoRegimento > 0 ) {
            this.idTipoRegimento = idTipoRegimento;
        }
        else {
            throw new IdInvalidoException("O id do tipo de regimento não é válido.");
        }
    }
    
    /**
     * Define a designação do tipo de seriação adotado para este tipo de regimento.
     * @param designacao
     */
    public void setDesignacao(String designacao){
        if (designacao == null || designacao.trim().isEmpty()) {
            throw new DescricaoInvalidaException("A designação tem de ser preenchida.");
        }
        this.designacao = designacao;
    }
    
    /**
     * Define as regras aplicáveis a este tipo de regimento.
     * @param descricaoRegras
     */
    public void setDescricaoRegras(String descricaoRegras){
        if (descricaoRegras == null || descricaoRegras.trim().isEmpty()) {
            throw new NomeInvalidoException("A descrição informada para as regras é inválida!");
        }
        this.descricaoRegras = descricaoRegras;
    }
    
    /**
     * Devolve o Id do Tipo de Regimento.
     * @return idTipoRegimento
     */
    public int getIdTipoRegimento(){
        return idTipoRegimento;
    }
    
    /**
     * Devolve a designação do tipo de seriação adotado para este tipo de regimento.
     * @return designacao
     */
    public String getDesignacao(){
        return designacao;
    }
    
    /**
     * Devolve as regras aplicáveis a este tipo de regimento.
     * @return descricaoRegras
     */
    public String getDescricaoRegras(){
        return descricaoRegras;
    }
    
    /**
     * Representação textual da classe Tipo de Regimento em formato de exibição
     * @return o Id do tipo de regimento, a designação do tipo de seriação adotado
     * deste tipo de regimento
     */
    @Override
    public String toString(){
        return String.format("ID: %-12s |Designação: %-20s", idTipoRegimento,
                  designacao);
    }
    
    /**
     * Representação textual da classe Tipo de Regimento
     * @return o Id do tipo de regimento, a designação do tipo de seriação adotado
     * e a descrição das regras deste tipo de regimento
     */
    public String toStringCompleto(){
        return String.format("Id do Tipo de Regimento: %s %nDesignação do tipo de"
                + "Seriação adotado:%s %nDescrição das regras: %s", idTipoRegimento,
                designacao.toString(), descricaoRegras);
    }
}
