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

    @FXML Button btnEditarDados;
    @FXML Button btnCancelar1;
    @FXML TextArea txtAnuncio;
    @FXML TextArea txtApresentacao;
    @FXML TextArea txtMotivacao;
    @FXML TextField txtValor;
    @FXML TextField txtDias;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editarDados(ActionEvent event) {
        txtApresentacao.setEditable(true);
        txtMotivacao.setEditable(true);
        txtValor.setEditable(true);
        txtDias.setEditable(true);
        
        
    }

    @FXML
    private void apagarCandidatura(ActionEvent event) {
    }

    @FXML
    private void cancelarAction(ActionEvent event) {
    }
    
}
