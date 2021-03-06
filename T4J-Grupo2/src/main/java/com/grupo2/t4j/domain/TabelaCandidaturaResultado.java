package com.grupo2.t4j.domain;

import com.grupo2.t4j.dto.DTO;
import com.grupo2.t4j.dto.TabelaCandidaturaResultadoDTO;

public class TabelaCandidaturaResultado implements DTO {

    private int idCandidatura;
    private double valorPretendido;
    private int duracaoEstimada;
    private String dataCandidatura;
    private String dataEdicao;
    private String resultado;

    public TabelaCandidaturaResultado (int idCandidatura, double valorPretendido, int duracaoEstimada,
                                       String dataCandidatura, String dataEdicao){
        setIdCandidatura(idCandidatura);
        setValorPretendido(valorPretendido);
        setDuracaoEstimada(duracaoEstimada);
        setDataCandidatura(dataCandidatura);
        setDataEdicao(dataEdicao);


    }
    public TabelaCandidaturaResultado (int idCandidatura, double valorPretendido, int duracaoEstimada,
                                       String dataCandidatura, String dataEdicao, String resultado){
        setIdCandidatura(idCandidatura);
        setValorPretendido(valorPretendido);
        setDuracaoEstimada(duracaoEstimada);
        setDataCandidatura(dataCandidatura);
        setDataEdicao(dataEdicao);
        setResultado(resultado);

    }

    public int getIdCandidatura() {
        return idCandidatura;
    }

    public void setIdCandidatura(int idCandidatura) {
        this.idCandidatura = idCandidatura;
    }

    public double getValorPretendido() {
        return valorPretendido;
    }

    public void setValorPretendido(double valorPretendido) {
        this.valorPretendido = valorPretendido;
    }

    public int getDuracaoEstimada() {
        return duracaoEstimada;
    }

    public void setDuracaoEstimada(int duracaoEstimada) {
        this.duracaoEstimada = duracaoEstimada;
    }

    public String getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(String dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public String getDataEdicao() {
        return dataEdicao;
    }

    public void setDataEdicao(String dataEdicao) {
        this.dataEdicao = dataEdicao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
            this.resultado = resultado;
    }

    @Override
    public Object toDTO() {
        return new TabelaCandidaturaResultadoDTO(idCandidatura,valorPretendido,
                duracaoEstimada, dataCandidatura, dataEdicao, resultado);
    }
}
