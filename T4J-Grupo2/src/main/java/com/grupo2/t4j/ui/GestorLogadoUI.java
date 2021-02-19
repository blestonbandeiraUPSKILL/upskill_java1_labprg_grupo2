package com.grupo2.t4j.ui;

import com.grupo2.t4j.api.UsersAPI;
import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.files.FicheiroRepositorioColaborador;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.persistence.inmemory.RepositorioColaboradorInMemory;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private RegistarCategoriaController registarCategoriaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarTarefaController registarTarefaController;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    
    private FicheiroRepositorioColaborador ficheiroC;
    private RepositorioColaboradorInMemory repositorioColaboradorInMemory;
    
    @FXML TextField txtReferencia1;
    @FXML TextField txtEmailColaborador;
    @FXML TextField txtCustoEstTarefa;
    @FXML TextField txtNomeColaborador;
    @FXML TextField txtDuracao1;
    @FXML TextField txtCusto1;
    @FXML TextField txtTelefoneColaborador;
    @FXML TextField txtRefTarefa;
    @FXML TextField txtDuracaoTarefa;
    @FXML TextField txtDesignTarefa;

    @FXML TextArea txtDescricaoInformal1;
    @FXML TextArea txtDescInformalTarefa;
    @FXML TextArea txtDescTecnicaTarefa;

    @FXML ListView<Tarefa> listViewTarefas;
    @FXML ListView<?> listViewCompTecReq;


    @FXML private TextField txtFuncaoColaborador;
    @FXML private TextField txtPasswordColaborador;
    @FXML Button btnRegistarColaborador;
    @FXML Button btnCancelarRegCol;
    @FXML Button btnLogout;
       
    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        registarColaboradorController = new RegistarColaboradorController();

        /*registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCategoriaController = new RegistarCategoriaController();
        registarTarefaController = new RegistarTarefaController();
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();*/

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        /*mbAreaActividadeListaTarefas.getItems().setAll(registarAreaActividadeController.getAreasActividade());
        cmbCategoriaTarefaListaTarefas.getItems().setAll(
                registarCategoriaController.getCategoriasByAreaActividade(
                        cmbAreaActividadeListaTarefas.getSelectionModel().getSelectedItem()));

        cmbAreaActividadeEspecificarTarefa.getItems().setAll(registarAreaActividadeController.getAreasActividade());
        cmbCategoriaTarefaEspecificarTarefa.getItems().setAll(
                registarCategoriaController.getCategoriasByAreaActividade(
                        cmbAreaActividadeEspecificarTarefa.getSelectionModel().getSelectedItem()));

        ListView listViewTarefas = new ListView();
        ListView listViewCompetenciasTecnicas = new ListView();
        listViewTarefas.getItems().addAll(registarTarefaController.getListTarefas());
        listViewCompetenciasTecnicas.getItems().addAll(registarCompetenciaTecnicaController.getCompetenciasTecnicasByAreaActividade(
                cmbAreaActividadeEspecificarTarefa.getSelectionModel().getSelectedItem()));*/
    }
    
    @FXML
    public void registarColaboradorAction(ActionEvent event){
        
        try {
            boolean adicionou = registarColaboradorController.registarColaborador(
                    txtEmailColaborador.getText(),
                    txtNomeColaborador.getText(),
                    txtFuncaoColaborador.getText(),
                    txtTelefoneColaborador.getText());

            if (adicionou) {

                txtPasswordColaborador.setText(registarColaboradorController.findByEmail(txtEmailColaborador.getText()).getPassword().getPasswordText());

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                        MainApp.TITULO_APLICACAO,
                        "Registar Colaborador.",
                        adicionou ? ("Colaborador registado com sucesso.")
                                : "Não foi possível registar o Colaborador.").show();
            }

        }
        catch (IllegalArgumentException | SQLException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    iae.getMessage()).show();
        }        
    }
    
    @FXML
    public void cancelarRegColAction(ActionEvent event) {
        this.txtNomeColaborador.clear();
        this.txtEmailColaborador.clear();
        this.txtFuncaoColaborador.clear();
        this.txtTelefoneColaborador.clear();
        this.txtPasswordColaborador.clear();
    }

    @FXML
    void navigateStartingPage(ActionEvent event) {
        try {
            FXMLLoader loaderStartingPage = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/StartingPageScene.fxml"));
            Parent rootStartingPage = loaderStartingPage.load();
            sceneStartingPage = new Scene(rootStartingPage);

            Window window = btnLogout.getScene().getWindow();
            window.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                            MainApp.TITULO_APLICACAO,
                            "Confirmação da acção",
                            "Tem a certeza que pretende terminar a sessão??");

                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        windowEvent.consume();
                    }
                }
            });
            window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));

            adicionarStage.setScene(sceneStartingPage);
            adicionarStage.setTitle(MainApp.TITULO_APLICACAO);
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

    public void logout(ActionEvent actionEvent) throws SQLException {
        UsersAPI usersAPI = new UsersAPI();
        boolean logout = usersAPI.logout();
        if (logout) {
            navigateStartingPage(actionEvent);

            usersAPI.resetUserAPI();
        }
        else {
            Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    "Não foi possível terminar a sessão.");
        }
    }

}
