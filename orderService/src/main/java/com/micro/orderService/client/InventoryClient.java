package com.micro.orderService.client;

import com.micro.orderService.client.config.InventoryFeignClientConfig;
import com.micro.orderService.dto.Inventory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="inventory-service" , configuration = InventoryFeignClientConfig.class)
public interface InventoryClient {
    @GetMapping("/inventory/{productId}")
    Inventory getInventory(@PathVariable Long productId);

    @PostMapping("/inventory")
    String updateInventory(@RequestBody Inventory inventory);
}
