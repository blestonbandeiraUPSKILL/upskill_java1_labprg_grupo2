package com.grupo2.t4j.ui;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;

public class TarefaUI implements Initializable {

    private JanelaPrincipalUI janelaPrincipalUI;

    public void associarParentUI(JanelaPrincipalUI janelaPrincipalUI) {
        this.janelaPrincipalUI = janelaPrincipalUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void encerrarTarefaUI(ActionEvent actionEvent) {
        //limpar os campos

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }


}
