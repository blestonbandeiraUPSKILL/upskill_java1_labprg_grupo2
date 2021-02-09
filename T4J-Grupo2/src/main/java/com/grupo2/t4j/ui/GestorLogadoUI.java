package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.*;
import com.grupo2.t4j.files.FicheiroRepositorioColaborador;
import com.grupo2.t4j.model.*;
import com.grupo2.t4j.repository.RepositorioColaborador;
import java.io.IOException;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class GestorLogadoUI implements Initializable {
    
    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    private Scene sceneStartingPage;
    private RegistarColaboradorController registarColaboradorController;
    private RegistarCategoriaController registarCategoriaController;
    private RegistarAreaActividadeController registarAreaActividadeController;
    private RegistarTarefaController registarTarefaController;
    private RegistarCompetenciaTecnicaController registarCompetenciaTecnicaController;
    
    private FicheiroRepositorioColaborador ficheiroC;
    private RepositorioColaborador repositorioColaborador;
    
    private AutenticacaoController autenticacaoController;

    /*
    @FXML private TextField txtReferencia1;
    @FXML private ComboBox<?> cmbArAct;
    @FXML private TextField txtDuracao1;
    @FXML private TextField txtCusto1;
    @FXML private TextArea txtDescricaoInformal1;
    @FXML private ListView<?> listViewTarefas;
    @FXML private TextArea txtDescInformalTarefa;
    @FXML private ComboBox<?> cmbCategoriaTarefaEspecificarTarefa;
    @FXML private TextField txtRefTarefa;
    @FXML private TextField txtDuracaoTarefa;
    @FXML private TextField txtDesignTarefa;
    @FXML private ComboBox<?> cmbCategoriaTarefaListaTarefas;
    @FXML private TextArea txtDescTecnicaTarefa;
    @FXML private ComboBox<?> cmbAreaActividadeListaTarefas;
    @FXML private ListView<?> listViewCompTecReq;
    @FXML private Button btnSairListaTarefas;
    @FXML private TextField txtCustoEstTarefa;
    @FXML private ComboBox<?> cmbAreaActividadeEspecificarTarefa;
    @FXML ComboBox<AreaActividade> cmbAreaActividadeListaTarefas;
    @FXML ComboBox<AreaActividade> cmbAreaActividadeEspecificarTarefa;
    @FXML ComboBox<Categoria> cmbCategoriaTarefaListaTarefas;
    @FXML ComboBox<Categoria> cmbCategoriaTarefaEspecificarTarefa;
    @FXML ListView<Tarefa> listViewTarefas;
    @FXML ListView<CaracterizacaoCT> listViewCaracterizacaoCTS;*/
    
    @FXML private TextField txtNomeColaborador;
    
    @FXML private TextField txtFuncaoColaborador;
    
    @FXML private TextField txtTelefoneColaborador;
    
    @FXML private TextField txtEmailColaborador;
    
    @FXML private TextField txtPasswordColaborador;
   
    @FXML Button btnRegistarColaborador;
    
    @FXML Button btnCancelarRegCol;
    
    @FXML Button btnLogout;
       
    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        registarColaboradorController = new RegistarColaboradorController();
        autenticacaoController = new AutenticacaoController();
        /*registarAreaActividadeController = new RegistarAreaActividadeController();
        registarCategoriaController = new RegistarCategoriaController();
        registarTarefaController = new RegistarTarefaController();
        registarCompetenciaTecnicaController = new RegistarCompetenciaTecnicaController();*/

        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        /*mbAreaActividadeListaTarefas.getItems().setAll(registarAreaActividadeController.getAreasActividade());
        cmbCategoriaTarefaListaTarefas.getItems().setAll(
                registarCategoriaController.getCategoriasByAreaActividade(
                        cmbAreaActividadeListaTarefas.getSelectionModel().getSelectedItem()));

        cmbAreaActividadeEspecificarTarefa.getItems().setAll(registarAreaActividadeController.getAreasActividade());
        cmbCategoriaTarefaEspecificarTarefa.getItems().setAll(
                registarCategoriaController.getCategoriasByAreaActividade(
                        cmbAreaActividadeEspecificarTarefa.getSelectionModel().getSelectedItem()));

        ListView listViewTarefas = new ListView();
        ListView listViewCompetenciasTecnicas = new ListView();
        listViewTarefas.getItems().addAll(registarTarefaController.getListTarefas());
        listViewCompetenciasTecnicas.getItems().addAll(registarCompetenciaTecnicaController.getCompetenciasTecnicasByAreaActividade(
                cmbAreaActividadeEspecificarTarefa.getSelectionModel().getSelectedItem()));*/
    }
    
    @FXML
    public void registarColaboradorAction(ActionEvent event){
        
        try{
            boolean adicionouCol = registarColaboradorController.registarColaborador(txtNomeColaborador.getText(),
                    txtEmailColaborador.getText(), txtFuncaoColaborador.getText(),
                    txtTelefoneColaborador.getText(), Rolename.COLABORADOR);
            int ultimoColAdd = registarColaboradorController.getColaboradores().size();
            txtPasswordColaborador.setText(registarColaboradorController.getColaboradores().get(ultimoColAdd).getPassword().getPasswordText());
            AlertsUI.criarAlerta(Alert.AlertType.INFORMATION, "Área do Gestor","Registar Colaborador.", adicionouCol ? "Colaborador registado com sucesso." : "Não foi possível registar o Colaborador.");
        }catch (IllegalArgumentException iae) {
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    iae.getMessage()).show();
        }        
    }
    
    @FXML
    public void cancelarRegColAction(ActionEvent event) {
        this.txtNomeColaborador.clear();
        this.txtEmailColaborador.clear();
        this.txtFuncaoColaborador.clear();
        this.txtTelefoneColaborador.clear();
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
}
