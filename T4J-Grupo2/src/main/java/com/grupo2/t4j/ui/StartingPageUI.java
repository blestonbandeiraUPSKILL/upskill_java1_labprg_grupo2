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
    private Scene sceneGestor;
    private Scene sceneAdministrativo;
    private Scene sceneColaborador;
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
            sceneLogin = new Scene(rootLogin);

            FXMLLoader loaderRegister = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/RegisterOrgScene.fxml"));
            Parent rootRegisterOrg = loaderRegister.load();
            sceneRegisterOrg = new Scene(rootRegisterOrg);

            FXMLLoader loaderGestor = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/GestorLogadoScene.fxml"));
            Parent rootGestor = loaderGestor.load();
            sceneGestor = new Scene(rootGestor);

            FXMLLoader loaderColaborador = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ColaboradorLogadoScene.fxml"));
            Parent rootColaborador = loaderColaborador.load();
            sceneColaborador = new Scene(rootColaborador);

            FXMLLoader loaderAdministrativo = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdministrativoLogadoScene.fxml"));
            Parent rootAdministrativo = loaderAdministrativo.load();
            sceneAdministrativo = new Scene(rootAdministrativo);

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

    public void login(ActionEvent actionEvent) throws IOException{

        if(usersAPIAdapter.login(txtEmailLogin.getText(), new Password(txtPasswordLogin.getText()))) {
            switch (applicationController.getRole()) {
                case "gestor":
                    adicionarStage.setScene(sceneGestor);
                    adicionarStage.setTitle("T4J - Gestor da Organização");
                    adicionarStage.show();
                    break;
                case "administrativo":
                    adicionarStage.setScene(sceneAdministrativo);
                    adicionarStage.setTitle("T4J - Administrativo");
                    adicionarStage.show();
                    break;
                case "colaborador":
                    adicionarStage.setScene(sceneColaborador);
                    adicionarStage.setTitle("T4J - Colaborador");
                    adicionarStage.show();
                    break;
            }
        }
        else {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro ao validar os dados",
                    "Dados de login inválidos");
        }
    }
}
