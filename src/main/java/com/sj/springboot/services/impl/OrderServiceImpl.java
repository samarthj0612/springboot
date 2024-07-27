package com.sj.springboot.services.impl;

import com.sj.springboot.models.Order;
import com.sj.springboot.models.Product;
import com.sj.springboot.repositories.OrderRepository;
import com.sj.springboot.repositories.ProductRepository;
import com.sj.springboot.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void placeOrder(Long productId, int quantity) {
        // Fetch product
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        // Check stock
        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock");
        }

        // Reduce stock
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        // Create order
        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        orderRepository.save(order);
    }
}
