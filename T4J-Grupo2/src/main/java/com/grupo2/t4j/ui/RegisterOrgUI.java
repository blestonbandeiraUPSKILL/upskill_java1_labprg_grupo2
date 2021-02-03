package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.ApplicationController;
import com.grupo2.t4j.controller.RegistarOrganizacaoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterOrgUI implements Initializable {

    private RegistarOrganizacaoController registarOrganizacaoController;
    private ApplicationController applicationController;
    private Stage adicionarStage;
    private Scene sceneLogin;
    private Scene sceneRegisterOrg;

    public void registarOrganizacaoCancel(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loaderRegister = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/RegisterOrgScene.fxml"));
            Parent rootRegisterOrg = loaderRegister.load();
            sceneRegisterOrg = new Scene(rootRegisterOrg);

            adicionarStage = new Stage();
            adicionarStage.initModality(Modality.APPLICATION_MODAL);
            adicionarStage.setResizable(false);

            applicationController = new ApplicationController();
        }
        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

/*    RegistarOrganizacaoController(RegistarOrganizacaoController registarOrganizacaoController) {
        this.registarOrganizacaoController = registarOrganizacaoController;
        introduzirDados();
        confirmarDados();
    }*/

    /*public void introduzirDados() throws IOException{
        try {
            registarOrganizacaoController.novaOrganizacao();
        }
        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados da organização.",
                    exception.getMessage()).show();
        }
    }*/

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
