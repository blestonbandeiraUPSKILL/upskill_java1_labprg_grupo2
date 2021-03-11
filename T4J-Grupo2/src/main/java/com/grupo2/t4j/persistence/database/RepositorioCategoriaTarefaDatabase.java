package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.domain.Categoria;
import com.grupo2.t4j.domain.Obrigatoriedade;
import com.grupo2.t4j.persistence.RepositorioCategoriaTarefa;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;

public class RepositorioCategoriaTarefaDatabase implements RepositorioCategoriaTarefa {

    /**
     * Define uma instância estática do Repositório em que estão
     * registadas todas as Categorias de Tarefa
     */
    private static RepositorioCategoriaTarefaDatabase repositorioCategoriaTarefaDatabase;

    /**
     * Inicializa o Repositório de Categorias de Tarefa
     */
    private RepositorioCategoriaTarefaDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Categorias de Tarefa
     *
     * @return RepositorioCategoriaTarefaDatabase
     */
    public static RepositorioCategoriaTarefaDatabase getInstance(){
        if(repositorioCategoriaTarefaDatabase == null) {
            repositorioCategoriaTarefaDatabase = new RepositorioCategoriaTarefaDatabase();
        }
        return repositorioCategoriaTarefaDatabase;
    }

    @Override
    public boolean save(String codigoCategoria, String descBreve,
                     String descDetalhada, String codigoAreaActividade, int idGrauProficiencia,
                     Obrigatoriedade obrigatoriedade) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createCategoria (?, ?, ?, ?, ?, ?) }"
            );

            if (findByCodigo(codigoCategoria) == null) {

                connection.setAutoCommit(false);

                callableStatement.setString(1, codigoCategoria);
                callableStatement.setString(2, descBreve);
                callableStatement.setString(3, descDetalhada);
                callableStatement.setString(4, codigoAreaActividade);
                callableStatement.setString(5, String.valueOf(obrigatoriedade));
                callableStatement.setInt(6, idGrauProficiencia);

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
            } catch (SQLException sqlException) {
                sqlException.getErrorCode();
            }
        } finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return false;
    }

    @Override
    public boolean save(Categoria categoria) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createCategoria(?, ?, ?, ?)}"
            );

            if (findByCodigo(categoria.getCodigoCategoria()) == null) {

                connection.setAutoCommit(false);

                callableStatement.setString(1, categoria.getCodigoCategoria());
                callableStatement.setString(2, categoria.getDescBreve());
                callableStatement.setString(3, categoria.getDescDetalhada());
                callableStatement.setString(4, categoria.getCodigoAreaActividade());

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
            } catch (SQLException sqlException) {
                sqlException.getErrorCode();
            }
        } finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return false;
    }

    @Override
    public Categoria findByCodigo(String codigoCategoria) throws SQLException {

        Categoria categoria = new Categoria();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL findByCodigoCategoria(?) }"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, codigoCategoria);
            callableStatement.executeQuery();
            return null;

            /*PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Categoria WHERE codigoCategoria LIKE ?"
            );

            preparedStatement.setString(1, codigoCategoria);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                categoria.setCodigo(codigoCategoria);
                categoria.setDescBreve(resultSet.getString(2));
                categoria.setDescDetalhada(resultSet.getString(3));
                categoria.setCodigoAreaActividade(resultSet.getString(4));

            }*/
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();
        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return categoria;
    }

    @Override
    public ArrayList<Categoria> findByAreaActividade(String codigoAreaActividade) throws SQLException {
        ArrayList<Categoria> categoriasTarefa = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Categoria WHERE codigoAreaActividade LIKE ?"
            );

            preparedStatement.setString(1, codigoAreaActividade);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String codigoCompetencia = resultSet.getString(1);
                String descBreve = resultSet.getString(2);
                String descDetalhada = resultSet.getString(3);
                categoriasTarefa.add(new Categoria(codigoCompetencia, descBreve, descDetalhada, codigoAreaActividade));
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

        return categoriasTarefa;
    }

    @Override
    public ArrayList<Categoria> getAll() throws SQLException {
        ArrayList<Categoria> categorias = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Categoria"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String codigoCategoria = resultSet.getString(1);
                String descBreve = resultSet.getString(2);
                String descDetalhada = resultSet.getString(3);
                String codigoAreaActividade = resultSet.getString(4);
                categorias.add(new Categoria(codigoCategoria, descBreve, descDetalhada, codigoAreaActividade));
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

        return categorias;
    }
}
