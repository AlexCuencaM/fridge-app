package com.fridge.domain.entities;

public class ProductInventory {
	private int Stock;
	private Product Product;
	
	public int getStock() {
		return Stock;
	}
	
	public void setStock(int stock) {
		Stock = stock;
	}

	public Product getProduct() {
		return Product;
	}

	public void setProduct(Product product) {
		
		Product = product;
	}
	
}
