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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTarefaInMemory implements Serializable{
    
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
    private RepositorioTarefaInMemory(){
        listaTarefas = new ArrayList<>();
    }

    public Tarefa novaTarefa(AreaActividade areaActividade, Categoria categoriaTarefa,
                             String referencia,
                             String designacao,
                             String descInformal,
                             String descTecnica,
                             int duracao,
                             double custo) {
        return new Tarefa(areaActividade, categoriaTarefa,
                referencia, designacao, descInformal, descTecnica, duracao, custo);
    }

    /**
     * Adiciona uma Tarefa à lista de Tarefas
     * @param tarefa do tipo da classe Tarefa
     * @throws TarefaDuplicadaException
     * @return
     */
    public boolean addTarefa(Tarefa tarefa) throws TarefaDuplicadaException {
        Tarefa t = getTarefaByReferencia(tarefa.getReferencia());
        if (t == null) {
            return this.listaTarefas.add(tarefa);
        } else {
            throw new TarefaDuplicadaException(t.getReferencia() + 
                    ": Tarefa já registada");
        }

    }
    
    /**
     * Adiciona uma Tarefa à lista de Tarefas
     * @param referencia a referência única de uma tarefa em uma Organização.
     * @param designacao a designação da tarefa.
     * @param descInformal a descrição informal da tarefa.
     * @param descTecnica a descrição técnica da tarefa.
     * @param duracaoEst a duração estimada da tarefa em dias.
     * @param custoEst o custo estimado da tarefa em euros.
     * @throws TarefaDuplicadaException
     */
    public void addTarefa(String referencia, String designacao, String descInformal, 
            String descTecnica, int duracaoEst, double custoEst) throws 
            TarefaDuplicadaException {
        Tarefa t = getTarefaByReferencia(referencia);
        if (t == null) {
            Tarefa tarefa = new Tarefa(referencia, designacao, descInformal, 
            descTecnica, duracaoEst, custoEst);
            this.listaTarefas.add(tarefa);
        } else {
            throw new TarefaDuplicadaException(t.getReferencia() + 
                    ": Tarefa já registada");
        }
    }
    /**
     * Adiciona uma Tarefa à lista de Tarefas
     * @param areaActividade a area de actividade em que se enquadra a tarefa
     * @param categoria a categoria em que se enquadra a tarefa
     * @param referencia a referência única de uma tarefa em uma Organização.
     * @param designacao a designação da tarefa.
     * @param descInformal a descrição informal da tarefa.
     * @param descTecnica a descrição técnica da tarefa.
     * @param duracaoEst a duração estimada da tarefa em dias.
     * @param custoEst o custo estimado da tarefa em euros.
     * @throws TarefaDuplicadaException
     */
    public boolean addTarefa(AreaActividade areaActividade, Categoria categoria, String referencia, String designacao, String descInformal, 
            String descTecnica, int duracaoEst, double custoEst) throws 
            TarefaDuplicadaException {
        Tarefa t = getTarefaByReferencia(referencia);
        if (t == null) {
            Tarefa tarefa = new Tarefa(referencia, designacao, descInformal, 
            descTecnica, duracaoEst, custoEst);
            return this.listaTarefas.add(tarefa);
        } else {
            throw new TarefaDuplicadaException(t.getReferencia() + 
                    ": Tarefa já registada");
        }
        
    }
    
    /**
     * Devolve uma Tarefa de acordo com a referência indicada
     * @param referencia a referência única da tarefa em uma Organização
     * @return tarefa registada
     */
    public Tarefa getTarefaByReferencia(String referencia) {
        Tarefa tarefa = null;
        for (int i = 0; i < this.listaTarefas.size(); i++) {
            tarefa = this.listaTarefas.get(i);
            if (tarefa.getReferencia().equals(referencia)) {
                return tarefa;
            }
        }        
        return null;
    }
    
     /**
     * Atualiza a lista de Tarefas
     *
     * @param listaTarefas
     */
    public void setListaTarefas(List<Tarefa> listaTarefas) {
        this.listaTarefas = listaTarefas;
    }

    /**
     * Devolve a lista de Tarefas
     *
     * @return 
     */
    public ArrayList<Tarefa> getAll() {

        return new ArrayList<Tarefa>(listaTarefas);
    }
    

    
    /**
     * Informa se a lista de Tarefas está ou não vazia
     * @return 
     */
    public boolean isVazia() {
        return listaTarefas.isEmpty();
    }

    //getAllByOrgPublicadas, getAllByOrgNaoPublicadas, getAllByOrg
}
