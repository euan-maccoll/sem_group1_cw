package com.napier.sem;

import java.sql.*;
public class populationQuery {

    /**
     * Method to print the world population
     * @param con Connection object to connect to the database
     */
    public static void printWorldPopulation(Connection con) {
        long worldPopulation = getWorldPopulation(con);
        if (worldPopulation != -1) {
            System.out.println("World population: " + worldPopulation);
        }
    }

    /**
     * Method to print the population of a continent
     * @param con Connection object to connect to the database
     * @param continentName Name of the continent
     */
    public static void printContinentPopulation(Connection con, String continentName) {
        long continentPopulation = getContinentPopulation(con, continentName);
        if (continentPopulation != -1L) {
            System.out.println("Population of " + continentName + ": " + continentPopulation);
        }
    }

    /**
     * Method to print the population of a region
     * @param con Connection object to connect to the database
     * @param regionName Name of the region
     */
    public static void printRegionPopulation(Connection con, String regionName) {
        long regionPopulation = getRegionPopulation(con, regionName);
        if (regionPopulation != -1L) {
            System.out.println("Population of " + regionName + ": " + regionPopulation);
        }
    }

    /**
     * Method to print the population of a country
     * @param con Connection object to connect to the database
     * @param countryName Name of the country
     */
    public static void printCountryPopulation(Connection con, String countryName) {
        long countryPopulation = getCountryPopulation(con, countryName);
        if (countryPopulation != -1L) {
            System.out.println("Population of " + countryName + ": " + countryPopulation);
        }
    }

    /**
     * Method to print the population of a district
     * @param con Connection object to connect to the database
     * @param districtName Name of the district
     */
    public static void printDistrictPopulation(Connection con, String districtName) {
        long districtPopulation = getDistrictPopulation(con, districtName);
        if (districtPopulation != -1L) {
            System.out.println("Population of " + districtName + ": " + districtPopulation);
        }
    }

    /**
     * Method to print the population of a city
     * @param con Connection object to connect to the database
     * @param cityName Name of the city
     */
    public static void printCityPopulation(Connection con, String cityName) {
        long cityPopulation = getCityPopulation(con, cityName);
        if (cityPopulation != -1L) {
            System.out.println("Population of " + cityName + ": " + cityPopulation);
        }
    }

    /**
     * Method to get the world population from the database
     * @param con Connection object to connect to the database
     * @return long representing the world population, -1 if the query fails or returns no result
     */
    public static long getWorldPopulation(Connection con) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT SUM(population) as total_population FROM country";
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                return rset.getLong("total_population");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get world population");
            return -1;
        }
    }

    /**
     * Method to get the population of a continent from the database
     * @param con Connection object to connect to the database
     * @param continentName Name of the continent
     * @return long representing the population of the continent, -1 if the query fails, returns no result or the total population is 0
     */
    public static long getContinentPopulation(Connection con, String continentName) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT SUM(population) as total_population FROM country WHERE continent = '" + continentName + "'";
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                long totalPopulation = rset.getLong("total_population");
                return totalPopulation != 0 ? totalPopulation : -1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get continent population");
            return -1;
        }
    }


    /**
     * Method to get the population of a region from the database
     * @param con Connection object to connect to the database
     * @param regionName Name of the region
     * @return long representing the population of the region, -1 if the query fails, returns no result or the total population is 0
     */
    public static long getRegionPopulation(Connection con, String regionName) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT SUM(population) as total_population FROM country WHERE region = '" + regionName + "'";
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                long totalPopulation = rset.getLong("total_population");
                return totalPopulation != 0 ? totalPopulation : -1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get region population");
            return -1;
        }
    }


    /**
     * Method to get the population of a country from the database
     * @param con Connection object to connect to the database
     * @param countryName Name of the country
     * @return long representing the population of the country, -1 if the query fails, returns no result or the population is 0
     */
    public static long getCountryPopulation(Connection con, String countryName) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT population FROM country WHERE Name = '" + countryName + "'";
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                long population = rset.getLong("population");
                return population != 0 ? population : -1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country population");
            return -1;
        }
    }


    /**
     * Method to get the population of a district from the database
     * @param con Connection object to connect to the database
     * @param districtName Name of the district
     * @return long representing the population of the district, -1 if the query fails, returns no result or the total population is 0
     */
    public static long getDistrictPopulation(Connection con, String districtName) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT SUM(population) as total_population FROM city WHERE district = '" + districtName + "'";
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                long totalPopulation = rset.getLong("total_population");
                return totalPopulation != 0 ? totalPopulation : -1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get district population");
            return -1;
        }
    }


    /**
     * Method to get the population of a city from the database
     * @param con Connection object to connect to the database
     * @param cityName Name of the city
     * @return long representing the population of the city, -1 if the query fails, returns no result or the population is 0
     */
    public static long getCityPopulation(Connection con, String cityName) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT population FROM city WHERE name = '" + cityName + "'";
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                long population = rset.getLong("population");
                return population != 0 ? population : -1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city population");
            return -1;
        }
    }

}
