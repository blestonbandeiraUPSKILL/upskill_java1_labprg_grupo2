/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.domain.strategy;

import com.grupo2.t4j.domain.Classificacao;
import com.grupo2.t4j.domain.Colaborador;
import com.grupo2.t4j.domain.RegimentoStrategy;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioCandidatura;
import com.grupo2.t4j.persistence.RepositorioClassificacao;
import com.grupo2.t4j.persistence.RepositorioSeriacao;
import com.grupo2.t4j.persistence.RepositorioTarefa;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author CAD
 */
public class RegimentoStrategy_3 implements RegimentoStrategy{

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioTarefa repositorioTarefa = fabricaRepositorios.getRepositorioTarefa();
    private RepositorioCandidatura repositorioCandidatura = fabricaRepositorios.getRepositorioCandidatura();
    private RepositorioClassificacao repositorioClassificacao = fabricaRepositorios.getRepositorioClassificacao();
    private RepositorioSeriacao repositorioSeriacao = fabricaRepositorios.getRepositorioSeriacao();

    @Override
    public boolean seriar(int idAnuncio){

        return false;
    }

    @Override
    public boolean seriar(int idAnuncio, List<Classificacao> classificacoes) throws SQLException {
        boolean seriacaoCriada = repositorioSeriacao.save(idAnuncio);
        boolean adicionou = false;
        if(seriacaoCriada) {
            int idSeriacao = repositorioSeriacao.getProcessoSeriacaoByAnuncio(idAnuncio).getIdSeriacao();
            for(Classificacao c : classificacoes){
                adicionou = repositorioClassificacao.save(c.getPosicaoFreelancer(), idSeriacao,
                        c.getIdCandidatura());
            }
        }
        return adicionou;
    }

    @Override
    public boolean seriar(int idAnuncio, List<Classificacao> classificacoes, List<String> colaboradores) throws SQLException{
        return false;
    }
    
    @Override
    public boolean atribuir(int idAnuncio){
        return false;
    }
}
