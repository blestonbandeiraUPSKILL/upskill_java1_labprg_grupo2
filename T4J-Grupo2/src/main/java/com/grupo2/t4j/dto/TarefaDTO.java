package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.CodigoInvalidoException;
import com.grupo2.t4j.exception.NomeInvalidoException;
import com.grupo2.t4j.exception.QuantidadeInvalidaException;

public class TarefaDTO {

    private String codigoAreaActividade;
    private String codigoCategoriaTarefa;
    private String referencia;
    private String nifOrganizacao;
    private String designacao;
    private String descInformal;
    private String descTecnica;
    private int duracaoEstimada;
    private double custoEstimado;
    private String emailColaborador;

    public TarefaDTO(){

    }

    public TarefaDTO(String nifOrganizacao, String referencia, String designacao,
                  String descInformal, String descTecnica, int duracao, double custo,
                  String codigoCategoriaTarefa,  String emailColaborador) {
        this.nifOrganizacao = nifOrganizacao;
        this.referencia = referencia;
        this.designacao = designacao;
        this.descInformal = descInformal;
        this.descTecnica = descTecnica;
        this.duracaoEstimada = duracao;
        this.custoEstimado = custo;
        this.codigoCategoriaTarefa = codigoCategoriaTarefa;
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

    @Override
    public String toString(){
        return String.format("Referência: %-15s |Designação: %-15s"
                        + " |Duração estimada: %-5d dias |Custo estimado: %-5.2f euros |Colaborador: %-15s" , referencia,
                designacao, duracaoEstimada, custoEstimado, emailColaborador);
    }
}
