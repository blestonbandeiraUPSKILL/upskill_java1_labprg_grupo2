package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.domain.*;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 */
public class ColaboradorLogadoUI implements Initializable {


    private RegistarCategoriaController registarCategoriaController;
    private RegistarColaboradorController registarColaboradorController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarTarefaController registarTarefaController;
    private SeriarAnuncioController seriarAnuncioController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private StartingPageUI startingPageUI;
    private Scene sceneStartingPage;
    private Stage adicionarStage;
    private Scene sceneAddTarefa;
    private Scene scenePublicarTarefa;
    private Scene sceneConsultarAnuncio;
    private Scene sceneConsultarCandidatura;

    @FXML Button btnLogout;
    
    @FXML ComboBox<FiltroTarefas> cmbFiltroTarefas;
    @FXML ComboBox<String> cmbAnuncio;
    @FXML Button btnPublicarTarefa;
    @FXML Button btnConsultarAnuncio;
    @FXML Button btnConsultarCandidaturaFreelancer;    
    @FXML Button btnSeriacaoAutomatica;
    @FXML Button btnSeriacaoManual;
    //@FXML TextField txtDataSeriacao;
    
    @FXML TableView<Tarefa> tabelaTarefas;
    @FXML TableColumn<Object, Object> colunaReferencia;
    @FXML TableColumn<Object, Object> colunaDesignacao;
    @FXML TableColumn<Object, Object> colunaDuracao;
    @FXML TableColumn<Object, Object> colunaCusto;

    @FXML TableView<Candidatura> tabelaFreelancers;
    @FXML TableColumn<Object, Object> colunaNome;
    @FXML TableColumn<Object, Object> colunaEmail;
    @FXML TableColumn<Object, Object> colunaDuracaoFree;
    @FXML TableColumn<Object, Object> colunaCustoFree;
    
