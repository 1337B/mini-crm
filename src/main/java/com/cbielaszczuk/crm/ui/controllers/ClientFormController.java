package com.cbielaszczuk.crm.ui.controllers;

import com.cbielaszczuk.crm.config.DatabaseConnection;
import com.cbielaszczuk.crm.dto.ClientDTO;
import com.cbielaszczuk.crm.repository.ClientRepository;
import com.cbielaszczuk.crm.service.ClientService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ClientFormController {

    @FXML private Label formTitle;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField companyField;
    @FXML private TextArea notesField;

    private ClientService clientService;
    private ClientDTO editingClient; // null si es creación

    private Runnable onSaveCallback; // para refrescar la tabla luego

    public ClientFormController() {
        clientService = new ClientService(new ClientRepository(DatabaseConnection.getInstance()));
    }

    public void setClientData(ClientDTO client) {
        this.editingClient = client;
        formTitle.setText(client == null ? "Add Client" : "Edit Client");

        if (client != null) {
            nameField.setText(client.getName());
            emailField.setText(client.getEmail());
            phoneField.setText(client.getPhone());
            companyField.setText(client.getCompany());
            notesField.setText(client.getNotes());
        }
    }

    public void setOnSaveCallback(Runnable callback) {
        this.onSaveCallback = callback;
    }

    @FXML
    private void handleSave() {
        try {
            ClientDTO dto = new ClientDTO(
                    editingClient != null ? editingClient.getId() : 0,
                    nameField.getText(),
                    emailField.getText(),
                    phoneField.getText(),
                    companyField.getText(),
                    notesField.getText()
            );

            if (editingClient == null) {
                clientService.createClient(dto);
            } else {
                clientService.updateClient(dto);
            }

            if (onSaveCallback != null) onSaveCallback.run();
            ((Stage) nameField.getScene().getWindow()).close();

        } catch (Exception e) {
            showError("Validation failed", e.getMessage());
        }
    }

    @FXML
    private void handleCancel() {
        ((Stage) nameField.getScene().getWindow()).close();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
