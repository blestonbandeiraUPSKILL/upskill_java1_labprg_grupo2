package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.domain.Obrigatoriedade;

public class CaracterizacaoCTDTO {

    private int idCaracterizacao;
    private String codigoCategoria;
    private int codigoGP;
    private Obrigatoriedade obrigatoriedade;

    public CaracterizacaoCTDTO (){
    }

    public CaracterizacaoCTDTO(int idCaracterizacao, String codigoCategoria,
                            int codigoGP, Obrigatoriedade obrigatoriedade){
        this.idCaracterizacao = idCaracterizacao;
        this.codigoCategoria = codigoCategoria;
        this.codigoGP = codigoGP;
        this.obrigatoriedade = obrigatoriedade;
    }

    public int getIdCaracterizacao() {
        return idCaracterizacao;
    }

    public int getCodigoGP() {
        return codigoGP;
    }

    public Obrigatoriedade getObrigatoriedade() {
        return obrigatoriedade;
    }

    public String getCodigoCategoria() {
        return codigoCategoria;
    }

}
