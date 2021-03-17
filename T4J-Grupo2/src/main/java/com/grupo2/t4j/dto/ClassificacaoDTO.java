package com.grupo2.t4j.dto;

import com.grupo2.t4j.domain.Classificacao;

public class ClassificacaoDTO {

    private int idClassificacao;
    private int idCandidatura;
    private int posicao;
    private int idSeriacao;

    public ClassificacaoDTO(){

    }

    public ClassificacaoDTO(int idClassificacao,  int posicao, int idSeriacao,
                         int idCandidatura){
        this.idClassificacao = idClassificacao;
        this.posicao = posicao;
        this.idSeriacao = idSeriacao;
        this.idCandidatura = idCandidatura;
    }

    public int getIdClassificacao(){
        return idClassificacao;
    }

    public int getPosicaoFreelancer(){
        return posicao;
    }

    public int getIdSeriacao(){
        return idSeriacao;
    }

    public int getIdCandidatura(){
        return idCandidatura;
    }


}
