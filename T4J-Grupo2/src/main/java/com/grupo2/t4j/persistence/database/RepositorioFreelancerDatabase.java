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

import com.grupo2.t4j.exception.FreelancerDuplicadoException;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Freelancer;
import com.grupo2.t4j.model.Password;
import com.grupo2.t4j.persistence.RepositorioFreelancer;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;

public class RepositorioFreelancerDatabase implements RepositorioFreelancer{
    
    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os Freelancers
     */
    private static RepositorioFreelancerDatabase repositorioFreelancerDatabase;
    
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";
    
    /**
     * Inicializa o Repositório de Freelancers
     */
    private RepositorioFreelancerDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório dos Freelancers
     *
     * @return RepositorioFreelancerDatabase
     */
    public static RepositorioFreelancerDatabase getInstance(){
        if(repositorioFreelancerDatabase == null) {
            repositorioFreelancerDatabase = new RepositorioFreelancerDatabase();
        }
        return repositorioFreelancerDatabase;
    }


    @Override
    public boolean save(String emailFree, String nome, String nif, String telefone, String password, String arruamento,
                        String numeroPorta, String localidade, String codPostal) throws FreelancerDuplicadoException,
            SQLException{

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, this.password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createFreelancer(?, ?, ?, ?, ?, ?, ?, ?, ?) } ");

        if (findByNif(nif) == null && findByEmail(emailFree) == null){

            try {
                connection.setAutoCommit(false);

                callableStatement.setString(1, emailFree);
                callableStatement.setString(2, nome);
                callableStatement.setString(3, password);
                callableStatement.setString(4, telefone);
                callableStatement.setString(5, nif);
                callableStatement.setString(6, arruamento);
                callableStatement.setString(7, numeroPorta);
                callableStatement.setString(8, localidade);
                callableStatement.setString(9, codPostal);
                
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
        }

        return false;

    }

    @Override
    public boolean save(Freelancer freelancer) throws FreelancerDuplicadoException,
            SQLException {
        return false;
    }

    @Override
    public Freelancer findByNif(String nif) throws SQLException{
        
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatementOrg = connection.prepareCall(
                 "{CALL findFreelancerByNif(?)}");

        try {
            connection.setAutoCommit(false);

            callableStatementOrg.setString(1, nif);
            callableStatementOrg.executeQuery();

            return null;

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();


        }

        return new Freelancer();
        

    }

    @Override
    public Password findPassword(String email) throws SQLException {

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT utilizador.password FROM Utilizador " +
                        "INNER JOIN Freelancer ON freelancer.email LIKE ? "
        );

        try {
            connection.setAutoCommit(false);

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String password = resultSet.getString(1);
                return new Password(password);
            }

        }

        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();
        }

        return null;
    }

    @Override
    public Freelancer findByEmail(String emailFree) throws SQLException {

        Freelancer freelancer = new Freelancer();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                 "{CALL findFreelancerByEmail(?)}"
        );

        try {
            connection.setAutoCommit(false);

            callableStatement.setString(1, emailFree);
            callableStatement.executeQuery();

            return null;

        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT Freelancer.telefone, " +
                        "Freelancer.nif, " +
                        "Utilizador.nome, " +
                        "Freelancer.idEnderecoPostal " +
                        "FROM Freelancer INNER JOIN Utilizador " +
                        "ON freelancer.email LIKE utilizador.email " +
                        "WHERE freelancer.email LIKE ?"
        );

        preparedStatement.setString(1, emailFree);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            freelancer.setEmail(new Email(emailFree));
            freelancer.setTelefone(resultSet.getString(1));
            freelancer.setNif(resultSet.getString(2));
            freelancer.setNome(resultSet.getString(3));
            freelancer.setIdEnderecoPostal(resultSet.getInt(4));
        }

        return freelancer;

    }

    @Override
    public ArrayList<Freelancer> getAll() throws SQLException {
        
        ArrayList<Freelancer> freelancers = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT Freelancer.email, Freelancer.telefone, Freelancer.nif, Utilizador.nome, Freelancer.idEnderecoPostal " +
                            "FROM Freelancer INNER JOIN Utilizador " +
                            "ON freelancer.email LIKE utilizador.email"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String email = resultSet.getString(1);
                String telefone = resultSet.getString(2);
                String nif = resultSet.getString(3);
                String nome = resultSet.getString(4);
                int idEnderecoPostal = resultSet.getInt(5);
                freelancers.add(new Freelancer(email, nome, telefone, nif, idEnderecoPostal));
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

        return freelancers;
    }
    
    @Override
    public ArrayList<String> getAllEmails() throws SQLException {
        
        ArrayList<String> listaEmailfreelancers = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Freelancer"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String email = resultSet.getString(1);
                String nome = resultSet.getString(2);
                String password = resultSet.getString(3);
                String nif = resultSet.getString(4);
                String telefone = resultSet.getString(5);
                String codigoEnderecoPostal = resultSet.getString(6);
                listaEmailfreelancers.add(email);
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
        return listaEmailfreelancers;

    }
}
