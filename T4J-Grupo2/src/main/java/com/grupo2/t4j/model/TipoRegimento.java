/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.NomeInvalidoException;

/**
 *
 * @author CAD
 */
public class TipoRegimento {
    
    /**
     * O Id do Tipo de Regimento.
     */
    private String idTipoRegimento;
    
    /**
     * A designação do tipo de seriação adotado para este tipo de regimento.
     */
    private DesignacaoSeriacao designacao;
    
    /**
     * As regras aplicáveis a este tipo de regimento.
     */
    private String descricaoRegras;
    
    /**
     * Construtor completo da classe Tipo de Regimento
     * @param idTipoRegimento - Id do Tipo de Regimento.
     * @param designacao - a designação do tipo de seriação adotado para este tipo de regimento.
     * @param descricaoRegras - as regras aplicáveis a este tipo de regimento.
     */
    public TipoRegimento(String idTipoRegimento, DesignacaoSeriacao designacao,
            String descricaoRegras){
        setIdTipoRegimento(idTipoRegimento);
        setDesignacaoSeriacao(designacao);
        setDescricaoRegras(descricaoRegras);
    }
    
    /**
     * Define o Id do Tipo de Regimento.
     * @param tipoRegimento
     */
    public TipoRegimento (TipoRegimento tipoRegimento){
        setIdTipoRegimento(tipoRegimento.idTipoRegimento);
        setDesignacaoSeriacao(tipoRegimento.designacao);
        setDescricaoRegras(tipoRegimento.descricaoRegras);
    }
    
    /**
     *
     * @param idTipoRegimento
     */
    public void setIdTipoRegimento(String idTipoRegimento){
        this.idTipoRegimento = idTipoRegimento;
    }
    
    /**
     * Define a designação do tipo de seriação adotado para este tipo de regimento.
     * @param designacao
     */
    public void setDesignacaoSeriacao(DesignacaoSeriacao designacao){
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
    public String getIdTipoRegimento(){
        return idTipoRegimento;
    }
    
    /**
     * Devolve a designação do tipo de seriação adotado para este tipo de regimento.
     * @return designacao
     */
    public DesignacaoSeriacao getDesignacaoSeriacao(){
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
     * Representação textual da classe Tipo de Regimento
     * @return o Id do tipo de regimento, a designação do tipo de seriação adotado
     * e a descrição das regras deste tipo de regimento
     */
    @Override
    public String toString(){
        return String.format("Id do Tipo de Regimento: %s %nDesignação do tipo de"
                + "Seriação adotado:%s %nDescrição das regras: %s", idTipoRegimento,
                designacao.toString(), descricaoRegras);
    }
}
