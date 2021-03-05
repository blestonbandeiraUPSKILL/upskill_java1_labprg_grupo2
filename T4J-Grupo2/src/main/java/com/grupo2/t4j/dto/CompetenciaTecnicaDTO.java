package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.AreaActividadeInexistenteException;
import com.grupo2.t4j.exception.CodigoInvalidoException;
import com.grupo2.t4j.exception.DescricaoInvalidaException;

@JsonPropertyOrder({"codigo", "descricaoBreve", "descricaoDetalhada", "codigoAreaActividade"})

public class CompetenciaTecnicaDTO {

    @JacksonXmlProperty(localName = "codigo")
    private String codigo;

    @JacksonXmlProperty(localName = "descricaoBreve")
    private String descricaoBreve;

    @JacksonXmlProperty(localName = "descricaoDetalhada")
    private String descricaoDetalhada;

    @JacksonXmlProperty(localName = "codigoAreaActividade")
    private String codigoAreaActividade;

    public CompetenciaTecnicaDTO() {
    }

    public CompetenciaTecnicaDTO(String codigo, String descricaoBreve, String descricaoDetalhada) {
        setCodigo(codigo);
        setDescricaoBreve(descricaoBreve);
        setDescricaoDetalhada(descricaoDetalhada);
    }

    public CompetenciaTecnicaDTO(CompetenciaTecnicaDTO competenciaTecnicaDTO) {
        setCodigo(competenciaTecnicaDTO.codigo);
        setDescricaoBreve(competenciaTecnicaDTO.descricaoBreve);
        setDescricaoDetalhada(competenciaTecnicaDTO.descricaoDetalhada);
        setCodigoAreaActividade(competenciaTecnicaDTO.codigoAreaActividade);
    }

    public CompetenciaTecnicaDTO(String codigo, String descricaoBreve, String descricaoDetalhada, String codigoAreaActividade) {
        setCodigo(codigo);
        setDescricaoBreve(descricaoBreve);
        setDescricaoDetalhada(descricaoDetalhada);
        setCodigoAreaActividade(codigoAreaActividade);
    }

    public void setCodigo(String codigo) throws CodigoInvalidoException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new CodigoInvalidoException("Deve introduzir um código válido!");
        } else {
            this.codigo = codigo;
        }
    }

    public void setDescricaoBreve(String descricaoBreve) throws DescricaoInvalidaException {
        if (descricaoBreve == null || descricaoBreve.trim().isEmpty()) {
            throw new DescricaoInvalidaException("Deve introduzir uma descrição breve válida!");
        } else {
            this.descricaoBreve = descricaoBreve;
        }
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) throws DescricaoInvalidaException {
        if (descricaoDetalhada == null || descricaoDetalhada.trim().isEmpty()) {
            throw new DescricaoInvalidaException("Deve introduzir uma descrição detalhada válida!");
        } else {
            this.descricaoDetalhada = descricaoDetalhada;
        }
    }

    public void setCodigoAreaActividade(String codigoAreaActividade) {
        if (codigoAreaActividade != null) {
            this.codigoAreaActividade = codigoAreaActividade;
        } else {
            throw new AreaActividadeInexistenteException("A área de actividade não existe");
        }
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


}
