package com.napier.sem;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class CountryTests extends BaseTest {

    /**
     * Test to see if app can handle if country is null e.g. if query doesn't return a list of cities
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


}
