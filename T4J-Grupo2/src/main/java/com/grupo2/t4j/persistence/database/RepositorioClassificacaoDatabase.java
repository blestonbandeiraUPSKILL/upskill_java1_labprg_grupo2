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

import com.grupo2.t4j.domain.*;
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
    public boolean save(int posicao, int idSeriacao, int idCandidatura) throws SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createClassificacao(?, ?, ?) } ");

            if (findByCandidatura(idCandidatura) == null){

                connection.setAutoCommit(false);
                
                callableStatement.setInt(1, posicao);
                callableStatement.setInt(2, idSeriacao); 
                callableStatement.setInt(3, idCandidatura);
                               
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
                    "{CALL createClassificacao(?, ?, ?, ?) } ");
            if (findByCandidatura(classificacao.getIdCandidatura()) == null){

                connection.setAutoCommit(false);

                callableStatement.setInt(1, classificacao.getIdClassificacao());
                callableStatement.setInt(2, classificacao.getPosicaoFreelancer());
                callableStatement.setInt(3, classificacao.getIdSeriacao()); 
                callableStatement.setInt(4, classificacao.getIdCandidatura());
                               
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
    public Classificacao findById(int idClassificacao) throws SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatementOrg = connection.prepareCall(
                    "{CALL findClassificacaoById(?)}");

            connection.setAutoCommit(false);

            callableStatementOrg.setInt(1, idClassificacao);
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
    public Classificacao findByCandidatura (int idCandidatura) throws SQLException{
         Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatementOrg = connection.prepareCall(
                    "{CALL findClassificacaoByCandidatura(?)}");

            connection.setAutoCommit(false);

            callableStatementOrg.setInt(1, idCandidatura);
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
    public Classificacao findBySeriacao (int idSeriacao) throws SQLException{
        
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatementOrg = connection.prepareCall(
                    "{CALL findClassificacaoBySeriacao(?)}");

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
        return new Classificacao();
    }
    
    @Override
    public ArrayList<Classificacao> getAllBySeriacao(int idSeriacao) throws SQLException{
        
        ArrayList<Classificacao> classificacoes = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                   "SELECT * FROM Classificacao " +
                            "WHERE idSeriacao =  ?"
            );

            preparedStatement.setInt(1, idSeriacao);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idClassificacao = resultSet.getInt(1);
                int posicao = resultSet.getInt(2);
                int idCandidatura = resultSet.getInt(3);
                        
                classificacoes.add(new Classificacao(idClassificacao,  
                        posicao, idSeriacao, idCandidatura));
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