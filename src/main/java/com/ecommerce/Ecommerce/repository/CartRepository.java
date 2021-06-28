package com.ecommerce.Ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.Ecommerce.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {

	Cart findByCustomerId(long customerId);
}
