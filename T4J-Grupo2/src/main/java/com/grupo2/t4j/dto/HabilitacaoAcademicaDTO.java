package com.grupo2.t4j.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.grupo2.t4j.exception.NomeInvalidoException;

@JsonPropertyOrder({"idHabilitacaoAcademica", "grau", "designacaoCurso",
        "nomeInstituicao", "mediaCurso"})

public class HabilitacaoAcademicaDTO {

    @JacksonXmlProperty(localName = "idHabilitacaoAcademica")
    private int idHabilitacaoAcademica;

    @JacksonXmlProperty(localName = "grau")
    private String grau;

    @JacksonXmlProperty(localName = "designacaoCurso")
    private String designacaoCurso;

    @JacksonXmlProperty(localName = "nomeInstituicao")
    private String nomeInstituicao;

    @JacksonXmlProperty(localName = "mediaCurso")
    private double mediaCurso;

    public HabilitacaoAcademicaDTO(){
    }

    public HabilitacaoAcademicaDTO(int idHabilitacao, String grau, String designacaoCurso,
                                String nomeInstituicao, double mediaCurso){
        setIdHabilitacao(idHabilitacao);
        setGrau(grau);
        setDesignacaoCurso(designacaoCurso);
        setNomeInstituicao(nomeInstituicao);
        setMediaCurso(mediaCurso);

    }

    public HabilitacaoAcademicaDTO(String grau, String designacaoCurso,
                                String nomeInstituicao, double mediaCurso){
        setGrau(grau);
        setDesignacaoCurso(designacaoCurso);
        setNomeInstituicao(nomeInstituicao);
        setMediaCurso(mediaCurso);
    }

    public HabilitacaoAcademicaDTO(HabilitacaoAcademicaDTO habilitacaoAcademicaDTO){
        setIdHabilitacao(habilitacaoAcademicaDTO.idHabilitacaoAcademica);
        setGrau(habilitacaoAcademicaDTO.grau);
        setDesignacaoCurso(habilitacaoAcademicaDTO.designacaoCurso);
        setNomeInstituicao(habilitacaoAcademicaDTO.nomeInstituicao);
        setMediaCurso(habilitacaoAcademicaDTO.mediaCurso);
    }

    public HabilitacaoAcademicaDTO(int idHabilitacao, String grau, String designacaoCurso, String nomeInstituicao, Double mediaCurso) {
        setIdHabilitacao(idHabilitacao);
        setGrau(grau);
        setDesignacaoCurso(designacaoCurso);
        setNomeInstituicao(nomeInstituicao);
        setMediaCurso(mediaCurso);
    }

    public void setIdHabilitacao(int idHabilitacaoAcademica){
        this.idHabilitacaoAcademica = idHabilitacaoAcademica;
    }

    public void setGrau(String grau){
        if (grau == null || grau.trim().isEmpty()) {
            throw new NomeInvalidoException("O grau informado é inválido!");
        }
        this.grau = grau;
    }

    public void setDesignacaoCurso(String designacaoCurso){
        if (designacaoCurso == null || designacaoCurso.trim().isEmpty()) {
            throw new NomeInvalidoException("A designação de curso informada é inválida!");
        }
        this.designacaoCurso = designacaoCurso;
    }

    public void setNomeInstituicao(String nomeInstituicao){
        if (nomeInstituicao == null || nomeInstituicao.trim().isEmpty()) {
            throw new NomeInvalidoException("O grau informado é inválido!");
        }
        this.nomeInstituicao = nomeInstituicao;
    }

    public void setMediaCurso(double mediaCurso){
        if (mediaCurso > 0) {
            this.mediaCurso = mediaCurso;
        }
        else{
            throw new IllegalArgumentException("A média do curso deve ser maior que zero!");
        }
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
