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

import com.grupo2.t4j.controller.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
public class ConsultarCandidaturaFreelancerUI {

    @FXML
    private TextField txtCustoAnuncio;

    @FXML
    private TextField txtHabilitacoes;

    @FXML
    private TextField txtIdCandidatura;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtDuracaoFreelancer;

    @FXML
    private TextField txtCompetencias;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtNIF;

    @FXML
    private TextField txtIdAnuncio;

    @FXML
    private TextField txtDuracaoAnuncio;

    @FXML
    private TextField txtLocalidade;

    @FXML
    private TextField txtCustoFreelancer;

    @FXML
    void voltarAtras(ActionEvent event) {

    }
}
