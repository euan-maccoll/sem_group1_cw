package com.napier.sem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ComparisonTests  extends BaseTest {

    /**
     * Test case to verify the correctness of getPopCountryComparison() method
     */
    @Test
    void testGetPopCountryComparison() {
        // Test with valid country name
        Country c = comparisonQuery.getPopCountryComparison(con, "China");
        assertNotNull(c);
        assertEquals("CHN", c.country_id);
        assertEquals("China", c.country_name);
        assertEquals("Asia", c.country_continent);
        assertEquals("Eastern Asia", c.country_region);
        assertEquals(1277558000, c.country_population);
        assertEquals(171180941, c.city_population);
        assertEquals(1106377059, c.non_city_population);

        // Test with invalid country name
        Country c2 = comparisonQuery.getPopCountryComparison(con, "Invalid");
        assertNull(c2);
    }

    /**
     * Test case to verify the correctness of testGetPopRegionComparison() method
     */
    @Test
    void testGetPopRegionComparison() {
        // Test with valid region name
        Region r = comparisonQuery.getPopRegionComparison(con, "Eastern Asia");
        assertNotNull(r);
        assertEquals("Eastern Asia", r.region_name);
        assertEquals(1507328000, r.region_population);
        assertEquals(312570678, r.region_city_population);
        assertEquals(1194757322, r.region_non_city_population);

        // Test with invalid region name
        Region r2 = comparisonQuery.getPopRegionComparison(con, "Invalid");
        assertNull(r2);
    }
    /**
     * Test case to verify the correctness of testGetContinentPopulation() method
     */
    @Test
    void testGetContinentPopulation() {
        // Test with valid continent name
        Continent c = comparisonQuery.getContinentPopulation(con, "Asia");
        assertNotNull(c);
        assertEquals("Asia", c.continent_name);
        assertEquals(3705025700L, c.continent_population);
        assertEquals(689143901L, c.continent_city_population);
        assertEquals(3015881799L, c.continent_non_city_population);

        // Test with invalid continent name
        Continent c2 = comparisonQuery.getContinentPopulation(con, "Invalid");
        assertNull(c2);
    }

    /**
     * This test case tests the printPopCountryComparison() method of the ComparisonQuery class.
     * It tests the method with three different scenarios:
     *
     *     Empty Country object.
     *
     *     Valid Country object.
     *
     *     Null Country object.
     *
     * The test verifies that the method prints the country's name, population, city population,
     * non-city population, city population percentage, and non-city population percentage correctly.
     * If the Country object is empty or null, the test verifies that the method returns an error message.
     */
    @Test
    void testPrintPopCountryComparison() {

        // Test with empty country object
        Country c1 = new Country();
        comparisonQuery.printPopCountryComparison(c1);

        // Test with valid country object
        Country c2 = new Country();
        c2.country_name = "China";
        c2.country_population = 1277558000;
        c2.city_population = 83341147;
        c2.non_city_population = 1194216853;
        comparisonQuery.printPopCountryComparison(c2);

        // Test with null country object
        comparisonQuery.printPopCountryComparison(null);
    }

    /**
     * This test case tests the printPopRegionComparison() method of the ComparisonQuery class.
     * It tests the method with three different scenarios:
     *
     * Empty Region object.
     *
     * Valid Region object.
     *
     * Null Region object.
     *
     * The test verifies that the method prints the region's name, population, city population,
     * non-city population, city population percentage, and non-city population percentage correctly.
     * If the Region object is empty or null, the test verifies that the method returns an error message.
     */
    @Test
    void testPrintPopRegionComparison() {

        // Test with empty region object
        Region r1 = new Region();
        comparisonQuery.printPopRegionComparison(r1);

        // Test with valid region object
        Region r = new Region();
        r.region_name = "Eastern Asia";
        r.region_population = 1641068690;
        r.region_city_population = 749804012;
        r.region_non_city_population = 891263678;
        comparisonQuery.printPopRegionComparison(r);

        // Test with null region object
        comparisonQuery.printPopRegionComparison(null);
}

    /**
     * This test case tests the printPopContinentComparison() method of the ComparisonQuery class.
     * It tests the method with three different scenarios:
     *
     * Empty Continent object.
     *
     * Valid Continent object.
     *
     * Null Continent object.
     *
     * The test verifies that the method prints the continent's name, population, city population,
     * non-city population, city population percentage, and non-city population percentage correctly.
     * If the Continent object is empty or null, the test verifies that the method returns an error message.
     */
    @Test
    void testPrintPopContinentComparison() {

        // Test with empty continent object
        Continent c1 = new Continent();
        comparisonQuery.printPopContinentComparison(c1);

        // Test with valid continent object
        Continent c = new Continent();
        c.continent_name = "Asia";
        c.continent_population = 3705025700L;
        c.continent_city_population = 689143901L;
        c.continent_non_city_population = 3015881799L;
        comparisonQuery.printPopContinentComparison(c);

        // Test with null continent object
        comparisonQuery.printPopContinentComparison(null);
    }


}