/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

import com.grupo2.t4j.exception.ReconhecimentoDuplicadoException;
import java.sql.SQLException;
import com.grupo2.t4j.model.Data;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.ReconhecimentoGP;
import java.util.List;

/**
 *
 * @author acris
 */
public interface RepositorioReconhecimentoGP {
    
    boolean save(int idGrauProficiencia, String dataReconhecimento, 
             Email emailFreelancer, String idCompetenciaTecnica) throws ReconhecimentoDuplicadoException,
            SQLException;
    
    boolean save(ReconhecimentoGP reconhecimentoGP)throws ReconhecimentoDuplicadoException,
            SQLException;
    
    List<ReconhecimentoGP> getAll() throws SQLException;
    
    List<ReconhecimentoGP> findByEmail(String email) throws SQLException;
    
    ReconhecimentoGP findByEmailCompetencia(String email, String idCompetenciaTecnica) throws SQLException;
    
}
