/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.Tarefa;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioTarefa;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.List;

public class RegistarTarefaController {

    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioTarefa repositorioTarefa = fabricaRepositorios.getRepositorioTarefa();

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



}
