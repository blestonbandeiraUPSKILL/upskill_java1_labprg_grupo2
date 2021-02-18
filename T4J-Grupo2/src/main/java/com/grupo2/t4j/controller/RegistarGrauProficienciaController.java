/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.persistence.FabricaRepositorios;
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
    
    public List<GrauProficiencia> getAll() {
        return repositorioGrauProficiencia.getAll();
    }

    /*public GrauProficiencia novoGrauProficiencia(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    public boolean registarGrauProficiencia(int valor, String designacao) {
        GrauProficiencia grauProficiencia = new GrauProficiencia (valor, designacao); 
        return repositorioGrauProficiencia.save(grauProficiencia);
    }
    
}
