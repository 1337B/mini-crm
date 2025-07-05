package com.cbielaszczuk.crm.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {
        Connection conn = DatabaseConnection.getInstance();
        try (Statement stmt = conn.createStatement()) {

            // Create USERS
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

            // Create CLIENTS
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

            // Create PROJECTS
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

            // Create TASKS
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

            System.out.println("✅ All tables created and data seeded correctly.");

        } catch (SQLException e) {
            System.err.println("❌ Error creating tables or seeding data: " + e.getMessage());
        }
    }
}
