/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.inmemory;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.exception.TarefaDuplicadaException;
import com.grupo2.t4j.model.Anuncio;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.Tarefa;
import com.grupo2.t4j.persistence.RepositorioTarefa;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTarefaInMemory implements Serializable, RepositorioTarefa {
    
    /**
     * Define uma instância estática do Repositório em que estão registados todos
     * os Administradores da plataforma
     */
    private static RepositorioTarefaInMemory repositorioTarefaInMemory;
    
     /**
     * Define o atributo da classe RepositorioTarefa como uma lista de
     * tipos da classe Tarefa
     */
    private List<Tarefa> listaTarefas;

    private List<Categoria> listaCategorias;

    /**
     * Devolve uma instância estática do Repositório de Tarefas
     * @return RepositorioTarefa
     */
    public static RepositorioTarefaInMemory getInstance(){
        if (repositorioTarefaInMemory == null){
            repositorioTarefaInMemory = new RepositorioTarefaInMemory();
        }
        return repositorioTarefaInMemory;
    }

    /**
     * Inicializa o Repositório de Tarefas
     */
    RepositorioTarefaInMemory(){
        listaTarefas = new ArrayList<>();
    }

    /**
     * Adiciona uma Tarefa à lista de Tarefas
     * @param tarefa do tipo da classe Tarefa
     * @throws TarefaDuplicadaException
     * @return
     */
    public boolean addTarefa(Tarefa tarefa) throws TarefaDuplicadaException {
        /*Tarefa t = findByReferenciaENIF(tarefa.getReferencia());
        if (t == null) {
            return this.listaTarefas.add(tarefa);
        } else {
            throw new TarefaDuplicadaException(t.getReferencia() + 
                    ": Tarefa já registada");
        }*/
        return false;

    }

    @Override
    public void save(String codigoAreaActividade, String codigoCategoriaTarefa, 
            String referencia, String designacao, String descInformal, 
            String descTecnica, int duracao, double custo, 
            String nifOrganizacao, String emailColaborador) {
        /*Tarefa t = findByReferenciaENIF(referencia);
        if (t == null) {
            Tarefa tarefa = new Tarefa(referencia, designacao, descInformal,
                    descTecnica, duracao, custo, codigoAreaActividade, codigoCategoriaTarefa);
            this.listaTarefas.add(tarefa);
        }
        else {
            throw new TarefaDuplicadaException(t.getReferencia() +
                    ": Tarefa já registada");
        }*/
        
    }

    @Override
    public boolean save(Tarefa tarefa) throws SQLException {
        /*Tarefa t = findByReferenciaENIF(tarefa.getReferencia());
        if (t == null) {
            Tarefa tar = new Tarefa(tarefa);
            this.listaTarefas.add(tar);
        }
        else {
            throw new TarefaDuplicadaException(t.getReferencia() +
                    ": Tarefa já registada");
        }*/
        return false;
    }

    @Override
    public Tarefa findByReferenciaENIF(String referencia, String NIF) throws SQLException{
        for (int i = 0; i < this.listaTarefas.size(); i++) {
            Tarefa tarefa = this.listaTarefas.get(i);
            if (tarefa.getReferencia().equals(referencia)) {
                return tarefa;
            }
        }
        return null;
    }

    @Override
    public List<Tarefa> findByCategoria(String codigoCategoria) {
        ArrayList<Tarefa> tarefasPorCategoria = new ArrayList<>();

        for(Tarefa t : listaTarefas) {
            if(t.getCodigoCategoriaTarefa().equals(codigoCategoria)) {
                tarefasPorCategoria.add(t);
            }
        }
        return tarefasPorCategoria;
    }

    /**
     * Devolve a lista de Tarefas
     *
     * @return 
     */
    public ArrayList<Tarefa> getAllOrganizacao(String nifOrganizacao) throws SQLException{

        return new ArrayList<Tarefa>(listaTarefas);
    }
    public ArrayList<Tarefa> getAll() throws SQLException{
        return null;
    }

    //getAllByOrgPublicadas, getAllByOrgNaoPublicadas, getAllByOrg

    @Override
    public List<Tarefa> findByColaboradorENif(String email, String nifOrganizacao) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Anuncio> findTarefasComAnuncio(List<String> email, String nifOrganizacao) throws SQLException {
        return null;
    }


    public List<Anuncio> findTarefasComAnuncio(String email, String nifOrganizacao) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Anuncio> findAnuncioByTarefa(String referencia, String nif) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tarefa> findTarefasSemAnuncio(String email, String nifOrganizacao) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> findReferenciaTarefa(String nifOrganizacao, String email) throws SQLException {
        return null;
    }
}
