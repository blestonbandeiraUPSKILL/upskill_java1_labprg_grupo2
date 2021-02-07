/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.repository.RepositorioAreaActividade;

import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarAreaActividadeController {
    
    private RepositorioAreaActividade repositorioAreaActividade;

    public boolean registarAreaActividade(String codigo, String descricaoBreve, String descricaoDetalhada) {
        return RepositorioAreaActividade.getInstance().addAreaActividade(codigo, descricaoBreve, descricaoDetalhada);
    }

    public List<AreaActividade> getAreasActividade() {
        return RepositorioAreaActividade.getInstance().getListaAreasActividade();
    }


    public boolean registarAreaActividade(AreaActividade areaActividade) {
        return RepositorioAreaActividade.getInstance().addAreaActividade(areaActividade);
    }

    public List<String> getAreasActividadeByDescBreve() {
        return RepositorioAreaActividade.getInstance().getListaAreasActividadeByDescBreve();
    }

    public AreaActividade getAreaActividadeByCodigo(String codigo) {
       List<AreaActividade> listaAreasActividade = RepositorioAreaActividade.getInstance().getListaAreasActividade();
        for (int i = 0; i < listaAreasActividade.size(); i++) {
            //AreaActividade areaActividade = RepositorioAreaActividade.getInstance().getAreaActividadeByCodigo(codigo);
            AreaActividade areaActividade = listaAreasActividade.get(i);
            if (areaActividade.getCodigo().equals(codigo)) {
                return areaActividade;
            }
        }
        return null;
    }

    public List<AreaActividade> getListaAreasActividade() {
        return RepositorioAreaActividade.getInstance().getListaAreasActividade();
    }
}