    @FXML TableView<Classificacao> tabelaClassificacao;
    @FXML TableColumn<Object, Object> colunaClassificacao;

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }
    
    /**
    * Initializes the controller (UI) class.
    */
    @Override
    public void initialize(URL location, ResourceBundle resources){

        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCategoriaController = new RegistarCategoriaController();
        try {
            registarTarefaController = new RegistarTarefaController();
            // cmbAnuncio.getItems().setAll(updateAnunciosASeriar());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
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

        try {
            tabelaTarefas.getItems().setAll(registarTarefaController.getAllOrganizacao(getNifOrganizacao()));
            preencherTabela();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        
        /*try{
            tabelaFreelancers.getItems().setAll(seriarAnuncioController.getAllByIdAnuncio(getIdAnuncio()));
            preencherTabelaFreelancer ();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }*/
    }

    public void updateTableViewTarefas() throws SQLException {
        cmbFiltroTarefas.getSelectionModel().clearSelection();
        tabelaTarefas.getItems().setAll(registarTarefaController.getAllOrganizacao(
                getNifOrganizacao() ));

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
    
    public int getIdAnuncio()throws SQLException{
        
        String referenciaTarefa = cmbAnuncio.getSelectionModel().getSelectedItem();
        String nifOrganizacao = getNifOrganizacao();
        return registarTarefaController.findIdAnuncio(nifOrganizacao, referenciaTarefa);
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

    public void navigateEspecificarTarefa(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/EspecificarTarefaColaboradorScene.fxml"));
            Parent rootAddTarefa = loaderAddTarefa.load();
            sceneAddTarefa = new Scene(rootAddTarefa);
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

    public void navigatePublicarTarefa(ActionEvent event) {
        try {
            FXMLLoader loaderPublicarTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/PublicarTarefaScene.fxml"));
            Parent rootPublicarTarefa = loaderPublicarTarefa.load();
            scenePublicarTarefa = new Scene(rootPublicarTarefa);
            PublicarTarefaUI publicarTarefaUI = loaderPublicarTarefa.getController();
            publicarTarefaUI.associarParentUI(this);

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
    
    public void preencherTabela () {
        colunaDesignacao.setCellValueFactory( new PropertyValueFactory<>("designacao"));
        colunaReferencia.setCellValueFactory( new PropertyValueFactory<>("referencia"));
        colunaDuracao.setCellValueFactory( new PropertyValueFactory<>("duracaoEst"));
        colunaCusto.setCellValueFactory( new PropertyValueFactory<>("custoEst"));
    }
    
    public void preencherTabelaFreelancer () {
        colunaNome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        colunaEmail.setCellValueFactory( new PropertyValueFactory<>("email"));
        colunaDuracaoFree.setCellValueFactory( new PropertyValueFactory<>("duracaoFree"));
        colunaCustoFree.setCellValueFactory( new PropertyValueFactory<>("custoFree"));
    }
    
    public void preencherTabelaClassificacao () {
        colunaClassificacao.setCellValueFactory( new PropertyValueFactory<>("classificacao"));        
    }
    
    public List<String> updateAnunciosASeriar() throws SQLException{
        String nifOrganizacao = getNifOrganizacao();
        List<String> tarefasOrg = seriarAnuncioController.getReferenciasTarefas(nifOrganizacao);
        String emailColaborador = gestaoUtilizadoresController.getEmail();
        List<Tarefa> anunciosColaborador = seriarAnuncioController.findTarefasPublicadas(
                tarefasOrg, nifOrganizacao, emailColaborador);
        List<String> anunciosColaboradorRefTarefas = seriarAnuncioController.getReferenciasTarefas(anunciosColaborador);
        List<String> refTarefasASeriar = seriarAnuncioController.getAllRefTarefasASeriar(
                        anunciosColaboradorRefTarefas, nifOrganizacao);
        
        return refTarefasASeriar;        
    }

    public void consultarAnuncioAction(ActionEvent event){
                
        try {
            FXMLLoader loaderConsultarAnuncio = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConsultarAnuncioScene.fxml"));
            Parent rootConsultarAnuncio = loaderConsultarAnuncio.load();
            sceneConsultarAnuncio = new Scene(rootConsultarAnuncio);
            ConsultarAnuncioUI consultarAnuncioUI = loaderConsultarAnuncio.getController();
            consultarAnuncioUI.associarParentUI(this);

            adicionarStage.setScene(sceneConsultarAnuncio);
            adicionarStage.setTitle("Consultar Anúncio");
            adicionarStage.show();

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }       
    }
    
    public void consultarCandidaturaFreelancer(ActionEvent event){
        try {
            FXMLLoader loaderConsultarCandidaturaFreelancer = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConsultarCandidaturaFreelancerScene.fxml"));
            Parent rootConsultarCandidaturaFreelancer = loaderConsultarCandidaturaFreelancer.load();
            sceneConsultarCandidatura = new Scene(rootConsultarCandidaturaFreelancer);
            ConsultarCandidaturaFreelancerUI consultarCandidaturaFreelancerUI = loaderConsultarCandidaturaFreelancer.getController();
            consultarCandidaturaFreelancerUI.associarParentUI(this);

            adicionarStage.setScene(sceneConsultarCandidatura);
            adicionarStage.setTitle("Consultar Candidatura do Freelancer");
            adicionarStage.show();

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        } 
    }
    
   
    
    public void seriacaoAutomaticaAction(ActionEvent event) throws SQLException{
        try{
            String referenciaTarefa = cmbAnuncio.getSelectionModel().getSelectedItem();
            String nifOrganizacao = getNifOrganizacao();
            int idAnuncio = registarTarefaController.findIdAnuncio(nifOrganizacao, referenciaTarefa);
            List<Candidatura> candidaturas = seriarAnuncioController.getAllByIdAnuncio(idAnuncio);
            List<Candidatura> candidaturasOrdenadas = seriarAnuncioController.ordenarByValor(candidaturas);
        } catch(SQLException exception){
             exception.printStackTrace();
        }
        
        
    }
    
     public void seriacaoManualAction(ActionEvent event){
        
    }
    
    
  

}
