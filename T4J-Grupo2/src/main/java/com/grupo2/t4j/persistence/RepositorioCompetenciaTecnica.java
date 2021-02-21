/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

import com.grupo2.t4j.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
public interface RepositorioCompetenciaTecnica {

    void save(String codigo, String descBreve, String descDetalhada,
              String codigoAreaActividade);

    boolean save(CompetenciaTecnica competenciaTecnica) throws SQLException;

    List<CompetenciaTecnica> getAll() throws SQLException;

    CompetenciaTecnica findByCodigo(String codigo) throws SQLException;

    //CompetenciaTecnica findByAreaActividade(String codigoAreaActividade) throws SQLException;

    List<CompetenciaTecnica> findByAreaActividade(String codigoAreaActividade) throws SQLException;


}
