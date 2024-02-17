package com.ShoppingService.OrderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ShoppingService.OrderService.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	
}
