package com.micro.orderService.controller;

import com.micro.orderService.OrderServiceApplication;
import com.micro.orderService.services.OrderServices;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServices orderServices;
    @PostMapping("/{productId}")
    public String placeOrder(@PathVariable Long productId){
        return orderServices.placeOrder(productId);
    }
}
