package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.Password;
import com.grupo2.t4j.model.Plataforma;

public class ApplicationController {

    public boolean login(String email, Password password) {
        return Plataforma.getInstance().getUsersAPI().login(email, password);
    }

    public boolean logout() {
        return Plataforma.getInstance().getUsersAPI().logout();
    }

    public String getRole() {
        return Plataforma.getInstance().getUsersAPI().getRole();
    }
}
