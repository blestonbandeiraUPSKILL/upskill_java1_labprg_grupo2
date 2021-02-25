/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarFreelancerController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author acris
 */
public class ConsultarFreelancerUI implements Initializable {
    
    @FXML TextField txtPorta;

    @FXML ListView<?> listViewCompTec;

    @FXML Button btnVoltar;

    @FXML ListView<?> listViewHabAcad;

    @FXML TextField txtNif;

    @FXML TextField txtNome;

    @FXML TextField txtEmail;

    @FXML TextField txtCodigoPostal;

    @FXML TextField txtLocalidade;

    @FXML TextField txtArruamento;
    
    private AdministrativoLogadoUI administrativoLogadoUI;
    private Stage adicionarStage;
    private RegistarFreelancerController registarFreelancerController;
    
    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        registarFreelancerController = new RegistarFreelancerController();

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
    }   

    public void transferData() throws SQLException {

        txtNome.setText(administrativoLogadoUI.listaFreelancer.getSelectionModel().getSelectedItem().getNome());
        txtNif.setText(administrativoLogadoUI.listaFreelancer.getSelectionModel().getSelectedItem().getNif());
        txtEmail.setText(administrativoLogadoUI.listaFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText());
        //txtArruamento.setText(administrativoLogadoUI.listaFreelancer.getSelectionModel().getSelectedItem().getEnderecoFreelancer());
        //listViewCompTec.getItems().setAll(administrativoLogadoUI.listaFreelancer.getSelectionModel().getSelectedItem().ge)
        
    }
    
    @FXML
    void voltarAtras(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }
    
}
