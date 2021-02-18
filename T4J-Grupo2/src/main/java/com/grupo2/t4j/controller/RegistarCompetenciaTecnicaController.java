/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.files.FicheiroRepositorioCompetenciaTecnica;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;
import com.grupo2.t4j.persistence.RepositorioCompetenciaTecnica;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;
import com.grupo2.t4j.persistence.inmemory.RepositorioCompetenciaTecnicaInMemory;
import java.io.File;

import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarCompetenciaTecnicaController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioCompetenciaTecnica repositorioCompetenciaTecnica = fabricaRepositorios.getRepositorioCompetenciaTecnica();
    private RepositorioAreaActividade repositorioAreaActividade = fabricaRepositorios.getRepositorioAreaActividade();

    private FicheiroRepositorioCompetenciaTecnica ficheiroCompTec;
    private RepositorioCompetenciaTecnicaInMemory repositorioCompetenciaTecnicaInMemory;

    public boolean registarCompetenciaTecnica(String codigo, String descBreve, String descDetalhada, String codigoAreaActividade) {

        AreaActividade areaActividade = repositorioAreaActividade.findByCodigo(codigoAreaActividade);
        CompetenciaTecnica competenciaTecnica = new CompetenciaTecnica(codigo, descBreve, descDetalhada, areaActividade);

        return repositorioCompetenciaTecnica.save(competenciaTecnica);
    }

    public List<CompetenciaTecnica> getAll() {
        return repositorioCompetenciaTecnica.getAll();
    }

    
    //////FICHEIROS////////
    public RegistarCompetenciaTecnicaController() {
        ficheiroCompTec = new FicheiroRepositorioCompetenciaTecnica();
        
        desserializar();
    }
    public boolean serializar() {
        return ficheiroCompTec.serializar(repositorioCompetenciaTecnicaInMemory);
    }

    public boolean serializar(File ficheiroExportar) {
        return ficheiroCompTec.serializar(ficheiroExportar, repositorioCompetenciaTecnicaInMemory);
    }

    public void desserializar() {
        repositorioCompetenciaTecnicaInMemory = ficheiroCompTec.desserializar();
    }

    public int desserializar(File ficheiroImportar) {
        RepositorioCompetenciaTecnicaInMemory listaCompetenciaTencicaImportada = ficheiroCompTec.desserializar(ficheiroImportar);

        return RepositorioCompetenciaTecnicaInMemory.getInstance().adicionarListaCompetenciasTecnicas(listaCompetenciaTencicaImportada);
    }

}
