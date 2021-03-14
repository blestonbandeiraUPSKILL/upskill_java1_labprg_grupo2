package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class StartingPageUI implements Initializable {

    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private Stage adicionarStage;
    private Scene sceneRegistarOrganizacao;
    private Scene sceneGestor;
    private Scene sceneAdministrativo;
    private Scene sceneColaborador;
    private Scene sceneFreelancer;
    private Scene scene;

    @FXML TextField txtEmailLogin;
    @FXML PasswordField txtPasswordLogin;
    @FXML Button btnSair;
    @FXML Button btnLogin;
    @FXML Button btnGoDark;
    @FXML Button btnGoLight;
    @FXML Button btnRegistarOrganizacao;

    public String estilo = "/com/grupo2/t4j/style/app.css";

    /**
     * Initializes the controller (UI) class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        
        adicionarStage = new Stage();      
             
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

    }

    /**
     * Altera as cores da aplicacao para tema escuro
     * @param event 
     */
    @FXML
    void cssGoDark(ActionEvent event) {

        Scene scene = btnGoDark.getScene();
        
        estilo = "/com/grupo2/t4j/style/dark.css";       
        
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(estilo).toExternalForm());
    }

    /**
     * Altera as cores da aplicacao para tema claro
     * @param event 
     */
    @FXML
    void cssGoLight(ActionEvent event) {

        Scene scene = btnGoLight.getScene();
       
        estilo = "/com/grupo2/t4j/style/app.css";
            
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(estilo).toExternalForm());

    }
       
    /**
     * Navega para a pagina RegistarOrgEGestorUI
     * @param actionEvent 
     */
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

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }

    }

    /**
     * Faz o login do utilizador e navega para a pagina indicada de acordo com o papel do utilizador
     * @param event
     */
    public void login(ActionEvent event) {

        boolean login = gestaoUtilizadoresController.login(
                txtEmailLogin.getText(),
                txtPasswordLogin.getText());

        if (login) {
            txtEmailLogin.clear();
            txtPasswordLogin.clear();

            filtroRole();
        }
        else {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Dados inválidos",
                    "Não foi possível fazer o login.").show();
        }
    }

    /**
     * Faz o login do utilizador e navega para a pagina indicada de acordo com o papel do utilizador
     * @param keyEvent
     */
    public void loginEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            txtPasswordLogin.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {

                    boolean login = gestaoUtilizadoresController.login(
                            txtEmailLogin.getText(),
                            txtPasswordLogin.getText());

                    if (login) {
                        txtEmailLogin.clear();
                        txtPasswordLogin.clear();

                        filtroRole();
                    }
                    else {
                        AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                                MainApp.TITULO_APLICACAO,
                                "Dados inválidos",
                                "Não foi possível fazer o login.").show();
                    }
                }
            });
        }
    }

    private void filtroRole() {
        try {
            switch (gestaoUtilizadoresController.getRole()) {
                case 1:
                    navigateAdministrativoLogado();
                    break;
                case 2:
                    navigateColaboradorLogado();
                    break;
                case 3:
                    navigateFreelancerLogado();
                    break;
                case 4:
                    navigateGestorLogado();
                    break;
            }
        }
        catch (IOException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro ao validar os dados",
                    exception.getMessage()).show();
        }
    }

    /**
     * Sai da aplicacao
     * @param actionEvent 
     */
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

    /**
     * Navega para a pagina AdministrativoLogadoUI
     * @throws IOException
     */
    public void navigateAdministrativoLogado() throws IOException {
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

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage()).show();
        }
    }

    /** 
     * Navega para a pagina GestorLogadoUI
     * @throws IOException
     */
    public void navigateGestorLogado() throws IOException {
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

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage()).show();
        }
    }

    /**
     * Navega para a pagina ColaboradorLogadoUI
     * @throws IOException
     */
    public void navigateColaboradorLogado() throws IOException {
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

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage()).show();
        }
    }

    /**
     * Navega para a pagina FreelancerLogadoUI
     * @throws IOException
     */
    public void navigateFreelancerLogado() throws IOException {
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

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage()).show();
        }
    }


}
