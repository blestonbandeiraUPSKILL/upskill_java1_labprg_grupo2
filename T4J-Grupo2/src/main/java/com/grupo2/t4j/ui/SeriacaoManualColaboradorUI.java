/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

/**
 *
 * @author CAD
 */

import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.SeriarAnuncioController;
import com.grupo2.t4j.domain.*;
import com.grupo2.t4j.dto.CandidaturaDTO;
import com.grupo2.t4j.dto.ColaboradorDTO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author CAD
 */
public class SeriacaoManualColaboradorUI implements Initializable{

    private ColaboradorLogadoUI colaboradorLogadoUI;
    private SeriarAnuncioController seriarAnuncioController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private Stage adicionarStage;

    private int idAnuncio;
    private int qtdCand;
    private boolean terminou = false;
    private boolean seriacaoConcluida = false;
    private String emailColaborador;
    private String nifOrganizacao;
    private List<Integer> opcoesClassificacoes = new ArrayList<>();
    private List<Classificacao> classificacoes = new ArrayList<>();
    private List<String> colaboradoresParticipantes = new ArrayList<>();

    @FXML TextField txtIdAnuncio;
    @FXML Button btnAdicionarColaborador;
    @FXML Button btnConfirmarClassificacao;
    @FXML Button btnSeriacao;
    @FXML Button btnVoltar;
    @FXML Label txtEmail;


    @FXML TableView<TabelaFreelancerClassificacao> tabelaClassificacao;
    private List<TabelaFreelancerClassificacao> listaCandidaturas = new ArrayList<>();
    @FXML TableColumn<Object, Object> colunaIdCand;
    @FXML TableColumn<Object, Object> colunaEmail;
    @FXML TableColumn<Object, Object> colunaClassificacao;
    @FXML ComboBox<Integer> cmbClassificacao;


    @FXML TableView<TabelaColaboradorAdicional> tabelaColaboradores;
    private List<TabelaColaboradorAdicional> colaboradoresOpcionais = new ArrayList<>();
    @FXML TableColumn<Object, Object> colunaColaborador;
    @FXML TableColumn<Object, Object> colunaParticipante;

    /**
     * Associa a interface raiz desta nova janela
     * @param colaboradorLogadoUI
     */
    public void associarParentUI(ColaboradorLogadoUI colaboradorLogadoUI) {
        this.colaboradorLogadoUI = colaboradorLogadoUI;
    }
    
