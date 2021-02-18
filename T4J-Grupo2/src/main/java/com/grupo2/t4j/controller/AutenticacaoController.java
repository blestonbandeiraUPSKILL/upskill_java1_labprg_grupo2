package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.*;

import java.sql.SQLException;

public class AutenticacaoController {

    public boolean login (String username, String password) throws SQLException {

        return Plataforma.getInstance().getUsersAPI().login(username, password);
    }

    public boolean logout() throws SQLException {
        return Plataforma.getInstance().getUsersAPI().logout();
    }

    public Rolename getRole() throws SQLException {
        return Rolename.valueOf(Plataforma.getInstance().getUsersAPI().getRole().toUpperCase());

    }

    public Email getEmail() throws SQLException {
        return new Email(Plataforma.getInstance().getUsersAPI().getEmail());
    }

    public boolean registarGestorComoUtilizador(Colaborador colaborador) throws SQLException {
        String nome = colaborador.getNome();
        Email email = colaborador.getEmail();

        AlgoritmoGeradorPasswords algoritmoGeradorPasswords = Plataforma.getInstance().getAlgoritmoGeradorPwd();
        Password password = new Password(algoritmoGeradorPasswords.geraPassword());
        colaborador.setPassword(password);

        UsersAPI usersAPI = Plataforma.getInstance().getUsersAPI();
        Utilizador utilizador = new Utilizador(email, nome, password, Rolename.GESTOR);

        return usersAPI.registerUserWithRoles(email, nome, password, "gestor")
                && Plataforma.getInstance().getRepositorioUtilizador().save(utilizador);
    }

    public boolean registarColaboradorComoUtilizador(Colaborador colaborador) throws SQLException {
        String nome = colaborador.getNome();
        Email email = colaborador.getEmail();

        AlgoritmoGeradorPasswords algoritmoGeradorPasswords = Plataforma.getInstance().getAlgoritmoGeradorPwd();
        Password password = new Password(algoritmoGeradorPasswords.geraPassword());
        colaborador.setPassword(password);

        UsersAPI usersAPI = Plataforma.getInstance().getUsersAPI();
        Utilizador utilizador = new Utilizador(email, nome, password, Rolename.COLABORADOR);

        return usersAPI.registerUserWithRoles(email, nome, password, "colaborador") &&
                Plataforma.getInstance().getRepositorioUtilizador().save(utilizador);
    }

    public void getContext() {

    }
}
