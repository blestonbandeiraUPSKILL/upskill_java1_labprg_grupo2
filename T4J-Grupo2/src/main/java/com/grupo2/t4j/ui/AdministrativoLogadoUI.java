package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.GestaoUtilizadoresController;
import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.controller.RegistarCategoriaController;
import com.grupo2.t4j.controller.RegistarCompetenciaTecnicaController;
import com.grupo2.t4j.files.FicheiroRepositorioAreaActividade;
import com.grupo2.t4j.files.FileChooserT4J;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.model.Categoria;
import com.grupo2.t4j.model.CompetenciaTecnica;
import com.grupo2.t4j.model.Freelancer;
import com.grupo2.t4j.persistence.inmemory.RepositorioAreaActividadeInMemory;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;

public class AdministrativoLogadoUI implements Initializable {

    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    private Scene sceneAddAreaActividade;
    private Scene sceneAddCategoriaTarefa;
    private Scene sceneAddCompetenciaTecnica;
    private Scene sceneStartingPage;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarCategoriaController registarCategoriaController;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    private GestaoUtilizadoresController gestaoUtilizadoresController;

    private FicheiroRepositorioAreaActividade ficheiroAt;
    private RepositorioAreaActividadeInMemory repositorioAreaActividadeInMemory;

    private static final String CABECALHO_IMPORTAR = "Importar Lista.";
    private static final String CABECALHO_EXPORTAR = "Exportar Lista.";

