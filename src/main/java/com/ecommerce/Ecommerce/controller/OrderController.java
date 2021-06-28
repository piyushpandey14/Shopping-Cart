package com.ecommerce.Ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Ecommerce.entity.Order;
import com.ecommerce.Ecommerce.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/")
	public List<Order> findAll() {
		return orderService.findAll();
	}

	@GetMapping("/order")
	public ResponseEntity<List<Order>> findByUserId(@RequestHeader("user-id") long userId) {
		return ResponseEntity.ok(orderService.findByUserId(userId));
	}
}
