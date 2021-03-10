package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.domain.Colaborador;
import com.grupo2.t4j.domain.FiltroTarefas;
import com.grupo2.t4j.domain.Tarefa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 */
public class GestorLogadoUI implements Initializable {

    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    private Scene sceneStartingPage;
    private Scene sceneAddColaborador;
    private Scene sceneAddTarefa;
    private Scene scenePublicarTarefa;
    private RegistarColaboradorController registarColaboradorController;
    private RegistarTarefaController registarTarefaController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    
    @FXML ComboBox<FiltroTarefas> cmbFiltroTarefas;
    @FXML Button btnLogout;
    @FXML Button btnPublicarTarefa;
    
    //TableView Colaboradores
    @FXML TableView<Colaborador> listViewColaboradores;
    @FXML TableColumn<Object, Object> colunaEmail;
    @FXML TableColumn<Object, Object> colunaNome;
    @FXML TableColumn<Object, Object> colunaTelefone;
    @FXML TableColumn<Object, Object> colunaFuncao;
    
    //TableView Tarefas
    @FXML TableColumn<Object, Object> colunaReferencia;
    @FXML TableColumn<Object, Object> colunaDesignacao;
    @FXML TableColumn<Object, Object> colunaDuracao;
    @FXML TableColumn<Object, Object> colunaCusto;

