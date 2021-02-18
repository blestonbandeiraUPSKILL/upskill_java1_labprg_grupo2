/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.files.*;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.*;
import com.grupo2.t4j.persistence.database.*;
import com.grupo2.t4j.persistence.inmemory.*;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class RegistarFreelancerController {
    
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioFreelancer repositorioFreelancer = fabricaRepositorios.getRepositorioFreelancer();
    
    private RepositorioUtilizador repositorioUtilizador = fabricaRepositorios.getRepositorioUtilizador();

    private AlgoritmoGeradorPasswords algoritmoGeradorPasswords;
    private RepositorioFreelancerInMemory repositorioFreelancerInMemory;
    private FicheiroRepositorioFreelancer ficheiroF;

    public boolean registarFreelancer(Email email, String nome, Password password, Rolename rolename,
            String NIF, EnderecoPostal enderecoPostalFreelancer) {
        Freelancer freelancer = new Freelancer(email, nome, password, rolename,
            NIF, enderecoPostalFreelancer);

        return repositorioFreelancer.save(freelancer);
    }

    public List<Freelancer> getAll() {
        return repositorioFreelancer.getAll();
    }

    ///////API
    public boolean registarFreelancer(Freelancer freelancer) throws SQLException {
        String nome = freelancer.getNome();
        Email email = freelancer.getEmail();

        AlgoritmoGeradorPasswords algoritmoGeradorPasswords = new AlgoritmoGeradorPasswords();
        Password password = new Password(algoritmoGeradorPasswords.geraPassword());
        freelancer.setPassword(password);

        UsersAPI usersAPI = new UsersAPI();
        Utilizador utilizador = new Utilizador(email, nome, password, Rolename.FREELANCER);

        return usersAPI.registerUserWithRoles(email, nome, password, "freelancer") &&
                repositorioUtilizador.save(utilizador);
    }


    //////FICHEIROS////////
    public RegistarFreelancerController() {
        ficheiroF = new FicheiroRepositorioFreelancer();
        
        desserializar();
    }
    public boolean serializar() {
        return ficheiroF.serializar(repositorioFreelancerInMemory);
    }

    public boolean serializar(File ficheiroExportar) {
        return ficheiroF.serializar(ficheiroExportar, repositorioFreelancerInMemory);
    }

    public void desserializar() {
        repositorioFreelancerInMemory = ficheiroF.desserializar();
    }

    public int desserializar(File ficheiroImportar) {
        RepositorioFreelancerInMemory listaFreelancerImportada = ficheiroF.desserializar(ficheiroImportar);

        return repositorioFreelancerInMemory.adicionarListaFreelancer(listaFreelancerImportada);
    }  
}
