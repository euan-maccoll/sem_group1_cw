package com.napier.sem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CityTests extends BaseTest
{

    /**
     * ==============================================================
     * PRINT METHOD TESTS
     * ==============================================================
     */


    /**
     * Test to see if app can handle if city is null e.g. if query doesn't return a list of cities
     */
    @Test
    void printCityTestNull()
    {
        citiesQuery.printCities(null);
    }

    /**
     * Test to see if app can handle if cities array is empty
     */
    @Test
    void printCityTestEmpty()
    {
        ArrayList<City> Cities = new ArrayList<City>();
        citiesQuery.printCities(Cities);
    }

    /**
     * Test to see if app can print a list which includes null value
     */
    @Test
    void printCityTestContainsNulls()
    {
        ArrayList<City> Cities = new ArrayList<City>();
        Cities.add(null);
        citiesQuery.printCities(Cities);
    }

    /**
     * Test to see if app runs if city array contains all non-null (i.e. normal running)
     */
    @Test
    void printCityPopNormal()
    {
        ArrayList<City> Cities = new ArrayList<City>();
        City c = new City();
        c.city_id = 2974;
        c.city_name = "Paris";
        c.city_country_code = "FRA";
        c.country_name = "France";
        c.city_district = "Île-de-France";
        c.city_population = 2125246;
        Cities.add(c);
        citiesQuery.printCities(Cities);
    }


    /**
     * ==============================================================
     * GET ALL CITIES IN THE WORLD METHOD TESTS
     * ==============================================================
     */

    /**
     * Test to ensure that getAllCitiesPop() method returns the correct number of cities when a valid limit is provided.
     * This test checks if the application runs normally when the city array contains all non-null elements.
     */
    @Test
    void testGetAllCitiesPopWithValidLimit() {
        ArrayList<City> test_cities = citiesQuery.getAllCitiesPop(con, 10);
        assertNotNull(test_cities);
        assertEquals(10, test_cities.size());
    }

    /**
     * This test checks if the getAllCitiesPop() method returns a non-empty array list when the limit is set to 0,
     * which means that all cities should be returned.
     */
    @Test
    void testGetAllCitiesPopWithLimitZero() {
        ArrayList<City> cities2 = citiesQuery.getAllCitiesPop(con, 0);
        assertNotNull(cities2);
        assertTrue(cities2.size() > 0);
    }

    /**
     * Test to see if getAllCitiesPop() method returns an error if limit is negative
     */
    @Test
    void testGetAllCitiesPopWithNegativeLimit() {
        ArrayList<City> cities3 = citiesQuery.getAllCitiesPop(con, -1);
        assertNull(cities3);
    }

    /**
     * ==============================================================
     * GET ALL CITIES IN A CONTINENT METHOD TESTS
     * ==============================================================
     */

    /**
     * Tests if the getAllCitiesPopContinent() method retrieves a non-empty list of cities with a valid continent and
     * limit parameter.Also checks if the list size is equal or less than the specified limit and that the cities
     * sorted in descending order of population.
     */
    @Test
    void testGetAllCitiesPopContinentWithValidContinentAndLimit() {
        // Ensure that the list of cities returned is not null
        ArrayList<City> cities = citiesQuery.getAllCitiesPopContinent(con, "Europe", 10);
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
     * Tests that the getAllCitiesPopContinent() method returns all cities from a given continent
     * when limit is set to 0. In this case, the test checks if the method returns a non-empty list
     * for the "Asia" continent, which should contain all cities in that continent, regardless of
     * population size.

     */
    @Test
    void testGetAllCitiesPopContinentWithValidContinentAndLimitOfZero() {
        ArrayList<City> cities = citiesQuery.getAllCitiesPopContinent(con, "Asia", 0);
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }

    /**
     * Test that the getAllCitiesPopContinent() method returns null when the limit is negative.
     */
    @Test
    void testGetAllCitiesPopContinentWithValidContinentAndNegativeLimit() {
        ArrayList<City> cities = citiesQuery.getAllCitiesPopContinent(con, "Asia", -1);
        assertNull(cities);
    }

    /**
     * Tests the behavior of getAllCitiesPopContinent() when called with an invalid continent name and a limit.
     * Expects an empty ArrayList to be returned.
     */
    @Test
    void testGetAllCitiesPopContinentWithInvalidContinentAndLimit() {
        ArrayList<City> cities = citiesQuery.getAllCitiesPopContinent(con, "Atlantis", 10);
        assertNotNull(cities);
        assertTrue(cities.isEmpty());
    }

    /**
     * ==============================================================
     * GET ALL CITIES IN A COUNTRY METHOD TESTS
     * ==============================================================
     */


    /**
     * Tests if the getAllCitiesPopCountry() method retrieves a non-empty list of cities with a valid country and
     * limit parameter. Also checks if the list size is equal or less than the specified limit and that the cities
     * sorted in descending order of population.
     */
    @Test
    void testGetAllCitiesPopCountryWithValidCountryAndLimit() {
        // Ensure that the list of cities returned is not null
        ArrayList<City> cities = citiesQuery.getAllCitiesPopCountry(con, "United States", 10);
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
     * Tests that the getAllCitiesPopCountry() method retrieves all cities from a given country (in descending order by population),
     * when the limit parameter is set to 0. In this case, the test checks if the method returns a non-empty list
     * for the "Spain" country, which should contain all cities in that country, regardless of population size.
     */
    @Test
    void testGetAllCitiesPopCountryWithValidCountryAndLimitOfZero() {
        ArrayList<City> cities = citiesQuery.getAllCitiesPopCountry(con, "Spain", 0);
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }

    /**
     * Test that the getAllCitiesPopCountry() method returns null when the limit is negative.
     */
    @Test
    void testGetAllCitiesPopCountryWithValidCountryAndNegativeLimit() {
        ArrayList<City> cities = citiesQuery.getAllCitiesPopCountry(con, "Spain", -1);
        assertNull(cities);
    }


    /**
     * Tests the behavior of getAllCitiesPopCountry() when called with an invalid country name and a valid limit.
     * Expects an empty ArrayList to be returned.
     */
    @Test
    void testGetAllCitiesPopCountryWithInvalidCountryAndLimit() {
        ArrayList<City> cities = citiesQuery.getAllCitiesPopCountry(con, "Narnia", 10);
        assertNotNull(cities);
        assertTrue(cities.isEmpty());
    }


    /**
     * ==============================================================
     * GET ALL CITIES IN A REGION METHOD TESTS
     * ==============================================================
     */

    /**
     * Tests if the getAllCitiesPopRegion() method retrieves a non-empty list of cities with a valid region and
     * limit parameter. Also checks if the list size is equal or less than the specified limit and that the cities
     * sorted in descending order of population.
     */
    @Test
    void testGetAllCitiesPopRegionWithValidRegionAndLimit() {

        // Ensure that the list of cities returned is not null
        ArrayList<City> cities = citiesQuery.getAllCitiesPopRegion(con, "Caribbean", 10);
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
     * Tests that the getAllCitiesPopRegion() method retrieves all cities from a given region (in descending order by population),
     * when the limit parameter is set to 0. In this case, the test checks if the method returns a non-empty list
     * for the "Western Europe" region, which should contain all cities in that region, regardless of population size.
     */
    @Test
    void testGetAllCitiesPopRegionWithValidRegionAndLimitOfZero() {
        ArrayList<City> cities = citiesQuery.getAllCitiesPopRegion(con, "Western Europe", 0);
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }
    /**
     * Test that the getAllCitiesPopRegion() method returns null when the limit is negative.
     */
    @Test
    void testGetAllCitiesPopRegionWithValidRegionAndNegativeLimit() {
        ArrayList<City> cities = citiesQuery.getAllCitiesPopRegion(con, "Western Europe", -1);
        assertNull(cities);
    }

    /**
     * Tests the behavior of getAllCitiesPopRegion() when called with an invalid region name and a valid limit.
     * Expects an empty ArrayList to be returned.
     */
    @Test
    void testGetAllCitiesPopRegionWithInvalidRegionAndLimit() {
        ArrayList<City> cities = citiesQuery.getAllCitiesPopRegion(con, "Narnia", 10);
        assertNotNull(cities);
        assertTrue(cities.isEmpty());
    }

    /**
     * ==============================================================
     * GET ALL CITIES IN A DISTRICT METHOD TESTS
     * ==============================================================
     */

    /**
     * Tests if the getAllCitiesPopDistrict() method retrieves a non-empty list of cities with a valid district and
     * limit parameter. Also checks if the list size is equal or less than the specified limit and that the cities
     * sorted in descending order of population.
     */
    @Test
    void testGetAllCitiesPopDistrictWithValidDistrictAndLimit() {
        // Ensure that the list of cities returned is not null
        ArrayList<City> cities = citiesQuery.getAllCitiesPopDistrict(con, "California", 10);
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
     * Tests that the getAllCitiesPopDistrict() method retrieves all cities from a given district (in descending order by population),
     * when the limit parameter is set to 0. In this case, the test checks if the method returns a non-empty list
     * for the "Nevada" district, which should contain all cities in that district, regardless of population size.
     */
    @Test
    void testGetAllCitiesPopDistrictWithValidDistrictAndLimitOfZero() {
        ArrayList<City> cities = citiesQuery.getAllCitiesPopDistrict(con, "Nevada", 0);
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }

    /**
     * Test that the getAllCitiesPopDistrict() method returns null when the limit is negative.
     */
    @Test
    void testGetAllCitiesPopDistrictWithValidDistrictAndNegativeLimit() {
        ArrayList<City> cities = citiesQuery.getAllCitiesPopDistrict(con, "New York", -1);
        assertNull(cities);
    }

    /**
     * Tests the behavior of getAllCitiesPopDistrict() when called with an invalid district name and a valid limit.
     * Expects an empty ArrayList to be returned.
     */
    @Test
    void testGetAllCitiesPopDistrictWithInvalidDistrictAndLimit() {
        ArrayList<City> cities = citiesQuery.getAllCitiesPopDistrict(con, "Narnia", 10);
        assertNotNull(cities);
        assertTrue(cities.isEmpty());
    }

}