package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.Organizacao;
import com.grupo2.t4j.model.Password;
import com.grupo2.t4j.model.Plataforma;
import com.grupo2.t4j.repository.RepositorioOrganizacao;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
