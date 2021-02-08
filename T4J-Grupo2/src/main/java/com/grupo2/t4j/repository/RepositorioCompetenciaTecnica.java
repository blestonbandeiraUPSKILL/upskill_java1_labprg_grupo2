/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.repository;

import com.grupo2.t4j.exception.CompetenciaTecnicaDuplicadaException;
import com.grupo2.t4j.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
public class RepositorioCompetenciaTecnica implements Serializable{

    /**
     * Atributos da classe Singletone RepositorioCompetenciaTecnica
     */
    public static RepositorioCompetenciaTecnica instance;
    List<CompetenciaTecnica> listaCompTecnicas;

    /**
     * Construtor da classe Singleton RepositorioCompetenciaTecnica
     */
    private RepositorioCompetenciaTecnica() {
        listaCompTecnicas = new ArrayList<>();
    }

    /**
     * Devolve ou cria uma instância estática de RepositorioCompetenciaTecnica
     *
     * @return a instance existente ou criada
     */
    public static RepositorioCompetenciaTecnica getInstance() {
        if (instance == null) {
            instance = new RepositorioCompetenciaTecnica();
        }
        return instance;
    }

    /**
     * Adiciona uma competencia tecnica a lista de Competencias Tecnicas
     *
     * @param competenciaTecnica
     * @throws com.grupo2.t4j.exception.CompetenciaTecnicaDuplicadaException
     * @throws CompetenciaTecnicaDuplicadaException
     */
    public boolean addCompetenciaTecnica(CompetenciaTecnica competenciaTecnica) throws CompetenciaTecnicaDuplicadaException {
        CompetenciaTecnica ct = getCompetenciaTecnicaByCodigo(competenciaTecnica.getCodigo());
        if (ct == null) {
            this.listaCompTecnicas.add(competenciaTecnica);
            return true;
        } else {
            throw new CompetenciaTecnicaDuplicadaException(ct.getCodigo() + ": Competencia Tecnica já existe");
        }
    }

    public CompetenciaTecnica novaCompetenciaTecnica(String codigo, String descBreve,
                                                     String descDetalhada, AreaActividade areaActividade) {
        return new CompetenciaTecnica(codigo, descBreve, descDetalhada, areaActividade);
    }

    public boolean addCompetenciaTecnica(String codigo,
                                         AreaActividade at,
                                         String descricaoBreve,
                                         String descricaoDetalhada){
        CompetenciaTecnica ct = getCompetenciaTecnicaByCodigo(codigo);
        if (ct == null) {
            CompetenciaTecnica compTec = new CompetenciaTecnica(codigo, descricaoBreve, descricaoDetalhada, at);
            this.listaCompTecnicas.add(compTec);
            return true;
        } else {
            throw new CompetenciaTecnicaDuplicadaException(ct.getCodigo() + ": Competencia Tecnica já existe");
        }
    }

    /**
     * Atualiza a lista de Competencias Tecnicas
     *
     * @param listaCompTecnicas
     */
    public void setListaCompTecnicas(List<CompetenciaTecnica> listaCompTecnicas) {
        this.listaCompTecnicas = listaCompTecnicas;
    }

    /**
     * Devolve a lista de competencias tecnicas
     *
     * @return
     */
    public List<CompetenciaTecnica> getCompetenciasTecnicas() {

        return new ArrayList<CompetenciaTecnica>(listaCompTecnicas);
    }

    /**
     * Devolve uma competencia tecnica de acordo com o seu codigo
     *
     * @param codigo
     * @return
     */
    public CompetenciaTecnica getCompetenciaTecnicaByCodigo(String codigo) {
        CompetenciaTecnica compTec = null;
        for (int i = 0; i < this.listaCompTecnicas.size(); i++) {
            compTec = this.listaCompTecnicas.get(i);
            if (compTec.getCodigo().equals(codigo)) {
                return compTec;

            }
        }
        return null;
    }

    /**
     * Devolve uma lista de competencias tecnicas por area de actividade
     *
     * @param at
     * @return
     */
    public ArrayList<CompetenciaTecnica> getCompetenciasTecnicasByAreaActividade(AreaActividade at) {
        ArrayList<CompetenciaTecnica> compTecPorAt = new ArrayList<>();

        for (CompetenciaTecnica ct : getCompetenciasTecnicasByAreaActividade(at)) {
            if (ct.getAreaActividade().equals(at)) {
                compTecPorAt.add(ct);
            }
        }

        return compTecPorAt;
    }
    
    public int adicionarListaCompetenciasTecnicas(RepositorioCompetenciaTecnica outraListaCompetenciasTecnicas) {
        int totalCompetenciasAdicionadas = 0;
        
        for (CompetenciaTecnica ct : outraListaCompetenciasTecnicas.listaCompTecnicas) {
            boolean areaActividadeAdicionada = addCompetenciaTecnica(ct);
            if (areaActividadeAdicionada) {
                totalCompetenciasAdicionadas++;
            }
        }
        return totalCompetenciasAdicionadas;
    }

   
}
