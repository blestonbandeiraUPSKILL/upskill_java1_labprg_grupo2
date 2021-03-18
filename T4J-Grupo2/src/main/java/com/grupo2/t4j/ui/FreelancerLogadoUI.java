package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.EfectuarCandidaturaController;
import com.grupo2.t4j.controller.EliminarCandidaturaController;
import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarTarefaController;
import com.grupo2.t4j.domain.Candidatura;
import com.grupo2.t4j.domain.TabelaCandidaturaResultado;
import com.grupo2.t4j.domain.Tarefa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class FreelancerLogadoUI implements Initializable {

    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    private Scene sceneStartingPage;

    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private EfectuarCandidaturaController efectuarCandidaturaController;
    private RegistarTarefaController registarTarefaController;
    private ConsultarCandidaturaUI consultarCandidaturaUI;
    private EliminarCandidaturaController eliminarCandidaturaController;
    private Scene sceneConsultarCandidatura;
    private Scene sceneConsultarResultado;
    
    @FXML Button btnCandidatura;
    @FXML Button btnConsultar;
    @FXML Button btnApagar;
    @FXML Button btnResultado;
    @FXML Button btnSair;

    ///// Tabela Anuncios //////////////
    @FXML TableView<Tarefa> tabelaAnuncios;

    @FXML TableColumn<Object, Object> colunaReferencia;
    @FXML TableColumn<Object, Object> colunaDesignacao;
    @FXML TableColumn<Object, Object> colunaDuracao;
    @FXML TableColumn<Object, Object> colunaCusto;

    /////Tabela Candidaturas ////////////
    @FXML TableView<TabelaCandidaturaResultado> tabelaCandidaturas;

    @FXML TableColumn<Object, Object> txtIdCandidatura;
    @FXML TableColumn<Object, Object> txtValorPretendido;
    @FXML TableColumn<Object, Object> txtDuracaoEstimada;
    @FXML TableColumn<Object, Object> txtDataCandidatura;
    @FXML TableColumn<Object, Object> txtDataEdicao;
    @FXML TableColumn<Object, Object> txtResultado;


    /**
     * Associa a scene StartingPageUI como parent desta Scene 
     * @param startingPageUI
     */
    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    /**
     * Initializes the controller (UI) class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            efectuarCandidaturaController = new EfectuarCandidaturaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        gestaoUtilizadoresController = new GestaoUtilizadoresController();
        try {
            registarTarefaController = new RegistarTarefaController();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        eliminarCandidaturaController = new EliminarCandidaturaController();

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        try {
            updateTableViewAnuncio();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            updateTableViewCandidaturas();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Devolve o email do Freelancer logado
     * @return 
     */
    public String getEmail() {
        return gestaoUtilizadoresController.getEmail();
    }

    @FXML
    void verAnuncioAction(ActionEvent event) {

    }

    private ObservableList<Tarefa> listaAnuncios() throws SQLException {
        return FXCollections.observableArrayList(registarTarefaController.tarefasElegiveis(
                gestaoUtilizadoresController.getEmail()));
    }

    /**
     * Preenche a tabela de anuncios
     * @throws SQLException 
     */
    public void updateTableViewAnuncio() throws SQLException {
        tabelaAnuncios.setItems(listaAnuncios());

        colunaDesignacao.setCellValueFactory(new PropertyValueFactory<>("designacao"));
        colunaReferencia.setCellValueFactory(new PropertyValueFactory<>("referencia"));
        colunaDuracao.setCellValueFactory(new PropertyValueFactory<>("duracaoEst"));
        colunaCusto.setCellValueFactory(new PropertyValueFactory<>("custoEst"));

    }

    /**
     * Faz logout da sessao
     * @param actionEvent 
     */
    public void logout(ActionEvent actionEvent) {
        Window window = btnSair.getScene().getWindow();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                        MainApp.TITULO_APLICACAO,
                        "Confirmação da acção",
                        "Tem a certeza que pretende terminar a sessão?");

                if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                    windowEvent.consume();
                } else {
                    boolean logout = gestaoUtilizadoresController.logout();
                    if (logout) {
                        gestaoUtilizadoresController.resetUsersAPI();

                        FXMLLoader loaderStartingPage = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/StartingPageScene.fxml"));
                        Parent rootStartingPage = null;
                        try {
                            rootStartingPage = loaderStartingPage.load();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                        assert rootStartingPage != null;
                        sceneStartingPage = new Scene(rootStartingPage);

                        sceneStartingPage.getStylesheets().add(startingPageUI.estilo);
                        adicionarStage.setScene(sceneStartingPage);
                        adicionarStage.setTitle(MainApp.TITULO_APLICACAO);
                        adicionarStage.show();
                    } else {
                        AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                                MainApp.TITULO_APLICACAO,
                                "Erro",
                                "Não foi possível terminar a sessão.");
                    }
                }
            }
        });
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));

    }

    
    /**
     * Navega para a pagina EfectuarCandidaturaUI
     * @param actionEvent 
     */
    public void navigateEfectuarCandidatura(ActionEvent actionEvent) {

        try {
            FXMLLoader loaderEfectuarCandidatura = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/EfectuarCandidaturaScene.fxml"));
            Parent rootEfectuarCandidatura = loaderEfectuarCandidatura.load();
            EfectuarCandidaturaUI efectuarCandidaturaUI = loaderEfectuarCandidatura.getController();
            efectuarCandidaturaUI.associarParentUI(this);
            efectuarCandidaturaUI.transferData();
            Scene sceneEfectuarCandidatura = new Scene(rootEfectuarCandidatura);

            adicionarStage.setScene(sceneEfectuarCandidatura);
            adicionarStage.setTitle("Efectuar Candidatura");
            adicionarStage.show();
        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

    /**
     * Preenche a tabela de candidaturas do freelancer
     * @throws SQLException 
     */
    public void updateTableViewCandidaturas() throws SQLException {

        String emailFreelancer = gestaoUtilizadoresController.getEmail();


        List<Candidatura> candidaturas = efectuarCandidaturaController.findByEmail(emailFreelancer);
        TabelaCandidaturaResultado cellCandidatura = null;
        for (int i = 0; i < candidaturas.size(); i++)
             cellCandidatura = new TabelaCandidaturaResultado(candidaturas.get(i).getIdCandidatura(),
                    candidaturas.get(i).getValorPretendido(), candidaturas.get(i).getNumeroDias(),
                    candidaturas.get(i).getDataCandidatura(), candidaturas.get(i).getDataEdicaoCandidatura(),"Sem Resultado");
        txtIdCandidatura.setCellValueFactory(new PropertyValueFactory<>("idCandidatura"));
        txtValorPretendido.setCellValueFactory(new PropertyValueFactory<>("valorPretendido"));
        txtDuracaoEstimada.setCellValueFactory(new PropertyValueFactory<>("duracaoEstimada"));
        txtDataCandidatura.setCellValueFactory(new PropertyValueFactory<>("dataCandidatura"));
        txtDataEdicao.setCellValueFactory(new PropertyValueFactory<>("dataEdicao"));
        txtResultado.setCellValueFactory(new PropertyValueFactory<>("resultado"));
        tabelaCandidaturas.getItems().setAll(cellCandidatura);
    }

    /**
     * Apaga uma candidatura do freelancer
     * @param actionEvent
     * @throws SQLException 
     */
    public void apagarCandidatura(ActionEvent actionEvent) throws SQLException {
        int idCandidatura = getIdCandidatura();
        boolean apaga = false;

        try {

            Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                    MainApp.TITULO_APLICACAO,
                    "Confirmação da acção",
                    "Tem a certeza que deseja apagar a candidatura?");

            if (alerta.showAndWait().get() == ButtonType.OK) {

                //actionEvent.consume();
                apaga = eliminarCandidaturaController.deleteCandidatura(idCandidatura);

            }

            if (apaga) {
                updateTableViewCandidaturas();
                updateTableViewAnuncio();

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION,
                        MainApp.TITULO_APLICACAO,
                        "Eliminar Candidatura.",
                        "Candidatura eliminada com sucesso.").show();
            }
        } catch (IllegalArgumentException | SQLException exception) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Eliminar Candidatura - Erro nos dados.",
                    "Não foi possível eliminar a candidatura: " + exception.getMessage()).show();
        }
    }

    /**
     * Navega para a pagina ConsultarCandidaturaUI
     * @param actionEvent
     * @throws SQLException 
     */
    public void consultarCandidatura(ActionEvent actionEvent) throws SQLException {
        try {

            FXMLLoader loaderConsultarCandidatura = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConsultarCandidaturaScene.fxml"));
            Parent rootConsultarCandidatura = loaderConsultarCandidatura.load();
            ConsultarCandidaturaUI consultarCandidaturaUI = loaderConsultarCandidatura.getController();
            consultarCandidaturaUI.associarParentUI(this);
            consultarCandidaturaUI.transferData();
            consultarCandidaturaUI.isCandidaturaEditavel();
            sceneConsultarCandidatura = new Scene(rootConsultarCandidatura);

            adicionarStage.setScene(sceneConsultarCandidatura);
            adicionarStage.setTitle("Consultar Candidatura");
            adicionarStage.show();

        } catch (IOException /*| SQLException*/ exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }
    
    /**
     * Navega para a pagina ConsultarResultadoUI
     * @param actionEvent 
     */
    public void consultarResultado (ActionEvent actionEvent){
        try {

            FXMLLoader loaderConsultarResultado = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConsultarResultadoScene.fxml"));
            Parent rootConsultarResultado = loaderConsultarResultado.load();
            ConsultarResultadoUI consultarResultadoUI = loaderConsultarResultado.getController();
            consultarResultadoUI.associarParentUI(this);
            consultarResultadoUI.transferData();
            sceneConsultarResultado = new Scene(rootConsultarResultado);

            adicionarStage.setScene(sceneConsultarResultado);
            adicionarStage.setTitle("Consultar Resultado");
            adicionarStage.show();

        } catch (IOException /*| SQLException*/ exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }
    
    /**
     * Devolve o id da candidatura selecionada
     * @return
     * @throws SQLException 
     */
    public int getIdCandidatura() throws SQLException {
        int idCandidatura = tabelaCandidaturas.getSelectionModel().getSelectedItem().getIdCandidatura();

        return idCandidatura;
    }
}
