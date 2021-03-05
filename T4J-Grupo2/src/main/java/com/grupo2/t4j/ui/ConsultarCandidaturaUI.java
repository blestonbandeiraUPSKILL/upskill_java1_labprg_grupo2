/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.EditarCandidaturaController;
import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.domain.Candidatura;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
    private EditarCandidaturaController editarCandidaturaController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;

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
        
        editarCandidaturaController = new EditarCandidaturaController();
        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        //isCandidaturaEditavel();
    }   


    @FXML
    private void editarDados(ActionEvent event) {
        //txtApresentacao.setEditable(true);
        //txtMotivacao.setEditable(true);
        //txtValor.setEditable(true);
        //txtDias.setEditable(true);
        txtApresentacao.setDisable(false);
        txtMotivacao.setDisable(false);
        txtValor.setDisable(false);
        txtDias.setDisable(false);
        //btnCancelar.setText("Cancelar");
        btnGuardar.setVisible(true);
        btnEditarDados.setVisible(false);
        
        
    }

    public void transferData() throws SQLException {
        
        txtApresentacao.setText(freelancerLogadoUI.listViewCandidaturas.getSelectionModel().getSelectedItem().getApresentacao());
        txtMotivacao.setText(freelancerLogadoUI.listViewCandidaturas.getSelectionModel().getSelectedItem().getMotivacao());
        txtValor.setText(String.valueOf(freelancerLogadoUI.listViewCandidaturas.getSelectionModel().getSelectedItem().getValorPretendido()));
        txtDias.setText(String.valueOf(freelancerLogadoUI.listViewCandidaturas.getSelectionModel().getSelectedItem().getNumeroDias()));

    }

    
    public void guardarAction(ActionEvent actionEvent) {
        /*editarCandidaturaController.updateCandidatura(idCandidatura, txtApresentacao.getText(),
                txtMotivacao.getText(), Double.parseDouble(txtValor.getText()), 
                Integer.parseInt(txtDias.getText()));*/
        
    }
    
    public void voltarAction(ActionEvent actionEvent) {
        btnVoltar.getScene().getWindow().hide();
    }
    
    public boolean isCandidaturaEditavel (){
        String emailFreelancer = gestaoUtilizadoresController.getEmail();
        List<Candidatura> listaCandidaturasEditaveis = editarCandidaturaController.getAllCandidaturasEditaveis(emailFreelancer);
        if (listaCandidaturasEditaveis.contains(freelancerLogadoUI.listViewCandidaturas.getSelectionModel().getSelectedItem())){
            btnEditarDados.setDisable(true);
        }
       return false;
    }
    

}
