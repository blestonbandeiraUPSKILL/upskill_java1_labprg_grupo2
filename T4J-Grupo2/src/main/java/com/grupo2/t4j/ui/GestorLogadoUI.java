package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.domain.*;
import com.grupo2.t4j.dto.*;
import com.grupo2.t4j.exception.DataInvalidaException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class GestorLogadoUI implements Initializable {

    private RegistarCategoriaController registarCategoriaController;
    private RegistarColaboradorController registarColaboradorController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private AtribuirTarefaController atribuirTarefaController;
    private RegistarTarefaController registarTarefaController;
    private SeriarAnuncioController seriarAnuncioController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private StartingPageUI startingPageUI;
    private Scene sceneStartingPage;
    private Scene sceneAddColaborador;
    private Stage adicionarStage;
    private Scene sceneAddTarefa;
    private Scene scenePublicarTarefa;
    private Scene sceneConsultarAnuncio;
    private Scene sceneConsultarCandidatura;
    private Scene sceneSeriacaoManual;
    private Scene sceneConsultarAtribuicaoGestor;
    private int idAnuncio;

    @FXML Button btnLogout;

    @FXML ComboBox<FiltroTarefas> cmbFiltroTarefas;
    @FXML ComboBox<String> cmbAnuncio;
    @FXML Button btnAddColaborador;
    @FXML Button btnPublicarTarefa;
    @FXML Button btnConsultarAnuncio;
    @FXML Button btnConsultarCandidaturaFreelancer;
    @FXML Button btnSeriacaoManual;
    @FXML Button btnAtribuicao;
    @FXML Button btnConsultarAtribuicao;
    @FXML Button btnMudarData;
    @FXML Button btnManterData;

    @FXML TextField txtDataSeriacao;
    @FXML TextField txtDataInTarefa;
    @FXML Label txtEmail;

    @FXML TableView<TarefaDTO> tabelaTarefas;
    @FXML TableColumn<Object, Object> colunaReferencia;
    @FXML TableColumn<Object, Object> colunaDesignacao;
    @FXML TableColumn<Object, Object> colunaDuracao;
    @FXML TableColumn<Object, Object> colunaCusto;

    @FXML TableView<TabelaCandidaturasAnuncio> tabelaCandidaturasFreelancers;
    List<TabelaCandidaturasAnuncio> listaCandidaturasAnuncio = new ArrayList<>();
    @FXML TableColumn<Object, Object> colunaClassificacao;
    @FXML TableColumn<Object, Object> colunaIdCandidatura;
    @FXML TableColumn<Object, Object> colunaEmailFreelancer;
    @FXML TableColumn<Object, Object> colunaDuracaoFree;
    @FXML TableColumn<Object, Object> colunaCustoFree;
    
    //TableView Colaboradores
    @FXML TableView<ColaboradorDTO> listViewColaboradores;
    @FXML TableColumn<Object, Object> colunaEmailColab;
    @FXML TableColumn<Object, Object> colunaNome;
    @FXML TableColumn<Object, Object> colunaTelefone;
    @FXML TableColumn<Object, Object> colunaFuncao;

    //TableView Atribuições
    @FXML TableView<TabelaConsultaAtribuicaoDTO> tabelaAtribuicoes;
    List<TabelaConsultaAtribuicaoDTO> listaAtribuicoesOrganizacao = new ArrayList<>();
    @FXML TableColumn<Object, Object> colunaRefTarefa;
    @FXML TableColumn<Object, Object> colunaFreelancer;
    @FXML TableColumn<Object, Object> colunaDataAtribuicao;
    @FXML TableColumn<Object, Object> colunaCodAtribuicao;
    @FXML TableColumn<Object, Object> colunaDataInicioTarefa;

    private boolean mudarMetodo = false;
    private boolean dataModificada = false;


     
    /**
     * Associa a scene StartingPageUI como parent desta Scene 
     * @param startingPageUI
     */
    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }
    
    /**
    * Initializes the controller (UI) class.
     * @param location
     * @param resources
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        registarAreaActividadeController = new RegistarAreaActividadeController();
        atribuirTarefaController = new AtribuirTarefaController();
        registarCategoriaController = new RegistarCategoriaController();
        try {
            registarTarefaController = new RegistarTarefaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        registarColaboradorController = new RegistarColaboradorController();
        seriarAnuncioController = new SeriarAnuncioController();

        txtEmail.setText(gestaoUtilizadoresController.getEmail());

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);
        adicionarStage.setResizable(false);

        btnConsultarAnuncio.setDisable(true);
        btnConsultarCandidaturaFreelancer.setDisable(true);
        btnSeriacaoManual.setDisable(true);

        try {
            existeAnuncioSeriado();
        }catch (SQLException exception) {
            exception.printStackTrace();
        }

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
                    txtDataSeriacao.clear();
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

        try {
            updateListViewColaboradores();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Devolve o email do gestor logado
     * @return
     * @throws SQLException
     */
    public String getEmailColaborador() throws SQLException {
        return gestaoUtilizadoresController.getEmail();
    }

    /**
     * Devolve o nif da organizacao a que pertence o gestor logado
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

        List<ProcessoSeriacaoDTO> processos = new ArrayList<>();
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
        List<CandidaturaDTO> candidaturas = new ArrayList<>();
        idAnuncio = getIdAnuncio();
        candidaturas = seriarAnuncioController.getAllByIdAnuncio(idAnuncio);
        limpaTabelaCandidaturas();
        if(candidaturas.size() > 0){
            btnConsultarCandidaturaFreelancer.setDisable(false);
            return true;
        }

        btnConsultarCandidaturaFreelancer.setDisable(true);
        return false;
    }

    public void existeAnuncioSeriado() throws SQLException{
        String nifOrganizacao = getNifOrganizacao();
        List<Integer> idsAnunciosSeriados = atribuirTarefaController.getAnunciosSeriados(nifOrganizacao);
        List<AtribuicaoDTO> atribuicoes = atribuirTarefaController.getAllByOrganizacao(nifOrganizacao);
        List<AnuncioDTO> anunciosSeriadosNaoAtribuidos = atribuirTarefaController.getAnunciosSeriadosNaoAtribuidos(nifOrganizacao);
        btnAtribuicao.setDisable(true);
        btnConsultarAtribuicao.setDisable(true);
        btnMudarData.setDisable(true);
        btnManterData.setDisable(true);
        txtDataInTarefa.setDisable(true);

        if(idsAnunciosSeriados.size() > 0){
            criaTabelaAtribuicao();
            if(atribuicoes.size() > 0){
                btnConsultarAtribuicao.setDisable(false);
            }
            else{
                btnConsultarAtribuicao.setDisable(true);
            }
            if(anunciosSeriadosNaoAtribuidos.size() > 0){
                btnAtribuicao.setDisable(false);

            }
            else{
                btnAtribuicao.setDisable(true);
            }
        }
        else{
            btnAtribuicao.setDisable(true);
            btnConsultarAtribuicao.setDisable(true);
            btnMudarData.setDisable(true);
            btnManterData.setDisable(true);
            txtDataInTarefa.setDisable(true);
        }
    }

    /**
     * Preenche a tabela de tarefas com todas as tarefas da organizacao
     * @throws SQLException
     */
    public void updateTableViewTarefas() throws SQLException {
        tabelaTarefas.getItems().clear();

        tabelaTarefas.getItems().setAll(registarTarefaController.getAllOrganizacao(
                getNifOrganizacao()));
    }

    /**
     * Preenche a tabela de tarefas com Tarefas publicadas
     * @throws SQLException
     */
    public void updateTableViewTarefasPublicadas() throws SQLException {
        tabelaTarefas.getItems().clear();

        String nifOrganizacao = getNifOrganizacao();

        tabelaTarefas.getItems().setAll(registarTarefaController.findTarefasPublicadas(nifOrganizacao));
        preencherTabela();
    }

    /**
     * Preenche a tabela de tarefas com tarefas nao publicadas
     * @throws SQLException
     */
    public void updateTableViewTarefasNaoPublicadas() throws SQLException {
        tabelaTarefas.getItems().clear();

        String email = getEmailColaborador();
        String nifOrganizacao = getNifOrganizacao();

        tabelaTarefas.getItems().setAll(registarTarefaController.findTarefasNaoPublicadas(nifOrganizacao, email));
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
            List<String> referenciasASeriar = seriarAnuncioController.getReferenciasTarefasASeriar(nifOrganizacao, emailColaborador);

            if(referenciasASeriar.size()>0){
                cmbAnuncio.getItems().setAll(referenciasASeriar);
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
     * Limpa a tabela de candidauras
     */
    public void limpaTabelaCandidaturas(){
        listaCandidaturasAnuncio.clear();
        preencherTabelaCandidaturas();
    }

    /**
     * Limpa a tabela de atribuições
     */
    public void limpaTabelaAtribuicoes(){
        listaAtribuicoesOrganizacao.clear();
        preencherTabelaAtribuicoes();
    }

    /**
     * Cria uma tabela de classificacao de freelancers no anuncio selecionado
     * @throws SQLException
     */
    public void criarTabelaClassificacao() throws SQLException{
        idAnuncio = getIdAnuncio();
        List<CandidaturaDTO> candidaturas = seriarAnuncioController.getAllByIdAnuncio(idAnuncio);
        for(int i = 0; i < candidaturas.size(); i++){
            TabelaCandidaturasAnuncio cellCandidatura = new TabelaCandidaturasAnuncio(
                    candidaturas.get(i).getIdCandidatura(), candidaturas.get(i).getEmailFreelancer(),
                    candidaturas.get(i).getNumeroDias(), candidaturas.get(i).getValorPretendido());
            listaCandidaturasAnuncio.add(cellCandidatura);
        }

        preencherTabelaCandidaturas();
    }

    /**
     * Atualiza a tabela de classificacao após candidaturas seriadas
     * @param idSeriacao
     * @throws SQLException
     */
    public void updateTabelaClassificacao(int idSeriacao)throws SQLException{
        limpaTabelaCandidaturas();
        idAnuncio = getIdAnuncio();
        List<CandidaturaDTO> candidaturas = seriarAnuncioController.getAllByIdAnuncio(idAnuncio);
        List<ClassificacaoDTO> listaClassificada = seriarAnuncioController.getAllBySeriacao(idSeriacao);
        for(int i = 0; i <candidaturas.size(); i++){
            for(int j = 0; j < listaClassificada.size(); j++){
                if(candidaturas.get(i).getIdCandidatura() == listaClassificada.get(j).getIdCandidatura()){
                    TabelaCandidaturasAnuncio cellCandidatura = new TabelaCandidaturasAnuncio(
                            listaClassificada.get(j).getPosicaoFreelancer(), candidaturas.get(i).getIdCandidatura(),
                            candidaturas.get(i).getEmailFreelancer(), candidaturas.get(i).getNumeroDias(),
                            candidaturas.get(i).getValorPretendido());
                    listaCandidaturasAnuncio.add(cellCandidatura);
                }
            }
        }
        preencherTabelaCandidaturas ();
    }

    public void criaTabelaAtribuicao() throws SQLException{
        String nifOrganizacao = getNifOrganizacao();
        List<AtribuicaoDTO> atribuicoes = atribuirTarefaController.getAllByOrganizacao(nifOrganizacao);
        List<AnuncioDTO> anunciosSeriadosNaoAtribuidos = atribuirTarefaController.getAnunciosSeriadosNaoAtribuidos(nifOrganizacao);

        for(int i = 0; i < atribuicoes.size(); i++){
            TabelaConsultaAtribuicaoDTO cellAtribuicao = new TabelaConsultaAtribuicaoDTO(atribuicoes.get(i).getRefTarefa(),
                    atribuicoes.get(i).getEmailFreelancer(), atribuicoes.get(i).getDataAtribuicao(),
            atribuicoes.get(i).getCodigoAtribuicao(), atribuicoes.get(i).getDataInicioTarefa());
            listaAtribuicoesOrganizacao.add(cellAtribuicao);
        }
        for(int i = 0; i < anunciosSeriadosNaoAtribuidos.size(); i++){
            AnuncioDTO anuncioDTO = anunciosSeriadosNaoAtribuidos.get(i);
            TabelaConsultaAtribuicaoDTO cellNaoAtribuidos = new TabelaConsultaAtribuicaoDTO(anuncioDTO.getReferenciaTarefa(),
                    atribuirTarefaController.getEmailFreelancer(anuncioDTO.getIdAnuncio()), "Não Atribuído!",
                    "Não atribuído!", anuncioDTO.getDtFimPub());
            listaAtribuicoesOrganizacao.add(cellNaoAtribuidos);
        }

        preencherTabelaAtribuicoes();
    }

    public void updateTabelaAtribuicao() throws SQLException{
        limpaTabelaAtribuicoes();
        criaTabelaAtribuicao();
    }

    /**
     * Atualiza a data de seriacao das candidaturas do anuncio selecionado
     * @throws SQLException
     */
    public void updateDataSeriacao() throws SQLException{
        txtDataSeriacao.clear();
        idAnuncio = getIdAnuncio();

        List<ProcessoSeriacaoDTO> processos = seriarAnuncioController.getAllPSByIdAnuncio(idAnuncio);

        txtDataSeriacao.setText(processos.get(0).getDataSeriacao());
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
        tabelaCandidaturasFreelancers.getItems().setAll(listaCandidaturasAnuncio);
        colunaClassificacao.setCellValueFactory( new PropertyValueFactory<>("classificacao"));
        colunaIdCandidatura.setCellValueFactory( new PropertyValueFactory<>("idCandidatura"));
        colunaEmailFreelancer.setCellValueFactory( new PropertyValueFactory<>("email"));
        colunaDuracaoFree.setCellValueFactory( new PropertyValueFactory<>("duracao"));
        colunaCustoFree.setCellValueFactory( new PropertyValueFactory<>("custo"));
    }

    public void preencherTabelaAtribuicoes(){
        tabelaAtribuicoes.getItems().setAll(listaAtribuicoesOrganizacao);
        colunaRefTarefa.setCellValueFactory( new PropertyValueFactory<>("refTarefa"));
        colunaFreelancer.setCellValueFactory( new PropertyValueFactory<>("emailFreelancer"));
        colunaDataAtribuicao.setCellValueFactory( new PropertyValueFactory<>("dataAtribuicao"));
        colunaCodAtribuicao.setCellValueFactory( new PropertyValueFactory<>("codigoAtribuicao"));
        colunaDataInicioTarefa.setCellValueFactory( new PropertyValueFactory<>("dataInicioTarefa"));
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
        if(idRegimento != 1){
            btnSeriacaoManual.setDisable(false);
        }
        else{
            seriacaoAutomatica();
        }
    }

    /**
     * Seria automaticamente as candidaturas a um anuncio
     * @throws SQLException
     */
    public void seriacaoAutomatica() throws SQLException{
        try{
            idAnuncio = getIdAnuncio();
            boolean sucesso = seriarAnuncioController.seriar(idAnuncio);
            if(sucesso){
                updateDataSeriacao();
                existeAnuncioSeriado();
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
            FXMLLoader loaderSeriacaoManual = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/SeriacaoManualGestorScene.fxml"));
            Parent rootSeriacaoManual = loaderSeriacaoManual.load();
            sceneSeriacaoManual = new Scene(rootSeriacaoManual);
            SeriacaoManualGestorUI seriacaoManualGestorUI = loaderSeriacaoManual.getController();
            seriacaoManualGestorUI.associarParentUI(this);
            seriacaoManualGestorUI.transferData();

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
            FXMLLoader loaderAddTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/EspecificarTarefaGestorScene.fxml"));
            Parent rootAddTarefa = loaderAddTarefa.load();
            sceneAddTarefa = new Scene(rootAddTarefa);
            EspecificarTarefaGestorUI especificarTarefaGestorUI = loaderAddTarefa.getController();
            especificarTarefaGestorUI.associarParentUI(this);

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
            FXMLLoader loaderPublicarTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/PublicarTarefaGestorScene.fxml"));
            Parent rootPublicarTarefa = loaderPublicarTarefa.load();
            scenePublicarTarefa = new Scene(rootPublicarTarefa);
            PublicarTarefaGestorUI publicarTarefaGestorUI = loaderPublicarTarefa.getController();
            publicarTarefaGestorUI.associarParentUI(this);

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
            FXMLLoader loaderConsultarAnuncio = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConsultarAnuncioColaboradorScene.fxml"));
            Parent rootConsultarAnuncio = loaderConsultarAnuncio.load();
            sceneConsultarAnuncio = new Scene(rootConsultarAnuncio);
            ConsultarAnuncioGestorUI consultarAnuncioGestorUI = loaderConsultarAnuncio.getController();
            consultarAnuncioGestorUI.associarParentUI(this);
            consultarAnuncioGestorUI.transferData();

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
            FXMLLoader loaderConsultarCandidaturaFreelancer = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConsultarCandidaturaFreelancerColaboradorScene.fxml"));
            Parent rootConsultarCandidaturaFreelancer = loaderConsultarCandidaturaFreelancer.load();
            sceneConsultarCandidatura = new Scene(rootConsultarCandidaturaFreelancer);
            ConsultarCandidaturaFreelancerGestorUI consultarCandidaturaFreelancerGestorUI = loaderConsultarCandidaturaFreelancer.getController();
            consultarCandidaturaFreelancerGestorUI.associarParentUI(this);
            consultarCandidaturaFreelancerGestorUI.transferData();

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
    /**
     * Preenche a tabela de colaboradores registados pelo gestor
     * @throws SQLException
     */
    public void updateListViewColaboradores() throws SQLException {
        String nifOrganizacacao = getNifOrganizacao();
        listViewColaboradores.getItems().setAll(registarColaboradorController.getAll(nifOrganizacacao));

        colunaNome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        colunaFuncao.setCellValueFactory( new PropertyValueFactory<>("funcao"));
        colunaTelefone.setCellValueFactory( new PropertyValueFactory<>("telefone"));
        colunaEmailColab.setCellValueFactory( new PropertyValueFactory<>("email"));
    }


    /**
     * Navega para a pagina RegistarColaboradorUI
     * @param actionEvent
     */
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

    @FXML
    public void mudarData(ActionEvent actionEvent){
        if (tabelaAtribuicoes.getSelectionModel().getSelectedItem().getCodigoAtribuicao().equals("Não atribuído!")) {
            String novaData = txtDataInTarefa.getText();
            try{
                Data data = new Data(novaData);
                tabelaAtribuicoes.getSelectionModel().getSelectedItem().setDataInicioTarefa(novaData);
                mudarMetodo = true;
                dataModificada = true;
                btnMudarData.setDisable(true);
                btnManterData.setDisable(true);
                txtDataInTarefa.setDisable(true);
                btnAtribuicao.requestFocus();
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                        MainApp.TITULO_APLICACAO,
                        "Data de início da tarefa modificada com sucesso!",
                        "Prossiga para o registo da atribuição!");
            }catch (IllegalArgumentException iae){
                throw new DataInvalidaException("A data informada é inválida!");
            }
        }
    }

    @FXML
    public void manterData (ActionEvent actionEvent){
        mudarMetodo = true;
        btnMudarData.setDisable(true);
        btnManterData.setDisable(true);
        txtDataInTarefa.setDisable(true);
        btnAtribuicao.requestFocus();

    }

    @FXML
    public void registarAtribuicao (ActionEvent actionEvent) {
        String refTarefa = tabelaAtribuicoes.getSelectionModel().getSelectedItem().getRefTarefa();
        if (tabelaAtribuicoes.getSelectionModel().getSelectedItem().getCodigoAtribuicao().equals("Não atribuído!")) {
            if (!mudarMetodo) {
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                        MainApp.TITULO_APLICACAO,
                        "Deseja modificar a data de início da tarefa?",
                        "Se sim, realize esta mudança antes de realizar a atribuição!");
                btnMudarData.setDisable(false);
                btnManterData.setDisable(false);
                txtDataInTarefa.setDisable(false);
                txtDataInTarefa.requestFocus();
            } else {
                if (dataModificada) {
                    try {
                        String nifOrganizacao = getNifOrganizacao();
                        int idAnuncio = atribuirTarefaController.getIdAnuncioByTarefa(refTarefa, nifOrganizacao);
                        String dataInicioTarefa = txtDataInTarefa.getText();
                        boolean sucesso = atribuirTarefaController.atribuir(idAnuncio, dataInicioTarefa);
                        if (sucesso) {
                            existeAnuncioSeriado();
                            updateTabelaAtribuicao();
                            mudarMetodo = false;
                            dataModificada = false;
                        } else {
                            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                                    MainApp.TITULO_APLICACAO,
                                    "Erro",
                                    "A atribuição não foi registada!");
                        }
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                } else {
                    try {
                        String nifOrganizacao = getNifOrganizacao();
                        int idAnuncio = atribuirTarefaController.getIdAnuncioByTarefa(refTarefa, nifOrganizacao);
                        boolean sucesso = atribuirTarefaController.atribuir(idAnuncio);
                        if (sucesso) {
                            existeAnuncioSeriado();
                            updateTabelaAtribuicao();
                            mudarMetodo = false;
                            dataModificada = false;
                        } else {
                            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                                    MainApp.TITULO_APLICACAO,
                                    "Erro",
                                    "A atribuição não foi registada!");
                        }
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }

    @FXML
    public void consultarAtribuicao (ActionEvent actionEvent) {
        try {
            FXMLLoader loaderConsultarAtribuicaoGestor = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConsultarAtribuicaoGestorScene.fxml"));
            Parent rootConsultarAtribuicaoGestor = loaderConsultarAtribuicaoGestor.load();
            sceneConsultarAtribuicaoGestor = new Scene(rootConsultarAtribuicaoGestor);
            ConsultarAtribuicaoGestorUI consultarAtribuicaoGestorUI = loaderConsultarAtribuicaoGestor.getController();
            consultarAtribuicaoGestorUI.associarParentUI(this);
            consultarAtribuicaoGestorUI.transferData();

            adicionarStage.setScene(sceneConsultarAtribuicaoGestor);
            adicionarStage.setTitle("Consultar Atribuição");
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
     * Navega para a página de adicionar tarefa
     * @param actionEvent
     */
    public void navigateAddTarefa(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/EspecificarTarefaColaboradorScene.fxml"));
            Parent rootAddTarefa = loaderAddTarefa.load();
            sceneAddTarefa = new Scene(rootAddTarefa);
            EspecificarTarefaGestorUI especificarTarefaGestorUI = loaderAddTarefa.getController();
            especificarTarefaGestorUI.associarParentUI(this);

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
}
