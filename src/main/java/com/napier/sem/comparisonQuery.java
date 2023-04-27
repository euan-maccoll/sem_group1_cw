package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class comparisonQuery {
    public static void printPopCountryComparison(Country country) {
        // Check if country is null or any field is null
        if (country == null || country.country_name == null || country.country_population == 0 ||
                country.city_population == 0 || country.non_city_population == 0) {
            System.out.println("Unable to print country comparison as there is no information provided");
            return;
        }

        // Calculate percentages
        double city_percent = (double) country.city_population / country.country_population * 100;
        double non_city_percent = (double) country.non_city_population / country.country_population * 100;
        long rounded_city_percent = Math.round(city_percent);
        long rounded_non_city_percent = Math.round(non_city_percent);

        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s",
                "Country Name", "Country Population", "Cities Population", "Non Cities Population",
                "Cities %", "Non Cities %"));
        // Print country information
        String c_string = String.format("%-30s %-30s %-30s %-30s %-30s %-30s",
                country.country_name, country.country_population, country.city_population,
                country.non_city_population, rounded_city_percent, rounded_non_city_percent);
        System.out.println(String.format(c_string));

    }




    public static void printPopRegionComparison(Region region) {
        // Check if region is null or any field is null
        if (region == null || region.region_name == null || region.region_population == 0 ||
                region.region_city_population == 0 || region.region_non_city_population == 0) {
            System.out.println("Unable to print region comparison as there is no information provided");
            return;
        }

        // Calculate percentages
        double city_percent = (double) region.region_city_population / region.region_population * 100;
        double non_city_percent = (double) region.region_non_city_population / region.region_population * 100;
        long rounded_city_percent = Math.round(city_percent);
        long rounded_non_city_percent = Math.round(non_city_percent);

        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s",
                "Region Name", "Region Population", "Cities Population", "Non Cities Population",
                "Cities %", "Non Cities %"));
        // Print region information
        String r_string = String.format("%-30s %-30s %-30s %-30s %-30s %-30s",
                region.region_name, region.region_population, region.region_city_population,
                region.region_non_city_population, rounded_city_percent, rounded_non_city_percent);
        System.out.println(r_string);
    }

    public static void printPopContinentComparison(Continent continent) {
        // Check if continent is null
        if (continent == null || continent.continent_name == null || continent.continent_population == null ||
                continent.continent_city_population == null || continent.continent_non_city_population == null) {
            System.out.println("Unable to print continent comparison as there is no information provided");
            return;
        }

        // Calculate percentages
        double city_percent = (double) continent.continent_city_population / continent.continent_population * 100;
        double non_city_percent = (double) continent.continent_non_city_population / continent.continent_population * 100;
        long rounded_city_percent = Math.round(city_percent);
        long rounded_non_city_percent = Math.round(non_city_percent);

        // Print header
        System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s",
                "Continent Name", "Continent Population", "Cities Population", "Non Cities Population",
                "Cities %", "Non Cities %"));
        // Print continent information
        String c_string = String.format("%-30s %-30s %-30s %-30s %-30s %-30s",
                continent.continent_name, continent.continent_population, continent.continent_city_population,
                continent.continent_non_city_population, rounded_city_percent, rounded_non_city_percent);
        System.out.println(c_string);
    }



    /**
     * method to get compare the population between city and non-city in a country
     */
    public static Country getPopCountryComparison(Connection con, String country_input) {
        country_input = "'" + country_input + "'";
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.population, country.continent, country.region, country.capital, SUM(DISTINCT city.population) AS SumCityPop  "
                            + "FROM country, city "
                            + "WHERE country.code = city.countrycode AND country.name = " + country_input
                            + "GROUP BY country.code";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            Country c = null;
            if (rset.next()) {
                c = new Country();
                c.country_id = rset.getString("country.code");
                c.country_name = rset.getString("country.name");
                c.country_population = rset.getInt("country.population");
                c.country_continent = rset.getString("country.continent");
                c.country_region = rset.getString("country.region");
                c.country_capital = rset.getInt("country.capital");
                c.city_population = rset.getInt("SumCityPop");
                c.non_city_population = c.country_population - c.city_population;
            }
            return c;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }


    public static Region getPopRegionComparison(Connection con, String regionName) {
        regionName = "'" + regionName + "'";
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(DISTINCT country.population) AS RegionPop, SUM(DISTINCT city.population) AS CityPop, country.region "
                            + "FROM country INNER JOIN city ON country.code = city.countrycode "
                            + "WHERE country.region = " + regionName + " "
                            + "GROUP BY country.region";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract region information
            Region region = null;
            while (rset.next()) {
                region = new Region();
                region.region_name = rset.getString("region");
                region.region_population = rset.getInt("RegionPop");
                region.region_city_population = rset.getInt("CityPop");
                region.region_non_city_population = region.region_population - region.region_city_population;
            }
            return region;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get region details");
            return null;
        }
    }


    public static Continent getContinentPopulation(Connection con, String continentName) {
        continentName = "'" + continentName + "'";
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(DISTINCT country.population) AS ContinentPop, SUM(DISTINCT city.population) AS CityPop, continent "
                            + "FROM country INNER JOIN city ON country.code = city.countrycode "
                            + "WHERE country.continent = " + continentName + " "
                            + "GROUP BY continent";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract continent information
            Continent continent = null;
            if (rset.next()) {
                continent = new Continent();
                continent.continent_name = rset.getString("continent");
                continent.continent_population = rset.getLong("ContinentPop");
                continent.continent_city_population = rset.getLong("CityPop");
                continent.continent_non_city_population = continent.continent_population - continent.continent_city_population;
            }
            return continent;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get continent details");
            return null;
        }
    }


}
