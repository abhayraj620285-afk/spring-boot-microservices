package com.micro.orderService.services;

import com.micro.orderService.client.InventoryClient;
import com.micro.orderService.dto.Inventory;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryClient inventoryClient;

     @Retryable(
           retryFor = RuntimeException.class,
           maxAttempts = 3,
           backoff = @Backoff(delay = 2000)
   )
  // @RateLimiter(name = "inventoryService",fallbackMethod = "fallbackMethod"
    // @CircuitBreaker(name="inventoryServiceCircuitBreaker",fallbackMethod = "circuitBreakerFallBackMethod")
//     public Inventory getInventory(Long productId) {
//         System.out.println("Calling Inventory Service for productId: " + productId);
//         return inventoryClient.getInventory(productId);
//     }
     // It is asynchronous method . We have to use completable future to implement this
     @TimeLimiter(name = "inventoryServiceTimeLimiter" , fallbackMethod = "timeLimiterFallbackMethod")
    public CompletableFuture<Inventory> getInventory(Long productId) {
        System.out.println("Calling Inventory Service for productId: " + productId);
        return CompletableFuture.supplyAsync(() -> inventoryClient.getInventory(productId));
    }
//    public Inventory circuitBreakerFallbackMethod(Long productId, Throwable throwable){
//        System.out.println("Fallback Method Called for productId: " + productId);
//        return new Inventory(productId.toString(),0);
//    }
//    public Inventory circuitBreakerFallBackMethod(Long productId,Throwable throwable){
//        System.out.println("Fallback Method called for inventoryService : " + productId);
//        return new Inventory(productId.toString(),0);
//    }
    public CompletableFuture<Inventory> timeLimiterFallbackMethod(Long productId, Throwable throwable){
        System.out.println("Fallback Method called for inventoryService : " + productId);
        Inventory inventory =  new Inventory(productId.toString(),0);
        return CompletableFuture.completedFuture(inventory);
    }

}
