package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.controller.RegistarCategoriaController;
import com.grupo2.t4j.controller.RegistarColaboradorController;
import com.grupo2.t4j.controller.RegistarTarefaController;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.CaracterizacaoCT;
import com.grupo2.t4j.model.Categoria;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Window;
import javafx.stage.WindowEvent;


import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class EspecificarTarefaUI implements Initializable {

    private RegistarTarefaController registarTarefaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarCategoriaController registarCategoriaController;
    private RegistarColaboradorController registarColaboradorController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private Initializable parentUI;

    @FXML TextField txtReferencia;
    @FXML TextField txtDesignacao;
    @FXML TextField txtEstimativaDuracao;
    @FXML TextField txtEstimativaCusto;
    @FXML TextArea txtDescInformal;
    @FXML TextArea txtDescTecnica;
    @FXML ListView<CaracterizacaoCT> listViewCaracterizacaoCT;
    @FXML ComboBox<Categoria> cmbCategoriaTarefa;
    @FXML ComboBox<AreaActividade> cmbAreaActividade;
    @FXML Button btnCancelar;

    public void associarParentUI(Initializable parentUI) {
        this.parentUI = parentUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registarTarefaController = new RegistarTarefaController();
        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCategoriaController = new RegistarCategoriaController();
        gestaoUtilizadoresController = new GestaoUtilizadoresController();

        try {
            cmbAreaActividade.getItems().setAll(registarAreaActividadeController.getAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        cmbAreaActividade.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    updateCmbCategoriasTarefaRegisto(event);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });

        ListView<CaracterizacaoCT> listViewCaracterizacaoCT = new javafx.scene.control.ListView<>();
        cmbCategoriaTarefa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateListViewCaracterizacaoCTS(event);
            }
        });

    }

    public void updateCmbCategoriasTarefaRegisto(ActionEvent actionEvent) throws SQLException {
        List<Categoria> listaCategoriasTarefa =
                registarCategoriaController.findByAreaActividade(
                        cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo());

        cmbCategoriaTarefa.getItems().addAll(listaCategoriasTarefa);
    }


    public void updateListViewCaracterizacaoCTS(ActionEvent actionEvent){

        listViewCaracterizacaoCT.getItems().setAll(
                cmbCategoriaTarefa.getSelectionModel().getSelectedItem().getCompTecnicasCaracter());
    }

    public void registarTarefa(ActionEvent actionEvent) throws SQLException{
        try {
            boolean adicionou = registarTarefaController.registarTarefa(
                    cmbAreaActividade.getSelectionModel().getSelectedItem().getCodigo(),
                    cmbCategoriaTarefa.getSelectionModel().getSelectedItem().getCodigoCategoria(),
                    txtReferencia.getText(),
                    txtDesignacao.getText(),
                    txtDescInformal.getText(),
                    txtDescTecnica.getText(),
                    Integer.parseInt(txtEstimativaDuracao.getText()),
                    Double.parseDouble(txtEstimativaCusto.getText()),
                    gestaoUtilizadoresController.getEmail(),
                    registarColaboradorController.findByEmail(gestaoUtilizadoresController.getEmail()).getNifOrganizacao());

            if (adicionou){
               // parentUI.updateListViewTarefas(actionEvent);
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


}
