package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.ApplicationController;
import com.grupo2.t4j.controller.RegistarOrganizacaoController;
import com.grupo2.t4j.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterOrgUI implements Initializable {

    UsersAPIAdapter usersAPIAdapter;
    private RegistarOrganizacaoController registarOrganizacaoController;
    private StartingPageUI startingPageUI;
    private ApplicationController applicationController;
    private Stage adicionarStage;
    private Scene sceneLogin;
    private Scene sceneStart;
    private Scene sceneRegisterGestor;

    @FXML
    TextField txtNomeOrganizacao;
    @FXML
    TextField txtNIFOrganizacao;
    @FXML
    TextField txtTelefoneOrganizacao;
    @FXML
    TextField txtWebsiteOrganizacao;
    @FXML
    TextField txtEmailOrganizacao;
    @FXML
    TextField txtEndArruamentoOrganizacao;
    @FXML
    TextField txtEndPortaOrganizacao;
    @FXML
    TextField txtEndLocalidadeOrganizacao;
    @FXML
    TextField txtEndCodPostalOrganizacao;
    @FXML
    Button btnRegistarOrganizacaoCancel;

    public void registarOrganizacaoCancel(ActionEvent actionEvent) {
        Window window = btnRegistarOrganizacaoCancel.getScene().getWindow();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);
        adicionarStage.setResizable(false);

        applicationController = new ApplicationController();

    }

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    public void irPaginaGestorComDados(ActionEvent actionEvent) {
        Organizacao novaOrganizacao = new Organizacao
                (txtNomeOrganizacao.getText(), txtNIFOrganizacao.getText(),
                        txtTelefoneOrganizacao.getText(), txtWebsiteOrganizacao.getText(),
                        txtEmailOrganizacao.getText(), new EnderecoPostal(txtEndArruamentoOrganizacao.getText(),
                        txtEndPortaOrganizacao.getText(), txtEndLocalidadeOrganizacao.getText(),
                        txtEndCodPostalOrganizacao.getText()));

        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loaderRegisterGestor = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/RegisterGestorOrgScene.fxml"));
            Parent rootRegisterGestor = loaderRegisterGestor.load();
            adicionarStage.setUserData(novaOrganizacao);
            sceneRegisterGestor = new Scene(rootRegisterGestor);

            adicionarStage.setScene(sceneRegisterGestor);
            adicionarStage.setTitle("Registar Organização - Dados Gestor");
            adicionarStage.show();
        }
        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
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
