package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.CaracterizacaoCTDuplicadaException;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.model.Obrigatoriedade;
import com.grupo2.t4j.persistence.RepositorioCaracterizacaoCT;
import com.grupo2.t4j.utils.DBConnectionHandler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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
    public void save(String codigoCCT, String codigoGP, Obrigatoriedade obrigatoriedade, String codigoCompetenciaTecnica) throws CaracterizacaoCTDuplicadaException {

    }

    @Override
    public boolean save(CaracterizacaoCT caracterizacaoCT) throws SQLException {
        return false;
     /*   DBConnectionHandler dbConnectionHandler = new DBConnectionHandler(jdbcUrl, username, password);
        Connection connection = dbConnectionHandler.openConnection();

        CallableStatement callableStatement = connection.prepareCall(
                "{CALL createCaracterizacaoCT()}"
        );*/
    }

    @Override
    public List<CaracterizacaoCT> getAll() {
        return null;
    }

    @Override
    public List<CaracterizacaoCT> findByCompetenciaTecnica(List<String> codigoCompetenciasTecnicas) {
        return null;
    }

    @Override
    public CaracterizacaoCT findByCodigo(String codigoCCT) {
        return null;
    }
}
