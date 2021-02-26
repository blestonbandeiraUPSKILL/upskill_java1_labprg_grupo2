package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.*;
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
                if (cct.getObrigatoriedade() == Obrigatoriedade.OBRIGATORIO
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

    public boolean comparaGrau(String email, String idCompetenciaTecnica, int grau) throws SQLException {
        ReconhecimentoGP rgp = repositorioReconhecimentoGP.findByEmailCompetencia(email, idCompetenciaTecnica);
        int gp = rgp.getIdGrauProficiencia();
        if (gp >= grau) {
            return true;
        }
        return false;

    }

}
