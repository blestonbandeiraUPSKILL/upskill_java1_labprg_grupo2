package com.grupo2.t4j.persistence.database;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.domain.Anuncio;
import com.grupo2.t4j.dto.AnuncioDTO;
import com.grupo2.t4j.domain.Tarefa;
import com.grupo2.t4j.domain.TipoRegimento;
import com.grupo2.t4j.exception.AnuncioDuplicadoException;
import com.grupo2.t4j.persistence.RepositorioAnuncio;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAnuncioDataBase implements RepositorioAnuncio {

    /**
     * Define uma instância estática do Repositório em que estão registados
     * todos os Anúncios
     */
    private static RepositorioAnuncioDataBase repositorioAnuncioDataBase;

    /**
     * Inicializa o Repositório de Anúncios
     */
    private RepositorioAnuncioDataBase() {
    }

    /**
     * Devolve uma instância estática do Repositório de Anúncios
     *
     * @return RepositorioAnuncioDatabase
     */
    public static RepositorioAnuncioDataBase getInstance() {
        if (repositorioAnuncioDataBase == null) {
            repositorioAnuncioDataBase = new RepositorioAnuncioDataBase();
        }
        return repositorioAnuncioDataBase;
    }

    /**
     * Guarda um anuncio na base de dados
     * @param referenciaTarefa
     * @param nifOrganizacao
     * @param dtInicioPublicitacao
     * @param dtFimPublicitacao
     * @param dtInicioCandidatura
     * @param dtFimCandidatura
     * @param dtInicioSeriacao
     * @param dtFimSeriacao
     * @param idTipoRegimento
     * @return
     * @throws AnuncioDuplicadoException
     * @throws SQLException 
     */
    @Override
    public boolean save(String referenciaTarefa, String nifOrganizacao, String dtInicioPublicitacao,
                        String dtFimPublicitacao, String dtInicioCandidatura, String dtFimCandidatura, String dtInicioSeriacao,
            String dtFimSeriacao, int idTipoRegimento) throws AnuncioDuplicadoException, SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {

            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createAnuncio(?, ?, ?, ?, ?, ?, ?, ?, ?) }");

            connection.setAutoCommit(false);

            callableStatement.setString(1, referenciaTarefa);
            callableStatement.setString(2, nifOrganizacao);
            callableStatement.setDate(3, Date.valueOf(dtInicioPublicitacao));
            callableStatement.setDate(4, Date.valueOf(dtFimPublicitacao));
            callableStatement.setDate(5, Date.valueOf(dtInicioCandidatura));
            callableStatement.setDate(6, Date.valueOf(dtFimCandidatura));
            callableStatement.setDate(7, Date.valueOf(dtInicioSeriacao));
            callableStatement.setDate(8, Date.valueOf(dtFimSeriacao));
            callableStatement.setInt(9, idTipoRegimento);

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
            DBConnectionHandler.getInstance().closeAll();
        }

        return false;

    }

    /**
     * Guarda um anuncio na base de dados
     * @param anuncio
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean save(Anuncio anuncio) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            if(findAnuncioByIdTarefa(anuncio.getReferenciaTarefa(), anuncio.getNifOrganizacao()) == null) {
                CallableStatement callableStatement = connection.prepareCall(
                        "{CALL createAnuncio(?, ?, ?, ?, ?, ?, ?, ?, ?)}");

                connection.setAutoCommit(false);

                callableStatement.setString(1, anuncio.getReferenciaTarefa());
                callableStatement.setString(2, anuncio.getNifOrganizacao());
                callableStatement.setDate(3, Date.valueOf(anuncio.getDtInicioPub()));
                callableStatement.setDate(4, Date.valueOf(anuncio.getDtFimPub()));
                callableStatement.setDate(5, Date.valueOf(anuncio.getDtInicioCand()));
                callableStatement.setDate(6, Date.valueOf(anuncio.getDtFimCand()));
                callableStatement.setDate(7, Date.valueOf(anuncio.getDtInicioSeriacao()));
                callableStatement.setDate(8, Date.valueOf(anuncio.getDtFimSeriacao()));
                callableStatement.setInt(9, anuncio.getIdTipoRegimento());

                callableStatement.executeQuery();

                connection.commit();
                return true;
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

        return false;
    }

    @Override
    public ArrayList<Anuncio> getAll() throws SQLException {
        return null;
    }


    /**
     * Devolve o anuncio que corresponde a uma tarefa 
     * @param referenciaTarefa
     * @param nifOrganizacao
     * @return
     * @throws SQLException 
     */
    @Override
    public Anuncio findAnuncioByIdTarefa(String referenciaTarefa, String nifOrganizacao) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{ CALL findAnuncioByTarefa(?, ?) }"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, referenciaTarefa);
            callableStatement.setString(2, nifOrganizacao);

            callableStatement.executeUpdate();
            return null;

        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return new Anuncio();
    }

    /**
     * Devolve todos os tipos de regimento registados na base de dados
     * @return
     * @throws SQLException 
     */
    @Override
    public ArrayList<TipoRegimento> getAllRegimento() throws SQLException {

        ArrayList<TipoRegimento> tiposRegimento = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM TipoRegimento"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idTipoRegimento = resultSet.getInt(1);
                String designacao = resultSet.getString(2);
                String descricaoRegras = resultSet.getString(3);
                tiposRegimento.add(new TipoRegimento(idTipoRegimento, designacao,
                        descricaoRegras));
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
        return tiposRegimento;
    }

    /**
     * Devolve um anuncio a partir do seu id
     * @param idAnuncio
     * @return
     * @throws SQLException 
     */
    public Anuncio getAnuncio(int idAnuncio) throws SQLException {
        Anuncio anuncio = new Anuncio();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT idAnuncio, referenciaTarefa, nifOrganizacao, " +
                            "TO_CHAR (dataInicioPublicitacao, 'yyyy-mm-dd'), " +
                            "TO_CHAR (dataFimPublicitacao, 'yyyy-mm-dd'), " +
                            "TO_CHAR (dataInicioCandidatura, 'yyyy-mm-dd'), " +
                            "TO_CHAR (dataFimCandidatura, 'yyyy-mm-dd'), " +
                            "TO_CHAR (dataInicioSeriacao, 'yyyy-mm-dd'), " +
                            "TO_CHAR (dataFimSeriacao, 'yyyy-mm-dd'), " +
                            "idProcessoSeriacao, idTipoRegimento " +
                            " FROM Anuncio " +
                            "WHERE idAnuncio = ?"

            );

            preparedStatement.setInt(1, idAnuncio);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                anuncio.setReferenciaTarefa(resultSet.getString(2));
                anuncio.setNifOrganizacao(resultSet.getString(3));
                anuncio.setDtInicioPub(resultSet.getString(4));
                anuncio.setDtFimPub(resultSet.getString(5));
                anuncio.setDtInicioCand(resultSet.getString(6));
                anuncio.setDtFimCand(resultSet.getString(7));
                anuncio.setDtInicioSeriacao(resultSet.getString(8));
                anuncio.setDtFimSeriacao(resultSet.getString(9));
                anuncio.setIdTipoRegimento(resultSet.getInt(11));

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
        return anuncio;
    }

    /**
     * Devolve o anuncio relativo a uma tarefa a que o freelancer se candidatou
     * @param emailFreelancer
     * @param referenciaTarefa
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean findAnuncioByEmailFreelancer(String emailFreelancer, String referenciaTarefa ) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Anuncio " +
                            "LEFT JOIN Candidatura " +
                            "ON Anuncio.idAnuncio = Candidatura.idAnuncio " +
                            "WHERE Candidatura.emailFreelancer LIKE ? " +
                            "AND Anuncio.referenciaTarefa LIKE ?"
            );

            preparedStatement.setString(1, emailFreelancer);
            preparedStatement.setString(2, referenciaTarefa);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return false;
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
        return true;
    }

    /**
     * Devolve todas as tarefas de um colaborador e de um dado tipo de regimento
     * @param referenciasTarefa
     * @param emailColaborador
     * @param idTipoRegimento
     * @return
     * @throws SQLException 
     */
    @Override
    public List<String> getAllRefTarefasTipoRegimento(List<String> referenciasTarefa, String emailColaborador, int idTipoRegimento) throws SQLException{
        
        List<String> refTarefasSeriacaoManual = new ArrayList<>();
        
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
           for (String referencia : referenciasTarefa) {
                CallableStatement callableStatement = connection.prepareCall(
                        "SELECT * FROM Anuncio " +
                                "INNER JOIN Tarefa " +
                                "ON Anuncio.referenciaTarefa LIKE Tarefa.referencia " +
                                "WHERE Tarefa.referencia LIKE ? " +
                                "AND Tarefa.emailColaborador LIKE ? " +
                                "AND Anuncio.idTipoRegimento LIKE ?"
                );
              
                callableStatement.executeUpdate();

                ResultSet resultSet = callableStatement.getResultSet();
            
            while (resultSet.next()) {
                   
                    refTarefasSeriacaoManual.add(referencia);                 
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
        return refTarefasSeriacaoManual;
      
        }
    
    /**
     * Devolve uma lista de referências de tarefas anunciadas mas não seriadas
     * @param referenciasTarefa
     * @param nifOrganizacao
     * @return
     * @throws SQLException 
     */
    @Override
    public List<String> getAllRefTarefasNaoSeriadas(List<String> referenciasTarefa, String nifOrganizacao) throws SQLException{
        
        List<String> refTarefasNaoSeriadas = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            for (String referencia : referenciasTarefa) {
                CallableStatement callableStatement = connection.prepareCall(
                    "SELECT * FROM Anuncio LEFT JOIN ProcessoSeriacao " +
                            "ON ProcessoSeriacao.idAnuncio = Anuncio.idAnuncio " +
                            "WHERE ProcessoSeriacao.idAnuncio IS NULL"
                );
                              
                callableStatement.executeUpdate();

                ResultSet resultSet = callableStatement.getResultSet();
            
            while (resultSet.next()) {
                    
                refTarefasNaoSeriadas.add(referencia);                 
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
        return refTarefasNaoSeriadas;
      
    }

    /**
     * Devolve uma lista de referências de tarefas anunciadas, em período de seriação,
     * independente de terem sido seriadas ou não
     * @param referenciasTarefa
     * @return
     * @throws SQLException
     */
    @Override
    public List<Tarefa> getAllRefTarefasASeriar(List<String> referenciasTarefa, String nifOrganizacao, String emailColaborador) throws SQLException{

        List<Tarefa> refTarefasASeriar = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            for (String referencia : referenciasTarefa) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Tarefa " +
                            "INNER JOIN Anuncio  " +
                            "ON Anuncio.referenciaTarefa LIKE Tarefa.referencia " +
                            "AND Anuncio.nifOrganizacao LIKE Tarefa.nifOrganizacao " +
                            "WHERE Tarefa.referencia LIKE ? " +
                            "AND Tarefa.nifOrganizacao LIKE ?" +
                            "AND sysdate BETWEEN Anuncio.dataInicioSeriacao AND Anuncio.dataFimSeriacao " +
                            "AND Tarefa.emailColaborador LIKE ? "
                    );

                preparedStatement.setString(1, referencia);
                preparedStatement.setString(2, nifOrganizacao);
                preparedStatement.setString(3, emailColaborador);

                preparedStatement.executeQuery();

                ResultSet resultSet = preparedStatement.getResultSet();

                while (resultSet.next()) {
                    String designacao = resultSet.getString(3);
                    String descInformal = resultSet.getString(4);
                    String descTecnica = resultSet.getString(5);
                    int duracaoEstimada = resultSet.getInt(6);
                    double custoEstimado = resultSet.getDouble(7);
                    String codigoCategoria = resultSet.getString(8);
                    refTarefasASeriar.add(new Tarefa(nifOrganizacao, referencia,
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

        return refTarefasASeriar;

    }

    @Override
    public List<Integer> getAnunciosSeriados(String nifOrganizacao) throws SQLException {
        List<Integer> idsAnunciosSeriados = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Anuncio " +
                            "INNER JOIN ProcessoSeriacao  " +
                            "ON Anuncio.idAnuncio LIKE ProcessoSeriacao.idAnuncio " +
                            "WHERE Anuncio.nifOrganizacao LIKE ?"

            );

            preparedStatement.setString(1, nifOrganizacao);

            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                int idAnuncio = resultSet.getInt(1);

                idsAnunciosSeriados.add(idAnuncio);

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
        return idsAnunciosSeriados;
    }
    
     
}
