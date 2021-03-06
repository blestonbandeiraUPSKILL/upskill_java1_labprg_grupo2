package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarTarefaController;
import com.grupo2.t4j.controller.SeriarAnuncioController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConsultarAnuncioColaboradorUI implements Initializable {

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
    @FXML Label txtEmail;

    private ColaboradorLogadoUI colaboradorLogadoUI;
    private SeriarAnuncioController seriarAnuncioController;
    private RegistarTarefaController registarTarefaController;
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

        seriarAnuncioController = new SeriarAnuncioController();

        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        txtEmail.setText(gestaoUtilizadoresController.getEmail());

        try {
            registarTarefaController = new RegistarTarefaController();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
    }

    /**
     * Preenche a scene com a informacao do anuncio
     * @throws SQLException 
     */
    public void transferData() throws SQLException {
        String referenciaTarefa = colaboradorLogadoUI.cmbAnuncio.getSelectionModel().getSelectedItem().toString();
        String nifOrganizacao = colaboradorLogadoUI.getNifOrganizacao();

        int idAnuncio = registarTarefaController.findIdAnuncio(nifOrganizacao, referenciaTarefa);
        String dataInicioPublicitacao = seriarAnuncioController.getAnuncio(idAnuncio).getDtInicioPub();
        String dataFimPublicitacao = seriarAnuncioController.getAnuncio(idAnuncio).getDtFimPub();
        String dataInicioCandidatura = seriarAnuncioController.getAnuncio(idAnuncio).getDtInicioCand();
        String dataFimCandidatura = seriarAnuncioController.getAnuncio(idAnuncio).getDtFimCand();
        String dataInicioSeriacao = seriarAnuncioController.getAnuncio(idAnuncio).getdataInicioSeriacao();
        String dataFimSeriacao = seriarAnuncioController.getAnuncio(idAnuncio).getdataFimSeriacao();
        int idRegimento = seriarAnuncioController.getAnuncio(idAnuncio).getIdTipoRegimento();

        txtIdAnuncio.setText(Integer.toString(idAnuncio));
        txtDtInPublicitacao.setText(dataInicioPublicitacao);
        txtDtFimPublicitacao.setText(dataFimPublicitacao);
        txtDtInCandidatura.setText(dataInicioCandidatura);
        txtDtFimCandidatura.setText(dataFimCandidatura);
        txtDtInSeriacao.setText(dataInicioSeriacao);
        txtDtFimSeriacao.setText(dataFimSeriacao);

        txtTipoRegDesignacao.setText(seriarAnuncioController.findRegimentoById(idRegimento).getDesignacao());
        txtRegras.setText(seriarAnuncioController.findRegimentoById(idRegimento).getDescricaoRegras());
        txtReferencia.setText(referenciaTarefa);
        String designacao = registarTarefaController.findTarefa(idAnuncio).getDesignacao();
        int duracao = registarTarefaController.findTarefa(idAnuncio).getDuracaoEst();
        double custo = registarTarefaController.findTarefa(idAnuncio).getCustoEst();
        String descInformal= registarTarefaController.findTarefa(idAnuncio).getDescInformal();
        String descTecnica = registarTarefaController.findTarefa(idAnuncio).getDescTecnica();

        txtDesignacao.setText(designacao);
        txtDuracao.setText(Integer.toString(duracao));
        txtCusto.setText(Double.toString(custo));
        txtDescInformal.setText(descInformal);
        txDescTecnica.setText(descTecnica);
    }

    /**
     * Volta para a scene anterior
     * @param event 
     */
    @FXML
    public void voltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }
}
