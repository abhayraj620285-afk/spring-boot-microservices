package com.micro.orderService.client;

import com.micro.orderService.dto.Inventory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="inventory-service" ,url="http://localhost:8081")
public interface InventoryClient {
    @GetMapping("inventory")
    Inventory getInventory(@PathVariable Long productId);

}
