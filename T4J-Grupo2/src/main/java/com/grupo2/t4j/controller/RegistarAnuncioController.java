package com.grupo2.t4j.controller;

import com.grupo2.t4j.files.*;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.*;
import com.grupo2.t4j.persistence.database.*;
import com.grupo2.t4j.persistence.inmemory.*;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class RegistarAnuncioController {
    
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
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
    public boolean registarAnuncio(String referenciaTarefa, String nifOrganizacao, Data dtInicioPublicitacao, 

            Data dtFimPublicitacao, Data dtInicioCandidatura, Data dtFimCandidatura, 
            Data dtInicioSeriacao, Data dtFimSeriacao, String idTipoRegimento) throws SQLException {

        Anuncio anuncio = new Anuncio(referenciaTarefa, nifOrganizacao, dtInicioPublicitacao, dtFimPublicitacao, 
                dtInicioCandidatura, dtFimCandidatura, dtInicioSeriacao, dtFimSeriacao, idTipoRegimento);

        return repositorioAnuncio.save(anuncio);
    }

    public List<Anuncio> getAll() {
        return repositorioAnuncio.getAll();
    }

    

}
