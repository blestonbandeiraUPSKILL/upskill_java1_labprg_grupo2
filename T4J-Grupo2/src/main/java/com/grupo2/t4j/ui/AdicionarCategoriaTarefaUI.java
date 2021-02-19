package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdicionarCategoriaTarefaUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private Scene sceneCaracterizarCompetenciaTecnica;
    private Stage adicionarStage;
    private Scene sceneStartingPage;
    private RegistarCategoriaController registarCategoriaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    private RegistarGrauProficienciaController registarGrauProficienciaController;
    private RegistarCaracterizacaoCTController registarCaracterizacaoCTController;
    private List<CaracterizacaoCT> caracterizacaoCTS;

    @FXML TextField txtDescricaoBreve;
    @FXML TextField txtCodigo;
    @FXML TextField txtCodigoCCT;
    @FXML TextArea txtDescricaoDetalhada;
    @FXML Button btnConfirmar;
    @FXML Button btnCancelar;
    @FXML Button btnAddCompTecCat;
    @FXML ComboBox<AreaActividade> cmbAreaActividade;
    @FXML ComboBox<GrauProficiencia> cmbGrauProficiencia;
    @FXML ComboBox<Obrigatoriedade> cmbObrigatoriedade;
    @FXML ComboBox<CompetenciaTecnica> cmbCompetenciaTecnica;
    @FXML ListView<CaracterizacaoCT> listViewCompTecCat;

    public void associarParentUI(AdministrativoLogadoUI administrativoLogadoUI) {
        this.administrativoLogadoUI = administrativoLogadoUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCategoriaController = new RegistarCategoriaController();
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();
        registarGrauProficienciaController = new RegistarGrauProficienciaController();
        registarCaracterizacaoCTController = new RegistarCaracterizacaoCTController();

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        //cmbGrauProficiencia.getItems().setAll(registarGrauProficienciaController.findByCompetenciaTecnica());
        cmbObrigatoriedade.getItems().setAll(Obrigatoriedade.values());

        cmbAreaActividade.getItems().addAll(registarAreaActividadeController.getAll());

        cmbAreaActividade.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               updateCmbCompetenciasTecnicas(event);

           }
        });
        
        cmbCompetenciaTecnica.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                updateCmbGrauProficiencia(event);
        }
        });
    }

    public void updateCmbCompetenciasTecnicas(ActionEvent actionEvent) {
        cmbCompetenciaTecnica.getItems().addAll(
                registarCompetenciaTecnicaController.findByAreaActividade(
                cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo()));
    }
    
    public void updateCmbGrauProficiencia(ActionEvent actionEvent) {

        cmbGrauProficiencia.getItems().addAll(registarGrauProficienciaController.findByCompetenciaTecnica(
                cmbCompetenciaTecnica.getSelectionModel().getSelectedItem().getCodigo()));
    }
    
    

    public void cancelarAction(ActionEvent event) {
        Window window = btnCancelar.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que quer voltar à página anterior, cancelando o actual registo?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                }
            }
        });
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    void registarCategoriaAction(ActionEvent event) {
        try {
            boolean adicionou = registarCategoriaController.registarCategoria(
                    txtCodigo.getText().trim(),
                    txtDescricaoBreve.getText().trim(),
                    txtDescricaoDetalhada.getText().trim(),
                    cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo(),
                    caracterizacaoCTS
            );

            if(adicionou) {
                administrativoLogadoUI.listaCategorias.getItems().addAll(registarCategoriaController.getAll());
            }

            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Registar Categoria de Tarefa.",
                    adicionou ? "Categoria de Tarefa registada com sucesso."
                            : "Não foi possível registar a Categoria de Tarefa.").show();

            closeAddCatgoriaTarefa(event);
        }
        catch (IllegalArgumentException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    iae.getMessage()).show();

        }
    }

    private void closeAddCatgoriaTarefa(ActionEvent actionEvent) {
        this.txtDescricaoBreve.clear();
        this.txtDescricaoDetalhada.clear();
        this.listViewCompTecCat.getItems().removeAll();

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @FXML
    public List<CaracterizacaoCT> addCompetenciaTecnica2CCTS() {

       boolean adicionou = registarCaracterizacaoCTController.registarCaracterizacaoCTS(
               txtCodigoCCT.getText(),
               cmbGrauProficiencia.getValue().getCodigoGP(),
               cmbObrigatoriedade.getValue(),
               cmbCompetenciaTecnica.getValue().getCodigo());

       if(adicionou) {
           listViewCompTecCat.getItems().add(registarCaracterizacaoCTController.findByCodigo(txtCodigoCCT.getText()));
           txtCodigoCCT.clear();
           cmbGrauProficiencia.getSelectionModel().clearSelection();
           cmbGrauProficiencia.getItems().clear();
           cmbObrigatoriedade.getSelectionModel().clearSelection();
           cmbCompetenciaTecnica.getSelectionModel().clearSelection();
           cmbAreaActividade.setDisable(true);
       }

        return caracterizacaoCTS;
    }


}
