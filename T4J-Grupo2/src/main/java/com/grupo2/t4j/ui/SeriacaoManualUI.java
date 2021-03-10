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
import com.grupo2.t4j.controller.RegistarTarefaController;
import com.grupo2.t4j.controller.SeriarAnuncioController;
import com.grupo2.t4j.domain.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;

public class SeriacaoManualUI implements Initializable{

    @FXML private TextField txtIdAnuncio;
    
    @FXML private TableView<TabelaFreelancerClassificacao> tabelaClassificacao;
    
    private List<TabelaFreelancerClassificacao> listaCandidaturas = new ArrayList<>();
    
    @FXML private TableColumn<Object, Object> colunaEmail;
     
    @FXML private TableColumn<Object, Object> colunaClassificacao;

    @FXML private ComboBox<Integer> cmbClassificacao;
    
    @FXML private Button btnConfirmarClassificacao;
    
    @FXML private TableView<TabelaColaboradorAdicional> tabelaColaboradores;
    
    private List<TabelaColaboradorAdicional> colaboradoresOpcionais = new ArrayList<>();
    
    @FXML private TableColumn<Object, Object> colunaColaborador;
    
    @FXML private TableColumn<Object, Object> colunaParticipante;
    
    @FXML private Button btnAdicionarColaborador;

    @FXML private Button btnConcluirSeriacao;

    @FXML private Button btnVoltar;
    
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
        
        try{
            transferData();
        }        
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        
        
    }
    
    public void transferData() throws SQLException {
        
        emailColaborador = colaboradorLogadoUI.getEmailColaborador();
        nifOrganizacao = colaboradorLogadoUI.getNifOrganizacao();
        idAnuncio = colaboradorLogadoUI.getIdAnuncio();
        
        boolean seriacaoCriada = seriarAnuncioController.saveSeriacao(idAnuncio);
        if(seriacaoCriada){
            idSeriacao = seriarAnuncioController.getIdSeriacao(idAnuncio);
        }
        
        txtIdAnuncio.setText(Integer.toString(idAnuncio));
        criaTabelaCandidaturas();
        criaTabelaColaboradoresOpcionais();
        criarOpcoesClassificacao();
        
        
        
        
        
        
        
    }
    
    public void criaTabelaCandidaturas() throws SQLException{
        List<Candidatura> candidaturas = seriarAnuncioController.getAllByIdAnuncio(idAnuncio);
        List<Candidatura> candidaturasOrd = seriarAnuncioController.ordenarByIdCandidatura(candidaturas);
        qtdCand = candidaturas.size();
        for(int i = 0; i < qtdCand; i++){
            TabelaFreelancerClassificacao cellCandidatura = new TabelaFreelancerClassificacao(candidaturasOrd.get(i).getEmailFreelancer());
            listaCandidaturas.add(cellCandidatura);
        }
        tabelaClassificacao.getItems().setAll(listaCandidaturas);
        preencherTabelaCandidaturas();
    }
    
    public void preencherTabelaCandidaturas () {
        colunaEmail.setCellValueFactory( new PropertyValueFactory<>("emailFreelancer"));
        colunaClassificacao.setCellValueFactory( new PropertyValueFactory<>("classificacao"));       
    }
    
    public void updateTabelaCandidaturas(int classificacao){
        
    }
    
    public void criaTabelaColaboradoresOpcionais() throws SQLException{
        List<String> colaboradores = seriarAnuncioController.getAllEmailsAlfByOrganizacao(nifOrganizacao);
        for(int i = 0; i < colaboradores.size(); i++){
            if(!colaboradores.get(i).equals(emailColaborador)){
                TabelaColaboradorAdicional cellColaborador = new TabelaColaboradorAdicional(colaboradores.get(i), "N");
                colaboradoresOpcionais.add(cellColaborador);
            }
        }
        tabelaColaboradores.getItems().setAll(colaboradoresOpcionais);
        preencherTabelaColaboradores ();
    }
    
    public void preencherTabelaColaboradores () {
        colunaColaborador.setCellValueFactory( new PropertyValueFactory<>("emailColaboradorAdd"));
        colunaParticipante.setCellValueFactory( new PropertyValueFactory<>("selecao"));       
    }
    
    public void updateTabelaColaboradores(String emailColabAdd){
        for(int i = 0; i < colaboradoresOpcionais.size(); i++){
            if(colaboradoresOpcionais.get(i).getEmail().equals(emailColabAdd)){
                colaboradoresOpcionais.get(i).setSelecao("S");
            }
        }
    }
    
    public void criarOpcoesClassificacao(){
        for(int i = 1; i < listaCandidaturas.size() + 1; i++){
            classificacoes.add(i);
        }
        cmbClassificacao.getItems().setAll(classificacoes);
    }
    
    public List<Integer> updateOpcoesClassificacao(int tam){
        List<Integer> lista = new ArrayList<>();
        for(int i = 1; i < tam + 1; i++){
            lista.add(i);
        }
        return lista;
    }
    
    @FXML 
    public void registarSeriacaoManual(ActionEvent event) {

    }

    @FXML
    public void atribuirClassificacao(ActionEvent event) {
        int classUsada = cmbClassificacao.getSelectionModel().getSelectedItem();
        classificacoes.remove(classUsada-1);
        cmbClassificacao.getItems().setAll(classificacoes);
    }

    @FXML
    public void registarClassificacao(ActionEvent event) {

    }
    
    @FXML
    public void registarColaborador (ActionEvent event) throws SQLException{
        String emailColabAdd = tabelaColaboradores.getSelectionModel().getSelectedItem().getEmail();
        boolean adicionouColab = seriarAnuncioController.update(emailColabAdd, idSeriacao);
        if(adicionouColab){
            updateTabelaColaboradores(emailColabAdd);
        }
    }
    
    @FXML
    public void voltar(ActionEvent event) {
         btnVoltar.getScene().getWindow().hide();
    }
}
