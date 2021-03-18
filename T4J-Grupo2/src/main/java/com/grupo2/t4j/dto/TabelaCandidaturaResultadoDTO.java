package com.grupo2.t4j.dto;

public class TabelaCandidaturaResultadoDTO {

    private int idCandidatura;
    private double valorPretendido;
    private int duracaoEstimada;
    private String dataCandidatura;
    private String dataEdicao;
    private String resultado;

    public TabelaCandidaturaResultadoDTO() {}

    public TabelaCandidaturaResultadoDTO (int idCandidatura, double valorPretendido, int duracaoEstimada,
                                       String dataCandidatura, String dataEdicao, String resultado){
        this.idCandidatura = idCandidatura;
        this.valorPretendido = valorPretendido;
        this.duracaoEstimada = duracaoEstimada;
        this.dataCandidatura = dataCandidatura;
        this.dataEdicao = dataEdicao;
        this.resultado = resultado;
    }

    public int getIdCandidatura() {
        return idCandidatura;
    }

    public double getValorPretendido() {
        return valorPretendido;
    }

    public int getDuracaoEstimada() {
        return duracaoEstimada;
    }

    public String getDataCandidatura() {
        return dataCandidatura;
    }

    public String getDataEdicao() {
        return dataEdicao;
    }

    public String getResultado() {
        return resultado;
    }


}
