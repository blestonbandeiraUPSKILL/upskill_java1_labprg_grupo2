package com.grupo2.t4j.repository;

import com.grupo2.t4j.model.DBConnectionHandler;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.EnderecoPostal;
import com.grupo2.t4j.model.Website;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrganizacaoDB {

    private DBConnectionHandler dbConnectionHandler;
    private PreparedStatement preparedStatement;
    private Connection connection;

    //INSERT
    public void insertOrganizacao(String nif, String nome, Website website, String telefone, Email email,
                                  Email emailGestor, String arruamento, String numeroPorta,
                                  String localidade, String codPostal) throws SQLException {

        EnderecoPostal enderecoPostal = new EnderecoPostal(arruamento, numeroPorta, localidade, codPostal);
        String idEnderecoPostal = enderecoPostal.getIdEnderecoPostal();

        insertEnderecoPostal(String arruamento, String numeroPorta, String localidade, String codPostal);


        preparedStatement = connection.prepareStatement(
                "INSERT INTO Organizacao" +
                        "(nif, nome, website, telefone, email, emailGestor, idEnderecoPostal)" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)");

        preparedStatement.setString(1, nif);
        preparedStatement.setString(2, nome);
        preparedStatement.setString(3, website.getWebsiteText());
        preparedStatement.setString(4, telefone);
        preparedStatement.setString(5, email.getEmailText());
        preparedStatement.setString(6, emailGestor.getEmailText());
        preparedStatement.setString(7, idEnderecoPostal);

        preparedStatement.execute();
        preparedStatement.close();

    }

    //UPDATE

    //DELETE
}
