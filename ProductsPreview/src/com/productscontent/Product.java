package com.productscontent;

import android.annotation.SuppressLint;

@SuppressLint("NewApi")
public class Product {
	public String id;
	private String name;
	private ProductCategory category;
	private int quantity;
	private float price;
	private static long nextId;
	
	public Product(String name, ProductCategory category, int quantity, float price) {
		this.id = String.format("%09d", nextId);
		nextId++;
		this.setName(name);
		this.setCategory(category);
		this.setQuantity(quantity);
		this.setPrice(price);
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			name = this.id;
		}
		
		this.name = name;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	
	public String getProductInfo() {
		return String.format(
				" Product: %s \n Name: %s \n Category: %s \n Quantity: %d \n Price: %.2f$",
				this.id,
				this.getName(),
				this.getCategory().toString(),
				this.getQuantity(),
				this.getPrice()) ;
	}
	
	@Override
	public String toString() {
		return String.format(
				" Product: %s \n Category: %s",
				this.getName(),
				this.getCategory().toString());
	}
}
