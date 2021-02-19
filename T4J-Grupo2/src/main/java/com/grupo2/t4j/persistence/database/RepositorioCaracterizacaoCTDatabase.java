package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.exception.CaracterizacaoCTDuplicadaException;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.model.GrauProficiencia;
import com.grupo2.t4j.model.Obrigatoriedade;
import com.grupo2.t4j.persistence.RepositorioCaracterizacaoCT;

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
    RepositorioCaracterizacaoCTDatabase(){    }

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
    public boolean save(CaracterizacaoCT caracterizacaoCT) {
        return false;
    }

    @Override
    public List<CaracterizacaoCT> getAll() {
        return null;
    }

    @Override
    public List<CaracterizacaoCT> findByCompetenciaTecnica(List<String> codigoCompetenciasTecnicas) {
        return null;
    }
}
