/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.grupo2.t4j.repository;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.exception.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioOrganizacao implements Serializable{

    /**
     * Atributos da classe Singleton RepositorioOrganizacao
     */
    private static RepositorioOrganizacao instance;
    private List<Organizacao> listaOrganizacoes;
    Colaborador colabGestor;
    private DBConnectionHandler dbConnectionHandler;

    /**
     * Construtor da classe Singleton RepositorioOrganizacao
     */
    private RepositorioOrganizacao() {
        listaOrganizacoes = new ArrayList<>();
    }

    /**
     * Devolve ou cria uma instância estática de RepositorioCompetenciaTecnica
     *
     * @return a instance existente ou criada
     */
    public static RepositorioOrganizacao getInstance() {
        if(instance == null) {
            instance = new RepositorioOrganizacao();
        }
        return instance;
    }

    /**
     *
     * Cria uma nova Organizacao usando os construtores dos objectos que a compõem
     *
     * @param nome nome da organização
     * @param nif nif da organização
     * @param arruamento rua, pertencente ao Endereço Postal
     * @param numeroPorta número da porta, pertencente ao Endereço Postal
     * @param localidade localidade, pertencente ao Endereço Postal
     * @param codigoPostal código postal, pertencente ao Endereço Postal
     * @param telefone contacto telefónico da organização
     * @param website website da organização
     * @param emailOrganizacao email da organização
     * @param nomeGestor nome do colaborador que tem actua gestor na organização
     * @param emailGestor email do colaborador que actua como gestor na organização
     * @param telefoneGestor telefone do colaboraor que actua como gestor na organização
     * @param rolename role do colaborador que actua como gestor na organização
     *
     * @return uma instância de Organizacao
     */
    public Organizacao novaOrganizacao(String nome, String nif, String arruamento,
                                       String numeroPorta, String localidade, String codigoPostal,
                                       String telefone, Website website, Email emailOrganizacao,
                                       String nomeGestor, Email emailGestor, String telefoneGestor,
                                       Rolename rolename) {
        EnderecoPostal endereco = Organizacao.novoEndereco(arruamento, numeroPorta, localidade, codigoPostal);
        colabGestor = Organizacao.novoColaborador(nomeGestor, emailGestor, telefoneGestor, rolename);

        return new Organizacao(nome, nif, endereco, website, telefone, emailOrganizacao, colabGestor);
    }

    /**
     *
     * Adiciona uma nova Organização ao RepositorioOrganizacao
     *
     * @param organizacao organização a ser adicionada
     *
     * @throws OrganizacaoDuplicadaException
     * @return
     */
    public boolean addOrganizacao(Organizacao organizacao) throws OrganizacaoDuplicadaException {
        Organizacao o = getOrganizacaoByNif(organizacao.getNif());
        if (o == null) {
            
            //return new Organizacao(organizacao);
            return this.listaOrganizacoes.add(organizacao);
            } else {
            throw new OrganizacaoDuplicadaException(o.getNif() + ": Organização já registada!");
        }
    }

    /**
     *
     * Procura no RepositorioOrganizacao por uma organização através do seu NIF
     *
     * @param NIF nif da organização a ser procurada
     *
     * @return a organização encontrada, caso exista
     */
    private Organizacao getOrganizacaoByNif(String NIF) {
        for (int i = 0; i < this.listaOrganizacoes.size(); i++) {
            Organizacao organizacao = this.listaOrganizacoes.get(i);
            if (organizacao.getNif().equals(NIF)) {
                return organizacao;
            }
        }
        return null;
    }

    /**
     * Devolve a lista de organizações no RepositorioOrganizacao
     * @return
     */
    public ArrayList<Organizacao> getOrganizacoes() {
        return new ArrayList<>(listaOrganizacoes);
    }


}
