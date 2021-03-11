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

import com.grupo2.t4j.domain.*;
import com.grupo2.t4j.persistence.RepositorioTipoRegimento;
import com.grupo2.t4j.persistence.RepositorioAnuncio;
import com.grupo2.t4j.utils.DBConnectionHandler;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTipoRegimentoDatabase implements RepositorioTipoRegimento {

    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os Tipos de Regimento para Anúncios
     */
    private static RepositorioTipoRegimentoDatabase repositorioTipoRegimentoDatabase;
    
    /**
     * Inicializa o Repositório de Tipos de Regimento
     */
    private RepositorioTipoRegimentoDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório dos Tipos de Regimento
     *
     * @return RepositorioTipoRegimentoDatabase
     */
    public static RepositorioTipoRegimentoDatabase getInstance(){
        if(repositorioTipoRegimentoDatabase == null) {
            repositorioTipoRegimentoDatabase = new RepositorioTipoRegimentoDatabase();
        }
        return repositorioTipoRegimentoDatabase;
    }
    
    @Override
    public boolean save(int idTipoRegimento, String designacao, String descricaoRegras) throws SQLException{
        return false;
    }
    
    @Override
    public boolean save(TipoRegimento tipoRegimento) throws SQLException{
        return false;
    }

    @Override
    public TipoRegimento findById(int idTipoRegimento) throws SQLException{
        
        TipoRegimento tipoRegimento = new TipoRegimento();
        
        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT idTipoRegimento, designacao, descricaoRegras " +
                            " FROM TipoRegimento " +
                            "WHERE idTipoRegimento = ?"
            );

            preparedStatement.setInt(1, idTipoRegimento);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tipoRegimento.setDesignacao(resultSet.getString(2));
                tipoRegimento.setDescricaoRegras(resultSet.getString(3));                
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
        return tipoRegimento;
    }

    @Override
    public List<TipoRegimento> getAll()throws SQLException{
        return null;
    }
}
