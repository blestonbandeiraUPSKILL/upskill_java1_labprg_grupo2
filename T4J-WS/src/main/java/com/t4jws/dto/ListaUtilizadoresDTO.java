package com.t4jws.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;


import java.util.ArrayList;

@JsonRootName("utilizadores")

public class ListaUtilizadoresDTO {

    @JsonUnwrapped(enabled = false)

    @JsonProperty("utilizador")
    private ArrayList<UtilizadorDTO> utilizadores;

    public ListaUtilizadoresDTO() {
    }

    public ArrayList<UtilizadorDTO> getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(ArrayList<UtilizadorDTO> utilizadores) {
        this.utilizadores = utilizadores;
    }
}
