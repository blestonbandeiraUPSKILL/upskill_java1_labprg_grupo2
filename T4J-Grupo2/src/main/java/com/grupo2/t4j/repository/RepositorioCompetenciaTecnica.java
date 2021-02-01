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
    
    public RepositorioCompetenciaTecnica (List<CompetenciaTecnica> listaCompTecnicas){
        this.listaCompTecnicas=listaCompTecnicas;
    }

    public List<CompetenciaTecnica> getListaCompTecnicas() {
        return listaCompTecnicas;
    }

    public void setListaCompTecnicas(List<CompetenciaTecnica> listaCompTecnicas) {
        this.listaCompTecnicas = listaCompTecnicas;
    }
}
   /* public ArrayList<CompetenciaTecnica> getCompetenciaTecnica() {
        CompetenciaTecnica compTecnica;
        ArrayList<CompetenciaTecnica> lista = new ArrayList<>();
        for (int i = 0; i < this.listaCompTecnicas.size(); i++) {
            compTecnica = this.listaCompTecnicas.get(i);
            if (pessoa instanceof Funcionario) {
            }
            Funcionario copia = new Funcionario((Funcionario) pessoa);
            lista.add(copia);
        }

        return null;
    }
    



        return lista;
    }*/
    

