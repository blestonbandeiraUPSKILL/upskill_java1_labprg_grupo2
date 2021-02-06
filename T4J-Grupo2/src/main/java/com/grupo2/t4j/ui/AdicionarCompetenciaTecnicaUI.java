/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarCompetenciaTecnicaController;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.GrauProficiencia;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 *
 * @author acris
 */
public class AdicionarCompetenciaTecnicaUI {
    @FXML
    private Button btnConfirmar;

    @FXML
    private TextArea txtDescricaoDetalhada;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<AreaActividade> cmbAreaActividade;

    @FXML
    private ComboBox<GrauProficiencia> cmbGrauProficiencia;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtDescricaoBreve;
    
    private StartingPageUI startingPageUI;
    
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    public void initialize(URL location, ResourceBundle resources) {
        RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();
        
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
    public void addCompetenciaTecnicaAction(ActionEvent event) {
        try {
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();
        boolean adicionou = registarCompetenciaTecnicaController.registarCompetenciaTecnica(
                txtCodigo.getText(), txtDescricaoBreve.getText(), txtDescricaoDetalhada.getText(),
                cmbAreaActividade.getSelectionModel().getSelectedItem(),
                cmbGrauProficiencia.getSelectionModel().getSelectedItem());
        AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registar Competencia Tecnica.",
                    adicionou ? "Competencia Tecnica registada com sucesso."
                            : "Não foi possível registar a Competencia Tecncia.").show();
        } catch (IllegalArgumentException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    iae.getMessage()).show();
        }
    }
}