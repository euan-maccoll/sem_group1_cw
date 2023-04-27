package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class capitalCitiesQuery {
    /**
     * Prints a list of cities and their populations.
     *
     * @param CapitalCities The list of cities to print.
     */

    public static void printCapitalCityPop(ArrayList<City> CapitalCities) {
        //Check cities is not null
        if (CapitalCities == null) {
            System.out.println("No Capital cities");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s", "Capital City Name", "Capital City Country", "Capital City Population"));
        // Loop over all cities in the list
        for (City c : CapitalCities) {
            if (c == null)
                continue;
            String c_string =
                    String.format("%-30s %-30s %-30s", c.city_name, c.country_name, c.city_population);
            System.out.println(c_string);
        }
    }


    /**
     * method to get all capital city populations in the world (largest to smallest)
     */
    public static ArrayList<City> getAllCapitalCitiesPop(Connection con, int limit) {
        //check if limit is not negative
        if (limit < 0) {
            System.err.println("Invalid limit parameter: " + limit);
            return null;
        }
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population, country.name "
                            + "FROM city "
                            + "JOIN country ON city.id = country.capital "
                            + "WHERE city.id = country.capital "
                            + "ORDER BY city.population DESC";
            if(limit > 0){
                strSelect += " LIMIT " + limit;
            }
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> Cities = new ArrayList<City>();
            while (rset.next()) {
                City c = new City();
                c.city_id = rset.getInt("id");
                c.city_name = rset.getString("name");
                c.city_country_code = rset.getString("countrycode");
                c.city_district = rset.getString("district");
                c.city_population = rset.getInt("population");
                c.country_name = rset.getString("country.name");
                Cities.add(c);
            }
            return Cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city details");
            return null;
        }
    }

    public static ArrayList<City> getCapitalCitiesByContinent(Connection con, String continent_input, int limit) {
        //check if limit is not negative
        if (limit < 0) {
            System.err.println("Invalid limit parameter: " + limit);
            return null;
        }
        //add quotation marks for SQL variable
        continent_input = "'" + continent_input + "'";
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population, country.name "
                            + "FROM city "
                            + "JOIN country ON city.id = country.capital "
                            + "WHERE city.id = country.capital "
                            + "AND country.continent = " + continent_input + " "
                            + "ORDER BY city.population DESC";
            if(limit > 0){
                strSelect += " LIMIT " + limit;
            }
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> Cities = new ArrayList<City>();
            while (rset.next()) {
                City c = new City();
                c.city_id = rset.getInt("id");
                c.city_name = rset.getString("name");
                c.city_country_code = rset.getString("countrycode");
                c.city_district = rset.getString("district");
                c.city_population = rset.getInt("population");
                c.country_name = rset.getString("country.name");
                Cities.add(c);
            }
            return Cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city details");
            return null;
        }
    }

    public static ArrayList<City> getCapitalCitiesByRegion(Connection con, String region_input, int limit) {
        //check if limit is not negative
        if (limit < 0) {
            System.err.println("Invalid limit parameter: " + limit);
            return null;
        }
        //add quotation marks for SQL variable
        region_input = "'" + region_input + "'";
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population, country.name "
                            + "FROM city "
                            + "JOIN country ON city.id = country.capital "
                            + "WHERE city.id = country.capital "
                            + "AND country.region = " + region_input + " "
                            + "ORDER BY city.population DESC";
            if(limit > 0){
                strSelect += " LIMIT " + limit;
            }
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> Cities = new ArrayList<City>();
            while (rset.next()) {
                City c = new City();
                c.city_id = rset.getInt("id");
                c.city_name = rset.getString("name");
                c.city_country_code = rset.getString("countrycode");
                c.city_district = rset.getString("district");
                c.city_population = rset.getInt("population");
                c.country_name = rset.getString("country.name");
                Cities.add(c);
            }
            return Cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city details");
            return null;
        }
    }
}

