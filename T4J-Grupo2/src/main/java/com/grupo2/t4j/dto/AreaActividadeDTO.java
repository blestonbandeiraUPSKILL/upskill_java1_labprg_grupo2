package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.IdInvalidoException;
import com.grupo2.t4j.exception.NomeInvalidoException;

@JsonPropertyOrder({"codigo", "descBreve", "descDetalhada"})

public class AreaActividadeDTO {

    @JacksonXmlProperty(localName = "codigo")
    private String codigo;

    @JacksonXmlProperty(localName = "descBreve")
    private String descBreve;

    @JacksonXmlProperty(localName = "descDetalhada")
    private String descDetalhada;

    public AreaActividadeDTO(){}

    public AreaActividadeDTO(String codigo, String descBreve, String descDetalhada){
        setCodigo(codigo);
        setDescBreve(descBreve);
        setDescDetalhada(descDetalhada);
    }

    public AreaActividadeDTO(AreaActividadeDTO areaActividadeDTO){
        setCodigo(areaActividadeDTO.codigo);
        setDescBreve(areaActividadeDTO.descBreve);
        setDescDetalhada(areaActividadeDTO.descDetalhada);
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IdInvalidoException("O Código da Área de Actividade é um campo obrigatório!");
        }
        this.codigo = codigo;
    }

    public void setDescBreve(String descBreve) {
        if (descBreve == null || descBreve.trim().isEmpty()) {
            throw new NomeInvalidoException("Descrição breve é obrigatória!");
        }
        if (descBreve.length() < 5) {
            throw new NomeInvalidoException("Descrição breve tem de ter pelo menos 5 caracteres!");
        }
        this.descBreve = descBreve;
    }

    public void setDescDetalhada(String descDetalhada) {
        if (descDetalhada == null || descDetalhada.trim().isEmpty()) {
            throw new NomeInvalidoException("Descrição detalhada é obrigatória!");
        }
        if (descDetalhada.length() < 10) {
            throw new NomeInvalidoException("Descrição detalhada tem de ter pelo menos 10 caracteres!");
        }
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
