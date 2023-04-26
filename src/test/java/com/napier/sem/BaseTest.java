package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
    }
}
