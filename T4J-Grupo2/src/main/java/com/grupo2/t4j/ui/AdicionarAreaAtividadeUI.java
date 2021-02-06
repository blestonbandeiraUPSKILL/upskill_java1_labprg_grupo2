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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author acris
 */
public class AdicionarAreaAtividadeUI implements Initializable {
    
    private RegistarAreaActividadeController registarAreaActividadeController;
    
    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnAddAreaAtividade;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtDescricaoBreve;
    
    @FXML
    private TextField txtDescricaoDetalhada;
    
    private StartingPageUI startingPageUI;
    
    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RegistarAreaActividadeController registarAreaActividadeController = new RegistarAreaActividadeController();
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
                        "Tem a certeza que quer voltar à página inicial, cancelando o actual registo?");

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
                    txtCodigo.getText(),txtDescricaoBreve.getText(),
                    txtDescricaoDetalhada.getText()
                );
        AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registar Área de Actividade.",
                    adicionou ? "Área de Actividade registada com sucesso."
                            : "Não foi possível registar a Área de Actividade.").show();
        } catch (IllegalArgumentException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    iae.getMessage()).show();
        
        }

    }


    
}
