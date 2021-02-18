/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.files.FicheiroRepositorioCompetenciaTecnica;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.persistence.inmemory.RepositorioCompetenciaTecnicaInMemory;

import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.repository.RepositorioAreaActividade;
import com.grupo2.t4j.repository.RepositorioCompetenciaTecnica;

import java.io.File;

import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarCompetenciaTecnicaController {

    
    
    private FicheiroRepositorioCompetenciaTecnica ficheiroCompTec;
    private RepositorioCompetenciaTecnicaInMemory repositorioCompetenciaTecnicaInMemory;

    public boolean registarCompetenciaTecnica(CompetenciaTecnica competenciaTecnica) {
        return RepositorioCompetenciaTecnicaInMemory.getInstance().addCompetenciaTecnica(competenciaTecnica);
    }

    public List<CompetenciaTecnica> getCompetenciasTecnicasByAreaActividade(AreaActividade areaActividade) {

       return RepositorioCompetenciaTecnicaInMemory.getInstance().getCompetenciasTecnicasByAreaActividade(areaActividade);
    }
    
    public static List<GrauProficiencia> getGrausAplicaveis() {
        return CompetenciaTecnica.getGrausAplicaveis();
    }

    public List<CompetenciaTecnica> getCompetenciasTecnicas() {
        return RepositorioCompetenciaTecnicaInMemory.getInstance().getCompetenciasTecnicas();
    }

    public CompetenciaTecnica novaCompetenciaTecnica(String codigo, String descBreve,
                                                     String descDetalhada, AreaActividade areaActividade) {
        return RepositorioCompetenciaTecnicaInMemory.getInstance().novaCompetenciaTecnica(codigo, descBreve,
                descDetalhada, areaActividade);
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
