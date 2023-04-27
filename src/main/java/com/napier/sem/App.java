package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;


public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        if(args.length <1){
            a.connect("localhost:33060", 100);
        }else{
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        // Create new Class
        new citiesQuery();
        new countryQuery();
        new capitalCitiesQuery();
        new languageQuery();
        new comparisonQuery();


        //testing app and DB functionality
        System.out.println("Testing app functionality..." + "\n" + "All variables currently hard coded while working on user input feature");

        //hardcoding values
        System.out.println("Hardcoding values..." + "\n" + "Country: France" + "\n" + "City: Paris" + "\n" + "Continent: Africa" + "\n" + "Region: North America" + "\n" + "District: California" + "\n" + "Results Limit: 0");
        String example_country = "France";
        String example_city = "Paris";
        String example_continent = "Africa";
        String example_region = "North America";
        String example_district = "California";
        int example_limit = 3;

        System.out.println("Displaying languages:");
        ArrayList<Language> languages = languageQuery.getLanguages(con);
        languageQuery.printLanguages(languages);



        /*
        // Calling all countryQueries
        System.out.println("Calling all country queries");
        countryQueries(example_limit, example_continent, example_region);

        // Calling all cityQueries
        System.out.println("Calling all city queries");
        cityQueries(example_limit, example_continent, example_region, example_country, example_district);

        City aCity = a.getCity(example_city);
        a.displayCity(aCity);
        // Calling all capitalCity Queries
        System.out.println("Calling all capital city queries");
        capitalCityQueries(example_limit, example_continent, example_region, example_country, example_district);


        // Testing Queries with limits
        System.out.println("\n" + "Hardcoding values..." + "\n" + "Limit: 3");
        example_limit = 3;

        // Calling city queries with limit of 3
        cityQueries(example_limit, example_continent, example_region, example_country, example_district);

        // Calling country queries with limit of 3
        System.out.println("Calling country queries with limit of 3");
        countryQueries(example_limit, example_continent, example_region);
        */

        // Calling population comparison queries
        System.out.println("Calling  population comparison queries");
        populationComparisonQueries(example_continent, example_region, example_country);

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
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");

                // Get the current database name
                System.out.println("Successfully connected");
                String dbName = con.getCatalog();
                System.out.println("Connected to database: " + dbName);
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " +                                  Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Check whether the database connection is established or not.
     *
     * @return true if the connection is established, false otherwise
     */
    public boolean isConnected() {
        return con != null;
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            } finally {
                // Set connection object to null
                con = null;
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
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population, country.name "
                            + "FROM city, country "
                            + "WHERE country.code = city.countrycode AND city.name = "  + city_input;
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
                c.country_name = rset.getString("country.name");
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
                            + "City population: " + c.city_population + "\n"
                            + "City country: "  + c.country_name);
        }
    }
    public static void countryQueries(int example_limit, String example_continent, String example_region){
        // Extract country population information
        System.out.println("\n" + "All countries in the world and their population (From largest to smallest)");
        ArrayList<Country> Countries = countryQuery.getAllCountriesPop(con, example_limit);

        // Display all countries and population in the world
        countryQuery.printCountries(Countries);
        // Clearing previous queries results
        Countries.clear();


        // Extract country population information
        System.out.println("\n" + "All countries in a continent and their population (From largest to smallest)");
        Countries = countryQuery.getAllCountriesPopContinent(con, example_continent, example_limit);

        // Display all cities and population in the selected continent
        countryQuery.printCountries(Countries);
        // Clearing previous queries results
        Countries.clear();


        // Extract country population information
        System.out.println("\n" + "All countries in a Region and their population (From largest to smallest)");
        Countries = countryQuery.getAllCountriesPopRegion(con, example_region, example_limit);

        // Display all cities and population in the selected region
        countryQuery.printCountries(Countries);
        // Clearing previous queries results
        Countries.clear();
    }

    public static void cityQueries(int example_limit, String example_continent, String example_region, String example_country, String example_district){
        // Extract city population information
        System.out.println("\n" + "All cities in the world and their population (From largest to smallest)");
        ArrayList<City> Cities = citiesQuery.getAllCitiesPop(con, example_limit);

        // Display all cities and population in the world
        citiesQuery.printCities(Cities);
        // Clearing previous queries results
        Cities.clear();

        // Extract city population information from a continent
        System.out.println("\n" + "All cities in a continent and their population (From largest to smallest)");
        Cities = citiesQuery.getAllCitiesPopContinent(con, example_continent, example_limit);

        // Display all cities and population in the selected continent
        citiesQuery.printCities(Cities);
        // Clearing previous queries results
        Cities.clear();

        // Extract city population information from a continent
        System.out.println("\n" + "All cities in a country and their population (From largest to smallest)");
        Cities = citiesQuery.getAllCitiesPopCountry(con, example_country, example_limit);

        // Display all cities and population in the selected continent
        citiesQuery.printCities(Cities);
        // Clearing previous queries results
        Cities.clear();

        // Extract city population information from a continent
        System.out.println("\n" + "All cities in a region and their population (From largest to smallest)");
        Cities = citiesQuery.getAllCitiesPopRegion(con, example_region, example_limit);

        // Display all cities and population in the selected continent
        citiesQuery.printCities(Cities);
        // Clearing previous queries results
        Cities.clear();

        // Extract city population information from a district
        System.out.println("\n" + "All cities in a district and their population (From largest to smallest)");
        Cities = citiesQuery.getAllCitiesPopDistrict(con, example_district, example_limit);

        // Display all cities and population in the selected district
        citiesQuery.printCities(Cities);
        // Clearing previous queries results
        Cities.clear();
    }

    public static void capitalCityQueries(int example_limit, String example_continent, String example_region, String example_country, String example_district){


        // All capital cities in the world
        System.out.println("All capital cities in the world and their population (From largest to smallest)");
        ArrayList<City> CapitalCities = capitalCitiesQuery.getAllCapitalCitiesPop(con, example_limit);

        // Display all capitals in the world
        capitalCitiesQuery.printCapitalCityPop(CapitalCities);
        // Clearing previous query results
        CapitalCities.clear();


        // All capital cities in a continent
        System.out.println("All capital cities in a specific continent");
        CapitalCities = capitalCitiesQuery.getCapitalCitiesByContinent(con, example_continent, example_limit);

        // Display all capitals in a continent
        capitalCitiesQuery.printCapitalCityPop(CapitalCities);
        // Clearing previous query results
        CapitalCities.clear();


        // All capital cities in a region
        System.out.println("All capital cities in a specific region");
        CapitalCities = capitalCitiesQuery.getCapitalCitiesByRegion(con, example_region, example_limit);

        // Display all capitals in a region
        capitalCitiesQuery.printCapitalCityPop(CapitalCities);
        // Clearing previous query results
        CapitalCities.clear();
    }

    public static void populationComparisonQueries(String example_continent, String example_region, String example_country){

        //Population comparison for continent (north america)
        Continent continent = comparisonQuery.getContinentPopulation(con, "Asia");
        comparisonQuery.printPopContinentComparison(continent);

        //Population comparison for region (north america)
        Region region = comparisonQuery.getPopRegionComparison(con, example_region);
        comparisonQuery.printPopRegionComparison(region);

        //Population comparison for country
        Country country = comparisonQuery.getPopCountryComparison(con, example_country);
        comparisonQuery.printPopCountryComparison(country);
    }
}