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
import com.grupo2.t4j.domain.Candidatura;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public interface RepositorioCandidatura {
    
    boolean save(double valorPretendido, int numeroDias,
            String txtApresentacao, String txtMotivacao, int idAnuncio, String 
            emailFreelancer) throws CandidaturaDuplicadaException, SQLException;

    boolean save(Candidatura candidatura) throws SQLException;

    Candidatura findById(int idCandidatura) throws SQLException;
    
    ArrayList<Candidatura> findByEmail (String emailFreelancer) throws SQLException;

    ArrayList<Candidatura> getAll()throws SQLException;  
    
    List<Candidatura> getAllByIdAnuncio(int idAnuncio) throws SQLException;
    
    List<Candidatura> ordenarByValor(List<Candidatura> candidaturas) throws SQLException;

    public boolean updateCandidatura(int idCandidatura, double valorPretendido,
            int numeroDias, String txtApresentacao, String txtMotivacao) throws SQLException;
    
    boolean deleteCandidatura(int idCandidatura) throws SQLException;


    List<Integer> getAllCandidaturasEditaveis(String emailFreelancer)throws SQLException;

    
}
