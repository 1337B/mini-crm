package com.cbielaszczuk.crm.ui.controllers;

import com.cbielaszczuk.crm.dto.UserDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    private UserDTO currentUser;

    public void setUsername(String username) {
        welcomeLabel.setText("Welcome, " + username + "!");
    }

    public void setCurrentUser(UserDTO user) {
        this.currentUser = user;
        setUsername(user.getUsername());
    }

    @FXML
    private void handleClients() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ClientManagementView.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Manage Clients");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load client management screen");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleProjects() {
        System.out.println("Navigating to Project Management...");
        // TODO: Load ProjectManagementView.fxml
    }

    @FXML
    private void handleTasks() {
        System.out.println("Navigating to Task Management...");
        // TODO: Load TaskManagementView.fxml
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("CRM Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
