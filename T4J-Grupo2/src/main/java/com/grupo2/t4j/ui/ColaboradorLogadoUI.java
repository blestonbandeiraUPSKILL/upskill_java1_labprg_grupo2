package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.controller.RegistarCategoriaController;
import com.grupo2.t4j.controller.RegistarCompetenciaTecnicaController;
import com.grupo2.t4j.controller.RegistarTarefaController;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.model.Tarefa;
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
import java.util.ResourceBundle;

public class ColaboradorLogadoUI implements Initializable {


    private RegistarCategoriaController registarCategoriaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarTarefaController registarTarefaController;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    private StartingPageUI startingPageUI;
    private Scene sceneStartingPage;
    private Stage adicionarStage;

    @FXML Button btnSairListaTarefas;
    @FXML ComboBox<AreaActividade> cmbAreaActividadeListaTarefas;
    @FXML ComboBox<AreaActividade> cmbAreaActividadeEspecificarTarefa;
    @FXML ComboBox<Categoria> cmbCategoriaTarefaListaTarefas;
    @FXML ComboBox<Categoria> cmbCategoriaTarefaEspecificarTarefa;
    @FXML ListView<Tarefa> listViewTarefas;
    @FXML ListView<CompetenciaTecnica> listViewCompetenciasTecnicas;

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCategoriaController = new RegistarCategoriaController();
        registarTarefaController = new RegistarTarefaController();
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

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
                cmbAreaActividadeEspecificarTarefa.getSelectionModel().getSelectedItem()));


    }

    @FXML
    void navigateStartingPage(ActionEvent event) {
        try {
            FXMLLoader loaderStartingPage = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/StartingPageScene.fxml"));
            Parent rootStartingPage = loaderStartingPage.load();
            sceneStartingPage = new Scene(rootStartingPage);

            Window window = btnSairListaTarefas.getScene().getWindow();
            window.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                            MainApp.TITULO_APLICACAO,
                            "Confirmação da acção",
                            "Tem a certeza que pretende terminar a sessão??");

                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        windowEvent.consume();
                    }
                }
            });
            window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));

            adicionarStage.setScene(sceneStartingPage);
            adicionarStage.setTitle(MainApp.TITULO_APLICACAO);
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
