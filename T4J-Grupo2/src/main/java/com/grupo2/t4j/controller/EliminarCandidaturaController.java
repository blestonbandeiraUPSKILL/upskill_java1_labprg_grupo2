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

    public boolean deleteCandidatura(int idCandidatura) throws SQLException {
        return repositorioCandidatura.deleteCandidatura(idCandidatura);
    }

}
