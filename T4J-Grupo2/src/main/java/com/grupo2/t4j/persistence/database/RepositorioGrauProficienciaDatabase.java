package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.persistence.RepositorioGrauProficiencia;

import java.util.ArrayList;
import java.util.List;

public class RepositorioGrauProficienciaDatabase implements RepositorioGrauProficiencia {
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
