/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.Anuncio;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.model.Data;
import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.model.HabilitacaoAcademica;
import com.grupo2.t4j.model.Obrigatoriedade;
import com.grupo2.t4j.model.ReconhecimentoGP;
import com.grupo2.t4j.model.Tarefa;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAnuncio;
import com.grupo2.t4j.persistence.RepositorioCandidatura;
import com.grupo2.t4j.persistence.RepositorioCaracterizacaoCT;
import com.grupo2.t4j.persistence.RepositorioCategoriaTarefa;
import com.grupo2.t4j.persistence.RepositorioCompetenciaTecnica;
import com.grupo2.t4j.persistence.RepositorioGrauProficiencia;
import com.grupo2.t4j.persistence.RepositorioReconhecimentoGP;
import com.grupo2.t4j.persistence.RepositorioTarefa;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
public class EfectuarCandidaturaController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioAnuncio repositorioAnuncio = fabricaRepositorios.getRepositorioAnuncio();
    private RepositorioReconhecimentoGP repositorioReconhecimentoGP = fabricaRepositorios.getRepositorioReconhecimentoGP();
    private RepositorioTarefa repositorioTarefa = fabricaRepositorios.getRepositorioTarefa();
    private RepositorioCompetenciaTecnica repositorioCompetenciaTecnica = fabricaRepositorios.getRepositorioCompetenciaTecnica();
    //private RepositorioGrauProficiencia repositorioGrauProficiencia = fabricaRepositorios.getRepositorioGrauProficiencia();
    private RepositorioCaracterizacaoCT repositorioCaracterizacaoCT = fabricaRepositorios.getRepositorioCaracterizacaoCT();
    private RepositorioCategoriaTarefa repositorioCategoriaTarefa = fabricaRepositorios.getRepositorioCategoriaTarefa();

    public List<ReconhecimentoGP> findReconhecimentoFreelancer(String email) {
        return repositorioReconhecimentoGP.findByEmail(email);
    }

    public List<Tarefa> findTarefasElegiveis(String email) throws SQLException {
        List<Tarefa> tarefasElegiveis = new ArrayList<>();
        List<Tarefa> listaTarefas = repositorioTarefa.getAll();
        List<ReconhecimentoGP> listaReconhecimentos = findReconhecimentoFreelancer(email);
        for (Tarefa tarefa : listaTarefas) {
            List<CaracterizacaoCT> competenciasDaTarefa = repositorioCategoriaTarefa.findByCodigo(
                    tarefa.getCodigoCategoriaTarefa()).getCompTecnicasCaracter();
            for (CaracterizacaoCT cct : competenciasDaTarefa) {
                if (cct.getObrigatoriedade() == Obrigatoriedade.OBRIGATORIO
                        && comparaCompetencias(email, cct.getCodigoCompetenciaTecnica())
                        && comparaGrau(email, cct.getCodigoCompetenciaTecnica(), cct.getCodigoGP())) {

                    tarefasElegiveis.add(tarefa);
                }
            }
        }

        return null;
    }

    public List<Anuncio> findAnunciosElegiveis(String email) throws SQLException {
        Anuncio anuncio = null;
        List<Anuncio> anunciosElegiveis = new ArrayList<>();
        Data dataAtual = Data.dataActual();
        for (Tarefa tarefa : findTarefasElegiveis(email)) {
            anuncio = repositorioAnuncio.findAnuncioByIdTarefa(tarefa.getReferencia());
            if (dataAtual.isMaior(anuncio.getDtInicioCand()) && dataAtual.isMaior(anuncio.getDtInicioPub())) {
                if (anuncio != null) {
                    anunciosElegiveis.add(anuncio);
                }
            }
        }
        return anunciosElegiveis;
    }

    public List<CompetenciaTecnica> competenciasTecnicasDoFreelancer(String email) throws SQLException {

        List<CompetenciaTecnica> listaCompetencias = new ArrayList<>();
        List<ReconhecimentoGP> listaReconhecimentos = repositorioReconhecimentoGP.findByEmail(email);
        for (ReconhecimentoGP rgp : listaReconhecimentos) {
            listaCompetencias.add(repositorioCompetenciaTecnica.findByCodigo(rgp.getIdCompetenciaTecnica()));
        }
        return listaCompetencias;
    }

    public boolean comparaCompetencias(String email, String idCompetenciaTecnica) throws SQLException {
        if (competenciasTecnicasDoFreelancer(email).contains(repositorioCompetenciaTecnica.findByCodigo(idCompetenciaTecnica))) {
            return true;
        }
        return false;
    }

    public boolean comparaGrau(String email, String idCompetenciaTecnica, String grau) {
        ReconhecimentoGP rgp = repositorioReconhecimentoGP.findByEmailCompetencia(email, idCompetenciaTecnica);
        String gp = rgp.getIdGrauProficiencia();
        if (Integer.parseInt(gp) >= Integer.parseInt(grau)) {
            return true;
        }
        return false;

    }

}