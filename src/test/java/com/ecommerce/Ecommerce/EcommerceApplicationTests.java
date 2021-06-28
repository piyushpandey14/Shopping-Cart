package com.ecommerce.Ecommerce;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.Ecommerce.controller.ProductController;

@SpringBootTest
class EcommerceApplicationTests {

	@Autowired
	ProductController productController;
	
	@Test
	void contextLoads() {
		assertThat(productController).isNotNull();
	}

}
