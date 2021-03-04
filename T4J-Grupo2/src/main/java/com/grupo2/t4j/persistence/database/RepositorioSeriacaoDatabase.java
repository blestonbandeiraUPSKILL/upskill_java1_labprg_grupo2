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
    public boolean save(int idSeriacao, int idAnuncio, String dataSeriacao, ArrayList<Classificacao> 
            listaCandidaturasSeriadas) throws SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createSeriacao(?, ?, ?) } ");

            if (findById(idSeriacao) == null){

                connection.setAutoCommit(false);

                callableStatement.setString(1, Integer.toString(idAnuncio));
                callableStatement.setString(2, dataSeriacao);
                //callableStatement.setString(3, Integer.toString(posicao));
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
                    "{CALL createSeriacao(?, ?, ?) } ");
            connection.setAutoCommit(false);

            /*callableStatement.setString(1, Integer.toString(classificacao.getIdAnuncio()));
            callableStatement.setString(2, Integer.toString(classificacao.getIdCandidatura()));
            callableStatement.setString(3, Integer.toString(classificacao.getColocacaoFreelancer()));*/
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

            //callableStatementOrg.setString(1, Integer.toString(idClassificacao));
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
    public ProcessoSeriacao findByAnuncio (int idAnuncio) throws SQLException{
        ProcessoSeriacao seriacao = new ProcessoSeriacao();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatementOrg = connection.prepareCall(
                    "SELECT idClassificacao, idCandidatura, posicao " +
                            "FROM Classificacao " +
                            "WHERE idAnuncio LIKE ?"
            );

            connection.setAutoCommit(false);

            callableStatementOrg.setString(1, Integer.toString(idAnuncio));
            callableStatementOrg.executeQuery();

            ResultSet resultSet = callableStatementOrg.getResultSet();

            while(resultSet.next()) {
                /*classificacao.setIdClassificacao(resultSet.getInt(1));
                classificacao.setIdAnuncio(resultSet.getInt(2));
                classificacao.setIdCandidatura(resultSet.getInt(3));
                classificacao.setColocacaoFreelancer(resultSet.getInt(4));*/
            }

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return seriacao;
    }
    
        
    @Override
    public ArrayList<ProcessoSeriacao> getAll() throws SQLException{
        
        ArrayList<ProcessoSeriacao> seriacoes = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Classificacao"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                /*int idClassificacao = resultSet.getInt(1);
                int idCandidatura = resultSet.getInt(3);
                int posicao = resultSet.getInt(4);              
               
                classificacoes.add(new Classificacao(idClassificacao, idAnuncio, 
                        idCandidatura, posicao));*/
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
