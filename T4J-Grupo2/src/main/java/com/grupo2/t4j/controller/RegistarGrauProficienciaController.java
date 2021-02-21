/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioCompetenciaTecnica;
import com.grupo2.t4j.persistence.RepositorioGrauProficiencia;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarGrauProficienciaController {
    
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioGrauProficiencia repositorioGrauProficiencia = fabricaRepositorios.getRepositorioGrauProficiencia();
    private RepositorioCompetenciaTecnica repositorioCompetenciaTecnica = fabricaRepositorios.getRepositorioCompetenciaTecnica();

    public RegistarGrauProficienciaController() throws SQLException {
    }

    public List<GrauProficiencia> getAll() {
        return repositorioGrauProficiencia.getAll();
    }

    public boolean registarGrauProficiencia(String codigoGP, String valor, String designacao, String codigoCompetenciaTecnica) {

        GrauProficiencia grauProficiencia = new GrauProficiencia (codigoGP, valor, designacao, codigoCompetenciaTecnica);

        return repositorioGrauProficiencia.save(grauProficiencia);
    }

    public List<GrauProficiencia> findByCompetenciaTecnica(String codigoCompetenciaTecnica) {
        return repositorioGrauProficiencia.findByCompetenciaTecnica(codigoCompetenciaTecnica);
    }
    
    public GrauProficiencia findByValor(String valor, String codigoCompetenciaTecnica) {
        return repositorioGrauProficiencia.findByGrau(valor, codigoCompetenciaTecnica);
    }
    
}
