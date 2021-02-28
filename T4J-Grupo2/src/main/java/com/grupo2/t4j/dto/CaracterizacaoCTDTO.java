package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.model.Obrigatoriedade;

@JsonPropertyOrder({"idCaracterizacao", "codigoCategoria", "codigoGP", "obrigatoriedade"})

public class CaracterizacaoCTDTO {

    @JacksonXmlProperty(localName = "idCaracterizacao")
    private int idCaracterizacao;

    @JacksonXmlProperty(localName = "codigoCategoria")
    private String codigoCategoria;

    @JacksonXmlProperty(localName = "codigoGP")
    private int codigoGP;

    @JacksonXmlProperty(localName = "obrigatoriedade")
    private ObrigatoriedadeDTO obrigatoriedadeDTO;

    public CaracterizacaoCTDTO (){
    }

    public CaracterizacaoCTDTO(CaracterizacaoCTDTO caracterizacaoCTDTO){
        setIdCaracterizacao(caracterizacaoCTDTO.idCaracterizacao);
        setCodigoCategoria(caracterizacaoCTDTO.codigoCategoria);
        setCodigoGP(caracterizacaoCTDTO.codigoGP);
        setObrigatoriedade(caracterizacaoCTDTO.obrigatoriedadeDTO);
    }

    public CaracterizacaoCTDTO(String codigoCategoria, int codigoGP,
                               ObrigatoriedadeDTO obrigatoriedadeDTO) {
        this.codigoCategoria = codigoCategoria;
        this.codigoGP = codigoGP;
        this.obrigatoriedadeDTO = obrigatoriedadeDTO;
    }

    public CaracterizacaoCTDTO(int idCaracterizacao, String codigoCategoria,
                            int codigoGP, ObrigatoriedadeDTO obrigatoriedadeDTO){
        this.idCaracterizacao = idCaracterizacao;
        setCodigoCategoria(codigoCategoria);
        setCodigoGP(codigoGP);
        setObrigatoriedade(obrigatoriedadeDTO);
    }

    public void setIdCaracterizacao(int idCaracterizacao) {
        this.idCaracterizacao = idCaracterizacao;
    }

    public void setCodigoCategoria(String codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public void setCodigoGP(int codigoGP) {
        this.codigoGP = codigoGP;
    }

    public void setObrigatoriedade(ObrigatoriedadeDTO obrigatoriedadeDTO) {
        this.obrigatoriedadeDTO = obrigatoriedadeDTO;
    }

    public int getIdCaracterizacao() {
        return idCaracterizacao;
    }

    public int getCodigoGP() {
        return codigoGP;
    }

    public ObrigatoriedadeDTO getObrigatoriedade() {
        return obrigatoriedadeDTO;
    }

    public String getCodigoCategoria() {
        return codigoCategoria;
    }

}
