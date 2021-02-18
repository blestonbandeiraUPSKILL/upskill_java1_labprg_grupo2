/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.model.*;

import java.util.ArrayList;

public interface RepositorioTarefa {


    void save(AreaActividade areaActividade, Categoria categoriaTarefa,
                             String referencia, String designacao,
                             String descInformal, String descTecnica,
                             int duracao, double custo);

    boolean save(Tarefa tarefa);

    Tarefa findByReferencia(String referencia);


    /**
     * Devolve a lista de Tarefas
     *
     * @return
     */
    public ArrayList<Tarefa> getAll();


    //getAllByOrgPublicadas, getAllByOrgNaoPublicadas, getAllByOrg

    //update, delete
}
