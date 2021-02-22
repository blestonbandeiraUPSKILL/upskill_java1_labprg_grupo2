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
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarGrauProficienciaController {
    
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioGrauProficiencia repositorioGrauProficiencia = fabricaRepositorios.getRepositorioGrauProficiencia();
    private RepositorioCompetenciaTecnica repositorioCompetenciaTecnica = fabricaRepositorios.getRepositorioCompetenciaTecnica();

    public RegistarGrauProficienciaController() throws SQLException {
    }

    public List<GrauProficiencia> getAll() throws SQLException {
        return repositorioGrauProficiencia.getAll();
    }

    public boolean registarGrauProficiencia(String designacao, String codigoCompetenciaTecnica, String grau) throws SQLException {

        GrauProficiencia grauProficiencia = new GrauProficiencia (designacao, codigoCompetenciaTecnica, grau);

        return repositorioGrauProficiencia.save(grauProficiencia);
    }

    public List<GrauProficiencia> findByCompetenciaTecnica(String codigoCompetenciaTecnica) throws SQLException {
        return repositorioGrauProficiencia.findByCompetenciaTecnica(codigoCompetenciaTecnica);
    }
    
    public GrauProficiencia findByValor(String grau) throws SQLException {
        return repositorioGrauProficiencia.findByGrau(grau);
    }

    public GrauProficiencia findByGrauECompetenciaTecnica(String grau, String codigoCompetenciaTecnica) throws SQLException {
        return repositorioGrauProficiencia.findByGrauECompetencia(grau, codigoCompetenciaTecnica);
    }
    
}
