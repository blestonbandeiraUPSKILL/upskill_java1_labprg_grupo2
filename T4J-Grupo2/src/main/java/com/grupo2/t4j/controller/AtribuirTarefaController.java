package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.*;
import com.grupo2.t4j.domain.strategy.*;
import com.grupo2.t4j.dto.AtribuicaoDTO;
import com.grupo2.t4j.dto.FreelancerDTO;
import com.grupo2.t4j.dto.TarefaDTO;
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
    private RepositorioFreelancer repositorioFreelancer = fabricaRepositorios.getRepositorioFreelancer();
    private RepositorioTarefa repositorioTarefa = fabricaRepositorios.getRepositorioTarefa();
    private RepositorioSeriacao repositorioSeriacao = fabricaRepositorios.getRepositorioSeriacao();

    public AtribuirTarefaController(){

    }

    public AtribuicaoDTO findAtribuicaoByTarefa(String refTarefa) throws SQLException{
        return repositorioAtribuicao.findAtribuicaoByTarefa(refTarefa);
    }

    public List<AtribuicaoDTO> getAllByOrganizacao(String nifOrganizacao) throws SQLException{
        return repositorioAtribuicao.getAllByOrganizacao(nifOrganizacao);
    }

    public ProcessoSeriacao getProcessoSeriacaoByAnuncio (int idAnuncio) throws SQLException{
        return repositorioSeriacao.getProcessoSeriacaoByAnuncio(idAnuncio);
    }

    public TarefaDTO findTarefa(int idAnuncio) throws SQLException {
        Tarefa tarefa = repositorioTarefa.findTarefa(idAnuncio);
        return (TarefaDTO) tarefa.toDTO();
    }

    /**
     * Devolve um freelancer a partir do seu email
     * @param emailFree
     * @return
     * @throws SQLException
     */
    public FreelancerDTO findByEmail(String emailFree) throws SQLException{
        Freelancer freelancer = repositorioFreelancer.findByEmail(emailFree);
        return (FreelancerDTO) freelancer.toDTO();
    }

}
