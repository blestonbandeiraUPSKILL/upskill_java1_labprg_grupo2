package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.Anuncio;
import com.grupo2.t4j.domain.Data;
import com.grupo2.t4j.domain.TipoRegimento;
import com.grupo2.t4j.dto.AnuncioDTO;
import com.grupo2.t4j.dto.TipoRegimentoDTO;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioAnuncio;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PublicarTarefaController {

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
                                  String dtInicioPublicitacao, String dtFimPublicitacao,
                                  String dtInicioCandidaturas, String dtFimCandidaturas,
                                  String dtInicioSeriacao, String dtFimSeriacao,
                                  int idTipoRegimento) throws SQLException {
        
        
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
    
    /**
     * Devolve uma lista de todos os anuncios
     * @return
     * @throws SQLException 
     */
    public List<AnuncioDTO> getAll() throws SQLException {
        List<Anuncio> anuncios = repositorioAnuncio.getAll();
        List<AnuncioDTO> anunciosDTO = new ArrayList<>();

        for (Anuncio anuncio : anuncios) {
            anunciosDTO.add((AnuncioDTO) anuncio.toDTO());
        }

        return anunciosDTO;
    }
    
    /**
    * Devolve uma lista com todos os tipos de regimento
    * @return
    * @throws SQLException
    */
    public List<TipoRegimentoDTO> getAllRegimento()throws SQLException {
        List<TipoRegimento> tiposRegimento = repositorioAnuncio.getAllRegimento();
        List<TipoRegimentoDTO> tiposRegimentoDTO = new ArrayList<>();

        for(TipoRegimento tipoRegimento : tiposRegimento) {
            tiposRegimentoDTO.add((TipoRegimentoDTO) tipoRegimento.toDTO());
        }
        return tiposRegimentoDTO;
    }
    
    
    
}
