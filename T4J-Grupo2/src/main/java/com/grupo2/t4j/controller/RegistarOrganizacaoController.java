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

    public void novaOrganizacao(String nome, String nif, String enderecoLocal, String enderecoPostal,
                                String localidade, String telefone, Website website,
                                Email email,String nomeGestor, String funcao,
                                String telefoneGestor, Email emailGestor) {
        repositorioOrganizacao = plataforma.getRepositorioOrganizacao();
        organizacao = repositorioOrganizacao.novaOrganizacao(nome, nif, enderecoLocal, enderecoPostal, localidade,
                telefone, website, email, nomeGestor, funcao, telefoneGestor, emailGestor);

        if (repositorioOrganizacao.validaOrganizacao(organizacao)) {

        }
      /*  else {
            throw new Exception();
        }*/

        /*public void registaOrganizacao() throws Exception {
            registaOrganizacao.registaOrganizacao(organizacao);
        }*/
    }
}
