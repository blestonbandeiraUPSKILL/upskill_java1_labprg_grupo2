/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.domain.Anuncio;
import com.grupo2.t4j.domain.Tarefa;
import com.grupo2.t4j.domain.TipoRegimento;
import com.grupo2.t4j.exception.AnuncioDuplicadoException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public interface RepositorioAnuncio {

    boolean save(String referenciaTarefa, String nifOrganizacao, String dtInicioPublicitacao, String dtFimPublicitacao, String
             dtInicioCandidatura, String dtFimCandidatura, String dtInicioSeriacao,
               String dtFimSeriacao, int idTipoRegimento) throws AnuncioDuplicadoException, SQLException;


    boolean save(Anuncio anuncio) throws SQLException;

    ArrayList<Anuncio> getAll() throws SQLException;
    
    Anuncio findAnuncioByIdTarefa(String referenciaTarefa, String nifOrganizacao)throws SQLException;

    ArrayList<TipoRegimento> getAllRegimento()throws SQLException;
    
    List<String> getAllRefTarefasTipoRegimento(List<String> referenciasTarefa, String emailColaborador, int idTipoRegimento) throws SQLException;
    
    List<String> getAllRefTarefasNaoSeriadas(List<String> referenciasTarefa, String nifOrganizacao) throws SQLException;
    
    List<Tarefa> getAllRefTarefasASeriar(List<String> referenciasTarefa, String nifOrganizacao, String emailColaborador) throws SQLException;

    List<Integer> getAnunciosSeriados(String nifOrganizacao) throws SQLException;

    Anuncio getAnuncio(int idAnuncio) throws SQLException;

    boolean findAnuncioByEmailFreelancer(String freelancer, String emailFreelancer) throws SQLException;
}
