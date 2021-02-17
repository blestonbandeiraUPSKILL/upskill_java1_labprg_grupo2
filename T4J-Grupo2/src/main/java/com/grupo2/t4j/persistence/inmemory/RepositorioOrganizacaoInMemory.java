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

import com.grupo2.t4j.exception.OrganizacaoDuplicadaException;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.RepositorioOrganizacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioOrganizacaoInMemory implements Serializable, RepositorioOrganizacao {

    /**
     * Atributos da classe Singleton RepositorioOrganizacao
     */
    private static RepositorioOrganizacaoInMemory instance;
    private List<Organizacao> listaOrganizacoes;
    Colaborador colabGestor;

    /**
     * Construtor da classe Singleton RepositorioOrganizacao
     */
    RepositorioOrganizacaoInMemory() {
        listaOrganizacoes = new ArrayList<>();
    }

    /**
     * Devolve ou cria uma instância estática de RepositorioCompetenciaTecnica
     *
     * @return a instance existente ou criada
     */
    public static RepositorioOrganizacaoInMemory getInstance() {
        if(instance == null) {
            instance = new RepositorioOrganizacaoInMemory();
        }
        return instance;
    }

    public void save(String nif, String nome, Website website, String telefone,
                     Email emailOrganizacao, Email emailGestor, String arruamento,
                     String numeroPorta, String localidade, String codigoPostal, String nomeGestor, Password password,
                     Rolename rolename, String telefoneGestor, String funcaoGestor) {

    }

    @Override
    public Organizacao findByNif(String nif) {
        for (int i = 0; i < this.listaOrganizacoes.size(); i++) {
            Organizacao organizacao = this.listaOrganizacoes.get(i);
            if (organizacao.getNif().equals(nif)) {
                return organizacao;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Organizacao> getAll() {
        return new ArrayList<>(listaOrganizacoes);
    }




}
