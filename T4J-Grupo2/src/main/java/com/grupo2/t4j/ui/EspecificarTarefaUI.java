package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.controller.RegistarCategoriaController;
import com.grupo2.t4j.controller.RegistarTarefaController;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.Categoria;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class EspecificarTarefaUI implements Initializable {

    private ColaboradorLogadoUI colaboradorLogadoUI;
    private RegistarTarefaController registarTarefaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarCategoriaController registarCategoriaController;

    @FXML TextField txtReferencia;
    @FXML TextField txtDesignacao;
    @FXML TextField txtDescInformal;
    @FXML TextField txtDescTecnica;
    @FXML TextField txtEstimativaDuracao;
    @FXML TextField txtEstimativaCusto;
    @FXML ListView<CaracterizacaoCT> listViewCaracterizacaoCT;
    @FXML ComboBox<Categoria> cmbCategoriaTarefa;
    @FXML ComboBox<AreaActividade> cmbAreaActividade;

    public void associarParentUI(ColaboradorLogadoUI colaboradorLogadoUI) {
        this.colaboradorLogadoUI = colaboradorLogadoUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registarTarefaController = new RegistarTarefaController();
        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCategoriaController = new RegistarCategoriaController();

        try {
            cmbAreaActividade.getItems().setAll(registarAreaActividadeController.getAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        cmbAreaActividade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateCmbCategoriasTarefaRegisto(event);
            }
        });

        javafx.scene.control.ListView<CaracterizacaoCT> listViewCaracterizacaoCT = new javafx.scene.control.ListView<>();
        cmbCategoriaTarefa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateListViewCaracterizacaoCTS(event);
            }
        });

    }

    public void updateCmbCategoriasTarefaRegisto(ActionEvent actionEvent) {
        List<Categoria> listaCategoriasTarefa =
                registarCategoriaController.findByAreaActividade(
                        cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo());

        cmbCategoriaTarefa.getItems().addAll(listaCategoriasTarefa);
    }


    public void updateListViewCaracterizacaoCTS(ActionEvent actionEvent){

        listViewCaracterizacaoCT.getItems().setAll(
                cmbCategoriaTarefa.getSelectionModel().getSelectedItem().getCompTecnicasCaracter());
    }

    public void registarTarefa(ActionEvent actionEvent) {
        try {
            boolean adicionou = registarTarefaController.registarTarefa(
                    cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo(),
                    cmbCategoriaTarefa.getSelectionModel().getSelectedItem().getCodigoCategoria(),
                    txtReferencia.getText(),
                    txtDesignacao.getText(),
                    txtDescInformal.getText(),
                    txtDescTecnica.getText(),
                    Integer.parseInt(txtEstimativaDuracao.getText()),
                    Double.parseDouble(txtEstimativaCusto.getText()));

            if (adicionou){
                colaboradorLogadoUI.listViewTarefas.updateListViewTarefas();
            }
            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Registar Competência Técnica.",
                    adicionou ? "Competencia Tecnica registada com sucesso."
                            : "Não foi possível registar a Competência Técncia.").show();

        }
        catch (IllegalArgumentException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    iae.getMessage()).show();

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
        this.cmbCategoriaTarefa.setItems(null);
        this.cmbAreaActividade.setItems(null);
    }

    public void logout(ActionEvent actionEvent) {
    }


}
