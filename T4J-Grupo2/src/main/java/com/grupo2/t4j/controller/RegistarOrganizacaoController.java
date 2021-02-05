package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.repository.RepositorioOrganizacao;

public class RegistarOrganizacaoController {

    private Plataforma plataforma;
    private RepositorioOrganizacao repositorioOrganizacao;
    private static Organizacao organizacao;

    public static Organizacao getOrganizacao() {
        return RegistarOrganizacaoController.organizacao;
    }

    public static void setOrganizacao(Organizacao organizacao) {
        RegistarOrganizacaoController.organizacao = organizacao;
    }

    public RegistarOrganizacaoController() {
        this.plataforma = Plataforma.getInstance();
    }

    public Organizacao novaOrganizacao(String nome, String nif, String arruamento,
                                String numeroPorta, String localidade, String codigoPostal,
                                String telefone, Website website, Email emailOrganizacao,
                                String nomeGestor, Email emailGestor,  String telefoneGestor,
                                Rolename rolename) throws Exception {
        repositorioOrganizacao = RepositorioOrganizacao.getInstance();
        organizacao = repositorioOrganizacao.novaOrganizacao(nome, nif, arruamento, numeroPorta, localidade, codigoPostal,
                telefone, website, emailOrganizacao, nomeGestor, emailGestor, telefoneGestor, rolename);

        if (repositorioOrganizacao.validaOrganizacao(organizacao)) {
            repositorioOrganizacao.addOrganizacao(organizacao);
        }
        else {
            throw new Exception();
        }
        return organizacao;
    }

    public boolean registaOrganizacao() throws Exception {
        return RepositorioOrganizacao.getInstance().registaOrganizacao(organizacao);
    }
}
