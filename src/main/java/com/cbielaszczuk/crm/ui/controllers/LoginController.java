package com.cbielaszczuk.crm.ui.controllers;

import com.cbielaszczuk.crm.dto.UserLoginDTO;
import com.cbielaszczuk.crm.dto.UserDTO;
import com.cbielaszczuk.crm.repository.UserRepository;
import com.cbielaszczuk.crm.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;


import java.sql.Connection;
import com.cbielaszczuk.crm.config.DatabaseConnection;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    private final UserService userService;

    public LoginController() {
        Connection conn = DatabaseConnection.getInstance();
        UserRepository repository = new UserRepository(conn);
        this.userService = new UserService(repository);
    }

    @FXML
    private void goToRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RegisterView.fxml"));
            Scene scene = new Scene(loader.load(), 400, 400);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            errorLabel.setText("Failed to load registration screen.");
        }
    }


    @FXML
    private void handleLogin() {
        try {
            String username = usernameField.getText();
            String password = passwordField.getText();

            UserLoginDTO loginDTO = new UserLoginDTO(username, password);
            UserDTO user = userService.login(loginDTO);

            System.out.println("Login successful. Loading Dashboard...");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DashboardView.fxml"));
            Parent root = loader.load();

            DashboardController dashboardController = loader.getController();
            dashboardController.setUsername(user.getUsername());

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
            stage.show();

            System.out.println("Dashboard scene loaded.");

        } catch (Exception e) {
            e.printStackTrace(); 
            errorLabel.setText(e.getMessage());
        }
    }
}
