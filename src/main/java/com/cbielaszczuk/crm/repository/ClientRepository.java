package com.cbielaszczuk.crm.repository;

import com.cbielaszczuk.crm.model.ClientModel;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    private final Connection connection;

    public ClientRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(ClientModel client) {
        String sql = "INSERT INTO clients (name, email, phone, company, notes) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getPhone());
            stmt.setString(4, client.getCompany());
            stmt.setString(5, client.getNotes());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error saving client: " + e.getMessage());
        }
    }

    public List<ClientModel> getAll() {
        List<ClientModel> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients WHERE deleted_at IS NULL";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ClientModel client = buildClientFromResultSet(rs);
                clients.add(client);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error retrieving clients: " + e.getMessage());
        }

        return clients;
    }

    public ClientModel getById(int id) {
        String sql = "SELECT * FROM clients WHERE id = ? AND deleted_at IS NULL";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return buildClientFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error finding client: " + e.getMessage());
        }

        return null;
    }

    public void update(ClientModel client) {
        String sql = "UPDATE clients SET name = ?, email = ?, phone = ?, company = ?, notes = ? WHERE id = ? AND deleted_at IS NULL";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getPhone());
            stmt.setString(4, client.getCompany());
            stmt.setString(5, client.getNotes());
            stmt.setInt(6, client.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error updating client: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "UPDATE clients SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting client: " + e.getMessage());
        }
    }

    private ClientModel buildClientFromResultSet(ResultSet rs) throws SQLException {
        ClientModel client = new ClientModel(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("company"),
                rs.getString("notes")
        );

        Timestamp ts = rs.getTimestamp("deleted_at");
        if (ts != null) {
            client.setDeletedAt(ts.toLocalDateTime());
        }

        return client;
    }
}
