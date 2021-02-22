package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.model.EnderecoPostal;
import com.grupo2.t4j.persistence.RepositorioEnderecoPostal;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RepositorioEnderecoPostalDatabase implements RepositorioEnderecoPostal {

    private static RepositorioEnderecoPostalDatabase repositorioEnderecoPostalDatabase;
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";

    private RepositorioEnderecoPostalDatabase() throws SQLException{}

    public static RepositorioEnderecoPostalDatabase getInstance() throws SQLException {
        if (repositorioEnderecoPostalDatabase == null) {
            repositorioEnderecoPostalDatabase = new RepositorioEnderecoPostalDatabase();
        }
        return repositorioEnderecoPostalDatabase;
    }

    @Override
    public void save(String codigoEnderecoPostal, String arruamento, String numeroPorta, String localidade, String codigoPostal) {

    }

    @Override
    public boolean save(EnderecoPostal enderecoPostal) throws SQLException {

       /* DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL EnderecoPostal(?, ?, ?, ?)}"
        );*/

        return false;
    }

    @Override
    public List<EnderecoPostal> getAll() {
        return null;
    }

    @Override
    public EnderecoPostal findByCodigo(String codigo) {
        return null;
    }
}
