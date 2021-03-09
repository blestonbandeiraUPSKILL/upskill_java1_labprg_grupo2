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
    @FXML TextField txtDataSeriacao;
    
    @FXML TableView<Tarefa> tabelaTarefas;
    @FXML TableColumn<Object, Object> colunaReferencia;
    @FXML TableColumn<Object, Object> colunaDesignacao;
    @FXML TableColumn<Object, Object> colunaDuracao;
    @FXML TableColumn<Object, Object> colunaCusto;

    @FXML TableView<Candidatura> tabelaCandidaturasFreelancers;
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
        
        btnConsultarAnuncio.setDisable(true);
        btnConsultarCandidaturaFreelancer.setDisable(true);
        btnSeriacaoAutomatica.setDisable(true);
        btnSeriacaoManual.setDisable(true);
                
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
        
        try{
            cmbAnuncio.getItems().setAll(listaAnunciosASeriar());
        }catch(SQLException exception) {
                   exception.printStackTrace();
        }
        
        
        cmbAnuncio.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               try {
                   btnConsultarAnuncio.setDisable(false);
                   tabelaCandidaturasFreelancers.getItems().setAll(seriarAnuncioController.getAllByIdAnuncio(getIdAnuncio()));
                   preencherTabelaCandidaturas ();
                   tipoSeriacao(event);                   
               } catch (SQLException exception) {
                   exception.printStackTrace();
               }
           }
           });
     }
    
    public String getNifOrganizacao() throws SQLException {
        return registarColaboradorController.getNifOrganizacao(
                gestaoUtilizadoresController.getEmail());
    }
    
    public int getIdAnuncio()throws SQLException{
        
        String referenciaTarefa = cmbAnuncio.getSelectionModel().getSelectedItem();
        String nifOrganizacao = getNifOrganizacao();
        return registarTarefaController.findIdAnuncio(nifOrganizacao, referenciaTarefa);
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
    
    public List<String> listaAnunciosASeriar() throws SQLException{
        String nifOrganizacao = getNifOrganizacao();
        List<String> tarefasOrg = seriarAnuncioController.getReferenciasTarefas(nifOrganizacao);
        String emailColaborador = gestaoUtilizadoresController.getEmail();
        List<Tarefa> anunciosColaborador = seriarAnuncioController.findTarefasPublicadas(
                tarefasOrg, nifOrganizacao, emailColaborador);
        List<String> anunciosColaboradorRefTarefas = seriarAnuncioController.getReferenciasTarefas(anunciosColaborador);
        List<String> refAnunciosASeriar = seriarAnuncioController.getAllRefTarefasASeriar(
                        anunciosColaboradorRefTarefas, nifOrganizacao);
        
        if(refAnunciosASeriar.size()>0){
            return refAnunciosASeriar;
        }
        else{
            List<String> listaVazia = new ArrayList<>();
            listaVazia.add("Sem Anúncios a seriar"); 
            return listaVazia;
        }       
    }
    
    public void updateDataSeriacao() throws SQLException{
        txtDataSeriacao.setText(seriarAnuncioController.findSeriacaoByAnuncio(getIdAnuncio()).getDataSeriacao());
    }
    
    public void preencherTabela () {
        colunaDesignacao.setCellValueFactory( new PropertyValueFactory<>("designacao"));
        colunaReferencia.setCellValueFactory( new PropertyValueFactory<>("referencia"));
        colunaDuracao.setCellValueFactory( new PropertyValueFactory<>("duracaoEst"));
        colunaCusto.setCellValueFactory( new PropertyValueFactory<>("custoEst"));
    }
    
    public void preencherTabelaCandidaturas () {
        colunaEmail.setCellValueFactory( new PropertyValueFactory<>("emailFreelancer"));
        colunaDuracaoFree.setCellValueFactory( new PropertyValueFactory<>("numeroDias"));
        colunaCustoFree.setCellValueFactory( new PropertyValueFactory<>("valorPretendido"));
    }
    
    public void preencherTabelaClassificacao () {
        colunaClassificacao.setCellValueFactory( new PropertyValueFactory<>("classificacao"));        
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
    
    public void tipoSeriacao(ActionEvent actionEvent) throws SQLException{
        int idAnuncio = getIdAnuncio();
        List<Candidatura> candidaturas = seriarAnuncioController.getAllByIdAnuncio(idAnuncio);
        int idSeriacao = seriarAnuncioController.getIdSeriacao(idAnuncio);
        int idRegimento = seriarAnuncioController.getAnuncio(idAnuncio).getIdTipoRegimento();
        if(idSeriacao !=0 && candidaturas.size()> 0){
            if(idRegimento == 1){
                 btnSeriacaoAutomatica.setDisable(false);
            }
            else{
                 btnSeriacaoManual.setDisable(false);
            }
        }
        if(idSeriacao==0){
            updateDataSeriacao();
        }
    }
    
    public void seriacaoAutomaticaAction(ActionEvent event) throws SQLException{
        try{
            int idAnuncio = getIdAnuncio();
            List<Candidatura> candidaturas = seriarAnuncioController.getAllByIdAnuncio(idAnuncio);
            List<Candidatura> candidaturasOrdenadas = seriarAnuncioController.ordenarByValor(candidaturas);
            boolean seriacaoCriada = seriarAnuncioController.saveSeriacao(idAnuncio);
            int idSeriacao = seriarAnuncioController.getIdSeriacao(idAnuncio);
            boolean sucesso = seriarAnuncioController.saveClassificacaoAutomatica(candidaturasOrdenadas, idSeriacao);
            if(sucesso){
                listaAnunciosASeriar();
            }
        } catch(SQLException exception){
             exception.printStackTrace();
        }      
    }
    
    public void seriacaoManualAction(ActionEvent event){
        
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
}
