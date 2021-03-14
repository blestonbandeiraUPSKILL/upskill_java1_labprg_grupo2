/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.*;
import com.grupo2.t4j.persistence.*;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CAD
 */
public class SeriarAnuncioController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioTarefa repositorioTarefa = fabricaRepositorios.getRepositorioTarefa();
    private RepositorioAnuncio repositorioAnuncio = fabricaRepositorios.getRepositorioAnuncio();
    private RepositorioCandidatura repositorioCandidatura = fabricaRepositorios.getRepositorioCandidatura();
    private RepositorioColaborador repositorioColaborador = fabricaRepositorios.getRepositorioColaborador();
    private RepositorioColaboradorSeriacao repositorioColaboradorSeriacao = fabricaRepositorios.getRepositorioColaboradorSeriacao();
    private RepositorioClassificacao repositorioClassificacao = fabricaRepositorios.getRepositorioClassificacao();
    private RepositorioSeriacao repositorioSeriacao = fabricaRepositorios.getRepositorioSeriacao();
    private RepositorioTipoRegimento repositorioTipoRegimento = fabricaRepositorios.getRepositorioTipoRegimento();
    
    /**
     * 
     */
    public SeriarAnuncioController(){
        
    }
    
    /**
     * Retorna uma lista de referências de tarefas de uma organização
     * @param nifOrganizacao - nif da organização
     * @return
     * @throws SQLException
     */
    public List<String> getReferenciasTarefas(String nifOrganizacao) throws SQLException{
        return repositorioTarefa.findReferenciaTarefa(nifOrganizacao);
    }
    
    /**
     * Retorna uma lista de tarefas publicadas de uma organização
     * @param nifOrganizacao - nif da organização
     * @return
     * @throws SQLException
     */
    public List<Tarefa> findTarefasPublicadas(String nifOrganizacao)throws SQLException{
        return repositorioTarefa.findTarefasPublicadas(nifOrganizacao);
    }
    
    /**
     * Retorna as referências das tarefas da lista de tarefas recebida
     * @param listaTarefas - lista de tarefas
     * @return
     * @throws SQLException
     */
    public List<String> getReferenciasTarefas(List<Tarefa> listaTarefas) throws SQLException{
        return repositorioTarefa.getReferenciasTarefas(listaTarefas);
    }
    
    /**
     * Retorna uma lista de referências de tarefa de uma organização, publicadas
     * por um dado colaborador e de acordo com um tipo específico de regimento
     * @param referenciasTarefa - lista de referências de tarefa
     * @param emailColaborador - email do colaborador que publicou as tarefas
     * @param idTipoRegimento - id do tipo de regimento pelo qual se dá a seriação da tarefa
     * @return
     * @throws SQLException
     */
    public List<String> getAllRefTarefasTipoRegimento(List<String> referenciasTarefa, String emailColaborador, int idTipoRegimento) throws SQLException{
        return repositorioAnuncio.getAllRefTarefasTipoRegimento(referenciasTarefa, emailColaborador, idTipoRegimento);
    }
    
    /**
     * Retorna uma lista de tarefas de uma organização, publicadas por um colaborador,
     * que estejam em período de seriação
     * @param referenciasTarefa - lista de referências de tarefa
     * @param nifOrganizacao - nif da organização
     * @param emailColaborador - email do colaborador que publicou as tarefas
     * @return
     * @throws SQLException
     */
    public List<Tarefa> getAllRefTarefasASeriar(List<String> referenciasTarefa, String nifOrganizacao, String emailColaborador) throws SQLException{
        return repositorioAnuncio.getAllRefTarefasASeriar(referenciasTarefa, nifOrganizacao, emailColaborador);
    }
    
    /**
     * Retorna o id do Anúncio referente a uma dada referência de tarefa
     * @param referenciaTarefa - referência da tarefa
     * @param nifOrganizacao - nif da organização
     * @return
     * @throws SQLException
     */
    public int getIdAnuncioByIdTarefa(String referenciaTarefa, String nifOrganizacao) throws SQLException{
        return repositorioAnuncio.findAnuncioByIdTarefa(referenciaTarefa, nifOrganizacao).getIdAnuncio();
    }
    
    /**
     * Retorna o anúncio de acordo com o id do anúncio
     * @param idAnuncio - id do anúncio
     * @return
     * @throws SQLException
     */
    public Anuncio getAnuncio(int idAnuncio) throws SQLException {
        return repositorioAnuncio.getAnuncio(idAnuncio);
    }
    
    /**
     * Retorna um tipo de regimento com base no id do Regimento
     * @param idTipoRegimento - id do Regimento de seriação
     * @return
     * @throws SQLException
     */
    public TipoRegimento findRegimentoById(int idTipoRegimento) throws SQLException{
        return repositorioTipoRegimento.findById(idTipoRegimento);
    }
    
    /**
     * Retorna uma candidatura com base no id da candidatura
     * @param idCandidatura - id da candidatura
     * @return
     * @throws SQLException
     */
    public Candidatura findById(int idCandidatura) throws SQLException {
        return repositorioCandidatura.findById(idCandidatura);
    }
    
    /**
     * Retorna uma lista de candidaturas para um dado anúncio
     * @param idAnuncio - id do anúncio
     * @return
     * @throws SQLException
     */
    public List<Candidatura> getAllByIdAnuncio(int idAnuncio) throws SQLException{
        return repositorioCandidatura.getAllByIdAnuncio(idAnuncio);
    }
    
    /**
     * Retorna uma lista de candidaturas ordenadas pelo valor crescente de valor 
     * pretendido pelo freelancer em sua candidatura
     * @param candidaturas - lista de candidaturas
     * @return
     * @throws SQLException
     */
    public List<Candidatura> ordenarByValor(List<Candidatura> candidaturas) throws SQLException{
        return repositorioCandidatura.ordenarByValor(candidaturas);
    }
     
    /**
     * Salva um processo de seriação de um anúncio
     * @param idAnuncio - id do anúncio
     * @return
     * @throws SQLException
     */
    public boolean saveSeriacao(int idAnuncio)throws SQLException{
        return repositorioSeriacao.save(idAnuncio);
    }
    
    /**
     * Retorna o id de seriação para o processo de seriação de um anúncio
     * @param idAnuncio - id do anúncio
     * @return
     * @throws SQLException
     */
    public int getIdSeriacao(int idAnuncio)throws SQLException{
        return repositorioSeriacao.getProcessoSeriacaoByAnuncio(idAnuncio).getIdSeriacao();
    }
    
    /**
     * Retorna um processo de seriação de um anúncio
     * @param idAnuncio - id do anúncio
     * @return
     * @throws SQLException
     */
    public ProcessoSeriacao findSeriacaoByAnuncio(int idAnuncio) throws SQLException{
        return repositorioSeriacao.findByAnuncio(idAnuncio);
    }
    
    /**
     * Salva uma classificação de um freelancer em um processo de seriação
     * @param posicao - a posição atribuída ao freelancer na seriação
     * @param idSeriacao - o id da seriação
     * @param idCandidatura - o id da candidatura
     * @return
     * @throws SQLException
     */
    public boolean saveClassificacao(int posicao, int idSeriacao, int idCandidatura) throws SQLException{
        return repositorioClassificacao.save(posicao, idSeriacao, idCandidatura);
    }
    
    /**
     * Salva uma lista de candidaturas ordenadas de um processo de seriação
     * @param candidaturasOrdenadas - lista de candidaturas
     * @param idSeriacao - o id da seriação
     * @return
     * @throws SQLException
     */
    public boolean saveClassificacaoAutomatica(List<Candidatura> candidaturasOrdenadas, int idSeriacao) throws SQLException{
        int posicao = 1;
        boolean adicionou = false;
        for(Candidatura c : candidaturasOrdenadas){
            adicionou = repositorioClassificacao.save(posicao, idSeriacao, c.getIdCandidatura());
            posicao++;
        }
        return adicionou;
    }
    
    /**
     * Retorna uma lista de classificações de candidaturas de um processo de seriação
     * @param idSeriacao - id do processo de seriação
     * @return
     * @throws SQLException
     */
    public List<Classificacao> getAllBySeriacao(int idSeriacao)throws SQLException{
        return repositorioClassificacao.getAllBySeriacao(idSeriacao);
    }
    
    /**
     * Retorna uma candidatura com base no seu id
     * @param idCandidatura - id da candidatura
     * @return
     * @throws SQLException
     */
    public Candidatura findCandidaturaById(int idCandidatura) throws SQLException{
        return repositorioCandidatura.findById(idCandidatura);
    }
    
    /**
     * Retorna uma classificação com base no seu id
     * @param idClassificacao - id da classificação
     * @return
     * @throws SQLException
     */
    public Classificacao findClassificacaoById(int idClassificacao) throws SQLException{
        return repositorioClassificacao.findById(idClassificacao);
    }
    
    /**
     * Retorna uma lista de colaboradores de uma organização
     * @param nifOrganizacacao - nif da organização
     * @return
     * @throws SQLException
     */
    public ArrayList<Colaborador> getAll(String nifOrganizacacao) throws SQLException{
        return repositorioColaborador.getAll(nifOrganizacacao);
    }
    
    /**
     * Regista um colaborador como participante no processo de seriação
     * @param emailColaborador - email do colaborador
     * @param idSeriacao - id da seriação
     * @return
     * @throws SQLException
     */
    public boolean update(String emailColaborador, int idSeriacao) throws SQLException{
        return repositorioColaboradorSeriacao.update(emailColaborador, idSeriacao);
    }
    
    /**
     * Retorna um processo de seriação de um anúncio
     * @param idAnuncio - id do anúncio
     * @return
     * @throws SQLException
     */
    public ProcessoSeriacao getProcesoSeriacaoByAnuncio(int idAnuncio) throws SQLException {
        return repositorioSeriacao.getProcessoSeriacaoByAnuncio(idAnuncio);
    }

    /**
     * Retorna uma lista de processos de seriação de um anúncio
     * @param idAnuncio - id do anúncio
     * @return
     * @throws SQLException
     */
    public List<ProcessoSeriacao> getAllPSByIdAnuncio(int idAnuncio) throws SQLException {
        return repositorioSeriacao.getAllByIdAnuncio(idAnuncio);
    }
}
