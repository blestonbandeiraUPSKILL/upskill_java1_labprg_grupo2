package com.grupo2.t4j.controller;

import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioCandidatura;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;

/**
 *
 * @author marta
 */
public class EliminarCandidaturaController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioCandidatura repositorioCandidatura = fabricaRepositorios.getRepositorioCandidatura();

    /**
     * Elimina uma candidatura a partir do seu id
     * @param idCandidatura
     * @return
     * @throws SQLException 
     */
    public boolean deleteCandidatura(int idCandidatura) throws SQLException {
        return repositorioCandidatura.deleteCandidatura(idCandidatura);
    }

}
