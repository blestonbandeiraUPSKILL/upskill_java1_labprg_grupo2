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
import com.grupo2.t4j.utils.DBConnectionHandler;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioFreelancerDatabase implements RepositorioFreelancer{
    
    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os Freelancers
     */
    private static RepositorioFreelancerDatabase repositorioFreelancerDatabase;
    
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";
    
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
    public void save(Email email, String nome, Password password, String nif, 
            String telefone, String codigoEnderecoPostal) throws FreelancerDuplicadoException,
            SQLException{

    }

    @Override
    public boolean save(Freelancer freelancer) throws FreelancerDuplicadoException,
            SQLException {
        return false;
    }

    @Override
    public Freelancer findByNif(String nif) throws SQLException{
        return null;
    }
    
     @Override
    public Freelancer findByEmail(String emailFree) {
        return null;
    }

    @Override
    public ArrayList<Freelancer> getAll() throws SQLException {
        return null;
    }
}
