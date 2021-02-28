package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.GrauProficienciaDuplicadoException;
import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.persistence.RepositorioGrauProficiencia;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioGrauProficienciaDatabase implements RepositorioGrauProficiencia {

    private static RepositorioGrauProficienciaDatabase repositorioGrauProficienciaDatabase;

    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";

    private RepositorioGrauProficienciaDatabase() throws SQLException {

    }

    public static RepositorioGrauProficienciaDatabase getInstance() throws SQLException {
        if (repositorioGrauProficienciaDatabase == null) {
            repositorioGrauProficienciaDatabase = new RepositorioGrauProficienciaDatabase();
        }
        return repositorioGrauProficienciaDatabase;
    }

    @Override
    public void save(int valor, String designacao, String codigoCompetenciaTecnica) {

    }

    @Override
    public boolean save(GrauProficiencia grauProficiencia) throws SQLException {

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createGrauProficiencia(?, ?, ?) }"
        );

        if (findByGrauECompetencia(grauProficiencia.getGrau(), grauProficiencia.getCodigoCompetenciaTecnica()) == null) {
            try {
                connection.setAutoCommit(false);

                callableStatement.setInt(1, grauProficiencia.getGrau());
                callableStatement.setString(2, grauProficiencia.getDesignacao());
                callableStatement.setString(3, grauProficiencia.getCodigoCompetenciaTecnica());

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
            throw new GrauProficienciaDuplicadoException(grauProficiencia.getDesignacao() + ": Grau de Proficiência já registado.");
        }
        return false;
    }

    @Override
    public List<GrauProficiencia> getAll() throws SQLException {
        return null;
    }


    public GrauProficiencia findByGrauECompetencia(int grau, String codigoCompetenciaTecnica) throws SQLException {
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{ CALL findByGrau(?, ?) }"
        );

        try {
            connection.setAutoCommit(false);

            callableStatement.setInt(1, grau);
            callableStatement.setString(2, codigoCompetenciaTecnica);

            callableStatement.executeUpdate();
            return null;

        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();


        }
        return new GrauProficiencia();
    }


    public List<GrauProficiencia> getAll(String codigoCompetenciaTecnica) throws SQLException {
        ArrayList<GrauProficiencia> grausProficiencia = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM GrauProficiencia WHERE codigoCompetenciaTecnica LIKE ?"
            );

            preparedStatement.setString(1, codigoCompetenciaTecnica);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idGrauProficiencia = resultSet.getInt(1);
                int grau = resultSet.getInt(2);
                String designacao = resultSet.getString(3);
                grausProficiencia.add(new GrauProficiencia(idGrauProficiencia, grau, designacao, codigoCompetenciaTecnica));
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

        return grausProficiencia;

    }

    @Override
    public ArrayList<GrauProficiencia> findByCompetenciaTecnica(String codigoCompetenciaTecnica) throws SQLException {
        ArrayList<GrauProficiencia> grausProficiencia = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM GrauProficiencia WHERE codigoCompetenciaTecnica LIKE ?"
            );

            preparedStatement.setString(1, codigoCompetenciaTecnica);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idGrauProficiencia = resultSet.getInt(1);
                String designacao = resultSet.getString(2);
                int grau = resultSet.getInt(3);
                grausProficiencia.add(new GrauProficiencia(idGrauProficiencia, grau, designacao, codigoCompetenciaTecnica));

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

        return grausProficiencia;
    }

    @Override
    public GrauProficiencia findByGrau(int grau) throws SQLException {
        return null;
    }



}
