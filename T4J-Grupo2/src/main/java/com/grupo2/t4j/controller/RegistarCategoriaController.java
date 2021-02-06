/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.repository.RepositorioCategoriaTarefa;

import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarCategoriaController {

    public boolean registarCategoriaController (AreaActividade areaActividade,
                                                String descBreve,
                                                String descDetalhada,
                                                List<CompetenciaTecnica> competenciasTecnicas) {
        return RepositorioCategoriaTarefa.getInstance().addCategoria(areaActividade, descBreve, descDetalhada, competenciasTecnicas);

        );
    }



    //public boolean registarCategoria(String id, String)
}

