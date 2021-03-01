package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.DescricaoInvalidaException;
import com.grupo2.t4j.exception.IdInvalidoException;
import com.grupo2.t4j.exception.NomeInvalidoException;

@JsonPropertyOrder({"idTipoRegimento", "designacao", "descricaoRegras"})

public class TipoRegimentoDTO {

    @JacksonXmlProperty(localName = "idTipoRegimento")
    private int idTipoRegimento;

    @JacksonXmlProperty(localName = "designacao")
    private String designacao;

    @JacksonXmlProperty(localName = "descricaoRegras")
    private String descricaoRegras;

    public TipoRegimentoDTO(){}

    public TipoRegimentoDTO(int idTipoRegimento, String designacao,
                         String descricaoRegras){
        setIdTipoRegimento(idTipoRegimento);
        setDesignacao(designacao);
        setDescricaoRegras(descricaoRegras);
    }

    public TipoRegimentoDTO(TipoRegimentoDTO tipoRegimentoDTO){
        setIdTipoRegimento(tipoRegimentoDTO.idTipoRegimento);
        setDesignacao(tipoRegimentoDTO.designacao);
        setDescricaoRegras(tipoRegimentoDTO.descricaoRegras);
    }

    public void setIdTipoRegimento(int idTipoRegimento){
        if(idTipoRegimento > 0 ) {
            this.idTipoRegimento = idTipoRegimento;
        }
        else {
            throw new IdInvalidoException("O id do tipo de regimento não é válido.");
        }

    }

    public void setDesignacao(String designacao){
        if (designacao == null || designacao.trim().isEmpty()) {
            throw new DescricaoInvalidaException("A designação tem de ser preenchida.");
        }
        this.designacao = designacao;
    }

    public void setDescricaoRegras(String descricaoRegras){
        if (descricaoRegras == null || descricaoRegras.trim().isEmpty()) {
            throw new NomeInvalidoException("A descrição informada para as regras é inválida!");
        }
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

}
