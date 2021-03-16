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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class ConsultarResultadoUI {
    
    @FXML TextField txtReferencia;
    @FXML TextField txtCusto;
    @FXML TextField txtDesignacao;
    @FXML TextField txtIdAtribuicao;
    @FXML TextField txtDtFimTarefa;
    @FXML TextField txtDescTecnica;
    @FXML TextField txtDtInTarefa;
    @FXML TextField txtNomeOrganizacao;
    @FXML TextField txtDataSeriacao;
    @FXML TextField txtDescInformal;
    @FXML Button btnVoltar;
    @FXML TextField txtEmailColaborador;
    @FXML TextField txtIdAnuncio;
    @FXML TextField txtClassificacao;
    @FXML TextField txtEmailFreelancer;
    
    private FreelancerLogadoUI freelancerLogadoUI;
    private Stage adicionarStage;
    
    
    
    /**
     * Associa a scene AdministrativoLogadoUI como parent desta Scene 
     * @param administrativoLogadoUI
     */
    public void associarParentUI(FreelancerLogadoUI freelancerLogadoUI) {
        this.freelancerLogadoUI = freelancerLogadoUI;
    }
    
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);
        adicionarStage.setResizable(false);

        

    }
    
    /**
     * Volta para a scene anterior
     * @param event 
     */
    @FXML
    public void voltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }

    /**
     * Preenche a scene com a informacao do resultado da candidatura
     * @throws SQLException 
     */
    public void transferData() {

        //txtReferencia.setText(consultarResultadoController.findResultadoById());

    }
}
