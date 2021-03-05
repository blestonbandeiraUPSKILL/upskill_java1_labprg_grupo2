package com.t4jws.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;




public class ErroDTO {

    private String mensagemErro;

    public ErroDTO(Exception exception) {
        mensagemErro = exception.getMessage();
        exception.printStackTrace();
    }

    public ErroDTO() {
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }
}
