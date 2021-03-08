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
import com.grupo2.t4j.domain.HabilitacaoAcademica;
import com.grupo2.t4j.domain.ReconhecimentoGP;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
public class ConsultarCandidaturaFreelancerUI implements Initializable{

    @FXML private TextField txtIdAnuncio;
    
    @FXML private TextField txtIdCandidatura;
    
    @FXML private TextField txtNome;
    
    @FXML private TextField txtNIF;
    
    @FXML private TextField txtEmail;
    
    @FXML private TextField txtLocalidade;
   
    @FXML ListView<HabilitacaoAcademica>listaHabilitacoes;
    
    @FXML ListView<ReconhecimentoGP> listaCompetencias;    
            
    @FXML private TextField txtCustoAnuncio;

    @FXML private TextField txtCustoFreelancer;
    
    @FXML private TextField txtDuracaoAnuncio;   

    @FXML private TextField txtDuracaoFreelancer;
  
    @FXML private Button btnVoltar;
    
    private ColaboradorLogadoUI colaboradorLogadoUI;
    
    private RegistarFreelancerController registarFreelancerController;
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
        String email = colaboradorLogadoUI.tabelaFreelancers.getSelectionModel().getSelectedItem().getEmailFreelancer();
        int idAnuncio = colaboradorLogadoUI.tabelaFreelancers.getSelectionModel().getSelectedItem().getIdAnuncio();
        
        txtIdAnuncio.setText(Integer.toString(idAnuncio));
        txtIdCandidatura.setText(Integer.toString(colaboradorLogadoUI.tabelaFreelancers.getSelectionModel().getSelectedItem().getIdCandidatura()));
        txtNome.setText(registarFreelancerController.findByEmail(email).getNome());
        txtNIF.setText(registarFreelancerController.findByEmail(email).getNif());
        txtEmail.setText(email);
        txtLocalidade.setText(registarFreelancerController.getEnderecoPostal(email).getLocalidade());
        listaHabilitacoes.getItems().setAll(registarFreelancerController.getAllHabsAcademicas(email));
        listaCompetencias.getItems().setAll(registarFreelancerController.getAllReconhecimentoGP(email));
        txtCustoAnuncio.setText(Double.toString(registarTarefaController.findTarefa(idAnuncio).getCustoEst()));
        txtCustoFreelancer.setText(Double.toString(colaboradorLogadoUI.tabelaFreelancers.getSelectionModel().getSelectedItem().getValorPretendido()));
        txtDuracaoFreelancer.setText(Integer.toString(colaboradorLogadoUI.tabelaFreelancers.getSelectionModel().getSelectedItem().getNumeroDias()));
        txtDuracaoAnuncio.setText(Integer.toString(registarTarefaController.findTarefa(idAnuncio).getDuracaoEst()));
    }

    @FXML
    public void voltarAtras(ActionEvent event) {
         btnVoltar.getScene().getWindow().hide();
    }
}
