/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.exception.AreaActividadeInexistenteException;
import com.grupo2.t4j.exception.CodigoInvalidoException;
import com.grupo2.t4j.exception.DescricaoInvalidaException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author acris
 */
public class CompetenciaTecnica implements Serializable {

    /**
     * O codigo da competencia tencica
     */
    private String codigo;

    /**
     * A descricao breve da competencia tecnica
     */
    private String descricaoBreve;

    /**
     * A descricao detalhada da competencia tecnica
     */
    private String descricaoDetalhada;

    /**
     * A area de actividade a que a competencia tecnica se refere
     */
    private AreaActividade areaActividade;

    /**
     * A competencia tecnica caracterizada
     */
    private CaracterizacaoCT cct;

    private Categoria categoria;
    
    private static List<GrauProficiencia> grausProficienciaAplicaveis = new ArrayList<>();

    /**
     * Construtor CompetenciaTecnica Completo
     *
     * @param codigo
     * @param descricaoBreve
     * @param descricaoDetalhada
     *
     */
    public CompetenciaTecnica(String codigo, String descricaoBreve, String descricaoDetalhada, AreaActividade areaActividade, CaracterizacaoCT cct) {
        setCodigo(codigo);
        setDescricaoBreve(descricaoBreve);
        setDescricaoDetalhada(descricaoDetalhada);
        setAreaActividade(areaActividade);
        setCct(cct);
    }

    /**
     * Construtor CompetenciaTecnica
     *
     * @param codigo
     * @param descricaoBreve
     * @param descricaoDetalhada
     *
     */
    public CompetenciaTecnica(String codigo, String descricaoBreve, String descricaoDetalhada) {
        setCodigo(codigo);
        setDescricaoBreve(descricaoBreve);
        setDescricaoDetalhada(descricaoDetalhada);
    }

    /**
     * Construtor Competencia Tecnica
     *
     * @param compTec
     */
    public CompetenciaTecnica(CompetenciaTecnica compTec) {
        setCodigo(compTec.codigo);
        setDescricaoBreve(compTec.descricaoBreve);
        setDescricaoDetalhada(compTec.descricaoDetalhada);
    }

    /**
     * Construtor competencia tecnica por parametros
     *
     * @param codigo
     * @param descricaoBreve
     * @param descricaoDetalhada
     * @param at
     */
    public CompetenciaTecnica(String codigo, String descricaoBreve, String descricaoDetalhada, AreaActividade at) {
        setCodigo(codigo);
        setDescricaoBreve(descricaoBreve);
        setDescricaoDetalhada(descricaoDetalhada);   
        this.areaActividade = at;
    }

    /**
     * devolve o codigo da competencia tecnica
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Atualiza e valida o codigo da competencia tecnica
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) throws CodigoInvalidoException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new CodigoInvalidoException("Deve introduzir um código válido!");
        } else {
            this.codigo = codigo;
        }
    }

    /**
     * Devolve a descricao breve da competencia tecnica
     * @return the descricaoBreve
     */
    public String getDescricaoBreve() {
        return descricaoBreve;
    }

    /**
     * Atualiza e valida a descricao breve da competencia tecnica
     * @param descricaoBreve the descricaoBreve to set
     */
    public void setDescricaoBreve(String descricaoBreve) throws DescricaoInvalidaException {
        if (descricaoBreve == null || descricaoBreve.trim().isEmpty()) {
            throw new DescricaoInvalidaException("Deve introduzir uma descrição breve válida!");
        } else {
            this.descricaoBreve = descricaoBreve;
        }
    }

    /**
     * Devolve a descricao detalhada da competencia tecnica
     * @return the descricaoDetalhada
     */
    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    /**
     * Atualiza e valida a descricao detalhada da competencia tecnica
     * @param descricaoDetalhada the descricaoDetalhada to set
     */
    public void setDescricaoDetalhada(String descricaoDetalhada) throws DescricaoInvalidaException {
        if (descricaoDetalhada == null || descricaoDetalhada.trim().isEmpty()) {
            throw new DescricaoInvalidaException("Deve introduzir uma descrição detalhada válida!");
        } else {
            this.descricaoDetalhada = descricaoDetalhada;
        }
    }

    /**
     * Devolve a caracterizacao da competencia tecnica
     * @return the cct
     */
    public CaracterizacaoCT getCct() {
        return cct;
    }

    /**
     * Atualiza a caracterizacao da competencia tecnica
     * @param cct the cct to set
     */
    public void setCct(CaracterizacaoCT cct) {
        this.cct = cct;
    }

    /**
     * Devolve a area de actividade a que se refere a competencia tecnica
     * 
     * @return the areaActividade
     */
    public AreaActividade getAreaActividade() {
        return areaActividade;
    }

    /**
     * Atualiza a area de atividade a que se refere a competencia tecnica
     * 
     * @param areaActividade the areaActividade to set
     */
    public void setAreaActividade(AreaActividade areaActividade) {
        if (areaActividade != null) {
            this.areaActividade = areaActividade;
        } else {
            throw new AreaActividadeInexistenteException("A área de actividade não existe");
        }
    }

    /**
     * Representacao textual da classe competencia tecnica
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("ID: %s; Descrição breve: %s; Descrição detalhada: %s.", codigo, descricaoBreve, descricaoDetalhada);
    }

    @Override
    public boolean equals(Object competenciaTecnica) {
        if (this == competenciaTecnica)
            return true;
        if (competenciaTecnica == null || getClass() != competenciaTecnica.getClass())
            return false;
        CompetenciaTecnica that = (CompetenciaTecnica) competenciaTecnica;
        return Objects.equals(codigo, that.codigo)
                && Objects.equals(descricaoBreve, that.descricaoBreve)
                && Objects.equals(descricaoDetalhada, that.descricaoDetalhada)
                && Objects.equals(areaActividade, that.areaActividade)
                && Objects.equals(cct, that.cct)
                && Objects.equals(categoria, that.categoria);
    }
    
    public static List<GrauProficiencia> getGrausAplicaveis() {
        return new ArrayList<GrauProficiencia>(grausProficienciaAplicaveis);
    }
}
