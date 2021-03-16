package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.IdInvalidoException;
import com.grupo2.t4j.exception.NomeInvalidoException;


public class AreaActividadeDTO {

    private String codigo;
    private String descBreve;
    private String descDetalhada;

    public AreaActividadeDTO(){}

    public AreaActividadeDTO(String codigo, String descBreve, String descDetalhada){
        this.codigo = codigo;
        this.descBreve = descBreve;
        this.descDetalhada = descDetalhada;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescBreve() {
        return descBreve;
    }

    public String getDescDetalhada() {
        return descDetalhada;
    }
}
