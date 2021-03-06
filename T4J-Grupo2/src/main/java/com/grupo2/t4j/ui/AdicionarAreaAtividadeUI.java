package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class AdicionarAreaAtividadeUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private Stage adicionarStage;

    @FXML Button btnCancelar;
    @FXML Button btnAddAreaAtividade;
    @FXML TextField txtCodigo;
    @FXML TextField txtDescricaoBreve;
    @FXML TextArea areaDescricaoDetalhada;
    @FXML Label txtEmail;

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

        registarAreaActividadeController = new RegistarAreaActividadeController();
        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
        
        txtEmail.setText(gestaoUtilizadoresController.getEmail());
    }

    /**
     * Cancela a operação
     * @param event 
     */
    @FXML
    void cancelarAction(ActionEvent event) {

        this.txtCodigo.clear();
        this.txtDescricaoBreve.clear();
        this.areaDescricaoDetalhada.clear();

        Window window = btnCancelar.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que quer voltar à página anterior, cancelando o actual registo?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                }
            }
        });

        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));

    }

    /**
     * Regista uma nova Area de Actividade
     * @param event 
     */
    @FXML
    public void registarAreaAction(ActionEvent event) {

        try {
            boolean adicionou = registarAreaActividadeController.registarAreaActividade(
                    txtCodigo.getText(),
                    txtDescricaoBreve.getText(),
                    areaDescricaoDetalhada.getText());

            if (adicionou) {
                administrativoLogadoUI.updateTableViewAreasActividade();

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                        MainApp.TITULO_APLICACAO,
                        "Registar Área de Actividade.",
                        "Área de Actividade registada com sucesso.").show();

            }

            closeAddAreaActividade(event);

        } catch (IllegalArgumentException | SQLException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Registar Área de Actividade - Erro nos dados.",
                    "Não foi possível registar a Área de Actividade. " + exception.getMessage()
            ).show();

        }

    }

    /**
     * Volta para a scene anterior
     * @param event 
     */
    private void closeAddAreaActividade(ActionEvent event) {
        this.txtCodigo.clear();
        this.txtDescricaoBreve.clear();
        this.areaDescricaoDetalhada.clear();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

}
