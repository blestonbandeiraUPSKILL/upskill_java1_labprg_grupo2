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
    /*private RepositorioEnderecoPostal repositorioEnderecoPostal = fabricaRepositorios.getRepositorioEnderecoPostal();*/
    
    private AlgoritmoGeradorPasswords algoritmoGeradorPasswords;
    private RepositorioFreelancerInMemory repositorioFreelancerInMemory;
    private FicheiroRepositorioFreelancer ficheiroF;
    
    public boolean registarFreelancer(String emailFree, String nome, String nif, 
            String telefone, String arruamento, String numeroPorta, String localidade, String codPostal) throws SQLException {

        AlgoritmoGeradorPasswords algoritmoGeradorPasswords = new AlgoritmoGeradorPasswords();
        Password password = new Password(algoritmoGeradorPasswords.geraPassword());
        
        EnderecoPostal endereco = new EnderecoPostal(arruamento, numeroPorta, localidade, codPostal);
        Freelancer freelancer = new Freelancer(new Email(emailFree), nome, password, nif, telefone, endereco.getCodigoEnderecoPostal());

        registarFreelancerComoUtilizador(freelancer);

        return repositorioFreelancer.save(freelancer);
    }

    public List<Freelancer> getAll() throws SQLException{
        return repositorioFreelancer.getAll();
    }
    
    public List<String> getAllEmails() throws SQLException{
        return repositorioFreelancer.getAllEmails();
    }
   
    public Freelancer findByNif(String NIF) throws SQLException{
        return repositorioFreelancer.findByNif(NIF);
    }
    
    public Freelancer findByEmail(String emailFree) throws SQLException{
        return repositorioFreelancer.findByEmail(emailFree);
    }

    ///////API
    public boolean registarFreelancerComoUtilizador(Freelancer freelancer) throws SQLException {
        String nome = freelancer.getNome();
        Email email = freelancer.getEmail();
        Password password = freelancer.getPassword();

        Utilizador utilizador = new Utilizador(email, nome, password);

        return UsersAPI.getInstance().registerUserWithRoles(email, nome, password, "freelancer")
                && repositorioUtilizador.save(utilizador);
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
