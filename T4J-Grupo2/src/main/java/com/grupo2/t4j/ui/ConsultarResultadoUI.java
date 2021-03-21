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
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.EfectuarCandidaturaController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class ConsultarResultadoUI implements Initializable {
    
    @FXML TextField txtReferencia;
    @FXML TextField txtCusto;
    @FXML TextField txtDesignacao;
    @FXML TextField txtIdAtribuicao;
    @FXML TextField txtDtFimTarefa;
    @FXML TextField txtDescTecnica;
    @FXML TextField txtDtInTarefa;
    @FXML TextField txtNomeOrganizacao;
    @FXML TextField txtDataSeriacao;
    @FXML TextField txtDescInformal;
    @FXML Button btnVoltar;
    @FXML TextField txtEmailColaborador;
    @FXML TextField txtIdAnuncio;
    @FXML TextField txtClassificacao;
    @FXML Label txtEmail;
    
    private FreelancerLogadoUI freelancerLogadoUI;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private EfectuarCandidaturaController efectuarCandidaturaController;

    private Stage adicionarStage;
    
    
    
    /**
     * Associa a scene AdministrativoLogadoUI como parent desta Scene 
     * @param freelancerLogadoUI
     */
    public void associarParentUI(FreelancerLogadoUI freelancerLogadoUI) {
        this.freelancerLogadoUI = freelancerLogadoUI;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        txtEmail.setText(gestaoUtilizadoresController.getEmail());

        try {
            efectuarCandidaturaController = new EfectuarCandidaturaController();
        }catch (SQLException exception){
            exception.printStackTrace();
        }

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);
        adicionarStage.setResizable(false);

    }
    
    /**
     * Volta para a scene anterior
     * @param event 
     */
    @FXML
    public void voltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }

    /**
     * Preenche a scene com a informacao do resultado da candidatura
     */
    public void transferData() {

        int idCandidatura = freelancerLogadoUI.tabelaCandidaturas.getSelectionModel().getSelectedItem().getIdCandidatura();
        try {
            int idAnuncio = efectuarCandidaturaController.findById(idCandidatura).getIdAnuncio();
            String nifOrganizacao = efectuarCandidaturaController.findTarefa(idAnuncio).getNifOrganizacao();
            txtIdAnuncio.setText(Integer.toString(idAnuncio));
            txtReferencia.setText(efectuarCandidaturaController.getAnuncio(idAnuncio).getReferenciaTarefa());
            txtDesignacao.setText(efectuarCandidaturaController.findTarefa(idAnuncio).getDesignacao());
            txtDescInformal.setText(efectuarCandidaturaController.findTarefa(idAnuncio).getDescInformal());
            txtDescTecnica.setText(efectuarCandidaturaController.findTarefa(idAnuncio).getDescTecnica());
            txtEmailColaborador.setText(efectuarCandidaturaController.findTarefa(idAnuncio).getEmailColaborador());
            //txtNomeOrganizacao.setText(efectuarCandidaturaController.findByNif(nifOrganizacao).getNome());

        }catch (SQLException exception){
            exception.printStackTrace();
        }

    }
}
