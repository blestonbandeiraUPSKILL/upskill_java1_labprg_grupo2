/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.database;

/**
 *
 * @author CAD
 */
import com.grupo2.t4j.exception.AnuncioDuplicadoException;
import com.grupo2.t4j.model.Anuncio;
import com.grupo2.t4j.model.Data;
import com.grupo2.t4j.model.TipoRegimento;
import com.grupo2.t4j.model.TipoStatusAnuncio;
import com.grupo2.t4j.persistence.RepositorioAnuncio;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RepositorioAnuncioDataBase implements RepositorioAnuncio {

    /**
     * Define uma instância estática do Repositório em que estão registados
     * todos os Anúncios
     */
    private static RepositorioAnuncioDataBase repositorioAnuncioDataBase;

    /**
     * A data atual no formato da classe Data
     */
    private Calendar cal = Calendar.getInstance();
    private Data hoje = new Data(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH));

    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";

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

    @Override
    public boolean save(String referenciaTarefa, String nifOrganizacao, String dtInicioPublicitacao, String dtFimPublicitacao, String dtInicioCandidatura, String dtFimCandidatura, String dtInicioSeriacao,
            String dtFimSeriacao, int idTipoRegimento) throws AnuncioDuplicadoException, SQLException {

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createAnuncio(?, ?, ?, ?, ?, ?, ?, ?, ?) }");

        try {
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
            dbConnectionHandler.closeAll();
        }

        return false;

    }

    @Override
    public boolean save(Anuncio anuncio) throws SQLException {
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createAnuncio(?, ?, ?, ?, ?, ?, ?, ?, ?)}");

        try {
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

        return false;
    }

    @Override
    public Anuncio findById(String idAnuncio) {
        return null;
    }

    /*@Override
    public ArrayList<Anuncio> getAllByStatus(TipoStatusAnuncio status) throws SQLException{
        
        
        return null;
    }*/

    @Override
    public ArrayList<Anuncio> getAll() {
        return null;
    }

    public ArrayList<Anuncio> getAllByStatus(TipoStatusAnuncio status) throws SQLException {
        /*ArrayList<Anuncio> anuncios = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Anuncio"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String dtInicioPublicitacao = resultSet.getString(1);
                String dtFimPublicitacao = resultSet.getString(2);
                String dtInicioCandidatura = resultSet.getString(3);
                String dtFimCandidatura = resultSet.getString(4);
                String dtInicioSeriacao = resultSet.getString(5);
                String dtFimSeriacao = resultSet.getString(6);
                String referenciaTarefa = resultSet.getString(7);
                String nifOrganizacao = resultSet.getString(8);
                String idTipoRegimento = resultSet.getString(9);
                
                anuncios.add(new Anuncio(referenciaTarefa, nifOrganizacao, new Data(dtInicioPublicitacao), 
                    new Data(dtFimPublicitacao),new Data(dtInicioCandidatura), new Data(dtFimCandidatura), 
                        new Data(dtInicioSeriacao), new Data(dtFimSeriacao), idTipoRegimento));
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
        
        ArrayList<Anuncio> anunciosStatus= new ArrayList<>();
        for(int i = 0; i < anuncios.size();i++){
            Anuncio anuncio = anuncios.get(i);
            if(anuncio.getStatusAnuncio(hoje).equals(status)){
                anunciosStatus.add(anuncio);
            }
        }
        return anunciosStatus;*/

        return null;
    }

    @Override
    public Anuncio findAnuncioByIdTarefa(String referenciaTarefa, String nifOrganizacao) throws SQLException {
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{ CALL findAnuncioByTarefa(?, ?) }"
        );

        try {
            connection.setAutoCommit(false);

            callableStatement.setString(1, referenciaTarefa);
            callableStatement.setString(2, nifOrganizacao);

            callableStatement.executeUpdate();
            return null;

        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        return new Anuncio();
    }

    @Override
    public ArrayList<TipoRegimento> getAllRegimento() throws SQLException {

        ArrayList<TipoRegimento> tiposRegimento = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

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
            dbConnectionHandler.closeAll();
        }
        return tiposRegimento;
    }

    @Override
    public List<Anuncio> findAnunciosElegiveis(String email) throws SQLException {
        ArrayList<Anuncio> anunciosElegiveis = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Anuncio "
                            + "INNER JOIN Tarefa "
                            + "ON Anuncio.referenciaTarefa = Tarefa.referencia "
                            + "AND Anuncio.nifOrganizacao = Tarefa.nifOrganizacao "
                            + "INNER JOIN Categoria "
                            + "ON Tarefa.codigoCategoria = Categoria.codigoCategoria "
                            + "INNER JOIN CaracterCT "
                            + "ON categoria.codigocategoria = CaracterCT.codigoCategoria "
                            + "INNER JOIN GrauProficiencia "
                            + "ON CaracterCT.grauProfMinimo = GrauProficiencia.idGrauProficiencia "
                            + "INNER JOIN ReconhecimentoGP "
                            + "ON GrauProficiencia.idGrauProficiencia = ReconhecimentoGP.idGrauProficiencia "
                            + "INNER JOIN Freelancer "
                            + "ON ReconhecimentoGP.emailFreelancer = Freelancer.email "
                            + "WHERE Freelancer.email LIKE ? " +
                            "AND CaracterCT.obrigatoria = 'obrigatoria'"
                           
            );
            
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idAnuncio = resultSet.getInt(1);
                String referenciaTarefa = resultSet.getString(2);
                String nifOrganizacao = resultSet.getString(3);
                String dtInicioPublicitacao = resultSet.getString(4);
                String dtFimPublicitacao = resultSet.getString(5);
                String dtInicioCandidatura = resultSet.getString(6);
                String dtFimCandidatura = resultSet.getString(7);
                String dtInicioSeriacao = resultSet.getString(8);
                String dtFimSeriacao = resultSet.getString(9);
                int idTipoRegimento = resultSet.getInt(10);
                anunciosElegiveis.add(new Anuncio(idAnuncio,referenciaTarefa, nifOrganizacao,
                        dtInicioPublicitacao, dtFimPublicitacao, dtInicioCandidatura,
                dtFimCandidatura, dtInicioSeriacao, dtFimSeriacao, idTipoRegimento));
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
            dbConnectionHandler.closeAll();
        }
        return anunciosElegiveis;
    }

    @Override
    public Anuncio getAnuncio(int idAnuncio) throws SQLException {
        Anuncio anuncio = new Anuncio();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

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
            dbConnectionHandler.closeAll();
        }
        return anuncio;
    }
}
