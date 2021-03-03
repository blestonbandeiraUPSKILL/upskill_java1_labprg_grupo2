package com.t4jws.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;



@JsonRootName("erro")
public class ErroDTO {
    @JsonProperty("mensagem")
    private String mensagemErro;
    public ErroDTO(Exception e) {
        mensagemErro = e.getMessage();
        e.printStackTrace();
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
