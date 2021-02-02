/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.repository;

import com.grupo2.t4j.exception.CompetenciaTecnicaDuplicadaException;
import com.grupo2.t4j.model.AreaActividade;
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
     * Adiciona uma competencia tecnica a lista de Competencias Tecnicas
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
 * Atualiza a lista de Competencias Tecnicas
 * @param listaCompTecnicas 
 */
    public void setListaCompTecnicas(List<CompetenciaTecnica> listaCompTecnicas) {
        this.listaCompTecnicas = listaCompTecnicas;
    }
    
    /**
     * Devolve a lista de competÃªncias tecnicas
     * @return 
     */
    public ArrayList<CompetenciaTecnica> getCompetenciasTecnicas() {
        
      return new ArrayList<CompetenciaTecnica>(listaCompTecnicas);
    }
    
   /**
    * Devolve uma competencia tecnica de acordo com o seu codigo
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
    /**
     * Devolve uma lista de competencias tecnicas por area de actividade
     * @param at
     * @return 
     */
    public ArrayList<CompetenciaTecnica> getCompetenciasTecnicasByAreaActividade(AreaActividade at) {
        ArrayList<CompetenciaTecnica> compTecPorAt = new ArrayList<>();

        for (CompetenciaTecnica ct : listaCompTecnicas) {
            if (ct.getAreaActividade().equals(at)) {
                compTecPorAt.add(ct);
            }
        }

        return compTecPorAt;
    }
}
