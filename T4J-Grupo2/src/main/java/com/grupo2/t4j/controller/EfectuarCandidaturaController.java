package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.*;
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

    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
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

    public List<ReconhecimentoGP> findReconhecimentoFreelancer(String email)throws SQLException {
        return repositorioReconhecimentoGP.findByEmail(email);
    }

    public List<Tarefa> findTarefasElegiveis(String email) throws SQLException {
        List<Tarefa> tarefasElegiveis = new ArrayList<>();
        List<Tarefa> listaTarefas = new ArrayList<>();
        listaTarefas = repositorioTarefa.getAll();

        for (Tarefa tarefa : listaTarefas) {
            List<CaracterizacaoCT> competenciasDaTarefa = repositorioCategoriaTarefa.findByCodigo(
                    tarefa.getCodigoCategoriaTarefa()).getCompTecnicasCaracter();
            for (CaracterizacaoCT cct : competenciasDaTarefa) {
                if (cct.getObrigatoriedade() == Obrigatoriedade.OBRIGATORIA
                        && comparaCompetencias(email, cct.getCodigoCategoria())
                        && comparaGrau(email, cct.getCodigoCategoria(), cct.getCodigoGP())) {

                    tarefasElegiveis.add(tarefa);
                }
            }
        }
        return null;
    }

    public List<Anuncio> findAnunciosElegiveis(String email) throws SQLException {
        return repositorioAnuncio.findAnunciosElegiveis(email);
    }

    public List<ReconhecimentoGP> competenciasTecnicasDoFreelancer(String email) throws SQLException {
       return repositorioReconhecimentoGP.getAll(email);

    }

    public boolean comparaCompetencias(String email, String idCompetenciaTecnica) throws SQLException {
        if (competenciasTecnicasDoFreelancer(email).contains(repositorioCompetenciaTecnica.findByCodigo(idCompetenciaTecnica))) {
            return true;
        }
        return false;
    }

    public boolean comparaGrau(String email, String idCompetenciaTecnica, int grau) throws SQLException {
        ReconhecimentoGP rgp = repositorioReconhecimentoGP.findByEmailCompetencia(email, idCompetenciaTecnica);
        int gp = rgp.getIdGrauProficiencia();
        if (gp >= grau) {
            return true;
        }
        return false;

    }

    public boolean registarCandidatura(double valorPretendido, int numeroDias, 
            String txtApresentacao, String txtMotivacao, int idAnuncio, String 
            emailFreelancer) throws SQLException {
        Candidatura candidatura = new Candidatura(valorPretendido, numeroDias, 
            txtApresentacao, txtMotivacao,idAnuncio, emailFreelancer);

        return repositorioCandidatura.save(candidatura);
    }

    public Candidatura findByEmail(String emailFreelancer) throws SQLException {
        return repositorioCandidatura.findByEmail(emailFreelancer);
    }
}
