package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.Anuncio;
import com.grupo2.t4j.domain.Candidatura;
import com.grupo2.t4j.domain.Organizacao;
import com.grupo2.t4j.domain.Tarefa;
import com.grupo2.t4j.dto.AnuncioDTO;
import com.grupo2.t4j.dto.CandidaturaDTO;
import com.grupo2.t4j.dto.OrganizacaoDTO;
import com.grupo2.t4j.dto.TarefaDTO;
import com.grupo2.t4j.persistence.*;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private RepositorioOrganizacao repositorioOrganizacao = fabricaRepositorios.getRepositorioOrganizacao();

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
    public List<CandidaturaDTO> findByEmail(String emailFreelancer) throws SQLException {
        List<Candidatura> candidaturas = repositorioCandidatura.findByEmail(emailFreelancer);
        List<CandidaturaDTO> candidaturasDTO = new ArrayList<>();

        for(Candidatura candidatura : candidaturas)
        {
            candidaturasDTO.add((CandidaturaDTO) candidatura.toDTO());
        }

        return candidaturasDTO;
    }

    /**
     * Retorna uma candidatura
     * @param idCandidatura
     * @return
     * @throws SQLException
     */
    public CandidaturaDTO findById(int idCandidatura) throws SQLException {
        Candidatura candidatura = repositorioCandidatura.findById(idCandidatura);
        return (CandidaturaDTO) candidatura.toDTO();
    }

    /**
     * Retorna o anúncio de acordo com o id do anúncio
     * @param idAnuncio - id do anúncio
     * @return
     * @throws SQLException
     */
    public AnuncioDTO getAnuncio(int idAnuncio) throws SQLException {
        Anuncio anuncio = repositorioAnuncio.getAnuncio(idAnuncio);
        return (AnuncioDTO) anuncio.toDTO();
    }

    /**
     * Retorna uma tarefa de acordo com o id do Anúncio
     * @param idAnuncio
     * @return
     * @throws SQLException
     */
    public TarefaDTO findTarefa(int idAnuncio) throws SQLException {
        Tarefa tarefa = repositorioTarefa.findTarefa(idAnuncio);
        return (TarefaDTO) tarefa.toDTO();
    }

    /**
     * Retorna uma organização com base em seu nif
     * @param nif
     * @return
     * @throws SQLException
     */
    public OrganizacaoDTO findByNif(String nif) throws SQLException{
        Organizacao organizacao = repositorioOrganizacao.findByNif(nif);
        return (OrganizacaoDTO) organizacao.toDTO();
    }
}
