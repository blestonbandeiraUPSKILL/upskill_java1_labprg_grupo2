/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.CandidaturaDuplicadaException;
import com.grupo2.t4j.model.Candidatura;
import com.grupo2.t4j.persistence.RepositorioCandidatura;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
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
    
    @Override
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
        
    }
    
    @Override
    public Candidatura findByEmail (String emailFreelancer) throws SQLException{
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatementOrg = connection.prepareCall(
                 "{CALL findCandidaturaByEmail(?)}");

        try {
            connection.setAutoCommit(false);

            callableStatementOrg.setString(1, emailFreelancer);
            callableStatementOrg.executeQuery();

            return null;

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();
        }

        return new Candidatura();
    }
    
    @Override
    public ArrayList<Candidatura> getAll() throws SQLException{
        
        ArrayList<Candidatura> candidaturas = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();        

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Candidatura"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idCandidatura = resultSet.getString(1);
                String emailFreelancer = resultSet.getString(2);
                String valorPretendido = resultSet.getString(3);
                String numeroDias = resultSet.getString(4);
                String txtApresentacao = resultSet.getString(5);
                String txtMotivacao = resultSet.getString(6);
                candidaturas.add(new Candidatura(idCandidatura, emailFreelancer, 
                        Double.parseDouble(valorPretendido),Integer.parseInt(numeroDias),
                txtApresentacao, txtMotivacao));
            }

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
        return candidaturas;
    }
}
