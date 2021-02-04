package com.grupo2.t4j.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmarRegistoOrgUI implements Initializable {

    private StartingPageUI startingPageUI;

    @FXML TextField txtConfNomeOrganizacao;



    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
