/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.files.FicheiroRepositorioCategoria;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.inmemory.RepositorioCategoriaTarefaInMemory;
import java.io.File;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarCategoriaController {
    
    private FicheiroRepositorioCategoria ficheiroCat;
    private RepositorioCategoriaTarefaInMemory repositorioCategoria;

    public boolean registarCategoria (AreaActividade areaActividade,
                                                String descBreve,
                                                String descDetalhada,
                                                List<CaracterizacaoCT> ccts) {

        return RepositorioCategoriaTarefaInMemory.getInstance().addCategoria(
                 descBreve, descDetalhada, areaActividade, ccts);

    }

    public Categoria novaCategoriaTarefa(String descBreve, String descDetalhada,
                                         AreaActividade areaActividade, List<CaracterizacaoCT> caracterizacaoCTS) {
        return RepositorioCategoriaTarefaInMemory.getInstance().novaCategoriaTarefa(descBreve, descDetalhada, areaActividade, caracterizacaoCTS);
    }

    public boolean registarCategoria(Categoria categoria) {
        return RepositorioCategoriaTarefaInMemory.getInstance().addCategoria(categoria);
    }

    public List<Categoria> getCategoriasTarefa() {
        return RepositorioCategoriaTarefaInMemory.getInstance().getCategorias();
    }

    public ArrayList<Categoria> getCategoriasByAreaActividade(AreaActividade at) {
        ArrayList<Categoria> categoriaPorAt = new ArrayList<>();

        for (Categoria cat : RepositorioCategoriaTarefaInMemory.getInstance().getCategoriasByAreaActividade(at)) {
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
        return ficheiroCat.serializar(repositorioCategoria);
    }

    public boolean serializar(File ficheiroExportar) {
        return ficheiroCat.serializar(ficheiroExportar, repositorioCategoria);
    }

    public void desserializar() {
        repositorioCategoria = ficheiroCat.desserializar();
    }

    public int desserializar(File ficheiroImportar) {
        RepositorioCategoriaTarefaInMemory listaCategoriasImportada = ficheiroCat.desserializar(ficheiroImportar);

        return RepositorioCategoriaTarefaInMemory.getInstance().adicionarListaCategorias(listaCategoriasImportada);
    }
}

