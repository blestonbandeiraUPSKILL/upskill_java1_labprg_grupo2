package com.grupo2.t4j.controller;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.RepositorioUtilizador;
import com.grupo2.t4j.persistence.inmemory.FabricaRepositoriosInMemory;

import java.sql.SQLException;

public class GestaoUtilizadoresController {

    /*private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosInMemory();
    //private FabricaRepositorios fabricaRepositorios = new FabricaRepositoriosDatabase();
    private RepositorioUtilizador repositorioUtilizador = fabricaRepositorios.getRepositorioUtilizador();*/

    private UsersAPI usersAPI = new UsersAPI();

    public boolean login(String email, String password) {
        return usersAPI.login(email, password);
    }

    public boolean logout() {
       return usersAPI.logout();
    }

    public void resetUsersAPI() {
        new UsersAPI();
    }

    public String getRole() {
        return usersAPI.getRole();
    }

}
