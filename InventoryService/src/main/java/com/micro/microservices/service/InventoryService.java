
package com.micro.microservices.service;

import com.micro.microservices.model.Inventory;
import com.micro.microservices.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    // Add a new inventory record
    public String addProduct(Inventory inventory) {
        // Always generate a new ID for a new record
        inventory.setId(null);
        inventoryRepository.save(inventory);
        return "Product added to inventory successfully";
    }

    // Check stock by product ID
    public Inventory checkStock(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Inventory not found for product ID: " + productId
                        ));
    }

    // Update an existing inventory record
    public String updateProduct(Long id, Inventory updatedInventory) {
        Inventory existing = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Inventory not found with ID: " + id
                        ));

        existing.setProductId(updatedInventory.getProductId());
        existing.setQuantity(updatedInventory.getQuantity());

        inventoryRepository.save(existing);
        return "Inventory updated successfully";
    }

    // Delete an inventory record by its inventory ID
    public String deleteProduct(Long id) {
        if (!inventoryRepository.existsById(id)) {
            throw new RuntimeException("Inventory not found with ID: " + id);
        }

        inventoryRepository.deleteById(id);
        return "Inventory deleted successfully";
    }
}
