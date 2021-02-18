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
import java.sql.SQLException;
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
                     Email emailOrganizacao, EnderecoPostal enderecoPostal, Colaborador gestor) {
        Organizacao o = findByNif(nif);
        if (o == null) {

            Organizacao org = new Organizacao(nif, nome, website, telefone,
                    emailOrganizacao, gestor, enderecoPostal );

            this.listaOrganizacoes.add(org);
        } else {
            throw new OrganizacaoDuplicadaException(o.getNif() + ": Organização já registada!");
        }

    }


    @Override
    public boolean save(Organizacao organizacao) {
        Organizacao o = findByNif(organizacao.getNif());
        if (o == null) {
            this.listaOrganizacoes.add(organizacao);
        } else {
            throw new OrganizacaoDuplicadaException(o.getNif() + ": Organização já registada!");
        }
        return false;
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
