/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarFreelancerController;
import com.grupo2.t4j.domain.HabilitacaoAcademica;
import com.grupo2.t4j.domain.ReconhecimentoGP;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class ConsultarFreelancerUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private Stage adicionarStage;
    private RegistarFreelancerController registarFreelancerController;

    @FXML TextField txtPorta;
    @FXML TextField txtNif;
    @FXML TextField txtNome;
    @FXML TextField txtEmail;
    @FXML TextField txtCodigoPostal;
    @FXML TextField txtLocalidade;
    @FXML TextField txtArruamento;
    @FXML ListView<ReconhecimentoGP> listViewCompTec;
    @FXML ListView<HabilitacaoAcademica> listViewHabAcad;
    @FXML Button btnVoltar;

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

    public void transferData() throws SQLException {
        String emailFreelancer = administrativoLogadoUI.tableViewFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText();

        String arruamento = registarFreelancerController.getEnderecoPostal(emailFreelancer).getArruamento();
        String numeroPorta = registarFreelancerController.getEnderecoPostal(emailFreelancer).getPorta();
        String localidade = registarFreelancerController.getEnderecoPostal(emailFreelancer).getLocalidade();
        String codPostal = registarFreelancerController.getEnderecoPostal(emailFreelancer).getCodigoPostal();

        txtNome.setText(administrativoLogadoUI.tableViewFreelancer.getSelectionModel().getSelectedItem().getNome());
        txtNif.setText(administrativoLogadoUI.tableViewFreelancer.getSelectionModel().getSelectedItem().getNif());
        txtEmail.setText(administrativoLogadoUI.tableViewFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText());
        txtArruamento.setText(arruamento);
        txtPorta.setText(numeroPorta);
        txtLocalidade.setText(localidade);
        txtCodigoPostal.setText(codPostal);
        listViewCompTec.getItems().setAll(registarFreelancerController.getAllReconhecimentoGP(emailFreelancer));
        listViewHabAcad.getItems().setAll(registarFreelancerController.getAllHabsAcademicas(emailFreelancer));
        
    }
    
    @FXML
    void voltarAtras(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }
    
}
