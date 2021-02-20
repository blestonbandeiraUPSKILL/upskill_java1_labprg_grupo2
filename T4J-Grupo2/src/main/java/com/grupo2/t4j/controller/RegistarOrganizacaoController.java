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

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioOrganizacao repositorioOrganizacao = fabricaRepositorios.getRepositorioOrganizacao();
    private RepositorioColaborador repositorioColaborador = fabricaRepositorios.getRepositorioColaborador();
    private RepositorioEnderecoPostal repositorioEnderecoPostal = fabricaRepositorios.getRepositorioEnderecoPostal();
    private UsersAPI usersAPI;

    public RegistarOrganizacaoController() throws SQLException {
    }

    public boolean registarOrganizacao(String nif, String nome, String website,
                                       String telefone, String emailOrganizacao, String emailGes,
                                       String codigoEnderecoPostal, String arruamento, String numeroPorta,
                                       String localidade, String codigoPostal, String nomeGestor,
                                       String telefoneGestor, String funcaoGestor) throws SQLException {

        EnderecoPostal enderecoPostal = new EnderecoPostal(codigoEnderecoPostal, arruamento, numeroPorta, localidade, codigoPostal);
        repositorioEnderecoPostal.save(enderecoPostal);

        AlgoritmoGeradorPasswords algoritmoGeradorPasswords = new AlgoritmoGeradorPasswords();
        Password pass = new Password(algoritmoGeradorPasswords.geraPassword());

        Email emailGestor = new Email(emailGes);
        Colaborador gestor = new Colaborador(emailGestor, nomeGestor, pass, funcaoGestor, telefoneGestor);
        repositorioColaborador.save(gestor);

        usersAPI.registerUserWithRoles(gestor.getEmail(), gestor.getNome(), pass, "gestor");

        Organizacao organizacao = new Organizacao(nif, nome, new Website(website), telefone, new Email(emailOrganizacao), emailGestor, codigoEnderecoPostal);

        return repositorioOrganizacao.save(organizacao);
    }

    public List<Organizacao> getAll() {
        return repositorioOrganizacao.getAll();
    }

    public Organizacao findByNif(String nif) throws SQLException {
        return repositorioOrganizacao.findByNif(nif);
    }

    public Colaborador findColaboradorByEmail(String email){
        return repositorioColaborador.findByEmail(email);
    }


}
