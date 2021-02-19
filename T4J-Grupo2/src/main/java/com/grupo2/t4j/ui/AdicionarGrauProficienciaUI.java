package com.grupo2.t4j.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import com.grupo2.t4j.controller.RegistarGrauProficienciaController;
import com.grupo2.t4j.model.GrauProficiencia;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
    private ListView<GrauProficiencia> listViewGrausAdicionados;

    @FXML
    private TextField txtDesignacao;

    @FXML
    private Button btnConcluir;

    @FXML
    private TextField txtValor;
    
    private List<GrauProficiencia> listaGrausAplicaveis;
    
    private AdicionarCompetenciaTecnicaUI adicionarCompetenciaTecnicaUI;
    private RegistarGrauProficienciaController registarGrauProficienciaController;
    
    public void associarParentUI(AdicionarCompetenciaTecnicaUI adicionarCompetenciaTecnicaUI) {
        this.adicionarCompetenciaTecnicaUI = adicionarCompetenciaTecnicaUI;
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registarGrauProficienciaController = new RegistarGrauProficienciaController();

    }    
    
    
   
    
    /*public void concluirAction (ActionEvent actionEvent){
        
        registarGrauProficienciaController = new RegistarGrauProficienciaController();
    } */   
    
    @FXML
    public void adicionarGrauAction (ActionEvent actionEvent){
        
        try{
            boolean adicionou = registarGrauProficienciaController.registarGrauProficiencia(Integer.parseInt(txtValor.getText()),
                    txtDesignacao.getText(), adicionarCompetenciaTecnicaUI.txtCodigo.getText());

            if(adicionou) {
                listViewGrausAdicionados.getItems().add(registarGrauProficienciaController.findByValor(Integer.parseInt(txtValor.getText()),adicionarCompetenciaTecnicaUI.txtCodigo.getText() ));
                txtValor.clear();
                txtDesignacao.clear();
            }

            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Registar Grau de Proficiência.",
                    adicionou ? "Grau de Proficiência registado com sucesso."
                                : "Não foi possível registar o Grau de Proficiência.").show();

            //concluirAction(actionEvent);

        }
        catch (IllegalArgumentException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    iae.getMessage()).show();
        
        }
        
        
        GrauProficiencia grauProficiencia = new GrauProficiencia(
        Integer.parseInt(txtValor.getText()), txtDesignacao.getText());
        listaGrausAplicaveis.add(grauProficiencia);
        
    }
    
    @FXML
    public void concluirAction (ActionEvent actionEvent){
        this.txtValor.clear();
        this.txtDesignacao.clear();
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

    }
    
    
}
