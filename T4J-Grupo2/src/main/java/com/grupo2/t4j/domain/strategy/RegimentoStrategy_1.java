/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain.strategy;

/**
 *
 * @author CAD
 */
import com.grupo2.t4j.domain.*;
import com.grupo2.t4j.persistence.*;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegimentoStrategy_1 implements RegimentoStrategy{
    
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioTarefa repositorioTarefa = fabricaRepositorios.getRepositorioTarefa();
    private RepositorioCandidatura repositorioCandidatura = fabricaRepositorios.getRepositorioCandidatura();
    private RepositorioClassificacao repositorioClassificacao = fabricaRepositorios.getRepositorioClassificacao();
    private RepositorioSeriacao repositorioSeriacao = fabricaRepositorios.getRepositorioSeriacao();
    
    @Override
    public boolean seriar(int idAnuncio) throws SQLException{
        List<Candidatura> candidaturas = repositorioCandidatura.getAllByIdAnuncio(idAnuncio);
        List<Candidatura> candidaturasOrdenadas = repositorioCandidatura.ordenarByValor(candidaturas);
        boolean seriacaoCriada = repositorioSeriacao.save(idAnuncio);
        if(seriacaoCriada){
            int idSeriacao = repositorioSeriacao.getProcessoSeriacaoByAnuncio(idAnuncio).getIdSeriacao();
            boolean sucesso = saveClassificacaoAutomatica(candidaturasOrdenadas, idSeriacao);
            if(sucesso){
                return true;
            }           
        }
        return false;
    }
    
    public boolean saveClassificacaoAutomatica(List<Candidatura> candidaturas, int idSeriacao) throws SQLException{
        int posicao = 1;
        boolean adicionou = false;
        for(Candidatura c : candidaturas){
            adicionou = repositorioClassificacao.save(posicao, idSeriacao, c.getIdCandidatura());
            posicao++;
        }
        return adicionou;
    }
    
    @Override
    public boolean atribuir(int idAnuncio){
        return false;
    }
    
}
