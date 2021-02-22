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
import com.grupo2.t4j.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AdicionarCompetenciaFreelancerUI {
    
    @FXML private TextField txtNomeFreelancer;
    
    @FXML private TextField txtNIFFreelancer;

    @FXML private ListView<CompetenciaTecnica> listaCompetenciaFreelancer;

    @FXML private ComboBox<CompetenciaTecnica> cmbCompetencia;
    
    @FXML private ComboBox<GrauProficiencia> cmbProficiencia;
    
    @FXML private TextField txtIDataValidacao;
    
    @FXML private Button btnAddCompetencia;
    
    @FXML private Button btnCancelar;

    @FXML private Button btnSairCompetencia;
     
    @FXML
    void addCompetencia(ActionEvent event) {

    }

    @FXML
    void cancelarAction(ActionEvent event) {

    }

    @FXML
    void sairAction(ActionEvent event) {

    }
}
