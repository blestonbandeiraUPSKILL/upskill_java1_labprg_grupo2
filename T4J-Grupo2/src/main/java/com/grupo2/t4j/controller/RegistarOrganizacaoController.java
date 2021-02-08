package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.repository.RepositorioOrganizacao;

public class RegistarOrganizacaoController {

    private Plataforma plataforma;
    private RepositorioOrganizacao repositorioOrganizacao;
    public static Organizacao organizacao;

    public static Organizacao getOrganizacao() {
        return RegistarOrganizacaoController.organizacao;
    }

    public static void setOrganizacao(Organizacao organizacao) {
        RegistarOrganizacaoController.organizacao = organizacao;
    }

    public RegistarOrganizacaoController() {
        this.plataforma = Plataforma.getInstance();
    }

    /*public Organizacao novaOrganizacao(String nome, String nif, String arruamento,
                                String numeroPorta, String localidade, String codigoPostal,
                                String telefone, Website website, Email emailOrganizacao,
                                String nomeGestor, Email emailGestor,  String telefoneGestor,
                                Rolename rolename) throws Exception {
        organizacao = RepositorioOrganizacao.getInstance().novaOrganizacao(nome, nif, arruamento, numeroPorta, localidade, codigoPostal,
                telefone, website, emailOrganizacao, nomeGestor, emailGestor, telefoneGestor, rolename);

        RepositorioOrganizacao.getInstance().addOrganizacao(organizacao);

//        if (repositorioOrganizacao.validaOrganizacao(organizacao)) {
//
//        }
//        else {
//            throw new Exception();
//        }
        return organizacao;
    }*/

    public boolean registaOrganizacao(Organizacao organizacao) throws Exception {
        return RepositorioOrganizacao.getInstance().addOrganizacao(organizacao);
    }
}
