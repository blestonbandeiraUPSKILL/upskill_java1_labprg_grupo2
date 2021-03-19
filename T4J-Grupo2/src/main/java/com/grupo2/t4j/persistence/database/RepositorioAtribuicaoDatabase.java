package com.grupo2.t4j.persistence.database;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.domain.Anuncio;
import com.grupo2.t4j.domain.Atribuicao;
import com.grupo2.t4j.domain.Candidatura;
import com.grupo2.t4j.dto.AtribuicaoDTO;
import com.grupo2.t4j.persistence.RepositorioAtribuicao;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAtribuicaoDatabase implements RepositorioAtribuicao{

    private static RepositorioAtribuicaoDatabase repositorioAtribuicaoDatabase;

    private RepositorioAtribuicaoDatabase(){

    }

    public static RepositorioAtribuicaoDatabase getInstance(){
        if(repositorioAtribuicaoDatabase == null){
            repositorioAtribuicaoDatabase = new RepositorioAtribuicaoDatabase();
        }
        return repositorioAtribuicaoDatabase;
    }

    @Override
    public boolean save(String nifOrganizacao, String refTarefa, int idAnuncio, int idCandidatura, String emailFreelancer,
                 double valorAceite, int numDiasAceite, String codigoAtribuicao, String dataInicioTarefa) throws SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {

            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createAtribuicao(?, ?, ?, ?, ?, ?, ?, ?) }");

            if(findAtribuicaoByAnuncio(idAnuncio) == null){
                connection.setAutoCommit(false);

                callableStatement.setString(1, nifOrganizacao);
                callableStatement.setString(2, refTarefa);
                callableStatement.setInt(3, idAnuncio);
                callableStatement.setInt(4, idCandidatura);
                callableStatement.setString(5, emailFreelancer);
                callableStatement.setDouble(6, valorAceite);
                callableStatement.setInt(7, numDiasAceite);
                callableStatement.setString(8, codigoAtribuicao);
                callableStatement.setDate(9, Date.valueOf(dataInicioTarefa));

                callableStatement.executeQuery();

                connection.commit();
                return true;
            }

        } catch (SQLException exception) {
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


    @Override
    public boolean save(AtribuicaoDTO atribuicao) throws SQLException{
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {

            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createAtribuicao(?, ?, ?, ?, ?, ?, ?, ?) }");

            if(findAtribuicaoByAnuncio(atribuicao.getIdAnuncio()) == null){
                connection.setAutoCommit(false);

                callableStatement.setString(1, atribuicao.getNifOrganizacao());
                callableStatement.setString(2, atribuicao.getRefTarefa());
                callableStatement.setInt(3, atribuicao.getIdAnuncio());
                callableStatement.setInt(4, atribuicao.getIdCandidatura());
                callableStatement.setString(5, atribuicao.getEmailFreelancer());
                callableStatement.setDouble(6, atribuicao.getValorAceite());
                callableStatement.setInt(7, atribuicao.getNumDiasAceite());
                callableStatement.setString(8, atribuicao.getCodigoAtribuicao());
                callableStatement.setDate(9, Date.valueOf(atribuicao.getDataInicioTarefa()));

                callableStatement.executeQuery();

                connection.commit();
                return true;
            }

        } catch (SQLException exception) {
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

    @Override
    public Atribuicao findAtribuicaoByAnuncio(int idAnuncio) throws SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{ CALL findAtribuicaoByAnuncio(?) }"
            );

            connection.setAutoCommit(false);

            callableStatement.setInt(1, idAnuncio);

            callableStatement.executeUpdate();
            return null;

        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return new Atribuicao();
    }

    @Override
    public List<Atribuicao> getAllByOrganizacao(String nifOrganizacao) throws SQLException{
        List<Atribuicao> atribuicoes = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Atribuicao "
                            + "WHERE nifOrganizacao =  ?"
            );

            preparedStatement.setString(1, nifOrganizacao);

            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {

                int idAtribuicao = resultSet.getInt(1);
                String refTarefa = resultSet.getString(3);
                int idAnuncio =  resultSet.getInt(4);
                int idCandidatura = resultSet.getInt(5);
                String emailFreelancer = resultSet.getString(6);
                double valorAceite = resultSet.getDouble(7);
                int numDiasAceite = resultSet.getInt(8);
                String codigoAtribuicao = resultSet.getString(9);
                String dataAtribuicao =  resultSet.getDate(10).toString();
                String dataInicioTarefa =  resultSet.getDate(11).toString();
                String dataFimTarefa =  resultSet.getDate(12).toString();

                atribuicoes.add(new Atribuicao(idAtribuicao, nifOrganizacao, refTarefa, idAnuncio, idCandidatura,
                        emailFreelancer, valorAceite, numDiasAceite, codigoAtribuicao, dataAtribuicao, dataInicioTarefa,
                        dataFimTarefa));
            }

        } catch (SQLException exception) {
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

        return atribuicoes;
    }
}
