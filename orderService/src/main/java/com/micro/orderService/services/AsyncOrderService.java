package com.micro.orderService.services;

import com.micro.orderService.dto.OrderCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AsyncOrderService {
    private final RabbitTemplate rabbitTemplate;
    public String placeOrder(Long productId){
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(1L,1L,productId,1);
        rabbitTemplate.convertAndSend(
                "orders.exchange",
                "order.created",
                orderCreatedEvent
        );

         return "async order placed successfully";
    }
}
