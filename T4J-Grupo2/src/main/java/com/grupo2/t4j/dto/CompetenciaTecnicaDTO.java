package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.AreaActividadeInexistenteException;
import com.grupo2.t4j.exception.CodigoInvalidoException;
import com.grupo2.t4j.exception.DescricaoInvalidaException;

public class CompetenciaTecnicaDTO {

    private String codigo;
    private String descricaoBreve;
    private String descricaoDetalhada;
    private String codigoAreaActividade;

    public CompetenciaTecnicaDTO() {
    }



    public CompetenciaTecnicaDTO(String codigo, String descricaoBreve, String descricaoDetalhada, String codigoAreaActividade) {
        this.codigo = codigo;
        this.descricaoBreve = descricaoBreve;
        this.descricaoDetalhada = descricaoDetalhada;
        this.codigoAreaActividade = codigoAreaActividade;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricaoBreve() {
        return descricaoBreve;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public String getCodigoAreaActividade() {
        return codigoAreaActividade;
    }

    public String toString() {
        return String.format("%s", descricaoBreve);
    }

}
