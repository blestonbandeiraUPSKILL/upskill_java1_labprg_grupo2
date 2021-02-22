package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.ColaboradorDuplicadoException;
import com.grupo2.t4j.model.Colaborador;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Password;
import com.grupo2.t4j.model.Rolename;
import com.grupo2.t4j.persistence.RepositorioColaborador;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioColaboradorDatabase implements RepositorioColaborador {

    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os Colaboradores
     */
    private static RepositorioColaboradorDatabase repositorioColaboradorDatabase;

    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";

    /**
     * Inicializa o Repositório de Colaboradores
     */
    private RepositorioColaboradorDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Colaboradores
     *
     * @return RepositorioColaboradorDatabase
     */
    public static RepositorioColaboradorDatabase getInstance(){
        if(repositorioColaboradorDatabase == null) {
            repositorioColaboradorDatabase = new RepositorioColaboradorDatabase();
        }
        return repositorioColaboradorDatabase;
    }

    @Override
    public void save(Email email, String nome, Password passowrd, String funcao, String telefone) throws ColaboradorDuplicadoException {

    }

    @Override
    public boolean save(Colaborador colaborador) {
        return false;
    }

    @Override
    public Colaborador findByEmail(String emailCol) {
        return null;
    }

    @Override
    public ArrayList<Colaborador> getAll() throws SQLException {

        ArrayList<Colaborador> colaboradores = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Colaborador"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String email = resultSet.getString(1);
                String funcao = resultSet.getString(2);
                String telefone = resultSet.getString(3);
                String nifOrganizacao = resultSet.getString(4);
                colaboradores.add(new Colaborador(new Email(email), funcao, telefone, nifOrganizacao));
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
        return colaboradores;
    }
}
