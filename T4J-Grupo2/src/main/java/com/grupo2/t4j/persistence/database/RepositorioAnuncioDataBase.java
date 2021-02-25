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
import com.grupo2.t4j.model.HabilitacaoAcademica;
import com.grupo2.t4j.model.ReconhecimentoGP;
import com.grupo2.t4j.model.TipoRegimento;
import com.grupo2.t4j.model.TipoStatusAnuncio;
import com.grupo2.t4j.persistence.RepositorioAnuncio;
import com.grupo2.t4j.utils.DBConnectionHandler;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RepositorioAnuncioDataBase implements RepositorioAnuncio {

    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os Anúncios
     */
    private static RepositorioAnuncioDataBase repositorioAnuncioDataBase;
    
    /**
     * A data atual no formato da classe Data
     */
    private Calendar cal = Calendar.getInstance();
    private Data hoje = new Data(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH));
    
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";

    /**
     * Inicializa o Repositório de Anúncios
     */
    private RepositorioAnuncioDataBase(){    }

    /**
     * Devolve uma instância estática do Repositório de Anúncios
     *
     * @return RepositorioAnuncioDatabase
     */
    public static RepositorioAnuncioDataBase getInstance(){
        if(repositorioAnuncioDataBase == null) {
            repositorioAnuncioDataBase = new RepositorioAnuncioDataBase();
        }
        return repositorioAnuncioDataBase;
    }
    
    @Override
    public void save(String idAnuncio, String idTarefa, Data dtInicioPublicitacao, Data dtFimPublicitacao, Data
             dtInicioCandidatura, Data dtFimCandidatura, Data dtInicioSeriacao,
                      Data dtFimSeriacao) throws AnuncioDuplicadoException {

    }
    
    @Override
    public boolean save(Anuncio anuncio) throws SQLException {
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createAnuncio(?,?,?,?,?,?,?,?,?)}");

        //if (findByCodigo(anuncio.getIdAnuncio()) == null) {
            try {
                connection.setAutoCommit(false);

                callableStatement.setString(1, anuncio.getDtInicioPub().toString());
                callableStatement.setString(2, anuncio.getDtFimPub().toString());
                callableStatement.setString(3, anuncio.getDtInicioCand().toString());
                callableStatement.setString(4, anuncio.getDtFimCand().toString());
                callableStatement.setString(5, anuncio.getDtInicioSeriacao().toString());
                callableStatement.setString(6, anuncio.getDtFimSeriacao().toString());
                callableStatement.setString(7, anuncio.getReferenciaTarefa());
                callableStatement.setString(8, anuncio.getNifOrganizacao());
                callableStatement.setString(9, anuncio.getIdTipoRegimento());

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
    
    @Override
    public ArrayList<Anuncio> getAllByStatus(TipoStatusAnuncio status) throws SQLException {
        ArrayList<Anuncio> anuncios = new ArrayList<>();

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
        return anunciosStatus;
        
        //return null;
    }


    @Override
    public Anuncio findAnuncioByIdTarefa(String referenciaTarefa, String nifOrganizacao) throws SQLException{
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{ CALL findAnuncioByTarefa(?, ?) }"
        );

        try {
            connection.setAutoCommit(false);

            callableStatement.setString(1, referenciaTarefa);
            callableStatement.setString(2, nifOrganizacao );

            callableStatement.executeUpdate();
            return null;

        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();


        }
        return new Anuncio();
    }
    
    @Override
    public ArrayList<TipoRegimento> getAllRegimento()throws SQLException {

        ArrayList<TipoRegimento> tiposRegimento = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM TipoRegimento"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idTipoRegimento = resultSet.getString(1);
                String designacao = resultSet.getString(2);
                String descricaoRegras = resultSet.getString(3);
                tiposRegimento.add(new TipoRegimento(idTipoRegimento, designacao,
                        descricaoRegras));
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
        return tiposRegimento;
    }
    
}
