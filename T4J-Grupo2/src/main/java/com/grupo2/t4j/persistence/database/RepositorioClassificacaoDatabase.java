package com.grupo2.t4j.persistence.database;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.domain.Classificacao;
import com.grupo2.t4j.persistence.RepositorioClassificacao;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    
    /**
     * Regista uma classificacao
     * @param posicao
     * @param idSeriacao
     * @param idCandidatura
     * @return
     * @throws SQLException 
     */
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
    
    /**
     * Regista uma classificacao
     * @param classificacao
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean save(Classificacao classificacao) throws SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createClassificacao(?, ?, ?) } ");
            if (findByCandidatura(classificacao.getIdCandidatura()) == null){

                connection.setAutoCommit(false);

                callableStatement.setInt(1, classificacao.getPosicaoFreelancer());
                callableStatement.setInt(2, classificacao.getIdSeriacao());
                callableStatement.setInt(3, classificacao.getIdCandidatura());
                               
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

    /**
     * Devolve uma classificacao a partir do seu id
     * @param idClassificacao
     * @return
     * @throws SQLException 
     */
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
    
    /**
     * Devolve uma classificacao a partir do id de uma candidatura
     * @param idCandidatura
     * @return
     * @throws SQLException 
     */
    @Override
    public Classificacao findByCandidatura (int idCandidatura) throws SQLException{
         Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatementOrg = connection.prepareCall(
                    "{ CALL findClassificacaoByCandidatura(?) }");

            connection.setAutoCommit(false);

            callableStatementOrg.setInt(1, idCandidatura);
            callableStatementOrg.executeQuery();

            return new Classificacao();

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return null;
    }
          
    /**
     * Devolve uma classificacao a partir do id da seriacao
     * @param idSeriacao
     * @return
     * @throws SQLException 
     */
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
    
    /**
     * Devolve todas as classificacoes de uma seriacao
     * @param idSeriacao
     * @return
     * @throws SQLException 
     */
    @Override
    public ArrayList<Classificacao> getAllBySeriacao(int idSeriacao) throws SQLException{
        
        ArrayList<Classificacao> classificacoes = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                   "SELECT * FROM Classificacao " +
                            "WHERE idProcessoSeriacao =  ?"
            );

            preparedStatement.setInt(1, idSeriacao);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idClassificacao = resultSet.getInt(1);
                int posicao = resultSet.getInt(2);
                int idCandidatura = resultSet.getInt(4);
                        
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
    
    /**
     * Ordena as classificacoes por id da candidatura
     * @param classificacoes
     * @return
     * @throws SQLException 
     */
    @Override
    public List<Classificacao> ordenarByIdCandidatura(List<Classificacao> classificacoes) throws SQLException{
        
        Collections.sort(classificacoes);
        
        return classificacoes;
    }
}
