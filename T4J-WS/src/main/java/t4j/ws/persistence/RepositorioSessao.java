package t4j.ws.persistence;

import t4j.ws.domain.Sessao;
import t4j.ws.domain.Contexto;
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

    public void saveContext(Contexto contextoDTO) throws SQLException {
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{ CALL createContext(?) }"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, contextoDTO.toString());

            callableStatement.executeQuery();

            connection.commit();

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

    }

    public boolean saveSessao(Sessao sessao) throws SQLException {
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createSessao(?)}"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, sessao.getContexto().toString());

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

    public Contexto findContextByString(String context) throws SQLException {

        Contexto contexto = new Contexto();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * From AppContext WHERE value LIKE ?"
            );

            preparedStatement.setString(1, context);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()) {
                contexto.setIdContexto(resultSet.getInt(1));
                contexto.setContexto(resultSet.getString(2));
                contexto.setValido(resultSet.getBoolean(3));
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
        return contexto;
    }

    public boolean contextInvalid(String context) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            connection.setAutoCommit(false);

            CallableStatement callableStatement = connection.prepareCall(
                    "UPDATE AppContext " +
                            "SET estado = 'false' " +
                            "WHERE value LIKE ?"
            );

            callableStatement.setString(1, context);
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
