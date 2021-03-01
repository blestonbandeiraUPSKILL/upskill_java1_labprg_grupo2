package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.EfectuarCandidaturaController;
import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarAnuncioController;
import com.grupo2.t4j.controller.RegistarTarefaController;
import com.grupo2.t4j.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class FreelancerLogadoUI implements Initializable {

    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    private Scene sceneStartingPage;

    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private RegistarAnuncioController registarAnuncioController;
    private EfectuarCandidaturaController efectuarCandidaturaController;
    private RegistarTarefaController registarTarefaController;
         
    @FXML ListView<Tarefa> listViewAnuncios;
    @FXML ListView<Candidatura> listViewCandidaturas;
    @FXML ComboBox<Anuncio> cmbAnuncio;
    @FXML TextField txtValor;
    @FXML TextField txtDias;
    @FXML TextField txtApresentacao;
    @FXML TextField txtMotivacao;
    @FXML Button btnAddCandidatura;
    @FXML Button btnSair;
    
    @FXML TableColumn<Object, Object> colunaReferencia;
    @FXML TableColumn<Object, Object> colunaDesignacao;
    @FXML TableColumn<Object, Object> colunaDuracao;
    @FXML TableColumn<Object, Object> colunaCusto;
    @FXML TableView<Tarefa> tabelaAnuncios;


    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    /**
    * Initializes the controller (UI) class.
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            efectuarCandidaturaController = new EfectuarCandidaturaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        try {
            registarTarefaController = new RegistarTarefaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
        
        try {
            updateTableViewAnuncio();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            updateListViewCandidaturas();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    public String getEmail() {
        return gestaoUtilizadoresController.getEmail();
    }

    @FXML
    void verAnuncioAction(ActionEvent event) {

    }
    
    private ObservableList<Tarefa> listaAnuncios() throws SQLException {
        return FXCollections.observableArrayList(registarTarefaController.getAllTarefasPublicadas(/*gestaoUtilizadoresController.getEmail()*/));
    }
    
    public void updateTableViewAnuncio() throws SQLException {
        
        tabelaAnuncios.setItems(listaAnuncios());

        colunaDesignacao.setCellValueFactory( new PropertyValueFactory<>("designacao"));
        colunaReferencia.setCellValueFactory( new PropertyValueFactory<>("referencia"));
        colunaDuracao.setCellValueFactory( new PropertyValueFactory<>("duracaoEst"));
        colunaCusto.setCellValueFactory( new PropertyValueFactory<>("custoEst"));
        
    }
        
    public void updateListViewAnuncio() throws SQLException {

        String emailFreelancer = gestaoUtilizadoresController.getEmail();
        listViewAnuncios.getItems().setAll(registarTarefaController.getAllTarefasPublicadas());
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
                        assert rootStartingPage != null;
                        sceneStartingPage = new Scene(rootStartingPage);
                        
                        sceneStartingPage.getStylesheets().add(startingPageUI.estilo);
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

    public void navigateEfectuarCandidatura(ActionEvent actionEvent) {

        try {
            FXMLLoader loaderEfectuarCandidatura = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/EfectuarCandidaturaScene.fxml"));
            Parent rootEfectuarCandidatura = loaderEfectuarCandidatura.load();
            EfectuarCandidaturaUI efectuarCandidaturaUI = loaderEfectuarCandidatura.getController();
            efectuarCandidaturaUI.associarParentUI(this);
            efectuarCandidaturaUI.transferData();
            Scene sceneEfectuarCandidatura = new Scene(rootEfectuarCandidatura);

            adicionarStage.setScene(sceneEfectuarCandidatura);
            adicionarStage.setTitle("Efectuar Candidatura");
            adicionarStage.show();
        }

        catch (IOException | SQLException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

    public void updateListViewCandidaturas() throws SQLException {
        String emailFreelancer = gestaoUtilizadoresController.getEmail();
        listViewCandidaturas.getItems().setAll(efectuarCandidaturaController.findByEmail(emailFreelancer));
    }
}
