package com.grupo2.t4j.controller;

import com.grupo2.t4j.model.Organizacao;
import com.grupo2.t4j.model.Password;
import com.grupo2.t4j.model.Plataforma;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

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

    public void preencherDados(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Organizacao organizacao = (Organizacao) stage.getUserData();

    }
}
