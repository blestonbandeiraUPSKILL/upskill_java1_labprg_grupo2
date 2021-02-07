/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.files.FicheiroRepositorioCategoria;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.repository.RepositorioCategoriaTarefa;
import java.io.File;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarCategoriaController {
    
    private FicheiroRepositorioCategoria ficheiroCat;
    private RepositorioCategoriaTarefa listaCategorias;

    public boolean registarCategoria (AreaActividade areaActividade,
                                                String descBreve,
                                                String descDetalhada,
                                                List<CaracterizacaoCT> ccts) {

        return RepositorioCategoriaTarefa.getInstance().addCategoria(
                 descBreve, descDetalhada, areaActividade, ccts);

    }

    public Categoria novaCategoriaTarefa(String descBreve, String descDetalhada,
                                         AreaActividade areaActividade, List<CaracterizacaoCT> caracterizacaoCTS) {
        return RepositorioCategoriaTarefa.getInstance().novaCategoriaTarefa(descBreve, descDetalhada, areaActividade, caracterizacaoCTS);
    }

    public boolean registarCategoria(Categoria categoria) {
        return RepositorioCategoriaTarefa.getInstance().addCategoria(categoria);
    }

    public List<Categoria> getCategoriasTarefa() {
        return RepositorioCategoriaTarefa.getInstance().getCategorias();    
    }

    public ArrayList<Categoria> getCategoriasByAreaActividade(AreaActividade at) {
        ArrayList<Categoria> categoriaPorAt = new ArrayList<>();

        for (Categoria cat : RepositorioCategoriaTarefa.getInstance().getCategoriasByAreaActividade(at)) {
            if (cat.getAt().equals(at)) {
                categoriaPorAt.add(cat);
            }
        }
        return categoriaPorAt;
    }
    
    ////FICHEIROS//////
    
    
    
    public RegistarCategoriaController() {
        ficheiroCat = new FicheiroRepositorioCategoria();
        
        desserializar();
    }
    public boolean serializar() {
        return ficheiroCat.serializar(listaCategorias);
    }

    public boolean serializar(File ficheiroExportar) {
        return ficheiroCat.serializar(ficheiroExportar, listaCategorias);
    }

    public void desserializar() {
        listaCategorias = ficheiroCat.desserializar();
    }

    public int desserializar(File ficheiroImportar) {
        RepositorioCategoriaTarefa listaCategoriasImportada = ficheiroCat.desserializar(ficheiroImportar);

        return listaCategorias.getInstance().adicionarListaCategorias(listaCategoriasImportada);
    }
}

