package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarFreelancerController;
import com.grupo2.t4j.controller.RegistarHabilitacaoAcademicaController;
import com.grupo2.t4j.domain.Freelancer;
import com.grupo2.t4j.domain.HabilitacaoAcademica;
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
public class AdicionarHabilitacaoAcademicaUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private RegistarHabilitacaoAcademicaController registarHabilitacaoAcademicaController;
    private RegistarFreelancerController registarFreelancerController;
    private Stage adicionarStage;

    @FXML
    TextField txtNomeFreelancer;
    @FXML
    TextField txtGrau;
    @FXML
    TextField txtDesignacao;
    @FXML
    TextField txtInstituicao;
    @FXML
    TextField txtMedia;
    @FXML
    ComboBox<Freelancer> cmbEmailFreelancer;
    @FXML
    ListView<HabilitacaoAcademica> listaHabilitacaoFreelancer;
    @FXML
    Button btnAddHabilitacao;
    @FXML
    Button btnCancelar;
    @FXML
    Button btnSair;

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
        registarHabilitacaoAcademicaController = new RegistarHabilitacaoAcademicaController();
        registarFreelancerController = new RegistarFreelancerController();

        cmbEmailFreelancer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateListViewHabilitacaoFreelancer(
                            cmbEmailFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText());
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

            }
        });

        try {
            cmbEmailFreelancer.getItems().setAll(registarFreelancerController.getAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        cmbEmailFreelancer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateTxtNomeFreelancer(event);
                    updateListViewHabilitacaoFreelancer(
                            cmbEmailFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText());
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    @FXML
    void addHabilitacao(ActionEvent event) {
        try {
            boolean adicionou = registarHabilitacaoAcademicaController.registarHabilitacaoAcademica(
                    txtGrau.getText(),
                    txtDesignacao.getText(),
                    txtInstituicao.getText(),
                    Double.parseDouble(txtMedia.getText()),
                    cmbEmailFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText());

            if (adicionou) {
                updateListViewHabilitacaoFreelancer(
                        cmbEmailFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText());
                this.txtGrau.clear();
                this.txtDesignacao.clear();
                this.txtInstituicao.clear();
                this.txtMedia.clear();
                btnAddHabilitacao.setDisable(true);

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                        MainApp.TITULO_APLICACAO, "Registar Habilitação Académica.",
                        "Habilitação Académica registada com sucesso.").show();
            }
        } catch (IllegalArgumentException | SQLException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Registar Habilitação Académica - Erro nos dados.",
                    "Não foi possível registar a Habilitação Académica." + exception.getMessage()).show();

        }
    }

    @FXML
    public void cancelarAction(ActionEvent event) {
        this.txtGrau.clear();
        this.txtDesignacao.clear();
        this.txtInstituicao.clear();
        this.txtMedia.clear();
    }

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

    public void updateListViewHabilitacaoFreelancer(String emailFreelancer) throws SQLException {
        listaHabilitacaoFreelancer.getItems().setAll(
                registarHabilitacaoAcademicaController.getAll(emailFreelancer));
    }

    public void updateTxtNomeFreelancer(ActionEvent actionEvent) throws SQLException {
        String emailFreelancer = cmbEmailFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText();
        txtNomeFreelancer.setText(registarFreelancerController.findByEmail(emailFreelancer).getNome());
    }

}
