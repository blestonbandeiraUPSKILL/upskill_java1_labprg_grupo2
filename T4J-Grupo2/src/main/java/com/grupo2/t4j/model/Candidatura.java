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
     * O id da Candidatura
     */
    private String idCandidatura;
    
    /**
     * O email do Freelancer que se candidatou ao anúncio
     */
    private String emailFreelancer;
    
    /**
     * A data da Candidatura
     */
    private Data dataCandidatura;
    
    /**
     * O valor pretendido pelo Freelancer na sua candidatura a uma dada Tarefa
     */
    private double valorPretendido;
    
    /**
     * O número de dias que o Freelancer indica para a realização de uma dada Tarefa
     * a que se candidatou
     */
    private int numeroDias;
    
    /**
     * O texto de apresentação do Freelancer para sua candidatura a uma dada Tarefa
     */
    private String txtApresentacao;
    
    /**
     * O texto de motivação do Freelancer para sua candidatura a uma dada Tarefa
     */
    private String txtMotivacao;
    
    /**
     *  O texto de apresentação do Freelancer para uma candidatura por omissão
     */
    private static final String APRESENTACAO_POR_OMISSAO = "Sem apresentação";
    
    /**
     *  O texto de motivação do Freelancer para uma candidatura por omissão
     */
    private static final String MOTIVACAO_POR_OMISSAO = "Sem motivação";
    
     /**
     * A data atual no formato da classe Data
     */
    private Calendar cal = Calendar.getInstance();
    private Data hoje = new Data(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH));
    
    /**
     * Construtor vazio da classe Candidatura
     */
    public Candidatura (){
        
    }
    
    /**
     * O construtor completo da classe Candidatura
     * @param idCandidatura - o id da Candidatura
     * @param emailFreelancer - o email do Freelancer em formato String
     * @param valorPretendido - o valor pretendido pelo Freelancer na sua candidatura a uma dada Tarefa
     * @param numeroDias - número de dias que o Freelancer indica para a realização de uma dada Tarefa
     * a que se candidatou
     * @param txtApresentacao - o texto de apresentação do Freelancer para sua candidatura a uma dada Tarefa
     * @param txtMotivacao - o texto de motivação do Freelancer para sua candidatura a uma dada Tarefa
     */
    public Candidatura(String idCandidatura, String emailFreelancer, double valorPretendido, 
            int numeroDias, String txtApresentacao,String txtMotivacao){
        setIdCandidatura(idCandidatura);
        setEmailFreelancer(emailFreelancer);
        setData();
        setValor(valorPretendido);
        setDias(numeroDias);
        setApresentacao(txtApresentacao);
        setMotivacao(txtMotivacao);
    }
    
    /**
     * Construtor da classe Candidatura com texto de Apresentação e de Motivação 
     * com valores por omissão
     * @param idCandidatura - o id da Candidatura
     * @param emailFreelancer - o email do Freelancer em formato String
     * @param valorPretendido - o valor pretendido pelo Freelancer na sua candidatura a uma dada Tarefa
     * @param numeroDias - número de dias que o Freelancer indica para a realização de uma dada Tarefa
     * a que se candidatou
     */
    public Candidatura(String idCandidatura, String emailFreelancer, double valorPretendido, 
            int numeroDias){
        setIdCandidatura(idCandidatura);
        setEmailFreelancer(emailFreelancer);
        setData();
        setValor(valorPretendido);
        setDias(numeroDias);
        setApresentacao(APRESENTACAO_POR_OMISSAO);
        setMotivacao(MOTIVACAO_POR_OMISSAO);
    }
    
    /**
     * Construtor da classe Candidatura 
     * @param candidatura é do tipo da classe Candidatura
     */
    public Candidatura(Candidatura candidatura){
        setIdCandidatura(candidatura.idCandidatura);
        setEmailFreelancer(candidatura.emailFreelancer);
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
     * Define o email do Freelancer que se candidatou ao anúncio
     * @param emailFreelancer - o email do Freelancer em formato String
     */
    public void setEmailFreelancer(String emailFreelancer){
        this.emailFreelancer = emailFreelancer;
    }

    /**
     * Define a data de Candidatura como sendo sempre a data atual do calendário     *   
     */
    public void setData(){
        this.dataCandidatura = hoje;
    }
      
    /**
     * Define o valor pretendido pelo Freelancer para realização da Tarefa à qual
     * se candidatou em euros
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
     * Define o número de dias que o Freelancer indica para a realização de uma dada Tarefa
     * a que se candidatou
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
     * Define o texto de apresentação do Freelancer para sua candidatura a uma dada Tarefa
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
     * Define o texto de motivação do Freelancer para sua candidatura a uma dada Tarefa
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
     * Devolve o id da Candidatura
     * @return idCandidatura
     */
    public String getIdCandidatura(){
        return idCandidatura;
    }
    
    /**
     * Devolve o email do Freelancer que se candidatou ao anúncio
     * @return emailFreelancer
     */
    public String getEmailFreelancer(){
        return emailFreelancer;
    }
    
    /**
     * Devolve a data de Candidatura no formato Data
     * @return dataCandidatura
     */
    public Data getDataCandidatura(){
        return dataCandidatura;
    }
    
    /**
     * Devolve o valor pretendido pelo Freelancer para realização da Tarefa à qual
     * se candidatou em euros
     * @return valorPretendido
     */
    public double getValorPretendido(){
        return valorPretendido;
    }
    
    /**
     * Devolve o número de dias que o Freelancer indica para a realização de uma dada Tarefa
     * a que se candidatou
     * @return numeroDias
     */
    public int getNumeroDias(){
        return numeroDias;
    }
    
    /**
     * Devolve o texto de apresentação do Freelancer para sua candidatura a uma dada Tarefa
     * @return
     */
    public String getApresentacao(){
        return txtApresentacao;
    }
    
    /**
     * Devolve o texto de motivação do Freelancer para sua candidatura a uma dada Tarefa
     * @return
     */
    public String getMotivacao(){
        return txtMotivacao;
    }
    
    /**
     * Representação textual da classe Candidatura em formato de exibição
     * @return o id da candidatura, o email do Freelancer candidato, a data da 
     * candidatura em formato texto, o valor pretendido pelo Freelancer para a 
     * realização da Tarefa, o número de dias para a realização da Tarefa,
     * o texto de apresentação da Candidatura e o texto de Motivação para a 
     * Candidatura
     */   
    @Override
    public String toString(){
        return String.format("ID: %-12s |Freelancer: %-20s |Data Candidatura: %-12s"
                + " |Valor pretendido: %-12f.2  euros |Número de dias: %-8d"
                + " |Apresentação: %-50s |Motivação: %-50s", idCandidatura, emailFreelancer, 
                dataCandidatura.toAnoMesDiaString(), valorPretendido,
                numeroDias, txtApresentacao, txtMotivacao);
    }
    
    /**
     * Representação textual da classe Candidatura Completa
     * @return o id da candidatura, o email do Freelancer candidato, a data da 
     * candidatura em formato texto, o valor pretendido pelo Freelancer para a 
     * realização da Tarefa, o número de dias para a realização da Tarefa,
     * o texto de apresentação da Candidatura e o texto de Motivação para a 
     * Candidatura
     */   
    public String toStringCompleto(){
        return String.format("ID: %s  %nEmail do Freelancer: %s %nData Candidatura: %s"
                + "%nValor pretendido: %f.2  euros %nNúmero de dias: %"
                + "%nApresentação: %s %nMotivação: %s", idCandidatura, emailFreelancer, 
                dataCandidatura.toAnoMesDiaString(), valorPretendido,
                numeroDias, txtApresentacao, txtMotivacao);
    }    
}
