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
import com.grupo2.t4j.domain.Candidatura;
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

public class SeriacaoManualUI implements Initializable{

    @FXML private TextField txtIdAnuncio;
    
    @FXML private TableView<?> tabelaClassificacao;
    
    @FXML private TableColumn<Object, Object> colunaEmail;
     
    @FXML private TableColumn<Object, Object> colunaClassificacao;

    @FXML private ComboBox<Integer> cmbClassificacao;
    
    @FXML private Button btnConfirmarClassificacao;
    
    @FXML private TableView<String> tabelaColaboradores;
    
    @FXML private TableColumn<Object, Object> colunaColaborador;
    
    @FXML private TableColumn<Object, Object> colunaParticipante;
    
    @FXML private Button btnAdicionarColaborador;

  
    @FXML private Button btnConcluirSeriacao;

    @FXML private Button btnVoltar;
    
    private List<Integer> classificacoes = new ArrayList<>();
    private int tamanho;
    private ColaboradorLogadoUI colaboradorLogadoUI;
    private SeriarAnuncioController seriarAnuncioController;
    private Stage adicionarStage;
    
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
        
        cmbClassificacao.getItems().setAll(classificacoes);
    }
    
    public void transferData() throws SQLException {
        int idAnuncio = colaboradorLogadoUI.getIdAnuncio();
        List<Candidatura> candidaturas = seriarAnuncioController.getAllByIdAnuncio(idAnuncio);
        tamanho = candidaturas.size();
        criarOpcoesClassificacao();
        boolean seriacaoCriada = seriarAnuncioController.saveSeriacao(idAnuncio);
        if(seriacaoCriada){
            int idSeriacao = seriarAnuncioController.getIdSeriacao(idAnuncio);
        }
        txtIdAnuncio.setText(Integer.toString(idAnuncio));
    }
    
    public List<Integer> criarOpcoesClassificacao(){
        for(int i = 1; i < tamanho + 1; i++){
            classificacoes.add(i);
        }
        return classificacoes;
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
    public void voltar(ActionEvent event) {
         btnVoltar.getScene().getWindow().hide();
    }
}
