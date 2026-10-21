package com.micro.orderService.services;

import com.micro.orderService.client.InventoryClient;
import com.micro.orderService.dto.Inventory;
import com.micro.orderService.exception.MyCustomRuntimeException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@AllArgsConstructor
public class OrderServices {
  //  private  final RestTemplate restTemplate;
    private final RestClient restClient;
    private final InventoryClient inventoryClient;
    public String placeOrder(Long productId) {
     /*
        // Rest Template Example
        String response = restTemplate.getForObject(
                "http://localhost:8081/inventory/" + productId,
                String.class
        );*/

/*
    //Simple RestClient Get
    ResponseEntity<Inventory> entity = restClient.get()
                .uri("http://localhost:8081/inventory/{productId}", productId)
                .retrieve()
                .toEntity(Inventory.class);
        */
        // onStatus Example
//        ResponseEntity<Inventory> entity = restClient.get()
//                .uri("http://localhost:8081/inventory/{productId}", productId)
//                .retrieve()
//                .onStatus(HttpStatusCode::is4xxClientError,((request, response) ->{
//                    throw new MyCustomRuntimeException(response.getStatusCode(),response.getHeaders());
//                } ))
//                .toEntity(Inventory.class);

     /*
     // Exchange Example For Demo
     Object exchange = restClient.get()
                .uri("http://localhost:8081/inventory/{productId}", productId)
                .exchange(((clientRequest, clientResponse) ->
                {
                    if (clientResponse.getStatusCode().is4xxClientError()) {
                        throw new MyCustomRuntimeException(clientResponse.getStatusCode(), clientResponse.getHeaders());
                    } else {
                        return clientResponse.getBody();
                    }
                }));*/


//        updateInventory(entity.getBody());
//
//        return  entity.getBody()!=null && entity.getBody().getQuantity()>0?
//                "Order Placed Successfully":
//                "Product Out Of Stock";


        Inventory inventory = inventoryClient.getInventory(productId);
        int quantity = inventory.getQuantity();
        updateInventory(inventory);
        return quantity>0 ? "Order Placed Successfully" :
                "Product not in stock";

    }

    private void updateInventory(Inventory inventory) {
        inventory.setQuantity(inventory.getQuantity()-1);
        inventoryClient.updateInventory(inventory);
//        restClient.post()
//                .uri("http://localhost:8081/inventory")
//                .body(inventory)
//                .retrieve()
//                .toBodilessEntity();
    }
}
