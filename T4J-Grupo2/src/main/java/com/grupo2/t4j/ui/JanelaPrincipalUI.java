package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.ApplicationController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JanelaPrincipalUI implements Initializable {

    private ApplicationController applicationController;
    private Stage adicionarStage;
    private Scene sceneTarefa;
    private Scene sceneColaborador;

    public ApplicationController getApplicationController() {
        return applicationController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loaderTarefa = new FXMLLoader(getClass().getResource("com/grupo2/t4j/fxml/TarefaScene.fxml"));
            Parent rootTarefa = loaderTarefa.load();

            FXMLLoader loaderColaborador = new FXMLLoader(getClass().getResource("com/grupo2/t4j/fxml/ColaboradorScene.fxml"));
            Parent rootColaborador = loaderColaborador.load();

            sceneTarefa = new Scene(rootTarefa);
            sceneColaborador = new Scene(rootColaborador);

            adicionarStage = new Stage();
            adicionarStage.initModality(Modality.APPLICATION_MODAL);;
            adicionarStage.setResizable(false);

            applicationController = new ApplicationController();

            TarefaUI tarefaUI = loaderTarefa.getController();
            tarefaUI.associarParentUI(this);
        }
        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }
}
