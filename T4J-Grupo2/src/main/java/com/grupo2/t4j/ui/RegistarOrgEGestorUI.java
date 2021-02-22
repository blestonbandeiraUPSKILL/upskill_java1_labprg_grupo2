package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarOrganizacaoController;
import com.grupo2.t4j.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.Node;

public class RegistarOrgEGestorUI implements Initializable {

    private RegistarOrganizacaoController registarOrganizacaoController;
    private StartingPageUI startingPageUI;

    private Stage adicionarStage;

    private Scene sceneConfirmarRegisto;

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

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            RegistarOrganizacaoController registarOrganizacaoController = new RegistarOrganizacaoController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

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

                txtPassword.setText(registarOrganizacaoController.findColaboradorByEmail(
                        txtEmailGestor.getText()).getPassword().getPasswordText());

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Registar Organização.",
                    registou ? ("Organização registada com sucesso.")
                                : "Não foi possível registar a Organização.").show();

                btnAvancarRegisto.disabledProperty();

            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados da organização.",
                    exception.getMessage()).show();
        }
    }

    private void closeAddOrganizacao(ActionEvent event) {
      /*  this.txtCodigo.clear();
        this.txtDescricaoBreve.clear();
        this.areaDescricaoDetalhada.clear();*/
        ((Node) event.getSource()).getScene().getWindow().hide();
    }        


}
