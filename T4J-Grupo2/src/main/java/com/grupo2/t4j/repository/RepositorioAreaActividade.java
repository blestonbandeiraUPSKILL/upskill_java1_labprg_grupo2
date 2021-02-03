/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.repository;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.exception.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAreaActividade {
    
    private static RepositorioAreaActividade repositorioAreaActividade;
    
    private Plataforma plataforma;
    private List<AreaActividade> listaAreasActividade = new ArrayList<>();
    
    public RepositorioAreaActividade(Plataforma plataforma, List<AreaActividade> listaAreasActividade){
        repositorioAreaActividade.plataforma = plataforma;
        repositorioAreaActividade.listaAreasActividade = listaAreasActividade;
    }
    
    public void addAreaActividade(AreaActividade areaActividade) throws AreaActividadeDuplicadaException {
        AreaActividade aa = getAreaActividadeByCodigo(areaActividade.getCodigo());
        if (aa == null) {
            this.listaAreasActividade.add(areaActividade);
        } else {
            throw new AreaActividadeDuplicadaException(aa.getCodigo() + ": Área de Actividade já registada!");
        }
    }
    
    private AreaActividade getAreaActividadeByCodigo(String codigo) {
        AreaActividade areaActividade = null;
        for (int i = 0; i < this.listaAreasActividade.size(); i++) {
            areaActividade = this.listaAreasActividade.get(i);
            if (areaActividade.getCodigo().equals(codigo)) {
                AreaActividade copia = new AreaActividade(areaActividade);
                return copia;
            }
        }
        return null;
    } 
    
    public static RepositorioAreaActividade getInstance(){
        if(RepositorioAreaActividade.repositorioAreaActividade == null) {
            List<AreaActividade> newlistaAreasActividade = new ArrayList<>();
            RepositorioAreaActividade.repositorioAreaActividade = new RepositorioAreaActividade(Plataforma.getInstance(), newlistaAreasActividade);
        }
        return repositorioAreaActividade;
    }
    
}
