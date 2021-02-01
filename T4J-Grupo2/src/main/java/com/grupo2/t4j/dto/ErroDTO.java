package com.grupo2.t4j.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "erro")

public class ErroDTO {

    @JacksonXmlProperty(localName = "mensagem")
    private String mensagemErro;

    public ErroDTO() {
    }

    public ErroDTO(Exception exception) {
        mensagemErro = exception.getMessage();
        //exception.printStackTrace();
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }
}
