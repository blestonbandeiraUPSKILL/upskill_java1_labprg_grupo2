package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.CategoriaInexistenteException;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.persistence.RepositorioCategoriaTarefa;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCategoriaTarefaDatabase implements RepositorioCategoriaTarefa {

    /**
     * Define uma instância estática do Repositório em que estão
     * registadas todas as Categorias de Tarefa
     */
    private static RepositorioCategoriaTarefaDatabase repositorioCategoriaTarefaDatabase;

    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";

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
    public void save(String codigoCategoria, String descBreve, String descDetalhada,
                     String codigoAreaActividade, List<CaracterizacaoCT> caracterizacaoCTS) {

    }

    @Override
    public boolean save(Categoria categoria) throws SQLException {
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createCategoria(?, ?, ?, ?)}"
        );

        if (findByCodigo(categoria.getCodigoCategoria()) == null) {
            try {
                connection.setAutoCommit(false);

                callableStatement.setString(1, categoria.getCodigoCategoria());
                callableStatement.setString(2, categoria.getDescBreve());
                callableStatement.setString(3, categoria.getDescDetalhada());
                callableStatement.setString(4, categoria.getCodigoAreaActividade());

                callableStatement.executeQuery();

                connection.commit();
                return true;
            } catch (SQLException exception) {
                exception.printStackTrace();
                exception.getSQLState();
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException sqlException) {
                    sqlException.getErrorCode();
                }
            } finally {
                dbConnectionHandler.closeAll();
            }
        }
        else {
            throw new CategoriaInexistenteException(categoria.getCodigoCategoria() + ": Categoria de Tarefa já registada.");
        }
        return false;
    }

    @Override
    public Categoria findByCodigo(String codigoCategoria) throws SQLException {

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL findByCodigoCategoria(?) }"
        );

        try {
            connection.setAutoCommit(false);

            callableStatement.setString(1, codigoCategoria);
            callableStatement.executeQuery();

            return null;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();
        }
        return new Categoria();
    }

    @Override
    public ArrayList<Categoria> findByAreaActividade(String codigoAreaActividade) {
        return null;
    }

    @Override
    public ArrayList<Categoria> getAll() throws SQLException {
        ArrayList<Categoria> categorias = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

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
            dbConnectionHandler.closeAll();
        }

        return categorias;
    }
}
