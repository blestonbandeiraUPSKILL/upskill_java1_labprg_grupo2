package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarTarefaController;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.model.Tarefa;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class ColaboradorLogadoUI implements Initializable {
    
    private RegistarTarefaController registarTarefaController;
    
    @FXML
    private ComboBox<AreaActividade> cmbAreaActividade;
    @FXML
    private ComboBox<Categoria> cmbCategoriaTarefa;
    @FXML
    private ListView<CompetenciaTecnica> listViewCompTec;
    @FXML
    private Button btnRegistarTarefa;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtReferencia;
    @FXML
    private TextField txtDesignacao;
    @FXML
    private TextArea txtDescricaoInformal;
    @FXML
    private TextArea txtDescricaoTecnica;
    @FXML
    private TextField txtDuracao;
    @FXML
    private TextField txtCusto;
    @FXML
    private ComboBox<AreaActividade> cmbArAct;
    @FXML
    private ComboBox<Categoria> cmbCategoria;
    @FXML
    private ListView<Tarefa> listViewTarefas;


    private StartingPageUI startingPageUI;

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        cmbAreaActividade.getItems().setAll(registarTarefaController.getListaAreasActividade());

    }
    
    @FXML
    private void selectAreaActAction(ActionEvent event) {
        AreaActividade areaActividade = cmbAreaActividade.getSelectionModel().getSelectedItem();
        cmbCategoriaTarefa.getItems().setAll(registarTarefaController.getListaCategoriaPorAreaActividade(areaActividade));
    }

    @FXML
    private void selectCatTarAction(ActionEvent event) {
        Categoria categoriaTarefa = cmbCategoriaTarefa.getSelectionModel().getSelectedItem();
        listViewCompTec.getItems().setAll(registarTarefaController.getCompetenciasTecnicasByCategoria(categoriaTarefa));
    }

    @FXML
    private void registarTarefaAction(ActionEvent event) {
        try {
        registarTarefaController = new RegistarTarefaController();
        boolean adicionou = registarTarefaController.registarTarefa(
                cmbAreaActividade.getSelectionModel().getSelectedItem(),
                cmbCategoriaTarefa.getSelectionModel().getSelectedItem(),
                txtReferencia.getText(), txtDesignacao.getText(), 
                txtDescricaoInformal.getText(), txtDescricaoTecnica.getText(),
                txtDuracao.getText(), txtCusto.getText());
        AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registar tarefa.",
                    adicionou ? "Tarefa registada com sucesso."
                            : "Não foi possível registar a tarefa.").show();
        } catch (IllegalArgumentException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    iae.getMessage()).show();
        }
    }

    @FXML
    private void CancelarAction(ActionEvent event) {
        Window window = btnCancelar.getScene().getWindow();
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
    private void selectArAct(ActionEvent event) {
        AreaActividade arAct = cmbArAct.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void selectCategoriaAction(ActionEvent event) {
        Categoria categoria = cmbCategoria.getSelectionModel().getSelectedItem();
    }
}
