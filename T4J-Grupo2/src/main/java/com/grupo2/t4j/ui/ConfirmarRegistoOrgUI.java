package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.ApplicationController;
import com.grupo2.t4j.model.Organizacao;
import com.grupo2.t4j.repository.RepositorioOrganizacao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmarRegistoOrgUI implements Initializable {

    private RepositorioOrganizacao repositorioOrganizacao;
    private ApplicationController applicationController;
    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    private Scene sceneStartingPage;
    private Scene sceneRegistarOrgEGestor;

    @FXML TextField txtConfNomeOrganizacao;
    @FXML TextField txtConfNif;
    @FXML TextField txtConfTelefoneOrganizacao;
    @FXML TextField txtConfWebsite;
    @FXML TextField txtConfEmailOrganizacao;
    @FXML TextField txtConfEndArruamento;
    @FXML TextField txtConfEndPorta;
    @FXML TextField txtConfEndLocalidade;
    @FXML TextField txtConfEndCodPostal;
    @FXML TextField txtConfNomeGestor;
    @FXML TextField txtConfTelefoneGestor;
    @FXML TextField txtConfEmailGestor;
    @FXML Button btnCancelarRegisto;

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);
        adicionarStage.setResizable(false);
        Window window = btnCancelarRegisto.getScene().getWindow();
        Organizacao organizacao = (Organizacao) window.getUserData();

        txtConfNomeOrganizacao.setText(organizacao.getNome());
        txtConfNif.setText(organizacao.getNif());
        txtConfTelefoneOrganizacao.setText(organizacao.getTelefone());
        txtConfWebsite.setText(organizacao.getTelefone());
        txtConfEmailOrganizacao.setText(organizacao.getEmail().toString());
        txtConfEndArruamento.setText(organizacao.getEnderecoPostal().getArruamento());
        txtConfEndPorta.setText(organizacao.getEnderecoPostal().getPorta());
        txtConfEndLocalidade.setText(organizacao.getEnderecoPostal().getLocalidade());
        txtConfEndCodPostal.setText(organizacao.getEnderecoPostal().getCodigoPostal());
        txtConfNomeGestor.setText(organizacao.getNomeGestor());
        txtConfTelefoneGestor.setText(organizacao.getTelefoneGestor());
        txtConfEmailGestor.setText(organizacao.getEmailGestor().toString());


        applicationController = new ApplicationController();
    }

    public void voltarPaginaRegistoComDados(ActionEvent actionEvent) {

    }

    public void gravarOrganizacao(ActionEvent actionEvent) {
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
}
