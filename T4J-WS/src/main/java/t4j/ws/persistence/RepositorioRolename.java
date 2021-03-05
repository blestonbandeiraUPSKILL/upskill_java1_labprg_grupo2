package t4j.ws.persistence;

import t4j.ws.domain.Rolename;
import t4j.ws.utils.DBConnectionHandler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
