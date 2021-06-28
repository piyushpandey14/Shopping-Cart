package com.ecommerce.Ecommerce.entity;

import org.springframework.data.redis.core.index.Indexed;

public class CartItem {

	@Indexed
	private String Id;

	private long quantity;

	private Product product;

	private String status;

	public CartItem(long quantity, Product product) {
		super();
		this.quantity = quantity;
		this.product = product;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		return product.getId() == (((CartItem) obj).getProduct().getId());
	}

}