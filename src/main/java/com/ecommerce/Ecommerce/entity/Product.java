package com.ecommerce.Ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 5186013952828648626L;

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "category")
	private String productCategory;

	@Column(name = "description")
	private String productDescription;

	@NotNull(message = "Please provide some price")
	@Min(value = 1, message = "Minimum value should be greater than 1")
	@Column(name = "price")
	private double productPrice;

	@Column(name = "unit")
	private long unitStock;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public long getUnitStock() {
		return unitStock;
	}

	public void setUnitStock(long unitStock) {
		this.unitStock = unitStock;
	}

	public Product(double productPrice, long unitStock, String productDescription, String productCategory) {
		super();
		this.productCategory = productCategory;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.unitStock = unitStock;
	}

	public Product() {

	}

}