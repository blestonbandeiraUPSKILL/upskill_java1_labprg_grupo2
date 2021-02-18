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
import com.grupo2.t4j.persistence.inmemory.RepositorioAreaActividadeInMemory;
import com.grupo2.t4j.persistence.inmemory.RepositorioCategoriaTarefaInMemory;
import com.grupo2.t4j.persistence.inmemory.RepositorioTarefaInMemory;

import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarTarefaController {

    public List<CaracterizacaoCT> getCompetenciasTecnicasByCategoria(Categoria categoria) { //Vai buscar a lista de Competencias Tecnicas da Categoria
        return categoria.getCompTecnicasCaracter();
    }

    public List<AreaActividade> getListaAreasActividade() {
        return RepositorioAreaActividadeInMemory.getInstance().getListaAreasActividade();
    }

    public List<Categoria> getListaCategoriaByAreaActividade(AreaActividade areaActividade) {
        return RepositorioCategoriaTarefaInMemory.getInstance().getCategoriasByAreaActividade(areaActividade);
    }

    public boolean registarTarefa(AreaActividade areaActividade, Categoria categoria, 
            String referencia, String designacao, String descricaoInformal, 
            String descricaoTecnica, int duracao, double custo) {
       return RepositorioTarefaInMemory.getInstance().addTarefa(areaActividade, categoria, referencia,
               designacao, descricaoInformal, descricaoTecnica, duracao, custo);
    }

    public List<Tarefa> getListTarefas() {
        return RepositorioTarefaInMemory.getInstance().getAll();
    }

    public Tarefa novaTarefa(AreaActividade areaActividade, Categoria categoriaTarefa,
                             String referencia,
                             String designacao,
                             String descInformal,
                             String descTecnica,
                             int duracao,
                             double custo) {
        return RepositorioTarefaInMemory.getInstance().novaTarefa(areaActividade, categoriaTarefa,
                referencia, designacao, descInformal, descTecnica, duracao, custo);
    }

    public boolean registarTarefa(Tarefa tarefa) {
        return RepositorioTarefaInMemory.getInstance().addTarefa(tarefa);
    }
}
