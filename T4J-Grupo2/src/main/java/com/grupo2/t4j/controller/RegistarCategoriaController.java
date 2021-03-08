package com.grupo2.t4j.controller;


import com.grupo2.t4j.domain.Categoria;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;
import com.grupo2.t4j.persistence.RepositorioCategoriaTarefa;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class RegistarCategoriaController {

    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioCategoriaTarefa repositorioCategoriaTarefa = fabricaRepositorios.getRepositorioCategoriaTarefa();
    private RepositorioAreaActividade repositorioAreaActividade = fabricaRepositorios.getRepositorioAreaActividade();


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




}

