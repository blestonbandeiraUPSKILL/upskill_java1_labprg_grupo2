package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.files.FileChooserT4J;
import com.grupo2.t4j.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ColaboradorLogadoUI implements Initializable {


    private RegistarCategoriaController registarCategoriaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarTarefaController registarTarefaController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private StartingPageUI startingPageUI;
    private Scene sceneStartingPage;
    private Stage adicionarStage;
    private Scene sceneEspecificarTarefa;

    private static final String CABECALHO_IMPORTAR = "Importar Lista.";

    @FXML Button btnLogout;
    @FXML Button btnImportAreaActividade;
    @FXML ComboBox<AreaActividade> cmbAreaActividade;
    @FXML ComboBox<AreaActividade> cmbAreaActividadeEspecificarTarefa;
    @FXML ComboBox<Categoria> cmbCategoriaTarefa;
    @FXML ListView<Tarefa> listViewTarefas;


    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCategoriaController = new RegistarCategoriaController();
        registarTarefaController = new RegistarTarefaController();
        gestaoUtilizadoresController = new GestaoUtilizadoresController();

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);
        adicionarStage.setResizable(false);

        try {
            cmbAreaActividade.getItems().setAll(registarAreaActividadeController.getAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        cmbAreaActividade.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               updateCmbCategoriasTarefaLista(event);
           }
        });

        ListView<Tarefa> listViewTarefas = new ListView<>();
        listViewTarefas.getItems().addAll(registarTarefaController.getAll());


    }

    public void updateListViewTarefas(ActionEvent actionEvent) {
        
    }
    
    public void updateCmbCategoriasTarefaLista(ActionEvent actionEvent) {
        List<Categoria> listaCategoriasTarefa =
                registarCategoriaController.findByAreaActividade(
                cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo());

        cmbCategoriaTarefa.getItems().addAll(listaCategoriasTarefa);
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


    public void navigateEspecificarTarefa(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/EspecificarTarefaScene.fxml"));
            Parent rootAddTarefa = loaderAddTarefa.load();
            sceneEspecificarTarefa = new Scene(rootAddTarefa);
            sceneEspecificarTarefa.getStylesheets().add("/com/grupo2/t4j/style/app.css");
            EspecificarTarefaUI especificarTarefaUIUI = loaderAddTarefa.getController();
            especificarTarefaUIUI.associarParentUI(this);

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }

        adicionarStage.setScene(sceneEspecificarTarefa);
        adicionarStage.setTitle("Especificar Tarefa");
        adicionarStage.show();

    }


    ////////////////// Ficheiros //////////////////

    public void importAreaActividade(ActionEvent actionEvent) throws SQLException {
        String descricao, extensao;

        descricao = "Ficheiro Area de Actividade";
        extensao = "*.txt";
        FileChooser fileChooser = FileChooserT4J.criarFileChooserT4J(descricao, extensao);
        File ficheiroImportar = fileChooser.showOpenDialog(btnImportAreaActividade.getScene().getWindow());

        if(ficheiroImportar != null) {
            int numeroAreasImportadas = 0;
            numeroAreasImportadas = registarAreaActividadeController.desserializar(ficheiroImportar);

            if(numeroAreasImportadas > 0) {
                cmbAreaActividadeEspecificarTarefa.getItems().setAll(registarAreaActividadeController.getAll());

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                        String.format("%d área(s) de actividade importada(s).", numeroAreasImportadas)).show();
            }
            else {
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                        "Ficheiro sem áreas de actividade para importar!").show();

            }
        }
        else {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                    "Não foi seleccionado nenhum ficheiro!").show();
        }
    }



}
