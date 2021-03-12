package com.grupo2.t4j.controller;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.persistence.FabricaRepositorios;
import com.grupo2.t4j.persistence.database.FabricaRepositoriosDatabase;

public class GestaoUtilizadoresController {

    private UsersAPI usersAPI = UsersAPI.getInstance();


    public boolean login(String email, String password) {
        return usersAPI.login(email, password);
    }

    public boolean logout() {
       return usersAPI.logout();
    }

    public void resetUsersAPI() {
        usersAPI.resetContext();
    }

    public int getRole() {
        return usersAPI.getRole();
    }

    public String getEmail() {
        return usersAPI.getEmail();
    }


}
