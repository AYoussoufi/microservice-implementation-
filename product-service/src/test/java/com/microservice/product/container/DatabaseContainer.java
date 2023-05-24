package com.microservice.product.container;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class DatabaseContainer {
    @Container
    public static MongoDBContainer myMongoDbContainer = new MongoDBContainer("mongo:6.0.5");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        //SETTING PROPERTY OF THE IMAGE DB WITH THE URI OF OUR APPLICATION
        dynamicPropertyRegistry.add("spring.data.mongodb.uri",myMongoDbContainer::getReplicaSetUrl);
    }
}
