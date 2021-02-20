package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.model.EnderecoPostal;
import com.grupo2.t4j.persistence.RepositorioEnderecoPostal;

import java.sql.SQLException;
import java.util.List;

public class RepositorioEnderecoPostalDatabase implements RepositorioEnderecoPostal {

    private static RepositorioEnderecoPostalDatabase repositorioEnderecoPostalDatabase;
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_04";
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
    public boolean save(EnderecoPostal enderecoPostal) {
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
