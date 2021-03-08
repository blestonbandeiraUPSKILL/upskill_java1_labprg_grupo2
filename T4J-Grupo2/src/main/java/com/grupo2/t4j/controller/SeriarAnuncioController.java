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
    
    public List<String> getAllRefTarefasASeriar(List<String> referenciasTarefa, String nifOrganizacao) throws SQLException{
        return repositorioAnuncio.getAllRefTarefasASeriar(referenciasTarefa, nifOrganizacao);
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
    
    public TipoRegimento findRegimentoById(int idTipoRegimento) throws SQLException{
        return repositorioTipoRegimento.findById(idTipoRegimento);
    }
    
    public List<Candidatura> getAllByIdAnuncio(int idAnuncio) throws SQLException{
        return repositorioCandidatura.getAllByIdAnuncio(idAnuncio);
    }
    
    public boolean saveSeriacao(int idAnuncio)throws SQLException{
        return repositorioSeriacao.save(idAnuncio);
    }
    
    public int getIdSeriacao(int idAnuncio)throws SQLException{
        return repositorioSeriacao.findByAnuncio(idAnuncio).getIdSeriacao();
    }
    
    public boolean saveClassificacao(int posicao, int idSeriacao, int idCandidatura) throws SQLException{
        return repositorioClassificacao.save(posicao, idSeriacao, idCandidatura);
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
    
    public ArrayList<String> getAllEmailsByOrganizacao(String nifOrganizacao) throws SQLException{
        return repositorioColaborador.getAllEmailsByOrganizacao(nifOrganizacao);
    }
    
    public boolean update(String emailColaborador, int idSeriacao) throws SQLException{
        return repositorioColaboradorSeriacao.update(emailColaborador, idSeriacao);
    }
    
    
    
}
