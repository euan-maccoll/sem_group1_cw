package com.napier.sem;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CapitalCityTests extends BaseTest {

    /*
     * ==============================================================
     * PRINT METHOD TESTS
     * ==============================================================
     */

        /**
         * Test to see if app can handle if city is null e.g. if query doesn't return a list of cities
         */
        @Test
        void printCapitalCityTestNull() {
            capitalCitiesQuery.printCapitalCityPop(null);
        }

        /**
         * Test to see if app can handle if cities array is empty
         */
        @Test
        void printCapitalCityTestEmpty() {
            ArrayList<City> Cities = new ArrayList<City>();
            capitalCitiesQuery.printCapitalCityPop(Cities);
        }

        /**
         * Test to see if app can print a list which includes null value
         */
        @Test
        void printCapitalCityTestContainsNulls() {
            ArrayList<City> Cities = new ArrayList<City>();
            Cities.add(null);
            capitalCitiesQuery.printCapitalCityPop(Cities);
        }

        /**
         * Test to see if app runs if city array contains all non-null (i.e. normal running)
         */
        @Test
        void printCapitalCityPopNormal() {
            ArrayList<City> Cities = new ArrayList<City>();
            City c = new City();
            c.city_id = 2974;
            c.city_name = "Paris";
            c.city_country_code = "FRA";
            c.country_name = "France";
            c.city_district = "Île-de-France";
            c.city_population = 2125246;
            Cities.add(c);
            capitalCitiesQuery.printCapitalCityPop(Cities);
        }

    /*
     * ==============================================================
     * GET ALL CAPITAL CITIES IN THE WORLD METHOD TESTS
     * ==============================================================
     */

    /**
     * Tests if the getAllCapitalCitiesPop() method retrieves a non-empty list of capital cities with a valid limit parameter.
     * Also checks if the list size is equal or less than the specified limit and that the cities
     * sorted in descending order of population.
     */
    @Test
    void testGetAllCapitalCitiesPopWithValidLimit() {
        // Ensure that the list of cities returned is not null
        ArrayList<City> cities = capitalCitiesQuery.getAllCapitalCitiesPop(con, 10);
        assertNotNull(cities);

        // Ensure that the list of cities returned is not empty
        assertFalse(cities.isEmpty());

        // Ensure that the list of cities returned does not exceed the specified limit
        assertTrue(cities.size() <= 10);

        // Ensure that the list of cities returned is sorted in descending order by population
        City prevCity = null;
        for (City city : cities) {
            if (prevCity != null) {
                assertTrue(prevCity.city_population >= city.city_population);
            }
            prevCity = city;
        }
    }

    /**
     * This test checks if the getAllCapitalCitiesPop() method returns a non-empty array list when the limit is set to 0,
     * which means that all cities should be returned.
     */
    @Test
    void testGetAllCapitalCitiesPopWithLimitZero() {
        ArrayList<City> cities2 = capitalCitiesQuery.getAllCapitalCitiesPop(con, 0);
        assertNotNull(cities2);
        assertTrue(cities2.size() > 0);
    }

    /**
     * Test to see if getAllCapitalCitiesPop() method returns an error if limit is negative
     */
    @Test
    void testGetAllCapitalCitiesPopWithNegativeLimit() {
        ArrayList<City> cities3 = capitalCitiesQuery.getAllCapitalCitiesPop(con, -1);
        assertNull(cities3);
    }


    /*
     * ==============================================================
     * GET ALL CAPITAL CITIES IN A CONTINENT METHOD TESTS
     * ==============================================================
     */

    /**
     * Tests if the getCapitalCitiesByContinent() method retrieves a non-empty list of capital cities with a valid
     * continent and limit parameter.Also checks if the list size is equal or less than the specified limit and that
     * the cities sorted in descending order of population.
     */
    @Test
    void testGetAllCapitalCitiesPopContinentWithValidContinentAndLimit() {
        // Ensure that the list of cities returned is not null
        ArrayList<City> cities = capitalCitiesQuery.getCapitalCitiesByContinent(con, "Europe", 10);
        assertNotNull(cities);

        // Ensure that the list of cities returned is not empty
        assertFalse(cities.isEmpty());

        // Ensure that the list of cities returned does not exceed the specified limit
        assertTrue(cities.size() <= 10);

        // Ensure that the list of cities returned is sorted in descending order by population
        City prevCity = null;
        for (City city : cities) {
            if (prevCity != null) {
                assertTrue(prevCity.city_population >= city.city_population);
            }
            prevCity = city;
        }
    }

    /**
     * Tests that the getCapitalCitiesByContinent() method returns all capital cities from a given continent
     * when limit is set to 0. In this case, the test checks if the method returns a non-empty list
     * for the "Asia" continent, which should contain all capital cities in that continent, regardless of
     * population size.

     */
    @Test
    void testGetAllCapitalCitiesPopContinentWithValidContinentAndLimitOfZero() {
        ArrayList<City> cities = capitalCitiesQuery.getCapitalCitiesByContinent(con, "Asia", 0);
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }

    /**
     * Test that the getCapitalCitiesByContinent() method returns null when the limit is negative.
     */
    @Test
    void testGetAllCapitalCitiesPopContinentWithValidContinentAndNegativeLimit() {
        ArrayList<City> cities = capitalCitiesQuery.getCapitalCitiesByContinent(con, "Asia", -1);
        assertNull(cities);
    }

    /**
     * Tests the behavior of getCapitalCitiesByContinent() when called with an invalid continent name and a limit.
     * Expects an empty ArrayList to be returned.
     */
    @Test
    void testGetAllCapitalCitiesPopContinentWithInvalidContinentAndLimit() {
        ArrayList<City> cities = capitalCitiesQuery.getCapitalCitiesByContinent(con, "Atlantis", 10);
        assertNotNull(cities);
        assertTrue(cities.isEmpty());
    }

    /*
     * ==============================================================
     * GET ALL CAPITAL CITIES IN A REGION METHOD TESTS
     * ==============================================================
     */

    /**
     * Tests if the getCapitalCitiesByRegion() method retrieves a non-empty list of capital cities with a valid region
     * and limit parameter. Also checks if the list size is equal or less than the specified limit and that the capital
     * cities sorted in descending order of population.
     */
    @Test
    void testGetAllCapitalCitiesPopRegionWithValidRegionAndLimit() {

        // Ensure that the list of cities returned is not null
        ArrayList<City> cities = capitalCitiesQuery.getCapitalCitiesByRegion(con, "Caribbean", 10);
        assertNotNull(cities);

        // Ensure that the list of cities returned is not empty
        assertFalse(cities.isEmpty());

        // Ensure that the list of cities returned does not exceed the specified limit
        assertTrue(cities.size() <= 10);

        // Ensure that the list of cities returned is sorted in descending order by population
        City prevCity = null;
        for (City city : cities) {
            if (prevCity != null) {
                assertTrue(prevCity.city_population >= city.city_population);
            }
            prevCity = city;
        }
    }

    /**
     * Tests that the getAllCapitalCitiesPopRegion() method retrieves all capital cities from a given region
     * (in descending order by population),  when the limit parameter is set to 0. In this case, the test checks if the
     * method returns a non-empty list for the "Western Europe" region, which should contain all cities in that region,
     * regardless of population size.
     */
    @Test
    void testGetAllCapitalCitiesPopRegionWithValidRegionAndLimitOfZero() {
        ArrayList<City> cities = capitalCitiesQuery.getCapitalCitiesByRegion(con, "Western Europe", 0);
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }
    /**
     * Test that the getAllCapitalCitiesPopRegion() method returns null when the limit is negative.
     */
    @Test
    void testGetAllCapitalCitiesPopRegionWithValidRegionAndNegativeLimit() {
        ArrayList<City> cities = capitalCitiesQuery.getCapitalCitiesByRegion(con, "Western Europe", -1);
        assertNull(cities);
    }

    /**
     * Tests the behavior of getAllCapitalCitiesPopRegion() when called with an invalid region name and a valid limit.
     * Expects an empty ArrayList to be returned.
     */
    @Test
    void testGetAllCapitalCitiesPopRegionWithInvalidRegionAndLimit() {
        ArrayList<City> cities = capitalCitiesQuery.getCapitalCitiesByRegion(con, "Narnia", 10);
        assertNotNull(cities);
        assertTrue(cities.isEmpty());
    }
    }
