package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.model.*;
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

/**
 * FXML Controller class
 */
public class ColaboradorLogadoUI implements Initializable {


    private RegistarCategoriaController registarCategoriaController;
    private RegistarColaboradorController registarColaboradorController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarTarefaController registarTarefaController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private StartingPageUI startingPageUI;
    private Scene sceneStartingPage;
    private Stage adicionarStage;
    private Scene sceneAddTarefa;
    private Scene scenePublicarTarefa;

    private static final String CABECALHO_IMPORTAR = "Importar Lista.";

    @FXML Button btnLogout;
    @FXML Button btnImportAreaActividade;
    @FXML ComboBox<AreaActividade> cmbAreaActividade;
    @FXML ComboBox<AreaActividade> cmbAreaActividadeEspecificarTarefa;
    @FXML ComboBox<Categoria> cmbCategoriaTarefa;
    @FXML ListView<Tarefa> listViewTarefas;
    @FXML ListView<Freelancer> listViewFreelancersCandidaturas;
    @FXML ListView<Freelancer> listViewSeriacao;
    @FXML ComboBox<FiltroTarefas> cmbFiltroTarefas;
    @FXML ComboBox<String> cmbAnuncio;
    @FXML Button btnPublicarTarefa;
    @FXML Button btnConsultarAnuncio;
    @FXML Button btnConsultarFreelancer;
    @FXML Button btnConsultarCandidatura;
    @FXML Button btnSeriacao;
    @FXML TextField txtDataSeriacao;

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }
    
    /**
    * Initializes the controller (UI) class.
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCategoriaController = new RegistarCategoriaController();
        registarTarefaController = new RegistarTarefaController();
        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        registarColaboradorController = new RegistarColaboradorController();

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);
        adicionarStage.setResizable(false);
        
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

    /*    try {
            cmbAreaActividade.getItems().setAll(registarAreaActividadeController.getAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }*/

   /*     cmbAreaActividade.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               try {
                   updateCmbCategoriasTarefaLista(event);
               } catch (SQLException exception) {
                   exception.printStackTrace();
               }
           }
        });*/


        try {
            listViewTarefas.getItems().setAll(registarTarefaController.getAllOrganizacao(getNifOrganizacao()));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }




    }

    public void updateListViewTarefas() throws SQLException {
        listViewTarefas.getItems().setAll(registarTarefaController.getAllOrganizacao(
                getNifOrganizacao() ));

    }
    
    public void updateListViewTarefasColaborador() throws SQLException {
        String email = gestaoUtilizadoresController.getEmail();
        String nifOrganizacao = registarColaboradorController.getNifOrganizacao(email);
        listViewTarefas.getItems().setAll(registarTarefaController.findByColaboradorENif(email, nifOrganizacao));
        
    }
    
    public void updateListViewTarefasPublicadas() throws SQLException {
        String email = gestaoUtilizadoresController.getEmail();
        String nifOrganizacao = registarColaboradorController.getNifOrganizacao(email);
        List<String> referenciasTarefa = registarTarefaController.findRefenciaTarefa(nifOrganizacao);
        listViewTarefas.getItems().setAll(registarTarefaController.findTarefasPublicadas(referenciasTarefa, nifOrganizacao));
        
    }
    
    public void updateListViewTarefasNaoPublicadas() throws SQLException {
        String email = gestaoUtilizadoresController.getEmail();
        String nifOrganizacao = registarColaboradorController.getNifOrganizacao(email);
        List<String> referenciasTarefa = registarTarefaController.findRefenciaTarefa(nifOrganizacao);
        listViewTarefas.getItems().setAll(registarTarefaController.findTarefasNaoPublicadas(referenciasTarefa, email, nifOrganizacao));
        
    }
    
 /*   public void updateCmbCategoriasTarefaLista(ActionEvent actionEvent) throws SQLException{
        List<Categoria> listaCategoriasTarefa =
                registarCategoriaController.findByAreaActividade(
                cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo());

        cmbCategoriaTarefa.getItems().setAll(listaCategoriasTarefa);
    }*/


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


    public void navigateEspecificarTarefa(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/EspecificarTarefaColaboradorScene.fxml"));
            Parent rootAddTarefa = loaderAddTarefa.load();
            sceneAddTarefa = new Scene(rootAddTarefa);
            //sceneAddTarefa.getStylesheets().add("/com/grupo2/t4j/style/app.css");
            EspecificarTarefaColaboradorUI especificarTarefaColaboradorUI = loaderAddTarefa.getController();
            especificarTarefaColaboradorUI.associarParentUI(this);

            adicionarStage.setScene(sceneAddTarefa);
            adicionarStage.setTitle("Especificar Tarefa");
            adicionarStage.show();

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }



    }
    
    public void aplicarFiltroTarefas(ActionEvent actionEvent)throws SQLException {
        
        switch (cmbFiltroTarefas.getSelectionModel().getSelectedItem()) {
            case TAREFAS_DA_ORGANIZACAO:
                updateListViewTarefas();
                break;
            case AS_MINHAS_TAREFAS:
                updateListViewTarefasColaborador();
                break;
            case TAREFAS_PUBLICADAS:
                updateListViewTarefasPublicadas();
                break;
            case TAREFAS_PARA_PUBLICAR:
                updateListViewTarefasNaoPublicadas();
                btnPublicarTarefa.setDisable(false);
        }
    }

    public void navigatePublicarTarefa(ActionEvent event) {
        try {
            FXMLLoader loaderPublicarTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/PublicarTarefaScene.fxml"));
            Parent rootPublicarTarefa = loaderPublicarTarefa.load();
            scenePublicarTarefa = new Scene(rootPublicarTarefa);
            scenePublicarTarefa.getStylesheets().add("/com/grupo2/t4j/style/app.css");
            PublicarTarefaUI PublicarTarefaUI = loaderPublicarTarefa.getController();
            PublicarTarefaUI.associarParentUI(this);

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


    public void consultarAnuncioAction(ActionEvent event){
        
    }
    
    public void navigateConsultarFreelancer(ActionEvent event){
        
    }
    
    public void navigateConsultarCandidatura(ActionEvent event){
        
    }
    
    public void navigateSeriacao(ActionEvent event){
        
    }
    
    
    
    
  

}
