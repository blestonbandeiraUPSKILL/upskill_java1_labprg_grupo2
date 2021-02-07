package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarCompetenciaTecnicaController;
import com.grupo2.t4j.controller.RegistarTarefaController;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.Categoria;
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

public class ColaboradorLogadoUI2 implements Initializable {
    
    private RegistarTarefaController registarTarefaController;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    private StartingPageUI startingPageUI;
    private Scene sceneStartingPage;
    private Stage adicionarStage;
    
    @FXML ComboBox<AreaActividade> cmbAreaActividade;
    @FXML ComboBox<Categoria> cmbCategoriaTarefa;
    @FXML ListView<CaracterizacaoCT> listViewCompTec;
    @FXML Button btnRegistarTarefa;
    @FXML Button btnCancelar;
    @FXML Button btnSairListaTarefas;
    @FXML Button btnAddAreaAtividade;
    @FXML TextField txtReferencia;
    @FXML TextField txtDesignacao;
    @FXML TextField txtDuracao;
    @FXML TextField txtCusto;
    @FXML TextArea txtDescricaoInformal;
    @FXML TextArea txtDescricaoTecnica;
    @FXML ComboBox<AreaActividade> cmbArAct;
    @FXML ComboBox<Categoria> cmbCategoria;
    @FXML ListView<Tarefa> listViewTarefas;

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        registarTarefaController = new RegistarTarefaController();
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();
        
        if(registarTarefaController.getListaAreasActividade() == null) {
            cmbAreaActividade.setValue(null);
        }
        else {
            cmbAreaActividade.getItems().setAll(registarTarefaController.getListaAreasActividade());
        }

    }
    
    @FXML
    private void selectAreaActAction(ActionEvent event) {
        AreaActividade areaActividade = cmbAreaActividade.getSelectionModel().getSelectedItem();
        cmbCategoriaTarefa.getItems().setAll(registarTarefaController.getListaCategoriaPorAreaActividade(areaActividade));
    }

    @FXML
    private void selectCatTarAction(ActionEvent event) {
        Categoria categoriaTarefa = cmbCategoriaTarefa.getSelectionModel().getSelectedItem();
        listViewCompTec.getItems().add(registarCompetenciaTecnicaController.getCompetenciasTecnicasByCategoria(categoriaTarefa));
    }

    @FXML
    public void registarTarefaAction(ActionEvent event) {
        try {
            registarTarefaController = new RegistarTarefaController();
            boolean adicionou = registarTarefaController.registarTarefa(
                    cmbAreaActividade.getSelectionModel().getSelectedItem(),
                    cmbCategoriaTarefa.getSelectionModel().getSelectedItem(),
                    txtReferencia.getText(),
                    txtDesignacao.getText(),
                    txtDescricaoInformal.getText(),
                    txtDescricaoTecnica.getText(),
                    txtDuracao.getText(),
                    txtCusto.getText());
            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registar tarefa.",
                        adicionou ? "Tarefa registada com sucesso."
                                : "Não foi possível registar a tarefa.").show();
        }
        catch (IllegalArgumentException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    iae.getMessage()).show();
        }
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

        /*
        btnAddAreaAtividade.getScene().getWindow().hide();*/
    }

    @FXML
    private void CancelarAction(ActionEvent event) {
        Window window = btnCancelar.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que quer voltar à página inicial, cancelando o actual registo?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                }
            }
        });

        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    private void selectArAct(ActionEvent event) {
        AreaActividade arAct = cmbArAct.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void selectCategoriaAction(ActionEvent event) {
        Categoria categoria = cmbCategoria.getSelectionModel().getSelectedItem();

    }

    public void updateListViewTarefas(ActionEvent actionEvent) {
    }

    public void navigateAddTarefa(ActionEvent actionEvent) {

    }

    /*public void registarTarefaAction(ActionEvent actionEvent) {
    }*/
}
