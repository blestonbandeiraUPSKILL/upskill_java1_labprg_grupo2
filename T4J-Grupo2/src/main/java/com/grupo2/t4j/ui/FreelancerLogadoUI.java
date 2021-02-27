package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.EfectuarCandidaturaController;
import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarAnuncioController;
import com.grupo2.t4j.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 */
public class FreelancerLogadoUI implements Initializable {

    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    private Scene sceneStartingPage;
    private Scene sceneEfectuarCandidatura;

    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private RegistarAnuncioController registarAnuncioController;
    private EfectuarCandidaturaController efectuarCandidaturaController;
         
    @FXML
    ListView<Anuncio> listViewAnuncios;
    @FXML private ListView<Candidatura> listViewCandidaturas;
    @FXML private ComboBox<Anuncio> cmbAnuncio;
    @FXML private TextField txtValor;
    @FXML private TextField txtDias;
    @FXML private TextField txtApresentacao;
    @FXML private TextField txtMotivacao;
    @FXML private Button btnAddCandidatura;
    @FXML Button btnSair;


    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    /**
    * Initializes the controller (UI) class.
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            efectuarCandidaturaController = new EfectuarCandidaturaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        gestaoUtilizadoresController = new GestaoUtilizadoresController();

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
        
        try {
            updateListViewAnuncio();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    public String getEmail() {
        return gestaoUtilizadoresController.getEmail();
    }

    @FXML
    void verAnuncioAction(ActionEvent event) {

    }
        
    public void updateListViewAnuncio() throws SQLException {
        String email = gestaoUtilizadoresController.getEmail();
        listViewAnuncios.getItems().setAll(efectuarCandidaturaController.findAnunciosElegiveis(email));
    }

    public void logout(ActionEvent actionEvent) {
        Window window = btnSair.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que pretende terminar a sessão?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                }
                else {
                    boolean logout = gestaoUtilizadoresController.logout();
                    if (logout) {
                        gestaoUtilizadoresController.resetUsersAPI();

                        FXMLLoader loaderStartingPage = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/StartingPageScene.fxml"));
                        Parent rootStartingPage = null;
                        try {
                            rootStartingPage = loaderStartingPage.load();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                        sceneStartingPage = new Scene(rootStartingPage);
                        
                        sceneStartingPage.getStylesheets().add(startingPageUI.estilo);
                        adicionarStage.setScene(sceneStartingPage);
                        adicionarStage.setTitle(MainApp.TITULO_APLICACAO);
                        adicionarStage.show();
                    } else {
                        AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                                MainApp.TITULO_APLICACAO,
                                "Erro",
                                "Não foi possível terminar a sessão.");
                    }
                }
            }
        });
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));


    }

    public void navigateEfectuarCandidatura(ActionEvent actionEvent) {

        try {
            FXMLLoader loaderEfectuarCandidatura = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/EfectuarCandidaturaScene.fxml"));
            Parent rootEfectuarCandidatura = loaderEfectuarCandidatura.load();
            EfectuarCandidaturaUI efectuarCandidaturaUI = loaderEfectuarCandidatura.getController();
            efectuarCandidaturaUI.associarParentUI(this);
            efectuarCandidaturaUI.transferData();
            sceneEfectuarCandidatura = new Scene(rootEfectuarCandidatura);

            adicionarStage.setScene(sceneEfectuarCandidatura);
            adicionarStage.setTitle("Efectuar Candidatura");
            adicionarStage.show();
        }

        catch (IOException | SQLException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }
}
