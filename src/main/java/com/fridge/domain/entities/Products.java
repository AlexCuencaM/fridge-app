package com.fridge.domain.entities;

import java.util.Date;

public class Products {
	private String Name;
	private Date ExpiryDate; 
	private Categories Category;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Date getExpiryDate() {
		return ExpiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		ExpiryDate = expiryDate;
	}
	public Categories getCategory() {
		return Category;
	}
	public void setCategory(Categories category) {
		Category = category;
	}
}