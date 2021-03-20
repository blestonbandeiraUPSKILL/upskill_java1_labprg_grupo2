package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.PublicarTarefaController;
import com.grupo2.t4j.domain.FiltroTarefas;
import com.grupo2.t4j.domain.TipoRegimento;
import com.grupo2.t4j.dto.TipoRegimentoDTO;
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
public class PublicarTarefaColaboradorUI implements Initializable {

    private ColaboradorLogadoUI colaboradorLogadoUI;
    private PublicarTarefaController publicarTarefaController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;

    @FXML TextField dtFimPublicitacao;
    @FXML TextField dtInicioCandidaturas;
    @FXML TextField dtFimCandidaturas;
    @FXML TextField dtInicioPublicitacao;
    @FXML TextField dtInicioSeriacao;
    @FXML TextField dtFimSeriacao;
    @FXML TextArea txtRegrasGerais;
    @FXML Button btnPublicar;
    @FXML Button btnVoltar;
    @FXML ComboBox<TipoRegimentoDTO> cmbTipoSeriacao;
    @FXML Label txtEmail;

    
    /**
     * Associa a scene ColaboradorLogadoUI como parent desta Scene 
     * @param colaboradorLogadoUI
     */
    public void associarParentUI(ColaboradorLogadoUI colaboradorLogadoUI) {
        this.colaboradorLogadoUI = colaboradorLogadoUI;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        publicarTarefaController = new PublicarTarefaController();

        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        txtEmail.setText(gestaoUtilizadoresController.getEmail());
        
        
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
    
    /**
     * Publica uma tarefa
     * @param actionEvent
     * @throws SQLException 
     */
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
            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO,
                    "Publicar Tarefa.",
                    "Tarefa publicada com sucesso.").show();
            }

            closePublicarTarefa(actionEvent);
            colaboradorLogadoUI.updateTableViewTarefas();
            colaboradorLogadoUI.cmbFiltroTarefas.getSelectionModel().select(FiltroTarefas.TAREFAS_DA_ORGANIZACAO);

        }
        catch (IllegalArgumentException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Publicar Tarefa - Erro nos dados.",
                    "Não foi possível publicar a Tarefa." + exception.getMessage()).show();

        }

    }
    
    /**
     * Volta para a scene anterior
     * @param event 
     */
    @FXML
    void voltarAtras(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }
    
    /**
     * Termina o registo de uma tarefa
     * @param actionEvent 
     */
    private void closePublicarTarefa(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
