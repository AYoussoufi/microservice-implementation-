package com.microservice.order.container;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class DatabaseContainer {
    @Container
    public static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0.33");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        //SETTING PROPERTY OF THE IMAGE DB WITH THE URI OF OUR APPLICATION
        dynamicPropertyRegistry.add("spring.datasource.url",mySQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username",mySQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password",mySQLContainer::getPassword);
        dynamicPropertyRegistry.add("order-service",mySQLContainer::getDatabaseName);
    }
}
