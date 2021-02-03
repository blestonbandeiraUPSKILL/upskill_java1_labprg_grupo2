package com.grupo2.t4j.ui;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AdministrativoLogadoUI implements Initializable {

    private StartingPageUI startingPageUI;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }
}
