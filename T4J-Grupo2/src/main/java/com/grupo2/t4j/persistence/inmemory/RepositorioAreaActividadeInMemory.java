/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.inmemory;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.AreaActividadeDuplicadaException;
import com.grupo2.t4j.domain.AreaActividade;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geral
 */
public class RepositorioAreaActividadeInMemory implements Serializable, RepositorioAreaActividade {
    
    /**
     * Define uma instância estática do Repositório em que estão registadas todas
     * as Áreas de Actividade da plataforma
     */
    private static RepositorioAreaActividadeInMemory repositorioAreaActividadeInMemory;
    
    /**
     * Define o atributo da classe RepositorioAreaActividade como uma lista de
     * tipos da classe AreaActividade
     */
    private List<AreaActividade> listaAreasActividade;
    
    /**
     * Inicializa o Repositório de Areas de Actividade
     */
    RepositorioAreaActividadeInMemory(){
        listaAreasActividade = new ArrayList<>();
    }

    /**
     * Devolve uma instância estática do Repositório de Áreas de Actividade
     * @return RepositorioAreaActividade
     */
    public static RepositorioAreaActividadeInMemory getInstance(){
        if(repositorioAreaActividadeInMemory == null) {
            repositorioAreaActividadeInMemory = new RepositorioAreaActividadeInMemory();
        }
        return repositorioAreaActividadeInMemory;
    }

    @Override
    public void save(String codigo, String descBreve, String descDetalhada) {
        AreaActividade aa = findByCodigo(codigo);
        if (aa == null) {
            listaAreasActividade.add(aa);
        }
        else {
            throw new AreaActividadeDuplicadaException(codigo + ": Área de Actividade já registada!");
        }
    }

    public boolean save(AreaActividade areaActividade) {
        AreaActividade aa = findByCodigo(areaActividade.getCodigo());
        if (aa == null) {
            return listaAreasActividade.add(areaActividade);
        }
        else {
            throw new AreaActividadeDuplicadaException(aa.getCodigo() + ": Área de Actividade já registada!");
        }
    }

    @Override
    public List<AreaActividade> getAll() {
        return listaAreasActividade;
    }

    @Override
    public AreaActividade findByCodigo(String codigo) {
        for (int i = 0; i < this.listaAreasActividade.size(); i++) {
            AreaActividade areaActividade = this.listaAreasActividade.get(i);
            if (areaActividade.getCodigo().equals(codigo)) {
                return areaActividade;
            }
        }
        return null;
    }

    @Override
    public AreaActividade getAreaActividade(String codigoAreaActividade) {
        return null;
    }

    public int adicionarListaAreasActividade(RepositorioAreaActividadeInMemory outraListaAreasActividade) {

        int totalAreasAdicionadas = 0;

        for (AreaActividade areaActividade : outraListaAreasActividade.listaAreasActividade) {
            boolean areaActividadeAdicionada = save(areaActividade);
            if (areaActividadeAdicionada) {
                totalAreasAdicionadas++;
            }
        }
        return totalAreasAdicionadas;

    }
}
