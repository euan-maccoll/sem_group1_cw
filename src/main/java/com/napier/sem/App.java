package com.napier.sem;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;


public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Create new Class
        new citiesQuery();
        new countryQuery();

        // Connect to database
        a.connect();

        //testing app and DB functionality
        System.out.println("Testing app functionality..." + "\n" + "All variables currently hard coded while working on user input feature");

        //hardcoding values
        System.out.println("Hardcoding values..." + "\n" + "Country: France" + "\n" + "City: Paris" + "\n" + "Continent: Africa" + "\n" + "Region: North America" + "\n" + "District: California");
        String example_country = "France";
        String example_city = "Paris";
        String example_continent = "Africa";
        String example_region = "North America";
        String example_district = "California";



        //get City based on user input
        City c = a.getCity(example_city);

        //print city info
        a.displayCity(c);

        // Extract city population information
        System.out.println("\n" + "All cities in the world and their population (From largest to smallest)");
        ArrayList<City> Cities = citiesQuery.getAllCitiesPop(con);

        // Display all cities and population in the world
        citiesQuery.printCityPop(Cities);
        // Clearing previous queries results
        Cities.clear();

        // Extract city population information from a continent
        System.out.println("\n" + "All cities in a continent and their population (From largest to smallest)");
        Cities = new citiesQuery().getAllCitiesPopContinent(con, example_continent);

        // Display all cities and population in the selected continent
        citiesQuery.printCityPop(Cities);
        // Clearing previous queries results
        Cities.clear();

        // Extract city population information from a continent
        System.out.println("\n" + "All cities in a country and their population (From largest to smallest)");
        Cities = citiesQuery.getAllCitiesPopCountry(con, example_country);

        // Display all cities and population in the selected continent
        citiesQuery.printCityPop(Cities);
        // Clearing previous queries results
        Cities.clear();

        // Extract city population information from a continent
        System.out.println("\n" + "All cities in a region and their population (From largest to smallest)");
        Cities = citiesQuery.getAllCitiesPopRegion(con, example_region);

        // Display all cities and population in the selected continent
        citiesQuery.printCityPop(Cities);
        // Clearing previous queries results
        Cities.clear();

        // Extract city population information from a district
        System.out.println("\n" + "All cities in a district and their population (From largest to smallest)");
        Cities = citiesQuery.getAllCitiesPopDistrict(con, example_district);

        // Display all cities and population in the selected district
        citiesQuery.printCityPop(Cities);
        // Clearing previous queries results
        Cities.clear();



        // Extract country population information
        System.out.println("\n" + "All countries in the world and their population (From largest to smallest)");
        ArrayList<Country> Countries = countryQuery.getAllCountriesPop(con);

        // Display all countries and population in the world
        countryQuery.printCountryPop(Countries);
        // Clearing previous queries results
        Countries.clear();


        // Extract country population information
        System.out.println("\n" + "All countries in a continent and their population (From largest to smallest)");
        Countries = countryQuery.getAllCountriesPopContinent(con, example_continent);

        // Display all cities and population in the selected continent
        countryQuery.printCountryPop(Countries);
        // Clearing previous queries results
        Countries.clear();


        // Extract country population information
        System.out.println("\n" + "All countries in a Region and their population (From largest to smallest)");
        Countries = countryQuery.getAllCountriesPopRegion(con, example_region);

        // Display all cities and population in the selected region
        countryQuery.printCountryPop(Countries);
        // Clearing previous queries results
        Countries.clear();

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private static Connection con = null;

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