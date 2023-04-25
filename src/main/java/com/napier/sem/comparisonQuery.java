package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class comparisonQuery {
    public static void printPopCountryComparison(ArrayList<Country> Countries) {
        //Check cities is not null
        if (Countries == null) {
            System.out.println("No cities");
            return;
        }

        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %-30s", "Country Name", "Country Population", "Cities Population", "Non Cities Population"));
        // Loop over all cities in the list
        for (Country c : Countries) {
            if (c == null)
                continue;
            String c_string =
                    String.format("%-30s %-30s %-30s %-30s", c.country_name, c.country_population, c.city_population, c.non_city_population);
            System.out.println(c_string);
        }
    }

    public static void printPopRegionComparison(ArrayList<Country> Countries) {
        //Check cities is not null
        if (Countries == null) {
            System.out.println("No cities");
            return;
        }

        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %-30s", "Region Name", "Region Population", "Cities Population", "Non Cities Population"));
        // Loop over all cities in the list
        for (Country c : Countries) {
            if (c == null)
                continue;
            String c_string =
                    String.format("%-30s %-30s %-30s %-30s", c.country_region, c.country_population, c.city_population, c.non_city_population);
            System.out.println(c_string);
        }
    }

    /**
     * method to get compare the population between city and non-city a country
     */
    public static  ArrayList<Country> getPopCountryComparison(Connection con, String country_input) {
        country_input = "'" + country_input + "'";
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.population, country.continent, country.region, country.capital, SUM(city.population) AS SumCityPop  "
                            + "FROM country, city "
                            + "WHERE country.code = city.countrycode AND country.name = " + country_input
                            + "GROUP BY country.code";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> Countries = new ArrayList<Country>();
            while (rset.next()) {
                Country c = new Country();
                c.country_id = rset.getString("country.code");
                c.country_name = rset.getString("country.name");
                c.country_population = rset.getInt("country.population");
                c.country_continent = rset.getString("country.continent");
                c.country_region = rset.getString("country.region");
                c.country_capital = rset.getInt("country.capital");
                c.city_population = rset.getInt("SumCityPop");
                c.non_city_population = c.country_population - c.city_population;
                Countries.add(c);
            }
            return Countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public static  ArrayList<Country> getPopRegionComparison(Connection con, String region_input){
        region_input = "'" + region_input + "'";
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            // IMPLEMENT NESTED QUERY?
            String strSelect =
                    "SELECT country.code, country.region, country.population, SUM(city.population) AS SumCityPop  "
                            + "FROM country, city "
                            + "WHERE country.code = city.countrycode AND country.region = " + region_input
                            + "GROUP BY country.code, country.region";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> Countries = new ArrayList<Country>();
            while (rset.next()) {
                Country c = new Country();
                c.country_population = rset.getInt("country.population");
                c.country_region = rset.getString("country.region");
                c.city_population = rset.getInt("SumCityPop");
                c.non_city_population = c.country_population - c.city_population;
                Countries.add(c);
            }
            return Countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
}
