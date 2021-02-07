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

import com.grupo2.t4j.repository.RepositorioAreaActividade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
    private Stage adicionarStage;

    @FXML Button btnConfirmar;
    @FXML Button btnCancelar;
    @FXML TextArea txtDescricaoDetalhada;
    @FXML TextField txtCodigo;
    @FXML TextField txtDescricaoBreve;
    @FXML ComboBox<AreaActividade> cmbAreaActividade;
    @FXML ComboBox<GrauProficiencia> cmbGrauProficiencia;

    

    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    public void initialize(URL location, ResourceBundle resources) {

        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();


        cmbGrauProficiencia.getItems().setAll(GrauProficiencia.values());
        cmbAreaActividade.getItems().setAll(RepositorioAreaActividade.getInstance().getListaAreasActividade());

        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
    }



    @FXML
    public void addCompetenciaTecnicaAction(ActionEvent event) {
        try {

            boolean adicionou = registarCompetenciaTecnicaController.registarCompetenciaTecnica(
                    txtCodigo.getText(),
                    cmbAreaActividade.getSelectionModel().getSelectedItem(),
                    txtDescricaoBreve.getText(),
                    txtDescricaoDetalhada.getText());

            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Registar Competencia Tecnica.",
                        adicionou ? "Competencia Tecnica registada com sucesso."
                                : "Não foi possível registar a Competencia Tecncia.").show();
        }
        catch (IllegalArgumentException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    iae.getMessage()).show();
        }
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
}
