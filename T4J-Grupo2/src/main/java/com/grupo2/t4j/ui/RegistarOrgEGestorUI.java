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

                String nomeOrganizacao = txtNomeOrganizacao.getText();
                String nif = txtNif.getText();
                String arruamento = txtEndArruamento.getText();
                String numeroPorta = txtEndPorta.getText();
                String localidade = txtEndLocalidade.getText();
                String codPostal = txtEndCodPostal.getText();
                String telefoneOrg = txtTelefoneOrganizacao.getText();
                String website = txtWebsite.getText();
                String emailOrg = txtEmailOrganizacao.getText();
                String nomeGestor = txtNomeGestor.getText();
                String emailGestor = txtEmailGestor.getText();
                String telefoneGestor = txtTelefoneGestor.getText();
                Rolename role = Rolename.GESTOR;

        Organizacao organizacao = new Organizacao(nomeOrganizacao,
                nif, arruamento, numeroPorta,
                localidade, codPostal, telefoneOrg,
                website, emailOrg, nomeGestor,
                emailGestor, telefoneGestor, role);

        RegistarOrganizacaoController.setOrganizacao(organizacao);

        Parent rootConfirmarRegisto = loaderConfirmarRegisto.load();
        loaderConfirmarRegisto.setController(registarOrganizacaoController);
        sceneConfirmarRegisto = new Scene(rootConfirmarRegisto);
        /*ConfirmarRegistoOrgUI confirmarRegistoOrgUI = loaderConfirmarRegisto.getController();
        confirmarRegistoOrgUI.associarParentUI(this);
*/
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
