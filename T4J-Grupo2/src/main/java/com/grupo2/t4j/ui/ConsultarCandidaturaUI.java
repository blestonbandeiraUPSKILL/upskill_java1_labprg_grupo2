package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.EditarCandidaturaController;
import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarTarefaController;
import com.grupo2.t4j.domain.Candidatura;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author marta
 */
public class ConsultarCandidaturaUI implements Initializable {

    @FXML Button btnEditarDados;
    @FXML Button btnApagar;
    @FXML Button btnCancelar;
    @FXML Button btnVoltar;
    @FXML Button btnGuardar;
    @FXML TextArea txtAnuncio;
    @FXML TextArea txtApresentacao;
    @FXML TextArea txtMotivacao;
    @FXML TextField txtValor;
    @FXML TextField txtDias;
    @FXML TextField txtDataEdicao;

    private Stage adicionarStage;
    private FreelancerLogadoUI freelancerLogadoUI;
    private EditarCandidaturaController editarCandidaturaController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private RegistarTarefaController registarTarefaController;

    /**
     * Associa a scene FreelancerLogadoUI como parent desta Scene 
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

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);
        adicionarStage.setResizable(false);

        //btnEditarDados.setDisable(true);
        editarCandidaturaController = new EditarCandidaturaController();
        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        try {
            registarTarefaController = new RegistarTarefaController();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarCandidaturaUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtAnuncio.setDisable(false);
        txtAnuncio.setEditable(false);
    }

    /**
     * Prepara a scene para a edicao da candidatura
     * @param event 
     */
    @FXML
    private void editarDados(ActionEvent event) {
        txtApresentacao.setEditable(true);
        txtMotivacao.setEditable(false);
        txtValor.setEditable(false);
        txtDias.setEditable(false);
        btnEditarDados.setVisible(false);
        btnVoltar.setVisible(false);
        btnGuardar.setVisible(true);
        btnCancelar.setVisible(true);
        
    }

    /**
     * Preenche a scene com os dados da candidatura
     * @throws SQLException 
     */
    public void transferData() throws SQLException {
        int idCandidatura = freelancerLogadoUI.tabelaCandidaturas.getSelectionModel().getSelectedItem().getIdCandidatura();
        String apresentacao = editarCandidaturaController.findById(idCandidatura).getApresentacao();
        String motivacao = editarCandidaturaController.findById(idCandidatura).getMotivacao();
        double valor = editarCandidaturaController.findById(idCandidatura).getValorPretendido();
        int duracao = editarCandidaturaController.findById(idCandidatura).getNumeroDias();
        String dataEdicao = editarCandidaturaController.findById(idCandidatura).getDataEdicaoCandidatura();
        txtApresentacao.setText(apresentacao);
        txtMotivacao.setText(motivacao);
        txtValor.setText(String.valueOf(valor));
        txtDias.setText(String.valueOf(duracao));
        txtAnuncio.setText(registarTarefaController.findTarefa(getIdAnuncio()).toStringCompleto());
        txtDataEdicao.setText(dataEdicao);
    }

    /**
     * Guarda as alteracoes feitas a candidatura
     * @param actionEvent
     * @throws SQLException 
     */
    public void guardarAction(ActionEvent actionEvent) throws SQLException {

        int idCandidatura = freelancerLogadoUI.tabelaCandidaturas.getSelectionModel().getSelectedItem().getIdCandidatura();
        
        try {
            boolean editou = editarCandidaturaController.updateCandidatura(idCandidatura, Double.parseDouble(txtValor.getText()),
                    Integer.parseInt(txtDias.getText()), txtApresentacao.getText(),
                    txtMotivacao.getText());

            if (editou) {

                freelancerLogadoUI.updateTableViewCandidaturas();
                freelancerLogadoUI.updateTableViewAnuncio();
                btnVoltar.setText("Voltar");

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                        MainApp.TITULO_APLICACAO,
                        "Editar Candidatura.",
                        "Candidatura editada com sucesso.").show();

                ((Node) actionEvent.getSource()).getScene().getWindow().hide();

            }

        } catch (IllegalArgumentException | SQLException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Editar Candidatura - Erro nos dados.",
                    "Não foi possível Editar a candidatura: " + exception.getMessage()).show();
        }

    }

    /**
     * Volta para a scene anterior
     * @param actionEvent 
     */
    public void voltarAction(ActionEvent actionEvent) {
        btnVoltar.getScene().getWindow().hide();
    }
    
    /**
     * Cancela a edicao da candidatura
     * @param actionEvent 
     */
    public void cancelarAction(ActionEvent actionEvent) {
        btnVoltar.setVisible(true);
        btnEditarDados.setVisible(true);
        btnCancelar.setVisible(false);
        btnGuardar.setVisible(false);
        
        txtApresentacao.setDisable(true);
        txtMotivacao.setDisable(true);
        txtValor.setDisable(true);
        txtDias.setDisable(true);
    }

    /**
     * Verifica se a candidatura selecionada e editavel
     * @throws SQLException 
     */
    public void isCandidaturaEditavel() throws SQLException {
        String emailFreelancer = gestaoUtilizadoresController.getEmail();
        int idCandidatura = freelancerLogadoUI.tabelaCandidaturas.getSelectionModel().getSelectedItem().getIdCandidatura();
        List<Integer> listaCandidaturasEditaveis = editarCandidaturaController.getAllCandidaturasEditaveis(emailFreelancer);

        for (int id : listaCandidaturasEditaveis) {
            if (id == idCandidatura) {
                btnEditarDados.setDisable(false);
            }
        }
    }

    /**
     * Devolve o id do anuncio selecionado
     * @return
     * @throws SQLException 
     */
    public int getIdAnuncio() throws SQLException {
        int idCandidatura = freelancerLogadoUI.tabelaCandidaturas.getSelectionModel().getSelectedItem().getIdCandidatura();
        Candidatura candidatura = editarCandidaturaController.findById(idCandidatura);
        int idAnuncio = candidatura.getIdAnuncio();

        return idAnuncio;
    }
}
