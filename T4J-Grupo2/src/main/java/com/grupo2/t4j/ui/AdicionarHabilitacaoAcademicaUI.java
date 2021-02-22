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
import com.grupo2.t4j.controller.RegistarFreelancerController;
import java.net.URL;
import java.util.ResourceBundle;
import com.grupo2.t4j.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;


public class AdicionarHabilitacaoAcademicaUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private RegistarFreelancerController registarFreelancerController;
    private Stage adicionarStage;
    
    @FXML private TextField txtNomeFreelancer;
    
    @FXML private TextField txtNIFFreelancer;

    @FXML private TextField txtGrau;

    @FXML private TextField txtDesignacao;

    @FXML private TextField txtInstituicao;

    @FXML private TextField txtMedia;
    
   @FXML private ListView<HabilitacaoAcademica> listaHabilitacaoFreelancer;
     
    @FXML private Button btnAddHabilitacao;
    
    @FXML private Button btnCancelar;
    
    @FXML private Button btnSairHabilitacao;

    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    /**
     * Initializes the controller (UI) class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        registarFreelancerController = new RegistarFreelancerController();
        
        //colunaGrau.setCellValueFactory(clbck);
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
    }  
   
    @FXML
    void addHabilitacao(ActionEvent event) {

    }

    @FXML
    void cancelarAction(ActionEvent event) {

    }

    @FXML
    void sairAction(ActionEvent event) {

    }
}
