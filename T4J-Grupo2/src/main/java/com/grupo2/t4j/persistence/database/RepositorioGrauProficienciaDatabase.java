package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.persistence.RepositorioGrauProficiencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioGrauProficienciaDatabase implements RepositorioGrauProficiencia {

    private static RepositorioGrauProficienciaDatabase repositorioGrauProficienciaDatabase;
    String jdbcUrl = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    String username = "UPSKILL_BD_TURMA1_04";
    String password = "qwerty";

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
    public boolean save(GrauProficiencia grauProficiencia) {
        return false;
    }

    @Override
    public List<GrauProficiencia> getAll() {
        return null;
    }

    @Override
    public ArrayList<GrauProficiencia> findByCompetenciaTecnica(String codigoCompetenciaTecnica) {
        return null;
    }

    @Override
    public GrauProficiencia findByValor(String valor, String codigoCompetenciaTecnica) {
        return null;
    }
}
