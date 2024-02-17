package com.ShoppingService.OrderService.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppingService.OrderService.OrderServiceApplication;
import com.ShoppingService.OrderService.dto.OrderRequest;
import com.ShoppingService.OrderService.service.service;

@RestController
@RequestMapping("/api/order")
public class orderServiceController {
	
	@Autowired
	service orderService;
	
	@PostMapping
	public String palceOrder(@RequestBody OrderRequest orderRequest){
		
		System.out.println("Inside Controller");
		
		orderService.palceOrder(orderRequest);
		
		return "Order Placed Successfully.";
		
	}

}
