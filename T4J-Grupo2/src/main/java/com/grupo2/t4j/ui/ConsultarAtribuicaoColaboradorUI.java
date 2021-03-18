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

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConsultarAtribuicaoColaboradorUI implements Initializable {

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

    private ColaboradorLogadoUI colaboradorLogadoUI;
    private Stage adicionarStage;

    /**
     * Associa a scene ColaboradorLogadoUI como parent desta Scene
     * @param colaboradorLogadoUI
     */
    public void associarParentUI(ColaboradorLogadoUI colaboradorLogadoUI) {
        this.colaboradorLogadoUI = colaboradorLogadoUI;
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
