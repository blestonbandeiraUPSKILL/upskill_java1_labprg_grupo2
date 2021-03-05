package t4j.ws.persistence;

import t4j.ws.domain.Sessao;
import t4j.ws.dto.Contexto;
import t4j.ws.utils.DBConnectionHandler;

import java.sql.*;


public class RepositorioSessao {

    private static RepositorioSessao repositorioSessao;

    private RepositorioSessao(){    }
    public static RepositorioSessao getInstance(){
        if(repositorioSessao == null) {
            repositorioSessao = new RepositorioSessao();
        }
        return repositorioSessao;
    }

    public void saveContext(Contexto contextoDTO) {

    }

    public boolean saveSessao(Sessao sessao) throws SQLException {
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createSessao(?)}"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(2, sessao.getContexto().toString());

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

    public Contexto findContextByString(String context) {
        return null;
    }

    public boolean contextInvalid(String context) throws SQLException {
        Connection connection = DBConnectionHandler.getInstance().openConnection();
        int idAppContext = 0;

        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareCall(
                    "SELECT idAppContext " +
                            "FROM AppContext " +
                            "WHERE value LIKE ?"
            );

            preparedStatement.setString(1, context);

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                idAppContext = resultSet.getInt(1);
            }

            CallableStatement callableStatement = connection.prepareCall(
                    "UPDATE UserSession " +
                            "SET estado LIKE 'invalido' " +
                            "WHERE idAppContext LIKE ?"
            );


            callableStatement.setInt(1, idAppContext);
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


    public Sessao findByContext(String appContext) throws SQLException {
        int idAppContext = 0;

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareCall(
                    "SELECT idAppContext " +
                            "FROM AppContext " +
                            "WHERE value LIKE ?"
            );

            preparedStatement.setString(1, appContext);

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                idAppContext = resultSet.getInt(1);
            }

            CallableStatement callableStatement = connection.prepareCall(
                    "UPDATE UserSession " +
                            "SET estado LIKE 'invalido' " +
                            "WHERE idAppContext LIKE ?"
            );

            callableStatement.setInt(1, idAppContext);
            callableStatement.executeQuery();

            connection.commit();
            return new Sessao();

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
        return null;
    }
}
