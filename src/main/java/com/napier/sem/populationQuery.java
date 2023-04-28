package com.napier.sem;

import java.sql.*;
public class populationQuery {

    public static void printWorldPopulation(Connection con) {
        long worldPopulation = getWorldPopulation(con);
        if (worldPopulation != -1) {
            System.out.println("World population: " + worldPopulation);
        }
    }
    public static void printContinentPopulation(Connection con, String continentName) {
        long continentPopulation = getContinentPopulation(con, continentName);
        if (continentPopulation != -1L) {
            System.out.println("Population of " + continentName + ": " + continentPopulation);
        }
    }

    public static void printRegionPopulation(Connection con, String regionName) {
        long regionPopulation = getRegionPopulation(con, regionName);
        if (regionPopulation != -1L) {
            System.out.println("Population of " + regionName + ": " + regionPopulation);
        }
    }

    public static void printCountryPopulation(Connection con, String countryName) {
        long countryPopulation = getCountryPopulation(con, countryName);
        if (countryPopulation != -1L) {
            System.out.println("Population of " + countryName + ": " + countryPopulation);
        }
    }

    public static void printDistrictPopulation(Connection con, String districtName) {
        long districtPopulation = getDistrictPopulation(con, districtName);
        if (districtPopulation != -1L) {
            System.out.println("Population of " + districtName + ": " + districtPopulation);
        }
    }

    public static void printCityPopulation(Connection con, String cityName) {
        long cityPopulation = getCityPopulation(con, cityName);
        if (cityPopulation != -1L) {
            System.out.println("Population of " + cityName + ": " + cityPopulation);
        }
    }


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
