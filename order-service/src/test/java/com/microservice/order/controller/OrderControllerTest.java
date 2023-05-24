package com.microservice.order.controller;

import com.microservice.order.container.DatabaseContainer;
import com.microservice.order.dto.OrderLineItemsDto;
import com.microservice.order.dto.OrderRequest;
import com.microservice.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class OrderControllerTest extends DatabaseContainer {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private OrderRepository orderRepository;

    private OrderLineItemsDto orderLineItemsDto;

    @Test
    void placeOrder() throws Exception {
        orderRepository.deleteAll();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/order/place-order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        assertTrue(orderRepository.findAll().size() == 1);
    }

    private OrderRequest orderRequest() {

        List<OrderLineItemsDto> orderLineItemsDtoList = new ArrayList<>();
        orderLineItemsDtoList.add(
                OrderLineItemsDto.builder()
                        .orderNumber(UUID.randomUUID().toString())
                        .skuCode("sk001")
                        .price(BigDecimal.valueOf(9.99))
                        .quantity(10)
                        .build()
        );

        return OrderRequest.builder()
                .orderLineItemsDtoList(orderLineItemsDtoList)
                .build();
    }
}