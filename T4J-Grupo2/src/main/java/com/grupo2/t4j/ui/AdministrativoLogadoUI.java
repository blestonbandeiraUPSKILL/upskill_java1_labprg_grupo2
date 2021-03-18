package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.domain.AreaActividade;
import com.grupo2.t4j.domain.Categoria;
import com.grupo2.t4j.domain.CompetenciaTecnica;
import com.grupo2.t4j.domain.Freelancer;
import com.grupo2.t4j.dto.AreaActividadeDTO;
import com.grupo2.t4j.dto.CategoriaDTO;
import com.grupo2.t4j.dto.CompetenciaTecnicaDTO;
import com.grupo2.t4j.dto.FreelancerDTO;
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
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class AdministrativoLogadoUI implements Initializable {

    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    private Scene sceneAddAreaActividade;
    private Scene sceneAddCategoriaTarefa;
    private Scene sceneAddCompetenciaTecnica;
    private Scene sceneAddFreelancer;
    private Scene sceneConsultarCompetenciaTecnica;
    private Scene sceneConsultarAreaActividade;
    private Scene sceneConsultarCategoria;
    private Scene sceneAddHabilitacaoFreelancer;
    private Scene sceneAddReconhecimentoGP;
    private Scene sceneStartingPage;
    private Scene sceneConsultarFreelancer;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarCategoriaController registarCategoriaController;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;
    private RegistarFreelancerController registarFreelancerController;
    private RegistarHabilitacaoAcademicaController registarHabilitacaoAcademicaController;
    private RegistarReconhecimentoGPController registarReconhecimentoGPController;

    @FXML Button btnAddAreaAtividade;
    @FXML Button btnAddcompetenciaTecnica;
    @FXML Button btnAddCategoriaTarefa;
    @FXML Button btnAdicionarFreelancer;
    @FXML Button btnAdicionarHabilitacao;
    @FXML Button btnAdicionarReconGP;
    @FXML Button btnSair;
    @FXML ListView<CompetenciaTecnica> listViewCompetenciasTecnicas;
    @FXML Label txt_email;

    //TableView AreaActividade
    @FXML TableColumn<Object, Object> colunaDescBreve;
    @FXML TableColumn<Object, Object> colunaDescDetalhada;
    @FXML TableColumn<Object, Object> colunaCodigo;
    @FXML TableView<AreaActividadeDTO> tableViewAreaActividade;

    //TableView CompetenciaTecnica
    @FXML TableColumn<Object, Object> colunaDescBreveCT;
    @FXML TableColumn<Object, Object> colunaDescDetalhadaCT;
    @FXML TableColumn<Object, Object> colunaCodigoCT;
    @FXML TableColumn<Object, Object> colunaCodigoATCT;
    @FXML TableView<CompetenciaTecnicaDTO> tableViewCompetenciaTecnica;

    //TableView Categoria
    @FXML TableColumn<Object, Object> colunaDescBreveCat;
    @FXML TableColumn<Object, Object> colunaDescDetalhadaCat;
    @FXML TableColumn<Object, Object> colunaCodigoCat;
    @FXML TableColumn<Object, Object> colunaCodigoATCat;
    @FXML TableView<CategoriaDTO> tableViewCategoria;

    //TableView Freelancer
    @FXML TableColumn<Object, Object> colunaNome;
    @FXML TableColumn<Object, Object> colunaNif;
    @FXML TableColumn<Object, Object> colunaTelefone;
    @FXML TableColumn<Object, Object> colunaEmail;
    @FXML TableView<FreelancerDTO> tableViewFreelancer;

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

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCategoriaController = new RegistarCategoriaController();
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();
        registarFreelancerController = new RegistarFreelancerController();
        registarHabilitacaoAcademicaController = new RegistarHabilitacaoAcademicaController();
        registarReconhecimentoGPController = new RegistarReconhecimentoGPController();
        gestaoUtilizadoresController = new GestaoUtilizadoresController();

        txt_email.setText(gestaoUtilizadoresController.getEmail());

        try {
            updateTableViewAreasActividade();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            updateTableViewCompetenciasTecnicas();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            updateTableViewCategoriasTarefa();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            updateTableViewFreelancer();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Navega para a pagina AdicionarAreaActividadeUI
     * @param actionEvent
     * @throws IOException 
     */
    public void addAreaActividade(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loaderAddAreaActividade = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdicionarAreaAtividadeScene.fxml"));
            Parent rootAddAreaActividade = loaderAddAreaActividade.load();
            sceneAddAreaActividade = new Scene(rootAddAreaActividade);
            AdicionarAreaAtividadeUI adicionarAreaAtividadeUI = loaderAddAreaActividade.getController();
            adicionarAreaAtividadeUI.associarParentUI(this);

            adicionarStage.setScene(sceneAddAreaActividade);
            adicionarStage.setTitle("Adicionar Área de Actividade");
            adicionarStage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

    /**
     * Navega para a pagina CategoriaTarefaUI
     * @param actionEvent 
     */
    public void addCategoriaTarefa(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddCategoriaTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdicionarCategoriaTarefa.fxml"));
            Parent rootAddCategoriaTarefa = loaderAddCategoriaTarefa.load();
            sceneAddCategoriaTarefa = new Scene(rootAddCategoriaTarefa);
            AdicionarCategoriaTarefaUI adicionarCategoriaTarefaUI = loaderAddCategoriaTarefa.getController();
            adicionarCategoriaTarefaUI.associarParentUI(this);
        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }

        adicionarStage.setScene(sceneAddCategoriaTarefa);
        adicionarStage.setTitle("Adicionar Categoria de Tarefa");
        adicionarStage.show();
    }

    /**
     * Navega para a página AdicionarCompetenciaTecnicaUI
     * @param actionEvent 
     */
    public void addCompetenciaTecnica(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddCompetenciaTecnica = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdicionarCompetenciaTecnicaScene.fxml"));
            Parent rootAddCompetenciaTecnica = loaderAddCompetenciaTecnica.load();
            sceneAddCompetenciaTecnica = new Scene(rootAddCompetenciaTecnica);
            AdicionarCompetenciaTecnicaUI adicionarCompetenciaTecnicaUI = loaderAddCompetenciaTecnica.getController();
            adicionarCompetenciaTecnicaUI.associarParentUI(this);

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }

        adicionarStage.setScene(sceneAddCompetenciaTecnica);
        adicionarStage.setTitle("Adicionar Competência Técnica");
        adicionarStage.show();
    }

    /**
     * Navega para a pagina AdicionarFreelancerUI
     * @param actionEvent 
     */
    public void addFreelancer(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddFreelancer = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdicionarFreelancerScene.fxml"));
            Parent rootAddFreelancer = loaderAddFreelancer.load();
            sceneAddFreelancer = new Scene(rootAddFreelancer);
            AdicionarFreelancerUI adicionarFreelancerUI = loaderAddFreelancer.getController();
            adicionarFreelancerUI.associarParentUI(this);

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }

        adicionarStage.setScene(sceneAddFreelancer);
        adicionarStage.setTitle("Adicionar Freelancer");
        adicionarStage.show();
    }

    /**
     * Navega para a pagina AdicionarHabilitacaoAcademicaUI
     * @param actionEvent 
     */
    public void addHabilitacaoFreelancer(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddHabilitacaoFreelancer = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdicionarHabilitacaoAcademicaScene.fxml"));
            Parent rootAddHabilitacaoFreelancer = loaderAddHabilitacaoFreelancer.load();
            sceneAddHabilitacaoFreelancer = new Scene(rootAddHabilitacaoFreelancer);
            AdicionarHabilitacaoAcademicaUI adicionarHabilitacaoFreelancerUI = loaderAddHabilitacaoFreelancer.getController();
            adicionarHabilitacaoFreelancerUI.associarParentUI(this);

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }

        adicionarStage.setScene(sceneAddHabilitacaoFreelancer);
        adicionarStage.setTitle("Adicionar Habilitação Académica");
        adicionarStage.show();

    }

    /**
     * Navega para a pagina AdicionarReconhecimentoGPUI
     * @param actionEvent 
     */
    public void addReconhecimentoGP(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddReconhecimentoGP = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdicionarReconhecimentoGPScene.fxml"));
            Parent rootAddReconhecimentoGP = loaderAddReconhecimentoGP.load();
            sceneAddReconhecimentoGP = new Scene(rootAddReconhecimentoGP);
            AdicionarReconhecimentoGPUI adicionarReconhecimentoGPUI = loaderAddReconhecimentoGP.getController();
            adicionarReconhecimentoGPUI.associarParentUI(this);

        } catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }

        adicionarStage.setScene(sceneAddReconhecimentoGP);
        adicionarStage.setTitle("Adicionar Competência Técnica de Freelancer");
        adicionarStage.show();

    }

    /**
     * Preenche a tabela de Freelancers
     * @throws SQLException 
     */
    public void updateTableViewFreelancer() throws SQLException {
        tableViewFreelancer.getItems().setAll(registarFreelancerController.getAll());

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaNif.setCellValueFactory(new PropertyValueFactory<>("nif"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    /**
     * Preenche a tabela de Areas de Atividade
     * @throws SQLException 
     */
    public void updateTableViewAreasActividade() throws SQLException {
        tableViewAreaActividade.getItems().setAll(registarAreaActividadeController.getAll());

        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaDescBreve.setCellValueFactory(new PropertyValueFactory<>("descBreve"));
        colunaDescDetalhada.setCellValueFactory(new PropertyValueFactory<>("descDetalhada"));
    }

    /**
     * Preenche a tabela de Categorias de Tarefa
     * @throws SQLException 
     */
    public void updateTableViewCategoriasTarefa() throws SQLException {
        tableViewCategoria.getItems().setAll(registarCategoriaController.getAll());

        colunaCodigoCat.setCellValueFactory(new PropertyValueFactory<>("codigoCategoria"));
        colunaDescBreveCat.setCellValueFactory(new PropertyValueFactory<>("descBreve"));
        colunaDescDetalhadaCat.setCellValueFactory(new PropertyValueFactory<>("descDetalhada"));
        colunaCodigoATCat.setCellValueFactory(new PropertyValueFactory<>("codigoAreaActividade"));

    }

    /**
     * Preenche a tabela de Competencias Tecnicas
     * @throws SQLException 
     */
    public void updateTableViewCompetenciasTecnicas() throws SQLException {
        tableViewCompetenciaTecnica.getItems().setAll(registarCompetenciaTecnicaController.getAll());

        colunaCodigoCT.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaDescBreveCT.setCellValueFactory(new PropertyValueFactory<>("descricaoBreve"));
        colunaDescDetalhadaCT.setCellValueFactory(new PropertyValueFactory<>("descricaoDetalhada"));
        colunaCodigoATCT.setCellValueFactory(new PropertyValueFactory<>("codigoAreaActividade"));
    }

    /**
     * Devolve o codigo da competencia tecnica selecionada
     * @return 
     */
    public String getCodigoCompetenciaTecnica() {
        return listViewCompetenciasTecnicas.getSelectionModel().getSelectedItem().getCodigo();
    }

    /**
     * Navega para a pagina ConsultarCompetenciaTecnicaUI
     * @param event 
     */
    public void consultarCompetenciaTecnicaAction(ActionEvent event) {

        try {
            FXMLLoader loaderConsultarCompetenciaTecnica = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConsultarCompetenciaTecnicaScene.fxml"));
            Parent rootConsultarCompetenciaTecnica = loaderConsultarCompetenciaTecnica.load();
            ConsultarCompetenciaTecnicaUI consultarCompetenciaTecnicaUI = loaderConsultarCompetenciaTecnica.getController();
            consultarCompetenciaTecnicaUI.associarParentUI(this);
            consultarCompetenciaTecnicaUI.transferData();
            sceneConsultarCompetenciaTecnica = new Scene(rootConsultarCompetenciaTecnica);

            adicionarStage.setScene(sceneConsultarCompetenciaTecnica);
            adicionarStage.setTitle("Consultar Competência Técnica");
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
     * Navega para a pagina ConsultarAreaActividadeUI
     * @param event 
     */
    public void consultarAreaActividadeAction(ActionEvent event) {

        try {
            FXMLLoader loaderConsultarAreaActividade = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConsultarAreaActividadeScene.fxml"));
            Parent rootConsultarAreaActividade = loaderConsultarAreaActividade.load();
            ConsultarAreaActividadeUI consultarAreaActividadeUI = loaderConsultarAreaActividade.getController();
            consultarAreaActividadeUI.associarParentUI(this);
            consultarAreaActividadeUI.transferData();
            sceneConsultarAreaActividade = new Scene(rootConsultarAreaActividade);

            adicionarStage.setScene(sceneConsultarAreaActividade);
            adicionarStage.setTitle("Consultar Área de Actividade");
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
     * Navega para a pagina ConsultarCategoriaUI
     * @param event
     * @throws SQLException 
     */
    public void consultarCategoriaAction(ActionEvent event) throws SQLException {

        try {
            FXMLLoader loaderConsultarCategoria = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConsultarCategoriaScene.fxml"));
            Parent rootConsultarCategoria = loaderConsultarCategoria.load();
            ConsultarCategoriaUI consultarCategoriaUI = loaderConsultarCategoria.getController();
            consultarCategoriaUI.associarParentUI(this);
            consultarCategoriaUI.transferData();
            sceneConsultarCategoria = new Scene(rootConsultarCategoria);

            adicionarStage.setScene(sceneConsultarCategoria);
            adicionarStage.setTitle("Consultar Categoria");
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
     * Navega para a pagina ConsultarFreelancerUI
     * @param actionEvent 
     */
    public void consultarFreelancer(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderConsultarFreelancer = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/ConsultarFreelancerScene.fxml"));
            Parent rootConsultarFreelancer = loaderConsultarFreelancer.load();
            ConsultarFreelancerUI consultarFreelancerUI = loaderConsultarFreelancer.getController();
            consultarFreelancerUI.associarParentUI(this);
            consultarFreelancerUI.transferData();
            sceneConsultarFreelancer = new Scene(rootConsultarFreelancer);

            adicionarStage.setScene(sceneConsultarFreelancer);
            adicionarStage.setTitle("Consultar Freelancer");
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
                        sceneStartingPage = new Scene(rootStartingPage);

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

}
