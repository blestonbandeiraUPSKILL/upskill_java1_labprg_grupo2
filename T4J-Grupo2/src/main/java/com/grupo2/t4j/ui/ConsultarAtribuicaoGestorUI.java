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

import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConsultarAtribuicaoGestorUI implements Initializable{
    @FXML private TextField txtRefTarefa;
    @FXML private TextField txtIdAnuncio;
    @FXML private TextField txtDataSeriacao;
    @FXML private TextField txtDataAtribuicao;
    @FXML private TextField txtDescInformal;
    @FXML private TextField txtDescTecnica;
    @FXML private TextField txtNomeFreelancer;
    @FXML private TextField txtEmailFreelancer;
    @FXML private TextField txtCodigoAtribuicao;
    @FXML private TextField txtCusto;
    @FXML private TextField txtDtInTarefa;
    @FXML private TextField txtDtFimTarefa;
    @FXML private TextField txtNumDias;
    @FXML private Button btnVoltar;
    @FXML Label txt_email;

    private GestorLogadoUI gestorLogadoUI;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private Stage adicionarStage;

    /**
     * Associa a scene GestorLogadoUI como parent desta Scene
     * @param gestorLogadoUI
     */
    public void associarParentUI(GestorLogadoUI gestorLogadoUI) {
        this.gestorLogadoUI = gestorLogadoUI;
    }

    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        txt_email.setText(gestaoUtilizadoresController.getEmail());
    }

    /**
     * Preenche a scene com a informacão do anuncio     *
     */
    public void transferData() {

    }

    /**
     * Volta para a scene anterior
     * @param event
     */
    @FXML
    void voltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }
}
