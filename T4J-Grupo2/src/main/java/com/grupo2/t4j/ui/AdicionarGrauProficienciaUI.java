package com.grupo2.t4j.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.grupo2.t4j.model.GrauProficiencia;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author acris
 */
public class AdicionarGrauProficienciaUI implements Initializable {
    
    @FXML
    private Button btnAdicionarGrau;

    @FXML
    private ListView<?> listViewGrausAdicionados;

    @FXML
    private TextField txtDesignacao;

    @FXML
    private Button btnConcluir;

    @FXML
    private TextField txtValor;
    
    private List<GrauProficiencia> listaGrausAplicaveis;
    
    private AdicionarCompetenciaTecnicaUI adicionarCompetenciaTecnicaUI;
    
    public void associarParentUI(AdicionarCompetenciaTecnicaUI adicionarCompetenciaTecnicaUI) {
        this.adicionarCompetenciaTecnicaUI = adicionarCompetenciaTecnicaUI;
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void adicionarGrauAction (ActionEvent actionEvent){
        GrauProficiencia grauProficiencia = new GrauProficiencia(
        Integer.parseInt(txtValor.getText()), txtDesignacao.getText());
    }
    
    @FXML
    public void concluirAction (ActionEvent actionEvent){
        
    }
    
    
}
