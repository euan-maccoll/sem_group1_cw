package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 5000);

    }



    /**
     * Test case to verify that the application can connect to the database successfully.
     * This test calls the 'connect()' method of the 'App' class with the appropriate
     * connection parameters and then checks if the connection is established by calling
     * the 'isConnected()' method. The test will fail if the connection is not established
     * or if the 'isConnected()' method returns 'false'.
     */
    @Test
    void testConnect() {
        // Attempt to connect to the database
        app.connect("localhost:33060", 5000);

        // Verify that the connection is established
        assertTrue(app.isConnected(), "Failed to connect to database");
    }

    /**
     * Test case to verify that the application can disconnect from the database successfully.
     * This test calls the 'disconnect()' method of the 'App' class to close the database connection
     * and then checks if the connection is closed by calling the 'isConnected()' method. The test
     * will fail if the connection is still open or if the 'isConnected()' method returns 'true'.
     */
    @Test
    void testDisconnect() {
        // Disconnect from the database
        app.disconnect();

        // Verify that the connection is closed
        assertFalse(app.isConnected(), "Failed to disconnect from database");
    }
}