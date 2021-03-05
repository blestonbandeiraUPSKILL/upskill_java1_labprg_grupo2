/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

/**
 *
 * @author acris
 */

import com.grupo2.t4j.exception.CandidaturaDuplicadaException;
import com.grupo2.t4j.model.Candidatura;

import java.sql.SQLException;
import java.util.ArrayList;
public interface RepositorioCandidatura {
    
    boolean save(int idCandidatura, double valorPretendido, int numeroDias, 
            String txtApresentacao, String txtMotivacao, int idAnuncio, String 
            emailFreelancer) throws CandidaturaDuplicadaException, SQLException;

    boolean save(Candidatura candidatura) throws SQLException;

    Candidatura findById(int idCandidatura) throws SQLException;
    
    Candidatura findByEmail (String emailFreelancer) throws SQLException;

    ArrayList<Candidatura> getAll()throws SQLException;   

}
