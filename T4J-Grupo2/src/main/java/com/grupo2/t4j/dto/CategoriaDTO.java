package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.domain.CaracterizacaoCT;
import com.grupo2.t4j.exception.AreaActividadeInexistenteException;
import com.grupo2.t4j.exception.CodigoInvalidoException;
import com.grupo2.t4j.exception.DescricaoInvalidaException;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDTO {

    private String codigoCategoria;
    private String descBreve;
    private String descDetalhada;
    private String codigoAreaActividade;
    private List<CaracterizacaoCTDTO> caracterizacaoCTSDTO = new ArrayList<>();

    public CategoriaDTO() {
    }

    public CategoriaDTO (String codigoCategoria, String descBreve, String descDetalhada,
                         String codigoAreaActividade, List<CaracterizacaoCTDTO> caracterizacaoCTSDTO){
        this.codigoCategoria = codigoCategoria;
        this.descBreve = descBreve;
        this.descDetalhada = descDetalhada;
        this.codigoAreaActividade = codigoAreaActividade;
        this.caracterizacaoCTSDTO = caracterizacaoCTSDTO;
    }

    public CategoriaDTO (String codigoCategoria, String descBreve, String descDetalhada,
                         String codigoAreaActividade){
        this.codigoCategoria = codigoCategoria;
        this.descBreve = descBreve;
        this.descDetalhada = descDetalhada;
        this.codigoAreaActividade = codigoAreaActividade;

    }

    public String getCodigoCategoria() {
        return codigoCategoria;
    }

    public String getDescDetalhada() {
        return descDetalhada;
    }

    public String getDescBreve() {
        return descBreve;
    }

    public String getCodigoAreaActividade() {
        return codigoAreaActividade;
    }



}
