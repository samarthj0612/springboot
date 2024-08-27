package com.sj.springboot.services;

import com.sj.springboot.models.Product;
import com.sj.springboot.repositories.ProductRepository;
import com.sj.springboot.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void addProduct_shouldSaveProduct() {
        String productName = "Test Product";
        int quantity = 10;

        productService.addProduct(productName, quantity);

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void addProduct_shouldSaveProductWithCorrectDetails() {
        String productName = "Test Product";
        int quantity = 10;

        productService.addProduct(productName, quantity);

        // Assert: Capture the Product object passed to the save method
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productCaptor.capture());

        Product savedProduct = productCaptor.getValue();
        assertEquals("", productName, savedProduct.getName());
        assertEquals("", quantity, savedProduct.getQuantity());
    }

    @Test
    void addProduct_withEmptyProductName_shouldThrowException() {
        String productName = "";
        int quantity = 10;

        assertThrows(IllegalArgumentException.class, () -> {
            productService.addProduct(productName, quantity);
        });
    }

    @Test
    void addProduct_withNegativeQuantityValue_shouldThrowException() {
        String productName = "Testing";
        int quantity = -10;

        assertThrows(IllegalArgumentException.class, () -> {
            productService.addProduct(productName, quantity);
        });
    }

}
