package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.controller.RegistarCaracterizacaoCTController;
import com.grupo2.t4j.domain.CaracterizacaoCT;
import com.grupo2.t4j.dto.CaracterizacaoCTDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class ConsultarCategoriaUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private Stage adicionarStage;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarCaracterizacaoCTController registarCaracterizacaoCTController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;

    @FXML TextField txtDescBreve;
    @FXML TextField txtCodigo;
    @FXML TextField txtAreaActividade;
    @FXML TextArea txtDescDetalhada;
    @FXML Button btnVoltar;
    @FXML TableView<CaracterizacaoCTDTO> tabelaCompetencias;
    @FXML TableColumn<Object, Object> txtCompetencia;
    @FXML TableColumn<Object, Object> txtGrau;
    @FXML TableColumn<Object, Object> txtObrigatoriedade;
    @FXML Label txt_email;

    /**
     * Associa a scene AdministrativoLogadoUI como parent desta Scene 
     * @param administrativoLogadoUI
     */
    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);
        adicionarStage.setResizable(false);

        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCaracterizacaoCTController = new RegistarCaracterizacaoCTController();

        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        txt_email.setText(gestaoUtilizadoresController.getEmail());

    }

    /**
     * Preenche a scene com os dados da categoria selecionada
     * @throws SQLException 
     */
    public void transferData() throws SQLException {

        String codigoAreaActividade = administrativoLogadoUI.tableViewCategoria.getSelectionModel().getSelectedItem().getCodigoAreaActividade();
        String codigoCategoria = administrativoLogadoUI.tableViewCategoria.getSelectionModel().getSelectedItem().getCodigoCategoria();
        txtCodigo.setText(administrativoLogadoUI.tableViewCategoria.getSelectionModel().getSelectedItem().getCodigoCategoria());
        txtAreaActividade.setText(registarAreaActividadeController.getAreaActividade(codigoAreaActividade).getDescBreve());
        txtDescBreve.setText(administrativoLogadoUI.tableViewCategoria.getSelectionModel().getSelectedItem().getDescBreve());
        txtDescDetalhada.setText(administrativoLogadoUI.tableViewCategoria.getSelectionModel().getSelectedItem().getDescDetalhada());
        tabelaCompetencias.getItems().setAll(registarCaracterizacaoCTController.getAllByCategoria(codigoCategoria));
        txtGrau.setCellValueFactory(new PropertyValueFactory<>("designacaoGrau"));
        txtObrigatoriedade.setCellValueFactory((new PropertyValueFactory<>("obrigatoriedade")));
        txtCompetencia.setCellValueFactory(new PropertyValueFactory<>("descBreveCompetencia"));
    }

    /**
     * Volta para a scene anterior
     * @param event 
     */
    @FXML
    void voltarAtras(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }

}
