/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioCompetenciaTecnica;
import com.grupo2.t4j.persistence.RepositorioGrauProficiencia;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;
import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarGrauProficienciaController {
    
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioGrauProficiencia repositorioGrauProficiencia = fabricaRepositorios.getRepositorioGrauProficiencia();
    private RepositorioCompetenciaTecnica repostitorioCompetenciaTecnica = fabricaRepositorios.getRepositorioCompetenciaTecnica();
    
    public List<GrauProficiencia> getAll() {
        return repositorioGrauProficiencia.getAll();
    }

    public boolean registarGrauProficiencia(int valor, String designacao, String codigoCompetenciaTecnica) {
        CompetenciaTecnica competenciaTecnica = repostitorioCompetenciaTecnica.findByCodigo(codigoCompetenciaTecnica);
        GrauProficiencia grauProficiencia = new GrauProficiencia (valor, designacao, competenciaTecnica); 
        return repositorioGrauProficiencia.save(grauProficiencia);
    }

    public List<GrauProficiencia> findByCompetenciaTecnica(String codigoCompetenciaTecnica) {
        return repositorioGrauProficiencia.findByCompetenciaTecnica(codigoCompetenciaTecnica);
    }
    
    public GrauProficiencia findByValor(int valor, String codigoCompetenciaTecnica) {
        return repositorioGrauProficiencia.findByValor(valor, codigoCompetenciaTecnica);
    }
    
}
