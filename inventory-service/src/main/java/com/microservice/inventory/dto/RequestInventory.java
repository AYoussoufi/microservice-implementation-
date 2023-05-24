package com.microservice.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestInventory {

    private String skuCode;
    private Integer quantity;
}
