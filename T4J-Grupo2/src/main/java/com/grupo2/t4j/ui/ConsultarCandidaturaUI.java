package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.EditarCandidaturaController;
import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarTarefaController;
import com.grupo2.t4j.domain.Candidatura;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * FXML Controller class
 *
 * @author marta
 */
public class ConsultarCandidaturaUI implements Initializable {

    @FXML
    Button btnEditarDados;
    @FXML
    Button btnApagar;
    @FXML
    Button btnVoltar;
    @FXML
    Button btnGuardar;
    @FXML
    TextArea txtAnuncio;
    @FXML
    TextArea txtApresentacao;
    @FXML
    TextArea txtMotivacao;
    @FXML
    TextField txtValor;
    @FXML
    TextField txtDias;
    @FXML
    TextField txtDataEdicao;

    private Stage adicionarStage;
    private FreelancerLogadoUI freelancerLogadoUI;
    private EditarCandidaturaController editarCandidaturaController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private RegistarTarefaController registarTarefaController;

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

    @FXML
    private void editarDados(ActionEvent event) {
        txtApresentacao.setDisable(false);
        txtMotivacao.setDisable(false);
        txtValor.setDisable(false);
        txtDias.setDisable(false);
        btnGuardar.setVisible(true);
        btnEditarDados.setVisible(false);
    }

    public void transferData() throws SQLException {
        txtApresentacao.setText(freelancerLogadoUI.listViewCandidaturas.getSelectionModel().getSelectedItem().getApresentacao());
        txtMotivacao.setText(freelancerLogadoUI.listViewCandidaturas.getSelectionModel().getSelectedItem().getMotivacao());
        txtValor.setText(String.valueOf(freelancerLogadoUI.listViewCandidaturas.getSelectionModel().getSelectedItem().getValorPretendido()));
        txtDias.setText(String.valueOf(freelancerLogadoUI.listViewCandidaturas.getSelectionModel().getSelectedItem().getNumeroDias()));
        txtAnuncio.setText(registarTarefaController.findTarefa(getIdAnuncio()).toStringCompleto());
        txtDataEdicao.setText(freelancerLogadoUI.listViewCandidaturas.getSelectionModel().getSelectedItem().getDataEdicaoCandidatura());
    }

    public void guardarAction(ActionEvent actionEvent) throws SQLException {

        int idCandidatura = freelancerLogadoUI.listViewCandidaturas.getSelectionModel().getSelectedItem().getIdCandidatura();

        try {
            boolean editou = editarCandidaturaController.updateCandidatura(idCandidatura, Double.parseDouble(txtValor.getText()),
                    Integer.parseInt(txtDias.getText()), txtApresentacao.getText(),
                    txtMotivacao.getText());

            if (editou) {

                freelancerLogadoUI.updateListViewCandidaturas();
                //btnAddCandidatura.setDisable(true);

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

    public void voltarAction(ActionEvent actionEvent) {
        btnVoltar.getScene().getWindow().hide();
    }

    public void isCandidaturaEditavel() throws SQLException {
        String emailFreelancer = gestaoUtilizadoresController.getEmail();
        int idCandidatura = freelancerLogadoUI.listViewCandidaturas.getSelectionModel().getSelectedItem().getIdCandidatura();
        List<Integer> listaCandidaturasEditaveis = editarCandidaturaController.getAllCandidaturasEditaveis(emailFreelancer);

        for (int id : listaCandidaturasEditaveis) {
            if (id == idCandidatura) {
                btnEditarDados.setDisable(false);
            }
        }
    }

    public int getIdAnuncio() throws SQLException {
        int idAnuncio = freelancerLogadoUI.listViewCandidaturas.getSelectionModel().getSelectedItem().getIdAnuncio();

        return idAnuncio;
    }
}