    @FXML Button btnAddAreaAtividade;
    @FXML Button btnAddCategoriaTarefa;
    @FXML Button btnSair;
    @FXML ListView<AreaActividade> listaAreasActividade;
    @FXML ListView<Categoria> listaCategorias;
    @FXML ListView<CompetenciaTecnica> listViewCompetenciasTecnicas;
    @FXML TableView<Freelancer> tabelaFreelancers;
    @FXML TableColumn<Freelancer, String> colunaNomeFreelancer;
    @FXML TableColumn<Freelancer, String> colunaEmailFreelancer;
    @FXML TableColumn<Freelancer, String> colunaNifFreelancer;


    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCategoriaController = new RegistarCategoriaController();
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();
        gestaoUtilizadoresController = new GestaoUtilizadoresController();
    }

    public void addAreaActividade(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loaderAddAreaActividade = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdicionarAreaAtividadeScene.fxml"));
            Parent rootAddAreaActividade = loaderAddAreaActividade.load();
            sceneAddAreaActividade = new Scene(rootAddAreaActividade);
            sceneAddAreaActividade.getStylesheets().add("/com/grupo2/t4j/style/app.css");
            AdicionarAreaAtividadeUI adicionarAreaAtividadeUI = loaderAddAreaActividade.getController();
            adicionarAreaAtividadeUI.associarParentUI(this);

        }
        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    exception.getMessage());
        }
        adicionarStage.setScene(sceneAddAreaActividade);
        adicionarStage.setTitle("Adicionar Área de Actividade");
        adicionarStage.show();
    }

    public void addCategoriaTarefa(ActionEvent actionEvent) {
        try {
            FXMLLoader loaderAddCategoriaTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdicionarCategoriaTarefa.fxml"));
            Parent rootAddCategoriaTarefa = loaderAddCategoriaTarefa.load();
            sceneAddCategoriaTarefa = new Scene(rootAddCategoriaTarefa);
            sceneAddCategoriaTarefa.getStylesheets().add("/com/grupo2/t4j/style/app.css");
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
            sceneAddCompetenciaTecnica.getStylesheets().add("/com/grupo2/t4j/style/app.css");
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

    public void logout(ActionEvent actionEvent) {
        //UsersAPI usersAPI = new UsersAPI();
        //boolean logout = usersAPI.logout();
        boolean logout = gestaoUtilizadoresController.logout();
        if (logout) {
            navigateStartingPage(actionEvent);
            gestaoUtilizadoresController.resetUsersAPI();
        }
        else {
            Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro",
                    "Não foi possível terminar a sessão.");
        }
    }


    public void navigateStartingPage(ActionEvent actionEvent) {
        try {
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
                }
            });
            window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));

            FXMLLoader loaderStartingPage = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/StartingPageScene.fxml"));
            Parent rootStartingPage = loaderStartingPage.load();
            sceneStartingPage = new Scene(rootStartingPage);
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

    public void updateListViewAreasActividade() {
        listaAreasActividade.getItems().setAll(registarAreaActividadeController.getAll());
    }
    public void updateListViewCategoriasTarefa() {
        listaCategorias.getItems().setAll(registarCategoriaController.getAll());
    }

    public void updateListViewCompetenciasTecnicas() {
        listViewCompetenciasTecnicas.getItems().setAll(registarCompetenciaTecnicaController.getAll());
    }

    ///////////////// Ficheiros /////////////////
    public void exportAreasActividade(ActionEvent actionEvent) {
        String descricao, extensao;

        descricao = /*DESCRICAO_SERIALIZACAO*/ "Ficheiro Area de Atividade";
        extensao = /*EXTENSAO_SERIALIZACAO*/"*.txt";
        FileChooser flChooser = FileChooserT4J.criarFileChooserT4J(descricao, extensao);
        File ficheiroExportar = flChooser.showSaveDialog(listaAreasActividade.getScene().getWindow());

        if (ficheiroExportar != null) {
            boolean gravou = false;
                gravou = registarAreaActividadeController.serializar(ficheiroExportar);
                if (gravou) {
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, CABECALHO_EXPORTAR,
                        "Áreas de actividade exportadas com sucesso.").show();
            } else {
                AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, CABECALHO_EXPORTAR,
                        "Problema a exportar a lista de áreas de actividade!").show();
            }
        } else {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, CABECALHO_EXPORTAR,
                    "Não foi seleccionado nenhum ficheiro!").show();
        }
    }

    public void importAreasActividade(ActionEvent actionEvent) {
        String descricao, extensao;

        descricao = /*DESCRICAO_SERIALIZACAO*/"Ficheiro Area de Atividade";
        extensao = /*EXTENSAO_SERIALIZACAO*/"*.txt";
        FileChooser flChooser = FileChooserT4J.criarFileChooserT4J(descricao, extensao);
        File ficheiroImportar = flChooser.showOpenDialog(listaAreasActividade.getScene().getWindow());

        if (ficheiroImportar != null) {
            int numeroAreasImportadas = 0;
                numeroAreasImportadas = registarAreaActividadeController.desserializar(ficheiroImportar);
            

            if (numeroAreasImportadas > 0) {
                updateListViewAreasActividade();

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                        String.format("%d área(s) de actividade importada(s).", numeroAreasImportadas)).show();
            } else {
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                        "Ficheiro sem áreas de actividade para importar!").show();
            }
        } else {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                    "Não foi seleccionado nenhum ficheiro!").show();
        }
    }

    public void exportCompetenciasTecnicas(ActionEvent actionEvent) {
        String descricao, extensao;

        descricao = /*DESCRICAO_SERIALIZACAO*/ "Ficheiro Competência Técnica";
        extensao = /*EXTENSAO_SERIALIZACAO*/".txt";
        FileChooser flChooser = FileChooserT4J.criarFileChooserT4J(descricao, extensao);
        File ficheiroExportar = flChooser.showSaveDialog(listViewCompetenciasTecnicas.getScene().getWindow());

        if (ficheiroExportar != null) {
            boolean gravou = false;
                gravou = registarCompetenciaTecnicaController.serializar(ficheiroExportar);
                if (gravou) {
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, CABECALHO_EXPORTAR,
                        "Competências técnicas exportadas com sucesso.").show();
            } else {
                AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, CABECALHO_EXPORTAR,
                        "Problema a exportar a lista de competências técnicas!").show();
            }
        } else {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, CABECALHO_EXPORTAR,
                    "Não foi seleccionado nenhum ficheiro!").show();
        }
    }

    public void importCompetenciasTecnicas(ActionEvent actionEvent) {
        String descricao, extensao;

        descricao = /*DESCRICAO_SERIALIZACAO*/"Ficheiro Competencias Tecnicas";
        extensao = /*EXTENSAO_SERIALIZACAO*/"*.txt";
        FileChooser flChooser = FileChooserT4J.criarFileChooserT4J(descricao, extensao);
        File ficheiroImportar = flChooser.showOpenDialog(listViewCompetenciasTecnicas.getScene().getWindow());

        if (ficheiroImportar != null) {
            int numeroCompetenciasImportadas = 0;
                numeroCompetenciasImportadas = registarCompetenciaTecnicaController.desserializar(ficheiroImportar);
            

            if (numeroCompetenciasImportadas > 0) {
                updateListViewCompetenciasTecnicas();

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                        String.format("%d competência(s) de actividade importada(s).", numeroCompetenciasImportadas)).show();
            } else {
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                        "Ficheiro sem competências técnicas para importar!").show();
            }
        } else {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                    "Não foi seleccionado nenhum ficheiro!").show();
        }
    }

    public void exportCategoriasTarefa(ActionEvent actionEvent) {
        String descricao, extensao;

        descricao = /*DESCRICAO_SERIALIZACAO*/ "Ficheiro Categoria";
        extensao = /*EXTENSAO_SERIALIZACAO*/".txt";
        FileChooser flChooser = FileChooserT4J.criarFileChooserT4J(descricao, extensao);
        File ficheiroExportar = flChooser.showSaveDialog(listaCategorias.getScene().getWindow());

        if (ficheiroExportar != null) {
            boolean gravou = false;
                gravou = registarCategoriaController.serializar(ficheiroExportar);
                if (gravou) {
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, CABECALHO_EXPORTAR,
                        "Categorias exportados com sucesso.").show();
            } else {
                AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, CABECALHO_EXPORTAR,
                        "Problema a exportar a lista de categorias!").show();
            }
        } else {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, CABECALHO_EXPORTAR,
                    "Não foi seleccionado nenhum ficheiro!").show();
        }
    }
    
    public void importCategorias(ActionEvent actionEvent) {
        String descricao, extensao;

        descricao = /*DESCRICAO_SERIALIZACAO*/"Ficheiro Categoria";
        extensao = /*EXTENSAO_SERIALIZACAO*/"*.txt";
        FileChooser flChooser = FileChooserT4J.criarFileChooserT4J(descricao, extensao);
        File ficheiroImportar = flChooser.showOpenDialog(listaCategorias.getScene().getWindow());

        if (ficheiroImportar != null) {
            int numeroCategoriasImportadas = 0;
                numeroCategoriasImportadas = registarCategoriaController.desserializar(ficheiroImportar);
            

            if (numeroCategoriasImportadas > 0) {
                updateListViewCategoriasTarefa();

                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                        String.format("%d categoria(s) importada(s).", numeroCategoriasImportadas)).show();
            } else {
                AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                        "Ficheiro sem categorias para importar!").show();
            }
        } else {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, CABECALHO_IMPORTAR,
                    "Não foi seleccionado nenhum ficheiro!").show();
        }
    }


    public void addHabilitacaoFreelancer(ActionEvent actionEvent) {
    }

    public void addCompetenciaFreelancer(ActionEvent actionEvent) {
    }

    public void addFreelancerScene(ActionEvent actionEvent) {
    }
}
