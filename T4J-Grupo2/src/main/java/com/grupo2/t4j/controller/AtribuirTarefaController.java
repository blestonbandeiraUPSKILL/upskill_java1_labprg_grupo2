package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.*;
import com.grupo2.t4j.domain.strategy.*;
import com.grupo2.t4j.dto.AtribuicaoDTO;
import com.grupo2.t4j.persistence.*;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CAD
 */
public class AtribuirTarefaController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioAtribuicao repositorioAtribuicao = fabricaRepositorios.getRepositorioAtribuicao();

    public AtribuirTarefaController(){

    }

    public AtribuicaoDTO findAtribuicaoByTarefa(String refTarefa) throws SQLException{
        return repositorioAtribuicao.findAtribuicaoByTarefa(refTarefa);
    }

    public List<AtribuicaoDTO> getAllByOrganizacao(String nifOrganizacao) throws SQLException{
        return repositorioAtribuicao.getAllByOrganizacao(nifOrganizacao);
    }

}
