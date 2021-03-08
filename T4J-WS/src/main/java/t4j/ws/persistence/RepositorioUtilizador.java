package t4j.ws.persistence;

import t4j.ws.domain.Utilizador;
import t4j.ws.utils.DBConnectionHandler;

import java.sql.*;
import java.util.List;

public class RepositorioUtilizador {

    private static RepositorioUtilizador repositorioUtilizador;

    private RepositorioUtilizador(){    }

    public static RepositorioUtilizador getInstance(){
        if(repositorioUtilizador == null) {
            repositorioUtilizador = new RepositorioUtilizador();
        }
        return repositorioUtilizador;
    }

    public boolean save(Utilizador utilizador) {
        return false;
    }

    public Utilizador findByEmail(String email) {
        return null;
    }

    public List<Utilizador> getAll() {
        return null;
    }

    public Utilizador getByEmail(String email) {
        return null;
    }

    public boolean addRoleToUser(Utilizador utilizador, String rolename) throws SQLException {
        int idRolename = 0;
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT idRolename FROM Rolename WHERE designacao LIKE ?"
            );

            preparedStatement.setString(1, rolename);
            preparedStatement.executeQuery();

            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) {
                idRolename = rs.getInt(1);
            }

            CallableStatement callableStatement = connection.prepareCall(
                    "UPDATE Utilizador " +
                            "SET idRolename = ? " +
                            "WHERE email LIKE ?"
            );

            callableStatement.setInt(1, idRolename);
            callableStatement.setString(2, utilizador.getEmail().toString());

            callableStatement.executeQuery();

            return true;
        }
        catch (SQLException exception) {
            exception.getSQLState();
            exception.printStackTrace();
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

    public boolean deleteRoleFromUser(Utilizador utilizador) throws SQLException {
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE rolename FROM Utilizador WHERE email LIKE ?"
            );

            preparedStatement.setString(1, utilizador.getEmail().toString());
            preparedStatement.executeQuery();
            return true;
        }
        catch (SQLException exception) {
            exception.getSQLState();
            exception.printStackTrace();
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

    public void saveWithRole(Utilizador utilizador) {
    }
}
