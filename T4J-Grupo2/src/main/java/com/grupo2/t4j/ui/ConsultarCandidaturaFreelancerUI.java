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
public class ConsultarCandidaturaFreelancerUI implements Initializable{

    @FXML private TextField txtIdAnuncio;
    
    @FXML private TextField txtIdCandidatura;
    
    @FXML private TextField txtNome;
    
    @FXML private TextField txtNIF;
    
    @FXML private TextField txtEmail;
    
    @FXML private TextField txtLocalidade;
   
    @FXML private TextField txtHabilitacoes;
    
    @FXML private TextField txtCompetencias;
        
    @FXML private TextField txtCustoAnuncio;

    @FXML private TextField txtCustoFreelancer;
    
    @FXML private TextField txtDuracaoAnuncio;   

    @FXML private TextField txtDuracaoFreelancer;
  
    @FXML private Button btnVoltar;
    
    private ColaboradorLogadoUI colaboradorLogadoUI;
    private SeriarAnuncioController seriarAnuncioController;
    private RegistarTarefaController registarTarefaController;
    private Stage adicionarStage;
    
     public void associarParentUI(ColaboradorLogadoUI colaboradorLogadoUI) {
        this.colaboradorLogadoUI = colaboradorLogadoUI;
    }
    
    /**
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){

        seriarAnuncioController = new SeriarAnuncioController();
        try {
            registarTarefaController = new RegistarTarefaController();
            
        } catch (SQLException exception) {
            exception.printStackTrace();
        }        

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
    }
    
    public void transferData() throws SQLException {
        
    }

    @FXML
    public void voltarAtras(ActionEvent event) {
         btnVoltar.getScene().getWindow().hide();
    }
}
