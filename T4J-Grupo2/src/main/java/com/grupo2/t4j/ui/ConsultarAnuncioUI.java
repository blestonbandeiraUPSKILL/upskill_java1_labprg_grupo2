/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

/**
 *
 * @author carol
 */
import com.grupo2.t4j.controller.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConsultarAnuncioUI implements Initializable{

    
    @FXML private TextField txtIdAnuncio;
    
    @FXML private TextField txtDtInPublicitacao; 
    
    @FXML private TextField txtDtFimPublicitacao;
    
    @FXML private TextField txtDtInCandidatura;
    
    @FXML private TextField txtDtFimCandidatura;
    
    @FXML private TextField txtDtInSeriacao;
    
    @FXML private TextField txtDtFimSeriacao;
    
    @FXML private TextField txtTipoRegDesignacao;
    
    @FXML private TextField txtRegras;
    
    @FXML private TextField txtReferencia;

    @FXML private TextField txtDesignacao;
    
    @FXML private TextField txtDuracao;
    
    @FXML private TextField txtCusto;

    @FXML private TextField txtDescInformal;    

    @FXML private TextField txDescTecnica;
    
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
        String referenciaTarefa = colaboradorLogadoUI.cmbAnuncio.getSelectionModel().getSelectedItem();
        String nifOrganizacao = colaboradorLogadoUI.getNifOrganizacao();
        //Tarefa tarefa = colaboradorLogadoUI.
        
        txtIdAnuncio.setText(Integer.toString(registarTarefaController.findIdAnuncio(nifOrganizacao, referenciaTarefa)));
        /*txtDtInPublicitacao.setText();
        txtDtFimPublicitacao.setText();
        txtDtInCandidatura.setText();
        txtDtFimCandidatura.setText();
        txtDtInSeriacao.setText();
        txtDtFimSeriacao.setText();
        txtTipoRegDesignacao.setText();
        txtRegras.setText();*/
        txtReferencia.setText(referenciaTarefa);
        txtDesignacao.setText(registarTarefaController.findTarefa(registarTarefaController.findIdAnuncio(nifOrganizacao, referenciaTarefa)).getDesignacao());
        txtDuracao.setText(Integer.toString(registarTarefaController.findTarefa(registarTarefaController.findIdAnuncio(nifOrganizacao, referenciaTarefa)).getDuracaoEst()));
        txtCusto.setText(Double.toString(registarTarefaController.findTarefa(registarTarefaController.findIdAnuncio(nifOrganizacao, referenciaTarefa)).getCustoEst()));
        txtDescInformal.setText(registarTarefaController.findTarefa(registarTarefaController.findIdAnuncio(nifOrganizacao, referenciaTarefa)).getDescInformal());
        txDescTecnica.setText(registarTarefaController.findTarefa(registarTarefaController.findIdAnuncio(nifOrganizacao, referenciaTarefa)).getDescTecnica());

        

        
        
    }
    
    
    @FXML
    public void voltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }
}
