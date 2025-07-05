package com.cbielaszczuk.crm.config;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;

    private static final String USER = "sa";
    private static final String PASSWORD = "";

    /**
     * Returns the singleton connection to the production H2 database.
     */
    public static Connection getInstance() {
        if (connection == null) {
            try {
                String dbPath = getDatabasePath(false);
                connection = DriverManager.getConnection("jdbc:h2:" + dbPath, USER, PASSWORD);
                System.out.println("✅ H2 connection established.");
            } catch (SQLException e) {
                System.err.println("❌ Failed to connect to H2:");
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * Returns the singleton connection to the test H2 database.
     */
    public static Connection getTestInstance() {
        if (connection == null) {
            try {
                String dbPath = getDatabasePath(true);
                connection = DriverManager.getConnection("jdbc:h2:" + dbPath, USER, PASSWORD);
                System.out.println("✅ H2 test connection established.");
            } catch (SQLException e) {
                System.err.println("❌ Failed to connect to H2 test:");
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * Builds the path to the H2 database, compatible with macOS and Windows.
     */
    private static String getDatabasePath(boolean isTest) {
        String userHome = System.getProperty("user.home");
        String baseDir = userHome + File.separator + "CRMApp" + File.separator + "database";
        File dir = new File(baseDir);
        if (!dir.exists()) dir.mkdirs();

        String dbName = isTest ? "test_crm" : "crm";
        return baseDir + File.separator + dbName;
    }
}
