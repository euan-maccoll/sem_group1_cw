package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
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
}
