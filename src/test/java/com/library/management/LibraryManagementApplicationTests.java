package com.library.management;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

// Test de pornire a contextului Spring pentru profilul test
// Incarca contextul Spring pentru test de integrare
@SpringBootTest
// Activeaza profilul de configurare in test
@ActiveProfiles("test")
class LibraryManagementApplicationTests {

    @Test
    void contextLoads() {
    }

}


