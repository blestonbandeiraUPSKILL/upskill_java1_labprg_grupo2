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
<<<<<<< Updated upstream
     * Atributos Plataforma
     */
=======
    * Atributos Plataforma
    */
>>>>>>> Stashed changes
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

    /**
     * Construtor Classe Plataforma
     */
    public Plataforma() {
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

}
