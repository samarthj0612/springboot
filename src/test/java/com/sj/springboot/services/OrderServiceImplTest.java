package com.sj.springboot.services;

import com.sj.springboot.models.Order;
import com.sj.springboot.models.Product;
import com.sj.springboot.repositories.OrderRepository;
import com.sj.springboot.repositories.ProductRepository;
import com.sj.springboot.services.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Product product;

    @BeforeEach
    void setUp() {
        // Sample product setup for testing
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setQuantity(10);
    }

    @Test
    void placeOrder_Success() {
        // Arrange
        Long productId = 1L;
        int orderQuantity = 5;

        // Mocking the productRepository to return the product
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        orderService.placeOrder(productId, orderQuantity);

        // Assert
        // Check if the product's quantity was reduced
        verify(productRepository).save(product);
        verify(orderRepository).save(any(Order.class));
        assert product.getQuantity() == 5; // Check if the stock is reduced correctly
    }

    @Test
    void placeOrder_ProductNotFound() {
        // Arrange
        Long productId = 2L;  // Non-existent product ID
        int orderQuantity = 5;

        // Mocking productRepository to return empty for non-existent product
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            orderService.placeOrder(productId, orderQuantity);
        });

        // Verify that the product was not saved or the order was not created
        verify(productRepository, never()).save(any(Product.class));
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void placeOrder_NotEnoughStock() {
        // Arrange
        Long productId = 1L;
        int orderQuantity = 15;  // More than available stock

        // Mocking the productRepository to return the product
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            orderService.placeOrder(productId, orderQuantity);
        });

        // Verify that the product was not saved and order was not created
        verify(productRepository, never()).save(any(Product.class));
        verify(orderRepository, never()).save(any(Order.class));
    }
}
