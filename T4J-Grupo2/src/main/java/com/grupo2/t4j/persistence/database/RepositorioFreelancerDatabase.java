/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.database;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.FreelancerDuplicadoException;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.RepositorioFreelancer;
import java.util.ArrayList;

public class RepositorioFreelancerDatabase implements RepositorioFreelancer{
    
    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os Freelancers
     */
    private static RepositorioFreelancerDatabase repositorioFreelancerDatabase;

    /**
     * Inicializa o Repositório de Freelancers
     */
    RepositorioFreelancerDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Utilizadores
     *
     * @return RepositorioUtilizadorDatabase
     */
    public static RepositorioFreelancerDatabase getInstance(){
        if(repositorioFreelancerDatabase == null) {
            repositorioFreelancerDatabase = new RepositorioFreelancerDatabase();
        }
        return repositorioFreelancerDatabase;
    }


    @Override
    public void save(Email email, String nome, Password password, String nif, String codigoEnderecoPostal) throws FreelancerDuplicadoException {

    }

    @Override
    public boolean save(Freelancer freelancer) {
        return false;
    }

    @Override
    public Freelancer findByNif(String nif) {
        return null;
    }

    @Override
    public ArrayList<Freelancer> getAll() {
        return null;
    }
}
