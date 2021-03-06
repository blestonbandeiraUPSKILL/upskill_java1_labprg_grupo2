/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.ui;

/**
 *
 * @author carol
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class ConsultarTarefaUI {

    @FXML
    private TextField txtEstimativaDuracao;

    @FXML
    private TextField txtReferencia;

    @FXML
    private TextArea txtDescInformal;

    @FXML
    private TextField txtDesignacao;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtEstimativaCusto;

    @FXML
    private TextArea txtDescTecnica;

    @FXML
    void voltar(ActionEvent event) {

    }
}
