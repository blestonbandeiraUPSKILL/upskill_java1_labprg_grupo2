/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author acris
 */
public class AdicionarAreaAtividadeUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private Stage adicionarStage;
    
    @FXML Button btnCancelar;
    @FXML Button btnAddAreaAtividade;
    @FXML TextField txtCodigo;
    @FXML TextField txtDescricaoBreve;
    @FXML TextArea areaDescricaoDetalhada;

    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    /**
     * Initializes the controller (UI) class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       registarAreaActividadeController = new RegistarAreaActividadeController();
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
    }   

    @FXML
    void cancelarAction(ActionEvent event) {
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

    @FXML
    void registarAreaAction(ActionEvent event) {
        try{
            registarAreaActividadeController = new RegistarAreaActividadeController();

            boolean adicionou = registarAreaActividadeController.registarAreaActividade(
                    txtCodigo.getText().toString(),
                    txtDescricaoBreve.getText(),
                    areaDescricaoDetalhada.getText());

            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Registar Área de Actividade.",
                    adicionou ? "Área de Actividade registada com sucesso."
                                : "Não foi possível registar a Área de Actividade.").show();
        }
        catch (IllegalArgumentException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    iae.getMessage()).show();
        
        }

    }


    
}
