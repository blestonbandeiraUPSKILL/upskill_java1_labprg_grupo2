/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.files.FicheiroRepositorioCompetenciaTecnica;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.repository.RepositorioAreaActividade;
import com.grupo2.t4j.repository.RepositorioCompetenciaTecnica;
import java.io.File;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarCompetenciaTecnicaController {
    
    private FicheiroRepositorioCompetenciaTecnica ficheiroCompTec;
    private RepositorioCompetenciaTecnica repositorioCompetenciaTecnica;

    public boolean registarCompetenciaTecnica(CompetenciaTecnica competenciaTecnica) {
        return RepositorioCompetenciaTecnica.getInstance().addCompetenciaTecnica(competenciaTecnica);
    }

    public List<CompetenciaTecnica> getCompetenciasTecnicasByAreaActividade(AreaActividade areaActividade) {

       return RepositorioCompetenciaTecnica.getInstance().getCompetenciasTecnicasByAreaActividade(areaActividade);
    }

    public List<CompetenciaTecnica> getCompetenciasTecnicas() {
        return RepositorioCompetenciaTecnica.getInstance().getCompetenciasTecnicas();
    }

    public CompetenciaTecnica novaCompetenciaTecnica(String codigo, String descBreve,
                                                     String descDetalhada, AreaActividade areaActividade) {
        return RepositorioCompetenciaTecnica.getInstance().novaCompetenciaTecnica(codigo, descBreve,
                descDetalhada, areaActividade);
    }
    
    //////FICHEIROS////////
    public RegistarCompetenciaTecnicaController() {
        ficheiroCompTec = new FicheiroRepositorioCompetenciaTecnica();
        
        desserializar();
    }
    public boolean serializar() {
        return ficheiroCompTec.serializar(repositorioCompetenciaTecnica);
    }

    public boolean serializar(File ficheiroExportar) {
        return ficheiroCompTec.serializar(ficheiroExportar, repositorioCompetenciaTecnica);
    }

    public void desserializar() {
        repositorioCompetenciaTecnica = ficheiroCompTec.desserializar();
    }

    public int desserializar(File ficheiroImportar) {
        RepositorioCompetenciaTecnica listaCompetenciaTencicaImportada = ficheiroCompTec.desserializar(ficheiroImportar);

        return RepositorioCompetenciaTecnica.getInstance().adicionarListaCompetenciasTecnicas(listaCompetenciaTencicaImportada);
    }


}
