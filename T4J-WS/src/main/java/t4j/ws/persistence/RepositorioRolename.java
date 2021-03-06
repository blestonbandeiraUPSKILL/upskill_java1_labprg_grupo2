package t4j.ws.persistence;

import t4j.ws.domain.Rolename;
import t4j.ws.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioRolename {

    private static RepositorioRolename repositorioRolename;

    private RepositorioRolename(){    }
    public static RepositorioRolename getInstance(){
        if(repositorioRolename == null) {
            repositorioRolename = new RepositorioRolename();
        }
        return repositorioRolename;
    }

    public boolean save(Rolename rolename) throws SQLException {
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createRolename(?, ?)}"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, rolename.getDesignacao());
            callableStatement.setString(2, rolename.getDescricao());

            callableStatement.executeQuery();
            connection.commit();
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

    public List<Rolename> getAll() throws SQLException {
        List<Rolename> rolenames = new ArrayList<Rolename>();
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "SELECT * FROM Rolename"
            );

            callableStatement.executeQuery();

            ResultSet resultSet = callableStatement.getResultSet();

            while(resultSet.next()) {
                int idRolename = resultSet.getInt(1);
                String designacao = resultSet.getString(2);
                String descricao = resultSet.getString(3);
                rolenames.add(new Rolename(idRolename, designacao, descricao));
            }
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
        return rolenames;
    }

    public Rolename getByName(String rolename) throws SQLException {
        Rolename rn = null;

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Rolename WHERE designacao LIKE ?"
            );

            preparedStatement.setString(1, rolename);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()) {
                rn.setIdRolename(resultSet.getInt(1));
                rn.setDesignacao(rolename);
                rn.setDescricao(resultSet.getString(3));
            }

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

        return rn;
    }
}
