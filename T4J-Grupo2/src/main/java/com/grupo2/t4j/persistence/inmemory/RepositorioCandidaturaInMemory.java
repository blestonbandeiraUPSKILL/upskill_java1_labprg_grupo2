/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.persistence.inmemory;

import com.grupo2.t4j.model.Candidatura;
import com.grupo2.t4j.persistence.RepositorioCandidatura;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acris
 */
class RepositorioCandidaturaInMemory implements Serializable, RepositorioCandidatura{
    
    /**
     * Define uma instância estática do Repositório em que estão registadas todas
     * as candidaturas efectuadas na plataforma
     */
    private static RepositorioCandidaturaInMemory repositorioCandidaturaInMemory;
    
    /**
     * Define o atributo da classe RepositorioAnuncio como uma lista de
     * tipos da classe Anuncio
     */
    private List<Candidatura> listaCandidaturas;
    
    /**
     * Inicializa o Repositório de Anúncios de uma organização
     *
     */
    public RepositorioCandidaturaInMemory(){
        listaCandidaturas = new ArrayList<>();
    }

    /**
     * Garante que existe apenas um repositorio para as candidaturas
     * @return repositório de candidaturas na memória
     */
    public static RepositorioCandidaturaInMemory getInstance(){
        if (repositorioCandidaturaInMemory == null){
            repositorioCandidaturaInMemory = new RepositorioCandidaturaInMemory();
        }
        return repositorioCandidaturaInMemory;
    }
    
}
