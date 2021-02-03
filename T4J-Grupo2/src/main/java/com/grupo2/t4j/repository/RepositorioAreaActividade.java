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
    
    private List<AreaActividade> listaAreasActividade;
    
    private RepositorioAreaActividade(){
        listaAreasActividade = new ArrayList<>();
    }
    
    public void addAreaActividade(AreaActividade areaActividade) throws AreaActividadeDuplicadaException {
        AreaActividade aa = getAreaActividadeByCodigo(areaActividade.getCodigo());
        if (aa == null) {
            this.listaAreasActividade.add(areaActividade);
        } else {
            throw new AreaActividadeDuplicadaException(aa.getCodigo() + ": Área de Actividade já registada!");
        }
    }
    
    public void addAreaActividade(String codigo, String descBreve, String descDetalhada) throws AreaActividadeDuplicadaException {
        AreaActividade aa = getAreaActividadeByCodigo(codigo);
        if (aa == null) {
            AreaActividade areaActividade = new AreaActividade(codigo, descBreve, descDetalhada);
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
        if(repositorioAreaActividade == null) {
            repositorioAreaActividade = new RepositorioAreaActividade();
        }
        return repositorioAreaActividade;
    }
    
}
