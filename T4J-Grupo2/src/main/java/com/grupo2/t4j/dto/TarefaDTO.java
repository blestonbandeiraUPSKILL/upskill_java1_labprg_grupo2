package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.CodigoInvalidoException;
import com.grupo2.t4j.exception.NomeInvalidoException;
import com.grupo2.t4j.exception.QuantidadeInvalidaException;
import com.grupo2.t4j.model.Tarefa;

@JsonPropertyOrder({"codigoAreaActividade", "codigoCategoriaTarefa", "referencia",
                    "nifOrganizacao", "designacao", "descInformal", "descTecnica",
                    "duracaoEstimada", "custoEstimado", "emailColaborador"})

public class TarefaDTO {

    @JacksonXmlProperty(localName = "codigoAreaActividade")
    private String codigoAreaActividade;

    @JacksonXmlProperty(localName = "codigoCategoriaTarefa")
    private String codigoCategoriaTarefa;

    @JacksonXmlProperty(localName = "referencia")
    private String referencia;

    @JacksonXmlProperty(localName = "nifOrganizacao")
    private String nifOrganizacao;

    @JacksonXmlProperty(localName = "designacao")
    private String designacao;

    @JacksonXmlProperty(localName = "descInformal")
    private String descInformal;

    @JacksonXmlProperty(localName = "descTecnica")
    private String descTecnica;

    @JacksonXmlProperty(localName = "duracaoEstimada")
    private int duracaoEstimada;

    @JacksonXmlProperty(localName = "custoEstimado")
    private double custoEstimado;

    @JacksonXmlProperty(localName = "emailColaborador")
    private String emailColaborador;

    public TarefaDTO(){

    }

    public TarefaDTO(String referencia, String designacao,
                  String descInformal, String descTecnica, int duracao, double custo,
                  String codigoAreaActividade,
                  String codigoCategoriaTarefa) {
        setCodigoAreaActividade(codigoAreaActividade);
        setCodigoCategoriaTarefa(codigoCategoriaTarefa);
        setReferencia(referencia);
        setDesignacao(designacao);
        setDescInformal(descInformal);
        setDescTecnica(descTecnica);
        setDuracaoEst(duracao);
        setCustoEst(custo);
    }

    public TarefaDTO(String nifOrganizacao, String referencia, String designacao,
                  String descInformal, String descTecnica, int duracao, double custo,
                  String codigoCategoriaTarefa,  String emailColaborador) {
        setCodigoCategoriaTarefa(codigoCategoriaTarefa);
        setReferencia(referencia);
        setDesignacao(designacao);
        setDescInformal(descInformal);
        setDescTecnica(descTecnica);
        setDuracaoEst(duracao);
        setCustoEst(custo);
        this.nifOrganizacao = nifOrganizacao;
        this.emailColaborador = emailColaborador;
    }

    public TarefaDTO(TarefaDTO tarefaDTO) {
        this.codigoAreaActividade = tarefaDTO.getCodigoAreaActividade();
        this.codigoCategoriaTarefa = tarefaDTO.getCodigoCategoriaTarefa();
        this.referencia = tarefaDTO.getReferencia();
        this.designacao = tarefaDTO.getDesignacao();
        this.descInformal = tarefaDTO.getDescInformal();
        this.descTecnica = tarefaDTO.descTecnica;
        this.duracaoEstimada = tarefaDTO.getDuracaoEst();
        this.custoEstimado = tarefaDTO.getCustoEst();
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

    public final void setReferencia(String referencia){
        if (referencia == null || referencia.trim().isEmpty()) {
            throw new NomeInvalidoException("Referência é inválida!");
        }
        this.referencia = referencia;
    }

    public final void setDesignacao(String designacao){
        if (designacao == null || designacao.trim().isEmpty()) {
            throw new NomeInvalidoException("Designação é inválida!");
        }
        this.designacao = designacao;
    }

    public final void setDescInformal(String descInformal){
        if (descInformal == null || descInformal.trim().isEmpty()) {
            throw new NomeInvalidoException("Descrição Informal é inválida!");
        }
        this.descInformal = descInformal;
    }

    public final void setDescTecnica(String descTecnica){
        if (descTecnica == null || descTecnica.trim().isEmpty()) {
            throw new NomeInvalidoException("Descrição Técnica é inválida!");
        }
        this.descTecnica = descTecnica;
    }

    public final void setDuracaoEst(int duracaoEstimada){
        if (duracaoEstimada <= 0) {
            throw new QuantidadeInvalidaException("A duração estimada é inválida!");
        }
        this.duracaoEstimada = duracaoEstimada;
    }

    public final void setCustoEst(double custoEstimado){
        if (custoEstimado <= 0) {
            throw new QuantidadeInvalidaException("O custo estimado é inválido!");
        }
        this.custoEstimado = custoEstimado;
    }

    public void setEmailColaborador(String emailColaborador) {
        this.emailColaborador = emailColaborador;
    }

    public String getReferencia(){
        return referencia;
    }

    public String getDesignacao(){
        return designacao;
    }

    public String getDescInformal(){
        return descInformal;
    }

    public String getDescTecnica(){
        return descTecnica;
    }

    public int getDuracaoEst(){
        return duracaoEstimada;
    }

    public double getCustoEst(){
        return custoEstimado;
    }

    public String getCodigoAreaActividade() {
        return codigoAreaActividade;
    }

    public String getCodigoCategoriaTarefa() {
        return codigoCategoriaTarefa;
    }

    public String getNifOrganizacao() {
        return nifOrganizacao;
    }

    public void setNifOrganizacao(String nifOrganizacao) {
        this.nifOrganizacao = nifOrganizacao;
    }

    public String getEmailColaborador() {
        return emailColaborador;
    }




}
