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
import com.grupo2.t4j.domain.ProcessoSeriacao;
import com.grupo2.t4j.dto.AtribuicaoDTO;
import com.grupo2.t4j.controller.AtribuirTarefaController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConsultarAtribuicaoColaboradorUI implements Initializable {

    @FXML private TextField txtRefTarefa;
    @FXML private TextField txtIdAnuncio;
    @FXML private TextField txtDataSeriacao;
    @FXML private TextField txtDataAtribuicao;
    @FXML private TextField txtDescInformal;
    @FXML private TextField txtDescTecnica;
    @FXML private TextField txtNomeFreelancer;
    @FXML private TextField txtEmailFreelancer;
    @FXML private TextField txtCodigoAtribuicao;
    @FXML private TextField txtCusto;
    @FXML private TextField txtDtInTarefa;
    @FXML private TextField txtDtFimTarefa;
    @FXML private TextField txtNumDias;
    @FXML private Button btnVoltar;
    @FXML Label txtEmail;

    private ColaboradorLogadoUI colaboradorLogadoUI;
    private AtribuirTarefaController atribuirTarefaController;
    private RegistarTarefaController registarTarefaController;
    private RegistarFreelancerController registarFreelancerController;
    private SeriarAnuncioController seriarAnuncioController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;

    private Stage adicionarStage;

    /**
     * Associa a scene ColaboradorLogadoUI como parent desta Scene
     * @param colaboradorLogadoUI
     */
    public void associarParentUI(ColaboradorLogadoUI colaboradorLogadoUI) {
        this.colaboradorLogadoUI = colaboradorLogadoUI;
    }

    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        atribuirTarefaController = new AtribuirTarefaController();
        registarFreelancerController = new RegistarFreelancerController();
        seriarAnuncioController = new SeriarAnuncioController();

        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        txtEmail.setText(gestaoUtilizadoresController.getEmail());

        try {
            registarTarefaController = new RegistarTarefaController();
        }catch (SQLException exception) {
            exception.printStackTrace();
        }


        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
    }

    /**
     * Preenche a scene com a informacão do anuncio     *
     */
    public void transferData() {

        String refTarefa = colaboradorLogadoUI.tabelaAtribuicoes.getSelectionModel().getSelectedItem().getRefTarefa();
        try {
            AtribuicaoDTO atribuicaoDTO = atribuirTarefaController.findAtribuicaoByTarefa(refTarefa);

            int idAnuncio = atribuicaoDTO.getIdAnuncio();
            String emailFreelancer = atribuicaoDTO.getEmailFreelancer();

            txtRefTarefa.setText(refTarefa);
            txtIdAnuncio.setText(Integer.toString(idAnuncio));
            txtDataSeriacao.setText(atribuirTarefaController.getProcessoSeriacaoByAnuncio(idAnuncio).getDataSeriacao());
            txtDataAtribuicao.setText(atribuicaoDTO.getDataAtribuicao());
            txtDescInformal.setText(atribuirTarefaController.findTarefa(idAnuncio).getDescInformal());
            txtDescTecnica.setText(atribuirTarefaController.findTarefa(idAnuncio).getDescTecnica());
            txtNomeFreelancer.setText(atribuirTarefaController.findByEmail(emailFreelancer).getNome());
            txtEmailFreelancer.setText(emailFreelancer);
            txtCodigoAtribuicao.setText(atribuicaoDTO.getCodigoAtribuicao());
            txtCusto.setText(Double.toString(atribuicaoDTO.getValorAceite()));
            txtDtInTarefa.setText(atribuicaoDTO.getDataInicioTarefa());
            txtDtFimTarefa.setText(atribuicaoDTO.getDataFimTarefa());
            txtNumDias.setText(Integer.toString(atribuicaoDTO.getNumDiasAceite()));

        }catch (SQLException exception) {
                exception.printStackTrace();
        }
    }

    /**
     * Volta para a scene anterior
     * @param event
     */
    @FXML
    void voltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }
}
