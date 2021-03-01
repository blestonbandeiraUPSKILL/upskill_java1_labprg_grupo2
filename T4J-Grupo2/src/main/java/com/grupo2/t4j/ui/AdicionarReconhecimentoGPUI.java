/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarCompetenciaTecnicaController;
import com.grupo2.t4j.controller.RegistarFreelancerController;
import com.grupo2.t4j.controller.RegistarGrauProficienciaController;
import com.grupo2.t4j.controller.RegistarReconhecimentoGPController;
import com.grupo2.t4j.model.*;
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
    private RegistarFreelancerController registarFreelancerController;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    private RegistarGrauProficienciaController registarGrauProficienciaController;
    
    private Stage adicionarStage;
    
    @FXML TextField txtNomeFreelancer;
    @FXML TextField txtIDataValidacao;
    @FXML ComboBox<Freelancer> cmbEmailFreelancer;
    @FXML ComboBox<CompetenciaTecnica> cmbCompetencia;
    @FXML ComboBox<GrauProficiencia> cmbProficiencia;
    @FXML ListView<ReconhecimentoGP> listReconhecimentoGP;
    @FXML Button btnAddCompetencia;
    @FXML Button btnCancelar;
    @FXML Button btnSair;
    
    
    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }
    
    /**
    * Initializes the controller (UI) class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        registarReconhecimentoGPController = new RegistarReconhecimentoGPController();
        registarFreelancerController = new RegistarFreelancerController();
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();
        try {
            registarGrauProficienciaController = new RegistarGrauProficienciaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        try {
            cmbEmailFreelancer.getItems().setAll(registarFreelancerController.getAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            cmbCompetencia.getItems().setAll(registarCompetenciaTecnicaController.getAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        cmbCompetencia.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateCmbGrauProficiencia(event);

                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });

        cmbEmailFreelancer.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateTxtNomeFreelancer(event);
                    updateListViewReconhecimentoGP();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });

    }

    public void updateCmbGrauProficiencia(ActionEvent actionEvent) throws SQLException {
        String codigoCompetenciaTecnica = cmbCompetencia.getSelectionModel().getSelectedItem().getCodigo();
        cmbProficiencia.getItems().setAll(
                registarGrauProficienciaController.findByCompetenciaTecnica(codigoCompetenciaTecnica));
    }

    public void updateListViewReconhecimentoGP() throws SQLException {
        String emailFreelancer = cmbEmailFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText();
        listReconhecimentoGP.getItems().setAll(
                registarReconhecimentoGPController.getAll(emailFreelancer));
    }

    public void updateTxtNomeFreelancer(ActionEvent actionEvent) throws SQLException {
        String emailFreelancer = cmbEmailFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText();
        txtNomeFreelancer.setText(registarFreelancerController.findByEmail(emailFreelancer).getNome());
    }
    
    @FXML
    void addCompetencia(ActionEvent event) {
        try{                      
            
            boolean adicionou = registarReconhecimentoGPController.registarReconhecimentoGP(
                    cmbProficiencia.getSelectionModel().getSelectedItem().getIdGrauProficiencia(),
                    cmbEmailFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText(),
                    txtIDataValidacao.getText());

            if(adicionou) {
                updateListViewReconhecimentoGP();
            
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO, "Registar Validação de Competência Técnica.",
                     "Competência Técnica de Freelancer validada com sucesso.").show();
            }
        }
        catch (IllegalArgumentException | SQLException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Registar Validação de Competência Técnica - Erro nos dados.",
                    "Não foi possível validar a Competência Técnica." + exception.getMessage()).show();
        
     
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


}
