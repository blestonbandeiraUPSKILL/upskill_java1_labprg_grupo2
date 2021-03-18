package com.grupo2.t4j.dto;

public class TabelaCandidaturasAnuncioDTO {

    private int classificacao;
    private int idCandidatura;
    private String email;
    private int duracao;
    private double custo;

    public TabelaCandidaturasAnuncioDTO(int classificacao,int idCandidatura, String email, int duracao, double custo){
        this.classificacao = classificacao;
        this.idCandidatura = idCandidatura;
        this.email = email;
        this.duracao = duracao;
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
