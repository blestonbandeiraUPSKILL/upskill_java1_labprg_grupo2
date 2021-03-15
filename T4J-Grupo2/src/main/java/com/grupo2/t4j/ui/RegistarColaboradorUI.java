package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarColaboradorController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class RegistarColaboradorUI implements Initializable {

    private RegistarColaboradorController registarColaboradorController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private GestorLogadoUI gestorLogadoUI;
    private Stage adicionarStage;

    @FXML TextField txtNomeColaborador;
    @FXML TextField txtFuncaoColaborador;
    @FXML TextField txtTelefoneColaborador;
    @FXML TextField txtEmailColaborador;
    @FXML TextField txtPasswordColaborador;
    @FXML TextField txtNifOrganizacao;
    @FXML Button btnCancelarRegisto;
    @FXML Button btnRegistarColaborador;
    @FXML Button btnSair;

     /**
     * Associa a scene GestorLogadoUI como parent desta Scene 
     * @param gestorLogadoUI
     */
    public void associarParentUI(GestorLogadoUI gestorLogadoUI) {
        this.gestorLogadoUI = gestorLogadoUI;
    }
    
    /**
    * Initializes the controller (UI) class.
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        registarColaboradorController = new RegistarColaboradorController();

    }

    /**
     * Associa o nif da organizacao do gestor ao colaborador
     * @throws SQLException 
     */
    void transferNif() throws SQLException {
        txtNifOrganizacao.setText(
                gestorLogadoUI.getNifOrganizacao());
    }

    /**
     * Regista um novo colaborador
     * @param actionEvent 
     */
    public void registarColaboradorAction(ActionEvent actionEvent) {

        try {
            boolean adicionou = registarColaboradorController.registarColaborador(
                    txtEmailColaborador.getText(),
                    txtNomeColaborador.getText(),
                    txtFuncaoColaborador.getText(),
                    txtTelefoneColaborador.getText(),
                    txtNifOrganizacao.getText());

            if (adicionou) {
                txtPasswordColaborador.setText(
                        registarColaboradorController.findPassword(txtEmailColaborador.getText()).getPasswordText());
                btnRegistarColaborador.setDisable(true);
                btnCancelarRegisto.setText("Sair");

                gestorLogadoUI.updateListViewColaboradores();

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                        MainApp.TITULO_APLICACAO,
                        "Registar Colaborador.",
                        "Colaborador registado com sucesso.").show();
            }

        }

        catch (IllegalArgumentException | SQLException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    " Registar Colaborador + Erro nos dados.",
                    "Não foi possível registar o Colaborador." + exception.getMessage()).show();
        }
    }

    /**
     * Cancela o registo de um colaborador
     * @param actionEvent 
     */
    public void cancelarRegisto(ActionEvent actionEvent) {
        this.txtNomeColaborador.clear();
        this.txtEmailColaborador.clear();
        this.txtFuncaoColaborador.clear();
        this.txtTelefoneColaborador.clear();
        this.txtPasswordColaborador.clear();
        btnCancelarRegisto.getScene().getWindow().hide();
    }


}
