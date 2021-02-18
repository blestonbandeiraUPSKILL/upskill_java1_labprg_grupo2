/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.controller.UsersAPI;
import com.grupo2.t4j.persistence.database.RepositorioOrganizacaoDatabase;
import com.grupo2.t4j.persistence.inmemory.*;

import java.sql.SQLException;

/**
 *
 * @author acris
 */
public class Plataforma {

  /**
     * Atributos Plataforma
     */

    private String designacao;
    private static Plataforma plataforma;
    private RepositorioOrganizacaoDatabase repositorioOrganizacao;
    private RepositorioCategoriaTarefaInMemory repositorioCategoriaTarefaInMemory;
    private RepositorioColaboradorInMemory repositorioColaboradorInMemory;
    private RepositorioCompetenciaTecnicaInMemory repositorioCompetenciaTecnicaInMemory;
    private RepositorioTarefaInMemory repositorioTarefaInMemory;
    private RepositorioUtilizadorInMemory repositorioUtilizadorInMemory;

    private AlgoritmoGeradorPasswords algoritmoGeradorPasswords;
    private UsersAPI usersAPI;


    /**
     * Construtor Classe Plataforma
     */
    public Plataforma() throws SQLException {
        algoritmoGeradorPasswords = new AlgoritmoGeradorPasswords();
        usersAPI = new UsersAPI();
        repositorioOrganizacao = RepositorioOrganizacaoDatabase.getInstance();
        repositorioCategoriaTarefaInMemory = RepositorioCategoriaTarefaInMemory.getInstance();
        repositorioColaboradorInMemory = RepositorioColaboradorInMemory.getInstance();
        repositorioUtilizadorInMemory = RepositorioUtilizadorInMemory.getInstance();
        repositorioCompetenciaTecnicaInMemory = RepositorioCompetenciaTecnicaInMemory.getInstance();
        repositorioTarefaInMemory = RepositorioTarefaInMemory.getInstance();
    }

    /**
     * @return the designacao
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * @param designacao the designacao to set
     */    
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public static Plataforma getInstance() throws SQLException {
        if(Plataforma.plataforma == null) {
            Plataforma.plataforma = new Plataforma();
        }
        return plataforma;
    }

    public RepositorioOrganizacaoDatabase getRepositorioOrganizacao() {
        return repositorioOrganizacao;
    }

    public RepositorioCategoriaTarefaInMemory getRepositorioCategoria() {
        return repositorioCategoriaTarefaInMemory;
    }

    public AlgoritmoGeradorPasswords getAlgoritmoGeradorPwd() {
        return algoritmoGeradorPasswords;
    }

    public RepositorioColaboradorInMemory getRepositorioColaborador() {
        return repositorioColaboradorInMemory;
    }

    public RepositorioCompetenciaTecnicaInMemory getRepositorioCompetenciaTecnica() {
        return repositorioCompetenciaTecnicaInMemory;
    }

    public RepositorioTarefaInMemory getRepositorioTarefa() {
        return repositorioTarefaInMemory;
    }

    public RepositorioUtilizadorInMemory getRepositorioUtilizador() {
        return repositorioUtilizadorInMemory;
    }

    public UsersAPI getUsersAPI() {
        return usersAPI;
    }

    public void resetUserAPI() {
        this.usersAPI = new UsersAPI();
    }

}
