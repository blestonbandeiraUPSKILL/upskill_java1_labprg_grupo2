package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.EfectuarCandidaturaController;
import com.grupo2.t4j.controller.RegistarAnuncioController;
import com.grupo2.t4j.model.Anuncio;
import com.grupo2.t4j.model.Data;
import com.grupo2.t4j.model.Tarefa;
import com.grupo2.t4j.model.TipoRegimento;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FreelancerLogadoUI implements Initializable {
    
     @FXML
    private TableColumn<Anuncio, Data> inicioCandidatura;

    @FXML
    private TableColumn<Anuncio, Data> fimCandidatura;

    @FXML
    private TableColumn<Tarefa, String> tarefa;

    @FXML
    private TableColumn<Anuncio, Data> fimSeriacao;

    @FXML
    private TableView<Anuncio> tableAnuncios;

    @FXML
    private TableColumn<Anuncio, Data> inicioPublicitacao;

    @FXML
    private TableColumn<Anuncio, Data> fimPublicitacao;

    @FXML
    private TableColumn<TipoRegimento, String> tipoRegimento;

    @FXML
    private TableColumn<Anuncio, Data> inicioSeriacao;

    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    
    private RegistarAnuncioController registarAnuncioController;
    private EfectuarCandidaturaController efectuarCandidaturaController;
    
    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
        
        
        tarefa.setCellValueFactory(new PropertyValueFactory<>("idTarefa"));
        inicioPublicitacao.setCellValueFactory(new PropertyValueFactory<>("dtInicioPublicitacao"));
        fimPublicitacao.setCellValueFactory(new PropertyValueFactory<>("dtFimPublicitacao"));
        inicioCandidatura.setCellValueFactory(new PropertyValueFactory<>("dtInicioCandidatura"));
        fimCandidatura.setCellValueFactory(new PropertyValueFactory<>("dtFimCandidatura"));
        inicioSeriacao.setCellValueFactory(new PropertyValueFactory<>("dtInicioSeriacao"));
        fimSeriacao.setCellValueFactory(new PropertyValueFactory<>("dtFimSeriacao"));

        try {
            updateTableViewAnuncio();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
    @FXML
    void verAnuncioAction(ActionEvent event) {

    }
    
    public void updateTableViewAnuncio() throws SQLException {
        ObservableList<Anuncio> anunciosElegiveis = FXCollections.observableArrayList(efectuarCandidaturaController.findAnunciosElegiveis(startingPageUI.txtEmailLogin.getText()));
        tableAnuncios.setItems(anunciosElegiveis);
    }
}
