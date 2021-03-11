package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.domain.GrauProficiencia;
import com.grupo2.t4j.domain.Tarefa;
import com.grupo2.t4j.persistence.RepositorioGrauProficiencia;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioGrauProficienciaDatabase implements RepositorioGrauProficiencia {

    private static RepositorioGrauProficienciaDatabase repositorioGrauProficienciaDatabase;

    private RepositorioGrauProficienciaDatabase() throws SQLException {

    }

    public static RepositorioGrauProficienciaDatabase getInstance() throws SQLException {
        if (repositorioGrauProficienciaDatabase == null) {
            repositorioGrauProficienciaDatabase = new RepositorioGrauProficienciaDatabase();
        }
        return repositorioGrauProficienciaDatabase;
    }

    @Override
    public void save(int valor, String designacao, String codigoCompetenciaTecnica) {

    }

    @Override
    public boolean save(GrauProficiencia grauProficiencia) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createGrauProficiencia(?, ?, ?) }"
            );

            if (findByGrauECompetencia(grauProficiencia.getGrau(), grauProficiencia.getCodigoCompetenciaTecnica()) == null) {

                connection.setAutoCommit(false);

                callableStatement.setInt(1, grauProficiencia.getGrau());
                callableStatement.setString(2, grauProficiencia.getDesignacao());
                callableStatement.setString(3, grauProficiencia.getCodigoCompetenciaTecnica());

                callableStatement.executeQuery();

                connection.commit();
                return true;
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
            DBConnectionHandler.getInstance().closeAll();
        }
        return false;
    }

    @Override
    public List<GrauProficiencia> getAll() throws SQLException {
        return null;
    }


    public GrauProficiencia findByGrauECompetencia(int grau, String codigoCompetenciaTecnica) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{ CALL findByGrau(?, ?) }"
            );

            connection.setAutoCommit(false);

            callableStatement.setInt(1, grau);
            callableStatement.setString(2, codigoCompetenciaTecnica);

            callableStatement.executeUpdate();
            return null;

        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();


        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return new GrauProficiencia();
    }

    public List<GrauProficiencia> getAll(String codigoCompetenciaTecnica) throws SQLException {
        ArrayList<GrauProficiencia> grausProficiencia = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM GrauProficiencia WHERE codigoCompetenciaTecnica LIKE ?"
            );

            preparedStatement.setString(1, codigoCompetenciaTecnica);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idGrauProficiencia = resultSet.getInt(1);
                int grau = resultSet.getInt(2);
                String designacao = resultSet.getString(3);
                grausProficiencia.add(new GrauProficiencia(idGrauProficiencia, grau, designacao, codigoCompetenciaTecnica));
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
            DBConnectionHandler.getInstance().closeAll();
        }

        return grausProficiencia;

    }

    @Override
    public ArrayList<GrauProficiencia> findByCompetenciaTecnica(String codigoCompetenciaTecnica) throws SQLException {
        ArrayList<GrauProficiencia> grausProficiencia = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM GrauProficiencia WHERE codigoCompetenciaTecnica LIKE ?"
            );

            preparedStatement.setString(1, codigoCompetenciaTecnica);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idGrauProficiencia = resultSet.getInt(1);
                int grau = resultSet.getInt(2);
                String designacao = resultSet.getString(3);

                grausProficiencia.add(new GrauProficiencia(idGrauProficiencia, grau, designacao, codigoCompetenciaTecnica));

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
            DBConnectionHandler.getInstance().closeAll();
        }

        return grausProficiencia;
    }

    @Override
    public GrauProficiencia findByGrau(int grau) throws SQLException {
        return null;
    }

    public List<GrauProficiencia> getAllGrausTarefa(Tarefa tarefa) throws SQLException {

        List<GrauProficiencia> graus = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM GrauProficiencia " +
                            "INNER JOIN CaracterCT " +
                            "ON GrauProficiencia.idGrauProficiencia = CaracterCT.grauProfMinimo " +
                            "INNER JOIN Categoria " +
                            "ON CaracterCT.codigoCategoria LIKE Categoria.codigoCategoria " +
                            "INNER JOIN Tarefa " +
                            "ON Categoria.codigoCategoria LIKE Tarefa.codigoCategoria " +
                            "WHERE Tarefa.codigoCategoria LIKE ?  "
            );

            preparedStatement.setString(1, tarefa.getCodigoCategoriaTarefa());

            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()) {
                int idGrauProficiencia = resultSet.getInt(1);
                int grau = resultSet.getInt(2);
                String designacao = resultSet.getString(3);

                graus.add(new GrauProficiencia(idGrauProficiencia, grau, designacao, tarefa.getCodigoCategoriaTarefa()));
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
            DBConnectionHandler.getInstance().closeAll();
        }
        return graus;
    }

    @Override
    public List<GrauProficiencia> getAllByCompetenciaTecnica(String codigoCompetenciaTecnica) throws SQLException {
        List<GrauProficiencia> grausProficiencia = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "SELECT * FROM GrauProficiencia WHERE codigoCompetenciaTecnica LIKE ?"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, codigoCompetenciaTecnica);
            callableStatement.executeQuery();

            ResultSet resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                int idGrauProficiencia = resultSet.getInt(1);
                int grau = resultSet.getInt(2);
                String designacao = resultSet.getString(3);
                grausProficiencia.add(new GrauProficiencia(idGrauProficiencia, grau, designacao, codigoCompetenciaTecnica));
            }


        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
            finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return grausProficiencia;
    }

    @Override
    public List<GrauProficiencia> getAllGrausFreelancer(String emailFreelancer) throws SQLException {
        List<GrauProficiencia> grausFreelancer = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "SELECT * FROM GrauProficiencia " +
                            "INNER JOIN ReconhecimentoGP " +
                            "ON ReconhecimentoGP.idGrauProficiencia = GrauProficiencia.idGrauProficiencia "  +
                            "INNER JOIN Freelancer " +
                            "ON Freelancer.email LIKE ReconhecimentoGP.emailFreelancer " +
                            "WHERE Freelancer.email LIKE ?"
            );

            connection.setAutoCommit(false);

            callableStatement.setString(1, emailFreelancer);

            callableStatement.executeQuery();

            ResultSet resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                int idGrauProficiencia = resultSet.getInt(1);
                int grau = resultSet.getInt(2);
                String designacao = resultSet.getString(3);
                String codigoCompetenciaTecnica = resultSet.getString(4);
                grausFreelancer.add(new GrauProficiencia(idGrauProficiencia, grau, designacao, codigoCompetenciaTecnica));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return grausFreelancer;
    }

    @Override
    public List<Tarefa> getAllGrausTarefasPublicadas(List<Tarefa> tarefasPublicadas) throws SQLException {
        List<Tarefa> tarefasComGraus = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            for (Tarefa tarefa : tarefasPublicadas) {
                String referencia = tarefa.getReferencia();
                String nifOrganizacao = tarefa.getNifOrganizacao();
                List<GrauProficiencia> grausProficiencia = new ArrayList<>();

                CallableStatement callableStatement = connection.prepareCall(
                        "SELECT * FROM GrauProficiencia " +
                                "INNER JOIN CaracterCT " +
                                "ON CaracterCT.grauProfMinimo = GrauProficiencia.idGrauProficiencia " +
                                "INNER JOIN Categoria " +
                                "ON Categoria.codigoCategoria LIKE CaracterCT.codigoCategoria " +
                                "INNER JOIN Tarefa " +
                                "ON Categoria.codigoCategoria LIKE Tarefa.codigoCategoria " +
                                "INNER JOIN Anuncio " +
                                "ON Tarefa.referencia LIKE Anuncio.referenciaTarefa " +
                                "AND Tarefa.nifOrganizacao LIKE Tarefa.nifOrganizacao " +
                                "WHERE Tarefa.referencia LIKE ? " +
                                "AND Tarefa.nifOrganizacao LIKE ?"
                );

                connection.setAutoCommit(false);

                callableStatement.setString(1, referencia);
                callableStatement.setString(2, nifOrganizacao);

                callableStatement.executeQuery();

                ResultSet resultSet = callableStatement.getResultSet();

                while (resultSet.next()) {
                    int idGrauProficiencia = resultSet.getInt(1);
                    int grau = resultSet.getInt(2);
                    String designacao = resultSet.getString(3);
                    String codigoCompetenciaTecnica = resultSet.getString(4);
                    grausProficiencia.add(new GrauProficiencia(idGrauProficiencia, grau, designacao, codigoCompetenciaTecnica));
                }
                tarefasComGraus.add(new Tarefa(referencia, nifOrganizacao, grausProficiencia));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return tarefasComGraus;
    }


}
