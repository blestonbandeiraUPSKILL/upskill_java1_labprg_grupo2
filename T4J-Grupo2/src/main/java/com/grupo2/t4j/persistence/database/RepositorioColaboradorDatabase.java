package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.ColaboradorDuplicadoException;
import com.grupo2.t4j.domain.Colaborador;
import com.grupo2.t4j.domain.Password;
import com.grupo2.t4j.persistence.RepositorioColaborador;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioColaboradorDatabase implements RepositorioColaborador {

    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os Colaboradores
     */
    private static RepositorioColaboradorDatabase repositorioColaboradorDatabase;

    /**
     * Inicializa o Repositório de Colaboradores
     */
    private RepositorioColaboradorDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Colaboradores
     *
     * @return RepositorioColaboradorDatabase
     */
    public static RepositorioColaboradorDatabase getInstance(){
        if(repositorioColaboradorDatabase == null) {
            repositorioColaboradorDatabase = new RepositorioColaboradorDatabase();
        }
        return repositorioColaboradorDatabase;
    }

    @Override
    public void save(String email, String nome, String passowrd, String funcao, String telefone) throws ColaboradorDuplicadoException {

    }

    @Override
    public boolean save(Colaborador colaborador) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createUtilizadorColaborador(?, ?, ?, ?)}"
            );

            if(findByEmail(colaborador.getEmail().getEmailText()) == null) {

                connection.setAutoCommit(false);

                callableStatement.setString(1, colaborador.getEmail().getEmailText());
                callableStatement.setString(2, colaborador.getTelefone());
                callableStatement.setString(3, colaborador.getFuncao());
                callableStatement.setString(4, colaborador.getNifOrganizacao());
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
            } catch (SQLException sqlException) {
                sqlException.getErrorCode();
            }

        } finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return false;
    }

    @Override
    public Colaborador findByEmail(String email) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL findByEmail(?) }"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, email);
            callableStatement.executeUpdate();

            return null;

        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return new Colaborador();

    }

    @Override
    public ArrayList<Colaborador> getAll() throws SQLException {

        ArrayList<Colaborador> colaboradores = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT Colaborador.email, Colaborador.funcao, Colaborador.telefone, Colaborador.nifOrganizacao, Utilizador.nome FROM COLABORADOR " +
                            "INNER JOIN Utilizador " +
                            "ON colaborador.email LIKE utilizador.email " +
                            "INNER JOIN Organizacao " +
                            "ON colaborador.nifOrganizacao LIKE organizacao.nif"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String email = resultSet.getString(1);
                String funcao = resultSet.getString(2);
                String telefone = resultSet.getString(3);
                String nifOrganizacao = resultSet.getString(4);
                String nome = resultSet.getString(5);
                colaboradores.add(new Colaborador(nome, email, funcao, telefone, nifOrganizacao));
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
        return colaboradores;
    }
    
    /**
     * Retorna todos os emails dos colaboradores de uma organização em ordem alfabética
     * @param nifOrganizacao
     * @return
     * @throws SQLException 
     */
    @Override
    public ArrayList<String> getAllEmailsAlfByOrganizacao(String nifOrganizacao) throws SQLException{
        
        ArrayList<Colaborador> colaboradores = getAll();
        
        ArrayList<String> colaboradoresOrganizacao = new ArrayList<>();
        
        for(int i= 0; i < colaboradores.size(); i++){
            Colaborador colaborador = colaboradores.get(i);
            if(colaborador.getNifOrganizacao().equals(nifOrganizacao)){
                colaboradoresOrganizacao.add(colaborador.getEmail().getEmailText());
            }            
        }
        
        Collections.sort(colaboradoresOrganizacao);
        return colaboradoresOrganizacao;
    }
  
    @Override
    public String getNifOrganizacao(String email) throws SQLException {

        String nifOrganizacao = null;

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT nifOrganizacao From Colaborador WHERE email LIKE ?"
            );
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                nifOrganizacao = resultSet.getString(1);
            }
            return  nifOrganizacao;
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

        return null;
    }

    @Override
    public Password findPassword(String email) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT utilizador.password FROM Utilizador " +
                            "INNER JOIN Colaborador ON colaborador.email LIKE utilizador.email WHERE colaborador.email LIKE ?"
            );

            connection.setAutoCommit(false);

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String password = resultSet.getString(1);
                return new Password(password);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return null;
    }
}
