package com.grupo2.t4j.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmarRegistoOrgUI implements Initializable {

    private StartingPageUI startingPageUI;

    @FXML
    TextField txtConfNomeOrganizacao;
    @FXML
    TextField txtConfNif;
    @FXML
    TextField txtConfTelefoneOrganizacao;
    @FXML
    TextField txtConfWebsite;
    @FXML
    TextField txtConfEmailOrganizacao;
    @FXML
    TextField txtConfEndArruamento;
    @FXML
    TextField txtConfEndPorta;
    @FXML
    TextField txtConfEndLocalidade;
    @FXML
    TextField txtConfEndCodPostal;
    @FXML
    TextField txtConfNomeGestor;
    @FXML
    TextField txtConfTelefoneGestor;
    @FXML
    TextField txtConfEmailGestor;

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void voltarPaginaRegistoComDados(ActionEvent actionEvent) {
    }

    public void gravarOrganizacao(ActionEvent actionEvent) {
    }
}
