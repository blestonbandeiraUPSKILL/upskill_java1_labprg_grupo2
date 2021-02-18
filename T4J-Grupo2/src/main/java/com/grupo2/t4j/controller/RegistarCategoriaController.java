/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.files.FicheiroRepositorioCategoria;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;
import com.grupo2.t4j.persistence.RepositorioCategoriaTarefa;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;
import com.grupo2.t4j.persistence.inmemory.RepositorioCategoriaTarefaInMemory;

import java.awt.geom.Area;
import java.io.File;

import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarCategoriaController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioCategoriaTarefa repositorioCategoriaTarefa = fabricaRepositorios.getRepositorioCategoriaTarefa();
    private RepositorioAreaActividade repositorioAreaActividade = fabricaRepositorios.getRepositorioAreaActividade();

    private FicheiroRepositorioCategoria ficheiroCat;
    private RepositorioCategoriaTarefaInMemory repositorioCategoria;

    public boolean registarCategoria(String codigoAreaActividade, String codigoCategoria, String descBreve,
                                     String descDetalhada, List<CaracterizacaoCT> ccts) {

        AreaActividade areaActividade = repositorioAreaActividade.findByCodigo(codigoAreaActividade);
        Categoria categoria = new Categoria(codigoCategoria, descBreve, descDetalhada, areaActividade, ccts);

        return repositorioCategoria.save(categoria);
    }

    public List<Categoria> getAll() {
        return repositorioCategoria.getAll();
    }

    public List<Categoria> findByAreaActividade(AreaActividade areaActividade) {
        return repositorioCategoria.findByAreaActividade(areaActividade.getCodigo());
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

