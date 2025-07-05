package com.cbielaszczuk.crm.repository;

import com.cbielaszczuk.crm.model.TaskModel;
import com.cbielaszczuk.crm.model.TaskStatusEnum;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private final Connection connection;

    public TaskRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(TaskModel task) {
        String sql = "INSERT INTO tasks (title, description, status, project_id, start_date, due_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getStatus().name());
            stmt.setInt(4, task.getProjectId());
            stmt.setDate(5, task.getStartDate() != null ? Date.valueOf(task.getStartDate()) : null);
            stmt.setDate(6, task.getDueDate() != null ? Date.valueOf(task.getDueDate()) : null);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving task: " + e.getMessage());
        }
    }

    public List<TaskModel> getAll() {
        List<TaskModel> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE deleted_at IS NULL";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tasks.add(buildTaskFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving tasks: " + e.getMessage());
        }

        return tasks;
    }

    public TaskModel getById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ? AND deleted_at IS NULL";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return buildTaskFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving task by ID: " + e.getMessage());
        }

        return null;
    }

    public void update(TaskModel task) {
        String sql = "UPDATE tasks SET title = ?, description = ?, status = ?, project_id = ?, start_date = ?, due_date = ? WHERE id = ? AND deleted_at IS NULL";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getStatus().name());
            stmt.setInt(4, task.getProjectId());
            stmt.setDate(5, task.getStartDate() != null ? Date.valueOf(task.getStartDate()) : null);
            stmt.setDate(6, task.getDueDate() != null ? Date.valueOf(task.getDueDate()) : null);
            stmt.setInt(7, task.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating task: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "UPDATE tasks SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting task: " + e.getMessage());
        }
    }

    private TaskModel buildTaskFromResultSet(ResultSet rs) throws SQLException {
        LocalDate startDate = rs.getDate("start_date") != null ? rs.getDate("start_date").toLocalDate() : null;
        LocalDate dueDate = rs.getDate("due_date") != null ? rs.getDate("due_date").toLocalDate() : null;

        return new TaskModel(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                TaskStatusEnum.valueOf(rs.getString("status")),
                rs.getInt("project_id"),
                startDate,
                dueDate
        );
    }
}
