/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.controller.RegistarCompetenciaTecnicaController;
import com.grupo2.t4j.controller.RegistarGrauProficienciaController;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.GrauProficiencia;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
//import com.grupo2.t4j.repository.RepositorioAreaActividade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
                administrativoLogadoUI.updateListViewCompetenciasTecnicas();
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                        MainApp.TITULO_APLICACAO,
                        "Registar Competência Técnica.",
                        adicionou ? "Competencia Tecnica registada com sucesso.  Pode adicionar os graus de proficiência."
                                : "Não foi possível registar a Competência Técncia.").show();

            }

        } catch (IllegalArgumentException | SQLException iae) {
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

    public void adicionarGrauAction(ActionEvent actionEvent) {
        try{
            boolean adicionou = registarGrauProficienciaController.registarGrauProficiencia(
                    txtValor.getText(),
                    txtDesignacao.getText(),
                    txtCodigo.getText());

            if(adicionou) {
                updateListViewGrausProficiencia(actionEvent);
                txtValor.clear();
                txtDesignacao.clear();
            }

            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Registar Grau de Proficiência.",
                    adicionou ? "Grau de Proficiência registado com sucesso. Pode regressar à página anterior."
                            : "Não foi possível registar o Grau de Proficiência.").show();

        }
        catch (IllegalArgumentException | SQLException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    iae.getMessage()).show();
        }

    }


    public void updateListViewGrausProficiencia(ActionEvent actionEvent) throws SQLException {
        listViewGrausAdicionados.getItems().add(
                registarGrauProficienciaController.findByGrauECompetenciaTecnica(
                        txtValor.getText(), txtCodigo.getText())
        );
    }


    public void voltarAtras(ActionEvent actionEvent) {
        btnVoltar.getScene().getWindow().hide();
    }
}
