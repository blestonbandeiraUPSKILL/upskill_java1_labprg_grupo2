/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.Tarefa;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;
import com.grupo2.t4j.persistence.RepositorioCategoriaTarefa;
import com.grupo2.t4j.persistence.RepositorioTarefa;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;
import com.grupo2.t4j.persistence.inmemory.RepositorioAreaActividadeInMemory;
import com.grupo2.t4j.persistence.inmemory.RepositorioCategoriaTarefaInMemory;
import com.grupo2.t4j.persistence.inmemory.RepositorioTarefaInMemory;

import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarTarefaController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioTarefa repositorioTarefa = fabricaRepositorios.getRepositorioTarefa();

    public List<Tarefa> getAll() {
        return repositorioTarefa.getAll();
    }

    public boolean registarTarefa(String codigoAreaActividade, String codigoCategoriaTarefa,
                             String referencia, String designacao,
                             String descInformal, String descTecnica,
                             int duracao, double custo) {

        Tarefa tarefa = new Tarefa(referencia, designacao, descInformal, descTecnica, duracao, custo, codigoAreaActividade, codigoCategoriaTarefa);

        return repositorioTarefa.save(tarefa);
    }
}
