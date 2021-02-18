package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioColaborador;
import com.grupo2.t4j.persistence.RepositorioOrganizacao;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import com.grupo2.t4j.persistence.database.RepositorioOrganizacaoDatabase;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;

import java.sql.SQLException;

public class RegistarOrganizacaoController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioOrganizacao repositorioOrganizacao = fabricaRepositorios.getRepositorioOrganizacao();
    private RepositorioColaborador repositorioColaborador = fabricaRepositorios.getRepositorioColaborador();


    public Organizacao registarOrganizacao(String nif, String nome, Website website,
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
