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

        // Connect to database
        a.connect();

        //testing app and DB functionality
        System.out.println("Testing app functionality..." + "\n" + "All variables currently hard coded while working on user input feature");

        //hardcoding values
        System.out.println("Hardcoding values..." + "\n" + "Country: France" + "\n" + "City: Paris");
        String example_country = "France";
        String example_city = "Paris";


        //get City based on user input
        City c = a.getCity(example_city);

        //print city info
        a.displayCity(c);

        // Extract city population information
        System.out.println("\n" + "All cities in the world and their population (From largest to smallest)");
        ArrayList<City> Cities = a.getAllCitiesPop();

        // Display all cities and population in the world
        a.printCityPop(Cities);
        // Clearing previous queries results
        Cities.clear();

        // Extract city population information from a continent
        System.out.println("\n" + "All cities in a continent (Africa is hardcoded) and their population (From largest to smallest)");
        Cities = a.getAllCitiesPopContinent();

        // Display all cities and population in the selected continent (Africa hardcoded to test)
        a.printCityPop(Cities);
        // Clearing previous queries results
        Cities.clear();

        // Extract city population information from a continent
        System.out.println("\n" + "All cities in a country (United Kingdom is hardcoded) and their population (From largest to smallest)");
        Cities = a.getAllCitiesPopCountry();

        // Display all cities and population in the selected continent (United Kingdom hardcoded to test)
        a.printCityPop(Cities);
        // Clearing previous queries results
        Cities.clear();

        // Extract city population information from a continent
        System.out.println("\n" + "All cities in a region (North America is hardcoded) and their population (From largest to smallest)");
        Cities = a.getAllCitiesPopRegion();

        // Display all cities and population in the selected continent (North America hardcoded to test)
        a.printCityPop(Cities);
        // Clearing previous queries results
        Cities.clear();

        // Extract city population information from a district
        System.out.println("\n" + "All cities in a district (california is hardcoded) and their population (From largest to smallest)");
        Cities = a.getAllCitiesPopDistrict();

        // Display all cities and population in the selected district (california hardcoded to test)
        a.printCityPop(Cities);
        // Clearing previous queries results
        Cities.clear();



        // Extract country population information
        System.out.println("\n" + "All countries in the world and their population (From largest to smallest)");
        ArrayList<Country> Countries = a.getAllCountriesPop();

        // Display all countries and population in the world
        a.printCountryPop(Countries);
        // Clearing previous queries results
        Countries.clear();


        // Extract country population information
        System.out.println("\n" + "All countries in a continent (Europe is hardcoded) and their population (From largest to smallest)");
        Countries = a.getAllCountriesPopContinent();

        // Display all countries and population from a continent
        a.printCountryPop(Countries);
        // Clearing previous queries results
        Countries.clear();

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

    /**
     * method to get all cities populations in the world (largest to smallest)
     */
    public ArrayList<City> getAllCitiesPop()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population  "
                            + "FROM city "
                            + "ORDER BY city.population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> Cities = new ArrayList<City>();
            while (rset.next())
            {
                City c = new City();
                c.city_id = rset.getInt("city.id");
                c.city_name = rset.getString("city.name");
                c.city_country_code = rset.getString("city.countrycode");
                c.city_district = rset.getString("city.district");
                c.city_population = rset.getInt("city.population");
                Cities.add(c);
            }
            return Cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * Prints a list of cities and their populations.
     * @param Cities The list of cities to print.
     */
    public void printCityPop(ArrayList<City> Cities)
    {
        // Print header
        System.out.println(String.format("%-30s %-15s", "City Name", "City Population"));
        // Loop over all cities in the list
        for (City c : Cities)
        {
            String c_string =
                    String.format("%-30s %-15s", c.city_name, c.city_population);
            System.out.println(c_string);
        }
    }

    /**
     * method to get all cities populations in a continent (largest to smallest)
     */
    public ArrayList<City> getAllCitiesPopContinent()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population, country.continent "
                            + "FROM city, country "
                            + "WHERE country.code = city.countrycode AND country.continent = 'Africa'"
                            + "ORDER BY city.population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> Cities = new ArrayList<City>();
            while (rset.next())
            {
                City c = new City();
                c.city_id = rset.getInt("city.id");
                c.city_name = rset.getString("city.name");
                c.city_country_code = rset.getString("city.countrycode");
                c.city_district = rset.getString("city.district");
                c.city_population = rset.getInt("city.population");
                Cities.add(c);
            }
            return Cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * method to get all cities populations in a country (largest to smallest)
     */
    public ArrayList<City> getAllCitiesPopCountry()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population, country.name "
                            + "FROM city, country "
                            + "WHERE country.code = city.countrycode AND country.name = 'United Kingdom'"
                            + "ORDER BY city.population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> Cities = new ArrayList<City>();
            while (rset.next())
            {
                City c = new City();
                c.city_id = rset.getInt("city.id");
                c.city_name = rset.getString("city.name");
                c.city_country_code = rset.getString("city.countrycode");
                c.city_district = rset.getString("city.district");
                c.city_population = rset.getInt("city.population");
                Cities.add(c);
            }
            return Cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * method to get all cities populations in a region (largest to smallest)
     */
    public ArrayList<City> getAllCitiesPopRegion()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population, country.region "
                            + "FROM city, country "
                            + "WHERE country.code = city.countrycode AND country.region = 'North America'"
                            + "ORDER BY city.population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> Cities = new ArrayList<City>();
            while (rset.next())
            {
                City c = new City();
                c.city_id = rset.getInt("city.id");
                c.city_name = rset.getString("city.name");
                c.city_country_code = rset.getString("city.countrycode");
                c.city_district = rset.getString("city.district");
                c.city_population = rset.getInt("city.population");
                Cities.add(c);
            }
            return Cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * method to get all cities populations in a district (largest to smallest)
     */

    public ArrayList<City> getAllCitiesPopDistrict()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population "
                            + "FROM city "
                            + "WHERE city.district = 'California'"
                            + "ORDER BY city.population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> Cities = new ArrayList<City>();
            while (rset.next())
            {
                City c = new City();
                c.city_id = rset.getInt("city.id");
                c.city_name = rset.getString("city.name");
                c.city_country_code = rset.getString("city.countrycode");
                c.city_district = rset.getString("city.district");
                c.city_population = rset.getInt("city.population");
                Cities.add(c);
            }
            return Cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }


    /**
     * Prints a list of countries and their populations.
     * @param Countries The list of cities to print.
     */
    public void printCountryPop(ArrayList<Country> Countries)
    {
        // Print header
        System.out.println(String.format("%-30s %-15s", "Country Name", "Country Population"));
        // Loop over all countries in the list
        for (Country c : Countries)
        {
            String c_string =
                    String.format("%-30s %-15s", c.country_name, c.country_population);
            System.out.println(c_string);
        }
    }



    /**
     * method to get all countries populations in the world (largest to smallest)
     */
    public ArrayList<Country> getAllCountriesPop()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.population  "
                            + "FROM country "
                            + "ORDER BY country.population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> Countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country c = new Country();
                c.country_id = rset.getString("country.code");
                c.country_name = rset.getString("country.name");
                c.country_population = rset.getInt("country.population");
                Countries.add(c);
            }
            return Countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * method to get all countries populations in the world (largest to smallest)
     */
    public ArrayList<Country> getAllCountriesPopContinent()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.population  "
                            + "FROM country "
                            + "WHERE country.continent = 'Europe'"
                            + "ORDER BY country.population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> Countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country c = new Country();
                c.country_id = rset.getString("country.code");
                c.country_name = rset.getString("country.name");
                c.country_population = rset.getInt("country.population");
                Countries.add(c);
            }
            return Countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
}