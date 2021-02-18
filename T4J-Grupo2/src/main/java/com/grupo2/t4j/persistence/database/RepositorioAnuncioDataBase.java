/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.database;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.AnuncioDuplicadoException;
import com.grupo2.t4j.model.Anuncio;
import com.grupo2.t4j.model.Data;
import com.grupo2.t4j.persistence.RepositorioAnuncio;

import java.util.ArrayList;

public class RepositorioAnuncioDataBase implements RepositorioAnuncio {

    /**
     * Define uma instância estática do Repositório em que estão
     * registados todos os Anúncios
     */
    private static RepositorioAnuncioDataBase repositorioAnuncioDataBase;

    /**
     * Inicializa o Repositório de Anúncios
     */
    RepositorioAnuncioDataBase(){    }

    /**
     * Devolve uma instância estática do Repositório de Anúncios
     *
     * @return RepositorioAnuncioDatabase
     */
    public static RepositorioAnuncioDataBase getInstance(){
        if(repositorioAnuncioDataBase == null) {
            repositorioAnuncioDataBase = new RepositorioAnuncioDataBase();
        }
        return repositorioAnuncioDataBase;
    }
    
    @Override
    public void save(String idAnuncio, Data dtInicioPublicitacao, Data dtFimPublicitacao, Data
             dtInicioCandidatura, Data dtFimCandidatura, Data dtInicioSeriacao,
                      Data dtFimSeriacao) throws AnuncioDuplicadoException {

    }
    
    @Override
    public boolean save(Anuncio anuncio) throws AnuncioDuplicadoException{
        return false;
    }
    
    @Override
    public Anuncio findById(String idAnuncio) {
        return null;
    }
    
    @Override
    public ArrayList<Anuncio> getAll() {
        return null;
    }
}
