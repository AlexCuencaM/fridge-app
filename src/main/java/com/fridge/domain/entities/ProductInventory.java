package com.fridge.domain.entities;

public class ProductInventory {
	private int Stock;
	private Products Product;
	
	public int getStock() {
		return Stock;
	}
	
	public void setStock(int stock) {
		Stock = stock;
	}

	public Products getProduct() {
		return Product;
	}

	public void setProduct(Products product) {
		
		Product = product;
	}
	
}
