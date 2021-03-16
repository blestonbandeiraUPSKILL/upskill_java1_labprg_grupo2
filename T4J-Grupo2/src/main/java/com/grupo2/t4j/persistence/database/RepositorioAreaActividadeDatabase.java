package com.grupo2.t4j.persistence.database;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.domain.AreaActividade;
import com.grupo2.t4j.exception.AreaActividadeDuplicadaException;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAreaActividadeDatabase implements RepositorioAreaActividade {

    /**
     * Define uma instância estática do Repositório em que estão registadas todas
     * as Áreas de Actividade da plataforma
     */
    private static RepositorioAreaActividadeDatabase repositorioAreaActividadeDatabase;

    /**
     * Inicializa o Repositório de Areas de Actividade
     */
    private RepositorioAreaActividadeDatabase(){
    }

    /**
     * Devolve uma instância estática do Repositório de Áreas de Actividade
     * @return RepositorioAreaActividade
     */
    public static RepositorioAreaActividadeDatabase getInstance(){
        if(repositorioAreaActividadeDatabase == null) {
            repositorioAreaActividadeDatabase = new RepositorioAreaActividadeDatabase();
        }
        return repositorioAreaActividadeDatabase;
    }

    /**
     * Guarda uma area de actividade na base de dados
     * @param codigo
     * @param descBreve
     * @param descDetalhada
     * @throws AreaActividadeDuplicadaException
     * @throws SQLException 
     */
    public void save(String codigo, String descBreve, String descDetalhada) throws AreaActividadeDuplicadaException, SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createAreaActividade(?,?,?)}");

            if (findByCodigo(codigo) == null) {

                connection.setAutoCommit(false);

                callableStatement.setString(1, codigo);
                callableStatement.setString(2, descBreve);
                callableStatement.setString(3, descDetalhada);

                callableStatement.executeQuery();

                connection.commit();
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

    }

    /**
     * Guarda uma area de atividade na base de dados
     * @param areaActividade
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean save(AreaActividade areaActividade) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createAreaActividade(?,?,?)}");

            if (findByCodigo(areaActividade.getCodigo()) == null) {

                connection.setAutoCommit(false);

                callableStatement.setString(1, areaActividade.getCodigo());
                callableStatement.setString(2, areaActividade.getDescBreve());
                callableStatement.setString(3, areaActividade.getDescDetalhada());

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

    /**
     * Devolve todas as areas de atividade registadas na base de dados
     * @return
     * @throws SQLException 
     */
    @Override
    public List<AreaActividade> getAll() throws SQLException {

        ArrayList<AreaActividade> areasActividade = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM AreaActividade"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String codigo = resultSet.getString(1);
                String descBreve = resultSet.getString(2);
                String descDetalhada = resultSet.getString(3);
                areasActividade.add(new AreaActividade(codigo, descBreve, descDetalhada));
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
        return areasActividade;
    }

    /**
     * Devolve uma area de atividade a partir do seu codigo
     * @param codigo
     * @return
     * @throws SQLException 
     */
    @Override
    public AreaActividade findByCodigo(String codigo) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL findByCodigoAreaActividade(?)}"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, codigo);
            callableStatement.executeQuery();

            return null;

        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return new AreaActividade();
    }

    /**
     * Devolve uma area de actividade a partir do seu codigo
     * @param codigoAreaActividade
     * @return
     * @throws SQLException 
     */
    @Override
    public AreaActividade getAreaActividade(String codigoAreaActividade) throws SQLException {

        AreaActividade areaActividade = new AreaActividade();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "SELECT * FROM AreaActividade WHERE codigoAreaActividade LIKE ?"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, codigoAreaActividade);
            callableStatement.executeQuery();

            ResultSet resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                areaActividade.setCodigo(codigoAreaActividade);
                areaActividade.setDescBreve(resultSet.getString(2));
                areaActividade.setDescDetalhada(resultSet.getString(3));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return areaActividade;

    }


}
