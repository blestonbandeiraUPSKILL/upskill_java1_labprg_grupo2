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

import com.grupo2.t4j.exception.AnuncioDuplicadoException;
import com.grupo2.t4j.model.Anuncio;
import com.grupo2.t4j.model.Data;
import com.grupo2.t4j.model.Organizacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class RepositorioAnuncioInMemory implements Serializable{

    /**
     * Define uma instância do Repositório em que estão registados todos
     * os anúncios de uma dada organização
     */
    private RepositorioAnuncioInMemory repositorioAnuncioInMemory;
    
    /**
     * A organização a que se refere o repositório
     */
    private Organizacao organizacao;
    
    /**
     * Define o atributo da classe RepositorioAnuncio como uma lista de
     * tipos da classe Anuncio
     */
    private List<Anuncio> listaAnuncios;
    
    /**
     * Inicializa o Repositório de Anúncios de uma organização
     * @param organizacao - a organização a que se refere o repositório de anúncios
     */
    public RepositorioAnuncioInMemory(Organizacao organizacao){
        listaAnuncios = new ArrayList<>();
    }
    
    /**
     * Adiciona um Anúncio à lista de anúncios de uma organização
     * @param anuncio do tipo da classe Anuncio
     * @throws AnuncioDuplicadoException
     */
    public boolean addAnuncio(Anuncio anuncio) throws AnuncioDuplicadoException {
        Anuncio a = getAnuncioById(anuncio.getIdAnuncio());
        if (a == null) {
            this.listaAnuncios.add(anuncio);
            return true;
        } else {
            throw new AnuncioDuplicadoException(anuncio.getIdAnuncio()
                    + ": Anúncio já registado!");
        }
    }
    
    /**
     * Adiciona um Anúncio à lista de anúncios de uma organização
     * @param idAnuncio - o id do Anúncio
     * @param dtInicioPublicitacao - data de início da publicitação do anúncio
     * @param dtFimPublicitacao - data do fim da publicitação do anúncio
     * @param dtInicioCandidatura - data de início da candidatura do anúncio
     * @param dtFimCandidatura - data do fim da candidatura do anúncio
     * @param dtInicioSeriacao - data de início do processo de seriação do anúncio
     * @param dtFimSeriacao - data do fim do processo de seriação do anúncio
     * @throws AnuncioDuplicadoException
     */
    public boolean addAnuncio(String idAnuncio,Data dtInicioPublicitacao, Data dtFimPublicitacao, Data 
            dtInicioCandidatura, Data dtFimCandidatura, Data dtInicioSeriacao,
            Data dtFimSeriacao) throws AnuncioDuplicadoException {
        Anuncio a = getAnuncioById(idAnuncio);
        if (a == null) {
            Anuncio anuncio = new Anuncio(idAnuncio, dtInicioPublicitacao, dtFimPublicitacao,
            dtInicioCandidatura, dtFimCandidatura, dtInicioSeriacao, dtFimSeriacao);
            this.listaAnuncios.add(anuncio);
            return true;
        } else {
            throw new AnuncioDuplicadoException(idAnuncio + ": Anúncio já registado!");
        }
    } 
      
    /**
     * Devolve um Colaborador de acordo com o email registado
     * @param idAnuncio - o id do Anúncio 
     * @return Anuncio registado
     */
    public Anuncio getAnuncioById(String idAnuncio) {
        Anuncio anuncio = null;
        for (int i = 0; i < this.listaAnuncios.size(); i++) {
            anuncio = this.listaAnuncios.get(i);
            if (anuncio.getIdAnuncio().equals(idAnuncio)) {
                return anuncio;
            }
        }
        return null;
    }

    /**
     * Atualiza a lista de Anúncios
     * @param listaAnuncios
     */
    public void setListaAnuncios(List<Anuncio> listaAnuncios){
        this.listaAnuncios = listaAnuncios;
    }
    
    /**
     * Deevolve a lista de Anúncios
     * @return listaAnuncios
     */
    public ArrayList<Anuncio> getListaAnuncios(){
        return new ArrayList<Anuncio>(listaAnuncios);
    }
    
    /**
     * Informa se a lista de Anúncios está ou não vazia
     * @return 
     */
    public boolean isVazia() {
        return listaAnuncios.isEmpty();
    }
    
    /**
     * Contabiliza a quantidade de anúncios registados
     * @param outraListaAnuncios
     * @return a quantidade de anúncios da lista de anúncios
     */
    public int adicionarListaAnuncios(RepositorioAnuncioInMemory outraListaAnuncios) {
        int totalAnunciosAdicionados = 0;
        
        for (Anuncio anuncio : outraListaAnuncios.listaAnuncios) {
            boolean anuncioAdicionado = addAnuncio(anuncio);
            if (anuncioAdicionado) {
                totalAnunciosAdicionados++;
            }
        }
        return totalAnunciosAdicionados;
    }
      
    /**
     * Constrói um novo anúncio
     * @param idAnuncio - o id do Anúncio
     * @param dtInicioPublicitacao - data de início da publicitação do anúncio
     * @param dtFimPublicitacao - data do fim da publicitação do anúncio
     * @param dtInicioCandidatura - data de início da candidatura do anúncio
     * @param dtFimCandidatura - data do fim da candidatura do anúncio
     * @param dtInicioSeriacao - data de início do processo de seriação do anúncio
     * @param dtFimSeriacao - data do fim do processo de seriação do anúncio
     * @return um novo anúncio
     */
    public Anuncio novoAnuncio(String idAnuncio,Data dtInicioPublicitacao, Data dtFimPublicitacao, Data 
            dtInicioCandidatura, Data dtFimCandidatura, Data dtInicioSeriacao,
            Data dtFimSeriacao) {
        return new Anuncio(idAnuncio, dtInicioPublicitacao, dtFimPublicitacao,
            dtInicioCandidatura, dtFimCandidatura, dtInicioSeriacao, dtFimSeriacao);
    }
}
