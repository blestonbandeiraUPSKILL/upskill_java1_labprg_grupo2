package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.DescricaoInvalidaException;
import com.grupo2.t4j.exception.IdInvalidoException;
import com.grupo2.t4j.exception.NomeInvalidoException;

public class TipoRegimentoDTO {


    private int idTipoRegimento;
    private String designacao;
    private String descricaoRegras;

    public TipoRegimentoDTO(){}

    public TipoRegimentoDTO(int idTipoRegimento, String designacao,
                         String descricaoRegras){
        this.idTipoRegimento = idTipoRegimento;
        this.designacao = designacao;
        this.descricaoRegras = descricaoRegras;
    }

    public int getIdTipoRegimento(){
        return idTipoRegimento;
    }

    public String getDesignacao(){
        return designacao;
    }

    public String getDescricaoRegras(){
        return descricaoRegras;
    }

    @Override
    public String toString() {
        return String.format("ID: %-12s |Designação: %-20s", idTipoRegimento,
                designacao);
    }
}
