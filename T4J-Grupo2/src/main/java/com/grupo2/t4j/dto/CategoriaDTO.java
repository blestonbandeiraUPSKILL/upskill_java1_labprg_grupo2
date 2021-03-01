package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.AreaActividadeInexistenteException;
import com.grupo2.t4j.exception.CodigoInvalidoException;
import com.grupo2.t4j.exception.DescricaoInvalidaException;

@JsonPropertyOrder({"codigoCategoria", "descBreve", "descDetalhada", "codigoAreaActividade"})

public class CategoriaDTO {

    @JacksonXmlProperty(localName = "codigoCategoria")
    private String codigoCategoria;

    @JacksonXmlProperty(localName = "descBreve")
    private String descBreve;

    @JacksonXmlProperty(localName = "descDetalhada")
    private String descDetalhada;

    @JacksonXmlProperty(localName = "codigoAreaActividade")
    private String codigoAreaActividade;

    public CategoriaDTO() {
    }

    public CategoriaDTO (String codigoCategoria, String descBreve, String descDetalhada, String codigoAreaActividade
                      //,List<CaracterizacaoCT> caracterizacaoCTS
                         ){
        setCodigo(codigoCategoria);
        setDescBreve(descBreve);
        setDescDetalhada(descDetalhada);
        this.codigoAreaActividade = codigoAreaActividade;
        //setCompTecnicasCaracter(caracterizacaoCTS);
    }

    public CategoriaDTO (CategoriaDTO categoriaDTO){
        setCodigo(categoriaDTO.codigoCategoria);
        setDescBreve(categoriaDTO.descBreve);
        setDescDetalhada(categoriaDTO.descDetalhada);
        setCodigoAreaActividade(categoriaDTO.codigoAreaActividade);
        //setCompTecnicasCaracter(categoriaDTO.caracterizacaoCTS);
    }

    public void setCodigo(String codigoCategoria) {
        if (codigoCategoria == null || codigoCategoria.trim().isEmpty()) {
            throw new CodigoInvalidoException("Deve introduzir um código válido!");
        } else {
            this.codigoCategoria = codigoCategoria;
        } this.codigoCategoria = codigoCategoria;
    }

    public void setDescBreve(String descBreve) {
        if (descBreve == null || descBreve.trim().isEmpty()) {
            throw new DescricaoInvalidaException("Deve introduzir uma descrição breve válida!");
        } else {
            this.descBreve = descBreve;
        }
    }

    public void setDescDetalhada(String descDetalhada) {
        if(descDetalhada == null || descDetalhada.trim().isEmpty()) {
            throw new DescricaoInvalidaException("Deve introduzir uma descrição detalhada válida!");
        }
        else {
            this.descDetalhada = descDetalhada;
        }
    }

    public void setCodigoAreaActividade(String codigoAreaActividade) {
        if(codigoAreaActividade != null) {
            this.codigoAreaActividade = codigoAreaActividade;
        }
        else {
            throw new AreaActividadeInexistenteException("A área de actividade não existe");
        }
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
