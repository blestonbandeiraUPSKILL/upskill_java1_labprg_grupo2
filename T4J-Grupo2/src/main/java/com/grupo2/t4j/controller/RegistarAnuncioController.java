package com.grupo2.t4j.controller;

import com.grupo2.t4j.files.FicheiroRepositorioAnuncio;
import com.grupo2.t4j.model.Anuncio;
import com.grupo2.t4j.model.Data;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAnuncio;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;

import java.sql.SQLException;
import java.util.List;
public class RegistarAnuncioController {
    
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioAnuncio repositorioAnuncio = fabricaRepositorios.getRepositorioAnuncio();
    
    private FicheiroRepositorioAnuncio ficheiroAnuncio;


   /**
     * Registar anúncio boolean
     *
     * @param referenciaTarefa as referência da tarefa
     * @param nifOrganizacao as nif da organização
     * @param dtInicioPublicitacao as data de início de publicitação
     * @param dtFimPublicitacao as data de fim de publicitação
     * @param dtInicioCandidatura as data de início de candidatura
     * @param dtFimCandidatura as data de término de candidatura
     * @param dtInicioSeriacao as data de início de seriação
     * @param dtFimSeriacao as data de término de seriação
     * @param idTipoRegimento as tipo de regimento
     * @return boolean
     */
    public boolean registarAnuncio(String referenciaTarefa, String nifOrganizacao, String dtInicioPublicitacao,
                                   String dtFimPublicitacao, String dtInicioCandidatura, String dtFimCandidatura,
                                   String dtInicioSeriacao, String dtFimSeriacao, int idTipoRegimento) throws SQLException {

        Anuncio anuncio = new Anuncio(referenciaTarefa, nifOrganizacao, dtInicioPublicitacao, dtFimPublicitacao, 
                dtInicioCandidatura, dtFimCandidatura, dtInicioSeriacao, dtFimSeriacao, idTipoRegimento);

        return repositorioAnuncio.save(anuncio);
    }

    public List<Anuncio> getAll() {
        return repositorioAnuncio.getAll();
    }


    public Anuncio getAnuncio(int idAnuncio) throws SQLException {
        return repositorioAnuncio.getAnuncio(idAnuncio);
    }
}