    /**
     * Inicializa a interface
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){

        seriarAnuncioController = new SeriarAnuncioController();

        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        txtEmail.setText(gestaoUtilizadoresController.getEmail());
          
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);  
        
    }
    
    /**
     * Tranfere as informações da janela anterior necessárias para a inicialização
     * desta janela
     * @throws SQLException
     */
    public void transferData() throws SQLException {
        
        emailColaborador = colaboradorLogadoUI.getEmailColaborador();
        nifOrganizacao = colaboradorLogadoUI.getNifOrganizacao();
        idAnuncio = colaboradorLogadoUI.getIdAnuncio();

        txtIdAnuncio.setText(Integer.toString(idAnuncio));
        
        try{
            criaTabelaCandidaturas();
            criaTabelaColaboradoresOpcionais();
            criarOpcoesClassificacao();    
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    
    /**
     *  Cria a tabela de candidaturas
     * @throws SQLException
     */
    public void criaTabelaCandidaturas() throws SQLException{
        List<CandidaturaDTO> candidaturas = seriarAnuncioController.getAllByIdAnuncio(idAnuncio);
        
        qtdCand = candidaturas.size();
        for(int i = 0; i < qtdCand; i++){
            TabelaFreelancerClassificacao cellCandidatura = new TabelaFreelancerClassificacao(
                    candidaturas.get(i).getIdCandidatura(),
                    candidaturas.get(i).getEmailFreelancer());
            listaCandidaturas.add(cellCandidatura);
        }
        preencherTabelaCandidaturas();
    }
    
    /**
     * Preenche a tabela de candidaturas
     */
    public void preencherTabelaCandidaturas () {
        tabelaClassificacao.getItems().setAll(listaCandidaturas);
        colunaIdCand.setCellValueFactory( new PropertyValueFactory<>("idCandidatura"));
        colunaEmail.setCellValueFactory( new PropertyValueFactory<>("emailFreelancer"));
        colunaClassificacao.setCellValueFactory( new PropertyValueFactory<>("classificacao"));       
    }
    
    /**
     * Faz a atualização da tabela de candidaturas após a atribuição de uma classificação
     * @param idCandidatura
     * @param posicao
     */
    public void updateTabelaCandidaturas(int idCandidatura, int posicao){
        for(int i = 0; i < listaCandidaturas.size(); i++){
            if(listaCandidaturas.get(i).getIdCandidatura() == idCandidatura){
                listaCandidaturas.get(i).setClassificacao(posicao);
            }
        }
        preencherTabelaCandidaturas();
    }
    
    /**
     * Cria a tabela de colaboradores da organização, excero pelo colaborador que está logado
     * @throws SQLException
     */
    public void criaTabelaColaboradoresOpcionais() throws SQLException{
        List<ColaboradorDTO> colaboradores = seriarAnuncioController.getAll(nifOrganizacao);
        
        for(int i = 0; i < colaboradores.size(); i++){
            if(!colaboradores.get(i).getEmail().getEmailText().equals(emailColaborador)){
                TabelaColaboradorAdicional cellColaborador = new TabelaColaboradorAdicional(colaboradores.get(i).getEmail().getEmailText(), "N");
                colaboradoresOpcionais.add(cellColaborador);
            }
        }
        preencherTabelaColaboradores ();
    }
    
    /**
     * Preenche a tabela de colaboradores
     */
    public void preencherTabelaColaboradores () {
        tabelaColaboradores.getItems().setAll(colaboradoresOpcionais);
        colunaColaborador.setCellValueFactory( new PropertyValueFactory<>("email"));
        colunaParticipante.setCellValueFactory( new PropertyValueFactory<>("selecao"));       
    }
    
    /**
     * Faz a atualização da tabela de colaboradores após a seleção de um deles como
     * participante
     * @param emailColabAdd
     */
    public void updateTabelaColaboradores(String emailColabAdd){
        for(int i = 0; i < colaboradoresOpcionais.size(); i++){
            if(colaboradoresOpcionais.get(i).getEmail().equals(emailColabAdd)){
                colaboradoresOpcionais.get(i).setSelecao("S");
            }
        }
        preencherTabelaColaboradores ();
    }
    
    /**
     * Cria as opções de classificação consoantes a quantidade de candidaturas
     */
    public void criarOpcoesClassificacao(){
        for(int i = 1; i < listaCandidaturas.size() + 1; i++){
            opcoesClassificacoes.add(i);
        }
        cmbClassificacao.getItems().setAll(opcoesClassificacoes);
    }
    
    /**
     * Elimina uma opção de classificação de candidatura considerando uma posição
     * que já tenha sido atribuída a uma candidatura
     * @param classUsada
     */
    public void updateOpcoesClassificacao(int classUsada){
        if(opcoesClassificacoes.size() != 1){
            List<Integer> novasOpcoes = new ArrayList<>();
            for(int i = 0; i < opcoesClassificacoes.size(); i++){
                if(opcoesClassificacoes.get(i) != classUsada){
                    novasOpcoes.add(opcoesClassificacoes.get(i));
                }
            }
            opcoesClassificacoes.clear();
            opcoesClassificacoes = novasOpcoes;
            cmbClassificacao.getItems().clear();
            cmbClassificacao.getItems().setAll(opcoesClassificacoes);
        }
        else{
            terminou = true;
            cmbClassificacao.setDisable(true);
            btnConfirmarClassificacao.setDisable(true);
            tabelaColaboradores.requestFocus();
            AlertsUI.criarAlerta(Alert.AlertType.WARNING,
                    MainApp.TITULO_APLICACAO,"A seriação está concluída.",
                    "Deseja adicionar colaboradores que tenham participado nesta" +
                            " seriação?").show();
        }
    }
  
    /**
     * Pede ao usuário confirmar se a classificação dada a uma candidatura pode ser
     * confirmada
     * @param event
     */
    @FXML
    public void atribuirClassificacao(ActionEvent event) {

        btnConfirmarClassificacao.requestFocus();
    }

    /**
     * Adiciona a classificação de uma candidatura na tabela
     * @param event
     * @throws SQLException
     */
    @FXML
    public void confirmarClassificacao(ActionEvent event) throws SQLException{
        int posicao = cmbClassificacao.getSelectionModel().getSelectedItem();
        int idCandidatura = tabelaClassificacao.getSelectionModel().getSelectedItem().getIdCandidatura();
        if(tabelaClassificacao.getSelectionModel().getSelectedItem().getClassificacao() == 0){
            updateOpcoesClassificacao(posicao);
            updateTabelaCandidaturas(idCandidatura, posicao);
        }
        else{
            AlertsUI.criarAlerta(Alert.AlertType.WARNING,
                    MainApp.TITULO_APLICACAO,"Classificação já atribuída!",
                    "Por favor, escolha outra classificação!").show();
        }
        tabelaClassificacao.requestFocus();
    }

    /**
     * Adiciona um colaborador como participante na seriação
     * @param event
     * @throws SQLException
     */
    @FXML
    public void adicionarColaborador (ActionEvent event) throws SQLException{
        String emailColabAdd = tabelaColaboradores.getSelectionModel().getSelectedItem().getEmail();
        if(tabelaColaboradores.getSelectionModel().getSelectedItem().getSelecao().equals("N")){
            updateTabelaColaboradores(emailColabAdd);
            colaboradoresParticipantes.add(emailColabAdd);
        }else{ AlertsUI.criarAlerta(Alert.AlertType.WARNING,
                MainApp.TITULO_APLICACAO,"Colaborador já selecionado!",
                "Por favor, escolha outro colaborador!").show();
        }
        tabelaColaboradores.requestFocus();
    }

    /**
     * Cria uma lista de classificações conforme o preenchimento da tabela de classificações, que serve como parâmetro
     * para o registo da seriação
     */
    public void listarClassificacoes(){
        for(int i = 0; i < listaCandidaturas.size(); i++){
           Classificacao classificacao = new Classificacao(listaCandidaturas.get(i).getClassificacao(),
           listaCandidaturas.get(i).getIdCandidatura());
           classificacoes.add(classificacao);
        }
    }

    /**
     * Faz o registo da seriação
     * @param event
     * @throws SQLException
     */
    @FXML
    public void registarSeriacao (ActionEvent event) throws SQLException {
        if (terminou) {
            listarClassificacoes();
            if(colaboradoresParticipantes.size() > 0){
                seriacaoConcluida = seriarAnuncioController.seriar(idAnuncio, classificacoes, colaboradoresParticipantes);
            }
            else{
                seriacaoConcluida = seriarAnuncioController.seriar(idAnuncio, classificacoes);
            }
        }
        else {
            AlertsUI.criarAlerta(Alert.AlertType.WARNING,
                    MainApp.TITULO_APLICACAO,"A seriação ainda não está concluída.",
                    "Por favor, termine de classificar as candidaturas!").show();

        }
        if(seriacaoConcluida){
            btnSeriacao.setDisable(true);
            btnVoltar.requestFocus();
        }
    }

    /**
     * Volta para a página anterior desde que todas as candidaturas tenham sido
     * já classificadas
     * @param event
     * @throws SQLException
     */
    @FXML
    public void voltar(ActionEvent event) throws SQLException {
        if (seriacaoConcluida) {
            colaboradorLogadoUI.updateDataSeriacao();
            btnVoltar.getScene().getWindow().hide();
        }
        else {
            AlertsUI.criarAlerta(Alert.AlertType.WARNING,
                    MainApp.TITULO_APLICACAO,"A seriação não foi registada.",
                    "Verifique se clasificou todas as candidaturas!").show();
        }
    }

    }
