package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.domain.CaracterizacaoCT;
import com.grupo2.t4j.domain.Obrigatoriedade;
import com.grupo2.t4j.exception.CaracterizacaoCTDuplicadaException;
import com.grupo2.t4j.persistence.RepositorioCaracterizacaoCT;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCaracterizacaoCTDatabase implements RepositorioCaracterizacaoCT {

    /**
     * Define uma instância estática do Repositório em que estão
     * registadas todas as Caracterizações de Competências Técnicas
     */
    private static RepositorioCaracterizacaoCTDatabase repositorioCaracterizacaoCTDatabase;

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

    /**
     * Regista a caracterizacao de uma competencia tecnica
     * @param caracterizacaoCT
     * @return
     * @throws SQLException 
     */
    @Override
    public boolean save(CaracterizacaoCT caracterizacaoCT) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                "{CALL createCaracterCT(?, ?, ?)}"
            );
            if (findByCategoriaEGrau(caracterizacaoCT.getCodigoCategoria(), caracterizacaoCT.getCodigoGP()) == null){

                connection.setAutoCommit(false);

                callableStatement.setString(3, caracterizacaoCT.getCodigoCategoria());
                callableStatement.setInt(2, caracterizacaoCT.getCodigoGP());
                callableStatement.setString(1, caracterizacaoCT.getObrigatoriedade().toString());

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
    public List<CaracterizacaoCT> getAll() {
        return null;
    }

    @Override
    public CaracterizacaoCT findByCodigo(int codigoCCT) {
        return null;
    }

    /**
     * Devolve a caracteizacao de uma competencia tecnica a partir do codigo de categoria 
     * e do codigo do grau de proficiencia
     * @param codigoCategoria
     * @param codigoGP
     * @return
     * @throws SQLException 
     */
    @Override
    public CaracterizacaoCT findByCategoriaEGrau(String codigoCategoria,
                                                 int codigoGP) throws SQLException {

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "{ CALL findByCodigoCategoriaECodigoGrau(?, ?) }"
            );

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
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }
        return new CaracterizacaoCT();
    }

    /**
     * Devolve todas as competencias tecnicas caracterizadas de uma categoria
     * @param codigoCategoria
     * @return
     * @throws SQLException 
     */
    @Override
    public List<CaracterizacaoCT> getAllByCategoria(String codigoCategoria) throws SQLException {
        List<CaracterizacaoCT> caracterizacoesCT = new ArrayList<>();

        Connection connection = DBConnectionHandler.getInstance().openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall(
                    "SELECT * FROM CaracterCT " +
                            "INNER JOIN GrauProficiencia " +
                            "ON GrauProficiencia.idGrauProficiencia = CaracterCT.grauProfMinimo " +
                            "INNER JOIN " +
                            "CompetenciaTecnica ON " +
                            "GrauProficiencia.codigoCompetenciaTecnica LIKE CompetenciaTecnica.codigoCompetenciaTecnica " +
                            "WHERE codigoCategoria LIKE ? "
            );

            callableStatement.setString(1, codigoCategoria);
            callableStatement.executeQuery();

            ResultSet resultSet = callableStatement.getResultSet();

            while(resultSet.next()) {
                String obrigatoria = resultSet.getString(2);
                String designacao = resultSet.getString(7);
                String descBreve = resultSet.getString(10);
                caracterizacoesCT.add(new CaracterizacaoCT(obrigatoria, designacao, descBreve));
            }

        }
        catch (SQLException exception) {
            exception.printStackTrace();
            exception.getSQLState();

        }
        finally {
            DBConnectionHandler.getInstance().closeAll();
        }

        return caracterizacoesCT;
    }
}
