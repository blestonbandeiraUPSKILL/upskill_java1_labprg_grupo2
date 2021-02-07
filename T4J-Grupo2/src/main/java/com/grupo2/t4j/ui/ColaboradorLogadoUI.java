package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarCompetenciaTecnicaController;
import com.grupo2.t4j.controller.RegistarTarefaController;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.model.Tarefa;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.geom.Area;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class ColaboradorLogadoUI implements Initializable {
    
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


}
