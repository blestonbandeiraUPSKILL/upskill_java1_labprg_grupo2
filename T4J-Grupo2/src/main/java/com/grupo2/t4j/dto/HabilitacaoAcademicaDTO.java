package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.NomeInvalidoException;

public class HabilitacaoAcademicaDTO {

    private int idHabilitacaoAcademica;
    private String grau;
    private String designacaoCurso;
    private String nomeInstituicao;
    private double mediaCurso;

    public HabilitacaoAcademicaDTO(){
    }

    public HabilitacaoAcademicaDTO(int idHabilitacao, String grau, String designacaoCurso,
                                String nomeInstituicao, double mediaCurso){
        this.idHabilitacaoAcademica = idHabilitacao;
        this.grau = grau;
        this.designacaoCurso = designacaoCurso;
        this.nomeInstituicao = nomeInstituicao;
        this.mediaCurso = mediaCurso;
    }

    public int getIdHabilitacaoAcademica(){
        return idHabilitacaoAcademica;
    }

    public String getGrau(){
        return grau;
    }

    public String getDesignacaoCurso(){
        return designacaoCurso;
    }

    public String getNomeInstituicao(){
        return nomeInstituicao;
    }

    public double getMediaCurso(){
        return mediaCurso;
    }


}
