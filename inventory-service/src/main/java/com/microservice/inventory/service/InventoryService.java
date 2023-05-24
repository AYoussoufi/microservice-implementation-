package com.microservice.inventory.service;

import com.microservice.inventory.dto.RequestInventory;
import com.microservice.inventory.model.Inventory;
import com.microservice.inventory.repository.InventoryRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly=true)
    public boolean isInStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

    @Transactional
    public void addStock(RequestInventory requestInventory) {
        if(inventoryRepository.findBySkuCode(requestInventory.getSkuCode()).isPresent()){
            throw new RuntimeException("This item already exist");
        }
        inventoryRepository.save(Inventory.builder()
                        .quantity(requestInventory.getQuantity())
                        .skuCode(requestInventory.getSkuCode())
                .build());
    }
}
