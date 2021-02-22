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
import java.util.List;

public interface RepositorioTarefa {


    void save(String codigoAreaActividade, String codigoCategoriaTarefa,
                             String referencia, String designacao,
                             String descInformal, String descTecnica,
                             int duracao, double custo);

    boolean save(Tarefa tarefa);

    Tarefa findByReferenciaENIF(String referencia, String NIF);


    List<Tarefa> findByCategoria (String codigoCategoria);


    /**
     * Devolve a lista de Tarefas
     *
     * @return
     */
    ArrayList<Tarefa> getAll();


    //getAllByOrgPublicadas, getAllByOrgNaoPublicadas, getAllByOrg

    //update, delete
}
