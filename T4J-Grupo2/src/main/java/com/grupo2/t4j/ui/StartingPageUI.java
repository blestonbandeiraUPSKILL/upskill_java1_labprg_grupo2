package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.ApplicationController;
import com.grupo2.t4j.controller.RegistarOrganizacaoController;
import com.grupo2.t4j.model.Password;
import com.grupo2.t4j.model.UsersAPIAdapter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartingPageUI implements Initializable {

    UsersAPIAdapter usersAPIAdapter;
    private ApplicationController applicationController;
    private Stage adicionarStage;
    private Scene sceneRegistarOrganizacao;
    private Scene sceneLogin;
    private Scene sceneGestor;
    private Scene sceneAdministrativo;
    private Scene sceneColaborador;
    @FXML TextField txtEmailLogin;
    @FXML TextField txtPasswordLogin;
    @FXML Button btnSair;
    @FXML Button btnAdministrativoLogado;

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

    public void sairApp(ActionEvent actionEvent) {
        Window window = btnSair.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que quer sair da aplicação?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                }
            }
        });
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void navigateAdministrativoLogado(ActionEvent actionEvent) throws IOException {
       try {
           FXMLLoader loaderAdministrativo = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdministrativoLogadoScene.fxml"));
           Parent rootAdministrativo = loaderAdministrativo.load();
           sceneAdministrativo = new Scene(rootAdministrativo);
           AdministrativoLogadoUI administrativoLogadoUI = loaderAdministrativo.getController();
           administrativoLogadoUI.associarParentUI(this);

           adicionarStage.setScene(sceneAdministrativo);
           adicionarStage.setTitle("T4J - Administrativo");
           adicionarStage.show();
           btnAdministrativoLogado.getScene().getWindow().hide();
       }
       catch (IOException exception) {
           exception.printStackTrace();
           AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                   MainApp.TITULO_APLICACAO,
                   "Erro",
                   exception.getMessage());
       }
    }

    public void navigateGestorLogado(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderGestor = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/GestorLogadoScene.fxml"));
            Parent rootGestor = loaderGestor.load();
            sceneGestor = new Scene(rootGestor);
            GestorLogadoUI gestorLogadoUI = loaderGestor.getController();
            gestorLogadoUI.associarParentUI(this);

            adicionarStage.setScene(sceneGestor);
            adicionarStage.setTitle("T4J - Gestor da Organização");
            adicionarStage.show();
        }
        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

    public void navigateColaboradorLogado(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderColaborador = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ColaboradorLogadoScene.fxml"));
            Parent rootColaborador = loaderColaborador.load();
            sceneColaborador = new Scene(rootColaborador);
            ColaboradorLogadoUI colaboradorLogadoUI = loaderColaborador.getController();
            colaboradorLogadoUI.associarParentUI(this);

            adicionarStage.setScene(sceneColaborador);
            adicionarStage.setTitle("T4J - Colaborador");
            adicionarStage.show();

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
