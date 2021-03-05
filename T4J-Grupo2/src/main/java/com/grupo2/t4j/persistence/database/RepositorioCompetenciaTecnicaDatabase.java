package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.domain.CompetenciaTecnica;
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

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createCompetenciaTecnica(?, ?, ?, ?) }"
            );

            if(findByCodigo(competenciaTecnica.getCodigo()) == null) {

                connection.setAutoCommit(false);

                callableStatement.setString(1, competenciaTecnica.getCodigo());
                callableStatement.setString(2, competenciaTecnica.getDescricaoBreve());
                callableStatement.setString(3, competenciaTecnica.getDescricaoDetalhada());
                callableStatement.setString(4, competenciaTecnica.getCodigoAreaActividade());

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
    public List<CompetenciaTecnica> getAll() throws SQLException {
        ArrayList<CompetenciaTecnica> competenciasTecnicas = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM CompetenciaTecnica"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String codigo = resultSet.getString(1);
                String descBreve = resultSet.getString(2);
                String descDetalhada = resultSet.getString(3);
                String codigoAreaActividade = resultSet.getString(4);
                competenciasTecnicas.add(new CompetenciaTecnica(codigo, descBreve, descDetalhada, codigoAreaActividade));
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

        return competenciasTecnicas;
    }

    @Override
    public CompetenciaTecnica findByCodigo(String codigo) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL findByCodigoCompetenciaTecnica(?)}"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, codigo);
            callableStatement.executeUpdate();

            return null;

        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();
        }

        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return new CompetenciaTecnica();
    }

    @Override
    public CompetenciaTecnica findCompetenciaByAreaActividade(String codigoAreaActividade) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL findCompTecnicaByAreaActividade(?) }"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, codigoAreaActividade);
            callableStatement.executeUpdate();

            return null;

        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return new CompetenciaTecnica();
    }


    @Override
    public List<CompetenciaTecnica> findByAreaActividade(String codigoAreaActividade) throws SQLException {
        ArrayList<CompetenciaTecnica> competenciasTecnicas = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM CompetenciaTecnica WHERE codigoAreaActividade LIKE ?"
            );

            preparedStatement.setString(1, codigoAreaActividade);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String codigoCompetencia = resultSet.getString(1);
                String descBreve = resultSet.getString(2);
                String descDetalhada = resultSet.getString(3);
                competenciasTecnicas.add(new CompetenciaTecnica(codigoCompetencia, descBreve, descDetalhada, codigoAreaActividade));
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

        return competenciasTecnicas;

    }

}
