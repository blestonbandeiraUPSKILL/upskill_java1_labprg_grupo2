/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.repository.RepositorioAreaActividade;
import com.grupo2.t4j.repository.RepositorioCategoria;
import com.grupo2.t4j.repository.RepositorioTarefa;
import java.util.List;

/**
 *
 * @author acris
 */
public class RegistarTarefaController {
    
    private RepositorioCategoria repositorioCategoria;
    private RepositorioAreaActividade repositorioAreaActividade;
    private RepositorioTarefa repositorioTarefa;

    public List<CaracterizacaoCT> getCompetenciasTecnicasByCategoria(Categoria categoria) { //Vai buscar a lista de Competencias Tecnicas da Categoria
        return categoria.getCompTecnicasCaracter();
    }

    public List<AreaActividade> getListaAreasActividade() {
        return repositorioAreaActividade.getListaAreasActividade();
    }

    public List<Categoria> getListaCategoriaPorAreaActividade(AreaActividade areaActividade) {
        return repositorioCategoria.getCategoriasByAreaActividade(areaActividade);
    }

    public boolean registarTarefa(AreaActividade areaActividade, Categoria categoria, 
            String referencia, String designacao, String descricaoInformal, 
            String descricaoTecnica, String duracao, String custo) {
       return repositorioTarefa.addTarefa(areaActividade, categoria, referencia,
               designacao, descricaoInformal, descricaoTecnica, Integer.parseInt(duracao),
               Double.parseDouble(custo));
    }
    
}
