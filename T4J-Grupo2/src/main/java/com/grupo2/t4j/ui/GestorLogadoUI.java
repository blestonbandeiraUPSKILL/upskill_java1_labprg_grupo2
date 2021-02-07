package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.controller.RegistarColaboradorController;
import com.grupo2.t4j.files.FicheiroRepositorioColaborador;
import com.grupo2.t4j.repository.RepositorioColaborador;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class GestorLogadoUI implements Initializable {
    
    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    private Scene sceneStartingPage;
    private RegistarColaboradorController registarColaboradorController;
    
    private FicheiroRepositorioColaborador ficheiroC;
    private RepositorioColaborador repositorioColaborador;
    
    @FXML private Button btnCancelar1;

    @FXML private TextArea txtDescricaoTecnica1;

    @FXML private ComboBox<?> cmbAreaActividade1;

    @FXML private Button btnCancelarRegCol;

    @FXML private Button btnRegistarColaborador;

    @FXML private Button btnRegistarTarefa1;

    @FXML private TextField txtDesignacao1;

    @FXML private ComboBox<?> cmbCategoria;

    @FXML private TextField txtFuncaoColaborador;

    @FXML private ComboBox<?> cmbCategoriaTarefa1;

    @FXML private ListView<?> listViewCompTec1;

    @FXML private TextField txtReferencia1;

    @FXML private ComboBox<?> cmbArAct;

    @FXML private TextField txtEmailColaborador;

    @FXML private TextField txtDuracao1;

    @FXML private TextField txtCusto1;

    @FXML private TextArea txtDescricaoInformal1;

    @FXML private TextField txtTelefoneColaborador;

    @FXML private ListView<?> listViewTarefas;

    @FXML private TextField txtNomeColaborador;

    
    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        registarColaboradorController = new RegistarColaboradorController();
    }
    
    @FXML
    public void registarColaboradorAction(ActionEvent event) {

    }

    @FXML
    public void cancelarRegColAction(ActionEvent event) {
        Window window = btnCancelarRegCol.getScene().getWindow();
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

    @FXML
    void selectArAct(ActionEvent event) {

    }

    @FXML
    void selectCategoriaAction(ActionEvent event) {

    }

    @FXML
    void registarTarefaAction(ActionEvent event) {

    }

    @FXML
    void CancelarAction(ActionEvent event) {

    }

    @FXML
    void selectAreaActAction(ActionEvent event) {

    }

    @FXML
    void selectCatTarAction(ActionEvent event) {

    }

    public void navigateStartingPage(ActionEvent actionEvent) {
    }
}
