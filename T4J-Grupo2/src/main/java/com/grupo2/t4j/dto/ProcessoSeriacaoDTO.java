package com.grupo2.t4j.dto;

public class ProcessoSeriacaoDTO {

    /**
     * O id da Seriação.
     */
    private int idSeriacao;

    /**
     * O id do Anúncio.
     */
    private int idAnuncio;

    /**
     * A data da realização da seriação
     */
    private String dataSeriacao;

    public ProcessoSeriacaoDTO() {
    }

    public ProcessoSeriacaoDTO(int idSeriacao, int idAnuncio, String dataSeriacao) {
        this.idSeriacao = idSeriacao;
        this.idAnuncio = idAnuncio;
        this.dataSeriacao = dataSeriacao;
    }

    public int getIdSeriacao() {
        return idSeriacao;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public String getDataSeriacao() {
        return dataSeriacao;
    }
}
