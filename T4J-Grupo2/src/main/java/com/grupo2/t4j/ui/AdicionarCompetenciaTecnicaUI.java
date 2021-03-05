/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.controller.RegistarCompetenciaTecnicaController;
import com.grupo2.t4j.controller.RegistarGrauProficienciaController;
import com.grupo2.t4j.domain.AreaActividade;
import com.grupo2.t4j.domain.GrauProficiencia;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class AdicionarCompetenciaTecnicaUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarGrauProficienciaController registarGrauProficienciaController;
    private Stage adicionarStage;

    @FXML Button btnVoltar;
    @FXML Button btnCancelar;
    @FXML TextArea txtDescDetalhada;
    @FXML TextField txtDescBreve;
    @FXML TextField txtCodigo;
    @FXML TextField txtDesignacao;
    @FXML TextField txtValor;

    @FXML ComboBox<AreaActividade> cmbAreaActividade;
    @FXML ListView<GrauProficiencia> listViewGrausAdicionados;

    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    /**
     * Initializes the controller (UI) class.
     */
    public void initialize(URL location, ResourceBundle resources) {

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();
        try {
            registarGrauProficienciaController = new RegistarGrauProficienciaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            cmbAreaActividade.getItems().setAll(
                    registarAreaActividadeController.getAll());

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    public void registarCompetenciaTecnicaAction(ActionEvent event) {
        try {
            boolean adicionou = registarCompetenciaTecnicaController.registarCompetenciaTecnica(
                    txtCodigo.getText().trim(),
                    txtDescBreve.getText().trim(),
                    txtDescDetalhada.getText().trim(),
                    cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo()
            );

            if (adicionou) {
                administrativoLogadoUI.updateTableViewCompetenciasTecnicas();
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                        MainApp.TITULO_APLICACAO,
                        "Registar Competência Técnica.",
                        "Competencia Tecnica registada com sucesso.  Pode adicionar os graus de proficiência."
                                ).show();
            }

        } catch (IllegalArgumentException | SQLException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    "Não foi possível registar a Competência Técncia." + exception.getMessage()).show();
        }
    }

    public void cancelarRegisto(ActionEvent actionEvent) {
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

    public void adicionarGrauAction(ActionEvent actionEvent) {
        try{
            boolean adicionou = registarGrauProficienciaController.registarGrauProficiencia(
                    txtValor.getText(),
                    txtDesignacao.getText(),
                    Integer.parseInt(txtCodigo.getText()));

            if(adicionou) {
                updateListViewGrausProficiencia(actionEvent);
                txtValor.clear();
                txtDesignacao.clear();
            }

            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Registar Grau de Proficiência.",
                    "Grau de Proficiência registado com sucesso. Pode regressar à página anterior.").show();

        }
        catch (IllegalArgumentException | SQLException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Registar Grau de Proficiência - Erro nos dados",
                    "Não foi possível registar o Grau de Proficiência." + exception.getMessage()).show();
        }

    }


    public void updateListViewGrausProficiencia(ActionEvent actionEvent) throws SQLException {
        listViewGrausAdicionados.getItems().add(
                registarGrauProficienciaController.findByGrauECompetenciaTecnica(
                        Integer.parseInt(txtValor.getText()), txtCodigo.getText())
        );
    }


    public void voltarAtras(ActionEvent actionEvent) {
        btnVoltar.getScene().getWindow().hide();
    }

}
