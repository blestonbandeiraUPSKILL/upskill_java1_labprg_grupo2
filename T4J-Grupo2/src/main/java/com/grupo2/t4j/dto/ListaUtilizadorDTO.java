package com.grupo2.t4j.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "utilizadores")

public class ListaUtilizadorDTO {

    @JacksonXmlElementWrapper(useWrapping = false)

    @JacksonXmlProperty(localName = "utilizador")
    private ArrayList<UtilizadorDTO> utilizadores;

    public ListaUtilizadorDTO() {
    }

    public ArrayList<UtilizadorDTO> getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(ArrayList<UtilizadorDTO> utilizadores) {
        this.utilizadores = utilizadores;
    }
}
