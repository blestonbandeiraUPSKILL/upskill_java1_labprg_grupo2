package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.PublicarTarefaController;
import com.grupo2.t4j.model.TipoRegimento;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class PublicarTarefaUI implements Initializable {

    private ColaboradorLogadoUI colaboradorLogadoUI;
    private PublicarTarefaController publicarTarefaController;

    @FXML TextField dtFimPublicitacao;
    @FXML TextField dtInicioCandidaturas;
    @FXML TextField dtFimCandidaturas;
    @FXML TextField dtInicioPublicitacao;
    @FXML TextField dtInicioSeriacao;
    @FXML TextField dtFimSeriacao;
    @FXML TextArea txtRegrasGerais;
    @FXML Button btnPublicar;
    @FXML Button btnVoltar;
    @FXML ComboBox<TipoRegimento> cmbTipoSeriacao;

    
    public void associarParentUI(ColaboradorLogadoUI colaboradorLogadoUI) {
        this.colaboradorLogadoUI = colaboradorLogadoUI;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        publicarTarefaController = new PublicarTarefaController();
        
        
        try {
            cmbTipoSeriacao.getItems().setAll(publicarTarefaController.getAllRegimento());
        } catch (SQLException exception) {
           exception.printStackTrace();
        }
        
        cmbTipoSeriacao.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               txtRegrasGerais.setText(cmbTipoSeriacao.getSelectionModel().getSelectedItem().getDescricaoRegras());
           }
           });
    }    
    @FXML
    void publicarTarefaAction(ActionEvent actionEvent) throws SQLException{
        try {
            boolean adicionou = publicarTarefaController.publicarTarefa(
                    colaboradorLogadoUI.tabelaTarefas.getSelectionModel().getSelectedItem().getReferencia(),
                    colaboradorLogadoUI.tabelaTarefas.getSelectionModel().getSelectedItem().getNifOrganizacao(),
                    dtInicioPublicitacao.getText(),
                    dtFimPublicitacao.getText(),
                    dtInicioCandidaturas.getText(),
                    dtFimCandidaturas.getText(),
                    dtInicioSeriacao.getText(),
                    dtFimSeriacao.getText(),
                    cmbTipoSeriacao.getSelectionModel().getSelectedItem().getIdTipoRegimento());

            if (adicionou){
                colaboradorLogadoUI.updateTableViewTarefas();

            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Publicar Tarefa.",
                    "Tarefa publicada com sucesso.").show();
            }

            closePublicarTarefa(actionEvent);

        }
        catch (IllegalArgumentException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Publicar Tarefa - Erro nos dados.",
                    "Não foi possível publicar a Tarefa." + exception.getMessage()).show();

        }

    }
    
    @FXML
    void voltarAtras(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }
    
    private void closePublicarTarefa(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
