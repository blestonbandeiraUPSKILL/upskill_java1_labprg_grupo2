package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.persistence.RepositorioCategoriaTarefa;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCategoriaTarefaDatabase implements RepositorioCategoriaTarefa {

    /**
     * Define uma instância estática do Repositório em que estão
     * registadas todas as Categorias de Tarefa
     */
    private static RepositorioCategoriaTarefaDatabase repositorioCategoriaTarefaDatabase;

    /**
     * Inicializa o Repositório de Categorias de Tarefa
     */
    RepositorioCategoriaTarefaDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Categorias de Tarefa
     *
     * @return RepositorioCategoriaTarefaDatabase
     */
    public static RepositorioCategoriaTarefaDatabase getInstance(){
        if(repositorioCategoriaTarefaDatabase == null) {
            repositorioCategoriaTarefaDatabase = new RepositorioCategoriaTarefaDatabase();
        }
        return repositorioCategoriaTarefaDatabase;
    }

    @Override
    public void save(String descBreve, String descDetalhada, AreaActividade areaActividade, List<CaracterizacaoCT> caracterizacaoCTS) {

    }

    @Override
    public boolean save(Categoria categoria) {
        return false;
    }

    @Override
    public Categoria findByCodigo(String codigoCategoria) {
        return null;
    }

    @Override
    public ArrayList<Categoria> findByAreaActividade(String codigoAreaActividade) {
        return null;
    }

    @Override
    public ArrayList<Categoria> getAll() {
        return null;
    }
}
