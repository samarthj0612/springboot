package com.sj.springboot.services.impl;

import com.sj.springboot.models.Product;
import com.sj.springboot.repositories.ProductRepository;
import com.sj.springboot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(String productName, int quantity) {
        Product product = new Product();
        product.setName(productName);
        product.setQuantity(quantity);
        productRepository.save(product);
    }
}
