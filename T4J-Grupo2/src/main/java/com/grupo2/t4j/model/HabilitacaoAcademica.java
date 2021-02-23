/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

/**
 *
 * @author CAD
 */
import com.grupo2.t4j.exception.*;

public class HabilitacaoAcademica {
    
    /**
     * O id da Habilitação Acadêmica do Freelancer
     */
    private int idHabilitacao;
    
    /**
     * O grau referente à habilitação acadêmica.
     */
    private String grau;
    
    /**
     * A designação do curso que conferiu a habilitação acadêmica.
     */
    private String designacaoCurso;
    
    /**
     * O nome da instituição onde ocorreu o curso que conferiu a habilitação acadêmica.
     */
    private String nomeInstituicao;
    /**
     * A média do curso em que se adquiriu a habilitação acadêmica.
     */
    private double mediaCurso;
    
    /**
     * Construtor vazio da classe Habilitação Acadêmica
     */
    public HabilitacaoAcademica(){
        
    }
    /**
     * Construtor completo da classe Habilitação Acadêmica
     * @param idHabilitacao - o id da Habilitação Acadêmica do Freelancer
     * @param grau - grau referente à habilitação acadêmica.
     * @param designacaoCurso - a designação do curso que conferiu a habilitação acadêmica.
     * @param nomeInstituicao - nome da instituição onde ocorreu o curso que conferiu a habilitação acadêmica.
     * @param mediaCurso - média do curso em que se adquiriu a habilitação acadêmica.
     */
    public HabilitacaoAcademica(int idHabilitacao, String grau, String designacaoCurso,
           String nomeInstituicao, double mediaCurso){
        setIdHabilitacao(idHabilitacao);
        setGrau(grau);
        setDesignacaoCurso(designacaoCurso);
        setNomeInstituicao(nomeInstituicao);
        setMediaCurso(mediaCurso);
    }
    
    /**
     * Construtor completo da classe Habilitação Acadêmica
     * @param grau - grau referente à habilitação acadêmica.
     * @param designacaoCurso - a designação do curso que conferiu a habilitação acadêmica.
     * @param nomeInstituicao - nome da instituição onde ocorreu o curso que conferiu a habilitação acadêmica.
     * @param mediaCurso - média do curso em que se adquiriu a habilitação acadêmica.
     */
    public HabilitacaoAcademica(String grau, String designacaoCurso,
           String nomeInstituicao, double mediaCurso){
        setGrau(grau);
        setDesignacaoCurso(designacaoCurso);
        setNomeInstituicao(nomeInstituicao);
        setMediaCurso(mediaCurso);
    }
    
    /**
     * Construtor da classe Habilitação Acadêmica que recebe um tipo da mesma classe
     * @param habilitacaoAcademica
     */
    public HabilitacaoAcademica(HabilitacaoAcademica habilitacaoAcademica){
        setIdHabilitacao(habilitacaoAcademica.idHabilitacao);
        setGrau(habilitacaoAcademica.grau);
        setDesignacaoCurso(habilitacaoAcademica.designacaoCurso);
        setNomeInstituicao(habilitacaoAcademica.nomeInstituicao);
        setMediaCurso(habilitacaoAcademica.mediaCurso);
    }
    
    /**
     * Define o id referente à habilitação acadêmica.
     * @param idHabilitacao
     */
    public void setIdHabilitacao(int idHabilitacao){
        this.idHabilitacao = idHabilitacao;
    }
    
    /**
     * Define o grau referente à habilitação acadêmica.
     * @param grau
     */
    public void setGrau(String grau){
        if (grau == null || grau.trim().isEmpty()) {
            throw new NomeInvalidoException("O grau informado é inválido!");
        }
        this.grau = grau;
    }
    
    /**
     * Define a designação do curso que conferiu a habilitação acadêmica.
     * @param designacaoCurso
     */
    public void setDesignacaoCurso(String designacaoCurso){
        if (designacaoCurso == null || designacaoCurso.trim().isEmpty()) {
            throw new NomeInvalidoException("A designação de curso informada é inválida!");
        }
        this.designacaoCurso = designacaoCurso;
    }
    
    /**
     * Define nome da instituição onde ocorreu o curso que conferiu a habilitação acadêmica.
     * @param nomeInstituicao
     */
    public void setNomeInstituicao(String nomeInstituicao){
        if (nomeInstituicao == null || nomeInstituicao.trim().isEmpty()) {
            throw new NomeInvalidoException("O grau informado é inválido!");
        }
        this.nomeInstituicao = nomeInstituicao;
    }
    
    /**
     * Define média do curso em que se adquiriu a habilitação acadêmica.
     * @param mediaCurso
     */
    public void setMediaCurso(double mediaCurso){
        if (mediaCurso > 0) {
            this.mediaCurso = mediaCurso;
        }        
        throw new IllegalArgumentException("A média do curso deve ser maior que zero!");
    }
    
    /**
     * Devolve o id referente à habilitação acadêmica.
     * @return idHabilitacao
     */
    public int getIdHabilitacao(){
        return idHabilitacao;
    }
    
    /**
     * Devolve o grau referente à habilitação acadêmica.
     * @return grau
     */
    public String getGrau(){
        return grau;
    }
    
    /**
     * Devolve a a designação do curso que conferiu a habilitação acadêmica.
     * @return designacaoCurso
     */
    public String getDesignacaoCurso(){
        return designacaoCurso;
    }
    
    /**
     * Devolve nome da instituição onde ocorreu o curso que conferiu a habilitação acadêmica.
     * @return nomeInstituicao
     */
    public String getNomeInstituicao(){
        return nomeInstituicao;
    }
    
    /**
     * Devolve a média do curso em que se adquiriu a habilitação acadêmica.
     * @return mediaCurso
     */
    public double getMediaCurso(){
        return mediaCurso;
    }
    
    /**
     * Representação textual da classe Habilitação Acadêmica em formato de exibição
     * @return grau da habilitação, designação do curso e média do curso
     */
    @Override
    public String toString(){
        return String.format("ID: %-12s |Grau: %-15s |Designação do Curso:%-30s "
                + "|Média do curso: %-5d",  idHabilitacao, 
                grau, designacaoCurso, mediaCurso);
    } 
    
    /**
     * Representação textual da classe Habilitação Acadêmica
     * @return grau da habilitação, designação do curso, nome da instituição e média
     * do curso
     */    
    public String toStringCompleta(){
        return String.format("id Habilitação: %s %nGrau: %s %nDesignação do Curso:%s "
                + "%nNome da Instituição: %s %nMédia do curso: %d",  idHabilitacao, 
                grau, designacaoCurso, nomeInstituicao, mediaCurso);
    }
}
