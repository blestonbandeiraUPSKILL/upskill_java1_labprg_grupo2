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
    private Scene sceneSeriacaoManual;
    private int idAnuncio;

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

    @FXML TableView<TabelaCandidaturasAnuncio> tabelaCandidaturasFreelancers;
    private List<TabelaCandidaturasAnuncio> listaCandidaturasAnuncio = new ArrayList<>();
    @FXML TableColumn<Object, Object> colunaClassificacao;
    @FXML TableColumn<Object, Object> colunaIdCandidatura;
    @FXML TableColumn<Object, Object> colunaEmail;
    @FXML TableColumn<Object, Object> colunaDuracaoFree;
    @FXML TableColumn<Object, Object> colunaCustoFree;
    
    /**
     * Associa a scene StartingPageUI como parent desta Scene 
     * @param StartinPageUI 
     */
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
        try {
            registarTarefaController = new RegistarTarefaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        registarColaboradorController = new RegistarColaboradorController();
        seriarAnuncioController = new SeriarAnuncioController();

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


        try {
            listaAnunciosASeriar();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        cmbAnuncio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    btnConsultarAnuncio.setDisable(false);
                    if(existeCandidatura()){
                       criarTabelaClassificacao();
                        if(existeSeriacao()){
                            updateDataSeriacao();
                        }
                        else {
                            tipoSeriacao();
                        }
                    }
                    else{
                        AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                                MainApp.TITULO_APLICACAO, "Não existem candidaturas ao anúncio selecionado",
                                "Por favor, escolha outro anúncio para seriar!").show();
                    }
                                          
                } catch (SQLException exception) {
                   exception.printStackTrace();
                }
            }
        });
     }
    
    /**
     * Devolve o email do colaborador logado
     * @return
     * @throws SQLException 
     */
    public String getEmailColaborador() throws SQLException {
        return gestaoUtilizadoresController.getEmail();
    }
        
    /**
     * Devolve o nif da organizacao a que pertence o colaborador logado
     * @return
     * @throws SQLException 
     */
    public String getNifOrganizacao() throws SQLException {
        return registarColaboradorController.getNifOrganizacao(
                gestaoUtilizadoresController.getEmail());
    }
    
    /**
     * Devolve o id do Anuncio selecionado
     * @return
     * @throws SQLException 
     */
    public int getIdAnuncio()throws SQLException{
        
        String referenciaTarefa = cmbAnuncio.getSelectionModel().getSelectedItem();
        String nifOrganizacao = getNifOrganizacao();
        return registarTarefaController.findIdAnuncio(nifOrganizacao, referenciaTarefa);
    }
    
    /**
     * Verifica se existe seriacao para o anuncio selecionado
     * @return
     * @throws SQLException 
     */
    public boolean existeSeriacao() throws SQLException{
        idAnuncio = getIdAnuncio();

        List<ProcessoSeriacao> processos = new ArrayList<>();
        processos = seriarAnuncioController.getAllPSByIdAnuncio(idAnuncio);
        if(processos.size() > 0){
            return true;
        }
        return false;
    }
    
    /**
     * Verifica se existe candidaturas para o anuncio selecionado
     * @return
     * @throws SQLException 
     */
    public boolean existeCandidatura() throws SQLException{
        List<Candidatura> candidaturas = new ArrayList<>();
        idAnuncio = getIdAnuncio();
        candidaturas = seriarAnuncioController.getAllByIdAnuncio(idAnuncio);
        if(candidaturas.size() > 0){
            return true;
        }
        return false;
    }
            
    /**
     * Preenche a tabela de tarefas com todas as tarefas da organizacao
     * @throws SQLException 
     */
    public void updateTableViewTarefas() throws SQLException {
        cmbFiltroTarefas.getSelectionModel().clearSelection();
        tabelaTarefas.getItems().setAll(registarTarefaController.getAllOrganizacao(
                getNifOrganizacao()));

    }

    /**
     * Preenche a tabela de tarefas com tarefas adicionadas pelo colaborador logado
     * @throws SQLException 
     */
    public void updateTableViewTarefasColaborador() throws SQLException {
        cmbFiltroTarefas.getSelectionModel().clearSelection();
        String email = getEmailColaborador();
        String nifOrganizacao = registarColaboradorController.getNifOrganizacao(email);
        tabelaTarefas.getItems().setAll(registarTarefaController.findByColaboradorENif(email, nifOrganizacao));
        preencherTabela();
    }

    /**
     * Preenche a tabela de tarefas com Tarefas publicadas
     * @throws SQLException 
     */
    public void updateTableViewTarefasPublicadas() throws SQLException {
        cmbFiltroTarefas.getSelectionModel().clearSelection();
        String email = getEmailColaborador();
        String nifOrganizacao = getNifOrganizacao();
        List<String> referenciasTarefa = registarTarefaController.findRefenciaTarefa(nifOrganizacao);
        tabelaTarefas.getItems().setAll(registarTarefaController.findTarefasPublicadas(referenciasTarefa, nifOrganizacao, email));
        preencherTabela();
    }

    /**
     * Preenche a tabela de tarefas com tarefas nao publicadas
     * @throws SQLException 
     */
    public void updateTableViewTarefasNaoPublicadas() throws SQLException {
        cmbFiltroTarefas.getSelectionModel().clearSelection();
        String email = getEmailColaborador();
        String nifOrganizacao = getNifOrganizacao();
        List<String> referenciasTarefa = registarTarefaController.findRefenciaTarefa(nifOrganizacao);
        tabelaTarefas.getItems().setAll(registarTarefaController.findTarefasNaoPublicadas(referenciasTarefa, email, nifOrganizacao));
        preencherTabela();
    }
    
    /**
     * Devolve uma lista de anuncios que ainda nao foram seriados
     * @throws SQLException 
     */
    public void listaAnunciosASeriar() throws SQLException{
        try{
            String emailColaborador = getEmailColaborador();
            String nifOrganizacao = getNifOrganizacao();
            List<String> tarefasOrg = seriarAnuncioController.getReferenciasTarefas(nifOrganizacao);
            List<Tarefa> refAnunciosASeriar = new ArrayList<>();
                    
            refAnunciosASeriar = seriarAnuncioController.getAllRefTarefasASeriar(
                        tarefasOrg, nifOrganizacao, emailColaborador);


            if(refAnunciosASeriar.size()>0){
                cmbAnuncio.getItems().setAll(seriarAnuncioController.getReferenciasTarefas(refAnunciosASeriar));
            }
            else{
                List<String> listaVazia = new ArrayList<>();
                listaVazia.add("Sem Anúncios a seriar"); 
                cmbAnuncio.getItems().setAll(listaVazia);;
            }       
        }catch(SQLException exception) {
                   exception.printStackTrace();
        }

    }
    
    /**
     * Cria uma tabela de classificacao de freelancers no anuncio selecionado
     * @throws SQLException 
     */
    public void criarTabelaClassificacao() throws SQLException{
        idAnuncio = getIdAnuncio();
        List<Candidatura> candidaturas = seriarAnuncioController.getAllByIdAnuncio(idAnuncio);
        for(int i = 0; i < candidaturas.size(); i++){
            TabelaCandidaturasAnuncio cellCandidatura = new TabelaCandidaturasAnuncio(
            candidaturas.get(i).getIdCandidatura(), candidaturas.get(i).getEmailFreelancer(), 
                    candidaturas.get(i).getNumeroDias(), candidaturas.get(i).getValorPretendido());
            listaCandidaturasAnuncio.add(cellCandidatura);
        }
        tabelaCandidaturasFreelancers.getItems().setAll(listaCandidaturasAnuncio);
        preencherTabelaCandidaturas ();
        btnConsultarCandidaturaFreelancer.setDisable(false);
    }
    
    /**
     * Preenche a tabela de classificacao
     * @param idSeriacao
     * @throws SQLException 
     */
    public void updateTabelaClassificacao(int idSeriacao)throws SQLException{
        List<Classificacao> listaClassificada = seriarAnuncioController.getAllBySeriacao(idSeriacao);
        for(int i = 0; i < listaCandidaturasAnuncio.size(); i++){
            for(int j = 0; j < listaClassificada.size(); j++){
                if(listaCandidaturasAnuncio.get(i).getIdCandidatura() == listaClassificada.get(j).getIdCandidatura()){
                    listaCandidaturasAnuncio.get(i).setClassificacao(listaClassificada.get(j).getPosicaoFreelancer());
                }
            }
        }
        tabelaCandidaturasFreelancers.getItems().setAll(listaCandidaturasAnuncio);
        preencherTabelaCandidaturas ();
    }
    
    /**
     * Atualiza a data de seriacao das candidaturas do anuncio selecionado
     * @throws SQLException 
     */
    public void updateDataSeriacao() throws SQLException{
        txtDataSeriacao.clear();
        idAnuncio = getIdAnuncio();
        
        List<ProcessoSeriacao> processos = new ArrayList<>();
        processos = seriarAnuncioController.getAllPSByIdAnuncio(idAnuncio);
        
        txtDataSeriacao.setText(processos.get(0).getDataSeriacao());
        btnSeriacaoAutomatica.setDisable(true);
        btnSeriacaoManual.setDisable(true);
        updateTabelaClassificacao(processos.get(0).getIdSeriacao());
    }
    
    /**
     * Preenche a tabela de tarefas com as tarefas pretendidas
     */
    public void preencherTabela () {
        colunaDesignacao.setCellValueFactory( new PropertyValueFactory<>("designacao"));
        colunaReferencia.setCellValueFactory( new PropertyValueFactory<>("referencia"));
        colunaDuracao.setCellValueFactory( new PropertyValueFactory<>("duracaoEst"));
        colunaCusto.setCellValueFactory( new PropertyValueFactory<>("custoEst"));
    }
    
    /**
     * Preenche a tabela de Candidaturas
     */
    public void preencherTabelaCandidaturas () {
        colunaClassificacao.setCellValueFactory( new PropertyValueFactory<>("classificacao"));        
        colunaIdCandidatura.setCellValueFactory( new PropertyValueFactory<>("idCandidatura"));        
        colunaEmail.setCellValueFactory( new PropertyValueFactory<>("email"));
        colunaDuracaoFree.setCellValueFactory( new PropertyValueFactory<>("duracao"));
        colunaCustoFree.setCellValueFactory( new PropertyValueFactory<>("custo"));
    }
      
    /**
     * Aplica um filtro de tarefas de acordo com o tipo de tarefas pretendido
     * @param actionEvent
     * @throws SQLException 
     */
    public void aplicarFiltroTarefas(ActionEvent actionEvent) throws SQLException {


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
    
    /**
     * Altera os botoes de seriacao de acordo com i tipo de regimento do anuncio
     * @throws SQLException 
     */
    public void tipoSeriacao() throws SQLException{
        
        idAnuncio = getIdAnuncio();
        int idRegimento = seriarAnuncioController.getAnuncio(idAnuncio).getIdTipoRegimento();
        if(idRegimento == 1){
            btnSeriacaoAutomatica.setDisable(false);
        }
        else{
            btnSeriacaoManual.setDisable(false);
        }
    }
    
    /**
     * Seria automaticamente as candidaturas a um anuncio
     * @param event
     * @throws SQLException 
     */
    public void seriacaoAutomaticaAction(ActionEvent event) throws SQLException{
        try{
            idAnuncio = getIdAnuncio();
            List<Candidatura> candidaturas = seriarAnuncioController.getAllByIdAnuncio(idAnuncio);
            List<Candidatura> candidaturasOrdenadas = seriarAnuncioController.ordenarByValor(candidaturas);
            boolean seriacaoCriada = seriarAnuncioController.saveSeriacao(idAnuncio);
            if(seriacaoCriada){
                int idSeriacao = seriarAnuncioController.getIdSeriacao(idAnuncio);
                boolean sucesso = seriarAnuncioController.saveClassificacaoAutomatica(candidaturasOrdenadas, idSeriacao);
                if(sucesso){
                    updateDataSeriacao();                    
                }
            }                                   
        } catch(SQLException exception){
             exception.printStackTrace();
        }      
    }
    
    /**
     * Seria manualmente as candidaturas a um anuncio
     * @param event
     * @throws SQLException 
     */
    public void seriacaoManualAction(ActionEvent event) throws SQLException{
        try {
            FXMLLoader loaderSeriacaoManual = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/SeriacaoManualScene.fxml"));
            Parent rootSeriacaoManual = loaderSeriacaoManual.load();
            sceneSeriacaoManual = new Scene(rootSeriacaoManual);
            SeriacaoManualUI seriacaoManualUI = loaderSeriacaoManual.getController();
            seriacaoManualUI.associarParentUI(this);
            seriacaoManualUI.transferData();
            
            adicionarStage.setScene(sceneSeriacaoManual);
            adicionarStage.setTitle("Seriação Manual do Anúncio");
            adicionarStage.show();

        } catch (IOException|SQLException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }   
        
    /**
     * Navega para a pagina EspecificarTarefaUI
     * @param actionEvent 
     */
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
    
    /**
     * Navega para a pagina PublicarTarefaUI
     * @param event 
     */
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
    
    /**
     * Navega para a pagina ConsultarAnuncioUI
     * @param event
     * @throws SQLException 
     */
    public void consultarAnuncioAction(ActionEvent event) throws SQLException{
        try {
            FXMLLoader loaderConsultarAnuncio = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConsultarAnuncioScene.fxml"));
            Parent rootConsultarAnuncio = loaderConsultarAnuncio.load();
            sceneConsultarAnuncio = new Scene(rootConsultarAnuncio);
            ConsultarAnuncioUI consultarAnuncioUI = loaderConsultarAnuncio.getController();
            consultarAnuncioUI.associarParentUI(this);
            consultarAnuncioUI.transferData();
            
            adicionarStage.setScene(sceneConsultarAnuncio);
            adicionarStage.setTitle("Consultar Anúncio");
            adicionarStage.show();

        } catch (IOException|SQLException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

    /**
     * Navega para a pagina ConsultarCandidaturaFreelancerUI
     * @param event
     * @throws SQLException 
     */
    public void consultarCandidaturaFreelancer(ActionEvent event) throws SQLException {
        try {
            FXMLLoader loaderConsultarCandidaturaFreelancer = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConsultarCandidaturaFreelancerScene.fxml"));
            Parent rootConsultarCandidaturaFreelancer = loaderConsultarCandidaturaFreelancer.load();
            sceneConsultarCandidatura = new Scene(rootConsultarCandidaturaFreelancer);
            ConsultarCandidaturaFreelancerUI consultarCandidaturaFreelancerUI = loaderConsultarCandidaturaFreelancer.getController();
            consultarCandidaturaFreelancerUI.associarParentUI(this);
            consultarCandidaturaFreelancerUI.transferData();

            adicionarStage.setScene(sceneConsultarCandidatura);
            adicionarStage.setTitle("Consultar Candidatura do Freelancer");
            adicionarStage.show();

        } catch (IOException | SQLException exception) {

            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

    /**
     * Faz logout da sessao
     * @param actionEvent 
     */
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
