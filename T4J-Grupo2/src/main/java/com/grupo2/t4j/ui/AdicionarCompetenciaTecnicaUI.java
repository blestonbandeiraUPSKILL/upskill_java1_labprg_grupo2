/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.controller.RegistarCompetenciaTecnicaController;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.model.GrauProficiencia;
import java.net.URL;
import java.util.ResourceBundle;
//import com.grupo2.t4j.repository.RepositorioAreaActividade;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

/**
 *
 * @author acris
 */
public class AdicionarCompetenciaTecnicaUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private Stage adicionarStage;
    private Scene sceneAddGrauProficiencia;

    @FXML
    Button btnConfirmar;
    @FXML
    Button btnCancelar;
    @FXML
    TextArea txtDescDetalhada;
    @FXML
    TextField txtCodigo;
    @FXML
    TextArea txtDescricaoBreve;
    @FXML
    ComboBox<AreaActividade> cmbAreaActividade;
    @FXML
    ComboBox<GrauProficiencia> cmbGrauProficiencia;
    @FXML
    ListView<GrauProficiencia> listViewGrausAplicaveis;

    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    public void initialize(URL location, ResourceBundle resources) {

        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
        cmbAreaActividade.getItems().addAll(registarAreaActividadeController.getAll());

    }

    @FXML
    public void registarCompetenciaTecnicaAction(ActionEvent event) {
        try {
            boolean adicionou = registarCompetenciaTecnicaController.registarCompetenciaTecnica(
                    txtCodigo.getText().trim(),
                    txtDescricaoBreve.getText().trim(),
                    txtDescDetalhada.getText().trim(),
                    cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo()
            );

            if (adicionou) {
                administrativoLogadoUI.listViewCompetenciasTecnicas.getItems().addAll();
                try {
                    FXMLLoader loaderAddGrauProficiencia = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdicionarGrauProficiencia.fxml"));
                    Parent rootAddGrauProficiencia = loaderAddGrauProficiencia.load();
                    sceneAddGrauProficiencia = new Scene(rootAddGrauProficiencia);
                    sceneAddGrauProficiencia.getStylesheets().add("/com/grupo2/t4j/style/app.css");
                    AdicionarGrauProficienciaUI adicionarGrauProficienciaUI = loaderAddGrauProficiencia.getController();
                    adicionarGrauProficienciaUI.associarParentUI(this);

                } catch (IOException exception) {
                    exception.printStackTrace();
                    AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                            MainApp.TITULO_APLICACAO,
                            "Erro",
                            exception.getMessage());
                }
                adicionarStage.setScene(sceneAddGrauProficiencia);
                adicionarStage.setTitle("Adicionar Grau de Proficiência");
                adicionarStage.show();
            }

            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Registar Competência Técnica.",
                    adicionou ? "Competencia Tecnica registada com sucesso."
                            : "Não foi possível registar a Competência Técncia.").show();
        } catch (IllegalArgumentException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    iae.getMessage()).show();
        }

        closeAddCompetenciaTecnica(event);
    }

    private void closeAddCompetenciaTecnica(ActionEvent event) {
        this.txtCodigo.clear();
        this.txtDescricaoBreve.clear();
        this.txtDescDetalhada.clear();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void cancelarAction(ActionEvent actionEvent) {
        Window window = btnCancelar.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que quer voltar à página anterior, cancelando o actual registo?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                }
            }
        });
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    /*public void addGrauAplicavelAction(ActionEvent actionEvent) {

        try {
            FXMLLoader loaderAddGrauProficiencia = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdicionarGrauProficiencia.fxml"));
            Parent rootAddGrauProficiencia = loaderAddGrauProficiencia.load();
            sceneAddGrauProficiencia = new Scene(rootAddGrauProficiencia);
            sceneAddGrauProficiencia.getStylesheets().add("/com/grupo2/t4j/style/app.css");
            AdicionarGrauProficienciaUI adicionarGrauProficienciaUI = loaderAddGrauProficiencia.getController();
            adicionarGrauProficienciaUI.associarParentUI(this);

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
        adicionarStage.setScene(sceneAddGrauProficiencia);
        adicionarStage.setTitle("Adicionar Grau de Proficiência");
        adicionarStage.show();

    }*/
}
