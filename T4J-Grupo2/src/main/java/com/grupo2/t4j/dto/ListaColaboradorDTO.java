package com.grupo2.t4j.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "colaboradores")

public class ListaColaboradorDTO {

    @JacksonXmlElementWrapper(useWrapping = false)

    @JacksonXmlProperty(localName = "colaborador")
    private ArrayList<ColaboradorDTO> colaboradores;

    public ListaColaboradorDTO() {
    }

    public ArrayList<ColaboradorDTO> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(ArrayList<ColaboradorDTO> colaboradores) {
        this.colaboradores = colaboradores;
    }
}
