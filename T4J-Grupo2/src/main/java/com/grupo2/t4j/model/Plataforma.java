/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.controller.UsersAPI;
import com.grupo2.t4j.repository.*;

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
    private RepositorioOrganizacao repositorioOrganizacao;
    private RepositorioCategoriaTarefa repositorioCategoriaTarefa;
    private RepositorioColaborador repositorioColaborador;
    private RepositorioCompetenciaTecnica repositorioCompetenciaTecnica;
    private RepositorioTarefa repositorioTarefa;
    private RepositorioUtilizador repositorioUtilizador;

    private AlgoritmoGeradorPasswords algoritmoGeradorPasswords;
    private UsersAPI usersAPI;


    /**
     * Construtor Classe Plataforma
     */
    public Plataforma() {
        //algoritmoGeradorPasswords = new AlgoritmoGeradorPasswords();
        usersAPI = new UsersAPI();
        repositorioOrganizacao = RepositorioOrganizacao.getInstance();
        repositorioCategoriaTarefa = RepositorioCategoriaTarefa.getInstance();
        //repositorioColaborador = new RepositorioColaborador();
        //repositorioUtilizador = new RepositorioUtilizador();
        repositorioCompetenciaTecnica = RepositorioCompetenciaTecnica.getInstance();
        //repositorioTarefa = new RepositorioTarefa();
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

    public static Plataforma getInstance() {
        if(Plataforma.plataforma == null) {
            Plataforma.plataforma = new Plataforma();
        }
        return plataforma;
    }

    public RepositorioOrganizacao getRepositorioOrganizacao() {
        return repositorioOrganizacao;
    }

    public RepositorioCategoriaTarefa getRepositorioCategoria() {
        return repositorioCategoriaTarefa;
    }

    public AlgoritmoGeradorPasswords getAlgoritmoGeradorPwd() {
        return algoritmoGeradorPasswords;
    }

    public RepositorioColaborador getRepositorioColaborador() {
        return repositorioColaborador;
    }

    public RepositorioCompetenciaTecnica getRepositorioCompetenciaTecnica() {
        return repositorioCompetenciaTecnica;
    }

    public RepositorioTarefa getRepositorioTarefa() {
        return repositorioTarefa;
    }

    public RepositorioUtilizador getRepositorioUtilizador() {
        return repositorioUtilizador;
    }

    public UsersAPI getUsersAPI() {
        return usersAPI;
    }

    public void resetUserAPI() {
        this.usersAPI = new UsersAPI();
    }

}
