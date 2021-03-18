package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.domain.Candidatura;
import com.grupo2.t4j.exception.CandidaturaDuplicadaException;
import com.grupo2.t4j.persistence.RepositorioCandidatura;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioCandidaturaDatabase implements RepositorioCandidatura{

    /**
     * Define uma instância estática do Repositório em que estão registados
     * todas as Candidaturas
     */
    private static RepositorioCandidaturaDatabase repositorioCandidaturaDatabase;

    /**
     * Inicializa o Repositório de Candidaturas
     */
    private RepositorioCandidaturaDatabase() {
    }

    /**
     * Devolve uma instância estática do Repositório dos Candidaturas
     *
     * @return RepositorioCandidaturaDatabase
     */
    public static RepositorioCandidaturaDatabase getInstance() {
        if (repositorioCandidaturaDatabase == null) {
            repositorioCandidaturaDatabase = new RepositorioCandidaturaDatabase();
        }
        return repositorioCandidaturaDatabase;
    }

    /**
     * Guarda uma candidatura na base de dados
     * @param valorPretendido
     * @param numeroDias
     * @param txtApresentacao
     * @param txtMotivacao
     * @param idAnuncio
     * @param emailFreelancer
     * @return
     * @throws CandidaturaDuplicadaException
     * @throws SQLException 
     */
    @Override
    public boolean save(double valorPretendido, int numeroDias,
            String txtApresentacao, String txtMotivacao, int idAnuncio, String emailFreelancer) throws CandidaturaDuplicadaException, SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createCandidatura(?, ?, ?, ?, ?, ?) } ");

            connection.setAutoCommit(false);

            callableStatement.setDouble(1, valorPretendido);
            callableStatement.setInt(2, numeroDias);
            callableStatement.setString(3, txtApresentacao);
            callableStatement.setString(4, txtMotivacao);
            callableStatement.setInt(5, idAnuncio);
            callableStatement.setString(6, emailFreelancer);

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
            DBConnectionHandler.getInstance().closeAll();
        }
        return false;
    }

    /**
     * Guarda uma candidatura na base de dados
     * @param candidatura
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean save(Candidatura candidatura) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL createCandidatura(?, ?, ?, ?, ?, ?) } ");
            connection.setAutoCommit(false);

            callableStatement.setDouble(1, candidatura.getValorPretendido());
            callableStatement.setInt(2, candidatura.getNumeroDias());
            callableStatement.setString(3, candidatura.getApresentacao());
            callableStatement.setString(4, candidatura.getMotivacao());
            callableStatement.setInt(5, candidatura.getIdAnuncio());
            callableStatement.setString(6, candidatura.getEmailFreelancer());

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
            DBConnectionHandler.getInstance().closeAll();
        }

        return false;

    }

    /**
     * Devolve uma candidatura a partir do seu id
     * @param idCandidatura
     * @return
     * @throws SQLException 
     */
    @Override
    public Candidatura findById(int idCandidatura) throws SQLException {


        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL findCandidaturaById(?)}");

            connection.setAutoCommit(false);

            callableStatement.setInt(1, idCandidatura);
            callableStatement.executeQuery();

            return new Candidatura();

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();
        } finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return null;

    }

    /**
     * Devolve a lista de candidaturas de um freelancer
     * @param emailFreelancer
     * @return
     * @throws SQLException 
     */
    @Override
    public ArrayList<Candidatura> findByEmail(String emailFreelancer) throws SQLException {
        ArrayList<Candidatura> candidaturasFreelancer = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Candidatura "
                    + "WHERE emailFreelancer LIKE ?"
            );

            preparedStatement.setString(1, emailFreelancer);
            ResultSet resultSet = preparedStatement.executeQuery();

            
            while (resultSet.next()) {

                int idCandidatura = resultSet.getInt(1);
                double valorPretendido = resultSet.getDouble(2);
                int numeroDias = resultSet.getInt(3);
                String txtApresentacao = resultSet.getString(4);
                String txtMotivacao = resultSet.getString(5);
                int idAnuncio = resultSet.getInt(6);
                String dataCandidatura = resultSet.getDate(8).toString();
                String dataEdicaoCandidatura = resultSet.getDate(9).toString();

                candidaturasFreelancer.add(new Candidatura(idCandidatura, valorPretendido,
                        numeroDias, txtApresentacao, txtMotivacao, idAnuncio, emailFreelancer,
                        dataCandidatura, dataEdicaoCandidatura));
            }

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();
        } finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return candidaturasFreelancer;
    }

    /**
     * Devolve todas as candidaturas registadas na base de dados
     * @return
     * @throws SQLException 
     */
    @Override
    public ArrayList<Candidatura> getAll() throws SQLException {

        ArrayList<Candidatura> candidaturas = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Candidatura"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCandidatura = resultSet.getInt(1);
                double valorPretendido = resultSet.getDouble(2);
                int numeroDias = resultSet.getInt(3);
                String txtApresentacao = resultSet.getString(4);
                String txtMotivacao = resultSet.getString(5);
                int idAnuncio = resultSet.getInt(6);
                String emailFreelancer = resultSet.getString(7);
                String dataCandidatura = resultSet.getDate(8).toString();
                String dataEdicaoCandidatura = resultSet.getDate(9).toString();

                candidaturas.add(new Candidatura(idCandidatura, valorPretendido,
                        numeroDias, txtApresentacao, txtMotivacao, idAnuncio, emailFreelancer,
                        dataCandidatura, dataEdicaoCandidatura));
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
        return candidaturas;
    }

    /**
     * Devolve uma lista de candidaturas a um anuncio
     * @param idAnuncio
     * @return
     * @throws SQLException 
     */
    @Override
    public List<Candidatura> getAllByIdAnuncio(int idAnuncio) throws SQLException {

        List<Candidatura> candidaturasAnuncio = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Candidatura "
                    + "WHERE idAnuncio =  ?"
            );

            preparedStatement.setInt(1, idAnuncio);

            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                int idCandidatura = resultSet.getInt(1);
                double valorPretendido = resultSet.getDouble(2);
                int numeroDias = resultSet.getInt(3);
                String txtApresentacao = resultSet.getString(4);
                String txtMotivacao = resultSet.getString(5);
                String emailFreelancer = resultSet.getString(7);
                String dataCandidatura = resultSet.getDate(8).toString();
                String dataEdicaoCandidatura = resultSet.getDate(9).toString();

                candidaturasAnuncio.add(new Candidatura(idCandidatura, valorPretendido,
                        numeroDias, txtApresentacao, txtMotivacao, idAnuncio, emailFreelancer,
                        dataCandidatura, dataEdicaoCandidatura));
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
        return candidaturasAnuncio;
    }

    /**
     * Atualiza os dados de uma candidatura
     * @param idCandidatura
     * @param valorPretendido
     * @param numeroDias
     * @param txtApresentacao
     * @param txtMotivacao
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean updateCandidatura(int idCandidatura, double valorPretendido,
            int numeroDias, String txtApresentacao, String txtMotivacao) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(

                    "UPDATE Candidatura "
                            + "SET valorPretendido = ?, "
                            + "numeroDias = ?, "
                            + "txtApresentacao = ?, "
                            + "txtMotivacao = ?, "
                            + "dataEdicaoCandidatura = sysdate "
                    + "WHERE idCandidatura = ? "
            );

            preparedStatement.setDouble(1, valorPretendido);
            preparedStatement.setInt(2, numeroDias);
            preparedStatement.setString(3, txtApresentacao);
            preparedStatement.setString(4, txtMotivacao);

            preparedStatement.setInt(5, idCandidatura);
            
            ResultSet resultSet = preparedStatement.executeQuery();

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
            DBConnectionHandler.getInstance().closeAll();
        }

        return false;
    }

    /**
     * Apaga uma candidatura
     * @param idCandidatura
     * @return
     * @throws SQLException 
     */
    public boolean deleteCandidatura(int idCandidatura) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{ CALL deleteCandidatura (?) }"
            );

            connection.setAutoCommit(false );

            callableStatement.setInt(1, idCandidatura);
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
            DBConnectionHandler.getInstance().closeAll();
        }
        return false;
    }

    /**
     * Devolve uma lista de todas as candidaturas editaveis
     * @param emailFreelancer
     * @return
     * @throws SQLException 
     */
    @Override
    public List<Integer> getAllCandidaturasEditaveis(String emailFreelancer) throws SQLException {
        ArrayList<Integer> candidaturasEditaveis = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT idCandidatura FROM Candidatura "
                    + "INNER JOIN Anuncio "
                    + "ON Candidatura.idAnuncio = Anuncio.idAnuncio "
                    + "WHERE sysdate BETWEEN Anuncio.dataInicioCandidatura AND anuncio.datafimcandidatura "
                    + "AND Candidatura.emailFreelancer LIKE ? "
            );

            preparedStatement.setString(1, emailFreelancer);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int idCandidatura = resultSet.getInt(1);

                candidaturasEditaveis.add(idCandidatura);
            }

        } catch (SQLException exceptionOrg) {
            exceptionOrg.printStackTrace();
            exceptionOrg.getSQLState();
        } finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return candidaturasEditaveis;
    }
    
    /**
     * Ordena as candidaturas por Valor pretendido e depois o número de Dias como
     * critério de desempate
     * @param candidaturas
     * @return
     * @throws SQLException 
     */
    @Override
    public List<Candidatura> ordenarByValor(List<Candidatura> candidaturas) throws SQLException{
               
        Collections.sort(candidaturas, Candidatura.CandidaturaComparator.VALOR_NUMDIAS);
                
        return candidaturas;
    }
    
    /**
     * Ordena as candidaturas pelo Id da Candidatura
     * @param candidaturas
     * @return
     * @throws SQLException 
     */
    @Override
    public List<Candidatura> ordenarByIdCandidatura(List<Candidatura> candidaturas) throws SQLException{
               
        Collections.sort(candidaturas, Candidatura.CandidaturaComparator.IDCANDIDATURA);
            
        return candidaturas;
    }
  
    
}
