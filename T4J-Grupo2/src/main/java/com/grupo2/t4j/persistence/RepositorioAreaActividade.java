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

import com.grupo2.t4j.domain.AreaActividade;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Geral
 */
public interface RepositorioAreaActividade {

    
    void save(String codigo, String descBreve, String descDetalhada) throws SQLException;

    boolean save(AreaActividade areaActividade) throws SQLException;

    List<AreaActividade> getAll() throws SQLException;

    AreaActividade findByCodigo(String codigo) throws SQLException;

    public AreaActividade getAreaActividade(String codigoAreaActividade) throws SQLException;




}
