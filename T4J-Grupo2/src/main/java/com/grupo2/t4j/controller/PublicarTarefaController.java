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

public class PublicarTarefaController {

    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioAnuncio repositorioAnuncio = fabricaRepositorios.getRepositorioAnuncio();
    

   /**
     * Publicar tarefa boolean.
     *
     * @param referencia as referência da tarefa
     * @param nifOrganizacao as nif da organização
     * @param dtInicioPublicitacao as data de início de publicitação
     * @param dtFimPublicitacao as data de fim de publicitação
     * @param dtInicioCandidaturas as data de início de candidaturas
     * @param dtFimCandidaturas as data de término de candidaturas
     * @param dtInicioSeriacao as data de início de seriação
     * @param dtFimSeriacao as data de término de seriação
     * @param idTipoRegimento as tipo de regimento
     * @return boolean
     */
    public boolean publicarTarefa(String referencia, String nifOrganizacao, 
            LocalDate dtInicioPublicitacao, LocalDate dtFimPublicitacao, 
            LocalDate dtInicioCandidaturas, LocalDate dtFimCandidaturas, 
            LocalDate dtInicioSeriacao, LocalDate dtFimSeriacao, 
            String idTipoRegimento) throws SQLException {
        
        
        return repositorioAnuncio.save(referencia, nifOrganizacao, dtInicioPublicitacao, dtFimPublicitacao, dtInicioCandidaturas,
                dtFimCandidaturas, dtInicioSeriacao, dtFimSeriacao, idTipoRegimento);
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
