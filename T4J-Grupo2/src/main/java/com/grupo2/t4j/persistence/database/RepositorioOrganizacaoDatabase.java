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

import com.grupo2.t4j.model.Organizacao;
import com.grupo2.t4j.persistence.RepositorioOrganizacao;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioOrganizacaoDatabase implements RepositorioOrganizacao {

    /**
     * Atributos da classe Singleton RepositorioOrganizacao
     */
    private static RepositorioOrganizacaoDatabase repositorioOrganizacaoDatabase;

    /**
     * Construtor da classe Singleton RepositorioOrganizacao
     */
    private RepositorioOrganizacaoDatabase() throws SQLException {

    }

    /**
     * Devolve ou cria uma instância estática de RepositorioOrganizacaoDatabase
     *
     * @return a instance existente ou criada
     */
    public static RepositorioOrganizacaoDatabase getInstance() throws SQLException {
        if(repositorioOrganizacaoDatabase == null) {
            repositorioOrganizacaoDatabase = new RepositorioOrganizacaoDatabase();
        }
        return repositorioOrganizacaoDatabase;
    }


    @Override
    public boolean save(String nif, String nome, String website, String telefone,
                        String emailOrganizacao, String emailGestor, String arruamento, String numeroPorta,
                        String localidade, String codPostal,
                     String nomeGestor, String telefoneGestor, String funcao, String pass) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createOrganizacao(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) } ");

            if (findByNif(nif) == null) {

                connection.setAutoCommit(false);

                callableStatement.setString(1, nif);
                callableStatement.setString(2, nome);
                callableStatement.setString(3, website);
                callableStatement.setString(4, telefone);
                callableStatement.setString(5, emailOrganizacao);
                callableStatement.setString(6, emailGestor);
                callableStatement.setString(7, arruamento);
                callableStatement.setString(8, numeroPorta);
                callableStatement.setString(9, localidade);
                callableStatement.setString(10, codPostal);
                callableStatement.setString(11, nomeGestor);
                callableStatement.setString(12, pass);
                callableStatement.setString(13, telefone);
                callableStatement.setString(14, funcao);

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
    public boolean save(Organizacao organizacao) {

        return false;
    }

    /**
     *
     * Procura no RepositorioOrganizacao por uma organização através do seu NIF
     *
     * @param nif nif da organização a ser procurada
     *
     * @return a organização encontrada, caso exista
     */
    public Organizacao findByNif(String nif) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatementOrg = connection.prepareCall(
                    "{CALL findByNif(?)}");

            connection.setAutoCommit(false);

            callableStatementOrg.setString(1, nif);
            callableStatementOrg.executeQuery();

            return null;

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();


        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return new Organizacao();
    }

    @Override
    public ArrayList<Organizacao> getAll() {
        return null;
    }


}
