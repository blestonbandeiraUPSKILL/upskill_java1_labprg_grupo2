/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.inmemory;

import com.grupo2.t4j.exception.GrauProficienciaDuplicadoException;
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
    public void save(int valor, String designacao, String codigoCompetenciaTecnica) {
        
    }

    @Override
    public boolean save(GrauProficiencia grauProficiencia) {
        GrauProficiencia gp = findByValor(grauProficiencia.getGrau(),grauProficiencia.getCompetenciaTecnica().getCodigo());
        if(gp==null) {
            this.listaGrausProficiencia.add(grauProficiencia);
        } else {
            throw new GrauProficienciaDuplicadoException(gp.getGrau()+": Grau já especificado");
        }
        return false;
    }

    @Override
    public List<GrauProficiencia> getAll() {
        return new ArrayList<GrauProficiencia>(listaGrausProficiencia);
    }

    @Override
    public ArrayList<GrauProficiencia> findByCompetenciaTecnica(String codigoCompetenciaTecnica) {
        ArrayList<GrauProficiencia> grauProficienciaPorCT = new ArrayList<>();
        for (GrauProficiencia gp: listaGrausProficiencia) {
            if(gp.getCompetenciaTecnica().getCodigo().equals(codigoCompetenciaTecnica)) {
                grauProficienciaPorCT.add(gp);
            }
        }
        return grauProficienciaPorCT;
    }

    @Override
    public GrauProficiencia findByValor(int valor, String codigoCompetenciaTecnica) {
        for(int i = 0; i < this.findByCompetenciaTecnica(codigoCompetenciaTecnica).size(); i++){
            GrauProficiencia gp = this.findByCompetenciaTecnica(codigoCompetenciaTecnica).get(i);
            if (gp.getGrau()==valor){
                return gp;
            }
        }
        return null;
    }

    
    
}
