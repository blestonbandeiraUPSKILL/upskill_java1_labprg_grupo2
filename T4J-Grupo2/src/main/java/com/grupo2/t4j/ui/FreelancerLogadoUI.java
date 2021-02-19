package com.grupo2.t4j.ui;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FreelancerLogadoUI implements Initializable {

    private StartingPageUI startingPageUI;
    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void verAnuncioAction(ActionEvent event) {

    }
}
