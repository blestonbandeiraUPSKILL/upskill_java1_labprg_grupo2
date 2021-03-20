/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

import com.grupo2.t4j.dto.AtribuicaoDTO;
import com.grupo2.t4j.dto.DTO;

/**
 *
 * @author CAD
 */
public class Atribuicao implements DTO {

    private int idAtribuicao;
    private String nifOrganizacao;
    private String refTarefa;
    private int idAnuncio;
    private int idCandidatura;
    private String emailFreelancer;
    private double valorAceite;
    private int numDiasAceite;
    private String codigoAtribuicao;
    private String dataAtribuicao;
    private String dataInicioTarefa;
    private String dataFimTarefa;

    public Atribuicao(){

    }

    public Atribuicao(int idAtribuicao, String nifOrganizacao, String refTarefa, int idAnuncio, int idCandidatura, String emailFreelancer,
                      double valorAceite, int numDiasAceite, String codigoAtribuicao, String dataAtribuicao, String dataInicioTarefa,
                      String dataFimTarefa){
        setIdAtribuicao(idAtribuicao);
        setNifOrganizacao(nifOrganizacao);
        setRefTarefa(refTarefa);
        setIdAnuncio(idAnuncio);
        setIdCandidatura(idCandidatura);
        setEmailFreelancer(emailFreelancer);
        setValorAceite(valorAceite);
        setNumDiasAceito(numDiasAceite);
        setCodigoAtribuicao(codigoAtribuicao);
        setDataAtribuicao(dataAtribuicao);
        setDataInicioTarefa(dataInicioTarefa);
        setDataFimTarefa(dataFimTarefa);
    }

    public Atribuicao(String nifOrganizacao, String refTarefa, int idAnuncio, int idCandidatura, String emailFreelancer,
                      double valorAceite, int numDiasAceite, String codigoAtribuicao, String dataAtribuicao, String dataInicioTarefa){
        setNifOrganizacao(nifOrganizacao);
        setRefTarefa(refTarefa);
        setIdAnuncio(idAnuncio);
        setIdCandidatura(idCandidatura);
        setEmailFreelancer(emailFreelancer);
        setValorAceite(valorAceite);
        setNumDiasAceito(numDiasAceite);
        setCodigoAtribuicao(codigoAtribuicao);
        setDataAtribuicao(dataAtribuicao);
        setDataInicioTarefa(dataInicioTarefa);
    }

    public Atribuicao(String nifOrganizacao, String refTarefa, int idAnuncio, int idCandidatura, String emailFreelancer,
                      double valorAceite, int numDiasAceite, String codigoAtribuicao, String dataInicioTarefa){
        setNifOrganizacao(nifOrganizacao);
        setRefTarefa(refTarefa);
        setIdAnuncio(idAnuncio);
        setIdCandidatura(idCandidatura);
        setEmailFreelancer(emailFreelancer);
        setValorAceite(valorAceite);
        setNumDiasAceito(numDiasAceite);
        setCodigoAtribuicao(codigoAtribuicao);
        setDataInicioTarefa(dataInicioTarefa);
    }

    public void setIdAtribuicao(int idAtribuicao){
        this.idAtribuicao = idAtribuicao;
    }

    public void setNifOrganizacao(String nifOrganizacao){
        this.nifOrganizacao = nifOrganizacao;
    }

    public void setRefTarefa(String refTarefa){
        this.refTarefa = refTarefa;
    }

    public void setIdAnuncio(int idAnuncio){
        this.idAnuncio = idAnuncio;
    }

    public void setIdCandidatura(int idCandidatura){
        this.idCandidatura = idCandidatura;
    }

    public void setEmailFreelancer(String emailFreelancer){
        this.emailFreelancer = emailFreelancer;
    }

    public void setValorAceite(double valorAceite){
        this.valorAceite = valorAceite;
    }

    public void setNumDiasAceito(int numDiasAceito){
        this.numDiasAceite = numDiasAceito;
    }

    public void setCodigoAtribuicao(String codigoAtribuicao){
        this.codigoAtribuicao = codigoAtribuicao;
    }

    public void setDataAtribuicao(String dataAtribuicao){
        this.dataAtribuicao = dataAtribuicao;
    }

    public void setDataInicioTarefa(String dataInicioTarefa){
        this.dataInicioTarefa = dataInicioTarefa;
    }

    public void setDataFimTarefa(String dataFimTarefa){
        this.dataFimTarefa = dataFimTarefa;
    }

    public int getIdAtribuicao(){
        return idAtribuicao;
    }

    public String getNifOrganizacao(){
        return nifOrganizacao;
    }

    public String getRefTarefa(){
        return refTarefa;
    }

    public int getIdAnuncio(){
        return idAnuncio;
    }

    public int getIdCandidatura(){
        return idCandidatura;
    }

    public String getEmailFreelancer(){
        return emailFreelancer;
    }

    public double getValorAceite(){
        return valorAceite;
    }

    public int getNumDiasAceite(){
        return numDiasAceite;
    }

    public String getCodigoAtribuicao(){
        return codigoAtribuicao;
    }

    public String getDataAtribuicao(){
        return dataAtribuicao;
    }

    public String getDataInicioTarefa(){
        return dataInicioTarefa;
    }

    public String getDataFimTarefa(){
        return dataFimTarefa;
    }

    @Override
    public Object toDTO() {
        return new AtribuicaoDTO(idAtribuicao, nifOrganizacao, refTarefa, idAnuncio, idCandidatura,
                emailFreelancer, valorAceite, numDiasAceite, codigoAtribuicao, dataAtribuicao, dataInicioTarefa, dataFimTarefa);
    }


    //Falta acabar
    
}
