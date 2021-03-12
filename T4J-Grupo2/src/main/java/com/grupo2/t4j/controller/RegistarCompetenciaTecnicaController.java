package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.CompetenciaTecnica;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;
import com.grupo2.t4j.persistence.RepositorioCompetenciaTecnica;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.List;

public class RegistarCompetenciaTecnicaController {

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

    /**
     * Devolve uma lista de todas as competencias tecnicas
     * @return
     * @throws SQLException 
     */
    public List<CompetenciaTecnica> getAll() throws SQLException {
        return repositorioCompetenciaTecnica.getAll();
    }

    /**
     * Devolve uma lista de todas as competencias tecnicas de uma area de atividade
     * @param codigoAreaActividade
     * @return
     * @throws SQLException 
     */
    public List<CompetenciaTecnica> findByAreaActividade(String codigoAreaActividade) throws SQLException {
        return repositorioCompetenciaTecnica.findByAreaActividade(codigoAreaActividade);
    }

    /**
     * Devolve uma competencia tecnica a partir do seu codigo
     * @param codigo
     * @return
     * @throws SQLException 
     */
    public CompetenciaTecnica findByCodigo(String codigo) throws SQLException {
        return repositorioCompetenciaTecnica.findByCodigo(codigo);
    }


}
