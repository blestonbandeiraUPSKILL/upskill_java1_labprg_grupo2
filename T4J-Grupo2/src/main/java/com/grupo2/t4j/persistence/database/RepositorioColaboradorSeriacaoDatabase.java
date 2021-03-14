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

import com.grupo2.t4j.domain.Colaborador;
import com.grupo2.t4j.domain.Password;
import com.grupo2.t4j.persistence.RepositorioColaboradorSeriacao;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class RepositorioColaboradorSeriacaoDatabase implements RepositorioColaboradorSeriacao{
    
    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os registos de seriações e colaboradores participantes
     * nestes processos
     */
    private static RepositorioColaboradorSeriacaoDatabase repositorioColaboradorSeriacaoDatabase;

    /**
     * Inicializa o Repositório de Colaboradores - Processos de Seriação
     */
    private RepositorioColaboradorSeriacaoDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Colaboradores - Processos de Seriação
     *
     * @return RepositorioColaboradorSeriacaoDatabase
     */
    public static RepositorioColaboradorSeriacaoDatabase getInstance(){
        if(repositorioColaboradorSeriacaoDatabase == null) {
            repositorioColaboradorSeriacaoDatabase = new RepositorioColaboradorSeriacaoDatabase();
        }
        return repositorioColaboradorSeriacaoDatabase;
    }
    
    /**
     * 
     * @param emaiColaborador
     * @param idSeriacao
     * @return
     * @throws SQLException 
     */
    @Override    
    public boolean update(String emaiColaborador, int idSeriacao) throws SQLException{
        
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createColaboradorSeriacao(?, ?)}"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, emaiColaborador);
            callableStatement.setInt(2, idSeriacao);
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
            } catch (SQLException sqlException) {
                sqlException.getErrorCode();
            }

        } finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return false;
    }
    
    /**
     * 
     * @param emaiColaborador
     * @param idSeriacao
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean findByEmailId(String emaiColaborador, int idSeriacao) throws SQLException{
        
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL findByEmail(?, ?) }"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, emaiColaborador);
            callableStatement.setInt(1, idSeriacao);
            callableStatement.executeUpdate();

            return false;

        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return true;
    }
    
    /**
     * 
     * @param idSeriacao
     * @return
     * @throws SQLException 
     */
    @Override    
    public ArrayList<String> getAllColaboresBySeriacao(int idSeriacao) throws SQLException{
        
        ArrayList<String> colaboradoresSeriacao = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM ColaboradorSeriacao WHERE idSeriacao LIKE ?"
            );

            preparedStatement.setInt(1, idSeriacao);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String emailColaborador = resultSet.getString(1);
                
                colaboradoresSeriacao.add(emailColaborador);
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
            DBConnectionHandler.getInstance().closeAll();
        }

        return colaboradoresSeriacao;  
    }
    
    /**
     * 
     * @param emailColaborador
     * @return
     * @throws SQLException 
     */
    @Override
    public ArrayList<Integer> getAllIdsSeriacaoByColaborador(String emailColaborador) throws SQLException{
        
        ArrayList seriacoesColaborador = new ArrayList();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM ColaboradorSeriacao WHERE emailColaborador LIKE ?"
            );

            preparedStatement.setString(1, emailColaborador);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int idSeriacao = resultSet.getInt(2);
                
                seriacoesColaborador.add(idSeriacao);
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
            DBConnectionHandler.getInstance().closeAll();
        }

        return seriacoesColaborador;
    }  
    
}
