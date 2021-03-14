package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.Candidatura;
import com.grupo2.t4j.persistence.*;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author acris
 */
public class EfectuarCandidaturaController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioAnuncio repositorioAnuncio = fabricaRepositorios.getRepositorioAnuncio();
    private RepositorioReconhecimentoGP repositorioReconhecimentoGP = fabricaRepositorios.getRepositorioReconhecimentoGP();
    private RepositorioTarefa repositorioTarefa = fabricaRepositorios.getRepositorioTarefa();
    private RepositorioCompetenciaTecnica repositorioCompetenciaTecnica = fabricaRepositorios.getRepositorioCompetenciaTecnica();
    private RepositorioGrauProficiencia repositorioGrauProficiencia = fabricaRepositorios.getRepositorioGrauProficiencia();
    private RepositorioCaracterizacaoCT repositorioCaracterizacaoCT = fabricaRepositorios.getRepositorioCaracterizacaoCT();
    private RepositorioCategoriaTarefa repositorioCategoriaTarefa = fabricaRepositorios.getRepositorioCategoriaTarefa();
    private RepositorioCandidatura repositorioCandidatura = fabricaRepositorios.getRepositorioCandidatura();

    public EfectuarCandidaturaController() throws SQLException {
    }

    /**
     * Regista uma nova candidatura
     * @param valorPretendido
     * @param numeroDias
     * @param txtApresentacao
     * @param txtMotivacao
     * @param idAnuncio
     * @param emailFreelancer
     * @return
     * @throws SQLException 
     */
    public boolean registarCandidatura(double valorPretendido, int numeroDias, 
            String txtApresentacao, String txtMotivacao, int idAnuncio, String 
            emailFreelancer) throws SQLException {
        Candidatura candidatura = new Candidatura(valorPretendido, numeroDias, 
            txtApresentacao, txtMotivacao,idAnuncio, emailFreelancer);

        return repositorioCandidatura.save(candidatura);
    }

    /**
     * Devolve a lista de candidaturas de um freelancer
     * @param emailFreelancer
     * @return
     * @throws SQLException 
     */
    public ArrayList<Candidatura> findByEmail(String emailFreelancer) throws SQLException {
        return repositorioCandidatura.findByEmail(emailFreelancer);
    }
}
