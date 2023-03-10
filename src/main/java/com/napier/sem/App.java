package com.napier.sem;

import java.sql.*;
import java.util.Scanner;


public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        //testing app and DB functionality
        System.out.println("Testing app functionality..." + "\n" + "Type in city name that you want details of");

        //taking user input
        Scanner myObj = new Scanner(System.in);
        String input = "";

        // Wait for user input until a non-empty string is entered
        while (input.isEmpty()) {
            if (myObj.hasNextLine()) {
                input = myObj.nextLine();
            } else {
                // Input stream has ended
                System.out.println("No input provided. Exiting...");
                return;
            }
        }

        //get City based on user input
        City c = a.getCity(input);

        //print city info
        a.displayCity(c);

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        //for loop to try a few times in case DB is not up and loaded in time
        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * method to get a single city's details
     */
    public City getCity(String city_input)
    {
        //add quotation marks for SQL variable
        city_input = "'" + city_input + "'";
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population "
                            + "FROM city "
                            + "WHERE city.name = " + city_input;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City c = new City();
                c.city_id = rset.getInt("city.id");
                c.city_name = rset.getString("city.name");
                c.city_country_code = rset.getString("city.countrycode");
                c.city_district = rset.getString("city.district");
                c.city_population = rset.getInt("city.population");
                return c;
            }
            else
                System.out.println("Failed to get city details");
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * method to print single city's info
     */
    public void displayCity(City c)
    {
        if (c != null)
        {
            System.out.println(
                    "City id: " + c.city_id + "\n"
                            + "City name: " + c.city_name + "\n"
                            + "City country code: " + c.city_country_code + "\n"
                            + "City district: " + c.city_district + "\n"
                            + "City population: " + c.city_population + "\n");
        }
    }
}