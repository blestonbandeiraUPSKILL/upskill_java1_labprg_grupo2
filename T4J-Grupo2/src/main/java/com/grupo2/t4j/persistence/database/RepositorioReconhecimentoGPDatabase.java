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

import com.grupo2.t4j.exception.ReconhecimentoDuplicadoException;
import com.grupo2.t4j.domain.Email;
import com.grupo2.t4j.domain.ReconhecimentoGP;
import com.grupo2.t4j.persistence.RepositorioReconhecimentoGP;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;

public class RepositorioReconhecimentoGPDatabase implements RepositorioReconhecimentoGP {
    
    /**
     * Define uma instância estática do Repositório em que estão
     * registadas as Competências Técnicas de grau de proficiência reconhecidaas
     * de todos os Freelancers
     */
    private static RepositorioReconhecimentoGPDatabase repositorioReconhecimentoGPDatabase;
    
    /**
     * Inicializa o Repositório de todas as Competências Técnicas de grau de proficiência 
     * reconhecidaas de todos os Freelancers
     */
    private RepositorioReconhecimentoGPDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Reconhecimento de GP
     *
     * @return RepositorioReconhecimentoGPDatabase
     */
    public static RepositorioReconhecimentoGPDatabase getInstance(){
        if(repositorioReconhecimentoGPDatabase == null) {
            repositorioReconhecimentoGPDatabase = new RepositorioReconhecimentoGPDatabase();
        }
        return repositorioReconhecimentoGPDatabase;
    }


    @Override
    public boolean save(int idGrauProficiencia, String emailFreelancer, String dataReconhecimento) throws  ReconhecimentoDuplicadoException,
            SQLException{

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createReconhecimentoGP(?, ?, ?)}"
            );

            connection.setAutoCommit(false);

            callableStatement.setInt(1, idGrauProficiencia);
            callableStatement.setString(2, emailFreelancer);
            callableStatement.setDate(3, Date.valueOf(dataReconhecimento));

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
    public boolean save(ReconhecimentoGP reconhecimentoGP) throws ReconhecimentoDuplicadoException,
            SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createReconhecimentoGP(?, ?, ?)}"
            );

            connection.setAutoCommit(false);

            callableStatement.setInt(1, reconhecimentoGP.getIdGrauProficiencia());
            callableStatement.setString(2, reconhecimentoGP.getEmailFreelancer().getEmailText());
            callableStatement.setString(3, reconhecimentoGP.getDataReconhecimento());

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
    public ArrayList<ReconhecimentoGP> getAll(String email) throws SQLException {
        ArrayList<ReconhecimentoGP> reconhecimentosGP = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM  ReconhecimentoGP " +
                            "INNER JOIN Freelancer " +
                            "ON ReconhecimentoGP.emailFreelancer LIKE Freelancer.email " +
                            "INNER JOIN GrauProficiencia " +
                            "ON grauproficiencia.idgrauproficiencia = reconhecimentogp.idgrauproficiencia " +
                            "INNER JOIN CompetenciaTecnica " +
                            "ON CompetenciaTecnica.codigoCompetenciaTecnica LIKE GrauProficiencia.codigoCompetenciaTecnica " +
                            "WHERE email LIKE ? "
            );

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String dataReconhecimento = resultSet.getString(3);
                String designacaoGrau = resultSet.getString(10);
                String descBreve = resultSet.getString(13);

                reconhecimentosGP.add(new ReconhecimentoGP(dataReconhecimento, designacaoGrau, descBreve));

            }
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
        return reconhecimentosGP;

    }


    
}
