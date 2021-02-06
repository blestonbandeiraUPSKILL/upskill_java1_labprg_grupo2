package com.grupo2.t4j.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdministrativoLogadoUI implements Initializable {

    private Stage adicionarStage;
    private Scene sceneAddAreaActividade;
    private StartingPageUI startingPageUI;
    @FXML Button btnAddAreaAtividade;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adicionarStage = new Stage();
        try {
            FXMLLoader loaderAddAreaActividade = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdicionarAreaAtividadeScene.fxml"));
            Parent rootAddAreaActividade = loaderAddAreaActividade.load();
            sceneAddAreaActividade = new Scene(rootAddAreaActividade);
            AdicionarAreaAtividadeUI adicionarAreaAtividadeUI = loaderAddAreaActividade.getController();
            adicionarAreaAtividadeUI.associarParentUI(this);
        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    public void addAreaActividade(ActionEvent actionEvent) throws IOException {
           adicionarStage.setScene(sceneAddAreaActividade);
           adicionarStage.setTitle("Adicionar Área de Actividade");
           adicionarStage.show();

           btnAddAreaAtividade.getScene().getWindow().hide();

    }
}
