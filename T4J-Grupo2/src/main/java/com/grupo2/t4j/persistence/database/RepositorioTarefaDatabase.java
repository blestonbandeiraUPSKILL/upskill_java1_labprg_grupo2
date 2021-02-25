package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.TarefaDuplicadaException;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.Tarefa;
import com.grupo2.t4j.persistence.RepositorioTarefa;
import com.grupo2.t4j.utils.DBConnectionHandler;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTarefaDatabase implements RepositorioTarefa {

    /**
     * Define uma instância estática do Repositório em que estão
     * registadas todas as Tarefas
     */
    private static RepositorioTarefaDatabase repositorioTarefaDatabase;
    
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";

    /**
     * Inicializa o Repositório de Tarefas
     */
    RepositorioTarefaDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Tarefas
     *
     * @return RepositorioTarefaDatabase
     */
    public static RepositorioTarefaDatabase getInstance(){
        if(repositorioTarefaDatabase == null) {
            repositorioTarefaDatabase = new RepositorioTarefaDatabase();
        }
        return repositorioTarefaDatabase;
    }

    @Override
    public void save(String codigoAreaActividade, String codigoCategoriaTarefa, 
            String referencia, String designacao, String descInformal, 
            String descTecnica, int duracao, double custo, String nifOrganizacao,
            String emailColaborador) {

    }

    @Override
    public boolean save(Tarefa tarefa) throws SQLException {

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createTarefa(?, ?, ?, ?, ?, ?, ?, ?, ?) }"
        );

        if (findByReferenciaENIF(tarefa.getReferencia(), tarefa.getNifOrganizacao()) == null) {
            try {
                connection.setAutoCommit(false);

                callableStatement.setString(1, tarefa.getNifOrganizacao());
                callableStatement.setString(2, tarefa.getReferencia());
                callableStatement.setString(3, tarefa.getDesignacao());
                callableStatement.setString(4, tarefa.getDescInformal());
                callableStatement.setString(5, tarefa.getDescTecnica());
                callableStatement.setInt(6, tarefa.getDuracaoEst());
                callableStatement.setDouble(7, tarefa.getCustoEst());
                callableStatement.setString(8, tarefa.getCodigoCategoriaTarefa());
                callableStatement.setString(9, tarefa.getEmailColaborador());

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
        else {
            throw new TarefaDuplicadaException(tarefa.getReferencia() + ": Tarefa já registada.");
        }
        return false;
    }

    @Override
    public Tarefa findByReferenciaENIF(String referencia, String nif) throws SQLException {
        
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{ CALL findByRefENif(?, ?) }"
        );

        try {
            connection.setAutoCommit(false);

            callableStatement.setString(1, referencia);
            callableStatement.setString(2, nif );

            callableStatement.executeUpdate();
            return null;

        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();


        }
        return new Tarefa();
    }

    @Override
    public List<Tarefa> findByCategoria(String codigoCategoria) throws SQLException {
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Tarefa WHERE codigoCategoria LIKE ?"
            );

            preparedStatement.setString(1, codigoCategoria);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nifOrganizacao = resultSet.getString(1);
                String referencia = resultSet.getString(2);
                String designacao = resultSet.getString(3);
                String descInformal = resultSet.getString(4);
                String descTecnica = resultSet.getString(5);
                int duracaoEstimada = resultSet.getInt(6);
                double custoEstimado = resultSet.getDouble(7);
                String emailColaborador = resultSet.getString(9);


                tarefas.add(new Tarefa(referencia, nifOrganizacao, designacao, descInformal, descTecnica,
                        duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador));
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

        return tarefas;
    }

    @Override
    public ArrayList<Tarefa> getAllOrganizacao(String nifOrganizacao) throws SQLException {
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Tarefa WHERE nifOrganizacao LIKE ?"
            );

            preparedStatement.setString(1, nifOrganizacao);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String referencia = resultSet.getString(2);
                String designacao = resultSet.getString(3);
                String descInformal = resultSet.getString(4);
                String descTecnica = resultSet.getString(5);
                int duracaoEst  = resultSet.getInt(6);
                double custoEst = resultSet.getDouble(7);
                String codigoCategoriaTarefa = resultSet.getString(8);
                String emailColaborador = resultSet.getString(9);

                tarefas.add(new Tarefa(referencia, nifOrganizacao, designacao, descInformal,
                        descTecnica, duracaoEst, custoEst,
                        codigoCategoriaTarefa,  emailColaborador));
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

        return tarefas;
    }
    
    @Override
    public ArrayList<Tarefa> getAll() throws SQLException {
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Tarefa"
            );

                        
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String nifOrganizacao = resultSet.getString(1);
                String referencia = resultSet.getString(2);
                String designacao = resultSet.getString(3);
                String descInformal = resultSet.getString(4);
                String descTecnica = resultSet.getString(5);
                int duracaoEst  = resultSet.getInt(6);
                double custoEst = resultSet.getDouble(7);
                String codigoCategoriaTarefa = resultSet.getString(8);
                String emailColaborador = resultSet.getString(9);

                tarefas.add(new Tarefa(referencia, nifOrganizacao, designacao, descInformal,
                        descTecnica, duracaoEst, custoEst,
                        codigoCategoriaTarefa,  emailColaborador));
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

        return tarefas;
    }
    

    @Override
    public List<Tarefa> findByColaboradorENif(String email, String nifOrganizacao) throws SQLException {
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Tarefa WHERE nifOrganizacao LIKE ? AND emailColaborador LIKE ?"
            );

            preparedStatement.setString(1, nifOrganizacao);
            preparedStatement.setString(2, email);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String referencia = resultSet.getString(2);
                String designacao = resultSet.getString(3);
                String descInformal = resultSet.getString(4);
                String descTecnica = resultSet.getString(5);
                int duracaoEst  = resultSet.getInt(6);
                double custoEst = resultSet.getDouble(7);
                String codigoCategoriaTarefa = resultSet.getString(8);
                String emailColaborador = resultSet.getString(9);

                tarefas.add(new Tarefa(referencia, nifOrganizacao, designacao, descInformal,
                        descTecnica, duracaoEst, custoEst,
                        codigoCategoriaTarefa,  emailColaborador));
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

        return tarefas;
    }

    @Override
    public List<Tarefa> findTarefasComAnuncio(String email, String nifOrganizacao) throws SQLException {
        List<Tarefa> tarefasPublicadas = new ArrayList<>();
        List<Tarefa> tarefas = new ArrayList<>();
        tarefas = findByColaboradorENif(email, nifOrganizacao);
        
        for (Tarefa tarefa : tarefas){
            if (findAnuncioByTarefa(tarefa.getEmailColaborador(), tarefa.getNifOrganizacao())!=null){
              tarefasPublicadas.add(tarefa);  
            } 
            
        }
        return tarefasPublicadas;

    }
    
    @Override
    public List<Tarefa> findTarefasSemAnuncio(String email, String nifOrganizacao) throws SQLException {
        List<Tarefa> tarefasNaoPublicadas = new ArrayList<>();
        List<Tarefa> tarefas = new ArrayList<>();
        tarefas = findByColaboradorENif(email, nifOrganizacao);
        
        for (Tarefa tarefa : tarefas){
            if (findAnuncioByTarefa(tarefa.getEmailColaborador(), tarefa.getNifOrganizacao())==null){
              tarefasNaoPublicadas.add(tarefa);  
            } 
            
        }
        return tarefasNaoPublicadas;

    }
    
    @Override
    public Tarefa findAnuncioByTarefa(String referencia, String nif) throws SQLException {
        
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{ CALL findAnuncioByTarefa(?, ?) }"
        );

        try {
            connection.setAutoCommit(false);

            callableStatement.setString(1, referencia);
            callableStatement.setString(2, nif );

            callableStatement.executeUpdate();
            return null;

        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();


        }
        return new Tarefa();
    }

}
