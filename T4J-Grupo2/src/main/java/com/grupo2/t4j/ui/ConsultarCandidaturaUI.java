/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author marta
 */
public class ConsultarCandidaturaUI implements Initializable {

    @FXML
    private Button btnEditarDados;
    @FXML
    private Button btnApagar;
    @FXML
    private Button btnCancelar1;
    @FXML
    private TextArea txtAnuncio;
    @FXML
    private TextArea txtApresentacao;
    @FXML
    private TextArea txtMotivacao;
    @FXML
    private TextField txtValor;
    @FXML
    private TextField txtDias;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editarDados(ActionEvent event) {
    }

    @FXML
    private void apagarCandidatura(ActionEvent event) {
    }

    @FXML
    private void cancelarAction(ActionEvent event) {
    }
    
}
