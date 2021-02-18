package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.database.RepositorioOrganizacaoDatabase;

import java.sql.SQLException;

public class RegistarOrganizacaoController {

    
    private Plataforma plataforma;
    private RepositorioOrganizacaoDatabase repositorioOrganizacao;

    public Organizacao novaOrganizacao(String nif, String nome, Website website,
                                       String telefone, Email emailOrganizacao, Email emailGestor,
                                       String arruamento, String numeroPorta, String localidade, String codigoPostal,
                                       String nomeGestor, Password password, String rolename, String telefoneGestor, String funcaoGestor) throws SQLException {

        EnderecoPostal enderecoPostal = new EnderecoPostal(arruamento, numeroPorta, localidade, codigoPostal);
        Colaborador gestor = new Colaborador(emailGestor, nomeGestor, password, funcaoGestor, telefoneGestor, Rolename.GESTOR);

        Organizacao organizacao = RepositorioOrganizacaoDatabase.getInstance().novaOrganizacao(
                nif, nome, website, telefone, emailOrganizacao, gestor, enderecoPostal);

        return organizacao;
    }

    public boolean registaOrganizacao(Organizacao organizacao) throws Exception {

        return RepositorioOrganizacaoDatabase.getInstance().addOrganizacao(organizacao);
    }


}
