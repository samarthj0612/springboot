package com.sj.springboot.repositories;

import com.sj.springboot.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}