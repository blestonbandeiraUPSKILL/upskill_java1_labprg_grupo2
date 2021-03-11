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
import com.grupo2.t4j.domain.Anuncio;
import com.grupo2.t4j.domain.Tarefa;
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

    
    @FXML TextField txtIdAnuncio;
    @FXML TextField txtDtInPublicitacao;
    @FXML TextField txtDtFimPublicitacao;
    @FXML TextField txtDtInCandidatura;
    @FXML TextField txtDtFimCandidatura;
    @FXML TextField txtDtInSeriacao;
    @FXML TextField txtDtFimSeriacao;
    @FXML TextField txtTipoRegDesignacao;
    @FXML TextField txtRegras;
    @FXML TextField txtReferencia;
    @FXML TextField txtDesignacao;
    @FXML TextField txtDuracao;
    @FXML TextField txtCusto;
    @FXML TextField txtDescInformal;
    @FXML TextField txDescTecnica;
    @FXML Button btnVoltar;

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
        int idAnuncio = registarTarefaController.findIdAnuncio(nifOrganizacao, referenciaTarefa);
        Anuncio anuncio = seriarAnuncioController.getAnuncio(idAnuncio);
        int idRegimento = anuncio.getIdTipoRegimento();
        
        txtIdAnuncio.setText(Integer.toString(idAnuncio));
        txtDtInPublicitacao.setText(anuncio.getDtInicioPub());
        txtDtFimPublicitacao.setText(anuncio.getDtFimPub());
        txtDtInCandidatura.setText(anuncio.getDtInicioCand());
        txtDtFimCandidatura.setText(anuncio.getDtFimCand());
        txtDtInSeriacao.setText(anuncio.getDtInicioSeriacao());
        txtDtFimSeriacao.setText(anuncio.getDtFimSeriacao());

        txtTipoRegDesignacao.setText(seriarAnuncioController.findRegimentoById(idRegimento).getDesignacao());
        txtRegras.setText(seriarAnuncioController.findRegimentoById(idRegimento).getDescricaoRegras());
        txtReferencia.setText(referenciaTarefa);
        Tarefa tarefa = registarTarefaController.findTarefa(idAnuncio);
        txtDesignacao.setText(tarefa.getDesignacao());
        txtDuracao.setText(Integer.toString(tarefa.getDuracaoEst()));
        txtCusto.setText(Double.toString(tarefa.getCustoEst()));
        txtDescInformal.setText(tarefa.getDescInformal());
        txDescTecnica.setText(tarefa.getDescTecnica());
    }
    
    
    @FXML
    public void voltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }
}
