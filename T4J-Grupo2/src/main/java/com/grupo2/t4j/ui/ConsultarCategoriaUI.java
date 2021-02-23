/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.model.CaracterizacaoCT;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acris
 */
public class ConsultarCategoriaUI implements Initializable {
    
    private AdministrativoLogadoUI administrativoLogadoUI;
    private Stage adicionarStage;
    private RegistarAreaActividadeController registarAreaActividadeController;
    
    @FXML TextField txtDescBreve;

    @FXML TextField txtAreaActividade;

    @FXML Button btnVoltar;

    @FXML ListView<CaracterizacaoCT> listViewCaracterizacao;

    @FXML TextField txtCodigo;

    @FXML TextArea txtDescDetalhada;
    
    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        adicionarStage = new Stage();
            adicionarStage.initModality(Modality.APPLICATION_MODAL);;
            adicionarStage.setResizable(false);
            
            registarAreaActividadeController = new RegistarAreaActividadeController();
        
    }    
    public void transferData() throws SQLException {
        
        String codigoAreaActividade = administrativoLogadoUI.listaCategorias.getSelectionModel().getSelectedItem().getCodigoAreaActividade();
        txtCodigo.setText(administrativoLogadoUI.listaCategorias.getSelectionModel().getSelectedItem().getCodigoCategoria());
        txtAreaActividade.setText(registarAreaActividadeController.findByCodigo(codigoAreaActividade).getDescBreve());
        txtDescBreve.setText(administrativoLogadoUI.listaCategorias.getSelectionModel().getSelectedItem().getDescBreve());
        txtDescDetalhada.setText(administrativoLogadoUI.listaCategorias.getSelectionModel().getSelectedItem().getDescDetalhada());

        
    }
    
    @FXML
    void voltarAtras(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }
    
}
