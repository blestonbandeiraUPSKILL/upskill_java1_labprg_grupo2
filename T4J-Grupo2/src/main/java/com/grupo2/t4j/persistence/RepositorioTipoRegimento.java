/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

import com.grupo2.t4j.domain.TipoRegimento;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author CAD
 */
public interface RepositorioTipoRegimento {

    boolean save(int idTipoRegimento, String designacao, String descricaoRegras) throws SQLException;
    
    boolean save(TipoRegimento tipoRegimento) throws SQLException;

    TipoRegimento findById(int idTipoRegimento) throws SQLException;
          
    List<TipoRegimento> getAll()throws SQLException;
}
