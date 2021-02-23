package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.EnderecoPostal;
import com.grupo2.t4j.persistence.RepositorioEnderecoPostal;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEnderecoPostalDatabase implements RepositorioEnderecoPostal {

    private static RepositorioEnderecoPostalDatabase repositorioEnderecoPostalDatabase;
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";

    private RepositorioEnderecoPostalDatabase() throws SQLException{}

    public static RepositorioEnderecoPostalDatabase getInstance() throws SQLException {
        if (repositorioEnderecoPostalDatabase == null) {
            repositorioEnderecoPostalDatabase = new RepositorioEnderecoPostalDatabase();
        }
        return repositorioEnderecoPostalDatabase;
    }

    @Override
    public void save(int codigoEnderecoPostal, String arruamento, String numeroPorta, String localidade, String codigoPostal) {

    }

    @Override
    public boolean save(EnderecoPostal enderecoPostal) throws SQLException {

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL EnderecoPostal(?, ?, ?, ?)}"
        );

        if (findById(enderecoPostal.getCodigoEnderecoPostal()) == null) {
            try {
                connection.setAutoCommit(false);

                callableStatement.setString(1, enderecoPostal.getArruamento());
                callableStatement.setString(2, enderecoPostal.getPorta());
                callableStatement.setString(3, enderecoPostal.getLocalidade());
                callableStatement.setString(4, enderecoPostal.getCodigoPostal());

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
    public List<EnderecoPostal> getAll() throws SQLException {

        ArrayList<EnderecoPostal> enderecosPostais = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM EnderecoPostal"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idEnderecoPostal = resultSet.getInt(1);
                String arruamento = resultSet.getString(2);
                String numeroPorta = resultSet.getString(3);
                String localidade = resultSet.getString(4);
                String codPostal = resultSet.getString(5);
                enderecosPostais.add(new EnderecoPostal(idEnderecoPostal, arruamento, numeroPorta, localidade, codPostal));
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
        return enderecosPostais;
    }

    @Override
    public EnderecoPostal findById(int codigo) throws SQLException {

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL findById (?) }"
        );

        try {
            connection.setAutoCommit(false);

            callableStatement.setInt(1, codigo);
            callableStatement.executeUpdate();

            return null;

        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        return new EnderecoPostal();
    }


}
