package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 5000);

    }

    @Test
    void testGetCity()
    {
        City c = app.getCity("Paris");
        assertEquals(c.city_id, 2974);
        assertEquals(c.city_name, "Paris");
        assertEquals(c.city_country_code, "FRA");
        assertEquals(c.city_district, "Île-de-France");
        assertEquals(c.city_population, 2125246);
        assertEquals(c.country_name, "France");
    }
}