package com.sj.springboot.controllers;

import com.sj.springboot.services.OrderService;
import com.sj.springboot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public String placeOrder(@RequestParam String productName, @RequestParam int quantity) {
        try {
            productService.addProduct(productName, quantity);
            return "Product successfully added";
        } catch (Exception e) {
            return "Failed to add product: " + e.getMessage();
        }
    }
}
