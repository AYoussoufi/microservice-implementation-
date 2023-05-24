package com.microservice.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseInventory {

    private Long id;
    private String skuCode;
    private Integer quantity;
}
