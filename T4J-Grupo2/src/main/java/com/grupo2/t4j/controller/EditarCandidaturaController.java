package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.Candidatura;
import com.grupo2.t4j.dto.CandidaturaDTO;
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

    
    /**
     * Faz o update da candidatura
     * @param idCandidatura
     * @param valorPretendido
     * @param numeroDias
     * @param txtApresentacao
     * @param txtMotivacao
     * @return
     * @throws SQLException 
     */
    public boolean updateCandidatura(int idCandidatura, double valorPretendido,
            int numeroDias, String txtApresentacao, String txtMotivacao) throws SQLException {

        return repositorioCandidatura.updateCandidatura(idCandidatura, valorPretendido,
                numeroDias, txtApresentacao, txtMotivacao);
    }

    /**
     * Devolve uma lista de candidaturas editaveis e um freelancer
     * @param emailFreelancer
     * @return
     * @throws SQLException 
     */
    public List<Integer> getAllCandidaturasEditaveis(String emailFreelancer) throws SQLException {
        return repositorioCandidatura.getAllCandidaturasEditaveis(emailFreelancer);
    }

    public CandidaturaDTO findById(int idCandidatura) throws SQLException{
        Candidatura candidatura = repositorioCandidatura.findById(idCandidatura);
        return (CandidaturaDTO) candidatura.toDTO();
    }

}
