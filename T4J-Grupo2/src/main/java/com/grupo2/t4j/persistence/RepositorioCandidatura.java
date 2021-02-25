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

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.exception.*;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public interface RepositorioCandidatura {
    
    boolean save(String idCandidatura, String emailFreelancer, double valorPretendido, 
            int numeroDias, String txtApresentacao,String txtMotivacao) throws CandidaturaDuplicadaException,
            SQLException;

    boolean save(Candidatura candidatura) throws SQLException;

    Candidatura findById(String idCandidatura) throws SQLException;
    
    Candidatura findByEmail (String emailFreelancer) throws SQLException;

    ArrayList<Candidatura> getAll()throws SQLException;   

}
