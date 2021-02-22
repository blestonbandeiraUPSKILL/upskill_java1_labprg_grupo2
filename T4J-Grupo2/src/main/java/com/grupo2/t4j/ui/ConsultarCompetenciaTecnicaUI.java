package com.grupo2.t4j.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.controller.RegistarGrauProficienciaController;
import com.grupo2.t4j.model.GrauProficiencia;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author acris
 */
public class ConsultarCompetenciaTecnicaUI implements Initializable {
    
    private AdministrativoLogadoUI administrativoLogadoUI;
    private RegistarGrauProficienciaController registarGrauProficienciaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private Stage adicionarStage;
    
    @FXML TextArea txtDescricaoDetalhada;
    @FXML TextField txtAreaActividade;
    @FXML ListView<GrauProficiencia> listViewGrausAplicaveis;
    @FXML TextField txtDescricaoBreve;
    @FXML TextField txtCodigoCompetenciaTecnica;
    @FXML Button btnVoltar;
    
    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            
            adicionarStage = new Stage();
            adicionarStage.initModality(Modality.APPLICATION_MODAL);;
            adicionarStage.setResizable(false);
            
            registarAreaActividadeController = new RegistarAreaActividadeController();
            registarGrauProficienciaController = new RegistarGrauProficienciaController();
            
            try {

                txtCodigoCompetenciaTecnica.setText(administrativoLogadoUI.listViewCompetenciasTecnicas.getSelectionModel().getSelectedItem().getCodigo());
                txtDescricaoBreve.setText(administrativoLogadoUI.listViewCompetenciasTecnicas.getSelectionModel().getSelectedItem().getDescricaoBreve());
                txtDescricaoDetalhada.setText(administrativoLogadoUI.listViewCompetenciasTecnicas.getSelectionModel().getSelectedItem().getDescricaoDetalhada());
                listViewGrausAplicaveis.getItems().setAll(registarGrauProficienciaController.findByCompetenciaTecnica(txtCodigoCompetenciaTecnica.getText()));
                txtAreaActividade.setText(registarAreaActividadeController.findByCodigo(administrativoLogadoUI.listViewCompetenciasTecnicas.getSelectionModel().getSelectedItem().getCodigoAreaActividade()).getDescBreve());
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        
    }    
    
    public void updateDados() throws SQLException {
        txtCodigoCompetenciaTecnica.setText(administrativoLogadoUI.listViewCompetenciasTecnicas.getSelectionModel().getSelectedItem().getCodigo());
        txtDescricaoBreve.setText(administrativoLogadoUI.listViewCompetenciasTecnicas.getSelectionModel().getSelectedItem().getDescricaoBreve());
        txtDescricaoDetalhada.setText(administrativoLogadoUI.listViewCompetenciasTecnicas.getSelectionModel().getSelectedItem().getDescricaoDetalhada());
        listViewGrausAplicaveis.getItems().setAll(registarGrauProficienciaController.findByCompetenciaTecnica(txtCodigoCompetenciaTecnica.getText()));
        txtAreaActividade.setText(registarAreaActividadeController.findByCodigo(administrativoLogadoUI.listViewCompetenciasTecnicas.getSelectionModel().getSelectedItem().getCodigoAreaActividade()).getDescBreve());
    }


    public void voltarAtras(ActionEvent actionEvent) {
        btnVoltar.getScene().getWindow().hide();
    }
    
}