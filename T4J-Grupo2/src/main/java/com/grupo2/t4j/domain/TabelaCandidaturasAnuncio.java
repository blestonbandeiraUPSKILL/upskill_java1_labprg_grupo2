/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain;

/**
 *
 * @author CAD
 */
public class TabelaCandidaturasAnuncio {

    private int classificacao;
    private int idCandidatura;
    private String email;
    private int duracao;
    private double custo;
    
    public TabelaCandidaturasAnuncio(int classificacao,int idCandidatura, String email, int duracao, double custo){
        setClassificacao(classificacao);
        setIdCandidatura(idCandidatura);
        setEmail(email);
        setDuracao(duracao);
        setCusto(custo);
    }
    
    public TabelaCandidaturasAnuncio(int idCandidatura, String email, int duracao, double custo){
        setIdCandidatura(idCandidatura);
        setEmail(email);
        setDuracao(duracao);
        setCusto(custo);
    }
    
    public void setClassificacao(int classificacao){
        this.classificacao = classificacao;
    }
    
    public void setIdCandidatura(int idCandidatura){
        this.idCandidatura = idCandidatura;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setDuracao(int duracao){
        this.duracao = duracao;
    }
    
    public void setCusto(double custo){
        this.custo = custo;
    }
    
    public int getClassificacao(){
        return classificacao;
    }
    
    public int getIdCandidatura(){
        return idCandidatura;
    }
    
    public String getEmail(){
        return email;
    }
    
    public int getDuracao(){
        return duracao;
    }
    
    public double getCusto(){
        return custo;
    }
}
