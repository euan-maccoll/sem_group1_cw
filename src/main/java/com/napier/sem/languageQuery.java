package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This class contains methods for querying and printing language data.
 */
public class languageQuery {

    /**
     * Prints a list of languages, their total number of speakers, and the percentage of the world population that speaks them.
     * @param languages The list of languages to print.
     */
    public static void printLanguages(ArrayList<Language> languages)

    {   //Check language is not null
        if (languages == null) {
            System.out.println("No languages");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-30s %-30s ", "Language", "Population", "Percentage"));
        // Loop over all languages in the list
        for (Language l : languages)
        {
            String c_string =
                    String.format("%-30s %-30s %-30s ", l.language, l.total_speakers, l.percentage);
            System.out.println(c_string);
        }
    }

    /**
     * Gets a list of languages, their total number of speakers, and the percentage of the world population that speaks them.
     * @param con The database connection object.
     * @return An ArrayList of Language objects.
     */
    public static ArrayList<Language> getLanguages(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cl.language, SUM(c.population * (cl.percentage / 100)) AS total_speakers, ROUND((SUM(c.population * (cl.percentage / 100)) / (SELECT SUM(population) FROM country)) * 100, 2) AS world_percentage "
                            + "FROM countrylanguage cl "
                            + "INNER JOIN country c ON c.code = cl.CountryCode "
                            + "INNER JOIN country cp ON cp.code = cl.CountryCode "
                            + "WHERE cl.language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') "
                            + "GROUP BY cl.language "
                            + "ORDER BY total_speakers DESC ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract language information
            ArrayList<Language> languages = new ArrayList<Language>();
            while (rset.next()) {
                Language l = new Language();
                l.language = rset.getString("language");
                l.total_speakers = rset.getString("total_speakers");
                l.percentage = rset.getString("world_percentage");
                languages.add(l);
            }
            return languages;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get language details");
            return null;
        }
    }
}
