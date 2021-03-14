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

import com.grupo2.t4j.controller.SeriarAnuncioController;
import com.grupo2.t4j.domain.Candidatura;
import com.grupo2.t4j.domain.Colaborador;
import com.grupo2.t4j.domain.TabelaColaboradorAdicional;
import com.grupo2.t4j.domain.TabelaFreelancerClassificacao;
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
    private Stage adicionarStage;

    private int idAnuncio;
    private int idSeriacao;
    private int qtdCand;
    private String emailColaborador;
    private String nifOrganizacao;
    private List<Integer> classificacoes = new ArrayList<>();
    private ArrayList<String> colaboradoresParticipantes = new ArrayList<>();

    @FXML TextField txtIdAnuncio;
    @FXML Button btnAdicionarColaborador;
    @FXML Button btnConfirmarClassificacao;
    @FXML Button btnVoltar;


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
     *
     * @param colaboradorLogadoUI
     */
    public void associarParentUI(ColaboradorLogadoUI colaboradorLogadoUI) {
        this.colaboradorLogadoUI = colaboradorLogadoUI;
    }
    
    /**
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){

        seriarAnuncioController = new SeriarAnuncioController();
          
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);  
        
    }
    
    /**
     * Tranfere as informações da 
     * @throws SQLException
     */
    public void transferData() throws SQLException {
        
        emailColaborador = colaboradorLogadoUI.getEmailColaborador();
        nifOrganizacao = colaboradorLogadoUI.getNifOrganizacao();
        idAnuncio = colaboradorLogadoUI.getIdAnuncio();
        
        boolean seriacaoCriada = seriarAnuncioController.saveSeriacao(idAnuncio);
        if(seriacaoCriada){
            idSeriacao = seriarAnuncioController.getIdSeriacao(idAnuncio);
        }
        
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
        List<Candidatura> candidaturas = seriarAnuncioController.getAllByIdAnuncio(idAnuncio);
        
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
     *  Faz a atualização da tabela de candidaturas após a atribuição de uma classificação
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
        List<Colaborador> colaboradores = seriarAnuncioController.getAll(nifOrganizacao);
        
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
            classificacoes.add(i);
        }
        cmbClassificacao.getItems().setAll(classificacoes);
    }
    
    /**
     * Elimina uma opção de classificação de candidatura considerando uma posição
     * que já tenha sido atribuída a uma candidatura
     * @param classUsada
     */
    public void updateOpcoesClassificacao(int classUsada){
        classificacoes.remove(classUsada-1);
        cmbClassificacao.getItems().clear();
        cmbClassificacao.getItems().setAll(classificacoes);
    }
  
    /**
     * Pede ao usuário confirmar se a classificação dada a uma candidatura pode ser
     * registada
     * @param event
     */
    @FXML
    public void atribuirClassificacao(ActionEvent event) {
        btnConfirmarClassificacao.requestFocus();
    }

    /**
     * Regista a classificação de uma candidatura na BD
     * @param event
     * @throws SQLException
     */
    @FXML
    public void registarClassificacao(ActionEvent event) throws SQLException{
        int posicao = cmbClassificacao.getSelectionModel().getSelectedItem();
        int idCandidatura = tabelaClassificacao.getSelectionModel().getSelectedItem().getIdCandidatura();
        boolean registou = seriarAnuncioController.saveClassificacao(posicao, idSeriacao, idCandidatura);
        if(registou){
            updateOpcoesClassificacao(posicao);
            updateTabelaCandidaturas(idCandidatura, posicao);
        }
        tabelaClassificacao.requestFocus();
    }
    
    /**
     * Regista um colaborador adicional como participante na seriação
     * @param event
     * @throws SQLException
     */
    @FXML
    public void registarColaborador (ActionEvent event) throws SQLException{
        String emailColabAdd = tabelaColaboradores.getSelectionModel().getSelectedItem().getEmail();
        boolean adicionouColab = seriarAnuncioController.update(emailColabAdd, idSeriacao);
        if(adicionouColab){
            updateTabelaColaboradores(emailColabAdd);
        }
        tabelaColaboradores.requestFocus();
    }
    
    /**
     * Volta para a página anterior desde que todas as candidaturas tenham sido
     * já classificadas
     * @param event
     * @throws SQLException
     */
    @FXML
    public void voltar(ActionEvent event) throws SQLException{
        if(classificacoes.size() == 0){
            colaboradorLogadoUI.updateDataSeriacao();
            btnVoltar.getScene().getWindow().hide();
        }
        else{
            Window window = btnVoltar.getScene().getWindow();
            window.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                            MainApp.TITULO_APLICACAO,
                            "A seriação ainda não está concluída.",
                            "Por favor, termine de classificar as candidaturas!");
                    windowEvent.consume();
                }
            });

        }
    }
}
