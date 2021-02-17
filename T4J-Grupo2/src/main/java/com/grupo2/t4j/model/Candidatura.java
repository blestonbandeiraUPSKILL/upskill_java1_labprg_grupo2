/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author CAD
 */
public class Candidatura implements Serializable {
    
    /**
     * 
     */
    private String idCandidatura;
    
    /**
     * 
     */
    private Data dataCandidatura;
    
    /**
     * 
     */
    private double valorPretendido;
    
    /**
     * 
     */
    private int numeroDias;
    
    /**
     * 
     */
    private String txtApresentacao;
    
    /**
     * 
     */
    private String txtMotivacao;
    
    /**
     * 
     */
    private static final String APRESENTACAO_POR_OMISSAO = "Sem apresentação";
    
    /**
     * 
     */
    private static final String MOTIVACAO_POR_OMISSAO = "Sem motivação";
    
     /**
     * A data atual no formato da classe Data
     */
    private Calendar cal = Calendar.getInstance();
    private Data hoje = new Data(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH));
    
    /**
     *
     * @param idCandidatura
     * @param valorPretendido
     * @param numeroDias
     * @param txtApresentacao
     * @param txtMotivacao
     */
    public Candidatura(String idCandidatura, double valorPretendido, int numeroDias,
    String txtApresentacao,String txtMotivacao){
        setIdCandidatura(idCandidatura);
        setData();
        setValor(valorPretendido);
        setDias(numeroDias);
        setApresentacao(txtApresentacao);
        setMotivacao(txtMotivacao);
    }
    
    /**
     *
     * @param idCandidatura
     * @param valorPretendido
     * @param numeroDias
     */
    public Candidatura(String idCandidatura, double valorPretendido, int numeroDias){
        setIdCandidatura(idCandidatura);
        setData();
        setValor(valorPretendido);
        setDias(numeroDias);
        setApresentacao(APRESENTACAO_POR_OMISSAO);
        setMotivacao(MOTIVACAO_POR_OMISSAO);
    }
    
    /**
     *
     * @param candidatura
     */
    public Candidatura(Candidatura candidatura){
        setIdCandidatura(candidatura.idCandidatura);
        setData();
        setValor(candidatura.valorPretendido);
        setDias(candidatura.numeroDias);
        setApresentacao(candidatura.txtApresentacao);
        setMotivacao(candidatura.txtMotivacao);
    }
    
    /**
     * Define o id da Candidatura
     * @param idCandidatura - o id da Candidatura
     */
    public void setIdCandidatura(String idCandidatura){
        if (idCandidatura == null || idCandidatura.trim().isEmpty()) {
            throw new IllegalArgumentException("Id da Candidatura é inválido!");
        }
        this.idCandidatura = idCandidatura;
    }

    /**
     * Define a data de Candidatura como sendo sempre a data atual do calendário     *   
     */
    public void setData(){
        this.dataCandidatura = hoje;
    }
      
    /**
     * Define o valor pretendido pelo Freelancer para realização da Tarefa à qual
     * se candidatou
     * @param valorPretendido - o valor pretendido pelo serviço, em euros
     */
    public void setValor(double valorPretendido){
        if (valorPretendido > 0) {
            this.valorPretendido = valorPretendido;
        }
        else{
            throw new IllegalArgumentException("O valor prentendido informado não"
                    + "é válido!");
        }
    }
    
    /**
     *
     * @param numeroDias
     */
    public void setDias(int numeroDias){
        if(numeroDias > 0){
            this.numeroDias = numeroDias;
        }
        else{
            throw new IllegalArgumentException("O número de dias informado não"
                    + "é válido!");
        }
    }
    
    /**
     *
     * @param txtApresentacao
     */
    public void setApresentacao(String txtApresentacao){
        if (txtApresentacao == null || txtApresentacao.trim().isEmpty()) {
            throw new IllegalArgumentException("O texto de apresentação é inválido!");
        }
        else{
            this.txtApresentacao = txtApresentacao;
        }
    }
    
    /**
     *
     * @param txtMotivacao
     */
    public void setMotivacao(String txtMotivacao){
        if (txtMotivacao == null || txtMotivacao.trim().isEmpty()) {
            throw new IllegalArgumentException("O texto de motivação é inválido!");
        }
        else{
            this.txtMotivacao = txtMotivacao;
        }
    }
    
    /**
     *
     * @return
     */
    public String getIdCandidatura(){
        return idCandidatura;
    }
    
    /**
     *
     * @return
     */
    public Data getDataCandidatura(){
        return dataCandidatura;
    }
    
    /**
     *
     * @return
     */
    public double getValorPretendido(){
        return valorPretendido;
    }
    
    /**
     *
     * @return
     */
    public int getNumeroDias(){
        return numeroDias;
    }
    
    /**
     *
     * @return
     */
    public String getApresentacao(){
        return txtApresentacao;
    }
    
    /**
     *
     * @return
     */
    public String getMotivacao(){
        return txtMotivacao;
    }
    
    /**
     * Representação textual da classe Candidatura
     * @return o id da candidatura
     */   
    @Override
    public String toString(){
        return String.format("Candidatura Id: %s  %nData Candidatura: %s"
                + "%nValor pretendido: %f.2  euros %nNúmero de dias: %"
                + "%nApresentação: %s %nMotivação: %s", idCandidatura, 
                dataCandidatura.toAnoMesDiaString(), valorPretendido,
                numeroDias, txtApresentacao, txtMotivacao);
    }
    
}
