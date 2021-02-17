/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.inmemory;

import com.grupo2.t4j.exception.CompetenciaTecnicaDuplicadaException;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.persistence.RepositorioCompetenciaTecnica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
public class RepositorioCompetenciaTecnicaInMemory implements Serializable, RepositorioCompetenciaTecnica {

    /**
     * Atributos da classe Singletone RepositorioCompetenciaTecnica
     */
    public static RepositorioCompetenciaTecnicaInMemory instance;
    List<CompetenciaTecnica> listaCompTecnicas;

    /**
     * Construtor da classe Singleton RepositorioCompetenciaTecnica
     */
    RepositorioCompetenciaTecnicaInMemory() {
        listaCompTecnicas = new ArrayList<>();
    }

    /**
     * Devolve ou cria uma instância estática de RepositorioCompetenciaTecnica
     *
     * @return a instance existente ou criada
     */
    public static RepositorioCompetenciaTecnicaInMemory getInstance() {
        if (instance == null) {
            instance = new RepositorioCompetenciaTecnicaInMemory();
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
        CompetenciaTecnica ct = findByCodigo(competenciaTecnica.getCodigo());
        if (ct == null) {
            this.listaCompTecnicas.add(competenciaTecnica);
            return true;
        } else {
            throw new CompetenciaTecnicaDuplicadaException(ct.getCodigo() + ": Competencia Tecnica já existe");
        }
    }

    @Override
    public void save(String codigo, String descBreve, String descDetalhada, AreaActividade areaActividade) {
        CompetenciaTecnica ct = findByCodigo(codigo);
        if (ct == null) {
            CompetenciaTecnica compTec = new CompetenciaTecnica(codigo, descBreve, descDetalhada, areaActividade);
            this.listaCompTecnicas.add(compTec);
        }
        else {
            throw new CompetenciaTecnicaDuplicadaException(ct.getCodigo() + ": Competencia Tecnica já existe");
        }
    }

    @Override
    public List<CompetenciaTecnica> getAll() {
        return new ArrayList<CompetenciaTecnica>(listaCompTecnicas);
    }

    @Override
    public CompetenciaTecnica findByCodigo(String codigo) {
        for (int i = 0; i < this.listaCompTecnicas.size(); i++) {
            CompetenciaTecnica compTec = this.listaCompTecnicas.get(i);
            if (compTec.getCodigo().equals(codigo)) {
                return compTec;

            }
        }
        return null;
    }

    @Override
    public ArrayList<CompetenciaTecnica> findByAreaActividade(AreaActividade at) {
        ArrayList<CompetenciaTecnica> compTecPorAt = new ArrayList<>();

        for (CompetenciaTecnica ct : listaCompTecnicas) {
            if (ct.getAreaActividade().equals(at)) {
                compTecPorAt.add(ct);
            }
        }
        return compTecPorAt;
    }

    public int adicionarListaCompetenciasTecnicas(RepositorioCompetenciaTecnicaInMemory outraListaCompetenciasTecnicas) {
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
