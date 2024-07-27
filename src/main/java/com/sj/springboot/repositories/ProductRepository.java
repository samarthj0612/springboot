package com.sj.springboot.repositories;

import com.sj.springboot.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}