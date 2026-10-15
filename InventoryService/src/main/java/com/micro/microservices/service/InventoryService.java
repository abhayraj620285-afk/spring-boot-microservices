package com.micro.microservices.service;

import com.micro.microservices.model.Inventory;
import com.micro.microservices.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public String addProduct(Inventory inventory) {

        inventoryRepository.save(inventory);

        return "Product added to inventory successfully";
    }

    public Inventory checkStock(Long productId) {

        Optional<Inventory> inventory = inventoryRepository.findByProductId(productId);
        return inventory.get();
    }
    public String  updateProduct(Inventory inventory){
        inventoryRepository.save(inventory);
        return "Product added Successfully";
    }
    public String deleteProduct(Long productId){
        inventoryRepository.deleteById(productId);
        return "Product Deleted";
    }
}