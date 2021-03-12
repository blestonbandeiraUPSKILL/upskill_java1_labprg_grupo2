package com.grupo2.t4j.controller;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

public class GestaoUtilizadoresController {

    private UsersAPI usersAPI = UsersAPI.getInstance();


    /**
     * Faz login do utilizador na API
     * @param email
     * @param password
     * @return 
     */
    public boolean login(String email, String password) {
        return usersAPI.login(email, password);
    }

    /**
     * Faz logout do utilizador
     * @return 
     */
    public boolean logout() {
       return usersAPI.logout();
    }

    /**
     * Apaga o context da sessao anterior
     */
    public void resetUsersAPI() {
        usersAPI.resetContext();
    }

    /**
     * Devolve o papel do utilizador logado
     * @return 
     */
    public int getRole() {
        return usersAPI.getRole();
    }

    /**
     * Devolve o email do utilizador logado
     * @return 
     */
    public String getEmail() {
        return usersAPI.getEmail();
    }


}
