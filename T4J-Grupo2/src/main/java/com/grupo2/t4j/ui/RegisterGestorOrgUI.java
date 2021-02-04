package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.ApplicationController;
import com.grupo2.t4j.model.Colaborador;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Rolename;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterGestorOrgUI implements Initializable {

    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    private Scene sceneRegisterOrg;
    private ApplicationController applicationController;

    @FXML
    Button btnCancelarRegistoGestor;
    @FXML
    TextField txtNomeGestor;
    @FXML
    TextField txtTelefoneGestor;
    @FXML
    TextField txtEmailGestor;

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);
        adicionarStage.setResizable(false);

        applicationController = new ApplicationController();
    }

    public void confirmarRegistoOrganizacao(ActionEvent actionEvent) {
    }

    public void registarGestorCancel(ActionEvent actionEvent) {
        Window window = btnCancelarRegistoGestor.getScene().getWindow();
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

    public void IrPaginaOrganizacaoComDados(ActionEvent actionEvent) {
        Colaborador novoColaborador = new Colaborador(
            txtNomeGestor.getText(), new Email(txtEmailGestor.getText()), txtTelefoneGestor.getText(), Rolename.GESTOR
        );

        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loaderRegisterOrg = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/RegisterrOrgScene.fxml"));
            Parent rootRegisterOrg = loaderRegisterOrg.load();
            adicionarStage.setUserData(novoColaborador);
            sceneRegisterOrg = new Scene(rootRegisterOrg);

            adicionarStage.setScene(sceneRegisterOrg);
            adicionarStage.setTitle("Register Organização - Dados Organização");;
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
}
