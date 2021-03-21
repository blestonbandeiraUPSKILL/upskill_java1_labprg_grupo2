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
    private RepositorioAnuncio repositorioAnuncio = fabricaRepositorios.getRepositorioAnuncio();
    private RepositorioAtribuicao repositorioAtribuicao = fabricaRepositorios.getRepositorioAtribuicao();
    private RepositorioCandidatura repositorioCandidatura = fabricaRepositorios.getRepositorioCandidatura();
    private RepositorioClassificacao repositorioClassificacao = fabricaRepositorios.getRepositorioClassificacao();
    private RepositorioSeriacao repositorioSeriacao = fabricaRepositorios.getRepositorioSeriacao();
    private RepositorioTarefa repositorioTarefa = fabricaRepositorios.getRepositorioTarefa();

    //private CodigoAtribuicao codigoAtribuicao = new CodigoAtribuicao();

    
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

    @Override
    public boolean seriar(int idAnuncio, List<Classificacao> classificacoes) throws SQLException{
        return false;
    }

    @Override
    public boolean seriar(int idAnuncio, List<Classificacao> classificacoes, List<String> colaboradores) throws SQLException{
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
        boolean adicionou = false;
        try {

            Tarefa tarefa = repositorioTarefa.findTarefa(idAnuncio);
            int idSeriacao = repositorioSeriacao.findProcessoSeriacaoByIdAnuncio(idAnuncio).getIdSeriacao();
            List<Classificacao> classificacoes = repositorioClassificacao.getAllBySeriacao(idSeriacao);
            int idCandidatura = 0;
            if(classificacoes.size() > 1) {
                for (int i = 0; i < classificacoes.size(); i++) {
                    if (classificacoes.get(i).getPosicaoFreelancer() == 2) {
                        idCandidatura = classificacoes.get(i).getIdCandidatura();
                        i = classificacoes.size();
                    }
                }
            }
            else{
                idCandidatura = classificacoes.get(0).getIdCandidatura();
                }
            Candidatura candidatura = repositorioCandidatura.findById(idCandidatura);

            Anuncio anuncio = repositorioAnuncio.getAnuncio(idAnuncio);
            //String codigo = codigoAtribuicao.gerarCodigo();

            adicionou = repositorioAtribuicao.save(tarefa.getNifOrganizacao(), tarefa.getReferencia(), idAnuncio,
                    candidatura.getEmailFreelancer(), anuncio.getDtFimPub(), idCandidatura);
            return adicionou;
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return adicionou;
    }

    @Override
    public boolean atribuir(int idAnuncio, String dataInicioTarefa){
        boolean adicionou = false;
        try {
            Tarefa tarefa = repositorioTarefa.findTarefa(idAnuncio);
            int idSeriacao = repositorioSeriacao.findProcessoSeriacaoByIdAnuncio(idAnuncio).getIdSeriacao();
            List<Classificacao> classificacoes = repositorioClassificacao.getAllBySeriacao(idSeriacao);
            int idCandidatura = 0;
            if(classificacoes.size() > 1) {
                for (int i = 0; i < classificacoes.size(); i++) {
                    if (classificacoes.get(i).getPosicaoFreelancer() == 2) {
                        idCandidatura = classificacoes.get(i).getIdCandidatura();
                        i = classificacoes.size();
                    }
                }
            }
            else{
                idCandidatura = classificacoes.get(0).getIdCandidatura();
            }
            Candidatura candidatura = repositorioCandidatura.findById(idCandidatura);

            //String codigo = codigoAtribuicao.gerarCodigo();

            adicionou = repositorioAtribuicao.save(tarefa.getNifOrganizacao(), tarefa.getReferencia(),
                    idAnuncio, candidatura.getEmailFreelancer(), dataInicioTarefa, idCandidatura);
            return adicionou;
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return adicionou;
    }
    
}
