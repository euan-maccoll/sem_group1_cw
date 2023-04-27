package com.napier.sem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ComparisonTests  extends BaseTest {
/*
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
        assertEquals(83341147, c.city_population);
        assertEquals(1194216853, c.non_city_population);

        // Test with invalid country name
        Country c2 = comparisonQuery.getPopCountryComparison(con, "Invalid");
        assertNull(c2);
    }

    @Test
    void testGetPopRegionComparison() {
        // Test with valid region name
        Region r = comparisonQuery.getPopRegionComparison(con, "Eastern Asia");
        assertNotNull(r);
        assertEquals("Eastern Asia", r.region_name);
        assertEquals(1641068690, r.region_population);
        assertEquals(749804012, r.region_city_population);
        assertEquals(891263678, r.region_non_city_population);

        // Test with invalid region name
        Region r2 = comparisonQuery.getPopRegionComparison(con, "Invalid");
        assertNull(r2);
    }

    @Test
    void testGetContinentPopulation() {
        // Test with valid continent name
        Continent c = comparisonQuery.getContinentPopulation(con, "Asia");
        assertNotNull(c);
        assertEquals("Asia", c.continent_name);
        assertEquals(4258722741L, c.continent_population);
        assertEquals(1072269839L, c.continent_city_population);
        assertEquals(3186452902L, c.continent_non_city_population);

        // Test with invalid continent name
        Continent c2 = comparisonQuery.getContinentPopulation(con, "Invalid");
        assertNull(c2);
    }
*/
    @Test
    void testPrintPopCountryComparison() {

        // Test with valid country object
        Country c = new Country();
        c.country_name = "China";
        c.country_population = 1277558000;
        c.city_population = 83341147;
        c.non_city_population = 1194216853;
        comparisonQuery.printPopCountryComparison(c);

        // Test with null country object
        comparisonQuery.printPopCountryComparison(null);
    }

    @Test
    void testPrintPopRegionComparison() {

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


}