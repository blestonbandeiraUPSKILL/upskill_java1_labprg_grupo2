package com.grupo2.t4j.ui;

import com.grupo2.t4j.model.Anuncio;
import com.grupo2.t4j.model.Data;
import com.grupo2.t4j.model.Tarefa;
import com.grupo2.t4j.model.TipoRegimento;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private TableView<Anuncio> tableCandidaturas;

    @FXML
    private TableColumn<Anuncio, Data> inicioPublicitacao;

    @FXML
    private TableColumn<Anuncio, Data> fimPublicitacao;

    @FXML
    private TableColumn<TipoRegimento, String> tipoRegimento;

    @FXML
    private TableColumn<Anuncio, Data> inicioSeriacao;

    private StartingPageUI startingPageUI;
    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void verAnuncioAction(ActionEvent event) {

    }
}
