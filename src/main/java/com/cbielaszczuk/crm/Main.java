package com.cbielaszczuk.crm;

import com.cbielaszczuk.crm.config.DatabaseConnection;
import com.cbielaszczuk.crm.config.DatabaseInitializer;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getInstance();

        if (conn != null) {
            System.out.println("Connected to H2 database.");
            DatabaseInitializer.initialize(); // Llama al método que crea las tablas
        } else {
            System.err.println("Failed to connect to database.");
        }
    }
}
