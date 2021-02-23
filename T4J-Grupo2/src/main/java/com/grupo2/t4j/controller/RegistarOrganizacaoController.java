package com.grupo2.t4j.controller;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioColaborador;
import com.grupo2.t4j.persistence.RepositorioEnderecoPostal;
import com.grupo2.t4j.persistence.RepositorioOrganizacao;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;
import com.grupo2.t4j.persistence.database.RepositorioOrganizacaoDatabase;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;

import java.sql.SQLException;
import java.util.List;

public class RegistarOrganizacaoController {

    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioOrganizacao repositorioOrganizacao = fabricaRepositorios.getRepositorioOrganizacao();
    private RepositorioColaborador repositorioColaborador = fabricaRepositorios.getRepositorioColaborador();
    private RepositorioEnderecoPostal repositorioEnderecoPostal = fabricaRepositorios.getRepositorioEnderecoPostal();

    public RegistarOrganizacaoController() throws SQLException {
    }

    public boolean registarOrganizacao(String nif, String nome, String website,
                                       String telefone, String emailOrganizacao, String emailGestor,
                                       String arruamento, String numeroPorta,
                                       String localidade, String codigoPostal, String nomeGestor,
                                       String telefoneGestor, String funcaoGestor) throws SQLException {

        AlgoritmoGeradorPasswords algoritmoGeradorPasswords = new AlgoritmoGeradorPasswords();
        String pass = algoritmoGeradorPasswords.geraPassword();

        Colaborador gestor = new Colaborador(emailGestor, nomeGestor, pass, funcaoGestor, telefoneGestor);

        UsersAPI usersAPI = UsersAPI.getInstance();
        usersAPI.registerUserWithRoles(gestor.getEmail(), gestor.getNome(), new Password(pass), "gestor");

        return repositorioOrganizacao.save(nif, nome, website, telefone,
                emailOrganizacao, emailGestor, arruamento, numeroPorta, localidade,
                codigoPostal, nomeGestor, pass, telefoneGestor, funcaoGestor);
    }

    public List<Organizacao> getAll() {
        return repositorioOrganizacao.getAll();
    }

    public Organizacao findByNif(String nif) throws SQLException {
        return repositorioOrganizacao.findByNif(nif);
    }

    public Colaborador findColaboradorByEmail(String email) throws SQLException {
        return repositorioColaborador.findByEmail(email);
    }




}
