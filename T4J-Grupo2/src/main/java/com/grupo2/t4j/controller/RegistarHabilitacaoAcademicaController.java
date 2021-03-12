package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.HabilitacaoAcademica;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioHabilitacaoAcademica;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.List;
public class RegistarHabilitacaoAcademicaController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioHabilitacaoAcademica repositorioHabilitacaoAcademica = fabricaRepositorios.getRepositorioHabilitacaoAcademica();


   /**
     * Registar habilitação académica boolean
     *
     * @param grau as grau atribuído pela habilitação académica
     * @param designacaoCurso as designação do curso
     * @param nomeInstituicao as nome da instituição académica
     * @param mediaCurso as média de final de curso em valores
     * @param emailFreelancer as email do freelancer
     * @return boolean
     */     
    public boolean registarHabilitacaoAcademica(String grau, String designacaoCurso,
           String nomeInstituicao, double mediaCurso, String emailFreelancer) throws SQLException {

        HabilitacaoAcademica habilitacao = new HabilitacaoAcademica(grau, designacaoCurso, nomeInstituicao, mediaCurso);

        return repositorioHabilitacaoAcademica.save(habilitacao, emailFreelancer);
    }

    public List<HabilitacaoAcademica> getAll(String emailFreelancer) throws SQLException{
        return repositorioHabilitacaoAcademica.getAll(emailFreelancer);
    }
        
    public HabilitacaoAcademica findById(int idHabilitacao) throws SQLException{
        return repositorioHabilitacaoAcademica.findById(idHabilitacao);
    }



}
