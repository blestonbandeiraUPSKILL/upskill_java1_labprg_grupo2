package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarCategoriaController;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CompetenciaTecnica;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdicionarCategoriaTarefaUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private Scene sceneCaracterizarCompetenciaTecnica;
    private Stage adicionarStage;
    private Scene sceneStartingPage;
    private RegistarCategoriaController registarCategoriaController;



    @FXML TextField txtDescricaoBreve;
    @FXML TextArea txtDescricaoDetalhada;
    @FXML Button btnConfirmar;
    @FXML Button btnCancelar;
    @FXML Button btnAddCompTecCat;
    @FXML ComboBox<AreaActividade> cmbAreaActividade;
    @FXML ListView<CompetenciaTecnica> listViewCompTecCat;



    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
    }


    public void cancelarAction(ActionEvent event) {
        Window window = btnCancelar.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que quer voltar à página anterior, cancelando o actual registo?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                }
            }
        });
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

  /*  @FXML
    void registarCategoriaAction(ActionEvent event) {
        try {
            registarCategoriaController = new RegistarCategoriaController();

            boolean adicionou = registarCategoriaController.registarCategoria(

            );
        }


    }*/

    @FXML
    public void addCompTec2Cat(ActionEvent event) {
        try {
            FXMLLoader loaderAddCompTec2Cat = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/CaracterizarCompetenciaTecnicaScene.fxml"));
            Parent rootAddCompTec2Cat = loaderAddCompTec2Cat.load();
            sceneCaracterizarCompetenciaTecnica= new Scene(rootAddCompTec2Cat);
            sceneCaracterizarCompetenciaTecnica.getStylesheets().add("/com/grupo2/t4j/style/app.css");

        }
        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }

        adicionarStage.setScene(sceneCaracterizarCompetenciaTecnica);
        adicionarStage.setTitle("Adicionar Competência Técnica à Categoria");
        adicionarStage.show();
    }

    
}
