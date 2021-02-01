/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.repository;

import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.CompetenciaTecnica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
public class RepositorioCompetenciaTecnica {
    List<CompetenciaTecnica> listaCompTecnicas= new ArrayList<>();
    
    public RepositorioCompetenciaTecnica (List<CompetenciaTecnica> listaCompTecnicas){
        this.listaCompTecnicas=listaCompTecnicas;
    }

    public void setListaCompTecnicas(List<CompetenciaTecnica> listaCompTecnicas) {
        this.listaCompTecnicas = listaCompTecnicas;
    }
    public ArrayList<CompetenciaTecnica> getCompetenciasTecnicas() {
        
      return new ArrayList<CompetenciaTecnica>(listaCompTecnicas);
    }
    
    public CompetenciaTecnica getCompetenciaTecnica(String id) {
        CompetenciaTecnica ct = getCompetenciaTecnicaByCodigo(id);
        if (ct != null) {
            return new CompetenciaTecnica(ct);
        }
        return null;
    }
    public CompetenciaTecnica getCompetenciaTecnicaByCodigo(String codigo) {
        CompetenciaTecnica compTec = null;
        for (int i = 0; i < this.listaCompTecnicas.size(); i++) {
            compTec = this.listaCompTecnicas.get(i);
            if (compTec.getCodigo().equals(codigo)) {
                CompetenciaTecnica copia = new CompetenciaTecnica(compTec);
                return copia;
               
            }
        }
        return null;
    }
}
