/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.grupo2.t4j.repository;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.exception.*;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioOrganizacao implements Serializable{

    /**
     * Atributos da classe Singleton RepositorioOrganizacao
     */
    private static RepositorioOrganizacao instance;
    private List<Organizacao> listaOrganizacoes;
    Colaborador colabGestor;
    private DBConnectionHandler dbConnectionHandler;
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_04";
    String password = "qwerty";

    /**
     * Construtor da classe Singleton RepositorioOrganizacao
     */
    private RepositorioOrganizacao() throws SQLException {

    }

    /**
     * Devolve ou cria uma instância estática de RepositorioCompetenciaTecnica
     *
     * @return a instance existente ou criada
     */
    public static RepositorioOrganizacao getInstance() throws SQLException {
        if(instance == null) {
            instance = new RepositorioOrganizacao();
        }
        return instance;
    }

    /**
     *
     * Cria uma nova Organizacao usando os construtores dos objectos que a compõem
     *
     * @param nome nome da organização
     * @param nif nif da organização
     * @param telefone contacto telefónico da organização
     * @param website website da organização
     * @param emailOrganizacao email da organização

     * @return uma instância de Organizacao
     */
    public Organizacao novaOrganizacao(String nif, String nome, Website website,
                                       String telefone, Email emailOrganizacao,
                                       Colaborador gestor, EnderecoPostal enderecoPostal) {

        return new Organizacao(nif, nome, website, telefone, emailOrganizacao, gestor, enderecoPostal);
    }

    public boolean createOrganizacao(Organizacao organizacao, Colaborador gestor,
                                   EnderecoPostal enderecoPostal) throws SQLException {

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatementOrg = connection.prepareCall(
                "{CALL createOrganizacao(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)} ");

        try {
            connection.setAutoCommit(false);

            callableStatementOrg.setString(1, organizacao.getNif());
            callableStatementOrg.setString(2, organizacao.getNome());
            callableStatementOrg.setString(3, organizacao.getWebsite().getWebsiteText());
            callableStatementOrg.setString(4, organizacao.getTelefone());
            callableStatementOrg.setString(5, organizacao.getEmail().getEmailText());
            callableStatementOrg.setString(6, gestor.getEmail().getEmailText());
            callableStatementOrg.setString(7, enderecoPostal.getArruamento());
            callableStatementOrg.setString(8, enderecoPostal.getPorta());
            callableStatementOrg.setString(9, enderecoPostal.getLocalidade());
            callableStatementOrg.setString(10, enderecoPostal.getCodigoPostal());
            callableStatementOrg.setString(11, gestor.getNome());
            callableStatementOrg.setString(12, gestor.getPassword().getPasswordText());
            callableStatementOrg.setString(13, gestor.getRolename().toString());
            callableStatementOrg.setString(14, gestor.getTelefone());
            callableStatementOrg.setString(15, gestor.getFuncao());

            callableStatementOrg.executeQuery();

            connection.commit();
            connection.close();

            return true;
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

        connection.close();
        dbConnectionHandler.closeAll();
        return false;
    }

    /**
     *
     * Adiciona uma nova Organização ao RepositorioOrganizacao
     *
     * @param organizacao organização a ser adicionada
     *
     * @throws OrganizacaoDuplicadaException
     * @return
     */
    public boolean addOrganizacao(Organizacao organizacao) throws OrganizacaoDuplicadaException, SQLException {

        if (!getOrganizacaoByNif(organizacao.getNif())) {
            return createOrganizacao(organizacao, organizacao.getColabGestor(), organizacao.getEnderecoPostal());
            } else {
            throw new OrganizacaoDuplicadaException(organizacao.getNif() + ": Organização com esse NIPC já registada!");
        }
    }

    /**
     *
     * Procura no RepositorioOrganizacao por uma organização através do seu NIF
     *
     * @param nif nif da organização a ser procurada
     *
     * @return a organização encontrada, caso exista
     */
    private boolean getOrganizacaoByNif(String nif) throws SQLException {

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatementOrg = connection.prepareCall(
                //"{? = CALL getOrganizacaoByNif(?)} ");
                 ("{? = CALL getOrganizacaoByNif(?)}");

        try {
            connection.setAutoCommit(false);

            callableStatementOrg.registerOutParameter(1, Types.INTEGER);
            callableStatementOrg.setString(2, nif);
            callableStatementOrg.executeUpdate();

            connection.commit();
            connection.close();

            if (callableStatementOrg.getInt(1) != 0) {
                return true;
            }


        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();
            try {
                System.err.print("Transaction is being rolled back");
                connection.rollback();
            } catch (SQLException sqlException) {
                sqlException.getErrorCode();
            }
        }

        connection.close();
        dbConnectionHandler.closeAll();
        return false;
    }


}
