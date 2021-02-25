package com.grupo2.t4j.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.grupo2.t4j.controller.PublicarTarefaController;
import com.grupo2.t4j.model.DesignacaoSeriacao;
import com.grupo2.t4j.model.TipoRegimento;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author acris
 */
public class PublicarTarefaUI implements Initializable {
    
    @FXML DatePicker dtFimPublicitacao;

    @FXML TextArea txtRegrasGerais;

    @FXML DatePicker dtInicioCandidaturas;

    @FXML Button btnPublicar;

    @FXML DatePicker dtFimCandidaturas;

    @FXML Button btnVoltar;

    @FXML DatePicker dtInicioPublicitacao;

    @FXML DatePicker dtInicioSeriacao;

    @FXML DatePicker dtFimSeriacao;

    @FXML ComboBox<TipoRegimento> cmbTipoSeriacao;
    
    private ColaboradorLogadoUI colaboradorLogadoUI;
    private PublicarTarefaController publicarTarefaController;
    
    public void associarParentUI(ColaboradorLogadoUI colaboradorLogadoUI) {
        this.colaboradorLogadoUI = colaboradorLogadoUI;
    }

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        publicarTarefaController = new PublicarTarefaController();
        
        
        try {
            cmbTipoSeriacao.getItems().setAll(publicarTarefaController.getAllRegimento());
        } catch (SQLException exception) {
           exception.printStackTrace();
        }
        
        cmbTipoSeriacao.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               txtRegrasGerais.setText(cmbTipoSeriacao.getSelectionModel().getSelectedItem().getDescricaoRegras());
           }
           });
    }    
    @FXML
    void publicarTarefaAction(ActionEvent actionEvent) throws SQLException{
        try {
            boolean adicionou = publicarTarefaController.publicarTarefa(
                    colaboradorLogadoUI.listViewTarefas.getSelectionModel().getSelectedItem().getReferencia(),
                    colaboradorLogadoUI.listViewTarefas.getSelectionModel().getSelectedItem().getNifOrganizacao(),
                    dtInicioPublicitacao.getValue(),
                    dtFimPublicitacao.getValue(),
                    dtInicioCandidaturas.getValue(),
                    dtFimCandidaturas.getValue(),
                    dtInicioSeriacao.getValue(),
                    dtFimSeriacao.getValue(),
                    cmbTipoSeriacao.getSelectionModel().getSelectedItem().getIdTipoRegimento());

            /*if (adicionou){
                colaboradorLogadoUI.updateListViewTarefas();
            }*/
            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Publicar Tarefa.",
                    adicionou ? "Tarefa publicada com sucesso."
                            : "Não foi possível publicar a Tarefa.").show();

        }
        catch (IllegalArgumentException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    iae.getMessage()).show();

        }

        closePublicarTarefa(actionEvent);
        
    }

    
    @FXML
    void voltarAtras(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }
    
    private void closePublicarTarefa(ActionEvent actionEvent) {
        /*this.cmbAreaActividade.setItems(null);
        this.cmbCategoriaTarefa.setItems(null);
        this.txtReferencia.clear();
        this.txtDesignacao.clear();
        this.txtDescInformal.clear();
        this.txtDescTecnica.clear();
        this.txtEstimativaDuracao.clear();
        this.txtEstimativaCusto.clear();*/


        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
