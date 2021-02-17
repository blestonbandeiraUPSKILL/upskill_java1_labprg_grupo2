/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.AreaActividadeDuplicadaException;
import com.grupo2.t4j.model.AreaActividade;

import java.util.List;

/**
 *
 * @author Geral
 */
public interface RepositorioAreaActividade {

    
    int save(RepositorioAreaActividade outraListaAreasActividade);

    boolean addAreaActividade(AreaActividade areaActividade) throws AreaActividadeDuplicadaException;

    /**
     * Adiciona uma Área de Actividade à lista de Áreas de Actividade
     * @param codigo o código único de cada Área de Actividade.
     * @param descBreve a descrição breve da Área de Actividade.
     * @param descDetalhada a descrição detalhada da Área de Actividade.
     * @throws AreaActividadeDuplicadaException
     */
    boolean addAreaActividade(String codigo, String descBreve, String descDetalhada) throws AreaActividadeDuplicadaException;
    

    /**
     * Devolve a lista de Áreas de Actividade
     *
     * @return 
     */
    List<AreaActividade> getAll();


    AreaActividade findByCodigo(String codigo);


    //update, delete



}
