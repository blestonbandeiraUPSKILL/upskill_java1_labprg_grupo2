/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.t4j.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author marta
 */
public class AdministrativoLogadoSceneController implements Initializable {

    @FXML
    private Button btn_addareaAtividade;
    @FXML
    private TextArea listaareasAtividade;
    @FXML
    private Button btn_addcategoriaTarefa;
    @FXML
    private TextArea listacategoriasTarefa;
    @FXML
    private Button btn_addcompetenciaTecnica;
    @FXML
    private TextArea listacompetenciasTecnicas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
