package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.CompetenciaTecnicaDuplicadaException;
import com.grupo2.t4j.exception.CompetenciaTecnicaInexistenteException;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.persistence.RepositorioCompetenciaTecnica;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCompetenciaTecnicaDatabase implements RepositorioCompetenciaTecnica {

    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os Colaboradores
     */
    private static RepositorioCompetenciaTecnicaDatabase repositorioCompetenciaTecnicaDatabase;

    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";

    /**
     * Inicializa o Repositório de Colaboradores
     */
    private RepositorioCompetenciaTecnicaDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Colaboradores
     *
     * @return RepositorioCompetenciaTecnicaDatabase
     */
    public static RepositorioCompetenciaTecnicaDatabase getInstance(){
        if(repositorioCompetenciaTecnicaDatabase == null) {
            repositorioCompetenciaTecnicaDatabase = new RepositorioCompetenciaTecnicaDatabase();
        }
        return repositorioCompetenciaTecnicaDatabase;
    }

    @Override
    public void save(String codigo, String descBreve, String descDetalhada, String CodigoAreaActividade) {

    }

    @Override
    public boolean save(CompetenciaTecnica competenciaTecnica) throws SQLException {
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createCompetenciaTecnica(?, ?, ?}"
        );

        if(findByCodigo(competenciaTecnica.getCodigo()) == null) {
            try {
                connection.setAutoCommit(false);

                callableStatement.setString(1, competenciaTecnica.getDescricaoBreve());
                callableStatement.setString(2, competenciaTecnica.getDescricaoDetalhada());
                callableStatement.setString(3, competenciaTecnica.getCodigoAreaActividade());

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
            throw new CompetenciaTecnicaDuplicadaException(competenciaTecnica.getCodigo() + ": Competência técnica já registada");
        }
        return false;
    }

    @Override
    public List<CompetenciaTecnica> getAll() throws SQLException {
        ArrayList<CompetenciaTecnica> competenciasTecnicas = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM CompetenciaTenica"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String codigo = resultSet.getString(1);
                String descBreve = resultSet.getString(2);
                String descDetalhada = resultSet.getString(3);
                String idCaracterCT = resultSet.getString(4);
                String codigoAreaActividade = resultSet.getString(5);
                competenciasTecnicas.add(new CompetenciaTecnica(codigo, descBreve, descDetalhada, codigoAreaActividade, idCaracterCT));
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

        if (competenciasTecnicas != null) {
            return competenciasTecnicas;
        }
        else {
            throw new CompetenciaTecnicaInexistenteException("Não há competências técnicas para mostrar");
        }
    }

    @Override
    public CompetenciaTecnica findByCodigo(String codigo) throws SQLException {
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);

        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL findByCodigoCompetenciaTecnica(?)}"
        );

        try {
            connection.setAutoCommit(false);

            callableStatement.setString(1, codigo);
            callableStatement.executeQuery();

            return new CompetenciaTecnica();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

            return null;
        }
    }


    @Override
    public ArrayList<CompetenciaTecnica> findByAreaActividade(String codigoAreaActividade) {
        return null;
    }
}
