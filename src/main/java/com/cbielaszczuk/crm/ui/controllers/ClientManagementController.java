package com.cbielaszczuk.crm.ui.controllers;

import com.cbielaszczuk.crm.config.DatabaseConnection;
import com.cbielaszczuk.crm.dto.ClientDTO;
import com.cbielaszczuk.crm.mapper.ClientMapper;
import com.cbielaszczuk.crm.model.ClientModel;
import com.cbielaszczuk.crm.repository.ClientRepository;
import com.cbielaszczuk.crm.service.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.List;

public class ClientManagementController {

    @FXML
    private TableView<ClientDTO> clientTable;
    @FXML
    private TableColumn<ClientDTO, Integer> idColumn;
    @FXML
    private TableColumn<ClientDTO, String> nameColumn;
    @FXML
    private TableColumn<ClientDTO, String> emailColumn;
    @FXML
    private TableColumn<ClientDTO, String> phoneColumn;
    @FXML
    private TableColumn<ClientDTO, String> companyColumn;

    private final ClientService clientService;

    public ClientManagementController() {
        Connection conn = DatabaseConnection.getInstance();
        ClientRepository repository = new ClientRepository(conn);
        this.clientService = new ClientService(repository);
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));

        loadClients();
    }

    private void loadClients() {
        List<ClientDTO> clients = clientService.getAllClients();
        ObservableList<ClientDTO> observableClients = FXCollections.observableArrayList(clients);
        clientTable.setItems(observableClients);
    }

    @FXML
    private void handleAdd() {
        showClientForm(null);
    }

    @FXML
    private void handleEdit() {
        ClientDTO selected = clientTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No selection", "Please select a client to edit.");
            return;
        }
        showClientForm(selected);
    }

    private void showClientForm(ClientDTO client) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ClientFormView.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle(client == null ? "Add Client" : "Edit Client");

            ClientFormController controller = loader.getController();
            controller.setClientData(client);
            controller.setOnSaveCallback(this::loadClients);

            stage.show();

        } catch (Exception e) {
            showAlert("Error", "Could not load client form.");
        }
    }

    @FXML
    private void handleDelete() {
        ClientDTO selected = clientTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No selection", "Please select a client to delete.");
            return;
        }

        try {
            clientService.deleteClient(selected.getId());
            loadClients();
        } catch (Exception e) {
            showAlert("Error", e.getMessage());
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DashboardView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) clientTable.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            showAlert("Error", "Could not go back to dashboard.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
