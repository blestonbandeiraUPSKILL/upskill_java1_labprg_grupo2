package com.grupo2.t4j.ui;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;

public class TarefaUI implements Initializable {

    private StartingPageUI startingPageUI;

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void encerrarTarefaUI(ActionEvent actionEvent) {
        //limpar os campos

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }


}
