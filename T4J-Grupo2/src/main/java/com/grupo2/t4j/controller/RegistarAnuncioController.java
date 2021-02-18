/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.files.*;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.*;
import com.grupo2.t4j.persistence.database.*;
import com.grupo2.t4j.persistence.inmemory.*;
import java.io.File;

import java.util.ArrayList;
import java.util.List;
public class RegistarAnuncioController {
    
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioAnuncio repositorioAnuncio = fabricaRepositorios.getRepositorioAnuncio();
    
    private FicheiroRepositorioAnuncio ficheiroAnuncio;
    private RepositorioAnuncioInMemory repositorioAnuncioIM;

    public boolean registarAnuncio(String idAnuncio, Data dtInicioPublicitacao, 
            Data dtFimPublicitacao, Data dtInicioCandidatura, Data dtFimCandidatura, 
            Data dtInicioSeriacao, Data dtFimSeriacao) {

        Anuncio anuncio = new Anuncio(idAnuncio, dtInicioPublicitacao, dtFimPublicitacao, 
                dtInicioCandidatura, dtFimCandidatura, dtInicioSeriacao, dtFimSeriacao);

        return repositorioAnuncioIM.save(anuncio);
    }

    public List<Anuncio> getAll() {
        return repositorioAnuncioIM.getAll();
    }

    
    ////FICHEIROS//////

    public RegistarAnuncioController() {
        ficheiroAnuncio = new FicheiroRepositorioAnuncio();
        
        desserializar();
    }
    public boolean serializar() {
        return ficheiroAnuncio.serializar(repositorioAnuncioIM);
    }

    public boolean serializar(File ficheiroExportar) {
        return ficheiroAnuncio.serializar(ficheiroExportar, repositorioAnuncioIM);
    }

    public void desserializar() {
        repositorioAnuncioIM = ficheiroAnuncio.desserializar();
    }

    public int desserializar(File ficheiroImportar) {
        RepositorioAnuncioInMemory listaAnunciosImportada = ficheiroAnuncio.desserializar(ficheiroImportar);

        return RepositorioAnuncioInMemory.getInstance().adicionarListaAnuncios(listaAnunciosImportada);
    }
}
