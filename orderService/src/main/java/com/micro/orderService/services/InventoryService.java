package com.micro.orderService.services;

import com.micro.orderService.client.InventoryClient;
import com.micro.orderService.dto.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryClient inventoryClient;

     @Retryable(
           retryFor = RuntimeException.class,
           maxAttempts = 3,
           backoff = @Backoff(delay = 2000)
   )
  // @RateLimiter(name = "inventoryService",fallbackMethod = "fallbackMethod" )
    public Inventory getInventory(Long productId) {
        System.out.println("Calling Inventory Service for productId: " + productId);
        return inventoryClient.getInventory(productId);
    }
    public Inventory circuitBreakerFallbackMethod(Long productId, Throwable throwable){
        System.out.println("Fallback Method Called for productId: " + productId);
        return new Inventory(productId.toString(),0);
    }

}
