package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.domain.CompetenciaTecnica;
import com.grupo2.t4j.domain.Freelancer;
import com.grupo2.t4j.domain.GrauProficiencia;
import com.grupo2.t4j.domain.ReconhecimentoGP;
import com.grupo2.t4j.dto.CompetenciaTecnicaDTO;
import com.grupo2.t4j.dto.FreelancerDTO;
import com.grupo2.t4j.dto.GrauProficienciaDTO;
import com.grupo2.t4j.dto.ReconhecimentoGPDTO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class AdicionarReconhecimentoGPUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;

    private RegistarReconhecimentoGPController registarReconhecimentoGPController;
    private RegistarFreelancerController registarFreelancerController;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    private RegistarGrauProficienciaController registarGrauProficienciaController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;

    private Stage adicionarStage;

    @FXML TextField txtNomeFreelancer;
    @FXML TextField txtIDataValidacao;
    @FXML ComboBox<FreelancerDTO> cmbEmailFreelancer;
    @FXML ComboBox<CompetenciaTecnicaDTO> cmbCompetencia;
    @FXML ComboBox<GrauProficienciaDTO> cmbProficiencia;
    @FXML Button btnAddCompetencia;
    @FXML Button btnCancelar;
    @FXML Button btnSair;
    @FXML Label txtEmail;
    
    ////Tabela Reconhecimento///////////////////////
    @FXML TableColumn<Object, Object> txtCompTec;
    @FXML TableColumn<Object, Object> txtDataReconhecimento;
    @FXML TableColumn<Object, Object> txtGrau;
    @FXML TableView<ReconhecimentoGPDTO> tabelaReconhecimento;

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

        registarReconhecimentoGPController = new RegistarReconhecimentoGPController();
        registarFreelancerController = new RegistarFreelancerController();
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();

        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        txtEmail.setText(gestaoUtilizadoresController.getEmail());

        try {
            registarGrauProficienciaController = new RegistarGrauProficienciaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        try {
            cmbEmailFreelancer.getItems().setAll(registarFreelancerController.getAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            cmbCompetencia.getItems().setAll(registarCompetenciaTecnicaController.getAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        cmbCompetencia.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateCmbGrauProficiencia(event);

                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });

        cmbEmailFreelancer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateTxtNomeFreelancer(event);
                    mostrarCompetencias();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    /**
     * Atualiza a combobox de grau de proficiencia de acordo com a competencia tecnica escolhida
     * @param actionEvent
     * @throws SQLException 
     */
    public void updateCmbGrauProficiencia(ActionEvent actionEvent) throws SQLException {
        String codigoCompetenciaTecnica = cmbCompetencia.getSelectionModel().getSelectedItem().getCodigo();
        cmbProficiencia.getItems().setAll(
                registarGrauProficienciaController.findByCompetenciaTecnica(codigoCompetenciaTecnica));
    }

    /**
     * Preenche a tabela de competencias do Freelancer
     * @throws SQLException 
     */
    public void mostrarCompetencias () throws SQLException {
        String emailFreelancer = cmbEmailFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText();
        tabelaReconhecimento.getItems().setAll(registarReconhecimentoGPController.getAll(emailFreelancer));
        
        txtCompTec.setCellValueFactory(new PropertyValueFactory<>("descBreveCompetencia"));
        txtGrau.setCellValueFactory(new PropertyValueFactory<>("designacaoGrau"));
        txtDataReconhecimento.setCellValueFactory(new PropertyValueFactory<>("dataReconhecimento"));
    }

    /**
     * Atualiza o nome do Freelancer de acordo com o escolhido na combobox
     * @param actionEvent
     * @throws SQLException 
     */
    public void updateTxtNomeFreelancer(ActionEvent actionEvent) throws SQLException {
        String emailFreelancer = cmbEmailFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText();
        txtNomeFreelancer.setText(registarFreelancerController.findByEmail(emailFreelancer).getNome());
    }

    /**
     * Adiciona um reconhecimento de competencia tecnica ao Freelancer
     * @param event 
     */
    @FXML
    void addCompetencia(ActionEvent event) {
        try {
            boolean adicionou = registarReconhecimentoGPController.registarReconhecimentoGP(
                    cmbProficiencia.getSelectionModel().getSelectedItem().getIdGrauProficiencia(),
                    cmbEmailFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText(),
                    txtIDataValidacao.getText());

            if (adicionou) {
                mostrarCompetencias();

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                        MainApp.TITULO_APLICACAO, "Registar Validação de Competência Técnica.",
                        "Competência Técnica de Freelancer validada com sucesso.").show();
            }
        } catch (IllegalArgumentException | SQLException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Registar Validação de Competência Técnica - Erro nos dados.",
                    "Não foi possível validar a Competência Técnica." + exception.getMessage()).show();
        }
    }

    /**
     * Cancela a operacao
     * @param event 
     */
    @FXML
    public void cancelarAction(ActionEvent event) {
        txtIDataValidacao.clear();
    }

    /**
     * Volta para a scene anterior
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
