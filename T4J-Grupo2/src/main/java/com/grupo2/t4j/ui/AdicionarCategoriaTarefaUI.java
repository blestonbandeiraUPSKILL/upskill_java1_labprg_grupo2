package com.grupo2.t4j.ui;

import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CompetenciaTecnica;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class AdicionarCategoriaTarefaUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private Scene sceneCaracterizarCompetenciaTecnica;

    @FXML
    private Button btnConfirmar;

    @FXML
    private TextArea txtDescricaoDetalhada;

    @FXML
    private ListView<CompetenciaTecnica> listViewCompTecCat;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnAddCompTecCat;

    @FXML
    private ComboBox<AreaActividade> cmbAreaActividade;

    @FXML
    private TextField txtDescricaoBreve;

    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCancelar.getScene().getStylesheets().add("/style/app.css");
    }


    void cancelarAction(ActionEvent event) {
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
    @FXML
    void registarCategoriaAction(ActionEvent event) {

    }

    

    @FXML
    void addCompTec2Cat(ActionEvent event) {
        try {
            FXMLLoader loaderAddCompTec2Cat = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/CaracterizarCompetenciaTecnicaScene.fxml"));
            Parent rootAddCompTec2Cat = loaderAddCompTec2Cat.load();
            sceneCaracterizarCompetenciaTecnica= new Scene(rootAddCompTec2Cat);
            sceneCaracterizarCompetenciaTecnica.getStylesheets().add("/com/grupo2/t4j/style/app.css");
            CaracterizarCompetenciaTecnicaUI caracterizarCompetenciaTecnicaUI = loaderAddCompTec2Cat.getController();
            caracterizarCompetenciaTecnicaUI.associarParentUI(this);
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
