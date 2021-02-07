/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.repository.RepositorioAreaActividade;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarAreaActividadeController {
    
    private RepositorioAreaActividade repositorioAreaActividade;

    public boolean registarAreaActividade(String codigo, String descricaoBreve, String descricaoDetalhada) {
        return repositorioAreaActividade.getInstance().addAreaActividade(new AreaActividade(codigo, descricaoBreve, descricaoDetalhada));
    }

    public List<AreaActividade> getAreasActividade() {
        return repositorioAreaActividade.getInstance().getListaAreasActividade();
    }


    
}
