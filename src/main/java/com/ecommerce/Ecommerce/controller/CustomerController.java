package com.ecommerce.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Ecommerce.entity.Customer;
import com.ecommerce.Ecommerce.service.CustomerService;
import com.ecommerce.Ecommerce.utils.UtilityProvider;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	UtilityProvider util;

	@Autowired
	CustomerService customerService;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Customer customer) {
		Customer customerDTO = customerService.findByEmailAndPassword(customer.getEmail(), customer.getPassword());
		if (customerDTO != null) {
			return ResponseEntity.ok(util.generateToken());
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	}

	@PostMapping("/register")
	public ResponseEntity<String> save() {
		try {
			return ResponseEntity.ok(customerService.saveInitialBatch());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
