/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.files.FicheiroRepositorioAreaActividade;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAreaActividade;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;
import com.grupo2.t4j.persistence.inmemory.RepositorioAreaActividadeInMemory;
import java.io.File;

import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarAreaActividadeController /*implements Serializable*/{

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioAreaActividade repo = fabricaRepositorios.getRepositorioAreaActividade();
    private FicheiroRepositorioAreaActividade ficheiroAt;
    private RepositorioAreaActividadeInMemory repositorioAreaActividadeInMemory;

    public List<AreaActividade> getAreasActividade() {
        return RepositorioAreaActividadeInMemory.getInstance().getListaAreasActividade();
    }

 /*   public AreaActividade novaAreaActividade(String codigo, String descBreve, String descDetalhada) {
        return RepositorioAreaActividadeInMemory.getInstance().novaAreaActividade(codigo, descBreve, descDetalhada);
    }*/


    public void registarAreaActividade(String codigo, String descBreve, String descDetalhada) {
        AreaActividade areaActividade = new AreaActividade(codigo, descBreve, descDetalhada);
        repo.save(areaActividade);
        //return RepositorioAreaActividadeInMemory.getInstance().addAreaActividade(areaActividade);
    }

    public List<String> getAreasActividadeByDescBreve() {
        return RepositorioAreaActividadeInMemory.getInstance().getListaAreasActividadeByDescBreve();
    }

    public AreaActividade getAreaActividadeByCodigo(String codigo) {
       List<AreaActividade> listaAreasActividade = RepositorioAreaActividadeInMemory.getInstance().getListaAreasActividade();
        for (int i = 0; i < listaAreasActividade.size(); i++) {
            AreaActividade areaActividade = listaAreasActividade.get(i);
            if (areaActividade.getCodigo().equals(codigo)) {
                return areaActividade;
            }
        }
        return null;
    }

    //////FICHEIROS////////
    public RegistarAreaActividadeController() {
        ficheiroAt = new FicheiroRepositorioAreaActividade();
        
        desserializar();
    }
    public boolean serializar() {
        return ficheiroAt.serializar(repositorioAreaActividadeInMemory);
    }

    public boolean serializar(File ficheiroExportar) {
        return ficheiroAt.serializar(ficheiroExportar, repositorioAreaActividadeInMemory);
    }

    public void desserializar() {
        repositorioAreaActividadeInMemory = ficheiroAt.desserializar();
    }

    public int desserializar(File ficheiroImportar) {
        RepositorioAreaActividadeInMemory listaAreaActividadeImportada = ficheiroAt.desserializar(ficheiroImportar);

        return RepositorioAreaActividadeInMemory.getInstance().adicionarListaAreasActividade(listaAreaActividadeImportada);
    }
}
