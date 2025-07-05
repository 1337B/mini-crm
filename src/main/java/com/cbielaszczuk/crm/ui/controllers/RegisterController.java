package com.cbielaszczuk.crm.ui.controllers;

import com.cbielaszczuk.crm.config.DatabaseConnection;
import com.cbielaszczuk.crm.dto.UserRegistrationDTO;
import com.cbielaszczuk.crm.repository.UserRepository;
import com.cbielaszczuk.crm.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import java.io.IOException;
import java.sql.Connection;
import javafx.animation.FadeTransition;


public class RegisterController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label errorLabel;

    private final UserService userService;
    @FXML
    private Label successLabel;

    public RegisterController() {
        Connection conn = DatabaseConnection.getInstance();
        this.userService = new UserService(new UserRepository(conn));
    }

    @FXML
    private void handleRegister() {
        try {
            // Validación y registro
            UserRegistrationDTO dto = new UserRegistrationDTO(
                    nameField.getText(),
                    emailField.getText(),
                    phoneField.getText(),
                    usernameField.getText(),
                    passwordField.getText(),
                    confirmPasswordField.getText()
            );

            userService.register(dto);

            successLabel.setText("Registered successfully!");
            successLabel.setVisible(true);

            // Fade in
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), successLabel);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setOnFinished(e -> {
                // Después de 2 segundos, volver al login
                PauseTransition wait = new PauseTransition(Duration.seconds(1));
                wait.setOnFinished(event -> goToLogin());
                wait.play();
            });
            fadeIn.play();

        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }


    @FXML
    private void goToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) successLabel.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
