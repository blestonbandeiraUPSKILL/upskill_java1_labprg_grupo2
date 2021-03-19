package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarFreelancerController;
import com.grupo2.t4j.controller.RegistarHabilitacaoAcademicaController;
import com.grupo2.t4j.domain.Freelancer;
import com.grupo2.t4j.domain.HabilitacaoAcademica;
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
public class AdicionarHabilitacaoAcademicaUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private RegistarHabilitacaoAcademicaController registarHabilitacaoAcademicaController;
    private RegistarFreelancerController registarFreelancerController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private Stage adicionarStage;

    @FXML TextField txtNomeFreelancer;
    @FXML TextField txtGrau;
    @FXML TextField txtDesignacao;
    @FXML TextField txtInstituicao;
    @FXML TextField txtMedia;
    @FXML ComboBox<Freelancer> cmbEmailFreelancer;
    @FXML Button btnAddHabilitacao;
    @FXML Button btnCancelar;
    @FXML Button btnSair;
    @FXML Label txt_email;
    
    ////Tabela Habilitacoes Academicas//////////////
    @FXML TableColumn<Object, Object> txtGrauHabilitacao;
    @FXML TableColumn<Object, Object> txtDesignacaoCurso;
    @FXML TableColumn<Object, Object> txtMediaCurso;
    @FXML TableColumn<Object, Object> txtInstituicaoEnsino;
    @FXML TableView<HabilitacaoAcademica> tabelaHabilitacao;

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
        registarHabilitacaoAcademicaController = new RegistarHabilitacaoAcademicaController();
        registarFreelancerController = new RegistarFreelancerController();

        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        txt_email.setText(gestaoUtilizadoresController.getEmail());

        cmbEmailFreelancer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    mostrarHabilitacao();
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
                    mostrarHabilitacao();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    /**
     * Adiciona uma habilitacao academica ao Freelancer
     * @param event 
     */
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
                mostrarHabilitacao();
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

    /**
     * Cancela a operacao
     * @param event 
     */
    @FXML
    public void cancelarAction(ActionEvent event) {
        this.txtGrau.clear();
        this.txtDesignacao.clear();
        this.txtInstituicao.clear();
        this.txtMedia.clear();
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
  
    /**
     * Preenche a tabela de habilitacoes do Freelancer
     * @throws SQLException 
     */
    public void mostrarHabilitacao () throws SQLException {
        String emailFreelancer = cmbEmailFreelancer.getSelectionModel().getSelectedItem().getEmail().getEmailText();
        tabelaHabilitacao.getItems().setAll(registarFreelancerController.getAllHabsAcademicas(emailFreelancer));
        
        txtGrauHabilitacao.setCellValueFactory(new PropertyValueFactory<>("grau"));
        txtMediaCurso.setCellValueFactory(new PropertyValueFactory<>("mediaCurso"));
        txtInstituicaoEnsino.setCellValueFactory(new PropertyValueFactory<>("nomeInstituicao"));
        txtDesignacaoCurso.setCellValueFactory(new PropertyValueFactory<>("designacaoCurso"));
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

}
