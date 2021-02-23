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

import com.grupo2.t4j.exception.HabilitacaoAcademicaDuplicadaException;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.RepositorioHabilitacaoAcademica;
import com.grupo2.t4j.utils.DBConnectionHandler;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioHabilitacaoAcademicaDatabase implements RepositorioHabilitacaoAcademica{
    
    /**
     * Define uma instância estática do Repositório em que estão
     * registados todas as Habilitações Acadêmicas dos Freelancers
     */
    private static RepositorioHabilitacaoAcademicaDatabase repositorioHabilitacaoAcademicaDatabase;
    
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";
    
    /**
     * Inicializa o Repositório das Habilitações Acadêmicas dos Freelancers
     */
    RepositorioHabilitacaoAcademicaDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Habilitações Acadêmicas dos Freelancers
     *
     * @return RepositorioHabilitacaoAcademicaDatabase
     */
    public static RepositorioHabilitacaoAcademicaDatabase getInstance(){
        if(repositorioHabilitacaoAcademicaDatabase == null) {
            repositorioHabilitacaoAcademicaDatabase = new RepositorioHabilitacaoAcademicaDatabase();
        }
        return repositorioHabilitacaoAcademicaDatabase;
    }


    @Override
    public boolean save(int idHabilitacao, String grau, String designacaoCurso,
           String nomeInstituicao, double mediaCurso, String emailFreelancer) throws HabilitacaoAcademicaDuplicadaException,
            SQLException{
        
        /*DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createHabilitacao(?, ?, ?, ?, ?, ?) } ");

        if (findById(idHabilitacao) == null){

            try {
                connection.setAutoCommit(false);

                callableStatement.setInt(1, idHabilitacao);
                callableStatement.setString(2, grau);
                callableStatement.setString(3, designacaoCurso);
                callableStatement.setString(4, nomeInstituicao);
                callableStatement.setDouble(5, mediaCurso);
                callableStatement.setString(6, emailFreelancer);
                                
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
        }*/
        return false;
    }

    @Override
    public boolean save(HabilitacaoAcademica habilitacaoAcademica) throws HabilitacaoAcademicaDuplicadaException,
            SQLException {
        return false;
    }

    @Override
    public HabilitacaoAcademica findById(int idHabilitacao) throws SQLException{
         
        /*DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatementOrg = connection.prepareCall(
                 "{CALL findById(?)}");

        try {
            connection.setAutoCommit(false);

            callableStatement.setString(2, grau);
            callableStatement.setString(3, designacaoCurso);
            callableStatement.setString(4, nomeInstituicao);
            callableStatement.setString(6, emailFreelancer);
            callableStatementOrg.executeQuery();

            return null;

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();


        }

        return new HabilitacaoAcademica();*/
        
        return null;
    }
    
    @Override
    public HabilitacaoAcademica findByGrauDesigInst(String grau, String designacaoCurso,
           String nomeInstituicao, String emailFreelancer) throws SQLException{
         
        /*DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatementOrg = connection.prepareCall(
                 "{CALL findByGrauDesigInst(?)}");

        try {
            connection.setAutoCommit(false);

            callableStatementOrg.setInt(1, idHabilitacao);
            callableStatementOrg.executeQuery();

            return null;

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();


        }

        return new HabilitacaoAcademica();*/
        
        return null;
    }

    @Override
    public ArrayList<HabilitacaoAcademica> getAll() throws SQLException {
        
        /*ArrayList<HabilitacaoAcademica> listaHabilitacao = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM HabilitacaoAcademica"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String idHabilitacao = resultSet.getInt(1);
                String grau = resultSet.getString(2);
                String designacaoCurso = resultSet.getString(3);
                String nomeInstituicao = resultSet.getString(4);
                Double mediaCurso = resultSet.getDouble(5);                
                String emailFreelancer = resultSet.getString(6);
                listaHabilitacao.add(new HabilitacaoAcademica(idHabilitacao, grau, designacaoCurso, nomeInstituicao, mediaCurso, emailFreelancer));
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
        return listaHabilitacao;*/
          
        return null;
    }
}
