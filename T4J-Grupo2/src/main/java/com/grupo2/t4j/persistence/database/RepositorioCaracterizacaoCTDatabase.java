package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.CaracterizacaoCTDuplicadaException;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.Obrigatoriedade;
import com.grupo2.t4j.persistence.RepositorioCaracterizacaoCT;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCaracterizacaoCTDatabase implements RepositorioCaracterizacaoCT {

    /**
     * Define uma instância estática do Repositório em que estão
     * registadas todas as Caracterizações de Competências Técnicas
     */
    private static RepositorioCaracterizacaoCTDatabase repositorioCaracterizacaoCTDatabase;

    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_01";
    String password = "qwerty";

    /**
     * Inicializa o Repositório de Caracterizações de Competências Técnicas
     */
    private RepositorioCaracterizacaoCTDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Caracterizações de Competências Técnicas
     *
     * @return RepositorioCaracterizacaoCTDatabase
     */
    public static RepositorioCaracterizacaoCTDatabase getInstance(){
        if(repositorioCaracterizacaoCTDatabase == null) {
            repositorioCaracterizacaoCTDatabase = new RepositorioCaracterizacaoCTDatabase();
        }
        return repositorioCaracterizacaoCTDatabase;
    }

    @Override
    public void save(String codigoCategoria, int codigoGP, Obrigatoriedade obrigatoriedade) throws CaracterizacaoCTDuplicadaException {

    }

    @Override
    public boolean save(CaracterizacaoCT caracterizacaoCT) throws SQLException {
       
        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createCaracterCT(?, ?, ?)}"
        );
        if (findByCategoriaEGrau(caracterizacaoCT.getCodigoCategoria(), caracterizacaoCT.getCodigoGP()) == null){
            try {
                connection.setAutoCommit(false);

                callableStatement.setString(1, caracterizacaoCT.getCodigoCategoria());
                callableStatement.setInt(2, caracterizacaoCT.getCodigoGP());
                callableStatement.setString(3, caracterizacaoCT.getObrigatoriedade().toString());

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
        }

        return false;
        
    }

    @Override
    public List<CaracterizacaoCT> getAll() {
        return null;
    }

    @Override
    public List<CaracterizacaoCT> findByCategoria(String codigoCategoria) throws SQLException{
        ArrayList<CaracterizacaoCT> listaCaracterizacaoCT = new ArrayList<>();

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM CaracterizacaoCT WHERE codigoCategoria LIKE ?"
            );

            preparedStatement.setString(1, codigoCategoria);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCaracterizacaoCT = resultSet.getInt(1);
                String codigoCategoriaTarefa = resultSet.getString(2);
                int codigoGP = resultSet.getInt(3);
                String obrigatoriedade = resultSet.getString(4);
                listaCaracterizacaoCT.add(new CaracterizacaoCT(idCaracterizacaoCT,
                        codigoCategoriaTarefa,
                        codigoGP,
                        Obrigatoriedade.valueOf(obrigatoriedade)));

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

        return listaCaracterizacaoCT;
    }

    @Override
    public CaracterizacaoCT findByCodigo(int codigoCCT) {
        return null;
    }

    @Override
    public CaracterizacaoCT findByCategoriaEGrau(String codigoCategoria,
                                                 int codigoGP) throws SQLException {

        DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{ CALL findByCodigoCategoriaECodigoGrau(?, ?) }"
        );

        try {
            connection.setAutoCommit(false);

            callableStatement.setString(1, codigoCategoria);
            callableStatement.setInt(2, codigoGP);

            callableStatement.executeUpdate();
            return null;

        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();


        }
        return new CaracterizacaoCT();
    }
}
