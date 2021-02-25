/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarFreelancerController;
import java.net.URL;
import java.util.ResourceBundle;
import com.grupo2.t4j.model.*;
import java.sql.SQLException;
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

/**
 * FXML Controller class
 */
public class AdicionarFreelancerUI implements Initializable {
    
    private AdministrativoLogadoUI administrativoLogadoUI;
    private RegistarFreelancerController registarFreelancerController;
    private Stage adicionarStage;
    
    @FXML TextField txtNomeFreelancer;
    @FXML TextField txtEmailFreelancer;
    @FXML TextField txtNIFFreelancer;
    @FXML TextField txtTelefoneFreelancer;
    @FXML TextField txtArruamentoFreelancer;
    @FXML TextField txtPortaFreelancer;
    @FXML TextField txtLocalidadeFreelancer;
    @FXML TextField txtCodPostalFreelancer;
    @FXML TextField txtPassFreelancer;
    @FXML Button btnAddFreelancer;
    @FXML Button btnCancelar;
    @FXML  Button btnSair;

    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    /**
     * Initializes the controller (UI) class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        registarFreelancerController = new RegistarFreelancerController();

    }   
   
    @FXML
    public void addFreelancer(ActionEvent event) throws SQLException {

        try{
            boolean adicionou = registarFreelancerController.registarFreelancer(
                    txtEmailFreelancer.getText().trim(),
                    txtNomeFreelancer.getText().trim(),
                    txtNIFFreelancer.getText().trim(),
                    txtTelefoneFreelancer.getText().trim(),
                    txtArruamentoFreelancer.getText().trim(),
                    txtPortaFreelancer.getText().trim(),
                    txtLocalidadeFreelancer.getText().trim(),
                    txtCodPostalFreelancer.getText().trim());

            if(adicionou) {
                txtPassFreelancer.setText(
                        registarFreelancerController.findPassword(
                                txtEmailFreelancer.getText()).getPasswordText());

                btnAddFreelancer.setDisable(true);
                btnAddFreelancer.disabledProperty();
                btnSair.setText("Voltar");

                administrativoLogadoUI.updateListViewFreelancer();
            
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO, "Registar Freelancer.",
                    adicionou ? "Freelancer registado com sucesso."
                                : "Não foi possível registar o Freelancer.").show();
            }
        }
        catch (IllegalArgumentException | SQLException ex) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    ex.getMessage()).show();
        
        }
    }
  
    @FXML
    public void cancelarAction(ActionEvent event) {
        this.txtNomeFreelancer.clear();
        this.txtEmailFreelancer.clear();
        this.txtNIFFreelancer.clear();
        this.txtTelefoneFreelancer.clear();
        this.txtArruamentoFreelancer.clear();
        this.txtPortaFreelancer.clear();
        this.txtLocalidadeFreelancer.clear();
        this.txtCodPostalFreelancer.clear();
        this.txtPassFreelancer.clear();
    }
    
    @FXML
    public void sairAction(ActionEvent event) {
        Window window = btnSair.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que quer voltar à página anterior?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                }
            }
        });
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
