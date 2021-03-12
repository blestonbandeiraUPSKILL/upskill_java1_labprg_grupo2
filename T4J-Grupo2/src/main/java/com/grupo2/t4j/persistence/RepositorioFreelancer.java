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

import com.grupo2.t4j.exception.FreelancerDuplicadoException;
import com.grupo2.t4j.domain.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RepositorioFreelancer {
    
    boolean save(String emailFree, String nome, String nif, String telefone, String password, String arruamento,
                 String numeroPorta, String localidade, String codPostal) throws FreelancerDuplicadoException,
            SQLException;

    boolean save(Freelancer freelancer) throws FreelancerDuplicadoException, 
            SQLException;

    Freelancer findByEmail(String emailFree) throws SQLException;
    
    Freelancer findByNif(String nif) throws SQLException;

    Password findPassword(String email) throws SQLException;

    ArrayList<Freelancer> getAll() throws SQLException;

    List<ReconhecimentoGP> getAllReconhecimentoGP(String emailFreelancer) throws SQLException;

    List<HabilitacaoAcademica> getAllHabsAcademicas(String emailFreelancer) throws SQLException;

    EnderecoPostal getEnderecoPostal(String emailFreelancer) throws SQLException;

}
