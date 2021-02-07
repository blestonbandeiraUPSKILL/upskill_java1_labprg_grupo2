/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.repository.RepositorioCategoriaTarefa;

import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarCategoriaController {

    public boolean registarCategoria (AreaActividade areaActividade,
                                                String descBreve,
                                                String descDetalhada,
                                                List<CaracterizacaoCT> ccts) {

        return RepositorioCategoriaTarefa.getInstance().addCategoria(
                 descBreve, descDetalhada, areaActividade, ccts);

    }

    public boolean registarCategoria(Categoria categoria) {
        return RepositorioCategoriaTarefa.getInstance().addCategoria(categoria);
    }

}

