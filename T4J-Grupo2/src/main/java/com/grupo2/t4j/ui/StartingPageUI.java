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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
     * @param actionEvent
     * @throws IOException
     * @throws SQLException 
     */
    public void login(ActionEvent actionEvent) throws IOException, SQLException {

        boolean login = gestaoUtilizadoresController.login(
                txtEmailLogin.getText(),
                txtPasswordLogin.getText());

        try {
            if (login) {
                txtEmailLogin.clear();
                txtPasswordLogin.clear();

                switch (gestaoUtilizadoresController.getRole()) {
                    case 1:
                        navigateAdministrativoLogado(actionEvent);
                        break;
                    case 2:
                        navigateColaboradorLogado(actionEvent);
                        break;
                    case 3:
                        navigateFreelancerLogado(actionEvent);
                        break;
                    case 4:
                        navigateGestorLogado(actionEvent);
                        break;
                }
            }
        } catch (IOException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro ao validar os dados",
                    exception.getMessage());

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
     * @param actionEvent
     * @throws IOException 
     */
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
        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

    /** 
     * Navega para a pagina GestorLogadoUI
     * @param actionEvent 
     */
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
        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

    /**
     * Navega para a pagina ColaboradorLogadoUI
     * @param actionEvent 
     */
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
        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

    /**
     * Navega para a pagina FreelancerLogadoUI
     * @param actionEvent
     * @throws IOException 
     */
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
        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }
}
