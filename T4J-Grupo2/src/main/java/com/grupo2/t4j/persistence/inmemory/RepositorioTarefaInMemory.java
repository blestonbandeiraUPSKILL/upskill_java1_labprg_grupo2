/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.inmemory;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.TarefaDuplicadaException;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.Tarefa;
import com.grupo2.t4j.persistence.RepositorioTarefa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTarefaInMemory implements Serializable, RepositorioTarefa {
    
    /**
     * Define uma instância estática do Repositório em que estão registados todos
     * os Administradores da plataforma
     */
    private static RepositorioTarefaInMemory repositorioTarefaInMemory;
    
     /**
     * Define o atributo da classe RepositorioTarefa como uma lista de
     * tipos da classe Tarefa
     */
    private List<Tarefa> listaTarefas;

    private List<Categoria> listaCategorias;

    /**
     * Devolve uma instância estática do Repositório de Tarefas
     * @return RepositorioTarefa
     */
    public static RepositorioTarefaInMemory getInstance(){
        if (repositorioTarefaInMemory == null){
            repositorioTarefaInMemory = new RepositorioTarefaInMemory();
        }
        return repositorioTarefaInMemory;
    }

    /**
     * Inicializa o Repositório de Tarefas
     */
    RepositorioTarefaInMemory(){
        listaTarefas = new ArrayList<>();
    }

    /**
     * Adiciona uma Tarefa à lista de Tarefas
     * @param tarefa do tipo da classe Tarefa
     * @throws TarefaDuplicadaException
     * @return
     */
    public boolean addTarefa(Tarefa tarefa) throws TarefaDuplicadaException {
        Tarefa t = findByReferencia(tarefa.getReferencia());
        if (t == null) {
            return this.listaTarefas.add(tarefa);
        } else {
            throw new TarefaDuplicadaException(t.getReferencia() + 
                    ": Tarefa já registada");
        }

    }

    @Override
    public void save(AreaActividade areaActividade, Categoria categoriaTarefa, String referencia,
                     String designacao, String descInformal, String descTecnica, int duracao, double custo) {
        Tarefa t = findByReferencia(referencia);
        if (t == null) {
            Tarefa tarefa = new Tarefa(referencia, designacao, descInformal,
                    descTecnica, duracao, custo);
            this.listaTarefas.add(tarefa);
        }
        else {
            throw new TarefaDuplicadaException(t.getReferencia() +
                    ": Tarefa já registada");
        }
    }

    @Override
    public Tarefa findByReferencia(String referencia) {
        for (int i = 0; i < this.listaTarefas.size(); i++) {
            Tarefa tarefa = this.listaTarefas.get(i);
            if (tarefa.getReferencia().equals(referencia)) {
                return tarefa;
            }
        }
        return null;
    }

    /**
     * Devolve a lista de Tarefas
     *
     * @return 
     */
    public ArrayList<Tarefa> getAll() {

        return new ArrayList<Tarefa>(listaTarefas);
    }

    //getAllByOrgPublicadas, getAllByOrgNaoPublicadas, getAllByOrg
}
