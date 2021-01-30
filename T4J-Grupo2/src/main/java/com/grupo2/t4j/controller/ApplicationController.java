package com.grupo2.t4j.controller;

import java.io.IOException;

import com.grupo2.t4j.ui.ApplicationController;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        ApplicationController.setRoot("secondary");
    }
}
