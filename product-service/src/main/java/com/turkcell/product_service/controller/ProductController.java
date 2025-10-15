package com.turkcell.product_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

        @GetMapping
        public String get(@RequestParam String name) {
            return "Merhaba" + name + " , Product-Service";
        }
    }
