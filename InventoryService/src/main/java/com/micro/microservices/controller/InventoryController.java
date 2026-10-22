
package com.micro.microservices.controller;

import com.micro.microservices.model.Inventory;
import com.micro.microservices.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{productId}")
    public Inventory checkInventory(
            @PathVariable("productId") Long productId) throws InterruptedException {
        Thread.sleep(5000);
        return inventoryService.checkStock(productId);
    }

    @PostMapping
    public String addProduct(@RequestBody Inventory inventory) {
        return inventoryService.addProduct(inventory);
    }

    @PutMapping("/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @RequestBody Inventory inventory) {

        return inventoryService.updateProduct(id, inventory);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return inventoryService.deleteProduct(id);
    }
}
