package com.grupo2.t4j.dto;

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
