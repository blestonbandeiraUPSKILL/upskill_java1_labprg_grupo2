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
import com.grupo2.t4j.controller.RegistarHabilitacaoAcademicaController;
import java.net.URL;
import java.util.ResourceBundle;
import com.grupo2.t4j.model.*;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;


public class AdicionarHabilitacaoAcademicaUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private RegistarHabilitacaoAcademicaController registarHabilitacaoAcademicaController;
    private Stage adicionarStage;
    
    @FXML private ComboBox<String> cmbNomeFreelancer;
    
    @FXML private ComboBox<String> cmbEmailFreelancer;

    @FXML private TextField txtGrau;

    @FXML private TextField txtDesignacao;

    @FXML private TextField txtInstituicao;

    @FXML private TextField txtMedia;
    
    @FXML private ListView<HabilitacaoAcademica> listaHabilitacaoFreelancer;
     
    @FXML private Button btnAddHabilitacao;
    
    @FXML private Button btnCancelar;
    
    @FXML private Button btnSair;

    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    /**
     * Initializes the controller (UI) class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        registarHabilitacaoAcademicaController = new RegistarHabilitacaoAcademicaController();
           
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);
        
         try {
            updateListViewHabilitacaoFreelancer();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }  
   
    @FXML
    void addHabilitacao(ActionEvent event) {
        try{                      
            boolean adicionou = registarHabilitacaoAcademicaController.registarHabilitacaoAcademica(
                    ("HB- " + cmbEmailFreelancer.getPromptText()), txtGrau.getText(), txtDesignacao.getText(),
                    txtInstituicao.getText(), Double.parseDouble(txtMedia.getText()));

            if(adicionou) {
                updateListViewHabilitacaoFreelancer();
            
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO, "Registar Habilitação Acadêmica.",
                    adicionou ? "Habilitação Acadêmica de Freelancer registada com sucesso."
                                : "Não foi possível registar a Habilitação Acadêmica.").show();
            }
        }
        catch (IllegalArgumentException | SQLException ex) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    ex.getMessage()).show();
        
        } 
    }

    @FXML
    public void cancelarAction(ActionEvent event) {
        this.txtGrau.clear();
        this.txtDesignacao.clear();
        this.txtInstituicao.clear();
        this.txtMedia.clear();  
    }

    @FXML
    public void sairAction(ActionEvent event) {
         Window window = btnSair.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que quer voltar à página anterior?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                }
            }
        });
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
    
    public void updateListViewHabilitacaoFreelancer() throws SQLException {
         listaHabilitacaoFreelancer.getItems().setAll(registarHabilitacaoAcademicaController.getAll());
    }
     
}
