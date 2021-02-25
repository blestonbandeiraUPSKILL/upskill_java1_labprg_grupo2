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
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.ReconhecimentoGP;
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
    
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";
    
    /**
     * Inicializa o Repositório de todas as Competências Técnicas de grau de proficiência 
     * reconhecidaas de todos os Freelancers
     */
    RepositorioReconhecimentoGPDatabase(){    }

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
    public boolean save(int idGrauProficiencia, String dataReconhecimento, 
             Email emailFreelancer, String idCompetenciaTecnica) throws  ReconhecimentoDuplicadoException,
            SQLException{
        
        
        return false;

    }

    @Override
    public boolean save(ReconhecimentoGP reconhecimentoGP) throws ReconhecimentoDuplicadoException,
            SQLException {
        
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createReconhecimentoGP(?, ?, ?, ?)}"
        );
       // if (findByCategoriaEGrau(reconhecimentoGP.getCodigoCategoria(), caracterizacaoCT.getCodigoGP()) == null){
            try {
                connection.setAutoCommit(false);

                callableStatement.setInt(1, reconhecimentoGP.getIdGrauProficiencia());
                callableStatement.setString(2, reconhecimentoGP.getIdCompetenciaTecnica());
                callableStatement.setString(3, reconhecimentoGP.getEmailFreelancer().getEmailText());
                callableStatement.setString(4, reconhecimentoGP.getDataReconhecimento());
                

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
        return false;
    }
    
    @Override
    public ArrayList<ReconhecimentoGP> getAll() throws SQLException {
        return null;
    }
    
    @Override
    public ArrayList<ReconhecimentoGP> findByEmail(String email) throws SQLException {
        ArrayList<ReconhecimentoGP> reconhecimentosFreelancer = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM ReconhecimentoGO WHERE emailFreelancer LIKE ?"
            );

            preparedStatement.setString(1, email);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                
                int idGrauProficiencia  = resultSet.getInt(1);
                String idCompetenciaTecnica = resultSet.getString(2);
                String dataReconhecimento = resultSet.getString(4);
                
                reconhecimentosFreelancer.add(new ReconhecimentoGP(
                        idGrauProficiencia, idCompetenciaTecnica, 
                        new Email(email), dataReconhecimento));
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

        return reconhecimentosFreelancer;
    }
    
    @Override
    public ReconhecimentoGP findByEmailCompetencia(String email, String idCompetenciaTecnica) throws SQLException{
        
        ReconhecimentoGP reconhecimentoGP = null;

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Tarefa WHERE emailFreelancer LIKE ? AND idCompetenciaTecnica LIKE ?"
            );

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, idCompetenciaTecnica);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int idGrauProficiencia  = resultSet.getInt(1);
                String dataReconhecimento = resultSet.getString(4);
                
                reconhecimentoGP = new ReconhecimentoGP( 
                        idGrauProficiencia, idCompetenciaTecnica, 
                        new Email(email), dataReconhecimento);
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

        return reconhecimentoGP;
    }

    
}
