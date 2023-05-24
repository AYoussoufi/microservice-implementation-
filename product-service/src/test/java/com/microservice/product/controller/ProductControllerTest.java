package com.microservice.product.controller;

import com.microservice.product.dto.ProductRequest;
import com.microservice.product.dto.ProductResponse;
import com.microservice.product.repository.ProductRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepository productRepository;
    private ObjectMapper objectMapper = new ObjectMapper();





    @Test
    void createProduct() throws Exception {
        //BUILDING THE JSON BODY
        String jsonProductRequest = objectMapper.writeValueAsString(getProductRequest());
        //DELETING ALL BEFORE TESTING
        productRepository.deleteAll();
        //TEST IF REQUEST IS PROCESSED
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product/add-product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonProductRequest))
                .andExpect(status().isCreated());
        //TEST IF THE ELEMENT IS ADDED
        Assertions.assertTrue(productRepository.findAll().size() == 1);
    }

    @Test
    void getListProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/list-product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());
    }

    private ProductRequest getProductRequest(){
        return ProductRequest.builder()
                .name("Jon Doe")
                .description("Description yo")
                .price(BigDecimal.valueOf(13))
                .build();
    }
}