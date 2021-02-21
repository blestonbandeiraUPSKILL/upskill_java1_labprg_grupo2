package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.GrauProficienciaDuplicadoException;
import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.persistence.RepositorioGrauProficiencia;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioGrauProficienciaDatabase implements RepositorioGrauProficiencia {

    private static RepositorioGrauProficienciaDatabase repositorioGrauProficienciaDatabase;

    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_04";
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
                "{CALL createGrauProficiencia(?, ?, ?)}"
        );

        if (!findByValorECompetenciaTecnica(grauProficiencia.getGrau(), grauProficiencia.getCodigoCompetenciaTecnica())) {
            try {
                connection.setAutoCommit(false);

                callableStatement.setString(1, grauProficiencia.getDesignacao());
                callableStatement.setString(2, grauProficiencia.getCodigoCompetenciaTecnica());
                callableStatement.setString(3, grauProficiencia.getGrau());

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
    public boolean findByValorECompetenciaTecnica(String grau, String codigoCompetenciaTecnica) throws SQLException {

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL findByValorECompetenciaTecnica(?,?) }"
        );

        try {
            connection.setAutoCommit(false);

            callableStatement.setString(1, grau);
            callableStatement.setString(2, codigoCompetenciaTecnica);
            callableStatement.executeUpdate();

            return true;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

            return false;
        }
    }

    @Override
    public List<GrauProficiencia> getAll() {
        return null;
    }

    @Override
    public ArrayList<GrauProficiencia> findByCompetenciaTecnica(String codigoCompetenciaTecnica) {
        return null;
    }

    @Override
    public GrauProficiencia findByGrau(String valor, String codigoCompetenciaTecnica) {
        return null;
    }
}
