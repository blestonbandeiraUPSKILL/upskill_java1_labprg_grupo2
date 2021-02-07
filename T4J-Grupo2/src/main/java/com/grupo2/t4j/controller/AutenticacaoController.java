package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.*;

public class AutenticacaoController {

    public boolean login (String username, Password password) {
        return Plataforma.getInstance().getUsersAPI().login(username, password);
    }

    public boolean logout() {
        return Plataforma.getInstance().getUsersAPI().logout();
    }

    public Rolename getRole(){
        return Rolename.valueOf(Plataforma.getInstance().getUsersAPI().getRole().toUpperCase());

    }

    public Email getEmail() {
        return new Email(Plataforma.getInstance().getUsersAPI().getEmail());
    }

    public boolean registarGestorComoUtilizador(Colaborador colaborador) {
        String nome = colaborador.getNome();
        Email email = colaborador.getEmail();

        AlgoritmoGeradorPasswords algoritmoGeradorPasswords = Plataforma.getInstance().getAlgoritmoGeradorPwd();
        Password password = new Password(algoritmoGeradorPasswords.geraPassword());
        System.out.println(password);

        UsersAPI usersAPI = Plataforma.getInstance().getUsersAPI();
        Utilizador utilizador = new Utilizador(nome, password, email, Rolename.GESTOR);

        return usersAPI.registerUserWithRoles(nome, email, password, "gestor")
                && Plataforma.getInstance().getRepositorioUtilizador().addUtilizador(utilizador);
    }

    public boolean registarColaboradorComoUtilizador(Colaborador colaborador) {
        String nome = colaborador.getNome();
        Email email = colaborador.getEmail();

        AlgoritmoGeradorPasswords algoritmoGeradorPasswords = Plataforma.getInstance().getAlgoritmoGeradorPwd();
        Password password = new Password(algoritmoGeradorPasswords.geraPassword());
        System.out.println(password);

        UsersAPI usersAPI = Plataforma.getInstance().getUsersAPI();
        Utilizador utilizador = new Utilizador(nome, password, email, Rolename.COLABORADOR);

        return usersAPI.registerUserWithRoles(nome, email, password, "colaborador") &&
                Plataforma.getInstance().getRepositorioUtilizador().addUtilizador(utilizador);
    }
}
