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

import com.grupo2.t4j.exception.OrganizacaoDuplicadaException;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.RepositorioOrganizacao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioOrganizacaoDatabase implements RepositorioOrganizacao {

    /**
     * Atributos da classe Singleton RepositorioOrganizacao
     */
    private static RepositorioOrganizacaoDatabase repositorioOrganizacaoDatabase;
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_04";
    String password = "qwerty";

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
    public void save(String nif, String nome, Website website, String telefone,
                     Email emailOrganizacao, Email emailGestor, String arruamento,
                     String numeroPorta, String localidade, String codigoPostal,
                     String nomeGestor, Password passwordGestor, Rolename rolename, String telefoneGestor, String funcaoGestor) throws SQLException {

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatementOrg = connection.prepareCall(
                "{CALL createOrganizacao(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)} ");

        if (findByNif(nif) == null) {

            try {
                connection.setAutoCommit(false);

                callableStatementOrg.setString(1, nif);
                callableStatementOrg.setString(2, nome);
                callableStatementOrg.setString(3, String.valueOf(website));
                callableStatementOrg.setString(4, telefone);
                callableStatementOrg.setString(5, String.valueOf(emailOrganizacao));
                callableStatementOrg.setString(6, String.valueOf(emailGestor));
                callableStatementOrg.setString(7, arruamento);
                callableStatementOrg.setString(8, numeroPorta);
                callableStatementOrg.setString(9, localidade);
                callableStatementOrg.setString(10, codigoPostal);
                callableStatementOrg.setString(11, nomeGestor);
                callableStatementOrg.setString(12, String.valueOf(passwordGestor));
                callableStatementOrg.setString(13, rolename.name());
                callableStatementOrg.setString(14, telefoneGestor);
                callableStatementOrg.setString(15, funcaoGestor);

                callableStatementOrg.executeQuery();

                connection.commit();
                connection.close();

            }
            catch (SQLException exceptionOrg) {
                exceptionOrg.printStackTrace();
                exceptionOrg.getSQLState();
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                }
                catch (SQLException sqlException) {
                    sqlException.getErrorCode();
                }
            }

        } else {
            throw new OrganizacaoDuplicadaException(nif + ": Organização com esse NIPC já registada!");
        }
        connection.close();
        dbConnectionHandler.closeAll();
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

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatementOrg = connection.prepareCall(
                 "{CALL getOrganizacaoByNif(?)}");

        try {
            connection.setAutoCommit(false);

            callableStatementOrg.setString(1, nif);
            callableStatementOrg.executeQuery();

            return new Organizacao();

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();

            return null;
        }
        finally {
            dbConnectionHandler.closeAll();
        }

    }

    @Override
    public ArrayList<Organizacao> getAll() {
        return null;
    }


}
