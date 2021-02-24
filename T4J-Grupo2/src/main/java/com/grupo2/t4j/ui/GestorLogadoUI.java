package com.grupo2.t4j.ui;

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
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class GestorLogadoUI implements Initializable {

    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    private Scene sceneStartingPage;
    private Scene sceneAddColaborador;
    private Scene sceneAddTarefa;
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
    @FXML ComboBox<AreaActividade> cmbAreaActividade;
    @FXML ComboBox<Categoria> cmbCategoriaTarefa;
    @FXML Button btnLogout;
       
    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarTarefaController = new RegistarTarefaController();
        registarCategoriaController = new RegistarCategoriaController();
        registarColaboradorController = new RegistarColaboradorController();
        gestaoUtilizadoresController = new GestaoUtilizadoresController();

        try {
            updateListViewColaboradores();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            updateListViewTarefas();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            cmbAreaActividade.getItems().setAll(
                    registarAreaActividadeController.getAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        cmbAreaActividade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateCmbCategoriaTarefa(event);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

            }
        });

        cmbCategoriaTarefa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateListViewTarefasComFiltro();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

            }
        });

    }

    public void updateListViewTarefasComFiltro() throws SQLException {
        String codigoCategoria = cmbCategoriaTarefa.getSelectionModel().getSelectedItem().getCodigoCategoria();
        listViewTarefas.getItems().setAll(
                registarTarefaController.findByCategoria(codigoCategoria)
        );

    }

    public void updateListViewTarefas() throws SQLException {
        listViewTarefas.getItems().setAll(registarTarefaController.getAll(
                getNifOrganizacao()));

    }

    private void updateCmbCategoriaTarefa(ActionEvent event) throws SQLException {
        String codigoAreaActividade = cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo();
        cmbCategoriaTarefa.getItems().setAll(
                registarCategoriaController.findByCodigo(codigoAreaActividade));

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

    public String getNifOrganizacao() throws SQLException {
        return registarColaboradorController.getNifOrganizacao(
                gestaoUtilizadoresController.getEmail());
    }

    public void navigateAddColaborador(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddColaborador = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/RegistarColaboradorScene.fxml"));
            Parent rootAddColaborador = loaderAddColaborador.load();
            sceneAddColaborador = new Scene(rootAddColaborador);
            //sceneAddColaborador.getStylesheets().add("/com/grupo2/t4j/style/app.css");
            RegistarColaboradorUI registarColaboradorUI = loaderAddColaborador.getController();
            registarColaboradorUI.associarParentUI(this);
            registarColaboradorUI.transferNif();

            adicionarStage.setScene(sceneAddColaborador);
            adicionarStage.setTitle("Registar Colaborador");
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

    public void navigateAddTarefa(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/EspecificarTarefaGestorScene.fxml"));
            Parent rootAddTarefa = loaderAddTarefa.load();
            sceneAddTarefa = new Scene(rootAddTarefa);
            //sceneAddTarefa.getStylesheets().add("/com/grupo2/t4j/style/app.css");
            EspecificarTarefaGestorUI especificarTarefaUI = loaderAddTarefa.getController();
            especificarTarefaUI.associarParentUI(this);

            adicionarStage.setScene(sceneAddTarefa);
            adicionarStage.setTitle("Especificar Tarefa");
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
