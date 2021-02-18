/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

import com.grupo2.t4j.model.GrauProficiencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
public interface RepositorioGrauProficiencia {
    
    void save (int valor, String designacao, String codigoCompetenciaTecnica);
    
    boolean save (GrauProficiencia grauProficiencia);
    
    List<GrauProficiencia> getAll();
    
    ArrayList<GrauProficiencia> findByCompetenciaTecnica();
    
    GrauProficiencia findByValor(int valor, String codigoCompetenciaTecnica);
}
