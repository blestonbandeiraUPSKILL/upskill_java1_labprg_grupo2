/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarReconhecimentoGPController;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.GrauProficiencia;
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
public class AdicionarReconhecimentoGPUI  implements Initializable {
    
    private AdministrativoLogadoUI administrativoLogadoUI;
    
    private RegistarReconhecimentoGPController registarReconhecimentoGPController;
    
    private Stage adicionarStage;
    
    @FXML private TextField txtNomeFreelancer;
    
    @FXML private ComboBox<String> cmbEmailFreelancer;

    @FXML private ListView<CompetenciaTecnica> listaCompetenciaFreelancer;

    @FXML private ComboBox<CompetenciaTecnica> cmbCompetencia;
    
    @FXML private ComboBox<GrauProficiencia> cmbProficiencia;
    
    @FXML private TextField txtIDataValidacao;
    
    @FXML private Button btnAddCompetencia;
    
    @FXML private Button btnCancelar;

    @FXML private Button btnSair;
    
    
    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }
    
    /**
    * Initializes the controller (UI) class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        registarReconhecimentoGPController = new RegistarReconhecimentoGPController();
           
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
        
        /*try {
            updateListViewReconhecimentoGP();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }*/
    }
    
    @FXML
    void addCompetencia(ActionEvent event) {
        try{                      
            
            boolean adicionou = registarReconhecimentoGPController.registarReconhecimentoGP(
                    cmbProficiencia.getSelectionModel().getSelectedIndex(), txtIDataValidacao.getText(),
                    new Email(cmbEmailFreelancer.getPromptText()),cmbCompetencia.getSelectionModel().getSelectedItem().getCodigo());

            if(adicionou) {
                //updateListViewReconhecimentoGPFreelancer();
            
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO, "Registar Competência Técnica.",
                    adicionou ? "Competência Técnica de Freelancer registada com sucesso."
                                : "Não foi possível registar a Competência Técnica.").show();
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
        txtIDataValidacao.clear();
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
    /*
    public void updateListViewReconhecimentoGPFreelancer() throws SQLException {
         listaCompetenciaFreelancer.getItems().setAll(registarReconhecimentoGPController.getAll());
    }*/
}
