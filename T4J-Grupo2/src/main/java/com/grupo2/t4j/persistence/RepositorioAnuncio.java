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

import com.grupo2.t4j.exception.AnuncioDuplicadoException;
import com.grupo2.t4j.model.Anuncio;
import com.grupo2.t4j.model.TipoRegimento;
import com.grupo2.t4j.model.TipoStatusAnuncio;

import java.sql.SQLException;
import java.util.ArrayList;
public interface RepositorioAnuncio {

    boolean save(String referenciaTarefa, String nifOrganizacao, String dtInicioPublicitacao, String dtFimPublicitacao, String
             dtInicioCandidatura, String dtFimCandidatura, String dtInicioSeriacao,
               String dtFimSeriacao, int idTipoRegimento) throws AnuncioDuplicadoException, SQLException;


     boolean save(Anuncio anuncio) throws SQLException;


    Anuncio findById(String idAnuncio);

    ArrayList<Anuncio> getAll();
    
    Anuncio findAnuncioByIdTarefa(String referenciaTarefa, String nifOrganizacao)throws SQLException;

    public ArrayList<TipoRegimento> getAllRegimento()throws SQLException;
    
    public ArrayList<Anuncio> getAllByStatus(TipoStatusAnuncio status) throws SQLException;
}
