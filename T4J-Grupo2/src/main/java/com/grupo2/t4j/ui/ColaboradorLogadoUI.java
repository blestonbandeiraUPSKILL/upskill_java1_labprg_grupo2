package com.grupo2.t4j.ui;

import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.CompetenciaTecnica;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ColaboradorLogadoUI implements Initializable {
    
    @FXML
    private ComboBox<AreaActividade> cmbAreaActividade;
    @FXML
    private ComboBox<Categoria> cmbCategoriaTarefa;
    @FXML
    private ListView<CompetenciaTecnica> listViewCompTec;
    @FXML
    private Button btnRegistarTarefa;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtReferencia;
    @FXML
    private TextField txtDesignacao;
    @FXML
    private TextArea txtDescricaoInformal;
    @FXML
    private TextArea txtDescricaoTecnica;
    @FXML
    private TextField txtDuracao;
    @FXML
    private TextField txtCusto;
    @FXML
    private ComboBox<AreaActividade> cmbArAct;
    @FXML
    private ComboBox<Categoria> cmbCategoria;
    @FXML
    private ListView<Tarefa> listViewTarefas;


    private StartingPageUI startingPageUI;

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    @FXML
    private void selectAreaActAction(ActionEvent event) {
    }

    @FXML
    private void selectCatTarAction(ActionEvent event) {
    }

    @FXML
    private void registarTarefaAction(ActionEvent event) {
    }

    @FXML
    private void CancelarAction(ActionEvent event) {
    }

    @FXML
    private void selectArAct(ActionEvent event) {
    }

    @FXML
    private void selectCategoriaAction(ActionEvent event) {
    }
}
