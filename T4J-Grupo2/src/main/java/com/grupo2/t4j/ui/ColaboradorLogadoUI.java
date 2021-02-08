package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.files.FicheiroRepositorioAreaActividade;
import com.grupo2.t4j.files.FileChooserT4J;
import com.grupo2.t4j.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ColaboradorLogadoUI implements Initializable {

    private RegistarCategoriaController registarCategoriaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarTarefaController registarTarefaController;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    private RegistarCaracterizacaoCTController registarCaracterizacaoCTController;
    private AutenticacaoController autenticacaoController;
    private StartingPageUI startingPageUI;
    private Scene sceneStartingPage;
    private Stage adicionarStage;

    private FicheiroRepositorioAreaActividade ficheiroRepositorioAreaActividade;

    private static final String CABECALHO_IMPORTAR = "Importar Lista.";

    @FXML Button btnLogout;
    @FXML Button btnCancelar;
    @FXML Button btnImportAreaActividade;
    @FXML ComboBox<AreaActividade> cmbAreaActividadeListaTarefas;
    @FXML ComboBox<AreaActividade> cmbAreaActividadeEspecificarTarefa;
    @FXML ComboBox<Categoria> cmbCategoriaTarefaListaTarefas;
    @FXML ComboBox<Categoria> cmbCategoriaTarefaEspecificarTarefa;
    @FXML ListView<Tarefa> listViewTarefas;
    @FXML ListView<CaracterizacaoCT> listViewCaracterizacaoCT;
    @FXML TextField txtReferencia;
    @FXML TextField txtDesignacao;
    @FXML TextArea txtDescInformal;
    @FXML TextArea txtDescTecnica;
    @FXML TextField txtEstimativaDuracao;
    @FXML TextField txtEstimativaCusto;

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCategoriaController = new RegistarCategoriaController();
        registarTarefaController = new RegistarTarefaController();
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();
        registarCaracterizacaoCTController = new RegistarCaracterizacaoCTController();
        autenticacaoController = new AutenticacaoController();

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);
        adicionarStage.setResizable(false);

        //tab Lista de Tarefas
        cmbAreaActividadeListaTarefas.getItems().setAll(registarAreaActividadeController.getAreasActividade());
        cmbCategoriaTarefaListaTarefas.getItems().setAll(
                registarCategoriaController.getCategoriasByAreaActividade(
                        cmbAreaActividadeListaTarefas.getSelectionModel().getSelectedItem()));

        ListView<Tarefa> listViewTarefas = new ListView<>();
        listViewTarefas.getItems().addAll(registarTarefaController.getListTarefas());

        //tab Especificar Tarefa
        cmbAreaActividadeEspecificarTarefa.getItems().setAll(registarAreaActividadeController.getAreasActividade());
        cmbCategoriaTarefaEspecificarTarefa.getItems().setAll(
                registarCategoriaController.getCategoriasByAreaActividade(
                        cmbAreaActividadeEspecificarTarefa.getSelectionModel().getSelectedItem()));

        ListView<CaracterizacaoCT> listViewCaracterizacaoCT = new ListView<>();
        listViewCaracterizacaoCT.getItems().addAll(registarCaracterizacaoCTController.getCaracterizacaoCTSByCompetenciaTecnica(
                registarCompetenciaTecnicaController.getCompetenciasTecnicasByAreaActividade(
                        cmbAreaActividadeEspecificarTarefa.getSelectionModel().getSelectedItem())));

    }

    public void registarTarefa(ActionEvent actionEvent) {
        try {
            AreaActividade areaActividade = registarAreaActividadeController.getAreaActividadeByCodigo(
                    cmbAreaActividadeEspecificarTarefa.getSelectionModel().getSelectedItem().getCodigo());
            ArrayList<Categoria> categoriasTarefa =
                    registarCategoriaController.getCategoriasByAreaActividade(areaActividade);

            List<CaracterizacaoCT> caracterizacaoCTS = registarCaracterizacaoCTController.getCaracterizacaoCTSByCompetenciaTecnica(
                    registarCompetenciaTecnicaController.getCompetenciasTecnicasByAreaActividade(areaActividade));

            Tarefa tarefa = registarTarefaController.novaTarefa(areaActividade, categoriasTarefa, caracterizacaoCTS,
                    txtReferencia.getText(),
                    txtDesignacao.getText(),
                    txtDescInformal.getText(),
                    txtDescTecnica.getText(),
                    Integer.parseInt(txtEstimativaDuracao.getText()),
                    Double.parseDouble(txtEstimativaCusto.getText()));

        }
        catch (IllegalArgumentException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    iae.getMessage()).show();

        }
    }

    @FXML
    void navigateStartingPage(ActionEvent event) {
        try {
            FXMLLoader loaderStartingPage = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/StartingPageScene.fxml"));
            Parent rootStartingPage = loaderStartingPage.load();
            sceneStartingPage = new Scene(rootStartingPage);

            Window window = btnLogout.getScene().getWindow();
            window.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                            MainApp.TITULO_APLICACAO,
                            "Confirmação da acção",
                            "Tem a certeza que pretende terminar a sessão??");

                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        windowEvent.consume();
                    }
                }
            });
            window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));

            adicionarStage.setScene(sceneStartingPage);
            adicionarStage.setTitle(MainApp.TITULO_APLICACAO);
            adicionarStage.show();

        }
        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

    public void logout(ActionEvent actionEvent) {
        boolean logout = autenticacaoController.logout();
        if (logout) {
            navigateStartingPage(actionEvent);
            Plataforma.getInstance().resetUserAPI();
        }
        else {
            Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    "Não foi possível terminar a sessão.");
        }
    }

    public void cancelarAction(ActionEvent actionEvent) {
        this.txtReferencia.clear();
        this.txtDesignacao.clear();
        this.txtDescInformal.clear();
        this.txtDescTecnica.clear();
        this.txtEstimativaDuracao.clear();
        this.txtEstimativaCusto.clear();
        this.listViewCaracterizacaoCT.setItems(null);
        this.cmbCategoriaTarefaEspecificarTarefa.setItems(null);
        this.cmbAreaActividadeEspecificarTarefa.setItems(null);
    }

    ////////////////// Ficheiros //////////////////

    public void importAreaActividade(ActionEvent actionEvent) {
        String descricao, extensao;

        descricao = "Ficheiro Area de Actividade";
        extensao = "*.txt";
        FileChooser fileChooser = FileChooserT4J.criarFileChooserT4J(descricao, extensao);
        File ficheiroImportar = fileChooser.showOpenDialog(btnImportAreaActividade.getScene().getWindow());

        if(ficheiroImportar != null) {
            int numeroAreasImportadas = 0;
            numeroAreasImportadas = registarAreaActividadeController.desserializar(ficheiroImportar);

            if(numeroAreasImportadas > 0) {
                cmbAreaActividadeEspecificarTarefa.getItems().setAll(registarAreaActividadeController.getAreasActividade());

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                        String.format("%d área(s) de actividade importada(s).", numeroAreasImportadas)).show();
            }
            else {
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                        "Ficheiro sem áreas de actividade para importar!").show();

            }
        }
        else {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                    "Não foi seleccionado nenhum ficheiro!").show();
        }
    }


}
