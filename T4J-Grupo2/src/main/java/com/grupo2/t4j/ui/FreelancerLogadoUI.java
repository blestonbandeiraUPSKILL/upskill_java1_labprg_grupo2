package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.EfectuarCandidaturaController;
import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarAnuncioController;
import com.grupo2.t4j.model.Anuncio;
import com.grupo2.t4j.model.Candidatura;
import com.grupo2.t4j.model.Data;
import com.grupo2.t4j.model.Tarefa;
import com.grupo2.t4j.model.TipoRegimento;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class FreelancerLogadoUI implements Initializable {

    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    private Scene sceneStartingPage;

    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private RegistarAnuncioController registarAnuncioController;
    private EfectuarCandidaturaController efectuarCandidaturaController;
         
    @FXML TableColumn<Anuncio, Data> inicioCandidatura;
    @FXML TableColumn<Anuncio, Data> fimCandidatura;
    @FXML TableColumn<Tarefa, String> tarefa;
    @FXML TableColumn<Anuncio, Data> fimSeriacao;
    @FXML TableView<Anuncio> tableAnuncios;
    @FXML TableColumn<Anuncio, Data> inicioPublicitacao;
    @FXML TableColumn<Anuncio, Data> fimPublicitacao;
    @FXML TableColumn<TipoRegimento, String> tipoRegimento;
    @FXML TableColumn<Anuncio, Data> inicioSeriacao;
    @FXML private ListView<Candidatura> listViewCandidaturas;
    @FXML private ComboBox<Anuncio> cmbAnuncio;
    @FXML private TextField txtValor;
    @FXML private TextField txtDias;
    @FXML private TextField txtApresentacao;
    @FXML private TextField txtMotivacao;
    @FXML private Button btnAddCandidatura;
    @FXML Button btnSair;


    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            efectuarCandidaturaController = new EfectuarCandidaturaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        gestaoUtilizadoresController = new GestaoUtilizadoresController();

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
        
        
        tarefa.setCellValueFactory(new PropertyValueFactory<>("idTarefa"));
        inicioPublicitacao.setCellValueFactory(new PropertyValueFactory<>("dtInicioPublicitacao"));
        fimPublicitacao.setCellValueFactory(new PropertyValueFactory<>("dtFimPublicitacao"));
        inicioCandidatura.setCellValueFactory(new PropertyValueFactory<>("dtInicioCandidatura"));
        fimCandidatura.setCellValueFactory(new PropertyValueFactory<>("dtFimCandidatura"));
        inicioSeriacao.setCellValueFactory(new PropertyValueFactory<>("dtInicioSeriacao"));
        fimSeriacao.setCellValueFactory(new PropertyValueFactory<>("dtFimSeriacao"));

     /*   try {
            updateTableViewAnuncio();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }*/

    }
    @FXML
    void verAnuncioAction(ActionEvent event) {

    }
        
    public void updateTableViewAnuncio() throws SQLException {


        ObservableList<Anuncio> anunciosElegiveis = FXCollections.observableArrayList(
                efectuarCandidaturaController.findAnunciosElegiveis(
                        gestaoUtilizadoresController.getEmail()));

        tableAnuncios.setItems(anunciosElegiveis);
    }
    
    @FXML
    void addCandidatura(ActionEvent event) {

    }

    public void logout(ActionEvent actionEvent) {
        Window window = btnSair.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que pretende terminar a sessão?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                }
                else {
                    boolean logout = gestaoUtilizadoresController.logout();
                    if (logout) {
                        gestaoUtilizadoresController.resetUsersAPI();

                        FXMLLoader loaderStartingPage = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/StartingPageScene.fxml"));
                        Parent rootStartingPage = null;
                        try {
                            rootStartingPage = loaderStartingPage.load();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                        sceneStartingPage = new Scene(rootStartingPage);
                        adicionarStage.setScene(sceneStartingPage);
                        adicionarStage.setTitle(MainApp.TITULO_APLICACAO);
                        adicionarStage.show();
                    } else {
                        AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                                MainApp.TITULO_APLICACAO,
                                "Erro",
                                "Não foi possível terminar a sessão.");
                    }
                }
            }
        });
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));


    }
}
