package com.grupo2.t4j.ui;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StartingPageUI implements Initializable {

    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private Stage adicionarStage;
    private Scene sceneRegistarOrganizacao;
    private Scene sceneGestor;
    private Scene sceneAdministrativo;
    private Scene sceneColaborador;
    private Scene sceneFreelancer;

    @FXML TextField txtEmailLogin;
    @FXML PasswordField txtPasswordLogin;
    @FXML Button btnSair;
    @FXML Button btnLogin;
    @FXML Button btnGoDark;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        gestaoUtilizadoresController = new GestaoUtilizadoresController();


        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);


    }

    public void registarOrganizacao(ActionEvent actionEvent) {

        try {
        FXMLLoader loaderRegistarOrg = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/RegistarOrgEGestorScene.fxml"));
        Parent rootRegistarOrg = loaderRegistarOrg.load();
        sceneRegistarOrganizacao = new Scene(rootRegistarOrg);
        RegistarOrgEGestorUI registarOrgEGestorUI = loaderRegistarOrg.getController();
        registarOrgEGestorUI.associarParentUI(this);

        adicionarStage.setScene(sceneRegistarOrganizacao);
        adicionarStage.setTitle("Registar Organização");
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

    public void login(ActionEvent actionEvent) throws IOException, SQLException {


        boolean login = gestaoUtilizadoresController.login(
                txtEmailLogin.getText(),
                txtPasswordLogin.getText());

        try {
            if (login) {
                txtEmailLogin.clear();
                txtPasswordLogin.clear();

                switch (gestaoUtilizadoresController.getRole()) {
                    case "gestor":
                        navigateGestorLogado(actionEvent);
                        break;
                    case "administrativo":
                        navigateAdministrativoLogado(actionEvent);
                        break;
                    case "colaborador":
                        navigateColaboradorLogado(actionEvent);
                        break;
                    case "freelancer":
                        navigateFreelancerLogado(actionEvent);
                }
            }
        }

        catch (IOException exception) {
                AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                MainApp.TITULO_APLICACAO,
                "Erro ao validar os dados",
                exception.getMessage());

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
           btnLogin.getScene().getWindow().hide();
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

            btnLogin.getScene().getWindow().hide();
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

            btnLogin.getScene().getWindow().hide();
        }
        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

    public void navigateFreelancerLogado(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loaderFreelancer = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/FreelancerLogadoScene.fxml"));
            Parent rootFreelancer = loaderFreelancer.load();
            sceneFreelancer = new Scene(rootFreelancer);
            FreelancerLogadoUI freelancerLogadoUI = loaderFreelancer.getController();
            freelancerLogadoUI.associarParentUI(this);

            adicionarStage.setScene(sceneFreelancer);
            adicionarStage.setTitle("T4J - Freelancer");
            adicionarStage.show();
            btnLogin.getScene().getWindow().hide();
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
