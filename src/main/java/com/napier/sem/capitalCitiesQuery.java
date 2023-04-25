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
        System.out.println(String.format("%-30s %-15s", "Capital City Name", "Capital City Population"));
        // Loop over all cities in the list
        for (City c : CapitalCities) {
            if (c == null)
                continue;
            String c_string =
                    String.format("%-30s %-15s", c.city_name, c.city_population);
            System.out.println(c_string);
        }
    }

    /**
     * method to get all capital city populations in the world (largest to smallest)
     */
    /**
     * method to get all capital city populations in the world (largest to smallest)
     */
    public static ArrayList<City> getAllCapitalCitiesPop(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.id, city.name, city.countrycode, city.district, city.population "
                            + "FROM city "
                            + "JOIN country ON city.id = country.capital "
                            + "WHERE city.id = country.capital "
                            + "ORDER BY city.population DESC";
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

