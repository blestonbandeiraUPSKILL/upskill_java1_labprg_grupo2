package com.grupo2.t4j.controller;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioUtilizador;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

public class GestaoUtilizadoresController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioUtilizador repositorioUtilizador = fabricaRepositorios.getRepositorioUtilizador();


    /**
     * Faz login do utilizador na API
     * @param email
     * @param password
     * @return 
     */
    public boolean login(String email, String password) {
        return UsersAPI.getInstance().login(email, password);
    }

    /**
     * Faz logout do utilizador
     * @return 
     */
    public boolean logout() {
       return UsersAPI.getInstance().logout();
    }

    /**
     * Apaga o context da sessao anterior
     */
    public void resetUsersAPI() {
        UsersAPI.getInstance().resetContext();
    }

    /**
     * Devolve o papel do utilizador logado
     * @return 
     */
    public int getRole() {
        return UsersAPI.getInstance().getRole();
    }

    /**
     * Devolve o email do utilizador logado
     * @return 
     */
    public String getEmail() {
        return UsersAPI.getInstance().getEmail();
    }


}
