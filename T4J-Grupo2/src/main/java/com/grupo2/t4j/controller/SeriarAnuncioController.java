package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.*;
import com.grupo2.t4j.domain.strategy.*;
import com.grupo2.t4j.dto.*;
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
    private RegimentoStrategy_1 regimentoStrategy_1 = new RegimentoStrategy_1();
    private RegimentoStrategy_2 regimentoStrategy_2 = new RegimentoStrategy_2();
    private RegimentoStrategy_3 regimentoStrategy_3 = new RegimentoStrategy_3();

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
     * Retorna as referências das tarefas da lista de tarefas recebida
     * @param listaTarefas - lista de tarefas
     * @return
     * @throws SQLException
     */
    public List<String> getReferenciasTarefas(List<Tarefa> listaTarefas) throws SQLException{
        return repositorioTarefa.getReferenciasTarefas(listaTarefas);
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
        List<Tarefa> tarefas = repositorioAnuncio.getAllRefTarefasASeriar(referenciasTarefa, nifOrganizacao, emailColaborador);
        List<TarefaDTO> tarefasDTO = new ArrayList<>();

        for(Tarefa tarefa : tarefas) {
            tarefasDTO.add((TarefaDTO) tarefa.toDTO());
        }
        return tarefas;
    }

    /**
     * Retorna o anúncio de acordo com o id do anúncio
     * @param idAnuncio - id do anúncio
     * @return
     * @throws SQLException
     */
    public AnuncioDTO getAnuncio(int idAnuncio) throws SQLException {
        Anuncio anuncio = repositorioAnuncio.getAnuncio(idAnuncio);
        return (AnuncioDTO) anuncio.toDTO();
    }

    /**
     * Retorna um tipo de regimento com base no id do Regimento
     * @param idTipoRegimento - id do Regimento de seriação
     * @return
     * @throws SQLException
     */
    public TipoRegimentoDTO findRegimentoById(int idTipoRegimento) throws SQLException{
        TipoRegimento tipoRegimento = repositorioTipoRegimento.findById(idTipoRegimento);
        return (TipoRegimentoDTO) tipoRegimento.toDTO();
    }

    /**
     * Retorna uma candidatura com base no id da candidatura
     * @param idCandidatura - id da candidatura
     * @return
     * @throws SQLException
     */
    public CandidaturaDTO findById(int idCandidatura) throws SQLException {
        Candidatura candidatura = repositorioCandidatura.findById(idCandidatura);

        return (CandidaturaDTO) candidatura.toDTO();
    }

    /**
     * Retorna uma lista de candidaturas para um dado anúncio
     * @param idAnuncio - id do anúncio
     * @return
     * @throws SQLException
     */
    public List<CandidaturaDTO> getAllByIdAnuncio(int idAnuncio) throws SQLException{
        List<Candidatura> candidaturas = repositorioCandidatura.getAllByIdAnuncio(idAnuncio);
        List<CandidaturaDTO> candidaturasDTO = new ArrayList<>();

        for(Candidatura candidatura : candidaturas) {
            candidaturasDTO.add((CandidaturaDTO) candidatura.toDTO());
        }
        return candidaturasDTO;
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

    public ProcessoSeriacao getProcessoSeriacaoByAnuncio (int idAnuncio) throws SQLException{
        return repositorioSeriacao.getProcessoSeriacaoByAnuncio(idAnuncio);
    }


    public boolean seriar(int idAnuncio) throws SQLException{
        boolean sucesso = false;
        int idRegimento = getAnuncio(idAnuncio).getIdTipoRegimento();
        if (idRegimento == 1){
            sucesso = regimentoStrategy_1.seriar(idAnuncio);
        }
        return sucesso;
    }

    public boolean seriar(int idAnuncio, List<Classificacao> classificacoes) throws SQLException{
        boolean sucesso = false;
        int idRegimento = getAnuncio(idAnuncio).getIdTipoRegimento();
        if (idRegimento == 2){
            sucesso = regimentoStrategy_2.seriar(idAnuncio, classificacoes);
        }
        if (idRegimento == 3){
            sucesso = regimentoStrategy_3.seriar(idAnuncio, classificacoes);
        }
        return sucesso;
    }

    public boolean seriar(int idAnuncio, List<Classificacao> classificacoes, List<String> colaboradores) throws SQLException{
        boolean sucesso = false;
        int idRegimento = getAnuncio(idAnuncio).getIdTipoRegimento();
        if (idRegimento == 2){
            sucesso = regimentoStrategy_2.seriar(idAnuncio, classificacoes, colaboradores);
        }
        if (idRegimento == 3){
            sucesso = regimentoStrategy_3.seriar(idAnuncio, classificacoes);
        }
        return sucesso;
    }

    /**
     * Retorna uma lista de classificações de candidaturas de um processo de seriação
     * @param idSeriacao - id do processo de seriação
     * @return
     * @throws SQLException
     */
    public List<ClassificacaoDTO> getAllBySeriacao(int idSeriacao)throws SQLException{
        List<Classificacao> classificacoes = repositorioClassificacao.getAllBySeriacao(idSeriacao);
        List<ClassificacaoDTO> classificaoesDTO = new ArrayList<>();

        for (Classificacao classificacao : classificacoes) {
            classificaoesDTO.add((ClassificacaoDTO) classificacao.toDTO());
        }
        return classificaoesDTO;
    }


    /**
     * Retorna uma classificação com base no seu id
     * @param idClassificacao - id da classificação
     * @return
     * @throws SQLException
     */
    public ClassificacaoDTO findClassificacaoById(int idClassificacao) throws SQLException{
        Classificacao classificacao = repositorioClassificacao.findById(idClassificacao);

        return (ClassificacaoDTO) classificacao.toDTO();
    }

    /**
     * Retorna uma lista de colaboradores de uma organização
     * @param nifOrganizacacao - nif da organização
     * @return
     * @throws SQLException
     */
    public List<ColaboradorDTO> getAll(String nifOrganizacacao) throws SQLException{
        List<Colaborador> colaboradores = repositorioColaborador.getAll(nifOrganizacacao);
        List<ColaboradorDTO> colaboradoresDTO = new ArrayList<>();

        for(Colaborador colaborador : colaboradores) {
            colaboradoresDTO.add((ColaboradorDTO) colaborador.toDTO());
        }
        return colaboradoresDTO;
    }
   
    /**
     * Retorna uma lista de processos de seriação de um anúncio
     * @param idAnuncio - id do anúncio
     * @return
     * @throws SQLException
     */
    public List<ProcessoSeriacaoDTO> getAllPSByIdAnuncio(int idAnuncio) throws SQLException {
        List<ProcessoSeriacao> processosSeriacao = repositorioSeriacao.getAllByIdAnuncio(idAnuncio);
        List<ProcessoSeriacaoDTO> processosSeriacaoDTO = new ArrayList<>();

        for(ProcessoSeriacao processoSeriacao : processosSeriacao) {
            processosSeriacaoDTO.add((ProcessoSeriacaoDTO) processoSeriacao.toDTO());
        }
        return processosSeriacaoDTO;
    }
}
