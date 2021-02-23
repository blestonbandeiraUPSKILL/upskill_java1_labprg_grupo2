package com.grupo2.t4j.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.shape.SVGPath;

/**
 * JavaFX App
 */
public class MainApp extends Application {

    public static final String TITULO_APLICACAO = "Tasks 4 Joe";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grupo2/t4j/fxml/StartingPageScene.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            //scene.getStylesheets().addAll(this.getClass().getResource("/com/grupo2/t4j/style/app.css").toExternalForm());
            scene.getStylesheets().add("com/grupo2/t4j/style/dark.css");
            //scene.getStylesheets().addAll(this.getClass().getResource("/com/grupo2/t4j/style/dark.css").toExternalForm());
            
            stage.setTitle(TITULO_APLICACAO);
            stage.setScene(scene);

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    Alert alerta = AlertsUI.criarAlerta(Alert.AlertType.CONFIRMATION,
                            TITULO_APLICACAO,
                            "Confirmação da acção",
                            "Deseja mesmo encerrar a aplicação?");

                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        windowEvent.consume();
                    }
                }
            });

            stage.show();
        }
        catch (IOException exception) {
            exception.printStackTrace();
            AlertsUI.criarAlerta(Alert.AlertType.ERROR,
                    TITULO_APLICACAO,
                    "Problemas no arranque da aplicação",
                    exception.getMessage()).show();
        }

    }

    public static void main(String[] args) {
        launch();
    }

}