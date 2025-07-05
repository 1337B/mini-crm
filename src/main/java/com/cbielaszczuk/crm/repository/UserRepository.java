package com.cbielaszczuk.crm.repository;

import com.cbielaszczuk.crm.model.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(UserModel user) {
        String sql = "INSERT INTO users (username, password, name, email, phone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error saving user: " + e.getMessage());
        }
    }

    public UserModel getById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToUser(rs);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error getting user by ID: " + e.getMessage());
        }
        return null;
    }

    public List<UserModel> getAll() {
        List<UserModel> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(mapRowToUser(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error getting all users: " + e.getMessage());
        }
        return users;
    }

    public void update(UserModel user) {
        String sql = "UPDATE users SET username = ?, password = ?, name = ?, email = ?, phone = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhone());
            stmt.setInt(6, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error updating user: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error deleting user: " + e.getMessage());
        }
    }

    public UserModel findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToUser(rs);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error finding user by username: " + e.getMessage());
        }
        return null;
    }

    private UserModel mapRowToUser(ResultSet rs) throws SQLException {
        return new UserModel(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("username"),
                rs.getString("password")
        );
    }
}
