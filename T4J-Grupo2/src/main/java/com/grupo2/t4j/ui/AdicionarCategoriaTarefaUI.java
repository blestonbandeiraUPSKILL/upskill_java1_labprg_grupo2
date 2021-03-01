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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
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
    @FXML TextField txtCodigoCategoria;
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

    /**
     * Initializes the controller (UI) class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCategoriaController = new RegistarCategoriaController();
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();
        try {
            registarGrauProficienciaController = new RegistarGrauProficienciaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        registarCaracterizacaoCTController = new RegistarCaracterizacaoCTController();

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        cmbObrigatoriedade.getItems().setAll(Obrigatoriedade.values());

        try {
            cmbAreaActividade.getItems().addAll(registarAreaActividadeController.getAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        cmbAreaActividade.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               try {
                   updateCmbCompetenciasTecnicas(event);
               } catch (SQLException exception) {
                   exception.printStackTrace();
               }

           }
        });
        
        cmbCompetenciaTecnica.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateCmbGrauProficiencia(event);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });


    }

    public void updateCmbCompetenciasTecnicas(ActionEvent actionEvent) throws SQLException {
        String codigoAreaActividade = cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo();
        cmbCompetenciaTecnica.getItems().setAll(
                registarCompetenciaTecnicaController.findByAreaActividade(codigoAreaActividade));
    }
    
    public void updateCmbGrauProficiencia(ActionEvent actionEvent) throws SQLException {
        String codigoCompetenciaTecnica = cmbCompetenciaTecnica.getSelectionModel().getSelectedItem().getCodigo();
        cmbGrauProficiencia.getItems().setAll(
                registarGrauProficienciaController.findByCompetenciaTecnica(codigoCompetenciaTecnica));
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

    private void closeAddCatgoriaTarefa(ActionEvent actionEvent) {
        this.txtDescricaoBreve.clear();
        this.txtDescricaoDetalhada.clear();
        this.listViewCompTecCat.getItems().removeAll();

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @FXML
    public void addCompetenciaTecnica2CCTS(ActionEvent actionEvent) throws SQLException {

        try {
            boolean adicionou = registarCaracterizacaoCTController.registarCaracterizacaoCTS(
                    txtCodigoCategoria.getText(),
                    cmbGrauProficiencia.getValue().getIdGrauProficiencia(),
                    cmbObrigatoriedade.getValue());

            if(adicionou){
                updateListViewCompTecCat(actionEvent);
                cmbCompetenciaTecnica.getSelectionModel().clearSelection();
                cmbGrauProficiencia.getSelectionModel().clearSelection();
                cmbGrauProficiencia.getItems().clear();
                cmbObrigatoriedade.getSelectionModel().clearSelection();

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Registar Caracterização de Competencia.",
                    "Caracterização efectuada com sucesso. Pode regressar à página anterior.").show();
            }
        }
        catch (IllegalArgumentException | SQLException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                MainApp.TITULO_APLICACAO,
                "Registar Caracterização de Competencia - Erro nos dados.",
                "Não foi possível registar a caracterização: " + iae.getMessage()).show();
        }


    }
    
    public void updateListViewCompTecCat(ActionEvent actionEvent) throws SQLException {
        listViewCompTecCat.getItems().add(
                registarCaracterizacaoCTController.findByCategoriaEGrau(
                    txtCodigoCategoria.getText(),
                    cmbGrauProficiencia.getValue().getIdGrauProficiencia()));


    }

    public void addCategoria(ActionEvent actionEvent) {
        try {
            boolean adicionou = registarCategoriaController.registarCategoria(
                    txtCodigoCategoria.getText().trim(),
                    txtDescricaoBreve.getText().trim(),
                    txtDescricaoDetalhada.getText().trim(),
                    cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo());

            if(adicionou) {
                administrativoLogadoUI.updateListViewCategoriasTarefa();
                cmbAreaActividade.setDisable(true);


            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                MainApp.TITULO_APLICACAO,
                "Registar Categoria de Tarefa.",
                "Categoria de Tarefa registada com sucesso. Pode adicionar os Graus de Proficiência aplicáveis.").show();

            }
        }
        catch (IllegalArgumentException | SQLException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                MainApp.TITULO_APLICACAO,
                "Registar Categoria de Tarefa - Erro nos dados.",
                "Não foi possível registar a Categoria de Tarefa: " + exception.getMessage()).show();

        }
    }
}
