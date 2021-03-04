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
import com.grupo2.t4j.persistence.RepositorioClassificacao;
import com.grupo2.t4j.utils.DBConnectionHandler;
import java.sql.*;
import java.util.ArrayList;

public class RepositorioClassificacaoDatabase implements RepositorioClassificacao{
    
    /**
     * Define uma instância estática do Repositório em que estão
     * registados todas as Classificações
     */
    private static RepositorioClassificacaoDatabase repositorioClassificacaoDatabase;
    
    /**
     * Inicializa o Repositório de Classificações
     */
    private RepositorioClassificacaoDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório das Classificações
     *
     * @return RepositorioClassificacaoDatabase
     */
    public static RepositorioClassificacaoDatabase getInstance(){
        if(repositorioClassificacaoDatabase == null) {
            repositorioClassificacaoDatabase = new RepositorioClassificacaoDatabase();
        }
        return repositorioClassificacaoDatabase;
    }
    
    @Override
    public boolean save(int idClassificacao, int idAnuncio, int idCandidatura, 
            int posicao) throws SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createClassificacao(?, ?, ?) } ");

            if (findById(idClassificacao) == null){

                connection.setAutoCommit(false);

                callableStatement.setString(1, Integer.toString(idAnuncio));
                callableStatement.setString(2, Integer.toString(idCandidatura));
                callableStatement.setString(3, Integer.toString(posicao));
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
    public boolean save(Classificacao classificacao) throws SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createClassificacao(?, ?, ?) } ");
            connection.setAutoCommit(false);

            callableStatement.setString(1, Integer.toString(classificacao.getIdAnuncio()));
            callableStatement.setString(2, Integer.toString(classificacao.getIdCandidatura()));
            callableStatement.setString(3, Integer.toString(classificacao.getColocacaoFreelancer()));
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
    public Classificacao findById(int idClassificacao) throws SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatementOrg = connection.prepareCall(
                    "{CALL findClassificacaoById(?)}");

            connection.setAutoCommit(false);

            callableStatementOrg.setString(1, Integer.toString(idClassificacao));
            callableStatementOrg.executeQuery();

            return null;

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return new Classificacao();        
        
    }
    
    @Override
    public Classificacao findByAnuncio (int idAnuncio) throws SQLException{
        Classificacao classificacao = new Classificacao();

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
                classificacao.setIdClassificacao(resultSet.getInt(1));
                classificacao.setIdAnuncio(resultSet.getInt(2));
                classificacao.setIdCandidatura(resultSet.getInt(3));
                classificacao.setColocacaoFreelancer(resultSet.getInt(4));
            }

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return classificacao;
    }
    
    @Override
    public Classificacao findByCandidatura (int idCandidatura) throws SQLException{
        Classificacao classificacao = new Classificacao();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatementOrg = connection.prepareCall(
                    "SELECT idClassificacao, idAnuncio, posicao " +
                            "FROM Classificacao " +
                            "WHERE idCandidatura LIKE ?"
            );

            connection.setAutoCommit(false);

            callableStatementOrg.setString(1, Integer.toString(idCandidatura));
            callableStatementOrg.executeQuery();

            ResultSet resultSet = callableStatementOrg.getResultSet();

            while(resultSet.next()) {
                classificacao.setIdClassificacao(resultSet.getInt(1));
                classificacao.setIdAnuncio(resultSet.getInt(2));
                classificacao.setIdCandidatura(resultSet.getInt(3));
                classificacao.setColocacaoFreelancer(resultSet.getInt(4));
            }

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return classificacao;
    }
    
    @Override
    public ArrayList<Classificacao> getAllByAnuncio(int idAnuncio) throws SQLException{
        
        ArrayList<Classificacao> classificacoes = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Classificacao"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idClassificacao = resultSet.getInt(1);
                int idCandidatura = resultSet.getInt(3);
                int posicao = resultSet.getInt(4);              
               
                classificacoes.add(new Classificacao(idClassificacao, idAnuncio, 
                        idCandidatura, posicao));
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
        return classificacoes;
    }
}
