package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.persistence.RepositorioCompetenciaTecnica;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCompetenciaTecnicaDatabase implements RepositorioCompetenciaTecnica {

    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os Colaboradores
     */
    private static RepositorioCompetenciaTecnicaDatabase repositorioCompetenciaTecnicaDatabase;

    /**
     * Inicializa o Repositório de Colaboradores
     */
    RepositorioCompetenciaTecnicaDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Colaboradores
     *
     * @return RepositorioCompetenciaTecnicaDatabase
     */
    public static RepositorioCompetenciaTecnicaDatabase getInstance(){
        if(repositorioCompetenciaTecnicaDatabase == null) {
            repositorioCompetenciaTecnicaDatabase = new RepositorioCompetenciaTecnicaDatabase();
        }
        return repositorioCompetenciaTecnicaDatabase;
    }

    @Override
    public void save(String codigo, String descBreve, String descDetalhada, AreaActividade areaActividade) {

    }

    @Override
    public List<CompetenciaTecnica> getAll() {
        return null;
    }

    @Override
    public CompetenciaTecnica findByCodigo(String codigo) {
        return null;
    }

    @Override
    public ArrayList<CompetenciaTecnica> findByAreaActividade(AreaActividade at) {
        return null;
    }
}
