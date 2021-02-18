/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.model;

import com.grupo2.t4j.persistence.database.RepositorioOrganizacaoDatabase;
import com.grupo2.t4j.persistence.inmemory.*;

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



    /**
     * Construtor Classe Plataforma
     */
    public Plataforma(){
    }




}
