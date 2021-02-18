/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.inmemory;

import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.persistence.RepositorioGrauProficiencia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
public class RepositorioGrauProficienciaInMemory implements Serializable, RepositorioGrauProficiencia{
    
    /**
     * Atributos da classe Singletone RepositorioGrauProficiencia
     */
    public static RepositorioGrauProficienciaInMemory instance;
    List<GrauProficiencia> listaGrausProficiencia;
    
    /**
     * Construtor da classe Singleton RepositorioGrauProficiencia
     */
    RepositorioGrauProficienciaInMemory() {
        listaGrausProficiencia = new ArrayList<>();
    }

    /**
     * Devolve ou cria uma instância estática de RepositorioGrauProficiencia
     *
     * @return a instance existente ou criada
     */
    public static RepositorioGrauProficienciaInMemory getInstance() {
        if (instance == null) {
            instance = new RepositorioGrauProficienciaInMemory();
        }
        return instance;
    }
    @Override
    public void save(int valor, String designacao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(GrauProficiencia grauProficiencia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GrauProficiencia> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<GrauProficiencia> findByCompetenciaTecnica() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
