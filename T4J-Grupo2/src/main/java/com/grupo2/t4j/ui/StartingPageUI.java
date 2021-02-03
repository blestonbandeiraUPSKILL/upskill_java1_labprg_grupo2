package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.ApplicationController;
import com.grupo2.t4j.model.Password;
import com.grupo2.t4j.model.UsersAPIAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartingPageUI implements Initializable {

    UsersAPIAdapter usersAPIAdapter;
    private ApplicationController applicationController;
    private Stage adicionarStage;
    private Scene sceneRegisterOrg;
    private Scene sceneLogin;
    @FXML
    TextField txtEmailLogin;
    @FXML
    TextField txtPasswordLogin;

    public ApplicationController getApplicationController() {
        return applicationController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/JanelaPrincipalScene.fxml"));
            Parent rootLogin = loaderLogin.load();

            FXMLLoader loaderRegister = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/RegisterOrgScene.fxml"));
            Parent rootRegisterOrg = loaderRegister.load();

            FXMLLoader loaderGestor = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/GestorLogadoScene.fxml"));

            sceneLogin = new Scene(rootLogin);
            sceneRegisterOrg = new Scene(rootRegisterOrg);

            adicionarStage = new Stage();
            adicionarStage.initModality(Modality.APPLICATION_MODAL);;
            adicionarStage.setResizable(false);

            applicationController = new ApplicationController();

            JanelaPrincipalUI janelaPrincipalUI = loaderLogin.getController();
            janelaPrincipalUI.associarParentUI(this);
        }
        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

    public void registarOrganizacao(ActionEvent actionEvent) {
        adicionarStage.setScene(sceneRegisterOrg);
        adicionarStage.setTitle("Registar Organização");
        adicionarStage.show();
    }

    public void login(ActionEvent actionEvent) {
        usersAPIAdapter.login(txtEmailLogin.getText(), new Password(txtPasswordLogin.getText()));
        switch (applicationController.getRole()) {
            case "gestor":
                adicionarStage.setScene(sceneGestor);
                adicionarStage.setTitle("T4J - Gestor da Organização");
                adicionarStage.show();
        }
    }
}
