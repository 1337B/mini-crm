package com.cbielaszczuk.crm.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDatabaseInitializer {

    public static void initialize() {
        Connection conn = DatabaseConnection.getTestInstance();

        try (Statement stmt = conn.createStatement()) {

            // Create USERS with all fields
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255),
                    email VARCHAR(255),
                    phone VARCHAR(255),
                    username VARCHAR(255) UNIQUE,
                    password VARCHAR(255)
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS clients (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255),
                    email VARCHAR(255),
                    phone VARCHAR(255),
                    company VARCHAR(255),
                    notes TEXT,
                    deleted_at TIMESTAMP DEFAULT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS projects (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    title VARCHAR(255),
                    description TEXT,
                    start_date DATE,
                    due_date DATE,
                    status VARCHAR(50),
                    client_id INT,
                    FOREIGN KEY (client_id) REFERENCES clients(id),
                    deleted_at TIMESTAMP DEFAULT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS tasks (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    title VARCHAR(255),
                    description TEXT,
                    start_date DATE,
                    due_date DATE,
                    status VARCHAR(50),
                    project_id INT,
                    FOREIGN KEY (project_id) REFERENCES projects(id),
                    deleted_at TIMESTAMP DEFAULT NULL
                )
            """);

            // Clear all respecting FK order
            stmt.execute("DELETE FROM tasks");
            stmt.execute("DELETE FROM projects");
            stmt.execute("DELETE FROM clients");
            stmt.execute("DELETE FROM users");

            // Sample users
            stmt.execute("""
                INSERT INTO users (name, email, phone, username, password) VALUES
                ('Admin', 'admin@test.com', '000', 'admin', 'admin123'),
                ('User', 'user@test.com', '111', 'user', 'user123')
            """);

            // Sample clients
            stmt.execute("""
                INSERT INTO clients (id, name, email, phone, company, notes) VALUES
                (1, 'Cliente Uno', 'uno@test.com', '123', 'Empresa1', 'Nota1'),
                (2, 'Cliente Dos', 'dos@test.com', '456', 'Empresa2', 'Nota2')
            """);

            // Sample projects
            stmt.execute("""
                INSERT INTO projects (id, title, description, start_date, due_date, status, client_id) VALUES
                (1, 'Proyecto 1', 'Desc 1', CURRENT_DATE, CURRENT_DATE + 10, 'IN_PROGRESS', 1),
                (2, 'Proyecto 2', 'Desc 2', CURRENT_DATE, CURRENT_DATE + 5, 'ON_HOLD', 2)
            """);

            // Sample tasks
            stmt.execute("""
                INSERT INTO tasks (title, description, start_date, due_date, status, project_id) VALUES
                ('Tarea 1', 'Detalle tarea 1', CURRENT_DATE, CURRENT_DATE + 5, 'IN_PROGRESS', 1),
                ('Tarea 2', 'Detalle tarea 2', CURRENT_DATE + 1, CURRENT_DATE + 6, 'ON_HOLD', 1)
            """);

            System.out.println("✅ Test DB initialized successfully.");

        } catch (SQLException e) {
            System.err.println("❌ Error creating tables or seeding data: " + e.getMessage());
        }
    }
}
