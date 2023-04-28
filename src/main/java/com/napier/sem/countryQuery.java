package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class countryQuery {
    /**
     * Prints a list of countries and their populations.
     * @param Countries The list of countries to print.
     */
    public static void printCountries(ArrayList<Country> Countries)
    {
        //Check cities is not null
        if (Countries == null) {
            System.out.println("No countries");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s", "Country Code", "Country Name", "Continent Name", "Region Name", "Country Population", "Capital ID"));
        // Loop over all countries in the list
        for (Country c : Countries)
        {
            if (c == null)
                continue;
            String c_string =
                    String.format("%-30s %-30s %-30s %-30s %-30s %-30s", c.country_id, c.country_name, c.country_continent, c.country_region, c.country_population, c.country_capital);
            System.out.println(c_string);
        }
    }

    /**
     * method to get all countries populations in the world (largest to smallest)
     * @param con The database connection object.
     * @param limit The number of results to limit the query to.
     * @return An ArrayList of Country objects representing the results of the query.
     */
    public static ArrayList<Country> getAllCountriesPop(Connection con, int limit)
    {
        //check for negative limit
        if (limit < 0) {
            System.err.println("Invalid limit parameter: " + limit);
            return null;
        }
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.population, country.continent, country.region, country.capital  "
                            + "FROM country "
                            + "ORDER BY country.population DESC";
            if(limit > 0){
                strSelect += " LIMIT " + limit;
            }
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
                c.country_continent = rset.getString("country.continent");
                c.country_region = rset.getString("country.region");
                c.country_capital = rset.getInt("country.capital");
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
     * method to get all countries populations in a given continent (largest to smallest)
     * @param con The database connection object.
     * @param continent_input The name of the continent to search for.
     * @param limit The number of results to limit the query to.
     * @return An ArrayList of Country objects representing the results of the query.
     */
    public static ArrayList<Country> getAllCountriesPopContinent(Connection con, String continent_input, int limit)
    {
        //check for negative limit
        if (limit < 0) {
            System.err.println("Invalid limit parameter: " + limit);
            return null;
        }

        //add quotation marks for SQL variable
        continent_input = "'" + continent_input + "'";
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.population, country.continent, country.region, country.capital  "
                            + "FROM country "
                            + "WHERE country.continent = " + continent_input
                            + "ORDER BY country.population DESC";
            if(limit > 0){
                strSelect += " LIMIT " + limit;
            }
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
                c.country_continent = rset.getString("country.continent");
                c.country_region = rset.getString("country.region");
                c.country_capital = rset.getInt("country.capital");
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
     * method to get all countries populations in a given region (largest to smallest)
     * @param con The database connection object.
     * @param input_region The name of the region to search for.
     * @param limit The number of results to limit the query to.
     * @return An ArrayList of Country objects representing the results of the query.
     */
    public static ArrayList<Country> getAllCountriesPopRegion(Connection con, String input_region, int limit)
    {
        //check for negative limit
        if (limit < 0) {
            System.err.println("Invalid limit parameter: " + limit);
            return null;
        }
        //add quotation marks for SQL variable
        input_region = "'" + input_region + "'";
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.population, country.continent, country.region, country.capital  "
                            + "FROM country "
                            + "WHERE country.region = " + input_region
                            + "ORDER BY country.population DESC";
            if(limit > 0){
                strSelect += " LIMIT " + limit;
            }
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
                c.country_continent = rset.getString("country.continent");
                c.country_region = rset.getString("country.region");
                c.country_capital = rset.getInt("country.capital");
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
