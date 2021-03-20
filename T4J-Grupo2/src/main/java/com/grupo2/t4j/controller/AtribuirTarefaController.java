package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.*;
import com.grupo2.t4j.domain.strategy.*;
import com.grupo2.t4j.dto.AnuncioDTO;
import com.grupo2.t4j.dto.AtribuicaoDTO;
import com.grupo2.t4j.dto.FreelancerDTO;
import com.grupo2.t4j.dto.TarefaDTO;
import com.grupo2.t4j.persistence.*;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CAD
 */
public class AtribuirTarefaController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioAnuncio repositorioAnuncio = fabricaRepositorios.getRepositorioAnuncio();
    private RepositorioAtribuicao repositorioAtribuicao = fabricaRepositorios.getRepositorioAtribuicao();
    private RepositorioCandidatura repositorioCandidatura = fabricaRepositorios.getRepositorioCandidatura();
    private RepositorioClassificacao repositorioClassificacao = fabricaRepositorios.getRepositorioClassificacao();
    private RepositorioFreelancer repositorioFreelancer = fabricaRepositorios.getRepositorioFreelancer();
    private RepositorioTarefa repositorioTarefa = fabricaRepositorios.getRepositorioTarefa();
    private RepositorioSeriacao repositorioSeriacao = fabricaRepositorios.getRepositorioSeriacao();
    private RepositorioTipoRegimento repositorioTipoRegimento = fabricaRepositorios.getRepositorioTipoRegimento();
    private RegimentoStrategy_1 regimentoStrategy_1 = new RegimentoStrategy_1();
    private RegimentoStrategy_2 regimentoStrategy_2 = new RegimentoStrategy_2();
    private RegimentoStrategy_3 regimentoStrategy_3 = new RegimentoStrategy_3();

    public AtribuirTarefaController(){

    }

    public AtribuicaoDTO findAtribuicaoByTarefa(String refTarefa) throws SQLException{
        return repositorioAtribuicao.findAtribuicaoByTarefa(refTarefa);
    }

    public List<AtribuicaoDTO> getAllByOrganizacao(String nifOrganizacao) throws SQLException{
        return repositorioAtribuicao.getAllByOrganizacao(nifOrganizacao);
    }

    public ProcessoSeriacao getProcessoSeriacaoByAnuncio (int idAnuncio) throws SQLException{
        return repositorioSeriacao.getProcessoSeriacaoByAnuncio(idAnuncio);
    }

    public TarefaDTO findTarefa(int idAnuncio) throws SQLException {
        Tarefa tarefa = repositorioTarefa.findTarefa(idAnuncio);
        return (TarefaDTO) tarefa.toDTO();
    }

    /**
     * Devolve um freelancer a partir do seu email
     * @param emailFree
     * @return
     * @throws SQLException
     */
    public FreelancerDTO findByEmail(String emailFree) throws SQLException{
        Freelancer freelancer = repositorioFreelancer.findByEmail(emailFree);
        return (FreelancerDTO) freelancer.toDTO();
    }

    /**
     * Retorna o anúncio de acordo com o id do anúncio
     * @param idAnuncio - id do anúncio
     * @return
     * @throws SQLException
     */
    public AnuncioDTO getAnuncio(int idAnuncio) throws SQLException {
        Anuncio anuncio = repositorioAnuncio.getAnuncio(idAnuncio);
        return (AnuncioDTO) anuncio.toDTO();
    }

    public boolean atribuir(int idAnuncio) throws SQLException{
        boolean sucesso = false;
        int idRegimento = getAnuncio(idAnuncio).getIdTipoRegimento();
        if (idRegimento == 1){
            sucesso = regimentoStrategy_1.atribuir(idAnuncio);
        }
        return sucesso;
    }

    public boolean atribuir(int idAnuncio, String dataInicioTarefa) throws SQLException{
        boolean sucesso = false;
        int idRegimento = getAnuncio(idAnuncio).getIdTipoRegimento();
        if (idRegimento == 1){
            sucesso = regimentoStrategy_1.atribuir(idAnuncio, dataInicioTarefa);
        }
        return sucesso;
    }

    public List<Integer> getAnunciosSeriados(String nifOrganizacao) throws SQLException {
        return repositorioAnuncio.getAnunciosSeriados(nifOrganizacao);
    }

    public List<AnuncioDTO> getAnunciosSeriadosNaoAtribuidos(String nifOrganizacao) throws SQLException{
        List<Integer> idsAnunciosSeriados = getAnunciosSeriados(nifOrganizacao);

        List<AnuncioDTO> anunciosSeriadosNaoAtribuidos = new ArrayList<>();
        for(int i = 0; i < idsAnunciosSeriados.size();i++){
            String refTarefa = getAnuncio(idsAnunciosSeriados.get(i)).getReferenciaTarefa();
            if(findAtribuicaoByTarefa(refTarefa) == null){
                anunciosSeriadosNaoAtribuidos.add(getAnuncio(idsAnunciosSeriados.get(i)));
            }
        }
        return anunciosSeriadosNaoAtribuidos;
    }

    public String getEmailFreelancer(int idAnuncio) throws SQLException{
        int tipoRegimento = getAnuncio(idAnuncio).getIdTipoRegimento();
        int idSeriacao = getProcessoSeriacaoByAnuncio(idAnuncio).getIdSeriacao();
        List<Classificacao> classificacoes = repositorioClassificacao.getAllBySeriacao(idSeriacao);

        int idCandidatura = 0;
        if(tipoRegimento == 1){
            if(classificacoes.size() > 1) {
                for (int i = 0; i < classificacoes.size(); i++) {
                    if (classificacoes.get(i).getPosicaoFreelancer() == 2) {
                        idCandidatura = classificacoes.get(i).getIdCandidatura();
                    }
                }
            }
            else{
                idCandidatura = classificacoes.get(0).getIdCandidatura();
            }
        }
        else{
            for (int i = 0; i < classificacoes.size(); i++) {
                if (classificacoes.get(i).getPosicaoFreelancer() == 1) {
                    idCandidatura = classificacoes.get(i).getIdCandidatura();
                }
            }
        }
        return repositorioCandidatura.findById(idCandidatura).getEmailFreelancer();
    }


}
