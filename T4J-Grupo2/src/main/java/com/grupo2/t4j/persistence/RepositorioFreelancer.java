/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.exception.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RepositorioFreelancer {
    
    boolean save(String emailFree, String nome, String passwordFree, String nif, 
            String telefone, String arruamento, String numeroPorta, String localidade, String codPostal) throws FreelancerDuplicadoException, 
            SQLException;

    boolean save(Freelancer freelancer) throws FreelancerDuplicadoException, 
            SQLException;

    Freelancer findByEmail(String emailFree) throws SQLException;
    
    Freelancer findByNif(String nif) throws SQLException;

    ArrayList<Freelancer> getAll() throws SQLException;
}
