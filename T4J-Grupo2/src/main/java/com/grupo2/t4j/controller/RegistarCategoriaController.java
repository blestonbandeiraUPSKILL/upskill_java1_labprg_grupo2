package com.grupo2.t4j.controller;

import com.grupo2.t4j.files.FicheiroRepositorioCategoria;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;
import com.grupo2.t4j.persistence.RepositorioCategoriaTarefa;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;
import com.grupo2.t4j.persistence.inmemory.RepositorioCategoriaTarefaInMemory;

import java.awt.geom.Area;
import java.io.File;

import java.sql.SQLException;
import java.util.List;

public class RegistarCategoriaController {

    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioCategoriaTarefa repositorioCategoriaTarefa = fabricaRepositorios.getRepositorioCategoriaTarefa();
    private RepositorioAreaActividade repositorioAreaActividade = fabricaRepositorios.getRepositorioAreaActividade();

    private FicheiroRepositorioCategoria ficheiroCat;
    private RepositorioCategoriaTarefaInMemory repositorioCategoriaTarefaInMemory;

    /**
    * Registar categoria boolean
    *
    * @param codigoCategoria as código da categoria
    * @param descBreve as descrição breve da categoria
    * @param descDetalhada as descrição detalhada da categoria
    * @param codigoAreaActividade as código da área da actividade
    * @return boolean
    */
    public boolean registarCategoria(String codigoCategoria, String descBreve,
                                     String descDetalhada, String codigoAreaActividade
                                     ) throws SQLException {

        Categoria categoria = new Categoria(codigoCategoria, descBreve, descDetalhada, codigoAreaActividade);

        return repositorioCategoriaTarefa.save(categoria);
    }

    public List<Categoria> getAll() throws SQLException {
        return repositorioCategoriaTarefa.getAll();
    }

    public List<Categoria> findByAreaActividade(String codigoAreaActividade) throws SQLException{
        return repositorioCategoriaTarefa.findByAreaActividade(codigoAreaActividade);
    }

    public Categoria findByCodigo(String codigoCategoria) throws SQLException {
        return repositorioCategoriaTarefa.findByCodigo(codigoCategoria);
    }
    
    ////FICHEIROS//////

    public RegistarCategoriaController() {
        ficheiroCat = new FicheiroRepositorioCategoria();
        
        desserializar();
    }

    public boolean serializar() {
        return ficheiroCat.serializar(repositorioCategoriaTarefaInMemory);
    }

    public boolean serializar(File ficheiroExportar) {
        return ficheiroCat.serializar(ficheiroExportar, repositorioCategoriaTarefaInMemory);
    }

    public void desserializar() {
        repositorioCategoriaTarefaInMemory = ficheiroCat.desserializar();
    }

    public int desserializar(File ficheiroImportar) {
        RepositorioCategoriaTarefaInMemory listaCategoriasImportada = ficheiroCat.desserializar(ficheiroImportar);

        return RepositorioCategoriaTarefaInMemory.getInstance().adicionarListaCategorias(listaCategoriasImportada);
    }



}

