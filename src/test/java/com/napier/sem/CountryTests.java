package com.napier.sem;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CountryTests extends BaseTest {

    /*
      ==============================================================
      PRINT METHOD TESTS
      ==============================================================
     */

    /**
     * Test to see if app can handle if country is null e.g. if query doesn't return a list of countries
     */
    @Test
    void printCountryTestNull()
    {
        countryQuery.printCountries(null);
    }

    /**
     * Test to see if app can handle if country array list is empty
     */
    @Test
    void printCountryTestEmpty()
    {
        ArrayList<Country> Countries = new ArrayList<>();
        countryQuery.printCountries(Countries);
    }

    /**
     * Test to see if app can print a country list which includes null value
     */
    @Test
    void printCountryTestContainsNulls()
    {
        ArrayList<Country> Countries = new ArrayList<>();
        Countries.add(null);
        countryQuery.printCountries(Countries);
    }

    /**
     * Test to see if app runs when country array contains all non-null (i.e. normal running)
     */
    @Test
    void printCountryNormal() {
        ArrayList<Country> countries = new ArrayList<>();
        Country c = new Country();
        c.country_id = "GBR";
        c.country_name = "United Kingdom";
        c.country_continent = "Europe";
        c.country_region = "British Islands";
        c.country_population = 67886011;
        c.country_capital = 1826645936;
        c.city_population = 0;
        c.non_city_population = 67886011;
        countries.add(c);
        countryQuery.printCountries(countries);
    }

    /*
      ==============================================================
      PRINT ALL COUNTRIES IN THE WORLD METHOD TESTS
      ==============================================================
     */

    /**
     * Tests if the getAllCountriesPop() method retrieves a non-empty list of countries with a valid limit parameter.
     * Also checks if the list size is equal or less than the specified limit and that the countries
     * sorted in descending order of population.
     */
    @Test
    void testGetAllCountriesPopWithValidContinentAndLimit() {
        // Ensure that the list of countries returned is not null
        ArrayList<Country> countries = countryQuery.getAllCountriesPop(con, 10);
        assertNotNull(countries);

        // Ensure that the list of countries returned is not empty
        assertFalse(countries.isEmpty());

        // Ensure that the list of countries returned does not exceed the specified limit
        assertTrue(countries.size() <= 10);

        // Ensure that the list of countries returned is sorted in descending order by population
        Country prevCountry = null;
        for (Country country : countries) {
            if (prevCountry != null) {
                assertTrue(prevCountry.country_population >= country.country_population);
            }
            prevCountry = country;
        }
    }

    /**
     * This test checks if the getAllCountriesPop() method returns a non-empty array list when the limit is set to 0,
     * which means that all countries should be returned.
     */
    @Test
    void testGetAllCountriesPopWithLimitZero() {
        ArrayList<Country> countries2 = countryQuery.getAllCountriesPop(con, 0);
        assertNotNull(countries2);
        assertTrue(countries2.size() > 0);
    }

    /**
     * Test to see if getAllCountriesPop() method returns an error if limit is negative
     */
    @Test
    void testGetAllCountriesPopWithNegativeLimit() {
        ArrayList<Country> countries3 = countryQuery.getAllCountriesPop(con, -1);
        assertNull(countries3);
    }

    /*
     * ==============================================================
     * GET ALL COUNTRIES IN A CONTINENT METHOD TESTS
     * ==============================================================
     */

    /**
     * Tests if the getAllCountriesPopContinent() method retrieves a non-empty list of countries with a valid continent and
     * limit parameter.Also checks if the list size is equal or less than the specified limit and that the countries
     * sorted in descending order of population.
     */
    @Test
    void testGetAllCountriesPopContinentWithValidContinentAndLimit() {
        // Ensure that the list of countries returned is not null
        ArrayList<Country> countries = countryQuery.getAllCountriesPopContinent(con, "Europe", 10);
        assertNotNull(countries);

        // Ensure that the list of countries returned is not empty
        assertFalse(countries.isEmpty());

        // Ensure that the list of countries returned does not exceed the specified limit
        assertTrue(countries.size() <= 10);

        // Ensure that the list of countries returned is sorted in descending order by population
        Country prevCountry = null;
        for (Country country : countries) {
            if (prevCountry != null) {
                assertTrue(prevCountry.country_population >= country.country_population);
            }
            prevCountry = country;
        }
    }

    /**
     * Tests that the getAllCountriesPopContinent() method returns all countries from a given continent
     * when limit is set to 0. In this case, the test checks if the method returns a non-empty list
     * for the "Asia" continent, which should contain all countries in that continent, regardless of
     * population size.

     */
    @Test
    void testGetAllCountriesPopContinentWithValidContinentAndLimitOfZero() {
        ArrayList<Country> countries = countryQuery.getAllCountriesPopContinent(con, "Asia", 0);
        assertNotNull(countries);
        assertFalse(countries.isEmpty());
    }

    /**
     * Test that the getAllCountriesPopContinent() method returns null when the limit is negative.
     */
    @Test
    void testGetAllCountriesPopContinentWithValidContinentAndNegativeLimit() {
        ArrayList<Country> countries = countryQuery.getAllCountriesPopContinent(con, "Asia", -1);
        assertNull(countries);
    }

    /**
     * Tests the behavior of getAllCountriesPopContinent() when called with an invalid continent name and a limit.
     * Expects an empty ArrayList to be returned.
     */
    @Test
    void testGetAllCountriesPopContinentWithInvalidContinentAndLimit() {
        ArrayList<Country> countries = countryQuery.getAllCountriesPopContinent(con, "Atlantis", 10);
        assertNotNull(countries);
        assertTrue(countries.isEmpty());
    }

    /*
     * ==============================================================
     * GET ALL COUNTRIES IN A REGION METHOD TESTS
     * ==============================================================
     */

    /**
     * Tests if the getAllCountriesPopRegion() method retrieves a non-empty list of countries with a valid regopm and
     * limit parameter.Also checks if the list size is equal or less than the specified limit and that the countries
     * sorted in descending order of population.
     */
    @Test
    void testGetAllCountriesPopRegionWithValidRegionAndLimit() {
        // Ensure that the list of countries returned is not null
        ArrayList<Country> countries = countryQuery.getAllCountriesPopRegion(con, "Caribbean", 10);
        assertNotNull(countries);

        // Ensure that the list of countries returned is not empty
        assertFalse(countries.isEmpty());

        // Ensure that the list of countries returned does not exceed the specified limit
        assertTrue(countries.size() <= 10);

        // Ensure that the list of countries returned is sorted in descending order by population
        Country prevCountry = null;
        for (Country country : countries) {
            if (prevCountry != null) {
                assertTrue(prevCountry.country_population >= country.country_population);
            }
            prevCountry = country;
        }
    }

    /**
     * Tests that the getAllCountriesPopRegion() method returns all countries from a given region
     * when limit is set to 0. In this case, the test checks if the method returns a non-empty list
     * for the "Western Europe" region, which should contain all countries in that continent, regardless of
     * population size.
     */
    @Test
    void testGetAllCountriesPopRegionWithValidRegionAndLimitOfZero() {
        ArrayList<Country> countries = countryQuery.getAllCountriesPopRegion(con, "Western Europe", 0);
        assertNotNull(countries);
        assertFalse(countries.isEmpty());
    }

    /**
     * Test that the getAllCountriesPopRegion() method returns null when the limit is negative.
     */
    @Test
    void testGetAllCountriesPopRegionWithValidRegionAndNegativeLimit() {
        ArrayList<Country> countries = countryQuery.getAllCountriesPopRegion(con, "Western Europe", -1);
        assertNull(countries);
    }

    /**
     * Tests the behavior of getAllCountriesPopRegion() when called with an invalid region name and a limit.
     * Expects an empty ArrayList to be returned.
     */
    @Test
    void testGetAllCountriesPopRegionWithInvalidRegionAndLimit() {
        ArrayList<Country> countries = countryQuery.getAllCountriesPopRegion(con, "Narnia", 10);
        assertNotNull(countries);
        assertTrue(countries.isEmpty());
    }


}
