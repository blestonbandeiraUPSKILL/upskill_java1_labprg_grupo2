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
import com.grupo2.t4j.model.AreaActividade;
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
    private RepositorioAreaActividadeInMemory(){
        listaAreasActividade = new ArrayList<>();
    }
    
    public int adicionarListaAreasActividade(RepositorioAreaActividadeInMemory outraListaAreasActividade) {
        int totalAreasAdicionadas = 0;
        
        for (AreaActividade areaActividade : outraListaAreasActividade.listaAreasActividade) {
            boolean areaActividadeAdicionada = addAreaActividade(areaActividade);
            if (areaActividadeAdicionada) {
                totalAreasAdicionadas++;
            }
        }
        return totalAreasAdicionadas;
    }

    public AreaActividade novaAreaActividade(String codigo, String descBreve, String descDetalhada) {
        return new AreaActividade(codigo, descBreve, descDetalhada);
    }


    @Override
    public int save(RepositorioAreaActividade outraListaAreasActividade) {
        return 0;
    }

    /**
     * Adiciona uma Área de Actividade à lista de Áreas de Actividade
     * @param areaActividade do tipo da classe AreaActividade
     * @throws AreaActividadeDuplicadaException
     * @return
     */
    public boolean addAreaActividade(AreaActividade areaActividade) throws AreaActividadeDuplicadaException {
        AreaActividade aa = getAreaActividadeByCodigo(areaActividade.getCodigo().toString());
        if (aa == null) {
            listaAreasActividade.add(areaActividade);
            return true;
        } else {
            throw new AreaActividadeDuplicadaException(aa.getCodigo() + ": Área de Actividade já registada!");
        }
    }
    
    /**
     * Adiciona uma Área de Actividade à lista de Áreas de Actividade
     * @param codigo o código único de cada Área de Actividade.
     * @param descBreve a descrição breve da Área de Actividade.
     * @param descDetalhada a descrição detalhada da Área de Actividade.
     * @throws AreaActividadeDuplicadaException
     */
    public boolean addAreaActividade(String codigo, String descBreve, String descDetalhada) throws AreaActividadeDuplicadaException {
        AreaActividade aa = getAreaActividadeByCodigo(codigo);
        if (aa == null) {
            AreaActividade areaActividade = new AreaActividade(codigo, descBreve, descDetalhada);
            return listaAreasActividade.add(areaActividade);
        } else {
            throw new AreaActividadeDuplicadaException(aa.getCodigo() + ": Área de Actividade já registada!");
        }
    }
    
    /**
     * Atualiza a lista de Áreas de Actividade
     *
     * @param listaAreasActividade
     */
    public void setListaAreasActividade(List<AreaActividade> listaAreasActividade) {
        this.listaAreasActividade = listaAreasActividade;
    }

    @Override
    public List<AreaActividade> getAll() {
        return null;
    }

    /**
     * Devolve a lista de Áreas de Actividade
     *
     * @return 
     */
    public List<AreaActividade> getListaAreasActividade() {

        return listaAreasActividade;
    }
    
    /**
     * Devolve uma Área de Actividade de acordo com o código registado
     * @param codigo o código único de cada Área de Actividade.
     * @return AreaActividade registada
     */
    public AreaActividade getAreaActividadeByCodigo(String codigo) {
        for (int i = 0; i < this.listaAreasActividade.size(); i++) {
            //AreaActividade areaActividade = RepositorioAreaActividade.getInstance().getAreaActividadeByCodigo(codigo);
            AreaActividade areaActividade = this.listaAreasActividade.get(i);
            if (areaActividade.getCodigo().equals(codigo)) {
                return areaActividade;
            }
        }
        return null;
    }

    @Override
    public AreaActividade find(String nif) {
        return null;
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

    //n funciona
    public List<String> getListaAreasActividadeByDescBreve() {
        List<AreaActividade> listaAreasActividade = getListaAreasActividade();
        List<String> listaAreasActividadeByDescBreve = new ArrayList<>();
        for(AreaActividade areaActividade : listaAreasActividade) {
            String descBreve = areaActividade.getDescBreve();
            listaAreasActividadeByDescBreve.add(descBreve);
        }
        return listaAreasActividadeByDescBreve;
    }
    
    /**
     * Informa se a lista de Áreas de Actividade está ou não vazia
     * @return 
     */
    public boolean isVazia() {
        return listaAreasActividade.isEmpty();
    }

    @Override
    public String toString() {
        return "RepositorioAreaActividade{" +
                "listaAreasActividade=" + listaAreasActividade +
                '}';
    }



}
