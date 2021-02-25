package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class ConsultarAreaActividadeUI implements Initializable {
    
    @FXML TextField txtDescBreve;
    @FXML Button btnVoltar;
    @FXML TextField txtCodigo;
    @FXML TextArea txtDescDetalhada;
    
    
    private AdministrativoLogadoUI administrativoLogadoUI;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private Stage adicionarStage;
    
    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //try {

            adicionarStage = new Stage();
            adicionarStage.initModality(Modality.APPLICATION_MODAL);;
            adicionarStage.setResizable(false);
            
            registarAreaActividadeController = new RegistarAreaActividadeController();
            

            
        /*} catch (SQLException exception) {
            exception.printStackTrace();
        }*/

    }    
    public void transferData() throws SQLException {

        txtCodigo.setText(administrativoLogadoUI.listaAreasActividade.getSelectionModel().getSelectedItem().getCodigo());
        txtDescBreve.setText(administrativoLogadoUI.listaAreasActividade.getSelectionModel().getSelectedItem().getDescBreve());
        txtDescDetalhada.setText(administrativoLogadoUI.listaAreasActividade.getSelectionModel().getSelectedItem().getDescDetalhada());
        
    }

    @FXML
    void voltarAtras(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }

}
