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

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.RepositorioSeriacao;
import com.grupo2.t4j.utils.DBConnectionHandler;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RepositorioSeriacaoDatabase implements RepositorioSeriacao{
    /**
     * Define uma instância estática do Repositório em que estão
     * registados todas as Seriações
     */
    private static RepositorioSeriacaoDatabase repositorioSeriacaoDatabase;
    
    /**
     * Inicializa o Repositório de Seriações
     */
    private RepositorioSeriacaoDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório das Seriações
     *
     * @return RepositorioSeriacaoDatabase
     */
    public static RepositorioSeriacaoDatabase getInstance(){
        if(repositorioSeriacaoDatabase == null) {
            repositorioSeriacaoDatabase = new RepositorioSeriacaoDatabase();
        }
        return repositorioSeriacaoDatabase;
    }
    
    @Override
    public boolean save(int idSeriacao, int idAnuncio, String dataSeriacao) throws SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createSeriacao(?, ?, ?) } ");

            if (findById(idSeriacao) == null){

                connection.setAutoCommit(false);

                callableStatement.setInt(1, idSeriacao);
                callableStatement.setInt(2, idAnuncio);
                callableStatement.setString(3, dataSeriacao);
                callableStatement.executeQuery();

                connection.commit();
                return true;
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
        return false;
    }
    
    @Override
    public boolean save(ProcessoSeriacao seriacao) throws SQLException{

         Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createSeriacao(?, ?) } ");

            if (findById(seriacao.getIdSeriacao()) == null){

                connection.setAutoCommit(false);

                callableStatement.setInt(1, seriacao.getIdAnuncio());
                callableStatement.setString(2, seriacao.getDataSeriacao());
                callableStatement.executeQuery();

                connection.commit();
                return true;
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
        return false;
    }

    @Override
    public ProcessoSeriacao findById(int idSeriacao) throws SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatementOrg = connection.prepareCall(
                    "{CALL findSeriacaoById(?)}");

            connection.setAutoCommit(false);

            callableStatementOrg.setInt(1, idSeriacao);
            callableStatementOrg.executeQuery();

            return null;

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return new ProcessoSeriacao();      
    }
    
    @Override
    public ProcessoSeriacao findByAnuncio(int idAnuncio) throws SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatementOrg = connection.prepareCall(
                    "{CALL findSeriacaoByAnuncio(?)}");

            connection.setAutoCommit(false);

            callableStatementOrg.setInt(1, idAnuncio);
            callableStatementOrg.executeQuery();

            return null;

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return new ProcessoSeriacao();      
    } 
        
    @Override
    public List<ProcessoSeriacao> getAll() throws SQLException{
        
        ArrayList<ProcessoSeriacao> seriacoes = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Seriacao"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idSeriacao = resultSet.getInt(1);
                int idAnuncio = resultSet.getInt(2);
                String dataSeriacao = resultSet.getString(3);
                seriacoes.add(new ProcessoSeriacao(idSeriacao, idAnuncio, dataSeriacao));
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
        return seriacoes;
    }
}
