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

    
    void save(String codigo, String descBreve, String descDetalhada);

    List<AreaActividade> getAll();

    AreaActividade findByCodigo(String codigo);


    //update, delete



}
