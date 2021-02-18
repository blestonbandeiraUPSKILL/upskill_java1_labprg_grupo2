/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.database;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.AreaActividadeDuplicadaException;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geral
 */
public class RepositorioAreaActividadeDatabase implements Serializable, RepositorioAreaActividade {

    /**
     * Define uma instância estática do Repositório em que estão registadas todas
     * as Áreas de Actividade da plataforma
     */
    private static RepositorioAreaActividadeDatabase repositorioAreaActividadeDatabase;


    /**
     * Inicializa o Repositório de Areas de Actividade
     */
    RepositorioAreaActividadeDatabase(){
    }

    /**
     * Devolve uma instância estática do Repositório de Áreas de Actividade
     * @return RepositorioAreaActividade
     */
    public static RepositorioAreaActividadeDatabase getInstance(){
        if(repositorioAreaActividadeDatabase == null) {
            repositorioAreaActividadeDatabase = new RepositorioAreaActividadeDatabase();
        }
        return repositorioAreaActividadeDatabase;
    }

    public void save(String codigo, String descBreve, String descDetalhada) throws AreaActividadeDuplicadaException {

    }

    @Override
    public List<AreaActividade> getAll() {
        return null;
    }

    @Override
    public AreaActividade findByCodigo(String codigo) {
        return null;
    }





}
