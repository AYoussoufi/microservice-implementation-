package com.microservice.inventory.controller;

import com.microservice.inventory.dto.RequestInventory;
import com.microservice.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;


    @GetMapping("/verify/stock/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-code") String skuCode){
        return inventoryService.isInStock(skuCode);
    }

    @PostMapping("/add-stock")
    @ResponseStatus(HttpStatus.CREATED)
    public String addStock(@RequestBody RequestInventory requestInventory ){
        inventoryService.addStock(requestInventory);
        return "Successfully Created";
    }

}
