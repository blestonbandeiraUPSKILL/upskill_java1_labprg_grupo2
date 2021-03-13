package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarColaboradorController;
import com.grupo2.t4j.controller.RegistarOrganizacaoController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class RegistarOrgEGestorUI implements Initializable {

    private RegistarOrganizacaoController registarOrganizacaoController;
    private RegistarColaboradorController registarColaboradorController;
    private StartingPageUI startingPageUI;

    @FXML TextField txtNomeOrganizacao;
    @FXML TextField txtNif;
    @FXML TextField txtTelefoneOrganizacao;
    @FXML TextField txtWebsite;
    @FXML TextField txtEmailOrganizacao;
    @FXML TextField txtEndArruamento;
    @FXML TextField txtEndPorta;
    @FXML TextField txtEndLocalidade;
    @FXML TextField txtEndCodPostal;
    @FXML TextField txtNomeGestor;
    @FXML TextField txtFuncaoGestor;
    @FXML TextField txtTelefoneGestor;
    @FXML TextField txtEmailGestor;
    @FXML TextField txtPassword;
    @FXML Button btnAvancarRegisto;
    @FXML Button btnCancelarRegisto;

    /**
     * Associa a scene StartingPageUI como parent desta Scene 
     * @param startingPageUI
     */
    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;       
    }

    /**
    * Initializes the controller (UI) class.
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            registarOrganizacaoController = new RegistarOrganizacaoController();                            
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        registarColaboradorController = new RegistarColaboradorController();
    }

    /**
     * Cancela o registo de uma organizacao e seu gestor
     * @param actionEvent 
     */
    public void cancelarRegisto(ActionEvent actionEvent) {
        Window window = btnCancelarRegisto.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que quer voltar à página inicial?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                }
            }
        });

        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    /**
     * Regista uma organizacao e o seu gestor
     * @param actionEvent
     * @throws IOException
     * @throws SQLException 
     */
    public void avancarRegistoComDados(ActionEvent actionEvent) throws IOException, SQLException {
        registarOrganizacaoController = new RegistarOrganizacaoController();

        try {
            boolean registou = registarOrganizacaoController.registarOrganizacao(
                    txtNif.getText(),
                    txtNomeOrganizacao.getText(),
                    txtWebsite.getText(),
                    txtTelefoneOrganizacao.getText(),
                    txtEmailOrganizacao.getText(),
                    txtEmailGestor.getText(),
                    txtEndArruamento.getText(),
                    txtEndPorta.getText(),
                    txtEndLocalidade.getText(),
                    txtEndCodPostal.getText(),
                    txtNomeGestor.getText(),
                    txtTelefoneGestor.getText(),
                    txtFuncaoGestor.getText()
            );

            if(registou) {
                txtPassword.setText(
                        registarColaboradorController.findPassword(
                                txtEmailGestor.getText()).getPasswordText());
                btnAvancarRegisto.setDisable(true);
                btnCancelarRegisto.setText("Voltar");

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Registar Organização.",
                    "Organização registada com sucesso.").show();

                btnAvancarRegisto.disabledProperty();

            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Registar Organização - Erro nos dados.",
                    "Não foi possível registar a Organização." + exception.getMessage()).show();
        }
    }

}
