package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.controller.RegistarGrauProficienciaController;
import com.grupo2.t4j.domain.GrauProficiencia;
import com.grupo2.t4j.dto.GrauProficienciaDTO;
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
public class ConsultarCompetenciaTecnicaUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private RegistarGrauProficienciaController registarGrauProficienciaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private Stage adicionarStage;

    @FXML TextArea txtDescricaoDetalhada;
    @FXML TextField txtAreaActividade;
    @FXML TextField txtDescricaoBreve;
    @FXML TextField txtCodigoCompetenciaTecnica;
    @FXML Button btnVoltar;
    @FXML TableView<GrauProficienciaDTO> tabelaGrausAplicaveis;
    @FXML TableColumn<Object, Object> txtGrau;
    @FXML TableColumn<Object, Object> txtDesignacao;
    @FXML Label txtEmail;

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

        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        txtEmail.setText(gestaoUtilizadoresController.getEmail());

        try {

            adicionarStage = new Stage();
            adicionarStage.initModality(Modality.APPLICATION_MODAL);;
            adicionarStage.setResizable(false);

            registarAreaActividadeController = new RegistarAreaActividadeController();
            registarGrauProficienciaController = new RegistarGrauProficienciaController();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Preenche a scene com os dados da competencia tecnica
     * @throws SQLException 
     */
    public void transferData() throws SQLException {

        String codigoCompetenciaTecnica = administrativoLogadoUI.tableViewCompetenciaTecnica.getSelectionModel().getSelectedItem().getCodigo();
        String codigoAreaActividade = administrativoLogadoUI.tableViewCompetenciaTecnica.getSelectionModel().getSelectedItem().getCodigoAreaActividade();
        txtCodigoCompetenciaTecnica.setText(codigoCompetenciaTecnica);
        txtDescricaoBreve.setText(administrativoLogadoUI.tableViewCompetenciaTecnica.getSelectionModel().getSelectedItem().getDescricaoBreve());
        txtDescricaoDetalhada.setText(administrativoLogadoUI.tableViewCompetenciaTecnica.getSelectionModel().getSelectedItem().getDescricaoDetalhada());
        txtAreaActividade.setText(registarAreaActividadeController.getAreaActividade(codigoAreaActividade).getDescBreve());
        tabelaGrausAplicaveis.getItems().setAll(registarGrauProficienciaController.getAllByCompetenciaTecnica(txtCodigoCompetenciaTecnica.getText()));
        txtGrau.setCellValueFactory(new PropertyValueFactory<>("grau"));
        txtDesignacao.setCellValueFactory(new PropertyValueFactory<>("designacao"));
    }



    /**
     * Volta para a scene anterior
     * @param actionEvent 
     */

    public void voltarAtras(ActionEvent actionEvent) {
        btnVoltar.getScene().getWindow().hide();
    }

}
