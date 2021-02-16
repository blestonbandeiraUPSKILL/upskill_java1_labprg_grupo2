package com.grupo2.t4j.model;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class DBConnectionHandler {

    private String jdbcUrl;
    private String username;
    private String password;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public DBConnectionHandler(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;

        connection = null;
        preparedStatement = null;
        resultSet = null;
        statement = null;
    }

    public Connection openConnection() throws SQLException {
        try {
            String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";

            String username = "UPSKILL_BD_TURMA1_04";
            String password = "qwerty";


            OracleDataSource ds = new OracleDataSource();
            ds.setURL(jdbcUrl);
            connection = ds.getConnection(username, password);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getMessage();
        }
        return connection;
    }

    public String closeAll() {

        StringBuilder message = new StringBuilder("");

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            resultSet = null;
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            preparedStatement = null;
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            statement = null;
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                message.append(ex.getMessage());
                message.append("\n");
            }
            connection = null;
        }
        return message.toString();
    }
}
