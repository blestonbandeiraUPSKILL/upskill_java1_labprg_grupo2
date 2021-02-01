/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.repository;

import com.grupo2.t4j.exception.CompetenciaTecnicaDuplicadaException;
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
    
    /**
     * Adiciona uma competÃªncia tÃ©cnica Ã  lista de Competencias TÃ©cnicas
     * @param competenciaTecnica
     * @throws com.grupo2.t4j.exception.CompetenciaTecnicaDuplicadaException
     * @throws CompetenciaTecnicaDuplicadaException 
     */
    public void addCompetenciaTecnica(CompetenciaTecnica competenciaTecnica) throws CompetenciaTecnicaDuplicadaException {
        CompetenciaTecnica ct = getCompetenciaTecnicaByCodigo(competenciaTecnica.getCodigo());
        if (ct == null) {
            this.listaCompTecnicas.add(competenciaTecnica);
        } else {
            throw new CompetenciaTecnicaDuplicadaException(ct.getCodigo() + ": Competencia Tecnica já existe");
        }
    }
/**
 * Atualiza a lista de CompetÃªncias TÃ©cnicas
 * @param listaCompTecnicas 
 */
    public void setListaCompTecnicas(List<CompetenciaTecnica> listaCompTecnicas) {
        this.listaCompTecnicas = listaCompTecnicas;
    }
    
    /**
     * Devolve a lista de competÃªncias tÃ©cnicas
     * @return 
     */
    public ArrayList<CompetenciaTecnica> getCompetenciasTecnicas() {
        
      return new ArrayList<CompetenciaTecnica>(listaCompTecnicas);
    }
    
   /**
    * Devolve uma competÃªncia tÃ©cnica de acordo com o seu cÃ³digo
    * @param codigo
    * @return 
    */
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
