package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarFreelancerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class AdicionarFreelancerUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private RegistarFreelancerController registarFreelancerController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private Stage adicionarStage;

    @FXML TextField txtNomeFreelancer;
    @FXML TextField txtEmailFreelancer;
    @FXML TextField txtNIFFreelancer;
    @FXML TextField txtTelefoneFreelancer;
    @FXML TextField txtArruamentoFreelancer;
    @FXML TextField txtPortaFreelancer;
    @FXML TextField txtLocalidadeFreelancer;
    @FXML TextField txtCodPostalFreelancer;
    @FXML TextField txtPassFreelancer;
    @FXML Button btnAddFreelancer;
    @FXML Button btnCancelar;
    @FXML Button btnSair;
    @FXML Label txt_email;

    /**
     * Associa a scene AdministrativoLogadoUI como parent desta Scene 
     * @param administrativoLogadoUI 
     */
    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    /**
     * Initializes the controller (UI) class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        registarFreelancerController = new RegistarFreelancerController();

        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        txt_email.setText(gestaoUtilizadoresController.getEmail());

    }

    /**
     * Adiciona um novo Freelancer
     * @param event
     * @throws SQLException 
     */
    @FXML
    public void addFreelancer(ActionEvent event) throws SQLException {

        try {
            boolean adicionou = registarFreelancerController.registarFreelancer(
                    txtEmailFreelancer.getText().trim(),
                    txtNomeFreelancer.getText().trim(),
                    txtNIFFreelancer.getText().trim(),
                    txtTelefoneFreelancer.getText().trim(),
                    txtArruamentoFreelancer.getText().trim(),
                    txtPortaFreelancer.getText().trim(),
                    txtLocalidadeFreelancer.getText().trim(),
                    txtCodPostalFreelancer.getText().trim());

            if (adicionou) {
                txtPassFreelancer.setText(
                        registarFreelancerController.findPassword(
                                txtEmailFreelancer.getText()).getPasswordText());

                btnAddFreelancer.setDisable(true);
                btnAddFreelancer.disabledProperty();
                btnSair.setText("Voltar");

                administrativoLogadoUI.updateTableViewFreelancer();

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                        MainApp.TITULO_APLICACAO, "Registar Freelancer.",
                        "Freelancer registado com sucesso.").show();
            }
        } catch (IllegalArgumentException | SQLException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Registar Freelancer - Erro nos dados.",
                    "Não foi possível registar o Freelancer." + exception.getMessage()).show();

        }
    }

    /**
     * Cancela a operacao
     * @param event 
     */
    @FXML
    public void cancelarAction(ActionEvent event) {
        this.txtNomeFreelancer.clear();
        this.txtEmailFreelancer.clear();
        this.txtNIFFreelancer.clear();
        this.txtTelefoneFreelancer.clear();
        this.txtArruamentoFreelancer.clear();
        this.txtPortaFreelancer.clear();
        this.txtLocalidadeFreelancer.clear();
        this.txtCodPostalFreelancer.clear();
        this.txtPassFreelancer.clear();
    }

    /**
     * Volta a scene anterior
     * @param event 
     */
    @FXML
    public void sairAction(ActionEvent event) {
        Window window = btnSair.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que quer voltar à página anterior?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                }
            }
        });
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
