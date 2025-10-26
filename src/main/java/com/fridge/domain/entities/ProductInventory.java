package com.fridge.domain.entities;

import java.util.Date;

public class ProductInventory {
	private int Stock;
	private double Price;
	private Date ExpiryDate;
	private Products Product;
	public ProductInventory() {
		// TODO Auto-generated constructor stub
	}
	public ProductInventory(int stock, double price, Products product) {
		Stock = stock;
		Price = price;
		Product = product;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
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
	public Date getExpiryDate() {
		return ExpiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		ExpiryDate = expiryDate;
	}
}
