package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.CompetenciaTecnica;
import com.grupo2.t4j.dto.CompetenciaTecnicaDTO;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;
import com.grupo2.t4j.persistence.RepositorioCompetenciaTecnica;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<CompetenciaTecnicaDTO> getAll() throws SQLException {
        List<CompetenciaTecnica> competenciasTecnicas = repositorioCompetenciaTecnica.getAll();
        List<CompetenciaTecnicaDTO> competenciasTecnicasDTO = new ArrayList<>();

        for(CompetenciaTecnica competenciaTecnica : competenciasTecnicas) {
            competenciasTecnicasDTO.add((CompetenciaTecnicaDTO) competenciaTecnica.toDTO());
        }
        return competenciasTecnicasDTO;
    }

    /**
     * Devolve uma lista de todas as competencias tecnicas de uma area de atividade
     * @param codigoAreaActividade
     * @return
     * @throws SQLException 
     */
    public List<CompetenciaTecnicaDTO> findByAreaActividade(String codigoAreaActividade) throws SQLException {
        List<CompetenciaTecnica> competenciasTecnicas = repositorioCompetenciaTecnica.findByAreaActividade(codigoAreaActividade);
        List<CompetenciaTecnicaDTO> competenciasTecnicasDTO = new ArrayList<>();

        for(CompetenciaTecnica competenciaTecnica : competenciasTecnicas) {
            competenciasTecnicasDTO.add((CompetenciaTecnicaDTO) competenciaTecnica.toDTO());
        }
        return competenciasTecnicasDTO;
    }

    /**
     * Devolve uma competencia tecnica a partir do seu codigo
     * @param codigo
     * @return
     * @throws SQLException 
     */
    public CompetenciaTecnicaDTO findByCodigo(String codigo) throws SQLException {
        CompetenciaTecnica competenciaTecnica = repositorioCompetenciaTecnica.findByCodigo(codigo);

        return (CompetenciaTecnicaDTO) competenciaTecnica.toDTO();
    }


}
