/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.CandidaturaDuplicadaException;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.RepositorioCandidatura;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CAD
 */
public class RepositorioCandidaturaDatabase implements RepositorioCandidatura{
    
    /**
     * Define uma instância estática do Repositório em que estão
     * registados todas as Candidaturas
     */
    private static RepositorioCandidaturaDatabase repositorioCandidaturaDatabase;
    
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";
    
    /**
     * Inicializa o Repositório de Candidaturas
     */
    private RepositorioCandidaturaDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório dos Candidaturas
     *
     * @return RepositorioCandidaturaDatabase
     */
    public static RepositorioCandidaturaDatabase getInstance(){
        if(repositorioCandidaturaDatabase == null) {
            repositorioCandidaturaDatabase = new RepositorioCandidaturaDatabase();
        }
        return repositorioCandidaturaDatabase;
    }
    
    @Override
    public boolean save(String idCandidatura, String emailFreelancer, double valorPretendido, 
            int numeroDias, String txtApresentacao,String txtMotivacao) throws CandidaturaDuplicadaException, SQLException{
        
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, this.password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createCandidatura(?, ?, ?, ?, ?, ?) } ");

        if (findById(idCandidatura) == null && findByEmail(emailFreelancer) == null){

            try {
                connection.setAutoCommit(false);

                callableStatement.setString(1, idCandidatura);
                callableStatement.setString(2, emailFreelancer);
                callableStatement.setString(3, Double.toString(valorPretendido));
                callableStatement.setString(4, Integer.toString(numeroDias));
                callableStatement.setString(5, txtApresentacao);
                callableStatement.setString(6, txtMotivacao);
                callableStatement.executeQuery();

                connection.commit();
                return true;
            }
            catch (SQLException exception) {
                exception.printStackTrace();
                exception.getSQLState();
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                }
                catch (SQLException sqlException) {
                    sqlException.getErrorCode();
                }
            }

            finally {
                dbConnectionHandler.closeAll();
            }
        }              
        return false;
    }

    public boolean save(Candidatura candidatura) throws SQLException{
        return false;
    }

    @Override
    public Candidatura findById(String idCandidatura) throws SQLException{
        
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatementOrg = connection.prepareCall(
                 "{CALL findCandidaturaById(?)}");

        try {
            connection.setAutoCommit(false);

            callableStatementOrg.setString(1, idCandidatura);
            callableStatementOrg.executeQuery();

            return null;

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();


        }

        return new Candidatura();
        
        return null;
    }
    
    public Candidatura findByEmail (String emailFreelancer){
        return null;
    }

    public ArrayList<Candidatura> getAll(){
        return null;
    }
}
