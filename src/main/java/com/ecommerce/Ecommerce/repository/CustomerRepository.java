package com.ecommerce.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.Ecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmailAndPassword(String email, String password);
}
