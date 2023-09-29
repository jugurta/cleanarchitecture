package com.jai.cleanarchitecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CleanarchitectureApplicationTests {

    @Autowired
    CleanarchitectureApplication cleanarchitectureApplication;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(cleanarchitectureApplication);
    }

}
