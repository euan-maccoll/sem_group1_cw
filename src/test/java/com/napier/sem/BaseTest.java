package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseTest {
    static Connection con;

    @BeforeAll
    static void init() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Create a connection object
            con = DriverManager.getConnection("jdbc:mysql://localhost:33061/testing_world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
            // Get the current database name
            String dbName = con.getCatalog();
            System.out.println("Connected to database: " + dbName);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Could not connect to database");
            System.exit(-1);
        }
    }

    @AfterAll
    static void tearDown() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
}
