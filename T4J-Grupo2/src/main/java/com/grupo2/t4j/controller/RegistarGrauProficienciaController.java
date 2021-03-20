package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.GrauProficiencia;
import com.grupo2.t4j.dto.GrauProficienciaDTO;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioCaracterizacaoCT;
import com.grupo2.t4j.persistence.RepositorioCompetenciaTecnica;
import com.grupo2.t4j.persistence.RepositorioGrauProficiencia;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistarGrauProficienciaController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioGrauProficiencia repositorioGrauProficiencia = fabricaRepositorios.getRepositorioGrauProficiencia();
    private RepositorioCompetenciaTecnica repositorioCompetenciaTecnica = fabricaRepositorios.getRepositorioCompetenciaTecnica();
    private RepositorioCaracterizacaoCT repositorioCaracterizacaoCT = fabricaRepositorios.getRepositorioCaracterizacaoCT();

    public RegistarGrauProficienciaController() throws SQLException {
    }

    public List<GrauProficienciaDTO> getAll() throws SQLException {
        List<GrauProficiencia> grausProficiencia = repositorioGrauProficiencia.getAll();
        List<GrauProficienciaDTO> grausProficienciaDTO = new ArrayList<>();

        for(GrauProficiencia grauProficiencia : grausProficiencia) {
            grausProficienciaDTO.add((GrauProficienciaDTO) grauProficiencia.toDTO());
        }
        return grausProficienciaDTO;
    }

   /**
     * Registar grau de proficiência boolean
     *
     * @param designacao as designação do grau de proficiência
     * @param codigoCompetenciaTecnica as código da competência técnica em questão
     * @param grau as atribuição do grau de proficiência respsctivo
     * @return boolean
     */ 
    public boolean registarGrauProficiencia(String designacao, String codigoCompetenciaTecnica, int grau) throws SQLException {

        GrauProficiencia grauProficiencia = new GrauProficiencia (grau, codigoCompetenciaTecnica, designacao);

        return repositorioGrauProficiencia.save(grauProficiencia);
    }

    /**
     * Devolve uma lista de graus de proficiencia aplicaveis para uma competencia tecnica
     * @param codigoCompetenciaTecnica
     * @return
     * @throws SQLException 
     */
    public List<GrauProficienciaDTO> findByCompetenciaTecnica(String codigoCompetenciaTecnica) throws SQLException {
        List<GrauProficiencia> grausProficiencia = repositorioGrauProficiencia.findByCompetenciaTecnica(codigoCompetenciaTecnica);
        List<GrauProficienciaDTO> grausProficienciaDTO = new ArrayList<>();

        for (GrauProficiencia grauProficiencia : grausProficiencia) {
            grausProficienciaDTO.add((GrauProficienciaDTO) grauProficiencia.toDTO());
        }
        return grausProficienciaDTO;
    }

    /**
     * Devolve todos os graus de proficiencia aplicaveis para uma competencia tecnica
     * @param codigoCompetenciaTecnica
     * @return
     * @throws SQLException 
     */
    public List<GrauProficienciaDTO> getAllByCompetenciaTecnica(String codigoCompetenciaTecnica) throws SQLException {
        List<GrauProficiencia> grausProficiencia = repositorioGrauProficiencia.getAllByCompetenciaTecnica(codigoCompetenciaTecnica);
        List<GrauProficienciaDTO> grausProficienciaDTO = new ArrayList<>();

        for(GrauProficiencia grauProficiencia : grausProficiencia) {
            grausProficienciaDTO.add((GrauProficienciaDTO) grauProficiencia.toDTO());
        }
        return grausProficienciaDTO;
    }

    /**
     * Devolve um grau de proficiencia de acordo com o seu grau e o codigo da competencia tecnica
     * @param grau
     * @param codigoCompetenciaTecnica
     * @return
     * @throws SQLException 
     */
    public GrauProficienciaDTO findByGrauECompetenciaTecnica(int grau, String codigoCompetenciaTecnica) throws SQLException {
        GrauProficiencia grauProficiencia = repositorioGrauProficiencia.findByGrauECompetencia(grau, codigoCompetenciaTecnica);
        return (GrauProficienciaDTO) grauProficiencia.toDTO();
    }
    
}
