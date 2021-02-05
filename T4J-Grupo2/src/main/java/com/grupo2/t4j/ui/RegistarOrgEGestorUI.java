package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.ApplicationController;
import com.grupo2.t4j.controller.RegistarOrganizacaoController;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.repository.RepositorioOrganizacao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import java.util.ResourceBundle;

public class RegistarOrgEGestorUI implements Initializable {

    UsersAPIAdapter usersAPIAdapter;
    private RepositorioOrganizacao repositorioOrganizacao;
    private RegistarOrganizacaoController registarOrganizacaoController;
    private ApplicationController applicationController;
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
    @FXML TextField txtTelefoneGestor;
    @FXML TextField txtEmailGestor;
    @FXML Button btnAvancarRegisto;
    @FXML Button btnCancelarRegisto;

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancelarRegisto(ActionEvent actionEvent) {
        Window window = btnCancelarRegisto.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que quer voltar à página inicial, cancelando o actual registo?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                }
            }
        });

        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void avancarRegistoComDados(ActionEvent actionEvent) throws Exception {
        registarOrganizacaoController = new RegistarOrganizacaoController();
        adicionarStage = new Stage();

        FXMLLoader loaderConfirmarRegisto = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConfirmarRegistoOrgScene.fxml"));
        Parent rootConfirmarRegisto = loaderConfirmarRegisto.load();

        loaderConfirmarRegisto.setController(registarOrganizacaoController);

        Organizacao organizacao = new Organizacao(
                );

        registarOrganizacaoController.setOrganizacao(new Organizacao
                (txtNomeOrganizacao.getText(),
                        txtNif.getText(),
                        txtEndArruamento.getText(),
                        txtEndPorta.getText(),
                        txtEndLocalidade.getText(),
                        txtEndCodPostal.getText(),
                        txtTelefoneOrganizacao.getText(),
                        txtWebsite.getText(),
                        txtEmailOrganizacao.getText(),
                        txtNomeGestor.getText(),
                        txtEmailGestor.getText(),
                        txtTelefoneGestor.getText(),
                        Rolename.GESTOR));

        sceneConfirmarRegisto = new Scene(rootConfirmarRegisto);

        adicionarStage.setScene(sceneConfirmarRegisto);
        adicionarStage.setTitle("Confirmar Dados");
        adicionarStage.show();

    }

    /* public void confirmarDados() throws IOException {
        try {
            registarOrganizacaoController.registaOrganizacao();
        }
        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados da organização.",
                    exception.getMessage()).show();
        }
    }*/
}
