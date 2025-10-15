package com.turkcell.order_service.controller;

import com.turkcell.order_service.client.ProductClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderServiceController {
    private final ProductClient productClient;

    public OrderServiceController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping
    String get(){
        String responseFromProductService= productClient.get();
        return "Order service çalıştı, Product serviceden gelen mesaj "+responseFromProductService;
    }
}
