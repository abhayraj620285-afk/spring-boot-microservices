package com.micro.microservices.controller;

import com.micro.microservices.model.Inventory;
import com.micro.microservices.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@AllArgsConstructor
public class InventoryController {
    public final InventoryService inventoryService;
    @GetMapping("/{productId}")
    public Inventory checkInventory(@PathVariable Long  productId){
        return inventoryService.checkStock(productId);
    }
    @PostMapping
    public String addProduct(@RequestBody Inventory inventory){
        return inventoryService.addProduct(inventory);
    }
}
