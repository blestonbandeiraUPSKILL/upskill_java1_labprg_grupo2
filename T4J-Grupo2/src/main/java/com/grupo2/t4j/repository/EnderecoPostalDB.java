package com.grupo2.t4j.repository;

import com.grupo2.t4j.model.DBConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnderecoPostalDB {

    private DBConnectionHandler dbConnectionHandler;
    private PreparedStatement preparedStatement;
    private Connection connection;

    //INSERT
    public void insertEnderecoPostal(String idEnderecoPostal, String arruamento, String numeroPorta,
                                     String localidade, String codPostal) throws SQLException {

        preparedStatement = connection.prepareStatement(
                "INSERT INTO EnderecoPostal" +
                        "(idEnderecoPostal, arruamento, numeroPorta, localidade, codPostal)" +
                        "VALUES (?, ?, ?, ?, ?)");

        preparedStatement.setString(1, idEnderecoPostal);
        preparedStatement.setString(2, arruamento);
        preparedStatement.setString(3, numeroPorta);
        preparedStatement.setString(4, localidade);
        preparedStatement.setString(5, codPostal);

        preparedStatement.execute();

    }

    //UPDATE

    //DELETE
}
