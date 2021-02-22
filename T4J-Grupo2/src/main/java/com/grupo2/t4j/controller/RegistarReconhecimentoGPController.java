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
public class RegistarReconhecimentoGPController {
    
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioReconhecimentoGP repositorioReconhecimentoGP = fabricaRepositorios.getRepositorioReconhecimentoGP();
    
    private RepositorioReconhecimentoGPInMemory repositorioReconhecimentoGPInMemory;
    private FicheiroRepositorioReconhecimentoGP ficheiroRGP;
    
     public boolean registarReconhecimentoGP(String email, String nome, String nif, String
            telefone, String codigoEnderecoPostal) throws SQLException {
        
        Freelancer freelancer = new Freelancer(new Email(email), nome, password, nif, 
                telefone, codigoEnderecoPostal);

        registarFreelancerComoUtilizador(freelancer);

        return repositorioFreelancer.save(freelancer);
    }

    public List<Freelancer> getAll() throws SQLException{
        return repositorioFreelancer.getAll();
    }
   
    public Freelancer findByNif(String NIF) throws SQLException{
        return repositorioFreelancer.findByNif(NIF);
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
