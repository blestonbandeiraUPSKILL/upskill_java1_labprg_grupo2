package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Organizacao;
import com.grupo2.t4j.model.Plataforma;
import com.grupo2.t4j.model.Website;
import com.grupo2.t4j.repository.RepositorioOrganizacao;

public class RegistarOrganizacaoController {

    private Plataforma plataforma;
    private RepositorioOrganizacao repositorioOrganizacao;
    private Organizacao organizacao;

    public RegistarOrganizacaoController() {
        this.plataforma = Plataforma.getInstance();
    }

    public void novaOrganizacao(String nome, String nif, String arruamento,
                                String numeroPorta, String localidade, String codigoPostal,
                                String telefone, Website website, Email emailOrganizacao,
                                String nomeGestor, String funcao, String telefoneGestor,
                                Email emailGestor) throws Exception {
        repositorioOrganizacao = plataforma.getRepositorioOrganizacao();
        organizacao = repositorioOrganizacao.novaOrganizacao(nome, nif, arruamento, numeroPorta, localidade, codigoPostal,
                telefone, website, emailOrganizacao, nomeGestor, funcao, telefoneGestor, emailGestor);

        if (repositorioOrganizacao.validaOrganizacao(organizacao)) {
            repositorioOrganizacao.addOrganizacao(organizacao);
        }
        else {
            throw new Exception();
        }
    }

    public void registaOrganizacao() throws Exception {
        repositorioOrganizacao.registaOrganizacao(organizacao);
    }
}
