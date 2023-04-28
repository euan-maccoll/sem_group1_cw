package com.napier.sem;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;
public class PopulationTests extends BaseTest {

    /**
     * Test that the getWorldPopulation() method returns the correct population for a populated database.
     */
    @Test
    public void testGetWorldPopulationValid() {
        long population = populationQuery.getWorldPopulation(con);
        assertEquals(6078749450l, population);
    }


    /**
     * Test that the method returns a valid population number for a populated database when given a valid continent name.
     */
    @Test
    public void testGetContinentPopulationValid() throws SQLException {
        long population = populationQuery.getContinentPopulation(con, "Europe");
        assertEquals(730074600l, population);
    }

    /**
     * Test that the method returns -1 if an invalid continent name is provided.
     */
    @Test
    public void testGetContinentPopulationInvalid() throws SQLException {
        long population = populationQuery.getContinentPopulation(con, "InvalidContinent");
        assertEquals(-1, population);
    }

    /**
     * Test that the getRegionPopulation method returns a valid population number for a populated database when given a valid region name.
     */
    @Test
    public void testGetRegionPopulationValid() {
        long population = populationQuery.getRegionPopulation(con, "Eastern Asia");
        assertEquals(1507328000l, population);
    }

    /**
     * Test that the getRegionPopulation method returns -1 if an invalid region name is provided.
     */
    @Test
    public void testGetRegionPopulationInvalid() {
        long population = populationQuery.getRegionPopulation(con, "Invalid Region");
        assertEquals(-1, population);
    }

    /**
     * Test that the method returns a valid population number for a populated database when given a valid country code.
     */
    @Test
    public void testGetCountryPopulationValid() throws SQLException {
        long population = populationQuery.getCountryPopulation(con, "Mexico");
        assertEquals(98881000, population);
    }

    /**
     * Test that the method returns -1 if an invalid country code is provided.
     */
    @Test
    public void testGetCountryPopulationInvalid() throws SQLException {
        long population = populationQuery.getCountryPopulation(con, "InvalidCountry");
        assertEquals(-1, population);
    }

    /**
     * Test that the method returns a valid population number for a populated database when given a valid district name.
     */
    @Test
    public void testGetDistrictPopulationValid() {
        long population = populationQuery.getDistrictPopulation(con, "Kabol");
        assertEquals(1780000, population);
    }

    /**
     * Test that the method returns -1 if an invalid district name is provided.
     */
    @Test
    public void testGetDistrictPopulationInvalid() {
        long population = populationQuery.getDistrictPopulation(con, "InvalidDistrict");
        assertEquals(-1, population);
    }

    /**
     * Test that the method returns a valid population number for a populated database when given a valid city name.
     */
    @Test
    public void testGetCityPopulationValid() throws SQLException {
        long population = populationQuery.getCityPopulation(con, "Tokyo");
        assertEquals(7980230, population);
    }

    /**
     * Test that the method returns -1 if an invalid city name is provided.
     */
    @Test
    public void testGetCityPopulationInvalid() throws SQLException {
        long population = populationQuery.getCityPopulation(con, "InvalidCity");
        assertEquals(-1, population);
    }

    /**
     * Tests the printWorldPopulation method to ensure that it prints the correct world population number for a
     * populated database.
     */
    @Test
    public void testPrintWorldPopulation() {
        // Set up a new output stream to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the printWorldPopulation method
        populationQuery.printWorldPopulation(con);

        // Compare the console output to the expected output
        assertEquals("World population: 6078749450", outContent.toString().trim());
    }

    /**
     * Tests the printWorldPopulation method to ensure that it prints the correct continent population number for a
     * specific continent.
     */
    @Test
    public void testPrintContinentPopulation() {
        // Set up a new output stream to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the printContinentPopulation method
        populationQuery.printContinentPopulation(con, "Europe");

        // Compare the console output to the expected output
        assertEquals("Population of Europe: 730074600", outContent.toString().trim());
    }

    /**
     * Tests the printWorldPopulation method to ensure that it prints the correct region population number for a
     * specific region.
     */
    @Test
    public void testPrintRegionPopulation() {
        // Set up a new output stream to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the printRegionPopulation method
        populationQuery.printRegionPopulation(con, "Caribbean");

        // Compare the console output to the expected output
        assertEquals("Population of Caribbean: 38140000", outContent.toString().trim());
    }

    /**
     * Tests the printWorldPopulation method to ensure that it prints the correct country population number for a
     * specific country.
     */
    @Test
    public void testPrintCountryPopulation() {
        // Set up a new output stream to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the printCountryPopulation method
        populationQuery.printCountryPopulation(con, "Denmark");

        // Compare the console output to the expected output
        assertEquals("Population of Denmark: 5330000", outContent.toString().trim());
    }

    /**
     * Tests the printWorldPopulation method to ensure that it prints the correct district population number for a
     * specific district.
     */
    @Test
    public void testPrintDistrictPopulation() {
        // Set up a new output stream to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the printDistrictPopulation method
        populationQuery.printDistrictPopulation(con, "Gaza");

        // Compare the console output to the expected output
        assertEquals("Population of Gaza: 453074", outContent.toString().trim());
    }

    /**
     * Tests the printWorldPopulation method to ensure that it prints the correct city population number for a
     * specific city.
     */
    @Test
    public void testPrintCityPopulation() {
        // Set up a new output stream to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the printCityPopulation method with a valid city name
        populationQuery.printCityPopulation(con, "London");

        // Compare the console output to the expected output
        assertEquals("Population of London: 7285000", outContent.toString().trim());
    }
}

