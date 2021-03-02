/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.controller.RegistarCaracterizacaoCTController;
import com.grupo2.t4j.model.CaracterizacaoCT;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class ConsultarCategoriaUI implements Initializable {
    
    private AdministrativoLogadoUI administrativoLogadoUI;
    private Stage adicionarStage;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarCaracterizacaoCTController registarCaracterizacaoCTController;
    
    @FXML TextField txtDescBreve;
    @FXML TextField txtCodigo;
    @FXML TextField txtAreaActividade;
    @FXML TextArea txtDescDetalhada;
    @FXML Button btnVoltar;
    @FXML ListView<CaracterizacaoCT> listViewCaracterizacao;
    
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
        registarCaracterizacaoCTController = new RegistarCaracterizacaoCTController();
        
    }    
    public void transferData() throws SQLException {
        
        String codigoAreaActividade = administrativoLogadoUI.tableViewCategoria.getSelectionModel().getSelectedItem().getCodigoAreaActividade();
        String codigoCategoria = administrativoLogadoUI.tableViewCategoria.getSelectionModel().getSelectedItem().getCodigoCategoria();
        txtCodigo.setText(administrativoLogadoUI.tableViewCategoria.getSelectionModel().getSelectedItem().getCodigoCategoria());
        txtAreaActividade.setText(registarAreaActividadeController.getAreaActividade(codigoAreaActividade).getDescBreve());
        txtDescBreve.setText(administrativoLogadoUI.tableViewCategoria.getSelectionModel().getSelectedItem().getDescBreve());
        txtDescDetalhada.setText(administrativoLogadoUI.tableViewCategoria.getSelectionModel().getSelectedItem().getDescDetalhada());
        listViewCaracterizacao.getItems().setAll(
                registarCaracterizacaoCTController.getAllByCategoria(codigoCategoria));
    }
    
    @FXML
    void voltarAtras(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }
    
}
