package com.grupo2.t4j.persistence.database;

import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.Tarefa;
import com.grupo2.t4j.persistence.RepositorioTarefa;

import java.util.ArrayList;

public class RepositorioTarefaDatabase implements RepositorioTarefa {

    /**
     * Define uma instância estática do Repositório em que estão
     * registadas todas as Tarefas
     */
    private static RepositorioTarefaDatabase repositorioTarefaDatabase;

    /**
     * Inicializa o Repositório de Tarefas
     */
    RepositorioTarefaDatabase(){    }

    /**
     * Devolve uma instância estática do Repositório de Tarefas
     *
     * @return RepositorioTarefaDatabase
     */
    public static RepositorioTarefaDatabase getInstance(){
        if(repositorioTarefaDatabase == null) {
            repositorioTarefaDatabase = new RepositorioTarefaDatabase();
        }
        return repositorioTarefaDatabase;
    }

    @Override
    public void save(AreaActividade areaActividade, Categoria categoriaTarefa,
                     String referencia, String designacao, String descInformal,
                     String descTecnica, int duracao, double custo) {

    }

    @Override
    public void save(Tarefa tarefa) {

    }

    @Override
    public Tarefa findByReferencia(String referencia) {
        return null;
    }

    @Override
    public ArrayList<Tarefa> getAll() {
        return null;
    }
}
