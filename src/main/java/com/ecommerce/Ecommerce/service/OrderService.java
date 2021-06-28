package com.ecommerce.Ecommerce.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Ecommerce.entity.Order;
import com.ecommerce.Ecommerce.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public List<Order> findByUserId(long userId) {
		return orderRepository.findByUserId(userId);
	}
}
