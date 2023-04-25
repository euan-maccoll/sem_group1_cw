package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class citiesQuery {

    /**
     * Prints a list of cities and their populations.
     *
     * @param Cities The list of cities to print.
     */
    public static void printCityPop(ArrayList<City> Cities) {
        //Check cities is not null
        if (Cities == null) {
            System.out.println("No cities");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %-30s", "City Name", "Country Name", "District Name", "City Population"));
        // Loop over all cities in the list
        for (City c : Cities) {
            if (c == null)
                continue;
            String c_string =
                    String.format("%-30s %-30s %-30s %-30s", c.city_name, c.country_name, c.city_district, c.city_population);
            System.out.println(c_string);
        }
    }

    /**
     * method to get all cities populations in the world (largest to smallest)
     */
    public static ArrayList<City> getAllCitiesPop(Connection con, int limit) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population, country.name "
                            + "FROM city, country "
                            + "WHERE country.code = city.countrycode "
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
                c.city_id = rset.getInt("city.id");
                c.city_name = rset.getString("city.name");
                c.city_country_code = rset.getString("city.countrycode");
                c.city_district = rset.getString("city.district");
                c.city_population = rset.getInt("city.population");
                c.country_name = rset.getString("country.name");
                Cities.add(c);
            }
            return Cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * method to get all cities populations in a continent (largest to smallest)
     */
    public static ArrayList<City> getAllCitiesPopContinent(Connection con, String continent_input, int limit) {
        //add quotation marks for SQL variable
        continent_input = "'" + continent_input + "'";
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population, country.continent, country.name "
                            + "FROM city, country "
                            + "WHERE country.code = city.countrycode AND country.continent = " + continent_input
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
                c.city_id = rset.getInt("city.id");
                c.city_name = rset.getString("city.name");
                c.city_country_code = rset.getString("city.countrycode");
                c.city_district = rset.getString("city.district");
                c.city_population = rset.getInt("city.population");
                c.country_name = rset.getString("country.name");
                Cities.add(c);
            }
            return Cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * method to get all cities populations in a country (largest to smallest)
     */
    public static ArrayList<City> getAllCitiesPopCountry(Connection con, String country_input, int limit) {
        //add quotation marks for SQL variable
        country_input = "'" + country_input + "'";
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population, country.name "
                            + "FROM city, country "
                            + "WHERE country.code = city.countrycode AND country.name = " + country_input
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
                c.city_id = rset.getInt("city.id");
                c.city_name = rset.getString("city.name");
                c.city_country_code = rset.getString("city.countrycode");
                c.city_district = rset.getString("city.district");
                c.city_population = rset.getInt("city.population");
                c.country_name = rset.getString("country.name");
                Cities.add(c);
            }
            return Cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * method to get all cities populations in a region (largest to smallest)
     */
    public static ArrayList<City> getAllCitiesPopRegion(Connection con, String input_region, int limit) {
        //add quotation marks for SQL variable
        input_region = "'" + input_region + "'";
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population, country.region, country.name "
                            + "FROM city, country "
                            + "WHERE country.code = city.countrycode AND country.region = " + input_region
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
                c.city_id = rset.getInt("city.id");
                c.city_name = rset.getString("city.name");
                c.city_country_code = rset.getString("city.countrycode");
                c.city_district = rset.getString("city.district");
                c.city_population = rset.getInt("city.population");
                c.country_name = rset.getString("country.name");
                Cities.add(c);
            }
            return Cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * method to get all cities populations in a district (largest to smallest)
     */

    public static ArrayList<City> getAllCitiesPopDistrict(Connection con, String input_district, int limit) {
        //add quotation marks for SQL variable
        input_district = "'" + input_district + "'";
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population, country.name "
                            + "FROM city, country "
                            + "WHERE country.code = city.countrycode AND city.district = " + input_district
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
                c.city_id = rset.getInt("city.id");
                c.city_name = rset.getString("city.name");
                c.city_country_code = rset.getString("city.countrycode");
                c.city_district = rset.getString("city.district");
                c.city_population = rset.getInt("city.population");
                c.country_name = rset.getString("country.name");
                Cities.add(c);
            }
            return Cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }
}
