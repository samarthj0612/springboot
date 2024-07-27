package com.sj.springboot.services;

public interface OrderService {
    void placeOrder(Long productId, int quantity);
}
