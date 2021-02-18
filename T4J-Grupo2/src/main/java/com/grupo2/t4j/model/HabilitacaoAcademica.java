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

    private String grau;
    
    private String designacaoCurso;
    
    private String nomeInstituicao;
    
    private double mediaCurso;
    
    public HabilitacaoAcademica(String grau, String designacaoCurso,
           String nomeInstituicao, double mediaCurso){
        
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
        throw new IllegalArgumentException("A média do curso deve ser maior que zero!");
    } 
    
    
    
}
