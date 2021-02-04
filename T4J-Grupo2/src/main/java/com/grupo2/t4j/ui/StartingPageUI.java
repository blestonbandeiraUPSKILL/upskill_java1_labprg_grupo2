package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.ApplicationController;
import com.grupo2.t4j.controller.RegistarOrganizacaoController;
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
    RegistarOrganizacaoController registarOrganizacaoController;
    private ApplicationController applicationController = new ApplicationController();
    private Stage adicionarStage;
    private Scene sceneRegistarOrganizacao;
    private Scene sceneLogin;
    private Scene sceneGestor;
    private Scene sceneAdministrativo;
    private Scene sceneColaborador;
    private Scene sceneConfirmarRegistoOrg;
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
            FXMLLoader loaderRegistarOrg = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/RegistarOrgEGestorScene.fxml"));
            Parent rootRegistarOrg = loaderRegistarOrg.load();
            sceneRegistarOrganizacao = new Scene(rootRegistarOrg);
            RegistarOrgEGestorUI registarOrgEGestorUI = loaderRegistarOrg.getController();
            registarOrgEGestorUI.associarParentUI(this);

//            FXMLLoader loaderConfirmarRegistoOrg = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConfirmarRegistoOrgScene.fxml"));
//            Parent rootConfirmarRegistoOrg = loaderConfirmarRegistoOrg.load();
//            sceneConfirmarRegistoOrg = new Scene(rootConfirmarRegistoOrg);
//            ConfirmarRegistoOrgUI confirmarRegistoOrgUI = loaderConfirmarRegistoOrg.getController();
//            confirmarRegistoOrgUI.associarParentUI(this);

            FXMLLoader loaderGestor = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/GestorLogadoScene.fxml"));
            Parent rootGestor = loaderGestor.load();
            sceneGestor = new Scene(rootGestor);
            GestorLogadoUI gestorLogadoUI = loaderGestor.getController();
            gestorLogadoUI.associarParentUI(this);

            FXMLLoader loaderColaborador = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ColaboradorLogadoScene.fxml"));
            Parent rootColaborador = loaderColaborador.load();
            sceneColaborador = new Scene(rootColaborador);
            /*ColaboradorLogadoUI colaboradorLogadoUI = loaderColaborador.getController();
            colaboradorLogadoUI.associarParentUI(this);*/

            FXMLLoader loaderAdministrativo = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdministrativoLogadoScene.fxml"));
            Parent rootAdministrativo = loaderAdministrativo.load();
            sceneAdministrativo = new Scene(rootAdministrativo);
           /* AdministrativoLogadoUI administrativoLogadoUI = loaderAdministrativo.getController();
            administrativoLogadoUI.associarParentUI(this);*/

            adicionarStage = new Stage();
            adicionarStage.initModality(Modality.APPLICATION_MODAL);;
            adicionarStage.setResizable(false);

            applicationController = new ApplicationController();

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

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        adicionarStage.setScene(sceneRegistarOrganizacao);
        adicionarStage.setTitle("Registar Organização");
        adicionarStage.show();
    }

    public void login(ActionEvent actionEvent) {

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
