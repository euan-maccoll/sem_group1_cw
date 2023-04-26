package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CityTests extends BaseTest
{

    /**
     * Test to see if app can handle if city is null e.g. if query doesn't return a list of cities
     */
    @Test
    void printCityPopTestNull()
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


}