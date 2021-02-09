package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.controller.RegistarCategoriaController;
import com.grupo2.t4j.controller.RegistarCompetenciaTecnicaController;
import com.grupo2.t4j.model.*;

import com.sun.javafx.collections.MappingChange;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.*;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdicionarCategoriaTarefaUI implements Initializable {

    private AdministrativoLogadoUI administrativoLogadoUI;
    private Scene sceneCaracterizarCompetenciaTecnica;
    private Stage adicionarStage;
    private Scene sceneStartingPage;
    private RegistarCategoriaController registarCategoriaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    private List<CaracterizacaoCT> caracterizacaoCTS;



    @FXML TextField txtDescricaoBreve;
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

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        cmbGrauProficiencia.getItems().setAll(GrauProficiencia.values());
        cmbObrigatoriedade.getItems().setAll(Obrigatoriedade.values());

        cmbAreaActividade.getItems().addAll(registarAreaActividadeController.getAreasActividade());

        cmbAreaActividade.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               updateCmbCompetenciasTecnicas(event);
           }
        });
    }

    public void updateCmbCompetenciasTecnicas(ActionEvent actionEvent) {
        List<CompetenciaTecnica> listaCompetenciasTecnicas =
                registarCompetenciaTecnicaController.getCompetenciasTecnicasByAreaActividade(
                cmbAreaActividade.getSelectionModel().getSelectedItem());

        cmbCompetenciaTecnica.getItems().addAll(listaCompetenciasTecnicas);
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

            AreaActividade areaActividade = registarAreaActividadeController.getAreaActividadeByCodigo(
                    cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo());

            Categoria categoria = registarCategoriaController.novaCategoriaTarefa(
                    txtDescricaoBreve.getText().trim(),
                    txtDescricaoDetalhada.getText().trim(),
                    areaActividade,
                    caracterizacaoCTS);

            boolean adicionou = registarCategoriaController.registarCategoria(categoria);

            if(adicionou) {
                administrativoLogadoUI.listaCategorias.getItems().add(categoria);
                
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

        CaracterizacaoCT caracterizacaoCT = new CaracterizacaoCT(
                cmbGrauProficiencia.getValue(),
                cmbObrigatoriedade.getValue(),
                cmbCompetenciaTecnica.getValue());

        caracterizacaoCTS = new ArrayList<>();
        caracterizacaoCTS.add(caracterizacaoCT);

        listViewCompTecCat.getItems().add(caracterizacaoCT);
        cmbGrauProficiencia.getSelectionModel().clearSelection();
        cmbObrigatoriedade.getSelectionModel().clearSelection();
        cmbCompetenciaTecnica.getSelectionModel().clearSelection();

        return caracterizacaoCTS;
    }


}
