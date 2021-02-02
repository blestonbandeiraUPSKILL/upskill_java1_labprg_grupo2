/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

/**
 *
 * @author acris
 */
public class Plataforma {

    /**
     * Atributos Plataforma
     */
    private String designacao;
    private String designacao;
    private static Plataforma plataforma;
    private RepositorioOrganizacao repositorioOrganizacao;
    private RepositorioCategoria repositorioCategoria;
    private RepositorioColaborador repositorioColaborador;
    private RepositorioCompetenciaTecnica repositorioCompetenciaTecnica;
    private RepositorioTarefa repositorioTarefa;
    private RepositorioUtilizador repositorioUtilizador;

    private IAlgoritmoGeradorPasswords algoritmoGeradorPasswords;
    private UsersAPI usersAPI;

    public Plataforma() {
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

}
