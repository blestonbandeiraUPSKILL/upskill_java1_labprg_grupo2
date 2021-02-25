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

import com.grupo2.t4j.model.*;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public interface RepositorioTarefa {


    void save(String codigoAreaActividade, String codigoCategoriaTarefa,
                             String referencia, String designacao,
                             String descInformal, String descTecnica,
                             int duracao, double custo, String nifOrganizacao, 
                             String emailColaborador);

    boolean save(Tarefa tarefa) throws SQLException;

    Tarefa findByReferenciaENIF(String referencia, String NIF)throws SQLException;


    List<Tarefa> findByCategoria (String codigoCategoria) throws SQLException;


    /**
     * Devolve a lista de Tarefas
     *
     * @return
     */
    ArrayList<Tarefa> getAllOrganizacao(String nifOrganizacao) throws SQLException;
    
    ArrayList<Tarefa> getAll() throws SQLException;


    //getAllByOrgPublicadas, getAllByOrgNaoPublicadas, getAllByOrg

    //update, delete

    List<Tarefa> findByColaboradorENif(String email, String nifOrganizacao) throws SQLException;

    List<Anuncio> findTarefasComAnuncio(List<String> email, String nifOrganizacao)throws SQLException;
    
    List<Anuncio> findAnuncioByTarefa(String referencia, String nif) throws SQLException;
    
    List<Tarefa> findTarefasSemAnuncio(String email, String nifOrganizacao) throws SQLException;

    List<String> findReferenciaTarefa(String nifOrganizacao, String email) throws SQLException;
}
