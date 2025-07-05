package com.cbielaszczuk.crm.repository;

import com.cbielaszczuk.crm.model.ProjectModel;
import com.cbielaszczuk.crm.model.ProjectStatusEnum;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {

    private final Connection connection;

    public ProjectRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(ProjectModel project) {
        String sql = "INSERT INTO projects (title, description, status, client_id, start_date, due_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getDescription());
            stmt.setString(3, project.getStatus().name());
            stmt.setInt(4, project.getClientId());
            stmt.setObject(5, project.getStartDate());
            stmt.setObject(6, project.getDueDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error saving project: " + e.getMessage());
        }
    }

    public List<ProjectModel> getAll() {
        List<ProjectModel> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects WHERE deleted_at IS NULL";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                projects.add(buildProjectFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error retrieving projects: " + e.getMessage());
        }

        return projects;
    }

    public ProjectModel getById(int id) {
        String sql = "SELECT * FROM projects WHERE id = ? AND deleted_at IS NULL";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return buildProjectFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error finding project: " + e.getMessage());
        }

        return null;
    }

    public void update(ProjectModel project) {
        String sql = "UPDATE projects SET title = ?, description = ?, status = ?, client_id = ?, start_date = ?, due_date = ? WHERE id = ? AND deleted_at IS NULL";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getDescription());
            stmt.setString(3, project.getStatus().name());
            stmt.setInt(4, project.getClientId());
            stmt.setObject(5, project.getStartDate());
            stmt.setObject(6, project.getDueDate());
            stmt.setInt(7, project.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error updating project: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "UPDATE projects SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error deleting project: " + e.getMessage());
        }
    }

    // ——————————————————————————————
    // 🔧 Utilidad: mapear ResultSet → Modelo
    // ——————————————————————————————
    private ProjectModel buildProjectFromResultSet(ResultSet rs) throws SQLException {
        ProjectModel project = new ProjectModel(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                ProjectStatusEnum.valueOf(rs.getString("status")),
                rs.getInt("client_id")
        );

        LocalDate start = rs.getObject("start_date", LocalDate.class);
        LocalDate due = rs.getObject("due_date", LocalDate.class);
        Timestamp deletedTs = rs.getTimestamp("deleted_at");

        if (start != null) project.setStartDate(start);
        if (due != null) project.setDueDate(due);
        if (deletedTs != null) project.setDeletedAt(deletedTs.toLocalDateTime());

        return project;
    }
}
