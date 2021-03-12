package com.grupo2.t4j.controller;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.domain.AlgoritmoGeradorPasswords;
import com.grupo2.t4j.domain.Colaborador;
import com.grupo2.t4j.domain.Email;
import com.grupo2.t4j.domain.Password;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioColaborador;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

import java.sql.SQLException;
import java.util.List;

public class RegistarColaboradorController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioColaborador repositorioColaborador = fabricaRepositorios.getRepositorioColaborador();

    private AlgoritmoGeradorPasswords algoritmoGeradorPasswords;

    /**
     * Registo do Colaborador boolean
     *
     * @param nome as nome do colaborador
     * @param funcao as função do colaborador
     * @param telefone as telefone do colaborador
     * @param email as email do colaborador
     * @param nifOrganizacao as NIF da organização
     * @return boolean
     */
    public boolean registarColaborador(String email, String nome, String funcao, String telefone, String nifOrganizacao) throws SQLException {

        AlgoritmoGeradorPasswords algoritmoGeradorPasswords = new AlgoritmoGeradorPasswords();
        Password password = new Password(algoritmoGeradorPasswords.geraPassword());

        Colaborador colaborador = new Colaborador(new Email(email), nome , password, funcao, telefone, nifOrganizacao);
        registarColaboradorComoUtilizador(colaborador);

        return repositorioColaborador.save(colaborador);
    }

    public List<Colaborador> getAll(String nifOrganizacacao) throws SQLException {
        return repositorioColaborador.getAll(nifOrganizacacao);
    }

    public String getNifOrganizacao(String email) throws SQLException {
        return repositorioColaborador.getNifOrganizacao(email);
    }

    public Colaborador findByEmail(String email) throws SQLException {
        return repositorioColaborador.findByEmail(email);
    }

    public Password findPassword(String email) throws SQLException {
        return repositorioColaborador.findPassword(email);
    }


    ///////API
    public boolean registarGestorComoUtilizador(Colaborador colaborador) throws SQLException {
        String nome = colaborador.getNome();
        Email email = colaborador.getEmail();
        Password password = colaborador.getPassword();

        return UsersAPI.getInstance().registerUserWithRoles(email, nome, password, "gestor");
    }

    public boolean registarColaboradorComoUtilizador(Colaborador colaborador) throws SQLException {
        String nome = colaborador.getNome();
        Email email = colaborador.getEmail();
        Password password = colaborador.getPassword();

        return UsersAPI.getInstance().registerUserWithRoles(email, nome, password, "colaborador");
    }



}
