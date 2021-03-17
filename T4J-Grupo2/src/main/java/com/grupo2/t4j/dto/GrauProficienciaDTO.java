package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.CompetenciaTecnicaInexistenteException;
import com.grupo2.t4j.exception.DescricaoInvalidaException;
import com.grupo2.t4j.exception.GrauInvalidoException;
import com.grupo2.t4j.exception.IdInvalidoException;

public class GrauProficienciaDTO {

    private int idGrauProficiencia;
    private int grau;
    private String designacao;
    private String codigoCompetenciaTecnica;

    public GrauProficienciaDTO() {
    }

    public GrauProficienciaDTO(int idGrauProficiencia, int grau, String designacao, String codigoCompetenciaTecnica){
        this.idGrauProficiencia = idGrauProficiencia;
        this.grau = grau;
        this.designacao = designacao;
        this.codigoCompetenciaTecnica = codigoCompetenciaTecnica;
    }

    public int getIdGrauProficiencia() {
        return idGrauProficiencia;
    }

    public int getGrau() {
        return grau;
    }

    public String getDesignacao() {
        return designacao;
    }

    public String getCodigoCompetenciaTecnica(){
        return codigoCompetenciaTecnica;
    }


}
