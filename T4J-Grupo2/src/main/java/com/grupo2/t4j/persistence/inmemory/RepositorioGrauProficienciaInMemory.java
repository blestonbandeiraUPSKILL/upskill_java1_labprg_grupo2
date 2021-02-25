/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.inmemory;

import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.persistence.RepositorioGrauProficiencia;

import java.io.Serializable;
import java.sql.SQLException;
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
    public boolean save(GrauProficiencia grauProficiencia) throws SQLException {
        return false;
    }

 /*   @Override
    public boolean save(GrauProficiencia grauProficiencia) {
        GrauProficiencia gp = findByGrau(grauProficiencia.getGrau(),grauProficiencia.getCodigoCompetenciaTecnica());
        if(gp == null) {
            this.listaGrausProficiencia.add(grauProficiencia);
            return true;
        } else {
            throw new GrauProficienciaDuplicadoException(gp.getGrau()+": Grau já especificado");
        }
    }*/

    @Override
    public List<GrauProficiencia> getAll() {
        return new ArrayList<GrauProficiencia>(listaGrausProficiencia);
    }

    @Override
    public ArrayList<GrauProficiencia> findByCompetenciaTecnica(String codigoCompetenciaTecnica) {
        ArrayList<GrauProficiencia> grauProficienciaPorCT = new ArrayList<>();
        for (GrauProficiencia gp: listaGrausProficiencia) {
            if(gp.getCodigoCompetenciaTecnica().equals(codigoCompetenciaTecnica)) {
                grauProficienciaPorCT.add(gp);
            }
        }
        return grauProficienciaPorCT;
    }

    @Override
    public GrauProficiencia findByGrau(String grau) {
        return null;
    }

    @Override
    public GrauProficiencia findByGrauECompetencia(String grau, String codigoCompetenciaTecnica) throws SQLException {
        return null;
    }


/*
    @Override
    public GrauProficiencia findByGrau(String grau, String codigoCompetenciaTecnica) {

        for(int i = 0; i < this.findByCompetenciaTecnica(codigoCompetenciaTecnica).size(); i++){
            GrauProficiencia gp = this.findByCompetenciaTecnica(codigoCompetenciaTecnica).get(i);
            if (gp.getGrau().equals(grau)){
                return gp;
            }
        }
        return null;
    }*/
/*
    @Override
    public boolean findByValorECompetenciaTecnica(String grau, String codigoCompetenciaTecnica) throws SQLException {

        for (int i = 0; i < this.findByCompetenciaTecnica(codigoCompetenciaTecnica).size(); i++) {
            GrauProficiencia gp = this.findByCompetenciaTecnica(codigoCompetenciaTecnica).get(i);
            if (gp.getGrau().equals(grau)) {
                return true;
            }
        }
        return false;
    }*/
}
