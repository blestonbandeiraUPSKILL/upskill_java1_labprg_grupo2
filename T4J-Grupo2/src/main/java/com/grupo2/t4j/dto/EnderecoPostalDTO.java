package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.NomeInvalidoException;

public class EnderecoPostalDTO {

    private int idEnderecoPostal;
    private String arruamento;
    private String numeroPorta;
    private String localidade;
    private String codigoPostal;

    public EnderecoPostalDTO() {
    }

    public EnderecoPostalDTO(int idEnderecoPostal, String arruamento, String numeroPorta, String localidade, String codigoPostal) {
        this.idEnderecoPostal = idEnderecoPostal;
        this.arruamento = arruamento;
        this.numeroPorta = numeroPorta;
        this.localidade = localidade;
        this.codigoPostal = codigoPostal;
    }

    public int getIdEnderecoPostal() {
        return idEnderecoPostal;
    }

    public String getArruamento() {
        return arruamento;
    }

    public String getPorta() {
        return numeroPorta;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

}