    @FXML TableView<Tarefa> tabelaTarefas;
     
    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }
    
    /**
    * Initializes the controller (UI) class.
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        try {
            registarTarefaController = new RegistarTarefaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        registarColaboradorController = new RegistarColaboradorController();
        gestaoUtilizadoresController = new GestaoUtilizadoresController();

        cmbFiltroTarefas.getItems().setAll(FiltroTarefas.values());
        
        cmbFiltroTarefas.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               try {
                   aplicarFiltroTarefas(event);
               } catch (SQLException exception) {
                   exception.printStackTrace();
               }
           }
           });
        
        try {
            tabelaTarefas.getItems().setAll(registarTarefaController.getAllOrganizacao(getNifOrganizacao()));
            preencherTabela();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        
        try {
            updateListViewColaboradores();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
    
    public void aplicarFiltroTarefas(ActionEvent actionEvent)throws SQLException {
        
        switch (cmbFiltroTarefas.getSelectionModel().getSelectedItem()) {
            case TAREFAS_DA_ORGANIZACAO:
                updateTableViewTarefas();
                btnPublicarTarefa.setDisable(true);
                break;
            case AS_MINHAS_TAREFAS:
                updateTableViewTarefasColaborador();
                btnPublicarTarefa.setDisable(true);
                break;
            case TAREFAS_PUBLICADAS:
                updateTableViewTarefasPublicadas();
                btnPublicarTarefa.setDisable(true);
                break;
            case TAREFAS_PARA_PUBLICAR:
                updateTableViewTarefasNaoPublicadas();
                btnPublicarTarefa.setDisable(false);
        }
    }

    public void updateTableViewTarefas() throws SQLException {
        tabelaTarefas.getItems().setAll(registarTarefaController.getAllOrganizacao(
                getNifOrganizacao()));
        
        colunaDesignacao.setCellValueFactory( new PropertyValueFactory<>("designacao"));
        colunaReferencia.setCellValueFactory( new PropertyValueFactory<>("referencia"));
        colunaDuracao.setCellValueFactory( new PropertyValueFactory<>("duracaoEst"));
        colunaCusto.setCellValueFactory( new PropertyValueFactory<>("custoEst"));
    }

    public void updateListViewColaboradores() throws SQLException {
        listViewColaboradores.getItems().setAll(registarColaboradorController.getAll());
        
        colunaNome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        colunaFuncao.setCellValueFactory( new PropertyValueFactory<>("funcao"));
        colunaTelefone.setCellValueFactory( new PropertyValueFactory<>("telefone"));
        colunaEmail.setCellValueFactory( new PropertyValueFactory<>("email"));
    }
  
    public void updateTableViewTarefasColaborador() throws SQLException {
        cmbFiltroTarefas.getSelectionModel().clearSelection();
        String email = gestaoUtilizadoresController.getEmail();
        String nifOrganizacao = registarColaboradorController.getNifOrganizacao(email);
        tabelaTarefas.getItems().setAll(registarTarefaController.findByColaboradorENif(email, nifOrganizacao));
        preencherTabela();
    }
    
    public void updateTableViewTarefasPublicadas() throws SQLException {
        cmbFiltroTarefas.getSelectionModel().clearSelection();
        String email = gestaoUtilizadoresController.getEmail();
        String nifOrganizacao = registarColaboradorController.getNifOrganizacao(email);
        List<String> referenciasTarefa = registarTarefaController.findRefenciaTarefa(nifOrganizacao);
        tabelaTarefas.getItems().setAll(registarTarefaController.findTarefasPublicadas(referenciasTarefa, nifOrganizacao, email));
        preencherTabela();
    }
    
    public void updateTableViewTarefasNaoPublicadas() throws SQLException {
        cmbFiltroTarefas.getSelectionModel().clearSelection();
        String email = gestaoUtilizadoresController.getEmail();
        String nifOrganizacao = registarColaboradorController.getNifOrganizacao(email);
        List<String> referenciasTarefa = registarTarefaController.findRefenciaTarefa(nifOrganizacao);
        tabelaTarefas.getItems().setAll(registarTarefaController.findTarefasNaoPublicadas(referenciasTarefa, email, nifOrganizacao));
        preencherTabela();
    }
    
    public void preencherTabela () {
        colunaDesignacao.setCellValueFactory( new PropertyValueFactory<>("designacao"));
        colunaReferencia.setCellValueFactory( new PropertyValueFactory<>("referencia"));
        colunaDuracao.setCellValueFactory( new PropertyValueFactory<>("duracaoEst"));
        colunaCusto.setCellValueFactory( new PropertyValueFactory<>("custoEst"));
    }

    public void logout(ActionEvent actionEvent) {
        Window window = btnLogout.getScene().getWindow();
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

    public String getNifOrganizacao() throws SQLException {
        return registarColaboradorController.getNifOrganizacao(
                gestaoUtilizadoresController.getEmail());
    }

    public void navigateAddColaborador(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddColaborador = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/RegistarColaboradorScene.fxml"));
            Parent rootAddColaborador = loaderAddColaborador.load();
            sceneAddColaborador = new Scene(rootAddColaborador);
            RegistarColaboradorUI registarColaboradorUI = loaderAddColaborador.getController();
            registarColaboradorUI.associarParentUI(this);
            registarColaboradorUI.transferNif();

            adicionarStage.setScene(sceneAddColaborador);
            adicionarStage.setTitle("Registar Colaborador");
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

    public void navigateAddTarefa(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/EspecificarTarefaGestorScene.fxml"));
            Parent rootAddTarefa = loaderAddTarefa.load();
            sceneAddTarefa = new Scene(rootAddTarefa);
            EspecificarTarefaGestorUI especificarTarefaUI = loaderAddTarefa.getController();
            especificarTarefaUI.associarParentUI(this);

            adicionarStage.setScene(sceneAddTarefa);
            adicionarStage.setTitle("Especificar Tarefa");
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
    
    public void navigatePublicarTarefa(ActionEvent event) {
        try {
            FXMLLoader loaderPublicarTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/PublicarTarefaGestorScene.fxml"));
            Parent rootPublicarTarefa = loaderPublicarTarefa.load();
            scenePublicarTarefa = new Scene(rootPublicarTarefa);
            PublicarTarefaGestorUI PublicarTarefaGestorUI = loaderPublicarTarefa.getController();
            PublicarTarefaGestorUI.associarParentUI(this);

            adicionarStage.setScene(scenePublicarTarefa);
            adicionarStage.setTitle("Publicar Tarefa");
            adicionarStage.show();

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }

    }
    
    
    
}
