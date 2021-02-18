/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

import com.grupo2.t4j.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
public interface RepositorioCompetenciaTecnica {

    void save(String codigo, String descBreve, String descDetalhada,
              AreaActividade areaActividade);

    boolean save(CompetenciaTecnica competenciaTecnica);

    List<CompetenciaTecnica> getAll();

    CompetenciaTecnica findByCodigo(String codigo);

    ArrayList<CompetenciaTecnica> findByAreaActividade(AreaActividade at);



}
