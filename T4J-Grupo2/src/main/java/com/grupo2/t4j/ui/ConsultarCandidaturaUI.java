/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.EfectuarCandidaturaController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marta
 */
public class ConsultarCandidaturaUI implements Initializable {


    @FXML Button btnEditarDados;
    @FXML Button btnApagar;
    @FXML Button btnVoltar;
    @FXML Button btnGuardar;
    @FXML TextArea txtAnuncio;
    @FXML TextArea txtApresentacao;
    @FXML TextArea txtMotivacao;
    @FXML TextField txtValor;
    @FXML TextField txtDias;

    
    private Stage adicionarStage;
    private FreelancerLogadoUI freelancerLogadoUI;
    private EfectuarCandidaturaController efectuarCandidaturaController;

    public void associarParentUI(FreelancerLogadoUI freelancerLogadoUI) {
        this.freelancerLogadoUI = freelancerLogadoUI;
    }

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);
        adicionarStage.setResizable(false);
        
        try { 
            efectuarCandidaturaController = new EfectuarCandidaturaController();
        } catch (SQLException exception) {
            exception.printStackTrace();       
        }
    }    


    @FXML
    private void editarDados(ActionEvent event) {
        txtApresentacao.setEditable(true);
        txtMotivacao.setEditable(true);
        txtValor.setEditable(true);
        txtDias.setEditable(true);
        
        btnVoltar.setText("Cancelar");
        btnGuardar.setVisible(true);
        
        
    }

    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        btnVoltar.getScene().getWindow().hide();
    }

    
    public void guardarAction(ActionEvent actionEvent) {
        
    }
    
    public void cancelarAction(ActionEvent actionEvent) {
        
    }
    

}
