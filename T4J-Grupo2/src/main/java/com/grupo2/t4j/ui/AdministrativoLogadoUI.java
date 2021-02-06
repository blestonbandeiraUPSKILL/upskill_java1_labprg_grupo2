package com.grupo2.t4j.ui;

import com.grupo2.t4j.controller.RegistarAreaActividadeController;
import com.grupo2.t4j.model.AreaActividade;
import com.grupo2.t4j.repository.RepositorioAreaActividade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdministrativoLogadoUI implements Initializable {

    private StartingPageUI startingPageUI;
    private Stage adicionarStage;
    private Scene sceneAddAreaActividade;
    private Scene sceneAddCategoriaTarefa;
    private Scene sceneAddCompetenciaTecnica;
    private RegistarAreaActividadeController registarAreaActividadeController;

    @FXML Button btnAddAreaAtividade;
    @FXML Button btnAddCategoriaTarefa;
    @FXML Button btnAddCompetenciaTecnica;
    @FXML ListView<AreaActividade> listaAreasAtividade;

    public void associarParentUI(StartingPageUI startingPageUI) {
        this.startingPageUI = startingPageUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adicionarStage = new Stage();
        adicionarStage.initModality(Modality.APPLICATION_MODAL);;
        adicionarStage.setResizable(false);

        ListView<AreaActividade> listaAreasAtividade = new ListView<AreaActividade>();
        listaAreasAtividade.setEditable(true);
        ObservableList<AreaActividade> areasActividade = FXCollections.observableArrayList(RepositorioAreaActividade.getInstance().getListaAreasActividade());
        listaAreasAtividade.getItems().setAll(areasActividade);



        try {
            FXMLLoader loaderAddAreaActividade = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdicionarAreaAtividadeScene.fxml"));
            Parent rootAddAreaActividade = loaderAddAreaActividade.load();
            sceneAddAreaActividade = new Scene(rootAddAreaActividade);
            sceneAddAreaActividade.getStylesheets().add("/com/grupo2/t4j/style/app.css");
            AdicionarAreaAtividadeUI adicionarAreaAtividadeUI = loaderAddAreaActividade.getController();
            adicionarAreaAtividadeUI.associarParentUI(this);

            /*FXMLLoader loaderAddCategoriaTarefa = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/AdicionarCompetenciaTecnicaScene.fxml"));
            Parent rootAddCategoriaTarefa = loaderAddCategoriaTarefa.load();
            sceneAddCategoriaTarefa = new Scene(rootAddCategoriaTarefa);
            sceneAddCategoriaTarefa.getStylesheets().add("/com/grupo2/t4j/style/app.css");
            AdicionarCategoriaTarefaUI adicionarCategoriaTarefaUI = loaderAddCategoriaTarefa.getController();
            adicionarCategoriaTarefaUI.associarParentUI(this);*/

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
    }

    public void addAreaActividade(ActionEvent actionEvent) throws IOException {
        adicionarStage.setScene(sceneAddAreaActividade);
        adicionarStage.setTitle("Adicionar Área de Actividade");
        adicionarStage.show();
    }

    public void addCategoriaTarefa(ActionEvent actionEvent) {
        adicionarStage.setScene(sceneAddCategoriaTarefa);
        adicionarStage.setTitle("Adicionar Categoria de Tarefa");
        adicionarStage.show();
    }

    public void addCompetenciaTecnica(ActionEvent actionEvent) {
        adicionarStage.setScene(sceneAddCompetenciaTecnica);
        adicionarStage.setTitle("Adicionar Competência Técnica");
        adicionarStage.show();
    }

    public void updateListViewAreaActividade() {
       listaAreasAtividade.getItems().setAll(registarAreaActividadeController.getAreasActividade());


    }
}
