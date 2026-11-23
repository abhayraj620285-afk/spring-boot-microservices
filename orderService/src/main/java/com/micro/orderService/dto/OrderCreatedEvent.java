package com.micro.orderService.dto;

import java.io.Serializable;

public record OrderCreatedEvent(Long orderId,Long customerId,Long productId,int quantity) implements Serializable {}
