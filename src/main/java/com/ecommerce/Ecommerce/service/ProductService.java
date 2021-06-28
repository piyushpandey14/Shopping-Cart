package com.ecommerce.Ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Ecommerce.entity.Product;
import com.ecommerce.Ecommerce.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	private static List<Product> products = new ArrayList<>();

	static {
		products.add(new Product(35.75d, 1000, "Pears baby soap for Kids", "category1"));
		products.add(new Product(45.50d, 500, "Signal Tooth Brushes Size in (L, M, S)", "category1"));
		products.add(new Product(1500.0d, 100, "Casual Shirt imported from France", "category2"));
		products.add(new Product(1000.0d, 400, "Leather bag imported from USA ", "category3"));
		products.add(new Product(450.0d, 800, "Hot Water Bottles", "category1"));
		products.add(new Product(2500.0d, 800, "Imported wrist watches from swiss", "category2"));
		products.add(new Product(45000.0d, 800, "3G/4G capability", "category4"));
		products.add(new Product(300.0d, 800, "Head and Shoulders Shampoo", "category3"));
		products.add(new Product(550.0d, 800, "Imported Leather Wallets from AUS", "category1"));
		products.add(new Product(85000.0d, 800, "Imported Canon camera from USA", "category4"));
	}

	public String saveInitialBatch() {
		productRepository.saveAll(products);

		return "Done";
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public void deleteProduct(long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent())
			productRepository.delete(product.get());
	}
}
