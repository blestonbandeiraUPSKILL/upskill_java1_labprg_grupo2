package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.model.Anuncio;
import com.grupo2.t4j.model.Tarefa;
import com.grupo2.t4j.persistence.RepositorioTarefa;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTarefaDatabase implements RepositorioTarefa {

    /**
     * Define uma instância estática do Repositório em que estão
     * registadas todas as Tarefas
     */
    private static RepositorioTarefaDatabase repositorioTarefaDatabase;

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

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createTarefa(?, ?, ?, ?, ?, ?, ?, ?, ?) }"
            );

            if (findByReferenciaENIF(tarefa.getReferencia(), tarefa.getNifOrganizacao()) == null) {

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

    @Override
    public Tarefa findByReferenciaENIF(String referencia, String nif) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{ CALL findByRefENif(?, ?) }"
            );

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

        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return new Tarefa();
    }

    @Override
    public List<Tarefa> findByCategoria(String codigoCategoria) throws SQLException {
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

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
            DBConnectionHandler.getInstance().closeAll();
        }

        return tarefas;
    }

    @Override
    public ArrayList<Tarefa> getAllOrganizacao(String nifOrganizacao) throws SQLException {
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

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
            DBConnectionHandler.getInstance().closeAll();
        }

        return tarefas;
    }
    
    @Override
    public ArrayList<Tarefa> getAll() throws SQLException {
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

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
            DBConnectionHandler.getInstance().closeAll();
        }

        return tarefas;
    }
    

    @Override
    public List<Tarefa> findByColaboradorENif(String email, String nifOrganizacao) throws SQLException {
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

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

                tarefas.add(new Tarefa(referencia, nifOrganizacao, designacao, descInformal,
                        descTecnica, duracaoEst, custoEst,
                        codigoCategoriaTarefa, email));
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

        return tarefas;
    }

    @Override
    public List<Tarefa> findTarefasPublicadas(List<String> referenciasTarefa, String nifOrganizacao, String emailColaborador) throws SQLException {
        List<Tarefa> tarefasComAnuncio = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            for (String referencia : referenciasTarefa) {
                CallableStatement callableStatement = connection.prepareCall(
                        "SELECT * FROM Tarefa " +
                                "INNER JOIN Anuncio " +
                                "ON Tarefa.referencia LIKE Anuncio.referenciaTarefa " +
                                "AND Tarefa.nifOrganizacao LIKE Anuncio.nifOrganizacao " +
                                "WHERE tarefa.referencia LIKE ? " +
                                "AND tarefa.nifOrganizacao LIKE ?" +
                                "AND Tarefa.emailColaborador LIKE ? "
                );


                callableStatement.setString(1, referencia);
                callableStatement.setString(2, nifOrganizacao);
                callableStatement.setString(3, emailColaborador);

                callableStatement.executeUpdate();

                ResultSet resultSet = callableStatement.getResultSet();

                while (resultSet.next()) {

                    String designacao = resultSet.getString(3);
                    String descInformal = resultSet.getString(4);
                    String descTecnica = resultSet.getString(5);
                    int duracaoEstimada = resultSet.getInt(6);
                    double custoEstimado = resultSet.getDouble(7);
                    String codigoCategoria = resultSet.getString(8);
                    tarefasComAnuncio.add(new Tarefa(referencia, nifOrganizacao,
                            designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador));

                }
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return tarefasComAnuncio;
    }

    public int findIdAnuncio(String nifOrganizacao, String referenciaTarefa) throws SQLException {
        int idAnuncio = 0;

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "SELECT idAnuncio FROM Anuncio " +
                            "INNER JOIN Tarefa " +
                            "ON Anuncio.referenciaTarefa LIKE Tarefa.referencia " +
                            "AND Anuncio.nifOrganizacao LIKE Tarefa.nifOrganizacao " +
                            " WHERE Anuncio.nifOrganizacao LIKE ? AND Anuncio.referenciaTarefa LIKE ?"
            );

            callableStatement.setString(1, nifOrganizacao);
            callableStatement.setString(2, referenciaTarefa);


            callableStatement.executeUpdate();

            ResultSet resultSet = callableStatement.getResultSet();

            while(resultSet.next()) {
                idAnuncio = resultSet.getInt(1);

            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return idAnuncio;

    }

    @Override
    public List<String> findReferenciaTarefa(String nifOrganizacao) throws SQLException {
        List<String> referenciasTarefa = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "SELECT referencia FROM Tarefa WHERE nifOrganizacao LIKE ?"
            );

            callableStatement.setString(1, nifOrganizacao);


            callableStatement.executeUpdate();

            ResultSet resultSet = callableStatement.getResultSet();

            while(resultSet.next()) {
                String referenciaTarefa = resultSet.getString(1);
                referenciasTarefa.add(referenciaTarefa);
            }
        }

        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }


        return referenciasTarefa;
    }

    @Override
    public List<Tarefa> findTarefasNaoPublicadas(List<String> referenciasTarefa, String email, String nifOrganizacao) throws SQLException {

        List<Tarefa> tarefasSemAnuncio = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            for (String referenciaTarefa : referenciasTarefa) {
                CallableStatement callableStatement = connection.prepareCall(
                        "SELECT * FROM Tarefa " +
                                "LEFT JOIN Anuncio " +
                                "ON Tarefa.referencia LIKE Anuncio.referenciaTarefa " +
                                "AND Tarefa.nifOrganizacao LIKE Anuncio.nifOrganizacao " +
                                "WHERE Anuncio.referenciaTAREFA IS NULL AND Anuncio.nifOrganizacao IS NULL " +
                                "AND Tarefa.referencia LIKE ? AND Tarefa.nifOrganizacao LIKE ? " +
                                "AND Tarefa.emailColaborador LIKE ?"
                );

                callableStatement.setString(1, referenciaTarefa);
                callableStatement.setString(2, nifOrganizacao);
                callableStatement.setString(3, email);

                callableStatement.executeUpdate();

                ResultSet resultSet = callableStatement.getResultSet();

                while (resultSet.next()) {

                    String designacao = resultSet.getString(3);
                    String descInformal = resultSet.getString(4);
                    String descTecnica = resultSet.getString(5);
                    int duracaoEstimada = resultSet.getInt(6);
                    double custoEstimado = resultSet.getDouble(7);
                    String codigoCategoria = resultSet.getString(8);
                    tarefasSemAnuncio.add(new Tarefa(referenciaTarefa, nifOrganizacao,
                            designacao, descInformal, descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, email));

                }
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return tarefasSemAnuncio;

    }


    public List<Anuncio> findAnuncioByTarefa(String referencia, String nif) throws SQLException {

        List<Anuncio> tarefasComAnuncio = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    " SELECT * FROM Anuncio WHERE referenciaTarefa LIKE ? AND nifOrganizacao LIKE ? "
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, referencia);
            callableStatement.setString(2, nif );

            callableStatement.executeUpdate();

            ResultSet resultSet = callableStatement.getResultSet();

            while(resultSet.next()) {
                int idAnuncio = resultSet.getInt(1);
                String referenciaTarefa = resultSet.getString(2);
                String nifOrganizacao = resultSet.getString(3);
                String dataInicioPublicitacao = resultSet.getDate(4).toString();
                String dataFimPublicitacao = resultSet.getDate(5).toString();
                String dataInicioCandidatura = resultSet.getDate(6).toString();
                String dataFimCandidatura = resultSet.getDate(7).toString();
                String dataInicioSeriacao = resultSet.getDate(8).toString();
                String dataFimSeriacao = resultSet.getDate(9).toString();
                int idTipoRegimento = resultSet.getInt(10);


                tarefasComAnuncio.add(new Anuncio(idAnuncio, referenciaTarefa, nifOrganizacao, dataInicioPublicitacao, dataFimPublicitacao, dataInicioCandidatura, dataFimCandidatura,
                        dataInicioSeriacao, dataFimSeriacao, idTipoRegimento));
            }

        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();
        }

        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return tarefasComAnuncio;
    }

    @Override
    public Tarefa findTarefa(int idAnuncio) throws SQLException {
        Tarefa tarefa = new Tarefa();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Tarefa " +
                            "INNER JOIN Anuncio " +
                            "ON Anuncio.referenciaTarefa LIKE Tarefa.referencia " +
                            "AND Anuncio.nifOrganizacao LIKE Tarefa.nifOrganizacao " +
                            "WHERE idAnuncio =  ?"

            );

            preparedStatement.setInt(1, idAnuncio);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                tarefa.setNifOrganizacao(resultSet.getString(1));
                tarefa.setReferencia(resultSet.getString(2));
                tarefa.setDesignacao(resultSet.getString(3));
                tarefa.setDescInformal(resultSet.getString(4));
                tarefa.setDescTecnica(resultSet.getString(5));
                tarefa.setDuracaoEst(resultSet.getInt(6));
                tarefa.setCustoEst(resultSet.getDouble(7));
                tarefa.setCodigoCategoriaTarefa(resultSet.getString(8));
                tarefa.setEmailColaborador(resultSet.getString(9));
            }

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
            DBConnectionHandler.getInstance().closeAll();
        }
        return tarefa;
    }

    public List<Tarefa> getAllTarefasPublicadas() throws SQLException {
        List<Tarefa> tarefas = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Tarefa INNER JOIN Anuncio " +
                            "ON Tarefa.referencia LIKE Anuncio.referenciaTarefa " +
                            "AND Tarefa.nifOrganizacao LIKE Anuncio.nifOrganizacao"
            );


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nifOrganizacao = resultSet.getString(1);
                String referencia = resultSet.getString(2);
                String designacao = resultSet.getString(3);
                String descInformal = resultSet.getString(4);
                String descTecnica = resultSet.getString(5);
                int duracaoEstimada = resultSet.getInt(6);
                double custoEstimado = resultSet.getDouble(7);
                String codigoCategoria = resultSet.getString(8);
                String emailColaborador = resultSet.getString(9);

                tarefas.add(new Tarefa(nifOrganizacao, referencia, designacao, descInformal,
                        descTecnica, duracaoEstimada, custoEstimado, codigoCategoria, emailColaborador));
            }

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
            DBConnectionHandler.getInstance().closeAll();
        }
        return tarefas;
    }
}
