package com.napier.sem;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class LanguageTests extends BaseTest {

    /*
     * ==============================================================
     * PRINT METHOD TESTS
     * ==============================================================
     */

    /**
     * Test to see if app can handle if language array is null
     */
    @Test
    void printLanguagesTestNull() {
        languageQuery.printLanguages(null);
    }

    /**
     * Test to see if app can handle if language array is empty
     */
    @Test
    void printLanguagesTestEmpty() {
        ArrayList<Language> languages = new ArrayList<Language>();
        languageQuery.printLanguages(languages);
    }

    /**
     * Test to see if app can print a list which includes null value
     */
    @Test
    void printLanguagesTestContainsNulls() {
        ArrayList<Language> languages = new ArrayList<Language>();
        Language l = new Language();
        l.language = null;
        l.total_speakers = "1000";
        l.percentage = "10.00";
        languages.add(l);
        languageQuery.printLanguages(languages);
    }

    /**
     * Test to ensure that the printLanguages method handles invalid Language objects correctly.
     * This test would create a Language object with null values for all properties and ensure that
     * the method handles it gracefully (e.g. prints a placeholder value instead of crashing).
     */
    @Test
    void testPrintLanguagesHandlesInvalidLanguage() {
        ArrayList<Language> languages = new ArrayList<Language>();
        Language invalidLanguage = new Language();
        invalidLanguage.language = null;
        invalidLanguage.total_speakers = null;
        invalidLanguage.percentage = null;
        languages.add(invalidLanguage);
        languageQuery.printLanguages(languages);
    }

    /**
     * Test to see if app runs if language array contains all non-null (i.e. normal running)
     */
    @Test
    void printLanguagesNormal() {
        ArrayList<Language> languages = new ArrayList<Language>();
        Language l1 = new Language();
        l1.language = "English";
        l1.total_speakers = "1129000000";
        l1.percentage = "16.54";
        Language l2 = new Language();
        l2.language = "Chinese";
        l2.total_speakers = "1090600000";
        l2.percentage = "15.99";
        languages.add(l1);
        languages.add(l2);
        languageQuery.printLanguages(languages);
    }

    /*
     * ==============================================================
     * GET ALL LANGUAGES METHOD TESTS
     * ==============================================================
     */

    /**
     * Tests if the getLanguages() method retrieves a non-empty list of languages.
     */
    @Test
    void testGetLanguages() {
        // Ensure that the list of languages returned is not null
        ArrayList<Language> languages = languageQuery.getLanguages(con);
        assertNotNull(languages);

        // Ensure that the list of languages returned is not empty
        assertFalse(languages.isEmpty());

        // Ensure that the list of languages returned contains the expected languages
        ArrayList<String> expectedLanguages = new ArrayList<String>();
        expectedLanguages.add("Chinese");
        expectedLanguages.add("English");
        expectedLanguages.add("Hindi");
        expectedLanguages.add("Spanish");
        expectedLanguages.add("Arabic");
        for (Language language : languages) {
            assertTrue(expectedLanguages.contains(language.language));
        }
    }

    /**
     * Test to ensure that the getLanguages method returns the correct number of languages.
     * This test would check if the size of the ArrayList returned by getLanguages is equal to the expected
     * number of languages (in this case, 5).
     */
    @Test
    void testGetLanguagesReturnsCorrectNumberOfLanguages() {
        ArrayList<Language> languages = languageQuery.getLanguages(con);
        assertNotNull(languages);
        assertEquals(5, languages.size());
    }

    /**
     * Test to ensure that the getLanguages method handles null Connection objects correctly.
     * This test would pass a null Connection object to the getLanguages method and ensure that it returns null.
     */
    @Test
    void testGetLanguagesWithNullConnectionReturnsNull() {
        ArrayList<Language> languages = languageQuery.getLanguages(null);
        assertNull(languages);
    }


}