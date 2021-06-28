package com.ecommerce.Ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Ecommerce.entity.Customer;
import com.ecommerce.Ecommerce.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	private static List<Customer> customers = new ArrayList<>();

	static {
		customers.add(new Customer("user1@gmail.com", "user1", "password1"));
		customers.add(new Customer("user2@gmail.com", "user2", "password2"));
	}

	public String saveInitialBatch() {
		repository.saveAll(customers);
		return "Done";
	}

	public Customer findByEmailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

}