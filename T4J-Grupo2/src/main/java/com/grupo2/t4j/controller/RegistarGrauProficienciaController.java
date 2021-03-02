package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioCaracterizacaoCT;
import com.grupo2.t4j.persistence.RepositorioCompetenciaTecnica;
import com.grupo2.t4j.persistence.RepositorioGrauProficiencia;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.List;

public class RegistarGrauProficienciaController {
    
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioGrauProficiencia repositorioGrauProficiencia = fabricaRepositorios.getRepositorioGrauProficiencia();
    private RepositorioCompetenciaTecnica repositorioCompetenciaTecnica = fabricaRepositorios.getRepositorioCompetenciaTecnica();
    private RepositorioCaracterizacaoCT repositorioCaracterizacaoCT = fabricaRepositorios.getRepositorioCaracterizacaoCT();

    public RegistarGrauProficienciaController() throws SQLException {
    }

    public List<GrauProficiencia> getAll() throws SQLException {
        return repositorioGrauProficiencia.getAll();
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

    public List<GrauProficiencia> findByCompetenciaTecnica(String codigoCompetenciaTecnica) throws SQLException {
        return repositorioGrauProficiencia.findByCompetenciaTecnica(codigoCompetenciaTecnica);
    }

    public List<GrauProficiencia> getAllByCompetenciaTecnica(String codigoCompetenciaTecnica) throws SQLException {
        return repositorioGrauProficiencia.getAllByCompetenciaTecnica(codigoCompetenciaTecnica);
    }
    
    public GrauProficiencia findByValor(int grau) throws SQLException {
        return repositorioGrauProficiencia.findByGrau(grau);
    }

    public GrauProficiencia findByGrauECompetenciaTecnica(int grau, String codigoCompetenciaTecnica) throws SQLException {
        return repositorioGrauProficiencia.findByGrauECompetencia(grau, codigoCompetenciaTecnica);
    }
    
}
