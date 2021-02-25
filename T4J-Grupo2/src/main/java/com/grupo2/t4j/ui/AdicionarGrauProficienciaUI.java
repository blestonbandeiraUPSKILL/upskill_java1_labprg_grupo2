package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarGrauProficienciaController;
import com.grupo2.t4j.model.GrauProficiencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class AdicionarGrauProficienciaUI implements Initializable {
    
    @FXML Button btnAdicionarGrau;
    @FXML Button btnConcluir;
    @FXML ListView<GrauProficiencia> listViewGrausAdicionados;
    @FXML TextField txtDesignacao;
    @FXML TextField txtValor;
    @FXML TextField txtCodigoGP;
    
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
        try {
            registarGrauProficienciaController = new RegistarGrauProficienciaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }    

    /*public void concluirAction (ActionEvent actionEvent){
        
        registarGrauProficienciaController = new RegistarGrauProficienciaController();
    } */   
    
    @FXML
    public void adicionarGrauAction (ActionEvent actionEvent){
        


    }
    
    @FXML
    public void concluirAction (ActionEvent actionEvent){
        /*this.txtValor.clear();
        this.txtDesignacao.clear();*/
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

    }
    
    
}
