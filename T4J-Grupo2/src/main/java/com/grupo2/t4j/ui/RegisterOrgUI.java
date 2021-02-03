package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarOrganizacaoController;
import javafx.scene.control.Alert;

import java.io.IOException;

public class RegisterOrgUI {

    private RegistarOrganizacaoController registarOrganizacaoController;

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
