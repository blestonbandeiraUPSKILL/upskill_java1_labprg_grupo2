/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.model.Tarefa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
public interface RepositorioGrauProficiencia {

    
    void save (int valor, String designacao, String codigoCompetenciaTecnica);
    
    boolean save (GrauProficiencia grauProficiencia) throws SQLException;
    
    List<GrauProficiencia> getAll() throws SQLException;
    
    ArrayList<GrauProficiencia> findByCompetenciaTecnica(String codigoCompetenciaTecnica) throws SQLException;
    
    GrauProficiencia findByGrau(int grau) throws SQLException;

    GrauProficiencia findByGrauECompetencia(int grau, String codigoCompetenciaTecnica) throws SQLException;

    List<GrauProficiencia> getAllGrausTarefa(Tarefa tarefa) throws SQLException;

    List<GrauProficiencia> getAllByCompetenciaTecnica(String codigoCompetenciaTecnica) throws SQLException;
}
