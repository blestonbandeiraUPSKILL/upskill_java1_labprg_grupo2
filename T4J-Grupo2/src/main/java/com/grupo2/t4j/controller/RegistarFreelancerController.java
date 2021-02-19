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

    public boolean registarFreelancer(String email, String nome, String nif, String
            telefone, String codigoEnderecoPostal) throws SQLException {

        AlgoritmoGeradorPasswords algoritmoGeradorPasswords = new AlgoritmoGeradorPasswords();
        Password password = new Password(algoritmoGeradorPasswords.geraPassword());

        Freelancer freelancer = new Freelancer(new Email(email), nome, password, nif, 
                telefone, codigoEnderecoPostal);

        registarFreelancerComoUtilizador(freelancer);

        return repositorioFreelancer.save(freelancer);
    }

    public List<Freelancer> getAll() {
        return repositorioFreelancer.getAll();
    }
    
    public Freelancer findByNif(String NIF) {
        return repositorioFreelancer.findByNif(NIF);
    }

    ///////API
    public boolean registarFreelancerComoUtilizador(Freelancer freelancer) throws SQLException {
        String nome = freelancer.getNome();
        Email email = freelancer.getEmail();
        Password password = freelancer.getPassword();

        UsersAPI usersAPI = new UsersAPI();
        Utilizador utilizador = new Utilizador(email, nome, password);

        return usersAPI.registerUserWithRoles(email, nome, password, "freelancer")
                && repositorioUtilizador.save(utilizador);
    }
}
