package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.CompetenciaTecnicaInexistenteException;
import com.grupo2.t4j.exception.DescricaoInvalidaException;
import com.grupo2.t4j.exception.GrauInvalidoException;
import com.grupo2.t4j.exception.IdInvalidoException;
import com.grupo2.t4j.model.GrauProficiencia;

@JsonPropertyOrder({"idGrauProficiencia", "grau", "designacao", "codigoCompetenciaTecnica"})

public class GrauProficienciaDTO {

    @JacksonXmlProperty(localName = "idGrauProficiencia")
    private int idGrauProficiencia;

    @JacksonXmlProperty(localName = "grau")
    private int grau;

    @JacksonXmlProperty(localName = "designacao")
    private String designacao;

    @JacksonXmlProperty(localName = "codigoCompetenciaTecnica")
    private String codigoCompetenciaTecnica;

    public GrauProficienciaDTO() {
    }

    public GrauProficienciaDTO(GrauProficienciaDTO grauProficienciaDTO) {
        setGrau(grauProficienciaDTO.grau);
        setDesignacao(grauProficienciaDTO.designacao);
    }

    public GrauProficienciaDTO(int grau, String designacao) {
        setGrau(grau);
        setDesignacao(designacao);


    }
    public GrauProficienciaDTO(int idGrauProficiencia, int grau, String designacao, String codigoCompetenciaTecnica){
        this.idGrauProficiencia = idGrauProficiencia;
        setGrau(grau);
        setDesignacao(designacao);
        setCodigoCompetenciaTecnica(codigoCompetenciaTecnica);
    }

    public GrauProficienciaDTO(int grau, String designacao, String codigoCompetenciaTecnica) {
        setGrau(grau);
        setDesignacao(designacao);
        setCodigoCompetenciaTecnica(codigoCompetenciaTecnica);
    }

    public void setIdGrauProficiencia(int idGrauProficiencia) {
        if (idGrauProficiencia > 0) {
            this.idGrauProficiencia = idGrauProficiencia;
        }
        else {
            throw new IdInvalidoException("O id do grau de proficiência é inválido");
        }
    }

    public void setGrau(int grau) {
        if (grau < 0) {
            throw new GrauInvalidoException("O valor do grau é inválido.");
        }
        this.grau = grau;
    }

    public void setDesignacao(String designacao) {
        if (designacao == null || designacao.trim().isEmpty()) {
            throw new DescricaoInvalidaException("A descrição é inválida.");
        }
        this.designacao = designacao;
    }

    private void setCodigoCompetenciaTecnica(String codigoCompetenciaTecnica) {
        if(codigoCompetenciaTecnica != null) {
            this.codigoCompetenciaTecnica = codigoCompetenciaTecnica;
        }
        else {
            throw new CompetenciaTecnicaInexistenteException("A competência técnica não existe");
        }
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
