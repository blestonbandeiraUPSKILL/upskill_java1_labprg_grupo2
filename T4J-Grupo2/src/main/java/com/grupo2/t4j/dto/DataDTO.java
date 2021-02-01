package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.grupo2.t4j.model.Mes;

@JsonPropertyOrder({"dia", "mes", "ano"})
@JacksonXmlRootElement(localName = "data")

public class DataDTO {

    @JacksonXmlProperty(localName = "dia")
    private int dia;

    @JacksonXmlProperty(localName = "mes")
    private int mes;

    @JacksonXmlProperty(localName = "ano")
    private int ano;

    public DataDTO() {
    }

    public DataDTO(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
}
