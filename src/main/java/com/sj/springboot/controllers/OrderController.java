package com.sj.springboot.controllers;

import com.sj.springboot.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public String placeOrder(@RequestParam Long productId, @RequestParam int quantity) {
        try {
            orderService.placeOrder(productId, quantity);
            return "Order placed successfully";
        } catch (Exception e) {
            return "Failed to place order: " + e.getMessage();
        }
    }
}
