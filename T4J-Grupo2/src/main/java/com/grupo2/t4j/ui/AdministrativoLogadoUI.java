package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.files.FicheiroRepositorioAreaActividade;
import com.grupo2.t4j.files.FileChooserT4J;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Freelancer;
import com.grupo2.t4j.persistence.inmemory.RepositorioAreaActividadeInMemory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    
    private FicheiroRepositorioAreaActividade ficheiroAt;
    private RepositorioAreaActividadeInMemory repositorioAreaActividadeInMemory;

    private static final String CABECALHO_IMPORTAR = "Importar Lista.";
    private static final String CABECALHO_EXPORTAR = "Exportar Lista.";

    @FXML Button btnAddAreaAtividade;
    @FXML Button btnAddcompetenciaTecnica;
    @FXML Button btnAddCategoriaTarefa;
    @FXML Button btnAdicionarFreelancer;
    @FXML Button btnAdicionarHabilitacao;
    @FXML Button btnAdicionarReconGP;
    @FXML Button btnSair;
    @FXML ListView<AreaActividade> listaAreasActividade;
    @FXML ListView<Categoria> listaCategorias;
    @FXML ListView<CompetenciaTecnica> listViewCompetenciasTecnicas;
    @FXML ListView<Freelancer> listaFreelancer;
    
    //TableView AreaActividade
    
    @FXML TableColumn<Object, Object> colunaDescBreve;
    @FXML TableColumn<Object, Object> colunaDescDetalhada;
    @FXML TableColumn<Object, Object> colunaCodigo;
    @FXML TableView<AreaActividade> tableViewAreaActividade;
    
    //TableView CompetenciaTecnica
    
    @FXML TableColumn<Object, Object> colunaDescBreveCT;
    @FXML TableColumn<Object, Object> colunaDescDetalhadaCT;
    @FXML TableColumn<Object, Object> colunaCodigoCT;
    @FXML TableColumn<Object, Object> colunaCodigoATCT;
    @FXML TableView<CompetenciaTecnica> tableViewCompetenciaTecnica;
    
    //TableView Categoria
    
    @FXML TableColumn<Object, Object> colunaDescBreveCat;
    @FXML TableColumn<Object, Object> colunaDescDetalhadaCat;
    @FXML TableColumn<Object, Object> colunaCodigoCat;
    @FXML TableColumn<Object, Object> colunaCodigoATCat;
    @FXML TableView<Categoria> tableViewCategoria;
    
    //TableView Freelancer
    
    @FXML TableColumn<Object, Object> colunaNome;
    @FXML TableColumn<Object, Object> colunaNif;
    @FXML TableColumn<Object, Object> colunaTelefone;
    @FXML TableColumn<Object, Object> colunaEmail;
    @FXML TableView<Freelancer> tableViewFreelancer;
    

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
        registarHabilitacaoAcademicaController = new  RegistarHabilitacaoAcademicaController();
        registarReconhecimentoGPController = new  RegistarReconhecimentoGPController();
        gestaoUtilizadoresController = new GestaoUtilizadoresController();
                     
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
        }

        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
    }

    public void addCategoriaTarefa(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddCategoriaTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdicionarCategoriaTarefa.fxml"));
            Parent rootAddCategoriaTarefa = loaderAddCategoriaTarefa.load();
            sceneAddCategoriaTarefa = new Scene(rootAddCategoriaTarefa);
            AdicionarCategoriaTarefaUI adicionarCategoriaTarefaUI = loaderAddCategoriaTarefa.getController();
            adicionarCategoriaTarefaUI.associarParentUI(this);
        }
        catch (IOException exception) {
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

    public void updateTableViewFreelancer() throws SQLException {
        tableViewFreelancer.getItems().setAll(registarFreelancerController.getAll());
                 
        colunaNome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        colunaNif.setCellValueFactory( new PropertyValueFactory<>("nif"));
        colunaTelefone.setCellValueFactory( new PropertyValueFactory<>("telefone"));
        colunaEmail.setCellValueFactory( new PropertyValueFactory<>("email"));
        
    }
    
    public void updateTableViewAreasActividade() throws SQLException {
        tableViewAreaActividade.getItems().setAll(registarAreaActividadeController.getAll());
        colunaCodigo.setCellValueFactory( new PropertyValueFactory<>("codigo"));
        colunaDescBreve.setCellValueFactory( new PropertyValueFactory<>("descBreve"));
        colunaDescDetalhada.setCellValueFactory( new PropertyValueFactory<>("descDetalhada"));
    }


      public void updateTableViewCategoriasTarefa() throws SQLException {
        tableViewCategoria.getItems().setAll(registarCategoriaController.getAll());
        
        colunaCodigoCat.setCellValueFactory( new PropertyValueFactory<>("codigoCategoria"));
        colunaDescBreveCat.setCellValueFactory( new PropertyValueFactory<>("descBreve"));
        colunaDescDetalhadaCat.setCellValueFactory( new PropertyValueFactory<>("descDetalhada"));
        colunaCodigoATCat.setCellValueFactory( new PropertyValueFactory<>("codigoAreaActividade"));

    }

    public void updateTableViewCompetenciasTecnicas() throws SQLException {
        tableViewCompetenciaTecnica.getItems().setAll(registarCompetenciaTecnicaController.getAll());

        colunaCodigoCT.setCellValueFactory( new PropertyValueFactory<>("codigo"));
        colunaDescBreveCT.setCellValueFactory( new PropertyValueFactory<>("descricaoBreve"));
        colunaDescDetalhadaCT.setCellValueFactory( new PropertyValueFactory<>("descricaoDetalhada"));
        colunaCodigoATCT.setCellValueFactory( new PropertyValueFactory<>("codigoAreaActividade"));
    }

    public String getCodigoCompetenciaTecnica() {
        return listViewCompetenciasTecnicas.getSelectionModel().getSelectedItem().getCodigo();
    }

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
                }
                else {
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
