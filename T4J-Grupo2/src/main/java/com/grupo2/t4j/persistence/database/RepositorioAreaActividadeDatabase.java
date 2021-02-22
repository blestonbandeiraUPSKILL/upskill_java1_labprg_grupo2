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

import com.grupo2.t4j.exception.AreaActividadeDuplicadaException;
import com.grupo2.t4j.exception.AreaActividadeInexistenteException;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.utils.DBConnectionHandler;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geral
 */
public class RepositorioAreaActividadeDatabase implements RepositorioAreaActividade {

    /**
     * Define uma instância estática do Repositório em que estão registadas todas
     * as Áreas de Actividade da plataforma
     */
    private static RepositorioAreaActividadeDatabase repositorioAreaActividadeDatabase;

    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";

    /**
     * Inicializa o Repositório de Areas de Actividade
     */
    private RepositorioAreaActividadeDatabase(){
    }

    /**
     * Devolve uma instância estática do Repositório de Áreas de Actividade
     * @return RepositorioAreaActividade
     */
    public static RepositorioAreaActividadeDatabase getInstance(){
        if(repositorioAreaActividadeDatabase == null) {
            repositorioAreaActividadeDatabase = new RepositorioAreaActividadeDatabase();
        }
        return repositorioAreaActividadeDatabase;
    }

    public void save(String codigo, String descBreve, String descDetalhada) throws AreaActividadeDuplicadaException, SQLException {

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createAreaActividade(?,?,?)}");

        if (findByCodigo(codigo) == null) {
            try {
                connection.setAutoCommit(false);

                callableStatement.setString(1, codigo);
                callableStatement.setString(2, descBreve);
                callableStatement.setString(3, descDetalhada);

                callableStatement.executeQuery();

                connection.commit();
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
        else {
            throw new AreaActividadeDuplicadaException(codigo + ": Área de actividade já registada");
        }

    }

    @Override
    public boolean save(AreaActividade areaActividade) throws SQLException {

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createAreaActividade(?,?,?)}");

        if (findByCodigo(areaActividade.getCodigo()) == null) {
            try {
                connection.setAutoCommit(false);

                callableStatement.setString(1, areaActividade.getCodigo());
                callableStatement.setString(2, areaActividade.getDescBreve());
                callableStatement.setString(3, areaActividade.getDescDetalhada());

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
        else {
            throw new AreaActividadeDuplicadaException(areaActividade.getCodigo() + ": Área de actividade já registada");
        }
        return false;
    }

    @Override
    public List<AreaActividade> getAll() throws SQLException {

        ArrayList<AreaActividade> areasActividade = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM AreaActividade"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String codigo = resultSet.getString(1);
                String descBreve = resultSet.getString(2);
                String descDetalhada = resultSet.getString(3);
                areasActividade.add(new AreaActividade(codigo, descBreve, descDetalhada));
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
        return areasActividade;
    }

    @Override
    public AreaActividade findByCodigo(String codigo) throws SQLException {
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL findByCodigoAreaActividade(?)}"
        );

        try {
            connection.setAutoCommit(false);

            callableStatement.setString(1, codigo);
            callableStatement.executeUpdate();

            return null;

        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();
            
        }
        return new AreaActividade();
    }


}
