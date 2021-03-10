package com.grupo2.t4j.controller;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioUtilizador;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

public class GestaoUtilizadoresController {

    private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioUtilizador repositorioUtilizador = fabricaRepositorios.getRepositorioUtilizador();


    public boolean login(String email, String password) {
        return UsersAPI.getInstance().login(email, password);
    }

    public boolean logout() {
       return UsersAPI.getInstance().logout();
    }

    public void resetUsersAPI() {
        UsersAPI.getInstance().resetContext();
    }

    public int getRole() {
        return UsersAPI.getInstance().getRole();
    }

    public String getEmail() {
        return UsersAPI.getInstance().getEmail();
    }


}
