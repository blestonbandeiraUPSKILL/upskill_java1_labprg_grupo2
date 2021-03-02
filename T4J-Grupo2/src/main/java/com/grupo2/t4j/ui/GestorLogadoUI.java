package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.files.FicheiroRepositorioColaborador;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.Colaborador;
import com.grupo2.t4j.model.Tarefa;
import com.grupo2.t4j.persistence.inmemory.RepositorioColaboradorInMemory;
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
    private RegistarColaboradorController registarColaboradorController;
    private RegistarCategoriaController registarCategoriaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarTarefaController registarTarefaController;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    
    private FicheiroRepositorioColaborador ficheiroC;
    private RepositorioColaboradorInMemory repositorioColaboradorInMemory;

    
    @FXML ListView<Tarefa> listViewTarefas;
    @FXML ComboBox<AreaActividade> cmbAreaActividade;
    @FXML ComboBox<Categoria> cmbCategoriaTarefa;
    @FXML Button btnLogout;
    
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

        registarAreaActividadeController = new RegistarAreaActividadeController();
        try {
            registarTarefaController = new RegistarTarefaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        registarCategoriaController = new RegistarCategoriaController();
        registarColaboradorController = new RegistarColaboradorController();
        gestaoUtilizadoresController = new GestaoUtilizadoresController();

        try {
            updateListViewColaboradores();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            updateTableViewTarefas();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            cmbAreaActividade.getItems().setAll(
                    registarAreaActividadeController.getAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        cmbAreaActividade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateCmbCategoriaTarefa(event);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

            }
        });

        cmbCategoriaTarefa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateListViewTarefasComFiltro();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

            }
        });

    }

    public void updateListViewTarefasComFiltro() throws SQLException {
        String codigoCategoria = cmbCategoriaTarefa.getSelectionModel().getSelectedItem().getCodigoCategoria();
        listViewTarefas.getItems().setAll(
                registarTarefaController.findByCategoria(codigoCategoria)
        );
    }

    public void updateTableViewTarefas() throws SQLException {
        tabelaTarefas.getItems().setAll(registarTarefaController.getAllOrganizacao(
                getNifOrganizacao()));
        
        colunaDesignacao.setCellValueFactory( new PropertyValueFactory<>("designacao"));
        colunaReferencia.setCellValueFactory( new PropertyValueFactory<>("referencia"));
        colunaDuracao.setCellValueFactory( new PropertyValueFactory<>("duracaoEst"));
        colunaCusto.setCellValueFactory( new PropertyValueFactory<>("custoEst"));
    }

    private void updateCmbCategoriaTarefa(ActionEvent event) throws SQLException {
        String codigoAreaActividade = cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo();
        cmbCategoriaTarefa.getItems().setAll(
                registarCategoriaController.findByCodigo(codigoAreaActividade));
    }

    public void updateListViewColaboradores() throws SQLException {
        listViewColaboradores.getItems().setAll(registarColaboradorController.getAll());
        
        colunaNome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        colunaFuncao.setCellValueFactory( new PropertyValueFactory<>("funcao"));
        colunaTelefone.setCellValueFactory( new PropertyValueFactory<>("telefone"));
        colunaEmail.setCellValueFactory( new PropertyValueFactory<>("email"));
        



        
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
}
