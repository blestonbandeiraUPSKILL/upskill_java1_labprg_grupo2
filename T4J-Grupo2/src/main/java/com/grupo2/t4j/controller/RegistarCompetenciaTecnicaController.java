/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.repository.RepositorioAreaActividade;
import com.grupo2.t4j.repository.RepositorioCompetenciaTecnica;
import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarCompetenciaTecnicaController {

    /*public List<CompetenciaTecnica> getListaCompetenciasTecnicas(){
        return RepositorioCompetenciaTecnica.getInstance().getCompetenciasTecnicas();
    }*/

    public boolean registarCompetenciaTecnica(
            String codigo,
            AreaActividade at,
            String descricaoBreve,
            String descricaoDetalhada) {
        return RepositorioCompetenciaTecnica.getInstance().addCompetenciaTecnica(codigo, at, descricaoBreve, descricaoDetalhada);
    }
    
    /*public boolean registarCompetenciaTecnica(String codCT, String codAT, String descBreve, String descDetalhada){
        CompetenciaTecnica compTec = new CompetenciaTecnica (codCT, RepositorioAreaActividade.getAreaAtividadeByCodigo(codAT), descBreve, descDetalhada);
        return RepositorioCompetenciaTecnica.getInstance().addCompetenciaTecnica(compTec);
    }*/

    public List<AreaActividade> getListaAreaActividade() {
        return RepositorioAreaActividade.getInstance().getListaAreasActividade();
    }

    public List<String> getListaAreaActividadeByDescBreve() {
        return RepositorioAreaActividade.getInstance().getListaAreasActividadeByDescBreve();
    }


    public boolean registarCompetenciaTecnica(CompetenciaTecnica competenciaTecnica) {
        return RepositorioCompetenciaTecnica.getInstance().addCompetenciaTecnica(competenciaTecnica);
    }

    public List<CompetenciaTecnica> getCompetenciasTecnicas() {
        return RepositorioCompetenciaTecnica.getInstance().getCompetenciasTecnicas();
    }
}
