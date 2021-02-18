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
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.RepositorioAnuncio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class RepositorioAnuncioInMemory implements Serializable, RepositorioAnuncio {

    /**
     * Define uma instância do Repositório em que estão registados todos
     * os anúncios de uma dada organização
     */
    private static RepositorioAnuncioInMemory repositorioAnuncioInMemory;
    
    /**
     * Define o atributo da classe RepositorioAnuncio como uma lista de
     * tipos da classe Anuncio
     */
    private List<Anuncio> listaAnuncios;
    
    /**
     * Inicializa o Repositório de Anúncios de uma organização
     *
     */
    public RepositorioAnuncioInMemory(){
        listaAnuncios = new ArrayList<>();
    }
    
    /**
     * Garante que existe apenas um repositorio para os anúncios
     * @return repositório de anúncios na memória
     */
    public static RepositorioAnuncioInMemory getInstance(){
        if (repositorioAnuncioInMemory == null){
            repositorioAnuncioInMemory = new RepositorioAnuncioInMemory();
        }
        return repositorioAnuncioInMemory;
    }
    
    @Override
    public void save(String idAnuncio, Data dtInicioPublicitacao, Data dtFimPublicitacao, Data dtInicioCandidatura, Data dtFimCandidatura, Data dtInicioSeriacao, Data dtFimSeriacao) throws AnuncioDuplicadoException {
        Anuncio a = findById(idAnuncio);
        if (a == null) {
            Anuncio anuncio = new Anuncio(idAnuncio, dtInicioPublicitacao, dtFimPublicitacao,
                    dtInicioCandidatura, dtFimCandidatura, dtInicioSeriacao, dtFimSeriacao);
            this.listaAnuncios.add(anuncio);
        } else {
            throw new AnuncioDuplicadoException(idAnuncio + ": Anúncio já registado!");
        }
    }

    @Override
    public void save(Anuncio anuncio) {
        Anuncio a = findById(anuncio.getIdAnuncio());
        if (a == null) {
            Anuncio anu = new Anuncio(anuncio);
            this.listaAnuncios.add(anu);
        } else {
            throw new AnuncioDuplicadoException(anuncio.getIdAnuncio() + ": Anúncio já registado!");
        }
    }

    @Override
    public Anuncio findById(String idAnuncio) {
        Anuncio anuncio = null;
        for (int i = 0; i < this.listaAnuncios.size(); i++) {
            anuncio = this.listaAnuncios.get(i);
            if (anuncio.getIdAnuncio().equals(idAnuncio)) {
                return anuncio;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Anuncio> getAll() {
        return new ArrayList<Anuncio>(listaAnuncios);
    }
}
