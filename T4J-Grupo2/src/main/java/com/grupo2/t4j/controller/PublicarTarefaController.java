/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.Anuncio;
import com.grupo2.t4j.model.Data;
import com.grupo2.t4j.model.DesignacaoSeriacao;
import com.grupo2.t4j.model.TipoRegimento;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAnuncio;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author acris
 */
public class PublicarTarefaController {

    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioAnuncio repositorioAnuncio = fabricaRepositorios.getRepositorioAnuncio();
    

    public boolean publicarTarefa(String referencia, String nifOrganizacao, 
            LocalDate dtInicioPublicitacao, LocalDate dtFimPublicitacao, 
            LocalDate dtInicioCandidaturas, LocalDate dtFimCandidaturas, 
            LocalDate dtInicioSeriacao, LocalDate dtFimSeriacao, 
            String idTipoRegimento) throws SQLException {
        
        Anuncio anuncio = new Anuncio(referencia, nifOrganizacao, 
            data(dtInicioPublicitacao), data(dtFimPublicitacao), 
            data(dtInicioCandidaturas), data(dtFimCandidaturas), 
            data(dtInicioSeriacao), data(dtFimSeriacao), idTipoRegimento);
        
        return repositorioAnuncio.save(anuncio);
    }
    
    /**
     * Transforma um objeto LocalDate num Objeto Data
     * @param localDate
     * @return data
     */
    public Data data(LocalDate localDate){
        Data data = new Data(localDate);
        return data;
    }
    
    public List<Anuncio> getAll() {
        return repositorioAnuncio.getAll();
    }
    
    public List<TipoRegimento> getAllRegimento()throws SQLException {
        return repositorioAnuncio.getAllRegimento();
    }
    
    
    
}
