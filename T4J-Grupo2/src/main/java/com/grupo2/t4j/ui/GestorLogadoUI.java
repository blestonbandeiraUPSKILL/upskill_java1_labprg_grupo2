package com.grupo2.t4j.ui;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.files.FicheiroRepositorioColaborador;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.inmemory.RepositorioColaboradorInMemory;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class GestorLogadoUI implements Initializable {
    
    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    private Scene sceneStartingPage;
    private Scene sceneAddColaborador;
    private RegistarColaboradorController registarColaboradorController;
    private RegistarCategoriaController registarCategoriaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarTarefaController registarTarefaController;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    
    private FicheiroRepositorioColaborador ficheiroC;
    private RepositorioColaboradorInMemory repositorioColaboradorInMemory;

    @FXML ListView<Colaborador> listViewColaboradores;
    @FXML ListView<Tarefa> listViewTarefas;
    @FXML Button btnLogout;
       
    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        registarColaboradorController = new RegistarColaboradorController();
        gestaoUtilizadoresController = new GestaoUtilizadoresController();

        try {
            updateListViewColaboradores();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }


        /*registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCategoriaController = new RegistarCategoriaController();
        registarTarefaController = new RegistarTarefaController();
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();*/

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        /*
        cmbAreaActividadeListaTarefas.getItems().setAll(registarAreaActividadeController.getAreasActividade());
        cmbCategoriaTarefaListaTarefas.getItems().setAll(
                registarCategoriaController.getCategoriasByAreaActividade(
                        cmbAreaActividadeListaTarefas.getSelectionModel().getSelectedItem()));

        cmbAreaActividadeEspecificarTarefa.getItems().setAll(registarAreaActividadeController.getAreasActividade());
        cmbCategoriaTarefaEspecificarTarefa.getItems().setAll(
                registarCategoriaController.getCategoriasByAreaActividade(
                        cmbAreaActividadeEspecificarTarefa.getSelectionModel().getSelectedItem()));

        ListView listViewTarefas = new ListView();
        ListView listViewCompetenciasTecnicas = new ListView();
        listViewTarefas.getItems().addAll(registarTarefaController.getListTarefas());
        listViewCompetenciasTecnicas.getItems().addAll(registarCompetenciaTecnicaController.getCompetenciasTecnicasByAreaActividade(
                cmbAreaActividadeEspecificarTarefa.getSelectionModel().getSelectedItem()));*/
    }

    public void updateListViewColaboradores() throws SQLException {
        listViewColaboradores.getItems().setAll(registarColaboradorController.getAll());

    }

    public void logout(ActionEvent actionEvent) {
        Window window = btnLogout.getScene().getWindow();
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

    public void navigateAddColaborador(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddColaborador = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/RegistarColaboradorScene.fxml"));
            Parent rootAddColaborador = loaderAddColaborador.load();
            sceneAddColaborador = new Scene(rootAddColaborador);
            sceneAddColaborador.getStylesheets().add("/com/grupo2/t4j/style/app.css");
            RegistarColaboradorUI registarColaboradorUI = loaderAddColaborador.getController();
            registarColaboradorUI.associarParentUI(this);

        }
        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
        adicionarStage.setScene(sceneAddColaborador);
        adicionarStage.setTitle("Registar Colaborador");
        adicionarStage.show();
    }
}
