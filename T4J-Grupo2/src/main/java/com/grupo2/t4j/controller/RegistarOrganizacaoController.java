package com.grupo2.t4j.controller;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.domain.AlgoritmoGeradorPasswords;
import com.grupo2.t4j.domain.Colaborador;
import com.grupo2.t4j.domain.Organizacao;
import com.grupo2.t4j.domain.Password;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioColaborador;
import com.grupo2.t4j.persistence.RepositorioEnderecoPostal;
import com.grupo2.t4j.persistence.RepositorioOrganizacao;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.List;

public class RegistarOrganizacaoController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioOrganizacao repositorioOrganizacao = fabricaRepositorios.getRepositorioOrganizacao();
    private RepositorioColaborador repositorioColaborador = fabricaRepositorios.getRepositorioColaborador();
    private RepositorioEnderecoPostal repositorioEnderecoPostal = fabricaRepositorios.getRepositorioEnderecoPostal();

    public RegistarOrganizacaoController() throws SQLException {
    }

    /**
    * Registar organização boolean
    *
    * @param nif as nif da organização
    * @param nome as nome da organização
    * @param website as página web da organização
    * @param telefone as contacto telefónico da organização
    * @param emailOrganizacao as email da organização
    * @param emailGestor as email do gestor
    * @param arruamento as nome de rua da organização
    * @param numeroPorta as número da porta da da organização
    * @param localidade as localidade da organização
    * @param codigoPostal as código postal da organização
    * @param nomeGestor as nome do gestor da organização
    * @param telefoneGestor as telefone do gestor da organização
    * @param funcaoGestor as função do gestor da organização
    * @return boolean
    */
    public boolean registarOrganizacao(String nif, String nome, String website,
                                       String telefone, String emailOrganizacao, String emailGestor,
                                       String arruamento, String numeroPorta,
                                       String localidade, String codigoPostal, String nomeGestor,
                                       String telefoneGestor, String funcaoGestor) throws SQLException {

        AlgoritmoGeradorPasswords algoritmoGeradorPasswords = new AlgoritmoGeradorPasswords();
        String pass = algoritmoGeradorPasswords.geraPassword();

        Colaborador gestor = new Colaborador(emailGestor, nomeGestor, pass, funcaoGestor, telefoneGestor, funcaoGestor);

        UsersAPI usersAPI = UsersAPI.getInstance();
        usersAPI.registerUserWithRoles(gestor.getEmail(), gestor.getNome(), new Password(pass), "gestor");

        return repositorioOrganizacao.save(arruamento, numeroPorta, localidade, codigoPostal,
                nif, nome, website, telefone, emailOrganizacao,
                emailGestor, funcaoGestor, telefoneGestor);
    }






}
