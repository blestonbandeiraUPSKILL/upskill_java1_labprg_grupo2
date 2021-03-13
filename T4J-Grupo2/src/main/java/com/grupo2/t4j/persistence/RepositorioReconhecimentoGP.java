/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

import com.grupo2.t4j.exception.ReconhecimentoDuplicadoException;
import com.grupo2.t4j.domain.ReconhecimentoGP;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author acris
 */
public interface RepositorioReconhecimentoGP {
    
    boolean save(int idGrauProficiencia, String emailFreelancer, String dataReconhecimento) throws ReconhecimentoDuplicadoException,
            SQLException;
    
    boolean save(ReconhecimentoGP reconhecimentoGP)throws ReconhecimentoDuplicadoException,
            SQLException;
    
    List<ReconhecimentoGP> getAll(String email) throws SQLException;


    
}
