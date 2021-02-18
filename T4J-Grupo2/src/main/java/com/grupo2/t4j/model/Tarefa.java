/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CAD
 */
public class Tarefa implements Serializable{

    /**
     *
     */
    private String codigoAreaActividade;

    private String codigoCategoriaTarefa;

    /**
     * A referência única de uma tarefa em uma Organização.
     */
    private String referencia;
    
    /**
     * A designação da tarefa.
     */
    private String designacao;
    
    /**
     * A descrição informal da tarefa.
     */
    private String descInformal;
    
    /**
     * A descrição técnica da tarefa.
     */
    private String descTecnica;
    
    /**
     * A duração estimada da tarefa em dias.
     */
    private int duracaoEst;
    
    /**
     * O custo estimado da tarefa em euros.
     */
    private double custoEst;
    
    private AreaActividade at;
    private Categoria ct;
    private List<CaracterizacaoCT> ccts;
    private List<Categoria> categoriaTarefa;
    
    /**
     * Construtor vazio da classe Tarefa.
     */
    public Tarefa(){
        
    }

    public Tarefa(String codigoAreaActividade,
                  String codigoCategoriaTarefa,
                  String referencia, String designacao,
                  String descInformal, String descTecnica, int duracao, double custo) {
        setCodigoAreaActividade(codigoAreaActividade);
        setCodigoCategoriaTarefa(codigoCategoriaTarefa);
        setReferencia(referencia);
        setDesignacao(designacao);
        setDescInformal(descInformal);
        setDescTecnica(descTecnica);
        setDuracaoEst(duracao);
        setCustoEst(custo);
    }

    public Tarefa(Tarefa outraTarefa) {
        this.codigoAreaActividade = outraTarefa.getCodigoAreaActividade();
        this.codigoCategoriaTarefa = outraTarefa.getCodigoCategoriaTarefa();
        this.referencia = outraTarefa.getReferencia();
        this.designacao = outraTarefa.getDesignacao();
        this.descInformal = outraTarefa.getDescInformal();
        this.descTecnica = outraTarefa.descTecnica;
        this.duracaoEst = outraTarefa.getDuracaoEst();
        this.custoEst = outraTarefa.getCustoEst();
    }

    public void setCodigoAreaActividade(String codigoAreaActividade) {
        if (codigoAreaActividade == null || codigoAreaActividade.trim().isEmpty()) {
            throw new CodigoInvalidoException("O código da Área de Actividade não é válido!");
        }
        this.codigoAreaActividade = codigoAreaActividade;
    }

    public void setCodigoCategoriaTarefa(String codigoCategoriaTarefa) {
        if(codigoCategoriaTarefa == null || codigoCategoriaTarefa.trim().isEmpty()) {
            throw new CodigoInvalidoException("O código da Categoria de Tarefa não é válido");
        }
        this.codigoCategoriaTarefa = codigoCategoriaTarefa;
    }

    /**
     * Verifica a validade do parâmetro recebido e regista a referência da tarefa.
     * @param referencia a referência única de uma tarefa em uma Organização.
     */
    public final void setReferencia(String referencia){
        if (referencia == null || referencia.trim().isEmpty()) {
            throw new NomeInvalidoException("Referência é inválida!");
        }
        this.referencia = referencia;
    }
    
    /**
     * Verifica a validade do parâmetro recebido e regista a designação de uma tarefa.
     * @param designacao a designação da tarefa.
     */
    public final void setDesignacao(String designacao){
        if (designacao == null || designacao.trim().isEmpty()) {
            throw new NomeInvalidoException("Designação é inválida!");
        }
        this.designacao = designacao;
    }
    
    /**
     * Verifica a validade do parâmetro recebido e regista a descrição informal da tarefa.
     * @param descInformal a descrição informal da tarefa.
     */
    public final void setDescInformal(String descInformal){
        if (descInformal == null || descInformal.trim().isEmpty()) {
            throw new NomeInvalidoException("Descrição Informal é inválida!");
        }
        this.descInformal = descInformal;
    }
    
    /**
     * Verifica a validade do parâmetro recebido e regista a descrição técnica da tarefa. 
     * @param descTecnica a descrição técnica da tarefa.
     */
    public final void setDescTecnica(String descTecnica){
        if (descTecnica == null || descTecnica.trim().isEmpty()) {
            throw new NomeInvalidoException("Descrição Técnica é inválida!");
        }
        this.descTecnica = descTecnica;
    }
    
    /**
     * Verifica a validade do parâmetro recebido e regista a duração estimada da tarefa em dias.
     * @param duracaoEst a duração estimada da tarefa em dias.
     */
    public final void setDuracaoEst(int duracaoEst){
        if (duracaoEst <= 0) {
            throw new QuantidadeInvalidaException("A duração estimada é inválida!");
        }
        this.duracaoEst = duracaoEst;
    }
    
    /**
     * Verifica a validade do parâmetro recebido e regista o custo estimado da tarefa em euros.
     * @param custoEst o custo estimado da tarefa em euros.
     */
    public final void setCustoEst(double custoEst){
        if (custoEst <= 0) {
            throw new QuantidadeInvalidaException("O custo estimado é inválido!");
        }
        this.custoEst = custoEst;
    }
    
    /**
     * Devolve a referência única de uma tarefa em uma Organização.
     * @return referencia
     */
    public String getReferencia(){
        return referencia;
    }
    
    /**
     * Devolve a designação de uma tarefa.
     * @return designacao
     */
    public String getDesignacao(){
        return designacao;
    }
    
    /**
     * Devolve a descrição informal da tarefa.
     * @return descInformal
     */
    public String getDescInformal(){
        return descInformal;
    }
    
    /**
     * Devolve a descrição técnica da tarefa.
     * @return descTecnica
     */
    public String getDescTecnica(){
        return descTecnica;
    }
    
    /**
     * Devolve a duração estimada da tarefa em dias.
     * @return duracaoEst
     */
    public int getDuracaoEst(){
        return duracaoEst;
    }
    
    /**
     * Devolve o custo estimado da tarefa em euros.
     * @return custoEst
     */
    public double getCustoEst(){
        return custoEst;
    }

    /**
     * Devolve o código da área de actividade
     * @return at
     */
    public String getCodigoAreaActividade() {
        return codigoAreaActividade;
    }

    public String getCodigoCategoriaTarefa() {
        return codigoCategoriaTarefa;
    }
    
    
    /**
     * Representação textual da classe Tarefa
     * @return referencia, designacao, descInformal, descTecnica, duracaoEst, custoEst
     */  
    @Override
    public String toString(){
        return String.format("A tarefa indicada tem os seguintes dados: %nReferência: "
                + "%s /%nDesignação: %s %nDescrição informal: %s %nDescrição Técnica: %s"
                + "%nDuração estimada: %d dias %nCusto estimado: %.2f euros", referencia,
                designacao, descInformal, descTecnica, duracaoEst, custoEst);
    }
}
