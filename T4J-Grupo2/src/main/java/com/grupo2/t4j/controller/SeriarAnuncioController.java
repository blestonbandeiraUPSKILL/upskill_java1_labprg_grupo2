/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.*;
import com.grupo2.t4j.persistence.*;
import com.grupo2.t4j.persistence.database.*;
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
    
    
    public SeriarAnuncioController(){
        
    }
    
    public List<String> getReferenciasTarefas(String nifOrganizacao) throws SQLException{
        return repositorioTarefa.findReferenciaTarefa(nifOrganizacao);
    }
    
    public List<Tarefa> findTarefasPublicadas(List<String> referenciasTarefa, 
            String nifOrganizacao, String emailColaborador)throws SQLException{
        return repositorioTarefa.findTarefasPublicadas(referenciasTarefa, nifOrganizacao, emailColaborador);
    }
    
    public List<String> getReferenciasTarefas(List<Tarefa> listaTarefas) throws SQLException{
        return repositorioTarefa.getReferenciasTarefas(listaTarefas);
    }
    
    public List<String> getAllRefTarefasTipoRegimento(List<String> referenciasTarefa, String emailColaborador, int idTipoRegimento) throws SQLException{
        return repositorioAnuncio.getAllRefTarefasTipoRegimento(referenciasTarefa, emailColaborador, idTipoRegimento);
    }
    
    public List<Tarefa> getAllRefTarefasASeriar(List<String> referenciasTarefa, String nifOrganizacao, String emailColaborador) throws SQLException{
        return repositorioAnuncio.getAllRefTarefasASeriar(referenciasTarefa, nifOrganizacao, emailColaborador);
    }
    
    public int getIdAnuncioByIdTarefa(String referenciaTarefa, String nifOrganizacao) throws SQLException{
        return repositorioAnuncio.findAnuncioByIdTarefa(referenciaTarefa, nifOrganizacao).getIdAnuncio();
    }
    
    public Anuncio getAnuncio(int idAnuncio) throws SQLException {
        return repositorioAnuncio.getAnuncio(idAnuncio);
    }
    
    public List<TipoRegimento> getAllRegimento()throws SQLException {
        return repositorioAnuncio.getAllRegimento();
    }
    
    public List<String> getAllRefTarefasNaoSeriadas(List<String> referenciasTarefa, String nifOrganizacao) throws SQLException{
        return repositorioAnuncio.getAllRefTarefasNaoSeriadas(referenciasTarefa, nifOrganizacao);
    }
    
    public TipoRegimento findRegimentoById(int idTipoRegimento) throws SQLException{
        return repositorioTipoRegimento.findById(idTipoRegimento);
    }
    
    public Candidatura findById(int idCandidatura) throws SQLException {
        return repositorioCandidatura.findById(idCandidatura);
    }
    
    public List<Candidatura> getAllByIdAnuncio(int idAnuncio) throws SQLException{
        return repositorioCandidatura.getAllByIdAnuncio(idAnuncio);
    }
    
    public List<Candidatura> ordenarByValor(List<Candidatura> candidaturas) throws SQLException{
        return repositorioCandidatura.ordenarByValor(candidaturas);
    }
    
    public List<Candidatura> ordenarByIdCandidatura(List<Candidatura> candidaturas) throws SQLException{
        return repositorioCandidatura.ordenarByIdCandidatura(candidaturas);
    }
    
    public boolean saveSeriacao(int idAnuncio)throws SQLException{
        return repositorioSeriacao.save(idAnuncio);
    }
    
    public int getIdSeriacao(int idAnuncio)throws SQLException{
        return repositorioSeriacao.findByAnuncio(idAnuncio).getIdSeriacao();
    }
    
    public ProcessoSeriacao findSeriacaoByAnuncio(int idAnuncio) throws SQLException{
        return repositorioSeriacao.findByAnuncio(idAnuncio);
    }
    
    public boolean saveClassificacao(int posicao, int idSeriacao, int idCandidatura) throws SQLException{
        return repositorioClassificacao.save(posicao, idSeriacao, idCandidatura);
    }
    
    public boolean saveClassificacaoAutomatica(List<Candidatura> candidaturasOrdenadas, int idSeriacao) throws SQLException{
        int posicao = 1;
        boolean adicionou = false;
        for(Candidatura c : candidaturasOrdenadas){
            adicionou = repositorioClassificacao.save(posicao, idSeriacao, c.getIdCandidatura());
            posicao++;
        }
        return adicionou;
    }
    
    public List<Classificacao> getAllBySeriacao(int idSeriacao)throws SQLException{
        return repositorioClassificacao.getAllBySeriacao(idSeriacao);
    }
    
    public Candidatura findCandidaturaById(int idCandidatura) throws SQLException{
        return repositorioCandidatura.findById(idCandidatura);
    }
    
    public Classificacao findClassificacaoById(int idClassificacao) throws SQLException{
        return repositorioClassificacao.findById(idClassificacao);
    }
    
    public ArrayList<String> getAllEmailsAlfByOrganizacao(String nifOrganizacao) throws SQLException{
        return repositorioColaborador.getAllEmailsAlfByOrganizacao(nifOrganizacao);
    }
    
    public boolean update(String emailColaborador, int idSeriacao) throws SQLException{
        return repositorioColaboradorSeriacao.update(emailColaborador, idSeriacao);
    }
    
    public boolean saveListaColaboradores(List<String> emailColaboradores, int idSeriacao) throws SQLException{
        boolean adicionou = false;
        for(String email : emailColaboradores){
            adicionou = repositorioColaboradorSeriacao.update(email, idSeriacao);
        }
        return adicionou;
    }
    
    
    
}
