/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.model.Tarefa;
import com.grupo2.t4j.persistence.*;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistarTarefaController {

    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioTarefa repositorioTarefa = fabricaRepositorios.getRepositorioTarefa();
    private RepositorioGrauProficiencia repositorioGrauProficiencia = fabricaRepositorios.getRepositorioGrauProficiencia();
    private RepositorioFreelancer repositorioFreelancer = fabricaRepositorios.getRepositorioFreelancer();
    private RepositorioCaracterizacaoCT repositorioCaracterizacaoCT = fabricaRepositorios.getRepositorioCaracterizacaoCT();

    public RegistarTarefaController() throws SQLException {
    }

    public List<Tarefa> getAllOrganizacao(String nifOrganizacao) throws SQLException {
        return repositorioTarefa.getAllOrganizacao(nifOrganizacao);
    }

    /**
    * Regista nova tarefa boolean
    *
    * @param codigoCategoriaTarefa as código da categoria da tarefa
    * @param referencia as referência da tarefa
    * @param designacao as designação da tarefa
    * @param descInformal as descrição informal
    * @param descTecnica as descrição técnica
    * @param duracao as tempo estimado de realização da tarefa em horas
    * @param custo as preço pela tarefa
    * @param nifOrganizacao as nif da organização
    * @param emailColaborador  as email do colaborador
    * @return boolean
    */
    public boolean registarTarefa( String codigoCategoriaTarefa,
                             String referencia, String designacao,
                             String descInformal, String descTecnica,
                             int duracao, double custo, String nifOrganizacao, String emailColaborador) throws SQLException{

        Tarefa tarefa = new Tarefa(referencia, nifOrganizacao, designacao, descInformal,
                descTecnica, duracao, custo,
                codigoCategoriaTarefa,  emailColaborador);

        return repositorioTarefa.save(tarefa);
    }

    public List<Tarefa> findByCategoria(String codigoCategoria) throws SQLException {
        return repositorioTarefa.findByCategoria(codigoCategoria);
    }

    public List<Tarefa> findByColaboradorENif(String email, String nifOrganizacao) throws SQLException{
        return repositorioTarefa.findByColaboradorENif(email, nifOrganizacao);
    }
    
    public List<Tarefa> findTarefasPublicadas(List<String> referenciasTarefa, String nifOrganizacao, String emailColaborador) throws SQLException{
        return repositorioTarefa.findTarefasPublicadas(referenciasTarefa, nifOrganizacao, emailColaborador);
    }
    
    public List<Tarefa> findTarefasNaoPublicadas(List<String> referenciasTarefa, String email, String nifOrganizacao) throws SQLException{
        return repositorioTarefa.findTarefasNaoPublicadas(referenciasTarefa, email, nifOrganizacao);
    }

    public List<String> findRefenciaTarefa(String nifOrganizacao) throws SQLException {
        return repositorioTarefa.findReferenciaTarefa(nifOrganizacao);
    }

    public Tarefa findTarefa(int idAnuncio) throws SQLException {
        return repositorioTarefa.findTarefa(idAnuncio);
    }

    public List<GrauProficiencia> getAllGrausTarefa(Tarefa tarefa) throws SQLException {
        return repositorioGrauProficiencia.getAllGrausTarefa(tarefa);
    }

    public List<GrauProficiencia> getAllGrausFreelancer(String emailFreelancer) throws SQLException {
        return repositorioFreelancer.getAllGrausFreelancer(emailFreelancer);
    }

    public List<CaracterizacaoCT> findCTSByCategoria(String codigoCategoria) throws SQLException {
        return repositorioCaracterizacaoCT.findByCategoria(codigoCategoria);
    }

    public List<Tarefa> getAllTarefasElegíveis(String emailFreelancer) throws SQLException {

        List<Tarefa> tarefasPublicadas = getAllTarefasPublicadas();

        List<GrauProficiencia> grausFreelancer = getAllGrausFreelancer(emailFreelancer);

        List<Tarefa> tarefasElegíveis = new ArrayList<>();
        for(Tarefa tarefa : tarefasPublicadas) {
            List<GrauProficiencia> grauTarefa = getAllGrausTarefa(tarefa);
            for(GrauProficiencia grau : grauTarefa) {
                for (GrauProficiencia grauProficienciaFreelancer : grausFreelancer) {
                    if (grau.getDesignacao().equals(grauProficienciaFreelancer.getDesignacao()) &&
                            grau.getGrau() < grauProficienciaFreelancer.getGrau()) {
                        tarefasElegíveis.add(tarefa);
                    }
                }
            }
        }

        return tarefasElegíveis;
    }


    public List<Tarefa> getAllTarefasPublicadas() throws SQLException {
        return repositorioTarefa.getAllTarefasPublicadas();
    }

    //findListGPsTarefasPublicadas(List<Tarefa>)
    public int findIdAnuncio(String nifOrganizacao, String referenciaTarefa) throws SQLException {
        return repositorioTarefa.findIdAnuncio(nifOrganizacao, referenciaTarefa);
    }
}
