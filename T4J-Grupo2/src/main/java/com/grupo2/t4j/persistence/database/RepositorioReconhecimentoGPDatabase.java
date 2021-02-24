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

import com.grupo2.t4j.exception.ReconhecimentoDuplicadoException;
import java.sql.SQLException;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.RepositorioReconhecimentoGP;
import com.grupo2.t4j.utils.DBConnectionHandler;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioReconhecimentoGPDatabase implements RepositorioReconhecimentoGP {
    
    /**
     * Define uma instância estática do Repositório em que estão
     * registadas as Competências Técnicas de grau de proficiência reconhecidaas
     * de todos os Freelancers
     */
    private static RepositorioReconhecimentoGPDatabase repositorioReconhecimentoGPDatabase;
    
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";
    
    /**
     * Inicializa o Repositório de todas as Competências Técnicas de grau de proficiência 
     * reconhecidaas de todos os Freelancers
     */
    RepositorioReconhecimentoGPDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Reconhecimento de GP
     *
     * @return RepositorioReconhecimentoGPDatabase
     */
    public static RepositorioReconhecimentoGPDatabase getInstance(){
        if(repositorioReconhecimentoGPDatabase == null) {
            repositorioReconhecimentoGPDatabase = new RepositorioReconhecimentoGPDatabase();
        }
        return repositorioReconhecimentoGPDatabase;
    }


    @Override
    public boolean save(int idGrauProficiencia, Data dataReconhecimento, 
             Email emailFreelancer, String idCompetenciaTecnica) throws  ReconhecimentoDuplicadoException,
            SQLException{
        return false;

    }

    @Override
    public boolean save(ReconhecimentoGP reconhecimentoGP) throws ReconhecimentoDuplicadoException,
            SQLException {
        return false;
    }
    
    @Override
    public ArrayList<ReconhecimentoGP> getAll() throws SQLException {
        return null;
    }
    
    @Override
    public ArrayList<ReconhecimentoGP> findByEmail(String email){
        return null;
    }
    
    @Override
    public ReconhecimentoGP findByEmailCompetencia(String email, String idCompetenciaTecnica){
        return null;
    }

    
}
