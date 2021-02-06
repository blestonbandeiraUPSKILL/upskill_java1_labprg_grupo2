package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.ApplicationController;
import com.grupo2.t4j.controller.RegistarOrganizacaoController;
import com.grupo2.t4j.model.Organizacao;
import com.grupo2.t4j.repository.RepositorioOrganizacao;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmarRegistoOrgUI implements Initializable {

    private ApplicationController applicationController;
    private RegistarOrganizacaoController registarOrganizacaoController;
    private Stage adicionarStage;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);
        adicionarStage.setResizable(false);

        applicationController = new ApplicationController();
        registarOrganizacaoController = new RegistarOrganizacaoController();
        Organizacao organizacao = RegistarOrganizacaoController.getOrganizacao();
        if (organizacao != null) {
            try {
                txtConfNomeOrganizacao.setText(organizacao.getNome());
                txtConfNif.setText(organizacao.getNif());
                txtConfTelefoneOrganizacao.setText(organizacao.getTelefone().toString());
                txtConfWebsite.setText(organizacao.getWebsite().getWebsiteText());
                txtConfEmailOrganizacao.setText(organizacao.getEmail().getEmailText());
                txtConfEndArruamento.setText(organizacao.getEnderecoPostal().getArruamento());
                txtConfEndPorta.setText(organizacao.getEnderecoPostal().getPorta());
                txtConfEndLocalidade.setText(organizacao.getEnderecoPostal().getLocalidade());
                txtConfEndCodPostal.setText(organizacao.getEnderecoPostal().getCodigoPostal());
                txtConfNomeGestor.setText(organizacao.getNomeGestor());
                txtConfTelefoneGestor.setText(organizacao.getTelefoneGestor());
                txtConfEmailGestor.setText(organizacao.getEmailGestor().getEmailText());

            } catch (Exception exception) {
                exception.printStackTrace();
                AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                        MainApp.TITULO_APLICACAO,
                        "Erro",
                        exception.getMessage());
            }
        }

    }

    public void voltarPaginaRegistoComDados(ActionEvent actionEvent) {

    }

    public void addOrganizacao(ActionEvent actionEvent) throws Exception {

        try {
            if(registarOrganizacaoController.registaOrganizacao()) {
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                        MainApp.TITULO_APLICACAO,
                        "Sucesso",
                        "Organização registada com sucesso!");
            }
            else {
                AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                        MainApp.TITULO_APLICACAO,
                        "Erro",
                        "Algo correu mal. Por favor tente de novo.");
            }

        }
        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados da organização.",
                    exception.getMessage()).show();
        }
    }

    public void cancelarRegisto() {
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
