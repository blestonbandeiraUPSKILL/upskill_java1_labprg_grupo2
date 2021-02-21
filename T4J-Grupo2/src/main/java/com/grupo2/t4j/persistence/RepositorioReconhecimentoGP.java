/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

import com.grupo2.t4j.exception.ReconhecimentoDuplicadoException;
import com.grupo2.t4j.model.Data;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.ReconhecimentoGP;
import java.util.List;

/**
 *
 * @author acris
 */
public interface RepositorioReconhecimentoGP {
    
    void save(String idGrauProficiencia, Data dataReconhecimento,
            Email emailFreelancer) throws ReconhecimentoDuplicadoException;
    
    List<ReconhecimentoGP> findByEmail(String email);
    
    ReconhecimentoGP findByEmailCompetencia(String email, String idCompetenciaTecnica);
    
}
