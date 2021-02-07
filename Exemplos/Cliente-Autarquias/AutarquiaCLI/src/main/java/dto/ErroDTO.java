/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 *
 * @author blest
 */

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
