/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.controller.RegistarFreelancerController;
import java.net.URL;
import java.util.ResourceBundle;
import com.grupo2.t4j.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class AdicionarFreelancerUI implements Initializable {
    
    private AdministrativoLogadoUI administrativoLogadoUI;
    private RegistarFreelancerController registarFreelancerController;
    private Stage adicionarStage;
    
    @FXML private TextField txtNomeFreelancer;
    
    @FXML private TextField txtEmailFreelancer;
    
    @FXML private TextField txtNIFFreelancer;
    
    @FXML private TextField txtTelefoneFreelancer;
    
    @FXML private TextField txtArruamentoFreelancer;
    
    @FXML private TextField txtPortaFreelancer;

    @FXML private TextField txtLocalidadeFreelancer;

    @FXML private TextField txtCodPostalFreelancer;
    
    @FXML private TextField txtPassFreelancer;
    
    @FXML private Button btnAddFreelancer;
   
    @FXML private Button btnCancelar;

    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    /**
     * Initializes the controller (UI) class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        registarFreelancerController = new RegistarFreelancerController();

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
    }   

    @FXML
    void cancelarAction(ActionEvent event) {

        this.txtCodigo.clear();
        this.txtDescricaoBreve.clear();
        this.areaDescricaoDetalhada.clear();

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
    public void addFreelancer(ActionEvent event) {

        try{
            boolean adicionou = registarFreelancerController.registarAreaActividade(
                    txtNomeFreelancer.getText(),
                    txtEmailFreelancer.getText(),
                    areaDescricaoDetalhada.getText());

            if(adicionou) {
                administrativoLogadoUI.listaAreasActividade.getItems().addAll(registarAreaActividadeController.getAll());
            }

            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Registar Área de Actividade.",
                    adicionou ? "Área de Actividade registada com sucesso."
                                : "Não foi possível registar a Área de Actividade.").show();

            closeAddFreelancer(event);

        }
        catch (IllegalArgumentException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    iae.getMessage()).show();
        
        }

    }

    private void closeAddFreelancer(ActionEvent event) {
        this.txtNomeFreelancer.clear();
        this.txtEmailFreelancer.clear();
        this.txtNIFFreelancer.clear();
        this.txtTelefoneFreelancer.clear();
        this.txtArruamentoFreelancer.clear();
        this.txtPortaFreelancer.clear();
        this.txtLocalidadeFreelancer.clear();
        this.txtCodPostalFreelancer.clear();
        this.txtPassFreelancer.clear();       
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}
