package com.cbielaszczuk.crm.ui;

import com.cbielaszczuk.crm.config.DatabaseInitializer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintStream;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Redirige stdout y stderr a log.txt
            String userHome = System.getProperty("user.home");
            File logDir = new File(userHome + "/Library/Application Support/CRMApp");
            if (!logDir.exists()) logDir.mkdirs();
            File logFile = new File(logDir, "log.txt");
            PrintStream log = new PrintStream(logFile);


            // Inicializa la base
            DatabaseInitializer.initialize();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
            Scene scene = new Scene(loader.load(), 400, 300);
            primaryStage.setTitle("CRM Login");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace(); // sigue dejando el stacktrace en el archivo

            // Alerta visible si se abre con doble clic (sin terminal)
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Startup Error");
                alert.setHeaderText("Error loading application");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
