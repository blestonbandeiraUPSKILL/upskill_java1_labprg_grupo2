/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.Candidatura;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioCandidatura;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author acris
 */
public class EditarCandidaturaController {
    
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioCandidatura repositorioCandidatura = fabricaRepositorios.getRepositorioCandidatura();


    public void updateCandidatura(int idCandidatura, double valorPretendido,
            int numeroDias, String txtApresentacao, String txtMotivacao) throws SQLException {
        repositorioCandidatura.updateCandidatura(idCandidatura, valorPretendido,
                numeroDias, txtApresentacao, txtMotivacao);
    }

    public List<Integer> getAllCandidaturasEditaveis(String emailFreelancer) throws SQLException {
        return repositorioCandidatura.getAllCandidaturasEditaveis(emailFreelancer);
    }
    
}
