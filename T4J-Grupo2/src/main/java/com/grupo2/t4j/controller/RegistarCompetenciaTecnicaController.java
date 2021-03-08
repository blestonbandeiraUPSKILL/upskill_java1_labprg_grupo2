package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.CompetenciaTecnica;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;
import com.grupo2.t4j.persistence.RepositorioCompetenciaTecnica;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class RegistarCompetenciaTecnicaController {

    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioCompetenciaTecnica repositorioCompetenciaTecnica = fabricaRepositorios.getRepositorioCompetenciaTecnica();
    private RepositorioAreaActividade repositorioAreaActividade = fabricaRepositorios.getRepositorioAreaActividade();

    /**
    * Registar competência técnica boolean
    *
    * @param codigo as código da competência técnica
    * @param descBreve as descrição breve da competência técnica
    * @param descDetalhada as descrição detalhada da competência técnica
    * @param codigoAreaActividade as código da área da actividade
    * @return boolean
    */
    public boolean registarCompetenciaTecnica(String codigo, String descBreve, String descDetalhada, String codigoAreaActividade) throws SQLException {

        CompetenciaTecnica competenciaTecnica = new CompetenciaTecnica(codigo, descBreve, descDetalhada, codigoAreaActividade);

        return repositorioCompetenciaTecnica.save(competenciaTecnica);
    }

    /*public static List<GrauProficiencia> getGrausAplicaveis() {
        return CompetenciaTecnica.getGrausAplicaveis();
    }*/

    public List<CompetenciaTecnica> getAll() throws SQLException {
        return repositorioCompetenciaTecnica.getAll();
    }

    public List<CompetenciaTecnica> findByAreaActividade(String codigoAreaActividade) throws SQLException {
        return repositorioCompetenciaTecnica.findByAreaActividade(codigoAreaActividade);
    }

    public CompetenciaTecnica findByCodigo(String codigo) throws SQLException {
        return repositorioCompetenciaTecnica.findByCodigo(codigo);
    }


}
