/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.files.FicheiroRepositorioAreaActividade;
import com.grupo2.t4j.domain.AreaActividade;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import com.grupo2.t4j.persistence.inmemory.RepositorioAreaActividadeInMemory;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarAreaActividadeController /*implements Serializable*/{

    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioAreaActividade repositorioAreaActividade = fabricaRepositorios.getRepositorioAreaActividade();

    private FicheiroRepositorioAreaActividade ficheiroAt;
    private RepositorioAreaActividadeInMemory repositorioAreaActividadeInMemory;

    public List<AreaActividade> getAll() throws SQLException {
        return repositorioAreaActividade.getAll();
    }

    /**
    * Sets new area of activity boolean.
    *
    * @param codigo as unique code
    * @param descBreve as description
    * @param descDetalhada as detailed description
    * @return boolean
    */
    public boolean registarAreaActividade(String codigo, String descBreve, String descDetalhada) throws SQLException {

        AreaActividade areaActividade = new AreaActividade(codigo, descBreve, descDetalhada);

        return repositorioAreaActividade.save(areaActividade);
    }

    public AreaActividade findByCodigo(String codigo) throws SQLException {
        return repositorioAreaActividade.findByCodigo(codigo);
    }

    public AreaActividade getAreaActividade(String codigoAreaActividade) throws SQLException {
        return repositorioAreaActividade.getAreaActividade(codigoAreaActividade);
    }

    //////FICHEIROS////////
    public RegistarAreaActividadeController() {
        ficheiroAt = new FicheiroRepositorioAreaActividade();
        
        desserializar();
    }
    public boolean serializar() {
        return ficheiroAt.serializar(repositorioAreaActividadeInMemory);
    }

    public boolean serializar(File ficheiroExportar) {
        return ficheiroAt.serializar(ficheiroExportar, repositorioAreaActividadeInMemory);
    }

    public void desserializar() {
        repositorioAreaActividadeInMemory = ficheiroAt.desserializar();
    }

    public int desserializar(File ficheiroImportar) {
        RepositorioAreaActividadeInMemory listaAreaActividadeImportada = ficheiroAt.desserializar(ficheiroImportar);

        return repositorioAreaActividadeInMemory.adicionarListaAreasActividade(listaAreaActividadeImportada);
    }


}
