package com.grupo2.t4j.controller;

import com.grupo2.t4j.domain.GrauProficiencia;
import com.grupo2.t4j.domain.Tarefa;
import com.grupo2.t4j.dto.GrauProficienciaDTO;
import com.grupo2.t4j.dto.TarefaDTO;
import com.grupo2.t4j.persistence.*;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistarTarefaController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioTarefa repositorioTarefa = fabricaRepositorios.getRepositorioTarefa();
    private RepositorioGrauProficiencia repositorioGrauProficiencia = fabricaRepositorios.getRepositorioGrauProficiencia();
    private RepositorioFreelancer repositorioFreelancer = fabricaRepositorios.getRepositorioFreelancer();
    private RepositorioCaracterizacaoCT repositorioCaracterizacaoCT = fabricaRepositorios.getRepositorioCaracterizacaoCT();
    private RepositorioAnuncio repositorioAnuncio = fabricaRepositorios.getRepositorioAnuncio();

    public RegistarTarefaController() throws SQLException {
    }

    /**
     * Devolve uma lista de todas as organizações
     * @param nifOrganizacao
     * @return
     * @throws SQLException 
     */
    public List<TarefaDTO> getAllOrganizacao(String nifOrganizacao) throws SQLException {
        List<Tarefa> tarefas = repositorioTarefa.getAllOrganizacao(nifOrganizacao);
        List<TarefaDTO> tarefasDTO = new ArrayList<>();

        for(Tarefa tarefa : tarefas) {
            tarefasDTO.add((TarefaDTO) tarefa.toDTO());
        }
        return tarefasDTO;
    }

    /**
    * Regista nova tarefa boolean
    *
    * @param codigoCategoriaTarefa as código da categoria da tarefa
    * @param referencia as referência da tarefa
    * @param designacao as designação da tarefa
    * @param descInformal as descrição informal
    * @param descTecnica as descrição técnica
    * @param duracao as tempo estimado de realização da tarefa em horas
    * @param custo as preço pela tarefa
    * @param nifOrganizacao as nif da organização
    * @param emailColaborador  as email do colaborador
    * @return boolean
    */
    public boolean registarTarefa( String codigoCategoriaTarefa,
                             String referencia, String designacao,
                             String descInformal, String descTecnica,
                             int duracao, double custo, String nifOrganizacao, String emailColaborador) throws SQLException{

        Tarefa tarefa = new Tarefa(nifOrganizacao, referencia, designacao, descInformal,
                descTecnica, duracao, custo,
                codigoCategoriaTarefa,  emailColaborador);

        return repositorioTarefa.save(tarefa);
    }
    
    /**
     * Devolve uma lista de tarefas publicadas
     * @param nifOrganizacao
     * @return
     * @throws SQLException 
     */
    public List<TarefaDTO> findTarefasPublicadas(String nifOrganizacao) throws SQLException{
        List<Tarefa> tarefas = repositorioTarefa.findTarefasPublicadas(nifOrganizacao);
        List<TarefaDTO> tarefasDTO = new ArrayList<>();

        for(Tarefa tarefa : tarefas) {
            tarefasDTO.add((TarefaDTO) tarefa.toDTO());
        }
        return tarefasDTO;
    }
    
    /**
     * Devolve uma lista de tarefas nao publicadas
     * @param email
     * @param nifOrganizacao
     * @return
     * @throws SQLException 
     */
    public List<TarefaDTO> findTarefasNaoPublicadas(String email, String nifOrganizacao) throws SQLException{
        List<Tarefa> tarefas = repositorioTarefa.findTarefasNaoPublicadas(email, nifOrganizacao);
        List<TarefaDTO> tarefasDTO = new ArrayList<>();

        for(Tarefa tarefa : tarefas) {
            tarefasDTO.add((TarefaDTO) tarefa.toDTO());
        }
        return tarefasDTO;
    }

    /**
     * Devolve uma tarefa a partir do id do seu anuncio
     * @param idAnuncio
     * @return
     * @throws SQLException 
     */
    public TarefaDTO findTarefa(int idAnuncio) throws SQLException {
        Tarefa tarefa = repositorioTarefa.findTarefa(idAnuncio);
        return (TarefaDTO) tarefa.toDTO();
    }

    /**
     * Devolve todas as tarefas publicadas
     * @return
     * @throws SQLException 
     */
    public List<Tarefa> getAllTarefasPublicadas() throws SQLException {

        return repositorioTarefa.getAllTarefasPublicadas();
    }

    /**
     * Devolve o id do anuncio de uma tarefa
     * @param nifOrganizacao
     * @param referenciaTarefa
     * @return
     * @throws SQLException 
     */
    public int findIdAnuncio(String nifOrganizacao, String referenciaTarefa) throws SQLException {
        return repositorioTarefa.findIdAnuncio(nifOrganizacao, referenciaTarefa);
    }

    /**
     * Devolve os graus de proficiencia de um freelancer
     * @param emailFreelancer
     * @return
     * @throws SQLException 
     */
    public List<GrauProficiencia> getAllGrausFreelancer(String emailFreelancer) throws SQLException {
        return repositorioGrauProficiencia.getAllGrausFreelancer(emailFreelancer);
    }

    /**
     * Devolve uma lista de tarefas com os respetivos graus de proficiencia necessarios
     * @return
     * @throws SQLException 
     */
    public List<Tarefa> getAllGrausTarefasPublicadas() throws SQLException {

        List<Tarefa> tarefasPublicadas = getAllTarefasPublicadas();
        return repositorioGrauProficiencia.getAllGrausTarefasPublicadas(tarefasPublicadas);

    }

    /**
     * Compara os graus de proficiencia de uma lista de tarefas com os graus de proficiencia
     * dos freelancers e devolve uma lista de tarefas compativeis
     * @param emailFreelancer
     * @return
     * @throws SQLException 
     */
    public List<TarefaDTO> tarefasElegiveis(String emailFreelancer) throws SQLException {
        List<Tarefa> tarefasCompativeis = new ArrayList<>();
        List<Tarefa> tarefasComGraus = getAllGrausTarefasPublicadas();
        List<GrauProficiencia> grausFreelancer = getAllGrausFreelancer(emailFreelancer);
        List<TarefaDTO> tarefasElegiveisDTO = new ArrayList<>();

       for (Tarefa tarefa : tarefasComGraus) {
           if (isAnuncioValido(emailFreelancer, tarefa.getReferencia())) {
               for (GrauProficiencia grauTarefa : tarefa.getGrauProficiencia()) {
                   for (GrauProficiencia grauFreelancer : grausFreelancer) {
                       if(grauFreelancer.getGrau() >= grauTarefa.getGrau() &&
                               grauFreelancer.getCodigoCompetenciaTecnica().equals(grauTarefa.getCodigoCompetenciaTecnica())) {
                           if (!tarefasCompativeis.contains(tarefa)) {
                               tarefasCompativeis.add(tarefa);
                           }
                       }
                   }
               }
           }
       }

       for(Tarefa tarefa : tarefasCompativeis) {
           Tarefa tarefaCompleta = getTarefaByRefENif( tarefa.getReferencia(), tarefa.getNifOrganizacao());
           tarefasElegiveisDTO.add((TarefaDTO) tarefaCompleta.toDTO());
       }

        return tarefasElegiveisDTO;
    }

    public boolean isAnuncioValido(String emailFreelancer, String referenciaTarefa) throws SQLException {
        return repositorioAnuncio.findAnuncioByEmailFreelancer(emailFreelancer, referenciaTarefa);
    }

    /**
     * Devolve uma tarefa a partir da sua referencia e do nif da organizacao que a criou
     * @param referencia
     * @param nifOrganizacao
     * @return
     * @throws SQLException 
     */
    private Tarefa getTarefaByRefENif(String referencia, String nifOrganizacao) throws SQLException {
        return repositorioTarefa.getTarefaByRefENif(referencia, nifOrganizacao);
    }

}
