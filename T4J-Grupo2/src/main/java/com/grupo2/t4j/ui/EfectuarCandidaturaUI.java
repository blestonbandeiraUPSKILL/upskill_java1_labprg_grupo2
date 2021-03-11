package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.EfectuarCandidaturaController;
import com.grupo2.t4j.controller.RegistarAnuncioController;
import com.grupo2.t4j.controller.RegistarTarefaController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EfectuarCandidaturaUI implements Initializable {

    private FreelancerLogadoUI freelancerLogadoUI;
    private EfectuarCandidaturaController efectuarCandidaturaController;
    private RegistarAnuncioController registarAnuncioController;
    private RegistarTarefaController registarTarefaController;

    @FXML TextArea txtAnuncio;
    @FXML TextArea txtApresentacao;
    @FXML TextArea txtMotivacao;
    @FXML TextField txtValor;
    @FXML TextField txtDias;
    @FXML Button btnCancelar;
    @FXML Button btnAddCandidatura;


    /**
     * Associa a scene FreelancerLogadoUI como parent desta Scene 
     * @param FreelancerLogadoUI 
     */
    public void associarParentUI(FreelancerLogadoUI freelancerLogadoUI) {
        this.freelancerLogadoUI = freelancerLogadoUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            efectuarCandidaturaController = new EfectuarCandidaturaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        registarAnuncioController = new RegistarAnuncioController();
        try {
            registarTarefaController = new RegistarTarefaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
    
    /**
     * Devolve o id do anuncio selecionado
     * @return
     * @throws SQLException 
     */
    public int getIdAnuncio() throws SQLException {

        String nifOrganizacao = freelancerLogadoUI.tabelaAnuncios.getSelectionModel().getSelectedItem().getNifOrganizacao();
        String referenciaTarefa = freelancerLogadoUI.tabelaAnuncios.getSelectionModel().getSelectedItem().getReferencia();
        int idAnuncio = registarTarefaController.findIdAnuncio(nifOrganizacao, referenciaTarefa);

        return idAnuncio ;
    }

    /**
     * Adiciona uma nova candidatura
     * @param actionEvent
     * @throws SQLException 
     */
    public void addCandidatura(ActionEvent actionEvent) throws SQLException {

        String emailFreelancer = freelancerLogadoUI.getEmail();
        int idAnuncio = getIdAnuncio();

        try {
            boolean adicionou = efectuarCandidaturaController.registarCandidatura(
                    Double.parseDouble(txtValor.getText()),
                    Integer.parseInt(txtDias.getText()),
                    txtApresentacao.getText(),
                    txtMotivacao.getText(),
                    idAnuncio,
                    emailFreelancer);

            if(adicionou) {

                freelancerLogadoUI.updateTableViewCandidaturas();
                btnAddCandidatura.setDisable(true);

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                        MainApp.TITULO_APLICACAO,
                        "Efectuar Candidatura.",
                        "Candidatura efectuada com sucesso.").show();

                closeEfectuarCandidatura(actionEvent);

            }

        }
        catch (IllegalArgumentException | SQLException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Efecutar Candidatura - Erro nos dados.",
                    "Não foi possível Efectuar a candidatura: " + exception.getMessage()).show();
        }

    }

    /**
     * Cancela a operacao
     * @param actionEvent 
     */
    public void cancelarAction(ActionEvent actionEvent) {
        Window window = btnCancelar.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que quer voltar à página anterior, cancelando a candidatura?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                }
            }
        });
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    /**
     * Preenche os dados do anuncio
     * @throws SQLException 
     */
    public void transferData() throws SQLException {
        txtAnuncio.setText(registarTarefaController.findTarefa(getIdAnuncio()).toStringCompleto());

    }

    /**
     * Fecha a candidatura
     * @param event 
     */
    private void closeEfectuarCandidatura(ActionEvent event) {
        this.txtAnuncio.clear();
        this.txtApresentacao.clear();
        this.txtMotivacao.clear();
        this.txtValor.clear();
        this.txtDias.clear();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}
