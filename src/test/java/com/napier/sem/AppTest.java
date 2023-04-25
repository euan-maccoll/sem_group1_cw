package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    /**
     * Test to see if app can handle if city is null e.g. if query doesn't return a list of cities
     */
    /*@Test
    void printCityPopTestNull()
    {
        app.printCityPop(null);
    }

    /**
     * Test to see if app can handle if cities array is empty
     */
    /*@Test
    void printCityPopTestEmpty()
    {
        ArrayList<City> Cities = new ArrayList<City>();
        app.printCityPop(Cities);
    }

    /**
     * Test to see if app can print a list which includes null value
     */
    /*@Test
    void printCityPopTestContainsNulls()
    {
        ArrayList<City> Cities = new ArrayList<City>();
        Cities.add(null);
        app.printCityPop(Cities);
    }

    /**
     * Test to see if app runs if city array contains all non-null (i.e. normal running)
     */
    /*@Test
    void printCityPop()
    {
        ArrayList<City> Cities = new ArrayList<City>();
        City c = new City();
        c.city_id = 2974;
        c.city_name = "Paris";
        c.city_country_code = "FRA";
        c.city_district = "Île-de-France";
        c.city_population = 2125246;
        Cities.add(c);
        app.printCityPop(Cities);
    }*/


}